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
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.Test;
//
//import com.pch.configuration.testpages.RFTestPage;
//import com.pch.configuration.testpages.RFTestPage.ResponseData;
//import com.pch.joomla.configuration.JoomlaAdministrator;
//import com.pch.utilities.Action_Wrapper;
//import com.pch.utilities.Database_Configuration;
//import com.pch.utilities.GroupName;
//import com.pch.utilities.Jmeter_Xml_Parser;
//
//public class B_35599_MT2_PCHApp_RF_ServiceUpdateforRBCore_AddPassword extends Action_Wrapper {
//	JoomlaAdministrator joomla = new JoomlaAdministrator();
//	Database_Configuration dbc = new Database_Configuration();
//	RFTestPage RF = new RFTestPage();
//	Jmeter_Xml_Parser xp = new Jmeter_Xml_Parser();
//	String response, Email;
//	String scriptName = "B_35599_MT2_PCHApp_RF_ServiceUpdateforRBCore_AddPassword";
//	String threadGroup1[] = {"Request withOut App/Source and Platform Code"};
//	String threadGroup2[] = {"Request Only With AppCode"};
//	String threadGroup3[] = {"Request Only With App/SourceCode"};
//	String threadGroup4[] = {"Request Only With App/Source and PlatformCode"};
//
//	@BeforeClass
//	public void setup() throws Exception {
//		
//		startTestCase("B_35599_MT2_PCHApp_RF_ServiceUpdateforRBCore_AddPassword");
//		createAccessTokenForAllApp();
//
//		response = RF.createSilverUserAPI();
//		
//		writeInProprtyFile("Encrypted_Email", getEncryptedData("Access_Token_RBCORE",RF.getResponseValueAPI(response, ResponseData.Email)));
//		writeInProprtyFile("Encrypted_GMT", getEncryptedData("Access_Token_RBCORE",RF.getResponseValueAPI(response, ResponseData.GlobalMemberToken)));
//		copyAndRenameFile();
//	}
//	
//	
//	@Test(groups = {GroupName.Regression, GroupName.RegFoundation})
//	public void story_B_35599_MT2_PCHApp_RF_ServiceUpdateforRBCore_AddPassword() throws Exception {
//		
//		// Thread run for the Request withOut App/Source and Platform Code
//		enableThread(scriptName+".jmx", threadGroup1);
//		Log.info("Start Executing Jmeter Script");
//		CallJmeterBuild(scriptName+".jmx");
//		Log.info("JMX file Called");
//		Assert.assertEquals("false", xp.Xml_Parser(scriptName+".xml"));
//		Log.info("Assertion Validation Completed");
//		
//		// Thread run for the Request Only With AppCode
//		
//		response = RF.createSilverUserAPI();
//		
//		writeInProprtyFile("Encrypted_Email", getEncryptedData("Access_Token_RBCORE",RF.getResponseValueAPI(response, ResponseData.Email)));
//		writeInProprtyFile("Encrypted_GMT", getEncryptedData("Access_Token_RBCORE",RF.getResponseValueAPI(response, ResponseData.GlobalMemberToken)));
//		copyAndRenameFile();
//		enableThread(scriptName+".jmx", threadGroup2);
//		Log.info("Start Executing Jmeter Script");
//		CallJmeterBuild(scriptName+".jmx");
//		Log.info("JMX file Called");
//		Assert.assertEquals("false", xp.Xml_Parser(scriptName+".xml"));
//		Log.info("Assertion Validation Completed");
//		
//		// Thread run for the Request Only With App/SourceCode
//		
//		response = RF.createSilverUserAPI();
//		
//		writeInProprtyFile("Encrypted_Email", getEncryptedData("Access_Token_RBCORE",RF.getResponseValueAPI(response, ResponseData.Email)));
//		writeInProprtyFile("Encrypted_GMT", getEncryptedData("Access_Token_RBCORE",RF.getResponseValueAPI(response, ResponseData.GlobalMemberToken)));
//		copyAndRenameFile();
//		enableThread(scriptName+".jmx", threadGroup3);
//		Log.info("Start Executing Jmeter Script");
//		CallJmeterBuild(scriptName+".jmx");
//		Log.info("JMX file Called");
//		Assert.assertEquals("false", xp.Xml_Parser(scriptName+".xml"));
//		Log.info("Assertion Validation Completed");
//		
//		
//		// Thread run for the Request Only With App/Source and PlatformCode
//		
//		response = RF.createSilverUserAPI();
//		
//		writeInProprtyFile("Encrypted_Email", getEncryptedData("Access_Token_RBCORE",RF.getResponseValueAPI(response, ResponseData.Email)));
//		writeInProprtyFile("Encrypted_GMT", getEncryptedData("Access_Token_RBCORE",RF.getResponseValueAPI(response, ResponseData.GlobalMemberToken)));
//		copyAndRenameFile();
//		enableThread(scriptName+".jmx", threadGroup4);
//		Log.info("Start Executing Jmeter Script");
//		CallJmeterBuild(scriptName+".jmx");
//		Log.info("JMX file Called");
//		Assert.assertEquals("false", xp.Xml_Parser(scriptName+".xml"));
//		Log.info("Assertion Validation Completed");
//		
//	
//	}
//
//	@AfterClass
//	public void tearDown() throws IOException, Exception {
//		endTestCase("End");
//	}
//}