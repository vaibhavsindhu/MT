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
import com.pch.joomla.configuration.MidTierApiConfiguration;
import com.pch.joomla.configuration.MidTierApiConfiguration.ServiceURL;
import com.pch.utilities.Action_Wrapper;
import com.pch.utilities.GroupName;
import com.pch.utilities.Jmeter_Xml_Parser;

public class B_31680_MT2_Memcached_APIConfig extends Action_Wrapper {
	Jmeter_Xml_Parser xp = new Jmeter_Xml_Parser();
	String response;
	JoomlaAdministrator joomla =new JoomlaAdministrator();
	MidTierApiConfiguration MTAPIConfig =new MidTierApiConfiguration();
	RFTestPage RF = new RFTestPage();
	
	@BeforeClass
	public void setup() throws Exception {
		
		startTestCase("B_31680_MT2_Memcached_APIConfig");
		createAccessTokenForAllApp("pchapp");
	}

	@Test(groups = {GroupName.Memcached,GroupName.Regression })
	public void BacklogItem_B_31680_MT2_Memcached_APIConfig() throws Exception {
		
		// Make changes to Joomla Under API Components
		openBrowser("chrome");
		joomla.login.login();
		MTAPIConfig.navigateToAPIComponents();
		MTAPIConfig.servicesUrl("Alter URL", ServiceURL.Registration_Foundation);
		MTAPIConfig.saveAndClose();
		GETclearMemCache("Access_Token_PCHAPP");
		
		// Full reg User Creation
		response = RF.createFullRegUserAPI();
		writeInProprtyFile("Encrypted_Email", getEncryptedData("Access_Token_PCHAPP",RF.getResponseValueAPI(response, ResponseData.Email)));
		writeInProprtyFile("Encrypted_GMT", getEncryptedData("Access_Token_PCHAPP",RF.getResponseValueAPI(response, ResponseData.GlobalMemberToken)));
		copyAndRenameFile();
		
		// Run JMeter Script		
		Log.info("Start Executing Jmeter Script");
		CallJmeterBuild("B_31680_MT2_Memcached_APIConfig.jmx");
		
		Assert.assertEquals("false", xp.Xml_Parser("B_31680_MT2_Memcached_APIConfig.xml"));
		Log.info("Validating assertion ");
		
	}

	@AfterClass
	public void tearDown() throws IOException, Exception {
		
		Log.info("Revert The Configuration");
		MTAPIConfig.navigateToAPIComponents();
		MTAPIConfig.servicesUrl("Valid URL", ServiceURL.Registration_Foundation);
		MTAPIConfig.saveAndClose();
		closeallwindows();
		GETclearMemCache("Access_Token_PCHAPP");
		endTestCase("End");

	}
}
