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
import com.pch.utilities.AccessToken;
import com.pch.utilities.Action_Wrapper;
import com.pch.utilities.GroupName;
import com.pch.utilities.Jmeter_Xml_Parser;

public class B_29892_Allapps_MT2_RegistrationDetails_PCHAPP extends Action_Wrapper {

	Jmeter_Xml_Parser xp = new Jmeter_Xml_Parser();
	String accessToken, response;
	RFTestPage RF = new RFTestPage();
	String threadGroup[]={"HTTP Request - Full Reg Email-GMT", "HTTP Request - Full Reg Email", "HTTP Request - Full Reg Email-GMT-UserAgent-DeviceType"};
	String threadGroup1[]={"HTTP Request - Mini reg Email-GMT", "HTTP Request - Mini reg Email-GMT-UserAgent-DeviceType"};
	String threadGroup2[]={"HTTP Request - FB Only Email - GMT", "HTTP Request - FB Only Email-GMT-UserAgent-DeviceType"};

	@BeforeClass
	public void setup() throws Exception {

		startTestCase("B_29892_Allapps_MT2_RegistrationDetails_PCHAPP");
		createAccessTokenForAllApp("pchapp");

		accessToken = AccessToken.getAccessToken("PCHAPP", "new");
		writeInProprtyFile("Access_Token_PCHAPP", accessToken);
		copyAndRenameFile();

		
		// Code Block for "HTTP Request - Full Reg Email-GMT" Scenario
		
		response = RF.createFullRegUserAPI();
		writeInProprtyFile("Encrypted_Email", getEncryptedData("Access_Token_PCHAPP",RF.getResponseValueAPI(response, ResponseData.Email)));
		writeInProprtyFile("Email", RF.getResponseValueAPI(response, ResponseData.Email));
		writeInProprtyFile("GMT", RF.getResponseValueAPI(response, ResponseData.GlobalMemberToken));
		writeInProprtyFile("Encrypted_GMT", getEncryptedData("Access_Token_PCHAPP",RF.getResponseValueAPI(response, ResponseData.GlobalMemberToken)));
		
		writeInProprtyFile("Address_ID", RF.getResponseValueAPI(response, ResponseData.AddressId));
		copyAndRenameFile();
		
		enableThread("B_29892_Allapps_MT2_RegistrationDetails_PCHAPP.jmx", threadGroup);
				
	}

	@Test(groups = { GroupName.RegFoundation, GroupName.Regression })
	public void story_B_29892_Allapps_MT2_RegistrationDetails_PCHAPP() throws Exception {

		//  *********************  Code Block for "Full Reg " Scenario ********************
		Log.info("Start Executing Jmeter Script");

		CallJmeterBuild("B_29892_Allapps_MT2_RegistrationDetails_PCHAPP.jmx");
		Log.info("JMX file Called");

		Log.info("Starting Assetion");
		Assert.assertEquals("false", xp.Xml_Parser("B_29892_Allapps_MT2_RegistrationDetails_PCHAPP.xml"));
		Log.info("Assetion Validation Completed for Full Reg");
		
		// ************************  Code Block for "Mini Reg " Scenario  **********************
		
		response = RF.createMiniRegUserAPI();
		writeInProprtyFile("EncMiniRegEmail", getEncryptedData("Access_Token_PCHAPP",RF.getResponseValueAPI(response, ResponseData.Email)));
		writeInProprtyFile("MiniRegEmail", RF.getResponseValueAPI(response, ResponseData.Email));
		writeInProprtyFile("MiniRegGMT", RF.getResponseValueAPI(response, ResponseData.GlobalMemberToken));
		writeInProprtyFile("EncMiniRegGMT", getEncryptedData("Access_Token_PCHAPP",RF.getResponseValueAPI(response, ResponseData.GlobalMemberToken)));
		
		writeInProprtyFile("MiniRegAddress_ID", RF.getResponseValueAPI(response, ResponseData.AddressId));
		copyAndRenameFile();
		
		enableThread("B_29892_Allapps_MT2_RegistrationDetails_PCHAPP.jmx", threadGroup1);
		
		Log.info("Start Executing Jmeter Script");

		CallJmeterBuild("B_29892_Allapps_MT2_RegistrationDetails_PCHAPP.jmx");
		Log.info("JMX file Called");

		Log.info("Starting Assetion");
		Assert.assertEquals("false", xp.Xml_Parser("B_29892_Allapps_MT2_RegistrationDetails_PCHAPP.xml"));
		Log.info("Assetion Validation Completed for HTTP Request - Mini Reg");
		
		
		// ************************  Code Block for "FB Reg" Scenario  **********************
		
		response = RF.createFBUserAPI();
		writeInProprtyFile("Encrypted_FBEmail", getEncryptedData("Access_Token_PCHAPP",RF.getResponseValueAPI(response, ResponseData.Email)));
		writeInProprtyFile("FBRegEmail", RF.getResponseValueAPI(response, ResponseData.Email));
		writeInProprtyFile("FBGMT", RF.getResponseValueAPI(response, ResponseData.GlobalMemberToken));
		writeInProprtyFile("Encrypted_FBGMT", getEncryptedData("Access_Token_PCHAPP",RF.getResponseValueAPI(response, ResponseData.GlobalMemberToken)));
		
		writeInProprtyFile("FBAddress_ID", RF.getResponseValueAPI(response, ResponseData.AddressId));
		copyAndRenameFile();
				
		enableThread("B_29892_Allapps_MT2_RegistrationDetails_PCHAPP.jmx", threadGroup2);
		
		Log.info("Start Executing Jmeter Script");

		CallJmeterBuild("B_29892_Allapps_MT2_RegistrationDetails_PCHAPP.jmx");
		Log.info("JMX file Called");

		Log.info("Starting Assetion");
		Assert.assertEquals("false", xp.Xml_Parser("B_29892_Allapps_MT2_RegistrationDetails_PCHAPP.xml"));
		Log.info("Assetion Validation Completed for HTTP Request - FB Only");
	}

	@AfterClass
	public void tearDown() {
		endTestCase("End");

	}
}
