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
import com.pch.utilities.Action_Wrapper;
import com.pch.utilities.GroupName;
import com.pch.utilities.Jmeter_Xml_Parser;

public class B_29886_MT2_ArticleDrivenConfig_Level_API_Secret_Key_PCHAPP extends Action_Wrapper{

	Jmeter_Xml_Parser xp = new Jmeter_Xml_Parser();
	JoomlaAdministrator joomla = new JoomlaAdministrator();
	String enable[] = {"Missing Level API Secret key - PchApp"};
	
	@BeforeClass
	public void setup() throws Exception {
		startTestCase("B_29886_MT2_ArticleDrivenConfig_Level_API_Secret_Key_PchApp");
		createAccessTokenForAllApp("pchapp");
		enableThread("B_29886_MT2_ArticleDrivenConfig_Level_API_Secret_Key.jmx", enable);
		openBrowser("chrome");
		joomla.login.login();
		joomla.article.navigateToArticleManager();
		joomla.article.selectcategoryDropDown("AppConfig");
		joomla.article.search("pchapp");
		joomla.article.appConfig_LevelAPISecretKey("");
		joomla.article.saveArticle();

	}

	@Test(groups = { GroupName.RegFoundation,GroupName.Regression,"Sprint 15" })
	public void story_B_29886_MT2_ArticleDrivenConfig_Level_API_Secret_Key_PCHAPP() throws Exception {
		Log.info("Start Executing Jmeter Script");
		CallJmeterBuild("B_29886_MT2_ArticleDrivenConfig_Level_API_Secret_Key.jmx");
		Log.info("calling  jmeter file ");
		Assert.assertEquals("false", xp.Xml_Parser("B_29886_MT2_ArticleDrivenConfig_Level_API_Secret_Key_PchApp.xml"));
		Log.info("Validating assertion ");
	}

	@AfterClass
	public void tearDown() throws IOException, Exception {
		joomla.article.selectcategoryDropDown("AppConfig");
		joomla.article.search("pchapp");
		joomla.article.appConfig_LevelAPISecretKey(returnPropertyValue("PCHAPP_LevelAPISecretKey"));
		joomla.article.saveArticle();
		closeallwindows();
		endTestCase("End");
	}
}