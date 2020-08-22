package com.pch.test.script;

import static com.pch.utilities.Log.Log;
import static com.pch.utilities.Log.endTestCase;
import static com.pch.utilities.Log.startTestCase;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.pch.configuration.testpages.RFTestPage;
import com.pch.configuration.testpages.RFTestPage.ResponseData;
import com.pch.joomla.configuration.JoomlaAdministrator;
import com.pch.joomla.configuration.MidTierApiConfiguration.ServiceURL;
import com.pch.utilities.Action_Wrapper;
import com.pch.utilities.Database_Configuration;
import com.pch.utilities.GroupName;
import com.pch.utilities.Jmeter_Xml_Parser;

public class B_34146_MT2_AppLoad_SPLIT_Response_Data extends Action_Wrapper {
	Jmeter_Xml_Parser xp = new Jmeter_Xml_Parser();
	JoomlaAdministrator joomla = new JoomlaAdministrator();
	RFTestPage RF = new RFTestPage();
	Database_Configuration DB = new Database_Configuration();

	String thread_Repeat_Visit_1[] = { "Repeat Visit 1" };
	String thread_Repeat_Visit_2[] = { "Repeat Visit 2" };
	String thread_Repeat_Visit_3[] = { "Repeat Visit 3" };
	String thread_Asset_Size_2x[] = { "Asset Size 2x" };
	String thread_Asset_Size_4x[] = { "Asset Size 4x" };
	String thread_Without_AssetSize[] = { "Without AssetSize" };
	String thread_Rules_Facts_base_URL[] = { "Rules Facts base URL" };
	String thread_Rules_Facts_base_URL_Alter[] = { "Rules Facts base URL_Alter" };

	String scriptName = "B_34146_MT2_AppLoad_SPLIT_Response_Data";
	String GMT;
	String Email,response;

	@BeforeClass
	public void setup() throws Exception {

		startTestCase("B-34146 [MT2] AppLoad- SPLIT_Response_Data");
		createAccessTokenForAllApp("pchapp");

		response=RF.createFullRegUserAPI();
		Email = RF.getResponseValueAPI(response, ResponseData.Email);
		GMT = RF.getResponseValueAPI(response, ResponseData.GlobalMemberToken);
		writeInProprtyFile("Email", getEncryptedData("Access_Token_PCHAPP",RF.getResponseValueAPI(response, ResponseData.Email)));
		writeInProprtyFile("GMT", getEncryptedData("Access_Token_PCHAPP",RF.getResponseValueAPI(response, ResponseData.GlobalMemberToken)));
		copyAndRenameFile();

		enableThread(scriptName + ".jmx", thread_Repeat_Visit_1);
	}

	@Test(groups = { GroupName.RegFoundation, GroupName.Game })
	public void strory_B_34146_MT2_AppLoad_SPLIT_Response_Data()
			throws Exception {

		/*
		 * Validate 1st Visit
		 */

		// Run JMeter Script
		Log.info("Start Executing Jmeter Script");
		CallJmeterBuild(scriptName + ".jmx");

		Assert.assertEquals("false", xp.Xml_Parser(scriptName + ".xml"));
		Log.info("Validating assertion ");

		Assert.assertTrue(DB.phpctrl_app_bonus_items_daily(GMT,"get Bonus data").contains("\"repeatVisitDays\":1"));
		DB.phpctrl_app_bonus_items_daily(GMT, "update date played");

		/*
		 * Validate 2nd Visit
		 */
		enableThread(scriptName + ".jmx", thread_Repeat_Visit_2);

		// Run JMeter Script
		Log.info("Start Executing Jmeter Script");
		CallJmeterBuild(scriptName + ".jmx");

		Assert.assertEquals("false", xp.Xml_Parser(scriptName + ".xml"));
		Log.info("Validating assertion ");

		Assert.assertTrue(DB.phpctrl_app_bonus_items_daily(GMT,
				"get Bonus data").contains("\"repeatVisitDays\":2"));
		DB.phpctrl_app_bonus_items_daily(GMT, "update date played");

		/*
		 * Validate 3rd Visit
		 */
		enableThread(scriptName + ".jmx", thread_Repeat_Visit_3);

		// Run JMeter Script
		Log.info("Start Executing Jmeter Script");
		CallJmeterBuild(scriptName + ".jmx");

		Assert.assertEquals("false", xp.Xml_Parser(scriptName + ".xml"));
		Log.info("Validating assertion ");

		Assert.assertTrue(DB.phpctrl_app_bonus_items_daily(GMT,
				"get Bonus data").contains("\"repeatVisitDays\":3"));

		/*
		 * assert Size 2x
		 */
		openBrowser("chrome");
		joomla.login.login();
		joomla.article.navigateToArticleManager();

		joomla.article.selectcategoryDropDown("- Pch App");
		joomla.article.search("Pch App Test 1", "search");
		joomla.article.articleStatus("unpublish");
		joomla.article.selectcategoryDropDown("- Pch App 2");
		joomla.article.search("PCH App 2.0 Android", "search");
		joomla.article.articleStatus("publish");		
		joomla.article.search("PCH App 2.0 Android", "ALIAS");
		joomla.alias.finishPublishingDate("");
		joomla.alias.saveAndClose();

		joomla.article.search("PCH App 2.0 Android");
		joomla.PA.FeatureFieldremove();
		joomla.PA.TileFieldremove();
		joomla.PA.bonusActionArticlesRemove();
		joomla.PA.saveArticle();
		joomla.PA.featureFieldSelectArticle("1MM Gold Seal Path");
		joomla.PA.tileFieldSelectArticle("20K Secret Stash Path", 0);

		joomla.PA.bonusActionArticle("PCHAPP - Bonus Reward for Set Password - Trigger","PCHAPP - Bonus Reward for Set Password - Trigger");
		joomla.article.saveArticle();

		waitForPageToLoad(MTDriver);
		joomla.article.selectcategoryDropDown("PchApp");

		joomla.article.search("1MM Gold Seal Path");
		joomla.ContentPath.removeActionArticle();
		joomla.ContentPath.selectActionArticleDropdown("1MM Gold Seal", 0);
		joomla.article.saveArticle();

		joomla.article.search("1MM Gold Seal");
		joomla.actions.addActionType("crosspromo");
		joomla.actions.addAction("openwebview");
		joomla.actions.addActionURL("https://spectrum.qa.pch.com/Path/PCHAppSupGoldSeal/Cert.aspx?tid=21001d07-e05d-4ce3-8c56-e0d5f276fe44");
		joomla.actions.addCredentials("em-e");

		joomla.actions.addcrmN("crmN detail");
		joomla.actions
				.addDescription("Want To See The Prize Patrol At Your Door?");

		joomla.actions.addRulesFactsId("22");
		joomla.actions.addPCHGameClassName("33");
		joomla.actions.featureIcon2x("upload");
		joomla.actions.featureIcon4x("upload");
		joomla.actions.tileIcon2x("upload");
		joomla.actions.tileIcon4x("upload");
		joomla.actions.addTopColor("#ffffff");
		joomla.actions.addBottomColor("#000000");
		joomla.actions.addButtonColor("#bce4ac");
		joomla.actions.addButtonText("Show Me The Seal!");
		joomla.article.saveArticle();

		//DB.flush("PchApp", "DELETE FROM `phpctrl_app_content_tree`");
		enableThread(scriptName + ".jmx", thread_Asset_Size_2x);

		// Run JMeter Script
		Log.info("Start Executing Jmeter Script");
		CallJmeterBuild(scriptName + ".jmx");

		Assert.assertEquals("false", xp.Xml_Parser(scriptName + ".xml"));
		Log.info("Validating assertion ");

		enableThread(scriptName + ".jmx", thread_Asset_Size_4x);

		// Run JMeter Script
		Log.info("Start Executing Jmeter Script");
		CallJmeterBuild(scriptName + ".jmx");

		Assert.assertEquals("false", xp.Xml_Parser(scriptName + ".xml"));
		Log.info("Validating assertion ");

		/*
		 * Without_AssetSize
		 */
		enableThread(scriptName + ".jmx", thread_Without_AssetSize);

		// Run JMeter Script
		Log.info("Start Executing Jmeter Script");
		CallJmeterBuild(scriptName + ".jmx");

		Assert.assertEquals("false", xp.Xml_Parser(scriptName + ".xml"));
		Log.info("Validating assertion ");

		/*
		 * Rules_Facts_base_URL_alter
		 */

		joomla.MTAPIConfig.navigateToAPIComponents();
		joomla.MTAPIConfig.servicesUrl("Alter URL",
				ServiceURL.RulesFacts_base_URL);
		joomla.MTAPIConfig.saveAndClose();
		enableThread(scriptName + ".jmx", thread_Rules_Facts_base_URL_Alter);
		GETclearMemCache("Access_Token_PCHAPP");

		// Run JMeter Script
		Log.info("Start Executing Jmeter Script");
		CallJmeterBuild(scriptName + ".jmx");

		Assert.assertEquals("false", xp.Xml_Parser(scriptName + ".xml"));
		Log.info("Validating assertion ");

		/*
		 * Rules_Facts_base
		 */

		joomla.MTAPIConfig.navigateToAPIComponents();
		joomla.MTAPIConfig.servicesUrl("Valid URL",
				ServiceURL.RulesFacts_base_URL);
		joomla.MTAPIConfig.saveAndClose();
		enableThread(scriptName + ".jmx", thread_Rules_Facts_base_URL);
		GETclearMemCache("Access_Token_PCHAPP");
		// Run JMeter Script
		Log.info("Start Executing Jmeter Script");
		CallJmeterBuild(scriptName + ".jmx");

		Assert.assertEquals("false", xp.Xml_Parser(scriptName + ".xml"));
		Log.info("Validating assertion ");
	}

	@AfterClass
	public void tearDown() throws IOException, Exception {
		closeallwindows();
		endTestCase("End");
	}
}