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
import com.pch.joomla.configuration.MidTierApiConfiguration.ServiceURL;
import com.pch.utilities.Action_Wrapper;
import com.pch.utilities.GroupName;
import com.pch.utilities.Jmeter_Xml_Parser;

public class B_36415_MT2_AllApps_Passthru_for_Address_Validation_SPLIT extends Action_Wrapper {
	Jmeter_Xml_Parser xp = new Jmeter_Xml_Parser();
	JoomlaAdministrator joomla = new JoomlaAdministrator();
	String accessToken;
	String ThreadList[] = {"Valid Address"};
	String ThreadList2[] = {"RF Validation API down"};
	String ThreadList1[]= {"ZIP Correction"};
	String ThreadList3[]= {"State Correction"};
	String ThreadList4[]= {"Street Different than ZIP/City"};
	String ThreadList5[]= {"Invalid Address1"};
	String ThreadList6[]= {"Empty Request"};
	String ThreadList7[]= {"Missing Address1"};
	String ThreadList8[]= {"Missing City"};
	String ThreadList9[]= {"Missing State"};
	String ThreadList10[]= {"Missing Zip"};
	
	String scriptName = this.getClass().getSimpleName();
	
	@BeforeClass
	public void setup() throws Exception {
		startTestCase("B_36415_MT2_AllApps_Passthru_for_Address_Validation_SPLIT");
		createAccessTokenForAllApp("pchapp");
		copyAndRenameFile();
		openBrowser("chrome");
		joomla.login.login();
		joomla.MTAPIConfig.navigateToAPIComponents();
		joomla.MTAPIConfig.servicesUrl("Valid URL", ServiceURL.RFValidation_url);
		joomla.MTAPIConfig.saveAndClose();
		GETclearMemCache("Access_Token_PCHAPP");
	
	}

	@Test(groups = { GroupName.RegFoundation, GroupName.Regression })
	public void story_B_36415_MT2_AllApps_Passthru_for_Address_Validation_SPLIT() throws Exception {
		Log.info("Start Executing Jmeter Script");
		
		// Code Block for Valid Address
		
		enableThread(scriptName + ".jmx", ThreadList);
		CallJmeterBuild(scriptName + ".jmx");
		Log.info("calling  jmeter file ");

		Assert.assertEquals("false", xp.Xml_Parser(scriptName+".xml"));
		Log.info("Validating Assertion ");
		
		
		// Code Block for ZIP Correction
		enableThread(scriptName + ".jmx", ThreadList1);
		CallJmeterBuild(scriptName + ".jmx");
		Log.info("calling  jmeter file ");

		Assert.assertEquals("false", xp.Xml_Parser(scriptName+".xml"));
		Log.info("Validating Assertion ");
		
		// Code Block for State Correction Scenario
		enableThread(scriptName + ".jmx", ThreadList3);
		Log.info("Start Executing Jmeter Script");
		CallJmeterBuild(scriptName + ".jmx");
		Log.info("calling  jmeter file ");

		Assert.assertEquals("false", xp.Xml_Parser(scriptName+".xml"));
		Log.info("Assertion Validation Done");
		
		// Code Block For Street Different than ZIP/City Validation
		enableThread(scriptName + ".jmx", ThreadList4);
		Log.info("Start Executing Jmeter Script");
		CallJmeterBuild(scriptName + ".jmx");
		Log.info("calling  jmeter file ");

		Assert.assertEquals("false", xp.Xml_Parser(scriptName+".xml"));
		Log.info("Assertion Validation Done");
		
		// Code Block For Invalid Address1 Validation
		enableThread(scriptName + ".jmx", ThreadList5);
		Log.info("Start Executing Jmeter Script");
		CallJmeterBuild(scriptName + ".jmx");
		Log.info("calling  jmeter file ");

		Assert.assertEquals("false", xp.Xml_Parser(scriptName+".xml"));
		Log.info("Assertion Validation Done");
		
		// Code Block For Empty Request
		enableThread(scriptName + ".jmx", ThreadList6);
		Log.info("Start Executing Jmeter Script");
		CallJmeterBuild(scriptName + ".jmx");
		Log.info("calling  jmeter file ");

		Assert.assertEquals("false", xp.Xml_Parser(scriptName+".xml"));
		Log.info("Assertion Validation Done");
		
		
		// Code Block For Missing Address 1
		enableThread(scriptName + ".jmx", ThreadList7);
		Log.info("Start Executing Jmeter Script");
		CallJmeterBuild(scriptName + ".jmx");
		Log.info("calling  jmeter file ");

		Assert.assertEquals("false", xp.Xml_Parser(scriptName+".xml"));
		Log.info("Assertion Validation Done");
		
		// Code Block For Missing City
		enableThread(scriptName + ".jmx", ThreadList8);
		Log.info("Start Executing Jmeter Script");
		CallJmeterBuild(scriptName + ".jmx");
		Log.info("calling  jmeter file ");

		Assert.assertEquals("false", xp.Xml_Parser(scriptName+".xml"));
		Log.info("Assertion Validation Done");
		
		// Code Block For Missing State
		enableThread(scriptName + ".jmx", ThreadList9);
		Log.info("Start Executing Jmeter Script");
		CallJmeterBuild(scriptName + ".jmx");
		Log.info("calling  jmeter file ");

		Assert.assertEquals("false", xp.Xml_Parser(scriptName+".xml"));
		Log.info("Assertion Validation Done");
		
		// Code Block For Missing ZIP
		enableThread(scriptName + ".jmx", ThreadList10);
		Log.info("Start Executing Jmeter Script");
		CallJmeterBuild(scriptName + ".jmx");
		Log.info("calling  jmeter file ");

		Assert.assertEquals("false", xp.Xml_Parser(scriptName+".xml"));
		Log.info("Assertion Validation Done");
		
		
		// Validation API Down Scenario
		joomla.MTAPIConfig.navigateToAPIComponents();
		joomla.MTAPIConfig.servicesUrl("Alter URL", ServiceURL.RFValidation_url);
		joomla.MTAPIConfig.saveAndClose();

		GETclearMemCache("Access_Token_PCHAPP");
		enableThread(scriptName + ".jmx", ThreadList2);
		
		CallJmeterBuild(scriptName + ".jmx");
		Log.info("=======  Calling  jmeter file for Service URL Scenarios =======");

		Assert.assertEquals("false", xp.Xml_Parser(scriptName+".xml"));
		Log.info("Validation Service URL Validation Assertion ");
		
	}

	@AfterClass
	public void tearDown() throws IOException, Exception {
		
		// Revert Service URL mis-configuration
		joomla.MTAPIConfig.navigateToAPIComponents();
		joomla.MTAPIConfig.servicesUrl("Valid URL", ServiceURL.RFValidation_url);
		joomla.MTAPIConfig.saveAndClose();
		GETclearMemCache("Access_Token_PCHAPP");
		
		closeallwindows();
		endTestCase("End");

	}
}