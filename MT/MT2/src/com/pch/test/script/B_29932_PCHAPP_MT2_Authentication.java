package com.pch.test.script;

import static com.pch.utilities.Log.Log;
import static com.pch.utilities.Log.endTestCase;
import static com.pch.utilities.Log.startTestCase;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.pch.utilities.Action_Wrapper;
import com.pch.utilities.Database_Configuration;
import com.pch.utilities.GroupName;
import com.pch.utilities.Jmeter_Xml_Parser;

public class B_29932_PCHAPP_MT2_Authentication extends Action_Wrapper {

	Jmeter_Xml_Parser xp = new Jmeter_Xml_Parser();
	String[] clientListData = new String[2];
	Database_Configuration db = new Database_Configuration();
	
	@BeforeClass
	public void setup() throws Exception {
		
		startTestCase("[All apps] MT2.0 Authentication [SPLIT]");
		createAccessTokenForAllApp("pchapp");

		clientListData=db.clientList("PCHAPP");
		writeInProprtyFile("Client_Id", clientListData[0]);
		writeInProprtyFile("Secret_key", clientListData[1]);
		copyAndRenameFile();
	}

	@Test(groups = { GroupName.AuthorizationServer, GroupName.Regression })
	public void BackLogItem_B_29932_PchApp_MT2_Authentication() throws Exception {
		
		Log.info("Start Executing Jmeter Script");
		
		CallJmeterBuild("B_29932_PCHAPP_MT2_Authentication.jmx");
		Log.info("JMX file Called");

		Assert.assertEquals("false", xp.Xml_Parser("B_29932_PCHAPP_MT2_Authentication.xml"));
		Log.info("Assertion Validation Completed");
	}

	@AfterClass
	public void tearDown() {
		endTestCase("End");

	}
}
