package com.pch.test.script;

import static com.pch.utilities.Log.Log;
import static com.pch.utilities.Log.endTestCase;
import static com.pch.utilities.Log.startTestCase;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.pch.configuration.testpages.RFTestPage;
import com.pch.joomla.configuration.JoomlaAdministrator;
import com.pch.utilities.Action_Wrapper;
import com.pch.utilities.GroupName;
import com.pch.utilities.Jmeter_Xml_Parser;

public class B_32300_MT2_facebookConnectWithEmail_PCHAPP extends Action_Wrapper {
	
	Jmeter_Xml_Parser xp = new Jmeter_Xml_Parser();
	JoomlaAdministrator joomla = new JoomlaAdministrator();
	RFTestPage RF = new RFTestPage();

	String threadGroup[]= {"SourceCode Validation","AppCode Validation","PlateformCode Validation"};
	String threadGroupSource[] = {"SourceCode Validation"};
	String threadGroupAppCode[] = {"AppCode Validation"};
	String threadGroupPlatform[] = {"PlateformCode Validation"};
	String scriptName = this.getClass().getSimpleName();
	@SuppressWarnings("static-access")
	String External_Email= RF.ExternalNewEmail;
	
	@BeforeClass
	public void setup() throws Exception {
		
		startTestCase("B_32300_MT2_facebookConnectWithEmail_PCHAPP");
		createAccessTokenForAllApp("pchapp");
		
		openBrowser("joomla");
		joomla.login.login();
		joomla.article.navigateToArticleManager();
		joomla.article.selectcategoryDropDown("AppConfig");
		joomla.article.search("PCHAPP");
		joomla.article.updateRFConfiguration("Appcode",returnPropertyValue("PCHAPP_AppCode"));
		joomla.article.updateRFConfiguration("Source Code", returnPropertyValue("PCHAPP_SourceCode"));
		joomla.article.updateRFConfiguration("Platform Code", returnPropertyValue("PCHAPP_PlatformCode"));
		joomla.article.updateRFConfiguration("Login Name", returnPropertyValue("PCHAPP_LoginName"));
		joomla.article.saveArticle();
		closeallwindows();
		
		// Generate External ID, External Email and PCHEMAIL
		writeInProprtyFile("ExternalId", generateRanString(15, false, true));
		writeInProprtyFile("External_Email", External_Email);
		writeInProprtyFile("ExternalEnc_Email", getEncryptedData("Access_Token_PCHAPP",External_Email));
		copyAndRenameFile();
		
		disableThread(scriptName+".jmx", threadGroup);

	}

	@Test(groups = { GroupName.RegFoundation, GroupName.Regression })
	public void story_B_32300_MT2_facebookConnectWithEmail_PCHAPP() throws Exception {
		Log.info("Start Executing Jmeter Script");
		
		CallJmeterBuild(scriptName+".jmx");
		Log.info("JMX file Called");
		Assert.assertEquals("false", xp.Xml_Parser(scriptName+".xml"));
		Log.info("Assertion Validation Completed");		
		
		// Admin Related Validation Start Here
		
		openBrowser("joomla");
		joomla.login.login();
		joomla.article.navigateToArticleManager();
		joomla.article.selectcategoryDropDown("AppConfig");
		joomla.article.search("PCHAPP");
		joomla.article.updateRFConfiguration("Appcode", "");
		joomla.article.updateRFConfiguration("Source Code", returnPropertyValue("PCHAPP_SourceCode"));
		joomla.article.updateRFConfiguration("Platform Code", returnPropertyValue("PCHAPP_PlatformCode"));
		joomla.article.updateRFConfiguration("Login Name", returnPropertyValue("PCHAPP_LoginName"));
		joomla.article.saveArticle();
		
		enableThread("B_32300_MT2_facebookConnectWithEmail_PCHAPP.jmx", threadGroupAppCode);
		
		CallJmeterBuild(scriptName+".jmx");
		Log.info("JMX file Called");
		Assert.assertEquals("false", xp.Xml_Parser(scriptName+".xml"));
		Log.info("Assertion Validation Completed");	
		
		joomla.article.search("PCHAPP");
		joomla.article.updateRFConfiguration("Appcode",returnPropertyValue("PCHAPP_AppCode"));
		joomla.article.updateRFConfiguration("Source Code", "");
		joomla.article.saveArticle();
		
		enableThread(scriptName+".jmx", threadGroupSource);
		Log.info("Start Executing Jmeter Script");
		CallJmeterBuild(scriptName+".jmx");
		Log.info("JMX file Called");
		Assert.assertEquals("false", xp.Xml_Parser(scriptName+".xml"));
		Log.info("Assertion Validation Completed");	
		
		joomla.article.search("PCHAPP");
		joomla.article.updateRFConfiguration("Source Code", returnPropertyValue("PCHAPP_SourceCode"));
		joomla.article.updateRFConfiguration("Platform Code", "");
		joomla.article.updateRFConfiguration("Login Name", returnPropertyValue("PCHAPP_LoginName"));
		joomla.article.saveArticle();
		
		enableThread(scriptName+".jmx", threadGroupPlatform);
		Log.info("Start Executing Jmeter Script");
		CallJmeterBuild(scriptName+".jmx");
		Log.info("JMX file Called");
		Assert.assertEquals("false", xp.Xml_Parser(scriptName+".xml"));
		Log.info("Assertion Validation Completed");	
		
	}

	@AfterClass
	public void tearDown() throws IOException, Exception {
		joomla.article.search("PCHAPP");
		joomla.article.updateRFConfiguration("Appcode",returnPropertyValue("PCHAPP_AppCode"));
		joomla.article.updateRFConfiguration("Source Code", returnPropertyValue("PCHAPP_SourceCode"));
		joomla.article.updateRFConfiguration("Platform Code", returnPropertyValue("PCHAPP_PlatformCode"));
		joomla.article.updateRFConfiguration("Login Name", returnPropertyValue("PCHAPP_LoginName"));
		joomla.article.saveArticle();
		closeallwindows();
		endTestCase("End");
	}
}