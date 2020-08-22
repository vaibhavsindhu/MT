package com.pch.test.script;

import static com.pch.utilities.Log.Log;
import static com.pch.utilities.Log.endTestCase;
import static com.pch.utilities.Log.startTestCase;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.pch.utilities.Action_Wrapper;
import com.pch.utilities.GroupName;
import com.pch.utilities.Jmeter_Xml_Parser;

public class B_36432_MT2_AllApp_GetWinners_update extends Action_Wrapper {
	Jmeter_Xml_Parser xp = new Jmeter_Xml_Parser();
	
	@BeforeClass
	public void setup() throws Exception {
	startTestCase("B_36432_MT2_AllApp_GetWinners_update");
	createAccessTokenForAllApp("playnwin");
	}

	@Test(groups = { GroupName.IWE, GroupName.Regression })
	public void getWinner() throws Exception {

		Log.info("Start Executing Jmeter Script");

		CallJmeterBuild("B_36432_MT2_AllApp_GetWinners_update.jmx");
		Log.info("JMX file Called");

		Log.info("Starting Assetion");

		Assert.assertEquals("false",xp.Xml_Parser("B_36432_MT2_AllApp_GetWinners_update.xml"));
		Log.info("Assetion Validation Completed");

	}

	@AfterClass
	public void tearDown() {
		Log.info("Quit Driver Instances");
		endTestCase("End");
	}

}
