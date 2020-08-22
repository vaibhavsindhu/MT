package com.pch.test.script;
//package com.pch.test.script;
//
//import static com.pch.utilities.Log.Log;
//import static com.pch.utilities.Log.endTestCase;
//import static com.pch.utilities.Log.startTestCase;
//
//import org.testng.Assert;
//import org.testng.annotations.AfterClass;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.Test;
//
//import com.pch.configuration.testpages.RFTestPage;
//import com.pch.utilities.Action_Wrapper;
//import com.pch.utilities.GroupName;
//import com.pch.utilities.Jmeter_Xml_Parser;
//
//public class B_35599_MT2_PCHApp_RFServiceUpdateforRBCore_ForgotPassword extends Action_Wrapper {
//
//	Jmeter_Xml_Parser xp = new Jmeter_Xml_Parser();
//	RFTestPage RF = new RFTestPage();
//
//	@BeforeClass
//	public void setup() throws Exception {
//
//		startTestCase("B_35599_MT2_PCHApp_RFServiceUpdateforRBCore_ForgotPassword");
//		createAccessTokenForAllApp();
//	}
//
//	@Test(groups = { GroupName.RegFoundation, GroupName.Regression })
//	public void Backlog_B_31683_CASHCASINO_MT2_Expose_Forgot_Password_API() throws Exception {
//
//		Log.info("Start Executing Jmeter Script");
//
//		CallJmeterBuild("B_35599_MT2_PCHApp_RFServiceUpdateforRBCore_ForgotPassword.jmx");
//		Log.info("JMX file Called");
//
//		Log.info("starting Assetion ");
//
//		Assert.assertEquals("false", xp.Xml_Parser("B_35599_MT2_PCHApp_RFServiceUpdateforRBCore_ForgotPassword.xml"));
//		Log.info("Assetion Validation Completed");
//	}
//
//	@AfterClass
//	public void tearDown() {
//		endTestCase("End");
//
//	}
//}
