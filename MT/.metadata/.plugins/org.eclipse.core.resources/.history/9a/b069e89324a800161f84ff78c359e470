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
import com.pch.utilities.Database_Configuration;
import com.pch.utilities.GroupName;
import com.pch.utilities.Jmeter_Xml_Parser;


public class B_31684_AllApps_MT2_ExposeAddPasswordAPI_PCHAPP extends Action_Wrapper {
	JoomlaAdministrator joomla = new JoomlaAdministrator();
	Database_Configuration dbc = new Database_Configuration();
	String accessToken,response,GMT,Encrypted_Email,Encrypted_GMT,Email;
	RFTestPage RF = new RFTestPage();
	Jmeter_Xml_Parser xp = new Jmeter_Xml_Parser();
	String ScriptName="B_31684_AllApps_MT2_ExposeAddPasswordAPI_PCHAPP";
	                   
	String thread1[]={"Http-silver user  add password ","Http-MissingParameters","Http-PasswordMismatch"};
	String thread2[]={"Http-MiniRegUserPassword"};
	String thread3[]={"Http-FullRegUserPassword"};
		
			
	@BeforeClass
	public void setup() throws Exception {
			
		startTestCase("B_31684_AllApps_MT2_ExposeAddPasswordAPI_PCHAPP");
		createAccessTokenForAllApp("pchapp");
	
		Log.info("***Creating silver user ***");
		String response = RF.createSilverUserAPI();
		Email=RF.getResponseValueAPI(response, ResponseData.Email);
		Log.info("Silver User Email :"+Email);
		GMT=RF.getResponseValueAPI(response, ResponseData.GlobalMemberToken);
		Log.info("Silver User GMT  :"+GMT);
		Encrypted_Email=getEncryptedData("Access_Token_PCHAPP",RF.getResponseValueAPI(response, ResponseData.Email));
		Encrypted_GMT=getEncryptedData("Access_Token_PCHAPP",RF.getResponseValueAPI(response, ResponseData.GlobalMemberToken));
			
		writeInProprtyFile("Encrypted_Email", Encrypted_Email);
		writeInProprtyFile("Encrypted_GMT", Encrypted_GMT);
		copyAndRenameFile();
						
	}
		
		@Test(groups = {GroupName.Regression, GroupName.RegFoundation})
		public void story_B_31684_AllApps_MT2_ExposeAddPasswordAPI_PCHAPP() throws Exception {
			
			// **** Silver user ****

			Log.info("***for Silver user  ***");
			enableThread(ScriptName+".jmx", thread1);
			Log.info("Start Executing Jmeter Script");
			  
			CallJmeterBuild(ScriptName+".jmx"); 
			Log.info("Calling  Jmeter file ");
			 
			Assert.assertEquals("false",xp.Xml_Parser(ScriptName+".xml")); 
			Log.info("Validating assertion ");
			
			
			// **** Mini Reg User  ****
			openBrowser("chrome");
			RF.CreateMiniRegUser();
			String MiniRegEmail = RF.getResponseValue(ResponseData.Email);
			//writeInProprtyFile("MiniRegEmail", MiniRegEmail);
			Log.info("Created Email Id  :" + MiniRegEmail);
			
			String GMT = RF.getResponseValue(ResponseData.GlobalMemberToken);
			//writeInProprtyFile("GMT", GMT);
			Log.info("GMT of Created Email Id  of mini reg user :" + GMT);
			
			
			
			String Encrypted_Email = getEncryptedData("Access_Token_PCHAPP",MiniRegEmail);
			
			writeInProprtyFile("Encrypted_Email", Encrypted_Email);
			writeInProprtyFile("Encrypted_GMT", getEncryptedData("Access_Token_PCHAPP", GMT));
			copyAndRenameFile();
		
			
			enableThread(ScriptName+".jmx", thread2);
			
			Log.info("***for Mini Reg user   ***");
		
			Log.info("Start Executing Jmeter Script");
					  
			CallJmeterBuild(ScriptName+".jmx"); 
			Log.info("Calling  Jmeter file  for mini reg user ");
					 
			Assert.assertEquals("false",xp.Xml_Parser(ScriptName+".xml")); 
			Log.info("Validating assertion for mini reg user ");
					
// Code Block for "HTTP Request - Full Reg Email-GMT" Scenario
					
			
			RF.CreateFullRegUser();
			String FullRegEmail = RF.getResponseValue(ResponseData.Email);
			Log.info("Created Email Id of full reg user  :" + FullRegEmail);
					
			String GMT_FullReg = RF.getResponseValue(ResponseData.GlobalMemberToken);
					
			Log.info("GMT of Created Email Id  :" + GMT_FullReg);
					
			writeInProprtyFile("Encrypted_Email", getEncryptedData("Access_Token_PCHAPP", FullRegEmail));
			writeInProprtyFile("Encrypted_GMT", getEncryptedData("Access_Token_PCHAPP", GMT_FullReg));
			copyAndRenameFile();
				
			Log.info("Start Executing Jmeter Script");
			enableThread(ScriptName+".jmx", thread3);
			Log.info("Calling  Jmeter file  for Full reg user ");
			CallJmeterBuild(ScriptName+".jmx"); 

			Log.info("Validating assertion for Full  reg user ");
			Assert.assertEquals("false",xp.Xml_Parser(ScriptName+".xml")); 
							
		}

	@AfterClass
	public void tearDown() throws IOException, Exception {
	closeallwindows();
	endTestCase("End");
	}
}