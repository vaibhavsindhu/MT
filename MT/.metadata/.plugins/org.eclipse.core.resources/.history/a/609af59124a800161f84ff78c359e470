package com.pch.test.script;

import static com.pch.utilities.Log.startTestCase;
import static com.pch.utilities.Log.endTestCase;
import static com.pch.utilities.Log.Log;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.pch.configuration.testpages.RFTestPage;
import com.pch.configuration.testpages.RFTestPage.ResponseData;
import com.pch.utilities.Action_Wrapper;
import com.pch.utilities.GroupName;
import com.pch.utilities.Jmeter_Xml_Parser;

public class B_31137_MT2_Leveling_Services_UserLevelingDetails extends Action_Wrapper {

	Jmeter_Xml_Parser xp = new Jmeter_Xml_Parser();
	RFTestPage RF = new RFTestPage();
	String accessToken, response;
	
	@BeforeClass
	public void setup() throws Exception {
		
		startTestCase("[MT2] Leveling Services- QA Only");
		createAccessTokenForAllApp("pchapp");

	}

	@Test(groups = { GroupName.Levelling })
	public void BackLogItem_B_31137_MT2_Leveling_Services_UserLevelingDetails() throws Exception {
		
		Log.info("Start Executing Jmeter Script");
		
		// Full reg User Creation
		response = RF.createFullRegUserAPI();
		writeInProprtyFile("Encrypted_Email", getEncryptedData("Access_Token_PCHAPP",RF.getResponseValueAPI(response, ResponseData.Email)));
		writeInProprtyFile("Encrypted_GMT", getEncryptedData("Access_Token_PCHAPP",RF.getResponseValueAPI(response, ResponseData.GlobalMemberToken)));
		copyAndRenameFile();
		
		
		CallJmeterBuild("B_31137_MT2_Leveling_Services_UserLevelingDetails.jmx");
		Log.info("JMX file Called");

		Assert.assertEquals("false", xp.Xml_Parser("B_31137_MT2_Leveling_Services_UserLevelingDetails.xml"));
		Log.info("Assetion Validation Completed");
	}

	@AfterClass
	public void tearDown() {
		endTestCase("End");

	}
}
