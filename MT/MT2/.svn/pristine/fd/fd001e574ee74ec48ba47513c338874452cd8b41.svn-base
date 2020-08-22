package com.pch.test.script;

import static com.pch.utilities.Log.Log;
import static com.pch.utilities.Log.endTestCase;
import static com.pch.utilities.Log.startTestCase;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.pch.joomla.configuration.JoomlaAdministrator;
import com.pch.utilities.AccessToken;
import com.pch.utilities.Action_Wrapper;
import com.pch.utilities.GroupName;
import com.pch.utilities.Jmeter_Xml_Parser;


public class B_31764_MT2_Authentication_Configuration extends Action_Wrapper{
	Jmeter_Xml_Parser xp = new Jmeter_Xml_Parser();
	JoomlaAdministrator joomla = new JoomlaAdministrator();
	String scriptName = this.getClass().getSimpleName();

	@BeforeClass
	public void setup() throws Exception {
		startTestCase(scriptName);
		openBrowser("chrome");
	}

	@Test(groups = { "Sprint 15",GroupName.AuthorizationServer })
	public void story_B_31764_MT2_Authentication_Configuration() throws Exception {
		joomla.login.login();
		joomla.manageOauthClient.navigate();
		
		joomla.manageOauthClient.pageUI();
		joomla.manageOauthClient.clickToolbarMenu("new");
		joomla.manageOauthClient.clickToolbarMenu("Save & Close");
		joomla.manageOauthClient.validateAddClientErrorMessage();

		joomla.manageOauthClient.addClient_ClientName("Demo_Client_Automation");
		joomla.manageOauthClient.clickToolbarMenu("Save & Close");
		joomla.manageOauthClient.validateAddClientErrorMessage("Secret Key");
		joomla.manageOauthClient.addClient_ClientName("");

		joomla.manageOauthClient.addClient_GenerateKey();
		joomla.manageOauthClient.clickToolbarMenu("Save & Close");
		joomla.manageOauthClient.validateAddClientErrorMessage("client Name");
		
		joomla.manageOauthClient.addClient_ClientName("Demo_Client_Automation");
		joomla.manageOauthClient.addClient_GenerateKey();
		joomla.manageOauthClient.clickToolbarMenu("Save & Close");
		joomla.manageOauthClient.changeDisplayTableDataCount();
		Assert.assertTrue(pageSource().contains("Demo_Client_Automation"));
		
		Assert.assertTrue(AccessToken.checkCreatedGetAddedInDB("Demo_Client_Automation"));
		
		writeInProprtyFile("Access_Token",AccessToken.getAccessToken("Demo_Client_Automation","new"));
		copyAndRenameFile();
		CallJmeterBuild(scriptName+".jmx");
		Log.info("calling  jmeter file ");
		Assert.assertEquals("false", xp.Xml_Parser(scriptName+".xml"));
		Log.info("Validating assertion ");
		
		joomla.manageOauthClient.editClient("Demo_Client_Automation");
		joomla.manageOauthClient.addClient_ClientName("Demo_Client_Automation_1");
		joomla.manageOauthClient.clickToolbarMenu("save");
		waitForPageToLoad(MTDriver);
		
		Assert.assertTrue(AccessToken.checkCreatedGetAddedInDB("Demo_Client_Automation_1"));
		AccessToken.getAccessToken("Demo_Client_Automation_1","new");
		
		Log.info("Deleting Client Created in this test script");
		joomla.manageOauthClient.deleteClient("Demo_Client_Automation_1");
	}

	@AfterClass
	public void tearDown() {
		closeallwindows();
		endTestCase("End");

	}
}