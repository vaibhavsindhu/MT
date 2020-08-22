package com.pch.test.script;
//package com.pch.test.script;
//
//import static com.pch.utilities.Log.Log;
//import static com.pch.utilities.Log.endTestCase;
//import static com.pch.utilities.Log.startTestCase;
//
//import java.io.IOException;
//
//import org.testng.Assert;
//import org.testng.annotations.AfterClass;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.Test;
//
//import com.pch.configuration.testpages.RFTestPage;
//import com.pch.configuration.testpages.RFTestPage.ResponseData;
//import com.pch.joomla.configuration.JoomlaAdministrator;
//import com.pch.joomla.configuration.MidTierApiConfiguration.ServiceURL;
//import com.pch.utilities.Action_Wrapper;
//import com.pch.utilities.GroupName;
//import com.pch.utilities.Jmeter_Xml_Parser;
//
//public class B_35599_MT2_PCHApp_RFServiceUpdateforRBCore_MiniReg extends Action_Wrapper {
//	Jmeter_Xml_Parser xp = new Jmeter_Xml_Parser();
//	JoomlaAdministrator joomla = new JoomlaAdministrator();
//	RFTestPage RF = new RFTestPage();
//	String response;
//
//	String scriptName = "B_35599_MT2_PCHApp_RFServiceUpdateforRBCore_MiniReg";
//	String threadGroup1[] = {"Request withOut App/Source and Platform Code"};
//	String threadGroup2[] = {"Request Only With AppCode"};
//	String threadGroup3[] = {"Request Only With App/SourceCode"};
//	String threadGroup4[] = {"Request Only With App/Source and PlatformCode"};
//
//	
//	@BeforeClass
//	public void setup() throws Exception {
//		startTestCase("B_35599_MT2_PCHApp_RFServiceUpdateforRBCore_MiniReg");
//		createAccessTokenForAllApp();
//
//		openBrowser("joomla");
//		joomla.login.login();
//		joomla.MTAPIConfig.navigateToAPIComponents();
//		joomla.MTAPIConfig.servicesUrl("Valid URL", ServiceURL.Registration_Foundation);
//		joomla.MTAPIConfig.saveAndClose();
//		clearMemCache("Access_Token_RBCORE");
//		
//		response = RF.createMiniRegUserAPI();
//		writeInProprtyFile("MiniEmail", RF.getResponseValueAPI(response, ResponseData.Email));
//		writeInProprtyFile("Encrypted_MiniEmail", getEncryptedData("Access_Token_RBCORE", RF.getResponseValueAPI(response, ResponseData.Email)));
//
//		copyAndRenameFile();
//	}
//
//	@Test(groups = { GroupName.RegFoundation, GroupName.Regression })
//	public void story_B_32299_MT2_miniRegister_Member_CashCasino() throws Exception {
//		Log.info("Start Executing Jmeter Script");
//		
//		CallJmeterBuild(scriptName+".jmx");
//		Log.info("Calling  jmeter file ");
//
//		Assert.assertEquals("false", xp.Xml_Parser(scriptName+".xml"));
//		Log.info("Validating assertion ");
//
//	}
//
//	@AfterClass
//	public void tearDown() throws IOException, Exception {
//		closeallwindows();
//		endTestCase("End");
//	}
//}