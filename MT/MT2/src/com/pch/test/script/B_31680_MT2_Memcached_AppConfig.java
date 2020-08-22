

//SPAL: This story is not applicable we enable clearCache=All called when we save the any appconfig article
/*package com.pch.test.script;

import static com.pch.utilities.Log.Log;
import static com.pch.utilities.Log.endTestCase;
import static com.pch.utilities.Log.startTestCase;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.pch.joomla.configuration.JoomlaAdministrator;
import com.pch.joomla.configuration.MidTierApiConfiguration;
import com.pch.joomla.configuration.MidTierApiConfiguration.ServiceURL;
import com.pch.utilities.Action_Wrapper;
import com.pch.utilities.GroupName;
import com.pch.utilities.Jmeter_Xml_Parser;

public class B_31680_MT2_Memcached_AppConfig extends Action_Wrapper {
	Jmeter_Xml_Parser xp = new Jmeter_Xml_Parser();
	String accessToken;
	JoomlaAdministrator joomla =new JoomlaAdministrator();
	MidTierApiConfiguration MTAPIConfig =new MidTierApiConfiguration();

	@BeforeClass
	public void setup() throws Exception {
		
		startTestCase("B_31680_MT2_Memcached_APIConfig");
		createAccessTokenForAllApp("playnwin");

	}

	@Test(groups = {GroupName.Memcached, "Sprint 15" })
	public void BacklogItem_B_31680_MT2_Memcached_AppConfig() throws Exception {
		
		Log.info("Update Service URLs to Correct path in case they are not updated");
		openBrowser("chrome");
		joomla.login.login();
		MTAPIConfig.navigateToAPIComponents();
		MTAPIConfig.servicesUrl("Valid URL", ServiceURL.Registration_Foundation);
		MTAPIConfig.saveAndClose();
		Action_Wrapper.clearMemCache("Access_Token_PLAYANDWIN");

		// Make changes to Joomla App Config Articles

		joomla.article.navigateToArticleManager();
		joomla.article.search("PLAYANDWIN");
		joomla.article.updateRFConfiguration("Appcode", "");
		joomla.article.updateRFConfiguration("Login Name", returnPropertyValue("PlayAndWin_LoginName"));
		joomla.article.saveArticle();
		closeallwindows();

		// Run JMeter Script		
		Log.info("Start Executing Jmeter Script");
		CallJmeterBuild("B_31680_MT2_Memcached_AppConfig.jmx");
		Assert.assertEquals("false", xp.Xml_Parser("B_31680_MT2_Memcached_AppConfig.xml"));
		Log.info("Validating assertion ");
		
	}

	@AfterClass
	public void tearDown() throws IOException, Exception {
		
		Log.info("Revert The Configuration");
		openBrowser("chrome");
		joomla.login.login();
		joomla.article.navigateToArticleManager();
		joomla.article.search("PLAYANDWIN");
		joomla.article.updateRFConfiguration("Appcode", "UNIFIED");
		joomla.article.saveArticle();
		
		closeallwindows();
		endTestCase("End");

	}
} */
