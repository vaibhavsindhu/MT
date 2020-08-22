package com.pch.test.script;

import static com.pch.utilities.Log.Log;
import static com.pch.utilities.Log.startTestCase;
import static com.pch.utilities.Log.endTestCase;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.pch.configuration.testpages.RFTestPage;
import com.pch.configuration.testpages.RFTestPage.ResponseData;
import com.pch.utilities.Action_Wrapper;
import com.pch.utilities.GroupName;
import com.pch.utilities.Jmeter_Xml_Parser;

public class D_06797_Defect_PlaceOrderFusionAPI_NoResponseatallincaseof_incorrectrequest_SPLIT extends Action_Wrapper {
	Jmeter_Xml_Parser xp = new Jmeter_Xml_Parser();
	String response;
	RFTestPage RF = new RFTestPage();
	String scriptName = this.getClass().getSimpleName();

	@BeforeClass
	public void setup() throws Exception {
		startTestCase("D_06797_Defect_PlaceOrderFusionAPI_NoResponseatallincaseof_incorrectrequest_SPLIT");
		createAccessTokenForAllApp("playnwin");
		
		response=RF.createFullRegUserAPI();
		writeInProprtyFile("Encrypted_GMT", getEncryptedData("Access_Token_PLAYANDWIN", RF.getResponseValueAPI(response,ResponseData.GlobalMemberToken)));
		
		copyAndRenameFile();
	}
	
	@Test(groups = { GroupName.Fusion, GroupName.Regression, "Sprint 8" })
	public void story_B_29893_All_Apps_MT2_Ecommerce_PlaceOrder() throws Exception {
		
		  Log.info("Start Executing Jmeter Script");
		  CallJmeterBuild(scriptName+".jmx");
		  Log.info("calling  jmeter file ");
		 
		  Assert.assertEquals("false", xp.Xml_Parser(scriptName+".xml")); 
		 Log.info("Validating assertion ");
	}

	@AfterClass
	public void tearDown() {
		endTestCase("End");
	}
}