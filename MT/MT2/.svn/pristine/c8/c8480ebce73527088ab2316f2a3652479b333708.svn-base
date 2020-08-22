package com.pch.test.script;

import static com.pch.utilities.Log.Log;
import static com.pch.utilities.Log.endTestCase;
import static com.pch.utilities.Log.startTestCase;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.pch.utilities.Action_Wrapper;

import com.pch.utilities.GroupName;
import com.pch.utilities.Jmeter_Xml_Parser;

public class B_38520_MT2_PCHAppValidationLayer_GameLoad extends Action_Wrapper {
	Jmeter_Xml_Parser xp = new Jmeter_Xml_Parser();
	String jmeterScriptName = this.getClass().getSimpleName();

	
	@BeforeClass
	public void setup() throws Exception {
		
		startTestCase(jmeterScriptName);
		createAccessTokenForAllApp("pchapp");

	}
	
	@Test(groups = {GroupName.PCHApp ,GroupName.Regression})
	public void story_B_38520_MT2_PCHAppValidationLayer_GameLoad() throws Exception {

		CallJmeterBuild(jmeterScriptName+".jmx");
		Log.info("Calling  Jmeter file ");
		
		Assert.assertEquals("false",xp.Xml_Parser(jmeterScriptName+".xml")); 
		Log.info("Validating assertion ");
		
	}
	

	@AfterClass
	public void tearDown() throws IOException, Exception {

		endTestCase("End");
	}
}