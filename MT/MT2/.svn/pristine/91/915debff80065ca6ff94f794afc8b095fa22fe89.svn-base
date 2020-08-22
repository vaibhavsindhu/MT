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
//import com.pch.utilities.Action_Wrapper;
//import com.pch.utilities.GroupName;
//import com.pch.utilities.Jmeter_Xml_Parser;
//
//public class B_35599_MT2_PCHApp_RFServiceUpdateforRBCore_Login extends Action_Wrapper {
//
//	Jmeter_Xml_Parser xp = new Jmeter_Xml_Parser();
//	String accessToken,response;
//	RFTestPage RF = new RFTestPage();
//
//	@BeforeClass
//	public void setup() throws Exception {
//
//		startTestCase("B_35599_MT2_PCHApp_RFServiceUpdateforRBCore_Login");
//		createAccessTokenForAllApp();
//
//		response=RF.createFullRegUserAPI();
//		writeInProprtyFile("Email",RF.getResponseValueAPI(response, ResponseData.Email));
//		writeInProprtyFile("GMT", RF.getResponseValueAPI(response, ResponseData.GlobalMemberToken));
//		writeInProprtyFile("Encrypted_Email", getEncryptedData("Access_Token_RBCORE",RF.getResponseValueAPI(response, ResponseData.Email)));
//		writeInProprtyFile("Encrypted_GMT", getEncryptedData("Access_Token_RBCORE",RF.getResponseValueAPI(response, ResponseData.GlobalMemberToken)));
//		writeInProprtyFile("LName", RF.getResponseValueAPI(response, ResponseData.LastName));
//		copyAndRenameFile();
//
//	}
//
//	@Test(groups = { GroupName.RegFoundation, GroupName.Regression })
//	public void story_B_35599_MT2_PCHApp_RFServiceUpdateforRBCore_Login() throws Exception {
//
//		Log.info("Start Executing Jmeter Script");
//
//		CallJmeterBuild("B_35599_MT2_PCHApp_RFServiceUpdateforRBCore_Login.jmx");
//		Log.info("JMX file Called");
//
//		Log.info("starting Assetion ");
//
//		Assert.assertEquals("false", xp.Xml_Parser("B_35599_MT2_PCHApp_RFServiceUpdateforRBCore_Login.xml"));
//		Log.info("Assetion Validation Completed");
//	}
//
//	@AfterClass
//	public void tearDown() {
//		endTestCase("End");
//	}
//}
