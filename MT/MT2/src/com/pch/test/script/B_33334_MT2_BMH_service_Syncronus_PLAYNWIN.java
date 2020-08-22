package com.pch.test.script;

import static com.pch.utilities.Log.Log;
import static com.pch.utilities.Log.endTestCase;
import static com.pch.utilities.Log.startTestCase;

import java.io.IOException;
import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.pch.configuration.testpages.RFTestPage;
import com.pch.configuration.testpages.ShopperLookUp;
import com.pch.configuration.testpages.RFTestPage.ResponseData;
import com.pch.joomla.configuration.JoomlaAdministrator;
import com.pch.joomla.configuration.MidTierApiConfiguration.ServiceURL;

import com.pch.utilities.Action_Wrapper;
import com.pch.utilities.Database_Configuration;
import com.pch.utilities.GroupName;
import com.pch.utilities.Jmeter_Xml_Parser;

public class B_33334_MT2_BMH_service_Syncronus_PLAYNWIN extends Action_Wrapper {
	JoomlaAdministrator joomla = new JoomlaAdministrator();
	String accessToken;
	RFTestPage RF = new RFTestPage();
	ArrayList<String> bmhArr = new ArrayList<String>();
	Database_Configuration DB = new Database_Configuration();
	Jmeter_Xml_Parser xp = new Jmeter_Xml_Parser();
	String threadGroup[]={"Valid Submit Sweepstake"};
	String threadGroup1[]={"Valid Submit Sweepstake Again"};
	String threadGroup2[]={"Invalid Service URL"};
	String threadGroup3[]={"Quanitity exceeds"};
	String Email,response;
	
	@BeforeClass
	public void setup() throws Exception {
		startTestCase("B-33334 [MT2] BMH service - Syncronus PLAYNWIN");
		createAccessTokenForAllApp("playnwin");

		response=RF.createFullRegUserAPI();
		Email = RF.getResponseValueAPI(response, ResponseData.Email);
		writeInProprtyFile("Encrypted_Email", getEncryptedData("Access_Token_PLAYANDWIN",RF.getResponseValueAPI(response, ResponseData.Email)));
		writeInProprtyFile("Encrypted_GMT", getEncryptedData("Access_Token_PLAYANDWIN",RF.getResponseValueAPI(response, ResponseData.GlobalMemberToken)));
		copyAndRenameFile();

		openBrowser("chrome");
		joomla.login.login();
		joomla.article.navigateToArticleManager();		
		joomla.article.selectcategoryDropDown("AppConfig");
		joomla.article.search("PlayAndWin");
		joomla.article.appConfig_BMHSubmissionMethod("sync");
		joomla.article.saveArticle();
		joomla.article.selectcategoryDropDown("- Select Category -");

		joomla.article.search("Standalone Sweepstake Game 1");
		joomla.article.ConfigureTCvalues();
		joomla.article.saveArticle();
		
		GETclearMemCache("Access_Token_PLAYANDWIN");
		enableThread("B_33334_MT2_BMH_service_Syncronus_PLAYNWIN.jmx", threadGroup);
	}
	
	@Test(groups = {GroupName.BillmeHandler})
	public void story_B_33334_MT2_BMH_service_Syncronus_PLAYNWIN() throws Exception {

		Log.info("Start Executing Jmeter Script");
		  
		CallJmeterBuild("B_33334_MT2_BMH_service_Syncronus_PLAYNWIN.jmx"); 
		Log.info("calling  jmeter file ");
		 
		Assert.assertEquals("false",xp.Xml_Parser("B_33334_MT2_BMH_service_Syncronus_PLAYNWIN.xml")); 
		Log.info("Validating assertion ");
		
		Assert.assertEquals("{\"279\":1}",DB.phpctrl_sso_properties(Email));

		Assert.assertEquals("PCH Unified App", ShopperLookUp.searchByEmailContest(Email,"ForeignSource"));
		
		enableThread("B_33334_MT2_BMH_service_Syncronus_PLAYNWIN.jmx", threadGroup1);
		CallJmeterBuild("B_33334_MT2_BMH_service_Syncronus_PLAYNWIN.jmx"); 
		Log.info("calling  jmeter file ");
		 
		Assert.assertEquals("false",xp.Xml_Parser("B_33334_MT2_BMH_service_Syncronus_PLAYNWIN.xml")); 
		Log.info("Validating assertion ");
		
		Assert.assertEquals("{\"279\":2}",DB.phpctrl_sso_properties(Email));

		Assert.assertEquals("PCH Unified App", ShopperLookUp.searchByEmailContest(Email,"ForeignSource"));
		
		// Entry Queued Scenario Start Here.
		
		enableThread("B_33334_MT2_BMH_service_Syncronus_PLAYNWIN.jmx", threadGroup2);

		joomla.MTAPIConfig.navigateToAPIComponents();
		joomla.MTAPIConfig.servicesUrl("Alter URL", ServiceURL.BillMeHandler);
		joomla.MTAPIConfig.saveAndClose();
		joomla.MTAPIConfig.saveAndClose();
		GETclearMemCache("Access_Token_PLAYANDWIN");
		GETclearMemCache("Access_Token_PLAYANDWIN");

		Log.info("Start Executing Jmeter Script");
		  
		CallJmeterBuild("B_33334_MT2_BMH_service_Syncronus_PLAYNWIN.jmx"); 
		Log.info("calling  jmeter file ");
		 
		Assert.assertEquals("false",xp.Xml_Parser("B_33334_MT2_BMH_service_Syncronus_PLAYNWIN.xml")); 
		Log.info("Validating assertion ");
		
		Assert.assertEquals("{\"279\":3}",DB.phpctrl_sso_properties(Email));
		Assert.assertEquals("0", DB.phpctrl_superprize_queue(Email));

		closeallwindows();

		openBrowser("chrome");

		joomla.login.login();
		joomla.MTAPIConfig.navigateToAPIComponents();
		joomla.MTAPIConfig.servicesUrl("Valid URL", ServiceURL.BillMeHandler);
		joomla.MTAPIConfig.saveAndClose();
		GETclearMemCache("Access_Token_PLAYANDWIN");
		closeallwindows();
		enableThread("B_33334_MT2_BMH_service_Syncronus_PLAYNWIN.jmx", threadGroup3);

		CallJmeterBuild("B_33334_MT2_BMH_service_Syncronus_PLAYNWIN.jmx"); 
		Log.info("calling  jmeter file ");
		 
		Assert.assertEquals("false",xp.Xml_Parser("B_33334_MT2_BMH_service_Syncronus_PLAYNWIN.xml")); 
		Log.info("Validating assertion ");		
	}

	@AfterClass
	public void tearDown() throws IOException, Exception {
		openBrowser("chrome");
		joomla.login.login();
		joomla.MTAPIConfig.navigateToAPIComponents();
		joomla.MTAPIConfig.servicesUrl("Valid URL", ServiceURL.BillMeHandler);
		joomla.MTAPIConfig.saveAndClose();
		GETclearMemCache("Access_Token_PLAYANDWIN");
		closeallwindows();
		endTestCase("End");
	}
}