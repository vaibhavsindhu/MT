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
import com.pch.joomla.configuration.JoomlaAdministrator;
import com.pch.joomla.configuration.MidTierApiConfiguration.ServiceURL;
import com.pch.utilities.Action_Wrapper;
import com.pch.utilities.GroupName;
import com.pch.utilities.Jmeter_Xml_Parser;

public class B_32299_MT2_miniRegister_Member_PlayAndWin extends Action_Wrapper{
	Jmeter_Xml_Parser xp = new Jmeter_Xml_Parser();
	JoomlaAdministrator joomla = new JoomlaAdministrator();
	String scriptName = this.getClass().getSimpleName();
	String threadGroup[]={"Invalid Service URL"};
	
	@BeforeClass
	public void setup() throws Exception {
		startTestCase(scriptName);
		String MiniUUser= RFTestPage.New_Email;
		createAccessTokenForAllApp("playnwin");
		
		writeInProprtyFile("MiniUUser", RFTestPage.New_Email);
		writeInProprtyFile("Encrypted_Email", getEncryptedData("Access_Token_PLAYANDWIN", MiniUUser));
		copyAndRenameFile();
		
		disableThread(scriptName + ".jmx", threadGroup);
	}

	@Test(groups = { GroupName.RegFoundation, GroupName.Regression })
	public void story_B_32299_MT2_miniRegister_Member_PlayAndWin() throws Exception {
		Log.info("Start Executing Jmeter Script");
		
		CallJmeterBuild(scriptName + ".jmx");
		Log.info("Calling  jmeter file");

		Assert.assertEquals("false", xp.Xml_Parser(scriptName + ".xml"));
		Log.info("Validating assertion ");
		
		enableThread(scriptName + ".jmx", threadGroup);

		openBrowser("joomla");
		joomla.login.login();
		joomla.MTAPIConfig.navigateToAPIComponents();
		joomla.MTAPIConfig.servicesUrl("Alter URL", ServiceURL.Registration_Foundation);
		joomla.MTAPIConfig.saveAndClose();
		closeallwindows();
		GETclearMemCache("Access_Token_PLAYANDWIN");
		
		CallJmeterBuild(scriptName + ".jmx");
		Log.info("calling  jmeter file ");

		Assert.assertEquals("false", xp.Xml_Parser(scriptName + ".xml"));
		Log.info("Validating assertion ");
	}

	@AfterClass
	public void tearDown() throws IOException, Exception {
		openBrowser("joomla");
		joomla.login.login();
		joomla.MTAPIConfig.navigateToAPIComponents();
		joomla.MTAPIConfig.servicesUrl("Valid URL", ServiceURL.Registration_Foundation);
		joomla.MTAPIConfig.saveAndClose();
		closeallwindows();
		GETclearMemCache("Access_Token_PLAYANDWIN");
		endTestCase("End");

	}
}