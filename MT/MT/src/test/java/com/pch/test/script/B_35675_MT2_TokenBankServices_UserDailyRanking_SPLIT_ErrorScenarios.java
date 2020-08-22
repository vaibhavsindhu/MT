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
import com.pch.utilities.GroupName;
import com.pch.utilities.Jmeter_Xml_Parser;

public class B_35675_MT2_TokenBankServices_UserDailyRanking_SPLIT_ErrorScenarios extends Action_Wrapper {
	Jmeter_Xml_Parser xp = new Jmeter_Xml_Parser();
	JoomlaAdministrator joomla = new JoomlaAdministrator();
	RFTestPage RF = new RFTestPage();
	String response;

	String ThreadList1[] = {"BU ID missing"};
	String ThreadList2[] = {"Device ID Missing"};
	String ThreadList3[] = {"Access Key Missing"};
	String ThreadList4[] = {"Activity ID Missing"};
	String ThreadList5[] = {"Token Type Missing"};
	String ThreadList6[] = {"Invalid IWE URL"};
	String ThreadList7[] = {"Invalid IWE URL2"};
	String ThreadList8[] = {"UserwithOutTokenBalance"};

	String scriptName = this.getClass().getSimpleName();
	
	
	@BeforeClass
	public void setup() throws Exception {
		startTestCase(scriptName);
		createAccessTokenForAllApp("playnwin");

		// Create Brand New User and write EMAIL/GMT in property file
		response = RF.createFullRegUserAPI();
		writeInProprtyFile("Encrypted_Email", getEncryptedData("Access_Token_PLAYANDWIN", RF.getResponseValueAPI(response,ResponseData.Email)));
		writeInProprtyFile("Encrypted_GMT", getEncryptedData("Access_Token_PLAYANDWIN", RF.getResponseValueAPI(response,ResponseData.GlobalMemberToken)));
		copyAndRenameFile();
		openBrowser("joomla");
		joomla.login.login();
		joomla.article.navigateToArticleManager();
		joomla.article.selectcategoryDropDown("AppConfig");
		joomla.article.search("PLAYANDWIN");

		joomla.article.updateRFConfiguration("BUID",returnPropertyValue("PlayAndWin_IWEBUID"));
		joomla.article.updateRFConfiguration("IWE_deviceID", returnPropertyValue("PlayAndWin_IWEDeviceID"));
		joomla.article.updateRFConfiguration("AccessKey", returnPropertyValue("PlayAndWin_AccessKey"));
		joomla.article.updateRFConfiguration("ActivityID", returnPropertyValue("PlayAndWin_ActivityID"));
		joomla.article.updateRFConfiguration("IWETokenType", returnPropertyValue("PlayAndWin_TokenType"));
		joomla.article.saveArticle();
		

	}

	@Test(groups = { GroupName.TokenBank, GroupName.Regression })
	public void story_B_35675_MT2_TokenBankServices_UserDailyRanking_SPLIT_ErrorScenarios() throws Exception {
		Log.info("Start Executing Jmeter Script");

		// Code Block for Missing BU ID Scenario
		
		joomla.article.navigateToArticleManager();
		joomla.article.selectcategoryDropDown("AppConfig");
		joomla.article.search("PLAYANDWIN");

		joomla.article.updateRFConfiguration("BUID","");
		joomla.article.updateRFConfiguration("IWE_deviceID", returnPropertyValue("PlayAndWin_IWEDeviceID"));
		joomla.article.updateRFConfiguration("AccessKey", returnPropertyValue("PlayAndWin_AccessKey"));
		joomla.article.updateRFConfiguration("ActivityID", returnPropertyValue("PlayAndWin_ActivityID"));
		joomla.article.updateRFConfiguration("IWETokenType", returnPropertyValue("PlayAndWin_TokenType"));
		joomla.article.saveArticle();
		
		enableThread(scriptName+".jmx", ThreadList1);
		
		Log.info("Start Executing Jmeter Script");
		
		CallJmeterBuild(scriptName+".jmx");
		Log.info("calling  jmeter file ");

		Assert.assertEquals("false", xp.Xml_Parser(scriptName+".xml"));
		Log.info("Business UNIT ID Assertion ");
		
		// Code Block For Missing IWE Device ID Validation
		
		joomla.article.search("PLAYANDWIN");

		joomla.article.updateRFConfiguration("BUID",returnPropertyValue("PlayAndWin_IWEBUID"));
		joomla.article.updateRFConfiguration("IWE_deviceID", "");
		joomla.article.updateRFConfiguration("AccessKey", returnPropertyValue("PlayAndWin_AccessKey"));
		joomla.article.updateRFConfiguration("ActivityID", returnPropertyValue("PlayAndWin_ActivityID"));
		joomla.article.updateRFConfiguration("IWETokenType", returnPropertyValue("PlayAndWin_TokenType"));
		joomla.article.saveArticle();

		enableThread(scriptName+".jmx", ThreadList2);
		
		Log.info("Start Executing Jmeter Script");
		
		CallJmeterBuild(scriptName+".jmx");
		Log.info("calling  jmeter file ");

		Assert.assertEquals("false", xp.Xml_Parser(scriptName+".xml"));
		Log.info("IWE Device ID Assertion ");
		
		// Code Block For Missing Access Key Validation
		joomla.article.search("PLAYANDWIN");

		joomla.article.updateRFConfiguration("BUID",returnPropertyValue("PlayAndWin_IWEBUID"));
		joomla.article.updateRFConfiguration("IWE_deviceID", returnPropertyValue("PlayAndWin_IWEDeviceID"));
		joomla.article.updateRFConfiguration("AccessKey", "");
		joomla.article.updateRFConfiguration("ActivityID", returnPropertyValue("PlayAndWin_ActivityID"));
		joomla.article.updateRFConfiguration("IWETokenType", returnPropertyValue("PlayAndWin_TokenType"));
		joomla.article.saveArticle();

		enableThread(scriptName+".jmx", ThreadList3);
		
		Log.info("Start Executing Jmeter Script");
		
		CallJmeterBuild(scriptName+".jmx");
		Log.info("calling  jmeter file ");

		Assert.assertEquals("false", xp.Xml_Parser(scriptName+".xml"));
		Log.info("Access key Assertion ");
		
		// Code Block For Missing Activity ID Validation
		joomla.article.search("PLAYANDWIN");

		joomla.article.updateRFConfiguration("BUID",returnPropertyValue("PlayAndWin_IWEBUID"));
		joomla.article.updateRFConfiguration("IWE_deviceID", returnPropertyValue("PlayAndWin_IWEDeviceID"));
		joomla.article.updateRFConfiguration("AccessKey", returnPropertyValue("PlayAndWin_AccessKey"));
		joomla.article.updateRFConfiguration("ActivityID", "");
		joomla.article.updateRFConfiguration("IWETokenType", returnPropertyValue("PlayAndWin_TokenType"));
		joomla.article.saveArticle();

		enableThread(scriptName+".jmx", ThreadList4);
		
		Log.info("Start Executing Jmeter Script");
		
		CallJmeterBuild(scriptName+".jmx");
		Log.info("calling  jmeter file ");

		Assert.assertEquals("false", xp.Xml_Parser(scriptName+".xml"));
		Log.info("Activity ID Assertion ");
		
		// Code Block For Missing Token Type Validation
		joomla.article.search("PLAYANDWIN");

		joomla.article.updateRFConfiguration("BUID",returnPropertyValue("PlayAndWin_IWEBUID"));
		joomla.article.updateRFConfiguration("IWE_deviceID", returnPropertyValue("PlayAndWin_IWEDeviceID"));
		joomla.article.updateRFConfiguration("AccessKey", returnPropertyValue("PlayAndWin_AccessKey"));
		joomla.article.updateRFConfiguration("ActivityID", returnPropertyValue("PlayAndWin_ActivityID"));
		joomla.article.updateRFConfiguration("IWETokenType", "");
		joomla.article.saveArticle();

		enableThread(scriptName+".jmx", ThreadList5);
		
		Log.info("Start Executing Jmeter Script");
		
		CallJmeterBuild(scriptName+".jmx");
		Log.info("calling  jmeter file ");

		Assert.assertEquals("false", xp.Xml_Parser(scriptName+".xml"));
		Log.info("IWE Token Type Assertion ");
		
		// Revert Token Type Config to ensure correct Configuration for next step.
		joomla.article.search("PLAYANDWIN");
		joomla.article.updateRFConfiguration("IWETokenType", returnPropertyValue("PlayAndWin_TokenType"));
		joomla.article.saveArticle();
		
		// Code Block For Invalid IWE URL Validation Exm: https://iwe.qa.pch.com/iwet

		joomla.MTAPIConfig.navigateToAPIComponents();
		joomla.MTAPIConfig.servicesUrl("Alter URL", ServiceURL.Instant_Win_Engine);
		joomla.MTAPIConfig.saveAndClose();

		GETclearMemCache("Access_Token_PLAYANDWIN");
		enableThread(scriptName+".jmx", ThreadList6);
		
		CallJmeterBuild(scriptName+".jmx");
		Log.info("=======  Calling  jmeter file for Service URL Scenarios =======");

		Assert.assertEquals("false", xp.Xml_Parser(scriptName+".xml"));
		Log.info("IWE Service URL Validation Assertion ");
		
		// Code Block For Invalid IWE URL Validation Exm: https://.qa.pch.com/iwe

		joomla.MTAPIConfig.navigateToAPIComponents();
		joomla.MTAPIConfig.servicesUrl("Alter URL1", ServiceURL.Instant_Win_Engine);
		joomla.MTAPIConfig.saveAndClose();
		
		GETclearMemCache("Access_Token_PLAYANDWIN");
		enableThread(scriptName+".jmx", ThreadList7);
		
		CallJmeterBuild(scriptName+".jmx");
		Log.info("=======  Calling  jmeter file for Service URL Scenarios =======");

		Assert.assertEquals("false", xp.Xml_Parser(scriptName+".xml"));
		Log.info("IWE Service URL Validation Assertion ");
		
	}

	@AfterClass
	public void tearDown() throws IOException, Exception {
		
		joomla.MTAPIConfig.navigateToAPIComponents();
		joomla.MTAPIConfig.servicesUrl("Valid URL", ServiceURL.Instant_Win_Engine);
		joomla.MTAPIConfig.saveAndClose();
		joomla.article.navigateToArticleManager();
		joomla.article.selectcategoryDropDown("AppConfig");
		joomla.article.search("PLAYANDWIN");

		joomla.article.updateRFConfiguration("BUID",returnPropertyValue("PlayAndWin_IWEBUID"));
		joomla.article.updateRFConfiguration("IWE_deviceID", returnPropertyValue("PlayAndWin_IWEDeviceID"));
		joomla.article.updateRFConfiguration("AccessKey", returnPropertyValue("PlayAndWin_AccessKey"));
		joomla.article.updateRFConfiguration("ActivityID", returnPropertyValue("PlayAndWin_ActivityID"));
		joomla.article.updateRFConfiguration("IWETokenType", returnPropertyValue("PlayAndWin_TokenType"));
		joomla.article.saveArticle();
		GETclearMemCache("Access_Token_PLAYANDWIN");
		
		closeallwindows();
		endTestCase("End");

	}
}