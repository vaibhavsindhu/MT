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


public class B_29934_All_Apps_MT2_Device_analyzer extends Action_Wrapper {

	Jmeter_Xml_Parser xp = new Jmeter_Xml_Parser();
	String accessToken;
	
	@BeforeClass
	public void setup() throws Exception {
		startTestCase("B-29934 [All Apps] MT2-Device analyzer [SPLIT]");
		createAccessTokenForAllApp("playnwin");
		copyAndRenameFile();	
	}

	@Test(groups = { GroupName.DeviceAnalyzer, GroupName.Regression,"Sprint 12" })
	public void story_B_29934_All_Apps_MT2_Device_analyzer() throws Exception {
		Log.info("Start Executing Jmeter Script");
		
		CallJmeterBuild("B_29934_All_Apps_MT2_Device_analyzer.jmx");
		Log.info("calling  jmeter file ");

		Assert.assertEquals("false", xp.Xml_Parser("B_29934_All_Apps_MT2_Device_analyzer.xml"));
		Log.info("Validating assertion ");
	}

	@AfterClass
	public void tearDown() {
		endTestCase("End");

	}
}
