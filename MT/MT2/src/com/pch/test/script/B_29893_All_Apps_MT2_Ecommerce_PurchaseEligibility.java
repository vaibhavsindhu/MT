package com.pch.test.script;

import static com.pch.utilities.Log.Log;
import static com.pch.utilities.Log.endTestCase;
import static com.pch.utilities.Log.startTestCase;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.pch.configuration.testpages.RFTestPage;
import com.pch.configuration.testpages.RFTestPage.ResponseData;
import com.pch.utilities.Action_Wrapper;
import com.pch.utilities.GroupName;
import com.pch.utilities.Jmeter_Xml_Parser;

public class B_29893_All_Apps_MT2_Ecommerce_PurchaseEligibility extends Action_Wrapper{
	
	RFTestPage RF = new RFTestPage();
	Jmeter_Xml_Parser xp = new Jmeter_Xml_Parser();
	String response;
	
	@BeforeClass
	public void setup() throws Exception {
		startTestCase("B_29893_All_Apps_MT2_Ecommerce_PurchaseEligibility");
		createAccessTokenForAllApp("pchapp");
		Log.info("Scenario which will be executed"
				+ "1. Check Eligiblity true"
				+ "2.Check Eligiblity false"
				+ "3. With GMT");
		
		response = RF.createFullRegUserAPI();
		writeInProprtyFile("Encrypted_Email", getEncryptedData("Access_Token_PCHAPP",RF.getResponseValueAPI(response, ResponseData.Email)));
		writeInProprtyFile("Encrypted_GMT", getEncryptedData("Access_Token_PCHAPP",RF.getResponseValueAPI(response, ResponseData.GlobalMemberToken)));
		copyAndRenameFile();
	}

	@Test(groups = { GroupName.Fusion, GroupName.Regression})
	public void story_B_29893_All_Apps_MT2_Ecommerce_PurchaseEligibility() throws Exception {
		Log.info("Start Executing Jmeter Script");
		
		CallJmeterBuild("B_29893_All_Apps_MT2_Ecommerce_PurchaseEligibility.jmx");
		Log.info("calling  jmeter file ");

		Assert.assertEquals("false", xp.Xml_Parser("B_29893_All_Apps_MT2_Ecommerce_PurchaseEligibility.xml"));
		Log.info("Validating assertion ");
	}

	@AfterClass
	public void tearDown() {
		endTestCase("End");
	}
}