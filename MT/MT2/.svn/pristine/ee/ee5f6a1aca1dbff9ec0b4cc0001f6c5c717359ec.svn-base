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

public class B_37103_MT2_RemoveRedundantDataFromAppLoad extends Action_Wrapper {

	Jmeter_Xml_Parser xp = new Jmeter_Xml_Parser();
	JoomlaAdministrator joomla = new JoomlaAdministrator();

	RFTestPage RF = new RFTestPage();
	String response, GMT, Email;
	String scriptName = this.getClass().getSimpleName();
	String Thread1[] = { "AppLoad" };

	

	@BeforeClass
	public void setup() throws Exception {
		startTestCase(scriptName);
		createAccessTokenForAllApp("pchapp");

		response = RF.createFullRegUserAPI();
		Email = RF.getResponseValueAPI(response, com.pch.configuration.testpages.RFTestPage.ResponseData.Email);
		GMT = RF.getResponseValueAPI(response, ResponseData.GlobalMemberToken);
		writeInProprtyFile("Encrypted_Email",
				getEncryptedData("Access_Token_PCHAPP", RF.getResponseValueAPI(response, ResponseData.Email)));
		writeInProprtyFile("Encrypted_GMT", getEncryptedData("Access_Token_PCHAPP",
				RF.getResponseValueAPI(response, ResponseData.GlobalMemberToken)));
		copyAndRenameFile();

		openBrowser("chrome");
		joomla.login.login();
		joomla.article.navigateToArticleManager();

		// Configure Content Path in App Article
		joomla.PA.filterArticleByNames(AppData.Category_ID.PchApp2_Category, AppData.PchApp2.PCHApp);

		joomla.PA.editArticle(AppData.PchApp2.PCHApp, "publish");

		joomla.PA.featureFieldSelectArticle(AppData.ContentPath.Lotto_ContentPath);
		joomla.alias.saveAndClose();

		// ensuring if content path article is published and adding article to
		// content path

		joomla.PA.filterArticleByNames(AppData.Category_ID.ContentPath_Category, AppData.ContentPath.Continuous_ContentPath);
		joomla.PA.editArticle(AppData.ContentPath.Continuous_ContentPath, "pubilish");
		//joomla.PA.selectActionArticles("428");
		joomla.PA.selectActionArticles(AppData.GamesID.SBG.NewPCHAppContinuousGame1);
		joomla.alias.saveAndClose();

		// configuring game article in content path

		Log.info("Adding menu link article ");
		joomla.PA.filterArticleByNames(AppData.Category_ID.MenuLink, AppData.Category_Name.MenuLink);
		joomla.PA.editArticle(AppData.Category_Name.MenuLink, "publish");
		joomla.ML.selectActionArticleDropdown(AppData.Action.FAQ, 0);
		joomla.alias.saveAndClose();

		// CONFIGURING ACTION ARTICLE

		joomla.PA.filterArticleByNames(AppData.Category_ID.Actions, AppData.Action.FAQ);
		joomla.PA.editArticle(AppData.Action.FAQ, "publish");
		joomla.actions.addRulesFactsId("22");
		joomla.alias.saveAndClose();

		joomla.PA.filterArticleByNames(AppData.Category_ID.SBG, AppData.GameName.SBG.NewPCHAppContinuousGame1);
		joomla.PA.editArticle(AppData.GameName.SBG.NewPCHAppContinuousGame1, "publish");
		joomla.alias.saveAndClose();

		enableThread(scriptName + ".jmx", Thread1);
	}

	@Test(groups = { GroupName.RegFoundation, GroupName.Game })
	public void strory_B_37103_MT2_RemoveRedundantDataFromAppLoad() throws Exception {

		Log.info("Start Executing Jmeter Script");
		CallJmeterBuild(scriptName + ".jmx");
		Log.info("Validating assertion ");
		Assert.assertEquals("false", xp.Xml_Parser(scriptName + ".xml"));

		Log.info("content path values is ::");
		Log.info(returnPropertyValue("\\contentPathData", "contentPathData"));

		Log.info("Verifying if 'feature4x' text present at app load->content path data");
		Assert.assertFalse(returnPropertyValue("\\contentPathData", "contentPathData").contains("featureIcon4x"),
				"featureIcon4x is not exist at page  ");
		Log.info(" feature4x  text present at app load->content path data");

		Log.info("Verifying if 'feature2x' text present at app load->content path data");
		Assert.assertFalse(returnPropertyValue("\\contentPathData", "contentPathData").contains("featureIcon2x"),
				"featureIcon2x is not exist at page ");
		Log.info(" feature2x  text present at app load->content path data");

		Log.info("Verifying if 'tile2x' text present at app load->content path data");
		Assert.assertFalse(returnPropertyValue("\\contentPathData", "contentPathData").contains("tile2x"),
				"tile2x is not exist at page ");
		Log.info(" tile2x  text present at app load->content path data");

		Log.info("Verifying if 'tile4x' text present at app load->content path data");
		Assert.assertFalse(returnPropertyValue("\\contentPathData", "contentPathData").contains("tile4x"),
				"tile 4x is not exist at page ");
		Log.info(" tile4x  text present at app load->content path data");

		Log.info("Verifying if 'rules_facts_id' text present at app load->content path data");

		Assert.assertFalse(returnPropertyValue("\\contentPathData", "contentPathData").contains("rules_facts_id"),
				"rule fact ID  is not exist at page ");
		Log.info(" rules_facts_id  text present at app load->content path data");
	}

	@AfterClass
	public void tearDown() throws IOException, Exception {
		closeallwindows();
		endTestCase("End");
	}
}
