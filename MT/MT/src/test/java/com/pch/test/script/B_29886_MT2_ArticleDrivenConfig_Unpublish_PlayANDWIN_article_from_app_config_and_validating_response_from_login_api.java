package com.pch.test.script;

import static com.pch.utilities.Log.Log;
import static com.pch.utilities.Log.endTestCase;
import static com.pch.utilities.Log.startTestCase;

import java.io.IOException;
import java.net.MalformedURLException;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.pch.configuration.testpages.RFTestPage;
import com.pch.configuration.testpages.RFTestPage.ResponseData;
import com.pch.joomla.configuration.JoomlaAdministrator;
import com.pch.utilities.Action_Wrapper;
import com.pch.utilities.GroupName;
import com.pch.utilities.Jmeter_Xml_Parser;

public class B_29886_MT2_ArticleDrivenConfig_Unpublish_PlayANDWIN_article_from_app_config_and_validating_response_from_login_api	extends Action_Wrapper {

	JoomlaAdministrator joomla = new JoomlaAdministrator();
	Jmeter_Xml_Parser xp = new Jmeter_Xml_Parser();
	RFTestPage RF = new RFTestPage();
	String response;
	String scriptName = "B_29886_MT2_ArticleDrivenConfig_Unpublish_PlayANDWIN_article_from_app_config_and_validating_response_from_login_api";
	
	
	@BeforeClass
	public void setup() throws Exception {
		startTestCase("B_29886_MT2_ArticleDrivenConfig_Unpublish_PlayANDWIN_article_from_app_config_and_validating_response_from_login_api");
		createAccessTokenForAllApp("playnwin");
		
		response = RF.createFullRegUserAPI();
		writeInProprtyFile("Encrypted_Email", getEncryptedData("Access_Token_PLAYANDWIN",RF.getResponseValueAPI(response, ResponseData.Email)));
		writeInProprtyFile("Encrypted_GMT", getEncryptedData("Access_Token_PLAYANDWIN",RF.getResponseValueAPI(response, ResponseData.GlobalMemberToken)));
		copyAndRenameFile();
		
		openBrowser("chrome");

		joomla.login.login();
		joomla.article.navigateToArticleManager();
		joomla.article.search("PLAYANDWIN");
		joomla.article.appConfArticleStatus("unpublish");
		joomla.article.saveArticle();
		waitForPageToLoad(MTDriver);

	}

	@Test(groups = {GroupName.RegFoundation,GroupName.Regression,"Sprint 15"})
	public void story_B_29886_validating_unpublish_PlayANDWIN_article_from_app_config_and_validating_response_from_login_api() throws Exception {
		
		Log.info("Start Executing Jmeter Script");

		CallJmeterBuild(scriptName+".jmx");
		Log.info("JMX file Called");

		Log.info("Starting Assetion");
		Assert.assertEquals("false", xp.Xml_Parser(scriptName+".xml"));
		Log.info("Assetion Validation Completed");
		
	}

	@AfterClass
	public void tearDown() throws MalformedURLException, IOException, Exception {
		waitForPageToLoad(MTDriver);
		joomla.article.search("PLAYANDWIN");
		joomla.article.appConfArticleStatus("publish");
		joomla.article.saveArticle();
		closeallwindows();
		endTestCase("End");

	}
}
