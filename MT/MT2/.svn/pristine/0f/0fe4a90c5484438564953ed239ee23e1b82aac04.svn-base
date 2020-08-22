package com.pch.test.script;

import static com.pch.utilities.Log.Log;
import static com.pch.utilities.Log.startTestCase;
import static com.pch.utilities.Log.endTestCase;

import java.io.IOException;
import java.net.MalformedURLException;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.pch.configuration.testpages.RFTestPage;
import com.pch.configuration.testpages.RFTestPage.ResponseData;
import com.pch.joomla.configuration.JoomlaAdministrator;
import com.pch.utilities.Action_Wrapper;
import com.pch.utilities.GroupName;
import com.pch.utilities.Jmeter_Xml_Parser;

public class B_29886_MT2_ArticleDrivenConfig_Registeration_foundation_parameters_App_code extends Action_Wrapper {
	
	RFTestPage RF = new RFTestPage();
	JoomlaAdministrator joomla = new JoomlaAdministrator();
	Jmeter_Xml_Parser xp = new Jmeter_Xml_Parser();
	String 	enableThreadListAppCode[] = {"Missing AppCode"};
	String enableThreadListSourceCode[] = {"Missing SourceCode"};
	String enableThreadListPlatformCode[] = {"Missing PlatformCode"};
	String enableThreadListLoginName[] = {"Missing LoginName"};
	String response;
	
	@BeforeClass
	public void setup() throws Exception {
		startTestCase("B-29886_MT2_ArticleDrivenConfig_Registeration foundation parameters_App code");
		createAccessTokenForAllApp("playnwin");

		response=RF.createFullRegUserAPI();
		writeInProprtyFile("Encrypted_Email", getEncryptedData("Access_Token_PLAYANDWIN",RF.getResponseValueAPI(response, ResponseData.Email)));
		copyAndRenameFile();
		
		enableThread("B_29886_MT2_ArticleDrivenConfig_Registeration_foundation_parameters_App_code.jmx", enableThreadListAppCode);

		openBrowser("chrome");
		joomla.login.login();
		joomla.article.navigateToArticleManager();
		joomla.article.search("playandwin");
		joomla.article.updateRFConfiguration("Appcode", "");
		joomla.article.updateRFConfiguration("Source Code", returnPropertyValue("PlayAndWin_SourceCode"));
		joomla.article.updateRFConfiguration("Platform Code", returnPropertyValue("PlayAndWin_PlatformCode"));
		joomla.article.updateRFConfiguration("Login Name", returnPropertyValue("PlayAndWin_LoginName"));
		joomla.article.saveArticle();
		
	}

	@Test(groups = { GroupName.RegFoundation,GroupName.Regression,"Sprint 15" })
	public void story_B_29886_MT2_ArticleDrivenConfig_Registeration_foundation_parameters_App_code() throws Exception {
		Log.info("Start Executing Jmeter Script");
		CallJmeterBuild("B_29886_MT2_ArticleDrivenConfig_Registeration_foundation_parameters_App_code.jmx");
		Log.info("calling  jmeter file ");
		Assert.assertEquals("false", xp.Xml_Parser("B_29886_MT2_ArticleDrivenConfig_Registeration_foundation_parameters_App_code.xml"));
		Log.info("Validating assertion ");
		

		enableThread("B_29886_MT2_ArticleDrivenConfig_Registeration_foundation_parameters_App_code.jmx", enableThreadListSourceCode);
		joomla.article.search("playandwin");
		joomla.article.updateRFConfiguration("Appcode", returnPropertyValue("PlayAndWin_AppCode"));
		joomla.article.updateRFConfiguration("Source Code", "");
		joomla.article.updateRFConfiguration("Platform Code", returnPropertyValue("PlayAndWin_PlatformCode"));
		joomla.article.updateRFConfiguration("Login Name", returnPropertyValue("PlayAndWin_LoginName"));
		joomla.article.saveArticle();
		
		Log.info("Start Executing Jmeter Script");
		CallJmeterBuild("B_29886_validating_Registeration_foundation_parameters_App_code_Source_code_Platform_code_PlayAndWIN.jmx");
		Log.info("calling  jmeter file ");
		Assert.assertEquals("false", xp.Xml_Parser("B_29886_MT2_ArticleDrivenConfig_Registeration_foundation_parameters_App_code.xml"));
		Log.info("Validating assertion ");
		
		

		enableThread("B_29886_MT2_ArticleDrivenConfig_Registeration_foundation_parameters_App_code.jmx", enableThreadListPlatformCode);
		joomla.article.search("playandwin");
		joomla.article.updateRFConfiguration("Appcode", returnPropertyValue("PlayAndWin_AppCode"));
		joomla.article.updateRFConfiguration("Source Code", returnPropertyValue("PlayAndWin_SourceCode"));
		joomla.article.updateRFConfiguration("Platform Code", "");
		joomla.article.updateRFConfiguration("Login Name", returnPropertyValue("PlayAndWin_LoginName"));
		joomla.article.saveArticle();
		
		Log.info("Start Executing Jmeter Script");
		CallJmeterBuild("B_29886_MT2_ArticleDrivenConfig_Registeration_foundation_parameters_App_code.jmx");
		Log.info("calling  jmeter file ");
		Assert.assertEquals("false", xp.Xml_Parser("B_29886_MT2_ArticleDrivenConfig_Registeration_foundation_parameters_App_code.xml"));
		Log.info("Validating assertion ");
		
		
		enableThread("B_29886_MT2_ArticleDrivenConfig_Registeration_foundation_parameters_App_code.jmx", enableThreadListLoginName);
		joomla.article.search("playandwin");
		joomla.article.updateRFConfiguration("Appcode", returnPropertyValue("PlayAndWin_AppCode"));
		joomla.article.updateRFConfiguration("Source Code", returnPropertyValue("PlayAndWin_SourceCode"));
		joomla.article.updateRFConfiguration("Platform Code", returnPropertyValue("PlayAndWin_PlatformCode"));
		joomla.article.updateRFConfiguration("Login Name", "");
		joomla.article.saveArticle();
		closeallwindows();

		Log.info("Start Executing Jmeter Script");
		CallJmeterBuild("B_29886_MT2_ArticleDrivenConfig_Registeration_foundation_parameters_App_code.jmx");
		Log.info("calling  jmeter file ");
		Assert.assertEquals("false", xp.Xml_Parser("B_29886_MT2_ArticleDrivenConfig_Registeration_foundation_parameters_App_code.xml"));
		Log.info("Validating assertion ");
		
	}

	@AfterClass
	public void tearDown() throws MalformedURLException, IOException, Exception {
		openBrowser("chrome");
		joomla.login.login();
		joomla.article.navigateToArticleManager();
		joomla.article.search("playandwin");
		joomla.article.updateRFConfiguration("Appcode", returnPropertyValue("PlayAndWin_AppCode"));
		joomla.article.updateRFConfiguration("Source Code", returnPropertyValue("PlayAndWin_SourceCode"));
		joomla.article.updateRFConfiguration("Platform Code", returnPropertyValue("PlayAndWin_PlatformCode"));
		joomla.article.updateRFConfiguration("Login Name", returnPropertyValue("PlayAndWin_LoginName"));
		joomla.article.saveArticle();

		closeallwindows();
		endTestCase("End");

	}
}
