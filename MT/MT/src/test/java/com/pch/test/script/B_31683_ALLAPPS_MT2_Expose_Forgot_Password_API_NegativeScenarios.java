package com.pch.test.script;

import static com.pch.utilities.Log.Log;
import static com.pch.utilities.Log.startTestCase;
import static com.pch.utilities.Log.endTestCase;

import java.io.IOException;
import java.net.MalformedURLException;

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

public class B_31683_ALLAPPS_MT2_Expose_Forgot_Password_API_NegativeScenarios extends Action_Wrapper {
	
	RFTestPage RF = new RFTestPage();
	JoomlaAdministrator joomla = new JoomlaAdministrator();
	Jmeter_Xml_Parser xp = new Jmeter_Xml_Parser();
	String response;
	
	String ThreadList[] = {"Email Misssing"};
	String ThreadList1[] = {"ResetURL Misssing"};
	String ThreadList2[] = {"HTTP Request -Missing Login Name"};
	String ThreadList3[] = {"HTTP Request -Missing App Code"};
	String ThreadList4[] = {"HTTP Request -Missing Source Code"};
	String ThreadList5[] = {"HTTP Request -Missing Plateform Code"};
	String Script_Name=this.getClass().getSimpleName();
	
	@BeforeClass
	public void setup() throws Exception {
		startTestCase(Script_Name);
		createAccessTokenForAllApp("playnwin");
		
		response = RF.createFullRegUserAPI();
		writeInProprtyFile("Encrypted_Email", getEncryptedData("Access_Token_PLAYANDWIN",RF.getResponseValueAPI(response, ResponseData.Email)));

		copyAndRenameFile();
		
		enableThread(Script_Name+".jmx", ThreadList1);
		
	}

	@Test(groups = { GroupName.RegFoundation, GroupName.Regression})
	public void story_B_31683_ALLAPPS_MT2_Expose_Forgot_Password_API_NegativeScenarios() throws Exception {
		Log.info("Start Executing Jmeter Script");
		
		// Code Block For Missing Reset URL from Request and Config as well
		openBrowser("chrome");
		joomla.login.login();
		joomla.MTAPIConfig.navigateToAPIComponents();
		joomla.MTAPIConfig.servicesUrl("Alter URL", ServiceURL.Reset_Password);
		joomla.MTAPIConfig.saveAndClose();

		GETclearMemCache("Access_Token_PLAYANDWIN");
				
		CallJmeterBuild(Script_Name+".jmx");
		Log.info("=======  Calling  jmeter file for Service URL Scenarios =======");

		Assert.assertEquals("false", xp.Xml_Parser(Script_Name+".xml"));
		Log.info("Reset Service URL Validation Assertion ");
		
		// Revert the Service URL
		joomla.MTAPIConfig.navigateToAPIComponents();
		joomla.MTAPIConfig.servicesUrl("Valid URL", ServiceURL.Reset_Password);
		joomla.MTAPIConfig.saveAndClose();
		GETclearMemCache("Access_Token_PLAYANDWIN");
		
		// Block For Missing Email ID
		enableThread(Script_Name+".jmx", ThreadList);
		CallJmeterBuild(Script_Name+".jmx");
		Log.info("calling  jmeter file ");
		Assert.assertEquals("false", xp.Xml_Parser(Script_Name+".xml"));
		Log.info("Validating Assertion ");
		
		// Block for No parameter passed HTTP Request -Missing Source Code
		enableThread(Script_Name+".jmx", ThreadList4);
		
		joomla.article.navigateToArticleManager();
		joomla.article.search("playandwin");
		joomla.article.updateRFConfiguration("Appcode", returnPropertyValue("PlayAndWin_AppCode"));
		joomla.article.updateRFConfiguration("Source Code", "");
		joomla.article.updateRFConfiguration("Platform Code", returnPropertyValue("PlayAndWin_PlatformCode"));
		joomla.article.updateRFConfiguration("Login Name", returnPropertyValue("PlayAndWin_LoginName"));
		joomla.article.saveArticle();
		
		Log.info("Start Executing Jmeter Script");
		CallJmeterBuild(Script_Name+".jmx");
		Log.info("calling  jmeter file ");
		Assert.assertEquals("false", xp.Xml_Parser(Script_Name+".xml"));
		Log.info("Validating Assertion for Missing Source Code");
		
		
		// Block for No parameter passed HTTP Request -Missing Platform Code
		enableThread(Script_Name+".jmx", ThreadList5);
		joomla.article.search("playandwin");
		joomla.article.updateRFConfiguration("Appcode", returnPropertyValue("PlayAndWin_AppCode"));
		joomla.article.updateRFConfiguration("Source Code", returnPropertyValue("PlayAndWin_SourceCode"));
		joomla.article.updateRFConfiguration("Platform Code", "");
		joomla.article.updateRFConfiguration("Login Name", returnPropertyValue("PlayAndWin_LoginName"));
		joomla.article.saveArticle();
		
		Log.info("Start Executing Jmeter Script");
		CallJmeterBuild(Script_Name+".jmx");
		Log.info("calling  jmeter file ");
		
		Assert.assertEquals("false", xp.Xml_Parser(Script_Name+".xml"));
		Log.info("Validating Assertion for Platform Code");
		
		// Block for No parameter passed HTTP Request -Missing Login Name
		enableThread(Script_Name+".jmx", ThreadList2);
		joomla.article.search("playandwin");
		joomla.article.updateRFConfiguration("Appcode", returnPropertyValue("PlayAndWin_AppCode"));
		joomla.article.updateRFConfiguration("Source Code", returnPropertyValue("PlayAndWin_SourceCode"));
		joomla.article.updateRFConfiguration("Platform Code", returnPropertyValue("PlayAndWin_PlatformCode"));
		joomla.article.updateRFConfiguration("Login Name", "");
		joomla.article.saveArticle();

		Log.info("Start Executing Jmeter Script");
		CallJmeterBuild(Script_Name+".jmx");
		Log.info("calling  jmeter file ");
		Assert.assertEquals("false", xp.Xml_Parser(Script_Name+".xml"));
		Log.info("Validating Assertion for Login Name");
		
		// Block for No parameter passed HTTP Request -Missing AppCode 
		joomla.article.search("playandwin");
		joomla.article.updateRFConfiguration("Appcode", "");
		joomla.article.updateRFConfiguration("Source Code", returnPropertyValue("PlayAndWin_SourceCode"));
		joomla.article.updateRFConfiguration("Platform Code", returnPropertyValue("PlayAndWin_PlatformCode"));
		joomla.article.updateRFConfiguration("Login Name", returnPropertyValue("PlayAndWin_LoginName"));
		joomla.article.saveArticle();
		closeallwindows();
		
		enableThread(Script_Name+".jmx", ThreadList3);
		
		Log.info("Start Executing Jmeter Script");
		CallJmeterBuild(Script_Name+".jmx");
		Log.info("calling  jmeter file ");
		Assert.assertEquals("false", xp.Xml_Parser(Script_Name+".xml"));
		Log.info("Validating Assertion for App Code");
	}

	@AfterClass
	public void tearDown() throws MalformedURLException, IOException, Exception {
		openBrowser("chrome");
		joomla.login.login();
		joomla.article.navigateToArticleManager();
		joomla.article.search("playandwin");
		joomla.article.updateRFConfiguration("Appcode", returnPropertyValue("PlayAndWin_AppCode"));
		joomla.article.updateRFConfiguration("Source Code", returnPropertyValue("PlayAndWin_SourceCode"));
		joomla.article.updateRFConfiguration("Platform Code", returnPropertyValue("PlayAndWin_PlatformCode"));
		joomla.article.updateRFConfiguration("Login Name", returnPropertyValue("PlayAndWin_LoginName"));
		joomla.article.saveArticle();

		closeallwindows();
		endTestCase("End");

	}
}
