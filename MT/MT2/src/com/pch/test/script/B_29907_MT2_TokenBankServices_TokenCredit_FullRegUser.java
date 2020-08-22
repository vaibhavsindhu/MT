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

public class B_29907_MT2_TokenBankServices_TokenCredit_FullRegUser extends Action_Wrapper {
	JoomlaAdministrator joomla = new JoomlaAdministrator();
	String response;
	RFTestPage RF = new RFTestPage();
	Jmeter_Xml_Parser xp = new Jmeter_Xml_Parser();
	String threadGroup[]={"Full Reg With out Token Balance", "Invalid Token Amount"};

	String Email;
	
	@BeforeClass
	public void setup() throws Exception {
		
		startTestCase("B_29907_MT2_TokenBankServices_TokenCredit_FullRegUser");
		createAccessTokenForAllApp("playnwin");
		
		response = RF.createFullRegUserAPI();
		writeInProprtyFile("Encrypted_Email", getEncryptedData("Access_Token_PLAYANDWIN", RF.getResponseValueAPI(response,ResponseData.Email)));
		writeInProprtyFile("Encrypted_GMT", getEncryptedData("Access_Token_PLAYANDWIN", RF.getResponseValueAPI(response,ResponseData.GlobalMemberToken)));

		copyAndRenameFile();

		enableThread("B_29907_MT2_TokenBankServices_TokenCredit_FullRegUser.jmx", threadGroup);
	}
	
	@Test(groups = {GroupName.TokenBank, GroupName.Regression})
	public void story_B_29907_MT2_TokenBankServices_TokenCredit_FullRegUser() throws Exception {

		Log.info("Start Executing Jmeter Script");
		  
		CallJmeterBuild("B_29907_MT2_TokenBankServices_TokenCredit_FullRegUser.jmx"); 
		Log.info("Calling  Jmeter file ");
		 
		Assert.assertEquals("false",xp.Xml_Parser("B_29907_MT2_TokenBankServices_TokenCredit_FullRegUser.xml")); 
		Log.info("Validating assertion ");
		
		disableThread("B_29907_MT2_TokenBankServices_TokenCredit_FullRegUser.jmx", threadGroup);
		
		CallJmeterBuild("B_29907_MT2_TokenBankServices_TokenCredit_FullRegUser.jmx"); 
		Log.info("calling  jmeter file ");
		 
		Assert.assertEquals("false",xp.Xml_Parser("B_29907_MT2_TokenBankServices_TokenCredit_FullRegUser.xml")); 
		Log.info("Validating assertion ");

	}

	@AfterClass
	public void tearDown() throws IOException, Exception {

		closeallwindows();
		endTestCase("End");
	}
}