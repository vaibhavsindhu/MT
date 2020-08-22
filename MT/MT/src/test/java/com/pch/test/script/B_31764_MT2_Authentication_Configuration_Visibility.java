package com.pch.test.script;

import static com.pch.utilities.Log.endTestCase;
import static com.pch.utilities.Log.startTestCase;
import static com.pch.utilities.Log.Log;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.pch.joomla.configuration.JoomlaAdministrator;
import com.pch.utilities.Action_Wrapper;
import com.pch.utilities.GroupName;

public class B_31764_MT2_Authentication_Configuration_Visibility extends Action_Wrapper{
	JoomlaAdministrator joomla = new JoomlaAdministrator();

	@BeforeClass
	public void setup() throws Exception {
		
		startTestCase("B-31764 MT2- Authentication Configuration Visibilty");
		openBrowser("chrome");
		joomla.login.login();
		
	}

	@Test(groups = { "Sprint 15",GroupName.AuthorizationServer })
	public void story_B_31764_MT2_Authentication_Configuration_Visibility() throws Exception {

		Log.info("Checking for Public");
		joomla.user.changeUserGroup("Public");
		joomla.login.clickLogoutButton();
		joomla.login.navigateTLogin();
		joomla.login.login(returnPropertyValue("Joomla_Username_2"),returnPropertyValue("Joomla_Password_2"));
		Assert.assertTrue(pageSource().contains("You do not have access to the administrator section of this site."));

		Log.info("Checking for Manager");
		joomla.login.login();
		joomla.user.changeUserGroup("Manager");
		joomla.login.clickLogoutButton();
		joomla.login.navigateTLogin();
		joomla.login.login(returnPropertyValue("Joomla_Username_2"),returnPropertyValue("Joomla_Password_2"));
		joomla.manageOauthClient.navigate();
		Assert.assertTrue(pageSource().contains("You are not authorised to view this resource."));
		joomla.login.clickLogoutButton();
		
		Log.info("Checking for Administrator");
		joomla.login.login();
		joomla.user.changeUserGroup("Administrator");
		joomla.login.clickLogoutButton();
		joomla.login.navigateTLogin();
		joomla.login.login(returnPropertyValue("Joomla_Username_2"),returnPropertyValue("Joomla_Password_2"));
		joomla.manageOauthClient.navigate();
		Assert.assertTrue(pageSource().contains("You are not authorised to view this resource."));
		joomla.login.clickLogoutButton();
		
		Log.info("Checking for Casino Admin");
		joomla.login.login();
		joomla.user.changeUserGroup("Casino Admin");
		joomla.login.clickLogoutButton();
		joomla.login.navigateTLogin();
		joomla.login.login(returnPropertyValue("Joomla_Username_2"),returnPropertyValue("Joomla_Password_2"));
		joomla.manageOauthClient.navigate();
		Assert.assertTrue(pageSource().contains("You are not authorised to view this resource."));
		joomla.login.clickLogoutButton();

		Log.info("Checking for Live To Win Admin");
		joomla.login.login();
		joomla.user.changeUserGroup("Live To Win Admin");
		joomla.login.clickLogoutButton();
		joomla.login.navigateTLogin();
		joomla.login.login(returnPropertyValue("Joomla_Username_2"),returnPropertyValue("Joomla_Password_2"));
		joomla.manageOauthClient.navigate();
		Assert.assertTrue(pageSource().contains("You are not authorised to view this resource."));
		joomla.login.clickLogoutButton();
		
		Log.info("Checking for Play and Win Admin");
		joomla.login.login();
		joomla.user.changeUserGroup("Play and Win Admin");
		joomla.login.clickLogoutButton();
		joomla.login.navigateTLogin();
		joomla.login.login(returnPropertyValue("Joomla_Username_2"),returnPropertyValue("Joomla_Password_2"));
		joomla.manageOauthClient.navigate();
		Assert.assertTrue(pageSource().contains("You are not authorised to view this resource."));
		joomla.login.clickLogoutButton();
		
		Log.info("Checking for Real Bucks Admin");
		joomla.login.login();
		joomla.user.changeUserGroup("Real Bucks Admin");
		joomla.login.clickLogoutButton();
		joomla.login.navigateTLogin();
		joomla.login.login(returnPropertyValue("Joomla_Username_2"),returnPropertyValue("Joomla_Password_2"));
		joomla.manageOauthClient.navigate();
		Assert.assertTrue(pageSource().contains("You are not authorised to view this resource."));
		joomla.login.clickLogoutButton();
		
		Log.info("Checking for PCHApp Admin");
		joomla.login.login();
		joomla.user.changeUserGroup("PCHApp Admin");
		joomla.login.clickLogoutButton();
		joomla.login.navigateTLogin();
		joomla.login.login(returnPropertyValue("Joomla_Username_2"),returnPropertyValue("Joomla_Password_2"));
		joomla.manageOauthClient.navigate();
		Assert.assertTrue(pageSource().contains("You are not authorised to view this resource."));
		joomla.login.clickLogoutButton();
		
		Log.info("Checking for Registered");
		joomla.login.login();
		joomla.user.changeUserGroup("Registered");
		joomla.login.clickLogoutButton();
		joomla.login.navigateTLogin();
		joomla.login.login(returnPropertyValue("Joomla_Username_2"),returnPropertyValue("Joomla_Password_2"));
		Assert.assertTrue(pageSource().contains("You do not have access to the administrator section of this site."));
		
		Log.info("Checking for Author");
		joomla.login.login();
		joomla.user.changeUserGroup("Author");
		joomla.login.clickLogoutButton();
		joomla.login.navigateTLogin();
		joomla.login.login(returnPropertyValue("Joomla_Username_2"),returnPropertyValue("Joomla_Password_2"));
		Assert.assertTrue(pageSource().contains("You do not have access to the administrator section of this site."));
		
		Log.info("Checking for Editor");
		joomla.login.login();
		joomla.user.changeUserGroup("Editor");
		joomla.login.clickLogoutButton();
		joomla.login.navigateTLogin();
		joomla.login.login(returnPropertyValue("Joomla_Username_2"),returnPropertyValue("Joomla_Password_2"));
		Assert.assertTrue(pageSource().contains("You do not have access to the administrator section of this site."));
		
		Log.info("Checking for Publisher");
		joomla.login.login();
		joomla.user.changeUserGroup("Publisher");
		joomla.login.clickLogoutButton();
		joomla.login.navigateTLogin();
		joomla.login.login(returnPropertyValue("Joomla_Username_2"),returnPropertyValue("Joomla_Password_2"));
		Assert.assertTrue(pageSource().contains("You do not have access to the administrator section of this site."));
		
		Log.info("Checking for Super Users");
		joomla.login.login();
		joomla.user.changeUserGroup("Super Users");

		joomla.login.clickLogoutButton();
		joomla.login.navigateTLogin();
		joomla.login.login(returnPropertyValue("Joomla_Username_2"),returnPropertyValue("Joomla_Password_2"));
		joomla.manageOauthClient.navigate();
		Assert.assertTrue(pageSource().contains("Manage Oauth Client"));
		joomla.login.clickLogoutButton();
	}

	@AfterClass
	public void tearDown() {
		closeallwindows();
		endTestCase("End");
	}
}