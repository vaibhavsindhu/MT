//package com.pch.test.script;
//
//import static com.pch.utilities.Log.Log;
//import static com.pch.utilities.Log.endTestCase;
//import static com.pch.utilities.Log.startTestCase;
//
//import java.io.IOException;
//
//import org.testng.Assert;
//import org.testng.annotations.AfterClass;
//import org.testng.annotations.BeforeTest;
//import org.testng.annotations.Test;
//
//import com.pch.configuration.testpages.RFTestPage;
//import com.pch.configuration.testpages.RFTestPage.ResponseData;
//import com.pch.utilities.AccessToken;
//import com.pch.utilities.Action_Wrapper;
//import com.pch.utilities.GroupName;
//import com.pch.utilities.Jmeter_Xml_Parser;
//
//public class B_35599_MT2_PCHApp_RFServiceUpdateforRBCore_ResetPassword extends Action_Wrapper {
//
//	RFTestPage RF = new RFTestPage();
//	Jmeter_Xml_Parser xp = new Jmeter_Xml_Parser();
//
//	String response;
//	String scriptName="B_31685_All_Apps_MT2_Expose_Reset_Password_API_LIVETOWIN";
//	
//	String threadResetPassword[]={"Reset Password","Login","Reset Password with Used Access Code"};
//	String threadResetPassword1[]={"Reset Password 2nd Access Code","Login Old Password","Login New Password"};
//	
//	@BeforeTest
//	public void setup() throws IOException, Exception {
//		startTestCase("B_31685_All_Apps_MT2_Expose_Reset_Password_API_LIVETOWIN");
//		createAccessTokenForAllApp();
//	
//		writeInProprtyFile("Access_Token_LIVETOWIN", AccessToken.getAccessToken("LIVETOWIN"));
//
//		response = RF.createFullRegUserAPI();
//		
//		writeInProprtyFile("Email_un",RF.getResponseValueAPI(response, ResponseData.Email));
//		writeInProprtyFile("Email", getEncryptedData("Access_Token_LIVETOWIN",RF.getResponseValueAPI(response, ResponseData.Email)));
//		
//		writeInProprtyFile("GMT_un", RF.getResponseValueAPI(response, ResponseData.GlobalMemberToken));
//		writeInProprtyFile("GMT", getEncryptedData("Access_Token_LIVETOWIN",RF.getResponseValueAPI(response, ResponseData.GlobalMemberToken)));
//		
//		writeInProprtyFile("AccessCode",RF.forgotPasswordAccessCode(RF.getResponseValueAPI(response, ResponseData.Email)));
//		writeInProprtyFile("AccessCode1",RF.forgotPasswordAccessCode(RF.getResponseValueAPI(response, ResponseData.Email)));
//		
//		copyAndRenameFile();
//	}
//
//	@Test(groups = { GroupName.RegFoundation, GroupName.Regression })
//	public void story_B_31685_All_Apps_MT2_Expose_Reset_Password_API_LIVETOWIN() throws IOException, Exception {
//
//		Log.info("Validating 1. Reset Password 2. Login 3.Reset Password with Used Access Code");
//		enableThread(scriptName+".jmx", threadResetPassword);
//		Log.info("Start Executing Jmeter Script");
//		CallJmeterBuild(scriptName+".jmx");
//		Log.info("JMX file Called");
//		Assert.assertEquals("false", xp.Xml_Parser(scriptName+".xml"));
//		Log.info("Assertion Validation Completed");
//
//		Log.info("Validating 1. Reset Password 2nd Access Code 2.Login Old Password 3. Login New Password");
//		enableThread(scriptName+".jmx", threadResetPassword1);
//		Log.info("Start Executing Jmeter Script");
//		CallJmeterBuild(scriptName+".jmx");
//		Log.info("JMX file Called");
//		Assert.assertEquals("false", xp.Xml_Parser(scriptName+".xml"));
//		Log.info("Assertion Validation Completed");
//		
//	}	
//	
//	@AfterClass
//	public void tearDown() throws IOException, Exception {
//		endTestCase("End");
//
//	}
//}
