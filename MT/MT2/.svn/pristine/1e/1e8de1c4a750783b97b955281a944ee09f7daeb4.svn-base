package com.pch.test.script;

import static com.pch.utilities.Log.endTestCase;
import static com.pch.utilities.Log.startTestCase;
import static com.pch.utilities.Log.Log;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.pch.configuration.testpages.RFTestPage;
import com.pch.configuration.testpages.RFTestPage.ResponseData;
import com.pch.utilities.Action_Wrapper;
import com.pch.utilities.GroupName;
import com.pch.utilities.HttpUtils;
import com.pch.utilities.Jmeter_Xml_Parser;

public class B_33339_MT2_FBConnectWithExternalTicket_PCHAPP extends	Action_Wrapper {

	Jmeter_Xml_Parser xp = new Jmeter_Xml_Parser();
	String accessToken, Email,AuthTicket ;
	RFTestPage RF = new RFTestPage();
	HttpUtils hutil = new HttpUtils();
	String scriptName = this.getClass().getSimpleName();

	@BeforeClass
	public void setup() throws Exception {

		startTestCase(scriptName);
		createAccessTokenForAllApp("pchapp");

		openBrowser("chrome");
		Log.info("Creating Full Reg user");
		RF.CreateFullRegUser();

		Email = RF.getResponseValue(ResponseData.Email);
		AuthTicket = RF.getTicketValue(ResponseData.AuthTicket);

		Log.info("Full reg user email id  :" + Email);
		Log.info("Auth ticket is  :" + AuthTicket);

		writeInProprtyFile("ExternalId", generateRanString(15, false, true));
		writeInProprtyFile("ExternalEnc_FullRegEmail", getEncryptedData("Access_Token_PCHAPP",Email));
		writeInProprtyFile("AuthTicket_FullReg", AuthTicket);
		copyAndRenameFile();

		Log.info("Creating mini reg user ");
		RF.CreateMiniRegUser();
		
		Email = RF.getResponseValue(ResponseData.Email);
		AuthTicket = RF.getTicketValue(ResponseData.AuthTicket);
		Log.info("Auth ticket is  :" + AuthTicket);
		writeInProprtyFile("Encrypted_MiniRegEmail", getEncryptedData("Access_Token_PCHAPP",Email));
		writeInProprtyFile("AuthTicket_MiniReg", AuthTicket);
		copyAndRenameFile();

	}

	@Test(groups = { GroupName.RegFoundation, GroupName.RegFoundation })
	public void FBConnectWithExternalAuthTicket() throws Exception {

		Log.info("Start Executing Jmeter Script");

		CallJmeterBuild(scriptName+".jmx");
		Log.info("JMX file Called");

		Log.info("starting Assetion ");

		Assert.assertEquals("false", xp.Xml_Parser(scriptName+".xml"));
		Log.info("Assetion Validation Completed");
	}

	@AfterClass
	public void tearDown() {
		closeallwindows();
		endTestCase("End");

	}
}
