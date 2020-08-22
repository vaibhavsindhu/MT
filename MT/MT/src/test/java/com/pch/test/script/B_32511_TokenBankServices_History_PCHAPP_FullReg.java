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
import com.pch.joomla.configuration.JoomlaAdministrator;
import com.pch.utilities.Action_Wrapper;
import com.pch.utilities.GroupName;
import com.pch.utilities.Jmeter_Xml_Parser;

public class B_32511_TokenBankServices_History_PCHAPP_FullReg extends Action_Wrapper {
	JoomlaAdministrator joomla = new JoomlaAdministrator();
	String accessToken,response;
	RFTestPage RF = new RFTestPage();
	Jmeter_Xml_Parser xp = new Jmeter_Xml_Parser();
	

	String Email;
	
	@BeforeClass
	public void setup() throws Exception {
		
		startTestCase("B_32511_TokenBankServices_History_PCHAPP_FullReg");
		createAccessTokenForAllApp("pchapp");

		response = RF.createFullRegUserAPI();
		writeInProprtyFile("Encrypted_Email", getEncryptedData("Access_Token_PCHAPP", RF.getResponseValueAPI(response,ResponseData.Email)));
		writeInProprtyFile("Encrypted_GMT", getEncryptedData("Access_Token_PCHAPP", RF.getResponseValueAPI(response,ResponseData.GlobalMemberToken)));
		copyAndRenameFile();
		
	}
	
	@Test(groups = {GroupName.PCHApp ,GroupName.Regression})
	public void story_B_32511_MT2_TokenBankService_History_PCHAPP() throws Exception {

		Log.info("Start Executing Jmeter Script");
		  
		CallJmeterBuild("B_32511_TokenBankServices_History_PCHAPP_FullReg.jmx"); 
		Log.info("Calling  Jmeter file ");
		 
		Assert.assertEquals("false",xp.Xml_Parser("B_32511_TokenBankServices_History_PCHAPP_FullReg.xml")); 
		Log.info("Validating assertion ");
		


	}

	@AfterClass
	public void tearDown() throws IOException, Exception {

		endTestCase("End");
	}
}