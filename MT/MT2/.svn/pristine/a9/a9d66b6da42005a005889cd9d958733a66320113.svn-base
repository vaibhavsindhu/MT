package com.pch.test.script;

import static com.pch.utilities.Log.Log;
import static com.pch.utilities.Log.endTestCase;
import static com.pch.utilities.Log.startTestCase;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.pch.configuration.testpages.RFTestPage;
import com.pch.configuration.testpages.RFTestPage.ResponseData;
import com.pch.utilities.Action_Wrapper;
import com.pch.utilities.GroupName;
import com.pch.utilities.Jmeter_Xml_Parser;

public class B_34110_MT2_TokenBankServices_HistorywithPagination extends Action_Wrapper {
	
	Jmeter_Xml_Parser xp = new Jmeter_Xml_Parser();
	RFTestPage RF = new RFTestPage();
	String scriptName = this.getClass().getSimpleName();
	String response;
	
	String ThreadList[] = {"Precondition - Token Credit"};
	
	@BeforeClass
	public void setup() throws Exception {
		startTestCase(scriptName);
		createAccessTokenForAllApp("pchapp");

		response = RF.createMiniRegUserAPI();
		writeInProprtyFile("Encrypted_Email", getEncryptedData("Access_Token_PCHAPP", RF.getResponseValueAPI(response,ResponseData.Email)));
		writeInProprtyFile("Encrypted_GMT", getEncryptedData("Access_Token_PCHAPP", RF.getResponseValueAPI(response,ResponseData.GlobalMemberToken)));
		copyAndRenameFile();

		enableThread(scriptName+".jmx", ThreadList);
	}

	@Test(groups = { GroupName.TokenBank, GroupName.Regression })
	public void story_B_34110_MT2_TokenBankServices_HistorywithPagination() throws Exception {
		Log.info("Start Executing Jmeter Script");
		
		CallJmeterBuild(scriptName+".jmx");
		Log.info("calling  jmeter file ");
		Assert.assertEquals("false", xp.Xml_Parser(scriptName+".xml"));
		Log.info("Validating assertion ");
		
		
		//Disable Pre-Condition Thread and run all other threads
		disableThread(scriptName+".jmx", ThreadList);
		CallJmeterBuild(scriptName+".jmx");
		
		Assert.assertEquals("false", xp.Xml_Parser(scriptName+".xml"));
		Log.info("Validating assertion ");
		
	}

	@AfterClass
	public void tearDown() throws IOException, Exception {
		endTestCase("End");
	}
}