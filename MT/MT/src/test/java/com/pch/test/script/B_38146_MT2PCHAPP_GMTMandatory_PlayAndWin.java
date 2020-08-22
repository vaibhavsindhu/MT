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

public class B_38146_MT2PCHAPP_GMTMandatory_PlayAndWin extends Action_Wrapper {

	Jmeter_Xml_Parser xp = new Jmeter_Xml_Parser();

	RFTestPage RF = new RFTestPage();
	String accessToken,response,GMT,Encrypted_Email,Encrypted_GMT,Email;
	
	

	@BeforeClass
	public void setup() throws Exception {

		startTestCase("B_38146_MT2PCHAPP_GMTMandatory_PlayAndWin");
		createAccessTokenForAllApp("playnwin");
    	copyAndRenameFile();

		
		// Code Block for "HTTP Request - Full Reg Email-GMT" Scenario
		
    	Log.info("***Creating full user ***");
		String response = RF.createFullRegUserAPI();
		Email=RF.getResponseValueAPI(response, ResponseData.Email);
		Log.info("full User Email :"+Email);
		GMT=RF.getResponseValueAPI(response, ResponseData.GlobalMemberToken);
		Log.info("full User GMT  :"+GMT);
		Encrypted_Email=getEncryptedData("Access_Token_PLAYANDWIN",RF.getResponseValueAPI(response, ResponseData.Email));
		Encrypted_GMT=getEncryptedData("Access_Token_PLAYANDWIN",RF.getResponseValueAPI(response, ResponseData.GlobalMemberToken));
		Log.info("***Creating second full user ***");
				
		writeInProprtyFile("Email", Email);
		writeInProprtyFile("GMT", GMT);
		writeInProprtyFile("Encrypted_Email", Encrypted_Email);
		writeInProprtyFile("Encrypted_GMT", Encrypted_GMT);
		
		copyAndRenameFile();

	
				
	}

	@Test(groups = { GroupName.RegFoundation, GroupName.Regression,GroupName.PLAYANDWIN })
	public void story_B_38146_MT2PCHAPP_GMTMandatory_PlayAndWin() throws Exception {

		//  *********************  Code Block for "Full Reg " Scenario ********************
		Log.info("Start Executing Jmeter Script");

		CallJmeterBuild("B_38146_MT2PCHAPP_GMTMandatory_PlayAndWin.jmx");
		Log.info("JMX file Called");

		Log.info("Starting Assetion");
		Assert.assertEquals("false", xp.Xml_Parser("B_38146_MT2PCHAPP_GMTMandatory_PlayAndWin.xml"));
		Log.info("Assetion Validation Completed for Full Reg");
		
		// ************************  Code Block for "Mini Reg " Scenario  **********************
		
		
		Log.info("***Creating full user ***");
		String respon = RF.createMiniRegUserAPI();
		Email=RF.getResponseValueAPI(respon, ResponseData.Email);
		Log.info("full User Email :"+Email);
		GMT=RF.getResponseValueAPI(respon, ResponseData.GlobalMemberToken);
		Log.info("full User GMT  :"+GMT);
		Encrypted_Email=getEncryptedData("Access_Token_PLAYANDWIN",RF.getResponseValueAPI(respon, ResponseData.Email));
		Encrypted_GMT=getEncryptedData("Access_Token_PLAYANDWIN",RF.getResponseValueAPI(respon, ResponseData.GlobalMemberToken));
		writeInProprtyFile("Email", Email);
		writeInProprtyFile("GMT", GMT);
		writeInProprtyFile("Encrypted_Email", Encrypted_Email);
		writeInProprtyFile("Encrypted_GMT", Encrypted_GMT);
		
		copyAndRenameFile();
		
		Log.info("Start Executing Jmeter Script  for Mini Reg User ");

		CallJmeterBuild("B_38146_MT2PCHAPP_GMTMandatory_PlayAndWin.jmx");
		Log.info("JMX file Called");

		Log.info("Starting Assetion");
		Assert.assertEquals("false", xp.Xml_Parser("B_38146_MT2PCHAPP_GMTMandatory_PlayAndWin.xml"));
		Log.info("Assetion Validation Completed for Mini Reg User ");
		
		
		
		// ************************  Code Block for "Silver User " Scenario  **********************
		
		
		Log.info("***Creating full user ***");
		String resp = RF.createSilverUserAPI();
		Email = RF.getResponseValueAPI(resp, ResponseData.Email);
		Log.info("full User Email :" + Email);
		GMT = RF.getResponseValueAPI(resp, ResponseData.GlobalMemberToken);
		Log.info("full User GMT  :" + GMT);
		Encrypted_Email = getEncryptedData("Access_Token_PLAYANDWIN",
				RF.getResponseValueAPI(resp, ResponseData.Email));
		Encrypted_GMT = getEncryptedData("Access_Token_PLAYANDWIN",
				RF.getResponseValueAPI(resp, ResponseData.GlobalMemberToken));
		writeInProprtyFile("Email", Email);
		writeInProprtyFile("GMT", GMT);
		writeInProprtyFile("Encrypted_Email", Encrypted_Email);
		writeInProprtyFile("Encrypted_GMT", Encrypted_GMT);

		copyAndRenameFile();

		Log.info("Start Executing Jmeter Script  for Mini Reg User ");

		CallJmeterBuild("B_38146_MT2PCHAPP_GMTMandatory_PlayAndWin.jmx");
		Log.info("JMX file Called");

		Log.info("Starting Assetion");
		Assert.assertEquals("false",
				xp.Xml_Parser("B_38146_MT2PCHAPP_GMTMandatory_PlayAndWin.xml"));
		Log.info("Assetion Validation Completed for Mini Reg User ");

		
	}

	@AfterClass
	public void tearDown() {
	endTestCase("End");

	}
}
