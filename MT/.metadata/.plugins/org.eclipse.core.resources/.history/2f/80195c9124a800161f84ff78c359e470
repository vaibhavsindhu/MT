package com.pch.test.script;

import static com.pch.utilities.Log.Log;
import static com.pch.utilities.Log.endTestCase;
import static com.pch.utilities.Log.startTestCase;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.pch.joomla.configuration.JoomlaAdministrator;
import com.pch.utilities.AccessToken;
import com.pch.utilities.Action_Wrapper;
import com.pch.utilities.GroupName;
import com.pch.utilities.Jmeter_Xml_Parser;

public class B_31764_MT2_Authentication_Configuration_lifetime_access_token extends Action_Wrapper{
	Jmeter_Xml_Parser xp = new Jmeter_Xml_Parser();
	JoomlaAdministrator joomla = new JoomlaAdministrator();

	@BeforeClass
	public void setup() throws Exception {
		startTestCase("B-31764 MT2- Authentication Configuration_lifetime_access_token");
		openBrowser("chrome");
		
		joomla.login.login();
		joomla.manageOauthClient.setExpireTime("222");
	}

	@Test(groups = { "Sprint 15",GroupName.AuthorizationServer, GroupName.Regression })
	public void story_B_31764_MT2_Authentication_Configuration() throws Exception {
		
		Assert.assertTrue(AccessToken.getExpireIn().contains("222"));

		joomla.manageOauthClient.setExpireTime("");
		Assert.assertTrue(AccessToken.getExpireIn().contains("86400"));
		
		joomla.manageOauthClient.setExpireTime("0");
		Assert.assertTrue(AccessToken.getExpireIn().contains("3600"));
		
	}

	@AfterClass
	public void tearDown() throws IOException, Exception {
		
		Log.info("Reverting Back Original Expirationn time");
		joomla.manageOauthClient.setExpireTime("86400");
		Assert.assertTrue(AccessToken.getExpireIn().contains("86400"));
		
		closeallwindows();
		endTestCase("End");

	}
}
