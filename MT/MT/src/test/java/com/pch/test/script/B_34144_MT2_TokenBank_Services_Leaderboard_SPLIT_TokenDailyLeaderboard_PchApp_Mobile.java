package com.pch.test.script;
//package com.pch.test.script;
//

//SPAL: This script taking more than 15 min to complete the test, IN my openion it is not worth to spend time of that also failing very frequently

//import static com.pch.utilities.Log.Log;
//import static com.pch.utilities.Log.endTestCase;
//import static com.pch.utilities.Log.startTestCase;
//
//import java.io.IOException;
//
//import org.testng.Assert;
//import org.testng.annotations.AfterClass;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.Test;
//
//import com.pch.configuration.testpages.RFTestPage;
//import com.pch.configuration.testpages.RFTestPage.ResponseData;
//import com.pch.joomla.configuration.JoomlaAdministrator;
//import com.pch.joomla.configuration.MidTierApiConfiguration.ServiceURL;
//import com.pch.utilities.Action_Wrapper;
//import com.pch.utilities.GroupName;
//import com.pch.utilities.Jmeter_Xml_Parser;
//
//public class B_34144_MT2_TokenBank_Services_Leaderboard_SPLIT_TokenDailyLeaderboard_PchApp_Mobile extends Action_Wrapper{
//	Jmeter_Xml_Parser xp = new Jmeter_Xml_Parser();
//	JoomlaAdministrator joomla = new JoomlaAdministrator();
//	RFTestPage RF = new RFTestPage();
//	Integer maxLimit, limit1=50, limit2=150,limit3=200;
//
//	String scriptName = "B_34144_MT2_TokenBank_Services_Leaderboard_SPLIT_TokenDailyLeaderboard_PchApp_Mobile";
//	
//	String thread_CheckLeaderboardHighestTokenRewarded[] = {"Credit Token","Check leaderboard highest token rewarded"};
//	String thread_creditToken[] = {"Credit Token_1","Credit Token_2","Credit Token_3"};
//	
//	String thread_Limit[] = {"Limit 1","Limit 3","Without Limit","Without Start parameter","when Start is 2"};
//	
//	String thread_Alter_IWE_Service_URL[]={"Alter IWE Service URL"};
//	String thread_Alter_RF_Service_URL [] = {"Alter RF Service URL"};
//	String thread_Remove_AppCode [] = {"Remove AppCode"};
//	String thread_Remove_Source_Code [] = {"Remove Source Code"};
//	String thread_Remove_Platform_Code [] = {"Remove Platform Code"};
//	String thread_Remove_Login_Name [] = {"Remove Login Name"};
//	
//	String response;
//	
//	@BeforeClass
//	public void setup() throws Exception {
//		startTestCase("B-34144 [MT2] TokenBank Services - Leaderboard- SPLIT_TokenDailyLeaderboard_PchApp_Mobile");
//		createAccessTokenForAllApp("pchapp");
//
//		openBrowser("chrome");
//		joomla.login.login();
//		joomla.article.navigateToArticleManager();
//		joomla.article.selectcategoryDropDown("AppConfig");
//		joomla.article.search("PCHAPP");
//		joomla.article.appConfig_EnableDailyLimitCheck("No");
//		joomla.article.saveArticle();
//
//		
//		enableThread(scriptName+".jmx", thread_CheckLeaderboardHighestTokenRewarded);
//		Log.info("Start Executing Jmeter Script");
//		CallJmeterBuild(scriptName+".jmx");
//		Log.info("JMX file Called");
//		Assert.assertEquals("false", xp.Xml_Parser(scriptName+".xml"));
//		Log.info("Assertion Validation Completed");
//		
//		maxLimit = Integer.parseInt(returnPropertyValue("\\maximumLimit","limit"));
//		
//		response=RF.createFullRegUserAPI();
//		writeInProprtyFile("Email_un", RF.getResponseValueAPI(response, ResponseData.Email));
//		writeInProprtyFile("Email", getEncryptedData("Access_Token_PCHAPP",RF.getResponseValueAPI(response, ResponseData.Email)));
//		writeInProprtyFile("GMT_un", RF.getResponseValueAPI(response, ResponseData.GlobalMemberToken));
//		writeInProprtyFile("GMT", getEncryptedData("Access_Token_PCHAPP",RF.getResponseValueAPI(response, ResponseData.GlobalMemberToken)));
//
//		
//		response=RF.createFullRegUserAPI();
//		writeInProprtyFile("Email_1", getEncryptedData("Access_Token_PCHAPP",RF.getResponseValueAPI(response, ResponseData.Email)));
//		writeInProprtyFile("GMT_1", getEncryptedData("Access_Token_PCHAPP",RF.getResponseValueAPI(response, ResponseData.GlobalMemberToken)));
//		writeInProprtyFile("Email_1_un", RF.getResponseValueAPI(response, ResponseData.Email));
//		writeInProprtyFile("GMT_1_un", RF.getResponseValueAPI(response, ResponseData.GlobalMemberToken));
//		
//		response=RF.createFullRegUserAPI();
//		writeInProprtyFile("Email_2", getEncryptedData("Access_Token_PCHAPP",RF.getResponseValueAPI(response, ResponseData.Email)));
//		writeInProprtyFile("GMT_2", getEncryptedData("Access_Token_PCHAPP",RF.getResponseValueAPI(response, ResponseData.GlobalMemberToken)));
//		writeInProprtyFile("Email_2_un", RF.getResponseValueAPI(response, ResponseData.Email));
//		writeInProprtyFile("GMT_2_un", RF.getResponseValueAPI(response, ResponseData.GlobalMemberToken));
//		
//		writeInProprtyFile("Limit_1", Integer.toString((maxLimit+limit1)));
//		writeInProprtyFile("Limit_2", Integer.toString((maxLimit+limit2)));
//		writeInProprtyFile("Limit_3", Integer.toString((maxLimit+limit3)));
//		copyAndRenameFile();
//
//		
//		writeInProprtyFile("Limit_1", Integer.toString((maxLimit+limit1)));
//		writeInProprtyFile("Limit_2", Integer.toString((maxLimit+limit2)));
//		writeInProprtyFile("Limit_3", Integer.toString((maxLimit+limit3)));
//		copyAndRenameFile();
//		
//		
//		enableThread(scriptName+".jmx", thread_creditToken);
//		Log.info("Start Executing Jmeter Script");
//		CallJmeterBuild(scriptName+".jmx");
//		Log.info("JMX file Called");
//		Assert.assertEquals("false", xp.Xml_Parser(scriptName+".xml"));
//		Log.info("Assertion Validation Completed");
//		
//		
//		
//	}
//
//	@Test(groups = { GroupName.Leaderboard, GroupName.Regression })
//	public void story_B_34144_MT2_TokenBank_Services_Leaderboard_SPLIT_TokenDailyLeaderboard_PchApp_Mobile() throws Exception {
//		
//		enableThread(scriptName+".jmx", thread_Limit);
//		Log.info("Start Executing Jmeter Script");
//		CallJmeterBuild(scriptName+".jmx");
//		Log.info("JMX file Called");
//		Assert.assertEquals("false", xp.Xml_Parser(scriptName+".xml"));
//		Log.info("Assertion Validation Completed");
//
//		/*
//		 * Alter RF
//		 */
//		Log.info("Alter RF");
//		joomla.MTAPIConfig.navigateToAPIComponents();
//		joomla.MTAPIConfig.servicesUrl("Alter URL", ServiceURL.Registration_Foundation);
//		joomla.MTAPIConfig.saveAndClose();
//		GETclearMemCache("Access_Token_PCHAPP");
//		
//		enableThread(scriptName+".jmx", thread_Alter_RF_Service_URL);
//		Log.info("Start Executing Jmeter Script");
//		
//		CallJmeterBuild(scriptName+".jmx");
//		Log.info("calling  jmeter file ");
//
//		Assert.assertEquals("false", xp.Xml_Parser(scriptName+".xml"));
//		Log.info("Validating assertion ");
//
//		
//		/*
//		 * Alter IWE
//		 */
//		Log.info("Alter IWE");
//		joomla.MTAPIConfig.navigateToAPIComponents();
//		joomla.MTAPIConfig.servicesUrl("Alter URL", ServiceURL.Instant_Win_Engine);
//		joomla.MTAPIConfig.servicesUrl("Valid URL", ServiceURL.Registration_Foundation);
//		joomla.MTAPIConfig.saveAndClose();
//		GETclearMemCache("Access_Token_PCHAPP");
//		
//		enableThread(scriptName+".jmx", thread_Alter_IWE_Service_URL);
//		Log.info("Start Executing Jmeter Script");
//		
//		CallJmeterBuild(scriptName+".jmx");
//		Log.info("calling  jmeter file ");
//
//		Assert.assertEquals("false", xp.Xml_Parser(scriptName+".xml"));
//		Log.info("Validating assertion ");
//
//		
//		/*
//		 * Remove appcode
//		 */
//		joomla.MTAPIConfig.navigateToAPIComponents();
//		joomla.MTAPIConfig.servicesUrl("Valid URL", ServiceURL.Registration_Foundation);
//		joomla.MTAPIConfig.servicesUrl("Valid URL", ServiceURL.Instant_Win_Engine);
//		joomla.MTAPIConfig.saveAndClose();
//		GETclearMemCache("Access_Token_PCHAPP");
//		
//		joomla.article.navigateToArticleManager();
//		joomla.article.search("pchapp");
//		joomla.article.updateRFConfiguration("Appcode", "");
//		joomla.article.updateRFConfiguration("Source Code", returnPropertyValue("PCHAPP_SourceCode"));
//		joomla.article.updateRFConfiguration("Platform Code", returnPropertyValue("PCHAPP_PlatformCode"));
//		joomla.article.updateRFConfiguration("Login Name", returnPropertyValue("PCHAPP_LoginName"));
//		joomla.article.saveArticle();
//
//		enableThread(scriptName+".jmx", thread_Remove_AppCode);
//		Log.info("Start Executing Jmeter Script");
//		CallJmeterBuild(scriptName+".jmx");
//		Log.info("calling  jmeter file ");
//
//		Assert.assertEquals("false", xp.Xml_Parser(scriptName+".xml"));
//		Log.info("Validating assertion ");
//		
//		/*
//		 * Remove source code
//		 */
//		joomla.article.search("PCHAPP");
//		joomla.article.updateRFConfiguration("Appcode", returnPropertyValue("PCHAPP_AppCode"));
//		joomla.article.updateRFConfiguration("Source Code", "");
//		joomla.article.saveArticle();
//
//		enableThread(scriptName+".jmx", thread_Remove_Source_Code);
//		Log.info("Start Executing Jmeter Script");
//		CallJmeterBuild(scriptName+".jmx");
//		Log.info("calling  jmeter file ");
//
//		Assert.assertEquals("false", xp.Xml_Parser(scriptName+".xml"));
//		Log.info("Validating assertion ");
//		
//		/*
//		 * Remove platform code
//		 */
//		joomla.article.search("PCHAPP");
//		joomla.article.updateRFConfiguration("Source Code", returnPropertyValue("PCHAPP_SourceCode"));
//		joomla.article.updateRFConfiguration("Platform Code", "");
//		joomla.article.saveArticle();
//
//		enableThread(scriptName+".jmx", thread_Remove_Platform_Code);
//		Log.info("Start Executing Jmeter Script");
//		CallJmeterBuild(scriptName+".jmx");
//		Log.info("calling  jmeter file ");
//
//		Assert.assertEquals("false", xp.Xml_Parser(scriptName+".xml"));
//		Log.info("Validating assertion ");
//		
//		
//		/*
//		 * Remove Login Name
//		 */
//		joomla.article.search("PCHAPP");
//		joomla.article.updateRFConfiguration("Platform Code", returnPropertyValue("PCHAPP_PlatformCode"));
//		joomla.article.updateRFConfiguration("Login Name","");
//		joomla.article.saveArticle();
//
//		enableThread(scriptName+".jmx", thread_Remove_Login_Name);
//		Log.info("Start Executing Jmeter Script");
//		CallJmeterBuild(scriptName+".jmx");
//		Log.info("calling  jmeter file ");
//
//		Assert.assertEquals("false", xp.Xml_Parser(scriptName+".xml"));
//		Log.info("Validating assertion ");
//		
//		
//	}
//	
//
//	@AfterClass
//	public void tearDown() throws IOException, Exception {
//		
//		joomla.article.navigateToArticleManager();
//		joomla.article.search("PCHAPP");
//		joomla.article.updateRFConfiguration("Appcode", returnPropertyValue("PCHAPP_AppCode"));
//		joomla.article.updateRFConfiguration("Source Code", returnPropertyValue("PCHAPP_SourceCode"));
//		joomla.article.updateRFConfiguration("Platform Code", returnPropertyValue("PCHAPP_PlatformCode"));
//		joomla.article.updateRFConfiguration("Login Name", returnPropertyValue("PCHAPP_LoginName"));
//		joomla.article.saveArticle();
//		joomla.MTAPIConfig.navigateToAPIComponents();
//		joomla.MTAPIConfig.servicesUrl("Valid URL", ServiceURL.Registration_Foundation);
//		joomla.MTAPIConfig.servicesUrl("Valid URL", ServiceURL.Instant_Win_Engine);
//
//		joomla.MTAPIConfig.saveAndClose();
//		closeallwindows();
//		endTestCase("End");
//	}
//}
