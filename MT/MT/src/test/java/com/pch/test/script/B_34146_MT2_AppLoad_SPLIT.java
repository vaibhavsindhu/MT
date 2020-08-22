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
import com.pch.utilities.Action_Wrapper;
import com.pch.utilities.AppData;
import com.pch.utilities.Database_Configuration;
import com.pch.utilities.GroupName;
import com.pch.utilities.Jmeter_Xml_Parser;

public class B_34146_MT2_AppLoad_SPLIT extends Action_Wrapper {
	Jmeter_Xml_Parser xp = new Jmeter_Xml_Parser();
	JoomlaAdministrator joomla = new JoomlaAdministrator();
	RFTestPage RF = new RFTestPage();
	Database_Configuration DB = new Database_Configuration();

	String thread_AppLoad[] = { "AppLoad" };
	String thread_Without_GlobalMemberToken [] = { "Without GlobalMemberToken " };
	String thread_oneFA_Ta_BA[] = { "one(FA + TA + BA)" };
	String thread_twoFA_TA_oneBA[] = { "two(FA + TA) + one(BA)" };
	String thread_order_twoFA_TA_oneBA[] = { "order two(FA + TA) + one(BA)" };
	String thread_Remove_all_Tiles_article[] = { "Remove all Tiles article" };
	String thread_Remove_all_Feature_article[] = { "Remove all Feature article" };
	String thread_Add_Duplicate_article[] = { "Add Duplicate article" };

	String scriptName = "B_34146_MT2_AppLoad_SPLIT";
	String GMT;
	String Email,response;

	@BeforeClass
	public void setup() throws Exception {

		startTestCase("B-34146 [MT2] AppLoad- SPLIT_Response_Data");
		createAccessTokenForAllApp("pchapp");
		
		response=RF.createFullRegUserAPI();
		Email = RF.getResponseValueAPI(response, ResponseData.Email);
		writeInProprtyFile("Email", getEncryptedData("Access_Token_PCHAPP",RF.getResponseValueAPI(response, ResponseData.Email)));
		writeInProprtyFile("GMT", getEncryptedData("Access_Token_PCHAPP",RF.getResponseValueAPI(response, ResponseData.GlobalMemberToken)));
		copyAndRenameFile();
		
		enableThread(scriptName + ".jmx", thread_AppLoad);
	}

	@Test(groups = { GroupName.RegFoundation, GroupName.Game })
	public void strory_B_34146_MT2_AppLoad_SPLIT()throws Exception {

		// Run JMeter Script
		Log.info("Start Executing Jmeter Script");
		CallJmeterBuild(scriptName + ".jmx");

		Assert.assertEquals("false", xp.Xml_Parser(scriptName + ".xml"));
		Log.info("Validating assertion ");
		
		/*
		 * thread_Without_GlobalMemberToken
		 */
		enableThread(scriptName + ".jmx", thread_Without_GlobalMemberToken);

		Log.info("Start Executing Jmeter Script");
		CallJmeterBuild(scriptName + ".jmx");

		Assert.assertEquals("false", xp.Xml_Parser(scriptName + ".xml"));
		Log.info("Validating assertion ");
		
		/*
		 * one(FA + TA + BA)
		 */
		openBrowser("chrome");
		joomla.login.login();
		joomla.article.navigateToArticleManager();

		joomla.article.selectcategoryDropDown("- Pch App 2");
		joomla.article.search("Pch App Test 1", "search");
		joomla.article.articleStatus("unpublish");
		joomla.article.search("PCH App", "search");
		joomla.article.articleStatus("unpublish");
		joomla.article.search(AppData.PchApp2.PCHApp, "search");
		joomla.article.articleStatus("publish");
		joomla.article.search(AppData.PchApp2.PCHApp, "ALIAS");
		joomla.alias.finishPublishingDate("");
		joomla.alias.saveAndClose();

		joomla.article.search(AppData.PchApp2.PCHApp);
		joomla.PA.FeatureFieldremove();
		joomla.PA.TileFieldremove();
		joomla.PA.bonusActionArticlesRemove();
		joomla.PA.saveArticle();
		joomla.PA.featureFieldSelectArticle("1MM Gold Seal Path");
		joomla.PA.tileFieldSelectArticle("20K Secret Stash Path", 0);

		joomla.PA.bonusActionArticle("PCHAPP - Bonus Reward for Set Password - Trigger","PCHAPP - Bonus Reward for Set Password - Trigger");
		joomla.article.saveArticle();

		enableThread(scriptName + ".jmx", thread_oneFA_Ta_BA);

		Log.info("Start Executing Jmeter Script");
		CallJmeterBuild(scriptName + ".jmx");

		Assert.assertEquals("false", xp.Xml_Parser(scriptName + ".xml"));
		Log.info("Validating assertion ");
		
		/*
		 * two(FA + TA) + one(BA)
		 */
		
		joomla.article.search(AppData.PchApp2.PCHApp);
		joomla.PA.featureFieldAdd(0);
		joomla.PA.tileFieldAdd(0);
		joomla.PA.saveArticle();
		joomla.PA.featureFieldSelectArticle("First Class Cash Path");
		joomla.PA.tileFieldSelectArticle("Aces High Path", 1);
		joomla.article.saveArticle();

		enableThread(scriptName + ".jmx", thread_twoFA_TA_oneBA);

		Log.info("Start Executing Jmeter Script");
		CallJmeterBuild(scriptName + ".jmx");

		Assert.assertEquals("false", xp.Xml_Parser(scriptName + ".xml"));
		Log.info("Validating assertion ");
		
		/*
		 * order two(FA + TA) + one(BA)
		 */
		joomla.article.search(AppData.PchApp2.PCHApp);
		joomla.PA.saveArticle();
		joomla.PA.featureFieldSelectArticle("First Class Cash Path");
		joomla.PA.featureFieldSelectArticle("1MM Gold Seal Path");
		joomla.article.saveArticle();
		
		enableThread(scriptName + ".jmx", thread_order_twoFA_TA_oneBA);

		Log.info("Start Executing Jmeter Script");
		CallJmeterBuild(scriptName + ".jmx");

		Assert.assertEquals("false", xp.Xml_Parser(scriptName + ".xml"));
		Log.info("Validating assertion ");
		
		/*
		 * Remove all Tiles article
		 */
		joomla.article.search(AppData.PchApp2.PCHApp);
		joomla.PA.TileFieldremove();
		joomla.PA.saveArticle();
		joomla.PA.tileFieldSelectArticle("- Select an Article -", 0);
		joomla.article.saveArticle();
		
		enableThread(scriptName + ".jmx", thread_Remove_all_Tiles_article);

		Log.info("Start Executing Jmeter Script");
		CallJmeterBuild(scriptName + ".jmx");

		Assert.assertEquals("false", xp.Xml_Parser(scriptName + ".xml"));
		Log.info("Validating assertion ");
		
		/*
		 * Remove all Feature article
		 */
		joomla.article.search(AppData.PchApp2.PCHApp);
		joomla.PA.FeatureFieldremove();
		joomla.PA.saveArticle();
		joomla.PA.featureFieldSelectArticle("First Class Cash Path");
		joomla.PA.tileFieldSelectArticle("- Select an Article -", 0);
		joomla.article.saveArticle();

		enableThread(scriptName + ".jmx", thread_Remove_all_Feature_article);

		Log.info("Start Executing Jmeter Script");
		CallJmeterBuild(scriptName + ".jmx");

		Assert.assertEquals("false", xp.Xml_Parser(scriptName + ".xml"));
		Log.info("Validating assertion ");
		
		
		/*
		 * Add Duplicate article
		 */
		joomla.article.search(AppData.PchApp2.PCHApp);
		joomla.PA.FeatureFieldremove();
		joomla.PA.TileFieldremove();
		joomla.PA.saveArticle();
		joomla.PA.featureFieldSelectArticle("1MM Gold Seal Path");
		joomla.PA.tileFieldSelectArticle("20K Secret Stash Path", 0);
		joomla.PA.featureFieldAdd(0);
		joomla.PA.featureFieldSelectArticle("1MM Gold Seal Path");
		joomla.article.saveArticle();
		
		DB.flush("PchApp", "DELETE FROM `phpctrl_app_content_tree`");
		
		enableThread(scriptName + ".jmx", thread_Add_Duplicate_article);

		Log.info("Start Executing Jmeter Script");
		CallJmeterBuild(scriptName + ".jmx");

		Assert.assertEquals("false", xp.Xml_Parser(scriptName + ".xml"));
		Log.info("Validating assertion ");
		
	}

	@AfterClass
	public void tearDown() throws IOException, Exception {
		joomla.article.search(AppData.PchApp2.PCHApp);
		joomla.PA.FeatureFieldremove();
		joomla.PA.TileFieldremove();
		joomla.PA.saveArticle();
		joomla.PA.featureFieldSelectArticle("1MM Gold Seal Path");
		joomla.PA.tileFieldSelectArticle("20K Secret Stash Path", 0);
		joomla.article.saveArticle();
		
		closeallwindows();
		endTestCase("End");
	}
}