package com.pch.test.script;

import static com.pch.utilities.Log.Log;
import static com.pch.utilities.Log.endTestCase;
import static com.pch.utilities.Log.startTestCase;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.pch.configuration.testpages.RFTestPage;
import com.pch.configuration.testpages.RFTestPage.ResponseData;
import com.pch.utilities.Action_Wrapper;
import com.pch.utilities.GroupName;
import com.pch.utilities.Jmeter_Xml_Parser;

public class B_31685_All_Apps_MT2_Expose_Reset_Password_API_NegativeScenarios extends Action_Wrapper {

	RFTestPage RF = new RFTestPage();
	Jmeter_Xml_Parser xp = new Jmeter_Xml_Parser();

	String response, acceessCode,email, response1;
	String scriptName = this.getClass().getSimpleName();
	
	@BeforeTest
	public void setup() throws IOException, Exception {
		startTestCase("B_31685_All_Apps_MT2_Expose_Reset_Password_API_NegativeScenarios");
		createAccessTokenForAllApp("pchapp");

		response = RF.createFullRegUserAPI();
		email=RF.getResponseValueAPI(response, ResponseData.Email);

		writeInProprtyFile("Encrypted_Email", getEncryptedData("Access_Token_PCHAPP",email));
		writeInProprtyFile("Encrypted_GMT", getEncryptedData("Access_Token_PCHAPP",RF.getResponseValueAPI(response, ResponseData.GlobalMemberToken)));
		
		writeInProprtyFile("AccessCode",RF.forgotPasswordAccessCode(RF.getResponseValueAPI(response, ResponseData.Email)));
		
		acceessCode = RF.forgotPasswordAccessCode(RF.getResponseValueAPI(response, ResponseData.Email));
		writeInProprtyFile("AccessCode2",RF.forgotPasswordAccessCode(RF.getResponseValueAPI(response, ResponseData.Email)));
		
		RF.forgotPasswordExpireAccessCode(email,acceessCode);
		writeInProprtyFile("AccessCode1",acceessCode);
		
		response1 = RF.createFullRegUserAPI();
		writeInProprtyFile("Encrypted_Email_new", getEncryptedData("Access_Token_PCHAPP",RF.getResponseValueAPI(response1, ResponseData.Email)));
		writeInProprtyFile("Encrypted_GMT_new", getEncryptedData("Access_Token_PCHAPP",RF.getResponseValueAPI(response1, ResponseData.GlobalMemberToken)));

		copyAndRenameFile();
	}

	@Test(groups = { GroupName.RegFoundation, GroupName.Regression })
	public void story_B_31685_All_Apps_MT2_Expose_Reset_Password_API_NegativeScenarios() throws IOException, Exception {

		Log.info("Start Executing Jmeter Script");
		CallJmeterBuild(scriptName+".jmx");
		Log.info("JMX file Called");
		Assert.assertEquals("false", xp.Xml_Parser(scriptName+".xml"));
		Log.info("Assertion Validation Completed");

	}	
	
	@AfterClass
	public void tearDown() throws IOException, Exception {
		endTestCase("End");

	}
	
}
