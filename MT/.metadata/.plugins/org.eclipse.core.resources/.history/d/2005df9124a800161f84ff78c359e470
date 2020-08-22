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
//import com.pch.utilities.GroupName;
//import com.pch.utilities.Jmeter_Xml_Parser;
//
//public class B_35599_MT2_PCHAp_RFServiceUpdateforRBCore_SubscribeWithEmailGMT extends Action_Wrapper {
//
//	Jmeter_Xml_Parser xp = new Jmeter_Xml_Parser();
//	RFTestPage RF = new RFTestPage();
//	JoomlaAdministrator joomla = new JoomlaAdministrator();
//	String response;
//	String scriptName = "B_35599_MT2_PCHAp_RFServiceUpdateforRBCore_SubscribeWithEmailGMT";
//	
//	@BeforeClass
//	public void setup() throws Exception {
//		
//		startTestCase("B_35599_MT2_PCHAp_RFServiceUpdateforRBCore_SubscribeWithEmailGMT");
//		createAccessTokenForAllApp();
//
//		response = RF.createFullRegUserAPI();
//		writeInProprtyFile("Encrypted_Email", getEncryptedData("Access_Token_RBCORE",RF.getResponseValueAPI(response, ResponseData.Email)));
//		writeInProprtyFile("Encrypted_GMT", getEncryptedData("Access_Token_RBCORE",RF.getResponseValueAPI(response, ResponseData.GlobalMemberToken)));
//		copyAndRenameFile();
//
//	}
//
//	@Test(groups = { GroupName.RegFoundation,GroupName.Regression })
//	public void BacklogItem_B_35599_MT2_PCHAp_RFServiceUpdateforRBCore_SubscribeWithEmailGMT() throws Exception {
//		Log.info("Start Executing Jmeter Script");
//		
//		CallJmeterBuild(scriptName+".jmx");
//		Log.info("JMX file Called");
//
//		Assert.assertEquals("false", xp.Xml_Parser(scriptName+".xml"));
//		Log.info("Assetion Validation Completed");
//		
//	}
//
//	@AfterClass
//	public void tearDown() throws IOException, Exception {
//		endTestCase("End");
//	}
//}
