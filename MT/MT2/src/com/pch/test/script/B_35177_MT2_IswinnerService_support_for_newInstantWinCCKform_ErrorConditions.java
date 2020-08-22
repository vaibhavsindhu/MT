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

public class B_35177_MT2_IswinnerService_support_for_newInstantWinCCKform_ErrorConditions extends Action_Wrapper {
	Jmeter_Xml_Parser xp = new Jmeter_Xml_Parser();
	JoomlaAdministrator joomla = new JoomlaAdministrator();
	RFTestPage RF = new RFTestPage();
	
	String response;
	String ThreadList[] = {"isWinner - Invalid GameID"};
	String ThreadList1[] = {"isWinner - Missing BUID"};
	String ThreadList2[] = {"isWinner - Missing DeviceID"};
	String ThreadList3[] = {"isWinner - Invalid Game Access Key"};
	String ThreadList4[] = {"isWinner - Invalid IWE URL"};
	String ThreadList5[] = {"isWinner - Wrong Access Token"};
	
	String scriptName = this.getClass().getSimpleName();
	
	@BeforeClass
	public void setup() throws Exception {
		startTestCase(scriptName);
		createAccessTokenForAllApp("pchapp");


		openBrowser("joomla");
		joomla.login.login();
		joomla.article.navigateToArticleManager();
		joomla.article.selectcategoryDropDown("AppConfig");
		joomla.article.search("PCHAPP");

		joomla.article.updateRFConfiguration("BUID",returnPropertyValue("PCHAPP_IWEBUID"));
		joomla.article.updateRFConfiguration("IWE_deviceID", returnPropertyValue("PCHAPP_IWEDeviceID"));
		joomla.article.updateRFConfiguration("AccessKey", returnPropertyValue("PCHAPP_AccessKey"));
		joomla.article.updateRFConfiguration("ActivityID", returnPropertyValue("PCHAPP_ActivityID"));
		joomla.article.updateRFConfiguration("IWETokenType", returnPropertyValue("PCHAPP_TokenType"));
		joomla.article.saveArticle();
		
		response=RF.createFullRegUserAPI();
//		Email = RF.getResponseValueAPI(response, ResponseData.Email);
//		GMT=RF.getResponseValueAPI(response, ResponseData.GlobalMemberToken);
		writeInProprtyFile("Encrypted_Email", getEncryptedData("Access_Token_PCHAPP",RF.getResponseValueAPI(response, ResponseData.Email)));
		writeInProprtyFile("Encrypted_GMT", getEncryptedData("Access_Token_PCHAPP",RF.getResponseValueAPI(response, ResponseData.GlobalMemberToken)));
		copyAndRenameFile();
	}

	@Test(groups = { GroupName.TokenBank, GroupName.Regression })
	public void story_B_35177_MT2_IswinnerService_support_for_newInstantWinCCKform_ErrorConditions() throws Exception {
		Log.info("Start Executing Jmeter Script");
		
		// Code Block for isWinner - Invalid GameID Scenario
		
		enableThread(scriptName + ".jmx", ThreadList);
		CallJmeterBuild(scriptName + ".jmx");
		Log.info("calling  jmeter file ");

		Assert.assertEquals("false", xp.Xml_Parser(scriptName+".xml"));
		Log.info("Validating assertion ");
		
		// Code Block for isWinner - Wrong Access Token Scenario
		createAccessTokenForAllApp("playandwin");
		enableThread(scriptName + ".jmx", ThreadList5);
		CallJmeterBuild(scriptName + ".jmx");
		Log.info("calling  jmeter file ");

		Assert.assertEquals("false", xp.Xml_Parser(scriptName+".xml"));
		Log.info("Validating assertion ");
		
		// Code Block for Missing BU ID Scenario
		
		joomla.article.navigateToArticleManager();
		joomla.article.selectcategoryDropDown("AppConfig");
		joomla.article.search("PCHAPP");

		joomla.article.updateRFConfiguration("BUID","");
		joomla.article.updateRFConfiguration("IWE_deviceID", returnPropertyValue("PCHAPP_IWEDeviceID"));
		joomla.article.updateRFConfiguration("AccessKey", returnPropertyValue("PCHAPP_AccessKey"));
		joomla.article.updateRFConfiguration("ActivityID", returnPropertyValue("PCHAPP_ActivityID"));
		joomla.article.updateRFConfiguration("IWETokenType", returnPropertyValue("PCHAPP_TokenType"));
		joomla.article.saveArticle();
		
		enableThread(scriptName + ".jmx", ThreadList1);
		
		Log.info("Start Executing Jmeter Script");
		CallJmeterBuild(scriptName + ".jmx");
		Log.info("calling  jmeter file ");
		Assert.assertEquals("false", xp.Xml_Parser(scriptName+".xml"));
		Log.info("Business UNIT ID Assertion ");
		
		// Code Block For Missing IWE Device ID Validation
		
		joomla.article.search("PCHAPP");
		joomla.article.updateRFConfiguration("BUID",returnPropertyValue("PCHAPP_IWEBUID"));
		joomla.article.updateRFConfiguration("IWE_deviceID", "");
		joomla.article.updateRFConfiguration("AccessKey", returnPropertyValue("PCHAPP_AccessKey"));
		joomla.article.updateRFConfiguration("ActivityID", returnPropertyValue("PCHAPP_ActivityID"));
		joomla.article.updateRFConfiguration("IWETokenType", returnPropertyValue("PCHAPP_TokenType"));
		joomla.article.saveArticle();

		enableThread(scriptName + ".jmx", ThreadList2);
		Log.info("Start Executing Jmeter Script");
		CallJmeterBuild(scriptName + ".jmx");
		Log.info("calling  jmeter file ");
		Assert.assertEquals("false", xp.Xml_Parser(scriptName+".xml"));
		Log.info("IWE Device ID Assertion ");
		
		// Code Block For isWinner - Invalid Game Access Key Validation
		joomla.article.search("PCHAPP");
		joomla.article.updateRFConfiguration("BUID",returnPropertyValue("PCHAPP_IWEBUID"));
		joomla.article.updateRFConfiguration("IWE_deviceID", returnPropertyValue("PCHAPP_IWEDeviceID"));
		joomla.article.updateRFConfiguration("AccessKey", returnPropertyValue("PCHAPP_AccessKey"));
		joomla.article.updateRFConfiguration("ActivityID", returnPropertyValue("PCHAPP_ActivityID"));
		joomla.article.updateRFConfiguration("IWETokenType", returnPropertyValue("PCHAPP_TokenType"));
		joomla.article.saveArticle();

		enableThread(scriptName + ".jmx", ThreadList3);
		Log.info("Start Executing Jmeter Script");
		CallJmeterBuild(scriptName + ".jmx");
		Log.info("calling  jmeter file ");

		Assert.assertEquals("false", xp.Xml_Parser(scriptName+".xml"));
		Log.info("Access key Assertion ");
		
		// Code Block For Invalid IWE URL Validation Exm: https://iwe.qa.pch.com/iwet

		joomla.MTAPIConfig.navigateToAPIComponents();
		joomla.MTAPIConfig.servicesUrl("Alter URL", ServiceURL.Instant_Win_Engine);
		joomla.MTAPIConfig.saveAndClose();

		GETclearMemCache("Access_Token_PCHAPP");
		enableThread(scriptName + ".jmx", ThreadList4);
		
		CallJmeterBuild(scriptName + ".jmx");
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
		joomla.article.search("PCHAPP");

		joomla.article.updateRFConfiguration("BUID",returnPropertyValue("PCHAPP_IWEBUID"));
		joomla.article.updateRFConfiguration("IWE_deviceID", returnPropertyValue("PCHAPP_IWEDeviceID"));
		joomla.article.updateRFConfiguration("AccessKey", returnPropertyValue("PCHAPP_AccessKey"));
		joomla.article.updateRFConfiguration("ActivityID", returnPropertyValue("PCHAPP_ActivityID"));
		joomla.article.updateRFConfiguration("IWETokenType", returnPropertyValue("PCHAPP_TokenType"));
		joomla.article.saveArticle();
		GETclearMemCache("Access_Token_PCHAPP");
		
		closeallwindows();
		endTestCase("End");
	}
}