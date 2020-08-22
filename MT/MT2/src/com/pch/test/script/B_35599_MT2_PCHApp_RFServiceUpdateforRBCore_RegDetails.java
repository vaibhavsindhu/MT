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
//public class B_35599_MT2_PCHApp_RFServiceUpdateforRBCore_RegDetails extends Action_Wrapper {
//
//	Jmeter_Xml_Parser xp = new Jmeter_Xml_Parser();
//	String accessToken;
//	RFTestPage RF = new RFTestPage();
//	String response,Email,GMT,AddressID,MiniRegEmail;
//
//	@BeforeClass
//	public void setup() throws Exception {
//
//		startTestCase("B_35599_MT2_PCHApp_RFServiceUpdateforRBCore_RegDetails");
//		createAccessTokenForAllApp();
//
//		response=RF.createFullRegUserAPI();
//		Email=RF.getResponseValueAPI(response, ResponseData.Email);
//		GMT=RF.getResponseValueAPI(response, ResponseData.GlobalMemberToken);
//		AddressID=RF.getResponseValueAPI(response, ResponseData.AddressId);
//		writeInProprtyFile("Email", Email);
//		writeInProprtyFile("GMT", GMT);
//		writeInProprtyFile("Address_ID", AddressID);
//
//		writeInProprtyFile("Encrypted_Email", getEncryptedData("Access_Token_RBCORE",Email));
//		writeInProprtyFile("Encrypted_GMT", getEncryptedData("Access_Token_RBCORE",GMT));
//		copyAndRenameFile();
//		
//				
//	}
//
//	@Test(groups = { GroupName.RegFoundation, GroupName.Regression })
//	public void story_B_35599_MT2_PCHApp_RFServiceUpdateforRBCore_RegDetails() throws Exception {
//
//		Log.info("Start Executing Jmeter Script");
//
//		CallJmeterBuild("B_35599_MT2_PCHApp_RFServiceUpdateforRBCore_RegDetails.jmx");
//		Log.info("JMX file Called");
//
//		Log.info("Starting Assetion");
//		Assert.assertEquals("false", xp.Xml_Parser("B_35599_MT2_PCHApp_RFServiceUpdateforRBCore_RegDetails.xml"));
//		Log.info("Assetion Validation Completed for Full Reg");
//		
//	}
//
//	@AfterClass
//	public void tearDown() {
//		endTestCase("End");
//
//	}
//}
