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
//import com.pch.joomla.configuration.JoomlaAdministrator;
//import com.pch.utilities.AccessToken;
//import com.pch.utilities.Action_Wrapper;
//import com.pch.utilities.GroupName;
//import com.pch.utilities.Jmeter_Xml_Parser;
//
//public class B_35599_MT2_PCHApp_RFServiceUpdateforRBCore_FBConnectEmail extends Action_Wrapper {
//	
//	Jmeter_Xml_Parser xp = new Jmeter_Xml_Parser();
//	JoomlaAdministrator joomla = new JoomlaAdministrator();
//
//	String scriptName = "B_35599_MT2_PCHApp_RFServiceUpdateforRBCore_FBConnectEmail";
//
//	@BeforeClass
//	public void setup() throws Exception {
//		
//		startTestCase("B_35599_MT2_PCHApp_RFServiceUpdateforRBCore_FBConnectEmail");
//		createAccessTokenForAllApp();
//		openBrowser("joomla");
//		joomla.login.login();
//		joomla.article.navigateToArticleManager();
//		joomla.article.selectcategoryDropDown("AppConfig");
//		joomla.article.search("rbcore");
//		joomla.article.updateRFConfiguration("Appcode",returnPropertyValue("RBCORE_AppCode"));
//		joomla.article.updateRFConfiguration("Source Code", returnPropertyValue("RBCORE_SourceCode"));
//		joomla.article.updateRFConfiguration("Platform Code", returnPropertyValue("RBCORE_PlatformCode"));
//		joomla.article.updateRFConfiguration("Login Name", returnPropertyValue("RBCORE_LoginName"));
//		joomla.article.saveArticle();
//		closeallwindows();
//		writeInProprtyFile("Access_Token_RBCORE", AccessToken.getAccessToken("RBCORE","new"));
//		copyAndRenameFile();
//		
//	}
//
//	@Test(groups = { GroupName.RegFoundation, GroupName.Regression })
//	public void story_B_35599_MT2_PCHApp_RFServiceUpdateforRBCore_FBConnectEmail() throws Exception {
//		Log.info("Start Executing Jmeter Script");
//		
//		CallJmeterBuild(scriptName+".jmx");
//		Log.info("Calling  jmeter file ");
//
//		Assert.assertEquals("false", xp.Xml_Parser(scriptName+".xml"));
//		Log.info("Validating Assertion ");
//		
//	}
//
//	@AfterClass
//	public void tearDown() throws IOException, Exception {
//		closeallwindows();
//		endTestCase("End");
//	}
//}