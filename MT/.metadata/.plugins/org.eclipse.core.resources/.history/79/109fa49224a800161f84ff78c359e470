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

public class B_29893_All_Apps_MT2_Ecommerce_PlaceOrder extends Action_Wrapper {
	Jmeter_Xml_Parser xp = new Jmeter_Xml_Parser();
	RFTestPage RF = new RFTestPage();
	String response,GMT;

	@BeforeClass
	public void setup() throws Exception {
		startTestCase("B_29893_All_Apps_MT2_Ecommerce_PlaceOrder");
		createAccessTokenForAllApp("pchapp");
		
		response = RF.createFullRegUserAPI();
		writeInProprtyFile("Encrypted_Email", getEncryptedData("Access_Token_PCHAPP",RF.getResponseValueAPI(response, ResponseData.Email)));
		writeInProprtyFile("Encrypted_GMT", getEncryptedData("Access_Token_PCHAPP",RF.getResponseValueAPI(response, ResponseData.GlobalMemberToken)));
		writeInProprtyFile("Address_ID", RF.getResponseValueAPI(response, ResponseData.AddressId));
		copyAndRenameFile();
		
		Log.info("Scenario which will be executed"
				+ "1. Placed Product for 2 different product"
				+ "2. invalid offerCode"
				+ "3. invalid ItemMailId"
				+ "4. one valid product detail and one with invalid product detail"
				+ "5. without GMT"
				+ "6. without Products"
				+ "7. invalid DeviceType"
				+ "8. invalid AddressId");
		
		copyAndRenameFile();
	}
	
	@Test(groups = { GroupName.Fusion, GroupName.Regression, "Sprint 14" })
	public void story_B_29893_All_Apps_MT2_Ecommerce_PlaceOrder()
			throws Exception {
		
		  Log.info("Start Executing Jmeter Script");
		  
		  CallJmeterBuild("B_29893_All_Apps_MT2_Ecommerce_PlaceOrder.jmx"); 
		  Log.info("calling  jmeter file ");
		 
		 Assert.assertEquals("false",xp.Xml_Parser("B_29893_All_Apps_MT2_Ecommerce_PlaceOrder.xml")); 
		 Log.info("Validating assertion ");
	}

	@AfterClass
	public void tearDown() {
		endTestCase("End");
	}
}