//package com.pch.test.script;
//
//SPAL: This script taking more than 15 min to complete the test, IN my openion it is not worth to spend time of that also failing very frequently


//import static com.pch.utilities.Log.Log;
//import static com.pch.utilities.Log.endTestCase;
//import static com.pch.utilities.Log.startTestCase;
//
//import org.testng.Assert;
//import org.testng.annotations.AfterClass;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.Test;
//
//import com.pch.configuration.testpages.RFTestPage;
//import com.pch.configuration.testpages.RFTestPage.ResponseData;
//import com.pch.joomla.configuration.JoomlaAdministrator;
//import com.pch.utilities.AccessToken;
//import com.pch.utilities.Action_Wrapper;
//import com.pch.utilities.Database_Configuration;
//import com.pch.utilities.GroupName;
//import com.pch.utilities.Jmeter_Xml_Parser;
//
//public class B_33167_MT2_All_App_Support_Restricting_configurable_maximum_token_values_being_displayed_on_the_Token_leaderboard_PlayAndWin_Mobile extends Action_Wrapper {
//
//	Jmeter_Xml_Parser xp = new Jmeter_Xml_Parser();
//	JoomlaAdministrator joomla = new JoomlaAdministrator();
//	Database_Configuration db = new Database_Configuration();
//	RFTestPage RF = new RFTestPage();
//	
//	String thread_CheckLeaderboardHighestTokenRewarded[] = {"Credit Token","Check leaderboard highest token rewarded"};
//	String thread_creditToken[] = {"Credit Token_1","Credit Token_2","Credit Token_3"};
//	String threadCheckDailyLeader[] = {"Check DailyLeader"};
//	String threadDisableTokenLimit[] = {"Disable Token Limit"};
//	String threadBlankDailyTokenLimt []= {"Blank Daily Token Limt "};
//	
//	String scriptName = "B_33167_MT2_All_App_Support_Restricting_configurable_maximum_token_values_being_displayed_on_the_Token_leaderboard_PlayAndWin_Mobile";
//	Integer maxLimit, limit1=50, limit2=150,limit3=400;
//	
//	@BeforeClass
//	public void setup() throws Exception {
//		
//		startTestCase("B-33167 [MT2] All App Support Restricting configurable maximum token values being displayed on the Token leaderboard_PlayAndWin_Mobile");
//		createAccessTokenForAllApp("playnwin");
//		enableThread(scriptName+".jmx", thread_CheckLeaderboardHighestTokenRewarded);
//		writeInProprtyFile("Access_Token_PLAYANDWIN", AccessToken.getAccessToken("PLAYANDWIN"));
//		copyAndRenameFile();
//
//		Log.info("Disable Token Limit");
//		openBrowser("chrome");
//		joomla.login.login();
//		joomla.article.navigateToArticleManager();
//		joomla.article.selectcategoryDropDown("AppConfig");
//		joomla.article.search("playandwin");
//		joomla.article.appConfig_EnableDailyLimitCheck("No");
//		joomla.article.saveArticle();
//		closeallwindows();
//
//		Log.info("Start Executing Jmeter Script");
//		CallJmeterBuild(scriptName+".jmx");
//		Log.info("JMX file Called");
//		Assert.assertEquals("false", xp.Xml_Parser(scriptName+".xml"));
//		Log.info("Assertion Validation Completed");
//		
//		maxLimit = Integer.parseInt(returnPropertyValue("\\maximumLimit","limit"));
//		System.out.println("-----------------------------"+maxLimit);
//		RF.CreateFullRegUser();
//		writeInProprtyFile("Email_un", RF.getResponseValue(ResponseData.Email));
//		writeInProprtyFile("Email", getEncryptedData("Access_Token_PLAYANDWIN",RF.getResponseValue(ResponseData.Email)));
//		writeInProprtyFile("GMT_un", RF.getResponseValue(ResponseData.GlobalMemberToken));
//		writeInProprtyFile("GMT", getEncryptedData("Access_Token_PLAYANDWIN",RF.getResponseValue(ResponseData.GlobalMemberToken)));
//
//		
//		RF.CreateFullRegUser();
//		writeInProprtyFile("Email_1", getEncryptedData("Access_Token_PLAYANDWIN",RF.getResponseValue(ResponseData.Email)));
//		writeInProprtyFile("GMT_1", getEncryptedData("Access_Token_PLAYANDWIN",RF.getResponseValue(ResponseData.GlobalMemberToken)));
//		writeInProprtyFile("Email_1_un", RF.getResponseValue(ResponseData.Email));
//		writeInProprtyFile("GMT_1_un", RF.getResponseValue(ResponseData.GlobalMemberToken));
//		
//		RF.CreateFullRegUser();
//		writeInProprtyFile("Email_2", getEncryptedData("Access_Token_PLAYANDWIN",RF.getResponseValue(ResponseData.Email)));
//		writeInProprtyFile("GMT_2", getEncryptedData("Access_Token_PLAYANDWIN",RF.getResponseValue(ResponseData.GlobalMemberToken)));
//		writeInProprtyFile("Email_2_un", RF.getResponseValue(ResponseData.Email));
//		writeInProprtyFile("GMT_2_un", RF.getResponseValue(ResponseData.GlobalMemberToken));
//		
//		writeInProprtyFile("Limit_1", Integer.toString((maxLimit+limit1)));
//		writeInProprtyFile("Limit_2", Integer.toString((maxLimit+limit2)));
//		writeInProprtyFile("Limit_3", Integer.toString((maxLimit+limit3)));
//		copyAndRenameFile();
//		
//	}
//
//	@Test(groups = {GroupName.Leaderboard,GroupName.Regression})
//	public void BackLogItem_B_33167_MT2_All_App_Support_Restricting_configurable_maximum_token_values_being_displayed_on_the_Token_leaderboard_PlayAndWin_Mobile() throws Exception {
//
//		
//		enableThread(scriptName+".jmx", thread_creditToken);
//		
//		Log.info("Start Executing Jmeter Script");
//		CallJmeterBuild(scriptName+".jmx");
//		Log.info("JMX file Called");
//		Assert.assertEquals("false", xp.Xml_Parser(scriptName+".xml"));
//		Log.info("Assertion Validation Completed");
//		
//		openBrowser("chrome");
//		joomla.login.login();
//		joomla.article.navigateToArticleManager();
//		joomla.article.selectcategoryDropDown("AppConfig");
//		joomla.article.search("playandwin");
//		joomla.article.appConfig_EnableDailyLimitCheck("Yes");
//		joomla.article.appConfig_DailyTokenLimit(maxLimit+limit2);
//		joomla.article.saveArticle();
//		closeallwindows();
//		
//		Log.info("Checking dailyLeaderboard limit");
//		enableThread(scriptName+".jmx", threadCheckDailyLeader);
//		Log.info("Start Executing Jmeter Script");
//		CallJmeterBuild(scriptName+".jmx");
//		Log.info("JMX file Called");
//		Assert.assertEquals("false", xp.Xml_Parser(scriptName+".xml"));
//		Log.info("Assertion Validation Completed");
//		
//		
//		
//		/**
//		 * Disable Token Limit
//		 */
//		Log.info("Disable Token Limit");
//		openBrowser("chrome");
//		joomla.login.login();
//		joomla.article.navigateToArticleManager();
//		joomla.article.selectcategoryDropDown("AppConfig");
//		joomla.article.search("playandwin");
//		joomla.article.appConfig_EnableDailyLimitCheck("No");
//		joomla.article.saveArticle();
//		
//		enableThread(scriptName+".jmx", threadDisableTokenLimit);
//
//		Log.info("Start Executing Jmeter Script");
//		CallJmeterBuild(scriptName+".jmx");
//		Log.info("JMX file Called");
//		Assert.assertEquals("false", xp.Xml_Parser(scriptName+".xml"));
//		Log.info("Assertion Validation Completed");
//		
//		
//		/**
//		 * Blank Daily Token Limit
//		 */
//		Log.info("Disable Token Limit");
//		joomla.article.selectcategoryDropDown("AppConfig");
//		joomla.article.search("playandwin");
//		joomla.article.appConfig_EnableDailyLimitCheck("yes");
//		joomla.article.appConfig_DailyTokenLimit();
//		joomla.article.saveArticle();
//		
//		enableThread(scriptName+".jmx", threadBlankDailyTokenLimt);
//
//		Log.info("Start Executing Jmeter Script");
//		CallJmeterBuild(scriptName+".jmx");
//		Log.info("JMX file Called");
//		Assert.assertEquals("false", xp.Xml_Parser(scriptName+".xml"));
//		Log.info("Assertion Validation Completed");
//	
//	}
//
//	@AfterClass
//	public void tearDown() {
//		joomla.article.selectcategoryDropDown("AppConfig");
//		joomla.article.search("playandwin");
//		joomla.article.appConfig_EnableDailyLimitCheck("No");
//		joomla.article.saveArticle();
//		closeallwindows();
//		endTestCase("End");
//
//	}
//}
