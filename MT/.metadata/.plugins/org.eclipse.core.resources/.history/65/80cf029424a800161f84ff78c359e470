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
import com.pch.utilities.QaBox;

public class B_36407_MT2_All_App_Welcome_Email_Synchronous_PlayAndWin extends Action_Wrapper{

	Jmeter_Xml_Parser xp = new Jmeter_Xml_Parser();
	JoomlaAdministrator joomla = new JoomlaAdministrator();
	Database_Configuration DB = new Database_Configuration();
	QaBox QB = QaBox.getInstance();
			
	RFTestPage RF = new RFTestPage();
	String response,GMT,Email, decrypt, requestData;
	String scriptName = this.getClass().getSimpleName();
	String threadGroup[] = {"Welcome Email sync","Welcome Email sync Email Already Sent"};
	String threadDisable[] = {"Disable Welcome Email Submission"};
	String threadEmptyTaxi[] = {"Without Taxi API Welcome url"};
	
	String threadInvalidTaxiURL[] = {"Invalid Taxi Service url"};

	@BeforeClass
	public void setup() throws Exception {
		startTestCase("B_36407_MT2_All_App_Welcome_Email_Synchronous_PlayAndWin");
		
		
	createAccessTokenForAllApp("playnwin");

		response = RF.createFullRegUserAPI();
		Email=RF.getResponseValueAPI(response, ResponseData.Email);
		GMT=RF.getResponseValueAPI(response, ResponseData.GlobalMemberToken);
		writeInProprtyFile("Email", getEncryptedData("Access_Token_PLAYANDWIN", Email));
		writeInProprtyFile("GMT", getEncryptedData("Access_Token_PLAYANDWIN", GMT));
		copyAndRenameFile();
		requestData="{\"email\":\""+Email+"\",\"gmt\":\""+GMT+"\"}";
		decrypt=getBase64encruption(requestData);
		
		openBrowser("chrome");
		joomla.login.login();
		
		joomla.MTAPIConfig.navigateToAPIComponents();
		joomla.MTAPIConfig.servicesUrl("Valid URL", ServiceURL.Taxi_Email); 
		joomla.MTAPIConfig.saveAndClose();
		 
		joomla.article.navigateToArticleManager();
		joomla.article.selectcategoryDropDown("AppConfig");
		joomla.article.search("PLAYANDWIN");
		joomla.article.setTAXIConfig("sync", "set");
		joomla.article.saveArticle();
		closeallwindows();
		GETclearMemCache("Access_Token_PLAYANDWIN");
		enableThread(scriptName+".jmx", threadGroup);
		
	}

	@Test(groups = { GroupName.Email, GroupName.Regression })
	public void story_B_36407_MT2_All_App_Welcome_Email_Synchronous_PlayAndWin() throws Exception {
		Log.info("Start Executing Jmeter Script");
		CallJmeterBuild(scriptName+".jmx");
		Log.info("JMX file Called");
		Assert.assertEquals("false", xp.Xml_Parser(scriptName+".xml"));
		Log.info("Assertion Validation Completed");
		
		Assert.assertEquals(DB.phpctrl_sso_properties(decrypt),"sent","Checking welcome email status");		
		Assert.assertEquals(DB.phpctrl_sso_properties(decrypt,"property"),"welcomeemail_playandwin","Validating the property from which welcome email triggered");
			
		enableThread(scriptName+".jmx", threadDisable);

		openBrowser("chrome");
		joomla.login.login();
		joomla.article.navigateToArticleManager();
		joomla.article.selectcategoryDropDown("AppConfig");
		joomla.article.search("PLAYANDWIN");
		joomla.article.setTAXIConfig("Disabled", "set");
		joomla.article.saveArticle();
		closeallwindows();
		
		
		Log.info("Start Executing Jmeter Script");
		CallJmeterBuild(scriptName+".jmx");
		Log.info("JMX file Called");
		Assert.assertEquals("false", xp.Xml_Parser(scriptName+".xml"));
		Log.info("Assertion Validation Completed");

		enableThread(scriptName+".jmx", threadEmptyTaxi);
		
		openBrowser("chrome");
		joomla.login.login();
		joomla.article.navigateToArticleManager();
		joomla.article.selectcategoryDropDown("AppConfig");
		joomla.article.search("PLAYANDWIN");
		joomla.article.setTAXIConfig("sync", "clear");
		joomla.article.saveArticle();
		closeallwindows();
		
		response = RF.createFullRegUserAPI();
		Email=RF.getResponseValueAPI(response, ResponseData.Email);
		GMT=RF.getResponseValueAPI(response, ResponseData.GlobalMemberToken);
		writeInProprtyFile("Email", getEncryptedData("Access_Token_PLAYANDWIN", Email));
		writeInProprtyFile("GMT", getEncryptedData("Access_Token_PLAYANDWIN", GMT));
		copyAndRenameFile();
		
		
		Log.info("Start Executing Jmeter Script");
		CallJmeterBuild(scriptName+".jmx");
		Log.info("JMX file Called");
		Assert.assertEquals("false", xp.Xml_Parser(scriptName+".xml"));
		Log.info("Assertion Validation Completed");

		enableThread(scriptName+".jmx", threadInvalidTaxiURL);

		openBrowser("chrome");
		joomla.login.login();
		joomla.MTAPIConfig.navigateToAPIComponents();
		joomla.MTAPIConfig.servicesUrl("Alter URL", ServiceURL.Taxi_Email); 
		joomla.MTAPIConfig.saveAndClose();
		joomla.article.navigateToArticleManager();
		joomla.article.selectcategoryDropDown("AppConfig");
		joomla.article.search("PLAYANDWIN");
		joomla.article.setTAXIConfig("sync", "set");
		joomla.article.saveArticle();
		closeallwindows();
		GETclearMemCache("Access_Token_PLAYANDWIN");
		
		Log.info("Start Executing Jmeter Script");
		CallJmeterBuild(scriptName+".jmx");
		Log.info("JMX file Called");
		Assert.assertEquals("false", xp.Xml_Parser(scriptName+".xml"));
		Log.info("Assertion Validation Completed");
	
	}

	@AfterClass
	public void tearDown() throws IOException, Exception {

		openBrowser("chrome");
		joomla.login.login();
		
		joomla.MTAPIConfig.navigateToAPIComponents();
		joomla.MTAPIConfig.servicesUrl("Valid URL", ServiceURL.Taxi_Email); 
		joomla.MTAPIConfig.saveAndClose();
		closeallwindows();
		GETclearMemCache("Access_Token_PLAYANDWIN");

		endTestCase("End");
	}
}