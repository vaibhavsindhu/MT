//package com.pch.test.script;
//
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
//public class B_35675_MT2_TokenBankServices_UserDailyRankingSPLIT_PCHAPP_Mobile extends Action_Wrapper {
//
//	Jmeter_Xml_Parser xp = new Jmeter_Xml_Parser();
//	JoomlaAdministrator joomla = new JoomlaAdministrator();
//	Database_Configuration db = new Database_Configuration();
//	RFTestPage RF = new RFTestPage();
//	
//	String thread_CreditToken[] = {"Credit Token"};
//	String thread_UserDailyRank[] = {"UserDailyRank"};
//	
//	String scriptName = "B_35675_MT2_TokenBankServices_UserDailyRankingSPLIT_PCHAPP_Mobile";
//	Integer TokenAmount = 200;
//	
//	@BeforeClass
//	public void setup() throws Exception {
//		
//		startTestCase("B-35675 [MT2]TokenBank Services -UserDailyRanking- SPLIT - PlayNWIN_Mobile");
//		createAccessTokenForAllApp("pchapp");
//
//		// Create Brand New User and write EMAIL/GMT in property file
//		openBrowser("chrome");
//		RF.CreateFullRegUser();
//		writeInProprtyFile("Email", getEncryptedData("Access_Token_PCHAPP",RF.getResponseValue(ResponseData.Email)));
//		writeInProprtyFile("GMT", getEncryptedData("Access_Token_PCHAPP",RF.getResponseValue(ResponseData.GlobalMemberToken)));
//
//		writeInProprtyFile("Access_Token_PCHAPP", AccessToken.getAccessToken("PCHAPP"));
//		writeInProprtyFile("TokenAmount", Integer.toString((TokenAmount)));
//		copyAndRenameFile();
//
//		closeallwindows();
//	}
//
//	@Test(groups = {GroupName.TokenBank})
//	public void BackLogItem_B_35675_MT2_TokenBankServices_UserDailyRankingSPLIT_PlayNWIN_Desktop() throws Exception {
//		
//		Log.info("Start Executing Jmeter Script for Credit Token");
//		
//		// Credit some tokens to the created user
//		enableThread(scriptName+".jmx", thread_CreditToken);
//		CallJmeterBuild(scriptName+".jmx");
//		Log.info("JMX file Executed");
//		
//		Assert.assertEquals("false", xp.Xml_Parser(scriptName+".xml"));
//		Log.info("Assertion Validation Completed");
//		
//		enableThread(scriptName+".jmx", thread_UserDailyRank);
//		
//		Log.info("Start Executing Jmeter Script");
//		CallJmeterBuild(scriptName+".jmx");
//		Log.info("JMX file Called");
//		
//		Assert.assertEquals("false", xp.Xml_Parser(scriptName+".xml"));
//		Log.info("Assertion Validation Completed");
//			
//	}
//
//	@AfterClass
//	public void tearDown() {
//		endTestCase("End");
//
//	}
//}
