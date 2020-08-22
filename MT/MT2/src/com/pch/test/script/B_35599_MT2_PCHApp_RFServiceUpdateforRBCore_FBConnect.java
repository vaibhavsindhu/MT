//package com.pch.test.script;
//
//import static com.pch.utilities.Log.endTestCase;
//import static com.pch.utilities.Log.startTestCase;
//import static com.pch.utilities.Log.Log;
//
//import org.testng.Assert;
//import org.testng.annotations.AfterClass;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.Test;
//
//import com.pch.configuration.testpages.RFTestPage;
//import com.pch.configuration.testpages.RFTestPage.ResponseData;
//import com.pch.utilities.Action_Wrapper;
//import com.pch.utilities.GroupName;
//import com.pch.utilities.HttpUtils;
//import com.pch.utilities.Jmeter_Xml_Parser;
//
//public class B_35599_MT2_PCHApp_RFServiceUpdateforRBCore_FBConnect extends Action_Wrapper {
//
//	Jmeter_Xml_Parser xp = new Jmeter_Xml_Parser();
//	String accessToken;
//	RFTestPage RF = new RFTestPage();
//	HttpUtils hutil = new HttpUtils();
//
//	
//	String response, Email, AuthTicket;
//	String scriptName = "B_35599_MT2_PCHApp_RFServiceUpdateforRBCore_FBConnect";
//	String threadGroup1[] = {"Request withOut App/Source and Platform Code"};
//	String threadGroup2[] = {"Request Only With AppCode"};
//	String threadGroup3[] = {"Request Only With App/SourceCode"};
//	String threadGroup4[] = {"Request Only With App/Source and PlatformCode"};
//
//	@BeforeClass
//	public void setup() throws Exception {
//
//		startTestCase("B_35599_MT2_PCHApp_RFServiceUpdateforRBCore_FBConnect");
//		createAccessTokenForAllApp();
//
//		openBrowser("chrome");
//		Log.info("Creating Full Reg user ");
//
//		RF.CreateFullRegUser();
//		Email = RF.getResponseValue(ResponseData.Email);
//		AuthTicket = RF.getTicketValue(ResponseData.AuthTicket);
//		writeInProprtyFile("AuthTicket", AuthTicket);
//		copyAndRenameFile();
//	}
//
//	@Test(groups = { GroupName.RegFoundation, GroupName.RegFoundation })
//	public void story_B_35599_MT2_PCHApp_RFServiceUpdateforRBCore_FBConnect() throws Exception {
//
//				// Thread run for the Request withOut App/Source and Platform Code
//				enableThread(scriptName+".jmx", threadGroup1);
//				Log.info("Start Executing Jmeter Script");
//				CallJmeterBuild(scriptName+".jmx");
//				Log.info("JMX file Called");
//				Assert.assertEquals("false", xp.Xml_Parser(scriptName+".xml"));
//				Log.info("Assertion Validation Completed");
//				
//				// Thread run for the Request Only With AppCode
//
//				RF.CreateFullRegUser();
//
//				Email = RF.getResponseValue(ResponseData.Email);
//				AuthTicket = RF.getTicketValue(ResponseData.AuthTicket);
//				writeInProprtyFile("AuthTicket", AuthTicket);
//				copyAndRenameFile();
//				enableThread(scriptName+".jmx", threadGroup2);
//				Log.info("Start Executing Jmeter Script");
//				CallJmeterBuild(scriptName+".jmx");
//				Log.info("JMX file Called");
//				Assert.assertEquals("false", xp.Xml_Parser(scriptName+".xml"));
//				Log.info("Assertion Validation Completed");
//				
//				// Thread run for the Request Only With App/SourceCode
//				
//				RF.CreateFullRegUser();
//
//				Email = RF.getResponseValue(ResponseData.Email);
//				AuthTicket = RF.getTicketValue(ResponseData.AuthTicket);
//				writeInProprtyFile("AuthTicket", AuthTicket);
//				copyAndRenameFile();
//				
//				enableThread(scriptName+".jmx", threadGroup3);
//				Log.info("Start Executing Jmeter Script");
//				CallJmeterBuild(scriptName+".jmx");
//				Log.info("JMX file Called");
//				Assert.assertEquals("false", xp.Xml_Parser(scriptName+".xml"));
//				Log.info("Assertion Validation Completed");
//				
//				
//				// Thread run for the Request Only With App/Source and PlatformCode
//
//				RF.CreateFullRegUser();
//
//				Email = RF.getResponseValue(ResponseData.Email);
//				AuthTicket = RF.getTicketValue(ResponseData.AuthTicket);
//				writeInProprtyFile("AuthTicket", AuthTicket);
//				copyAndRenameFile();
//				
//				enableThread(scriptName+".jmx", threadGroup4);
//				Log.info("Start Executing Jmeter Script");
//				CallJmeterBuild(scriptName+".jmx");
//				Log.info("JMX file Called");
//				Assert.assertEquals("false", xp.Xml_Parser(scriptName+".xml"));
//				Log.info("Assertion Validation Completed");
//				
//	}
//
//	@AfterClass
//	public void tearDown() {
//		closeallwindows();
//		endTestCase("End");
//
//	}
//}
