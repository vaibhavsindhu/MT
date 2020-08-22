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
import com.pch.joomla.configuration.MidTierApiConfiguration;
import com.pch.joomla.configuration.MidTierApiConfiguration.ServiceURL;
import com.pch.utilities.Action_Wrapper;
import com.pch.utilities.GroupName;
import com.pch.utilities.Jmeter_Xml_Parser;

public class B_29892_Allapps_MT2_RegistrationDetails_ErrorCodes extends Action_Wrapper {
	
	String response;
	RFTestPage RF = new RFTestPage();
	JoomlaAdministrator joomla = new JoomlaAdministrator();
	MidTierApiConfiguration MTAPIConfig =new MidTierApiConfiguration();
	Jmeter_Xml_Parser xp = new Jmeter_Xml_Parser();
	
	String ThreadList[] = {"HTTP Request - ExpiredAuthCode"};
	String ThreadList1[] = {"HTTP Request - No values passed"};
	String ThreadList2[] = {"HTTP Request - WithOut Incryption"};
	String ThreadList3[] = {"HTTP Request - Invalid UserAgent ID"};
	String ThreadList4[] = {"HTTP Request -Missing Login Name"};
	String ThreadList5[] = {"HTTP Request -Missing App Code"};
	String ThreadList6[] = {"HTTP Request -Missing Source Code"};
	String ThreadList7[] = {"HTTP Request -Missing Plateform Code"};
	
	@BeforeClass
	public void setup() throws Exception {
		startTestCase("B_29892_Allapps_MT2_RegistrationDetails_ErrorCodes");
		createAccessTokenForAllApp("pchapp");
		
		// Full reg User Creation
		response = RF.createFullRegUserAPI();
		writeInProprtyFile("Encrypted_Email", getEncryptedData("Access_Token_PCHAPP",RF.getResponseValueAPI(response, ResponseData.Email)));
		writeInProprtyFile("Encrypted_GMT", getEncryptedData("Access_Token_PCHAPP",RF.getResponseValueAPI(response, ResponseData.GlobalMemberToken)));
		copyAndRenameFile();
		
		openBrowser("chrome");
		joomla.login.login();
		joomla.article.navigateToArticleManager();
		joomla.article.search("PCHAPP");
		joomla.article.updateRFConfiguration("Appcode", returnPropertyValue("PCHAPP_AppCode"));
		joomla.article.updateRFConfiguration("Source Code", returnPropertyValue("PCHAPP_SourceCode"));
		joomla.article.updateRFConfiguration("Platform Code", returnPropertyValue("PCHAPP_PlatformCode"));
		joomla.article.updateRFConfiguration("Login Name", returnPropertyValue("PCHAPP_LoginName"));
		joomla.article.saveArticle();
		
		MTAPIConfig.navigateToAPIComponents();
		MTAPIConfig.servicesUrl("Valid URL", ServiceURL.Registration_Foundation);
		MTAPIConfig.saveAndClose();
		GETclearMemCache("Access_Token_PCHAPP");
		
		// Expired Auth Code code will be enabled after the fix for error details response.
		enableThread("B_29892_Allapps_MT2_RegistrationDetails_ErrorCodes.jmx", ThreadList);
		
	}

	@Test(groups = { GroupName.RegFoundation, GroupName.Regression})
	public void story_B_29892_Allapps_MT2_RegistrationDetails_ErrorCodes() throws Exception {
		Log.info("Start Executing Jmeter Script");
		
		// Block for Expired Auth Code
		CallJmeterBuild("B_29892_Allapps_MT2_RegistrationDetails_ErrorCodes.jmx");
		Log.info("calling  jmeter file ");
		Assert.assertEquals("false", xp.Xml_Parser("B_29892_Allapps_MT2_RegistrationDetails_ErrorCodes.xml"));
		Log.info("Validating Assertion ");
		
		// Block for No parameter passed
		
		enableThread("B_29892_Allapps_MT2_RegistrationDetails_ErrorCodes.jmx", ThreadList1);
		CallJmeterBuild("B_29892_Allapps_MT2_RegistrationDetails_ErrorCodes.jmx");
		Log.info("calling  jmeter file ");
		Assert.assertEquals("false", xp.Xml_Parser("B_29892_Allapps_MT2_RegistrationDetails_ErrorCodes.xml"));
		Log.info("Validating Assertion for empty parameter ");
		
		// Block for No parameter passed HTTP Request - WithOut Incryption
		enableThread("B_29892_Allapps_MT2_RegistrationDetails_ErrorCodes.jmx", ThreadList2);
		CallJmeterBuild("B_29892_Allapps_MT2_RegistrationDetails_ErrorCodes.jmx");
		Log.info("calling  jmeter file ");
		Assert.assertEquals("false", xp.Xml_Parser("B_29892_Allapps_MT2_RegistrationDetails_ErrorCodes.xml"));
		Log.info("Validating Assertion for Encryption Error");
		
		
		// Block for No parameter passed HTTP Request - Invalid UserAgent ID
		enableThread("B_29892_Allapps_MT2_RegistrationDetails_ErrorCodes.jmx", ThreadList3);
		CallJmeterBuild("B_29892_Allapps_MT2_RegistrationDetails_ErrorCodes.jmx");
		Log.info("calling  jmeter file ");
		Assert.assertEquals("false", xp.Xml_Parser("B_29892_Allapps_MT2_RegistrationDetails_ErrorCodes.xml"));
		Log.info("Validating Assertion for Invalid UserAgent ID");
		
		// Block for No parameter passed HTTP Request -Missing Source Code
		enableThread("B_29892_Allapps_MT2_RegistrationDetails_ErrorCodes.jmx", ThreadList6);

		joomla.article.navigateToArticleManager();
		joomla.article.search("PCHAPP");
		joomla.article.updateRFConfiguration("Appcode", returnPropertyValue("PCHAPP_AppCode"));
		joomla.article.updateRFConfiguration("Source Code", "");
		joomla.article.updateRFConfiguration("Platform Code", returnPropertyValue("PCHAPP_PlatformCode"));
		joomla.article.updateRFConfiguration("Login Name", returnPropertyValue("PCHAPP_LoginName"));
		joomla.article.saveArticle();
		
		Log.info("Start Executing Jmeter Script");
		CallJmeterBuild("B_29892_Allapps_MT2_RegistrationDetails_ErrorCodes.jmx");
		Log.info("calling  jmeter file ");
		Assert.assertEquals("false", xp.Xml_Parser("B_29892_Allapps_MT2_RegistrationDetails_ErrorCodes.xml"));
		Log.info("Validating Assertion for Missing Source Code");
		
		
		// Block for No parameter passed HTTP Request -Missing Platform Code
		enableThread("B_29892_Allapps_MT2_RegistrationDetails_ErrorCodes.jmx", ThreadList7);
		joomla.article.search("PCHAPP");
		joomla.article.updateRFConfiguration("Appcode", returnPropertyValue("PCHAPP_AppCode"));
		joomla.article.updateRFConfiguration("Source Code", returnPropertyValue("PCHAPP_SourceCode"));
		joomla.article.updateRFConfiguration("Platform Code", "");
		joomla.article.updateRFConfiguration("Login Name", returnPropertyValue("PCHAPP_LoginName"));
		joomla.article.saveArticle();
		
		Log.info("Start Executing Jmeter Script");
		CallJmeterBuild("B_29892_Allapps_MT2_RegistrationDetails_ErrorCodes.jmx");
		Log.info("calling  jmeter file ");
		Assert.assertEquals("false", xp.Xml_Parser("B_29892_Allapps_MT2_RegistrationDetails_ErrorCodes.xml"));
		Log.info("Validating Assertion for Platform Code");
		
		// Block for No parameter passed HTTP Request -Missing Login Name
		enableThread("B_29892_Allapps_MT2_RegistrationDetails_ErrorCodes.jmx", ThreadList4);
		joomla.article.search("PCHAPP");
		joomla.article.updateRFConfiguration("Appcode", returnPropertyValue("PCHAPP_AppCode"));
		joomla.article.updateRFConfiguration("Source Code", returnPropertyValue("PCHAPP_SourceCode"));
		joomla.article.updateRFConfiguration("Platform Code", returnPropertyValue("PCHAPP_PlatformCode"));
		joomla.article.updateRFConfiguration("Login Name", "");
		joomla.article.saveArticle();


		Log.info("Start Executing Jmeter Script");
		CallJmeterBuild("B_29892_Allapps_MT2_RegistrationDetails_ErrorCodes.jmx");
		Log.info("calling  jmeter file ");
		Assert.assertEquals("false", xp.Xml_Parser("B_29892_Allapps_MT2_RegistrationDetails_ErrorCodes.xml"));
		Log.info("Validating Assertion for Login Name");
		
		// Block for No parameter passed HTTP Request -Missing AppCode 

		joomla.article.search("PCHAPP");
		joomla.article.updateRFConfiguration("Appcode", "");
		joomla.article.updateRFConfiguration("Source Code", returnPropertyValue("PCHAPP_SourceCode"));
		joomla.article.updateRFConfiguration("Platform Code", returnPropertyValue("PCHAPP_PlatformCode"));
		joomla.article.updateRFConfiguration("Login Name", returnPropertyValue("PCHAPP_LoginName"));
		joomla.article.saveArticle();
		
		enableThread("B_29892_Allapps_MT2_RegistrationDetails_ErrorCodes.jmx", ThreadList5);
		
		Log.info("Start Executing Jmeter Script");
		CallJmeterBuild("B_29892_Allapps_MT2_RegistrationDetails_ErrorCodes.jmx");
		Log.info("calling  jmeter file ");
		Assert.assertEquals("false", xp.Xml_Parser("B_29892_Allapps_MT2_RegistrationDetails_ErrorCodes.xml"));
		Log.info("Validating Assertion for App Code");
	}

	@AfterClass
	public void tearDown() throws MalformedURLException, IOException, Exception {

		joomla.article.navigateToArticleManager();
		joomla.article.search("PCHAPP");
		joomla.article.updateRFConfiguration("Appcode", returnPropertyValue("PCHAPP_AppCode"));
		joomla.article.updateRFConfiguration("Source Code", returnPropertyValue("PCHAPP_SourceCode"));
		joomla.article.updateRFConfiguration("Platform Code", returnPropertyValue("PCHAPP_PlatformCode"));
		joomla.article.updateRFConfiguration("Login Name", returnPropertyValue("PCHAPP_LoginName"));
		joomla.article.saveArticle();

		closeallwindows();
		endTestCase("End");

	}
}
