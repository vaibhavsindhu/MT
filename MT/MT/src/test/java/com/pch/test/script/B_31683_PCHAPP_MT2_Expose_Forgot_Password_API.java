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

public class B_31683_PCHAPP_MT2_Expose_Forgot_Password_API extends Action_Wrapper {

	Jmeter_Xml_Parser xp = new Jmeter_Xml_Parser();
	String response;
	RFTestPage RF = new RFTestPage();
	String scriptName = this.getClass().getSimpleName();

	@BeforeClass
	public void setup() throws Exception {

		startTestCase(scriptName);
		createAccessTokenForAllApp("pchapp");

		response=RF.createFullRegUserAPI();
		writeInProprtyFile("Encrypted_FullRegEmail", getEncryptedData("Access_Token_PCHAPP", RF.getResponseValueAPI(response,ResponseData.Email)));
		
		response=RF.createSilverUserAPI();
		writeInProprtyFile("Encrypted_SilverEmail", getEncryptedData("Access_Token_PCHAPP", RF.getResponseValueAPI(response,ResponseData.Email)));
		copyAndRenameFile();
		
	}

	@Test(groups = { GroupName.RegFoundation, GroupName.Regression })
	
	public void Backlog_B_31683_PCHAPP_MT2_Expose_Forgot_Password_API() throws Exception {

		Log.info("Start Executing Jmeter Script");

		CallJmeterBuild(scriptName+".jmx");
		Log.info("JMX file Called");

		Log.info("starting Assetion ");

		Assert.assertEquals("false", xp.Xml_Parser(scriptName+".xml"));
		Log.info("Assetion Validation Completed");
	}

	@AfterClass
	public void tearDown() {
		endTestCase("End");

	}
}
