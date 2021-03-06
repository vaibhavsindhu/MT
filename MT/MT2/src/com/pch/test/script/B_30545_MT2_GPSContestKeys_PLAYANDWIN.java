package com.pch.test.script;

import static com.pch.utilities.Log.Log;
import static com.pch.utilities.Log.endTestCase;
import static com.pch.utilities.Log.startTestCase;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.pch.joomla.configuration.JoomlaAdministrator;
import com.pch.utilities.Action_Wrapper;
import com.pch.utilities.GroupName;

public class B_30545_MT2_GPSContestKeys_PLAYANDWIN extends Action_Wrapper {

	JoomlaAdministrator joomla = new JoomlaAdministrator();

	@BeforeClass
	public void setup() throws Exception {
		new JoomlaAdministrator();

		startTestCase("[MT2] GPS contest key ");
		createAccessTokenForAllApp("playnwin");

		openBrowser("chrome");

	}

	@Test(groups = { GroupName.Regression })
	public void story_B_30545_MT2_GPSContestKeys_PLAYANDWIN() throws Exception {
		Action_Wrapper act = new Action_Wrapper();

		Log.info("Start Executing Jmeter Script");
		String App = "PLAY&WIN";
		String AppCode="UNIAPP";

		act.Generate_Futuredate(0);
		act.Generate_Futuredate(30);

		String url = returnPropertyValue("GPSPageURL");
		Log.info("url is " + url);

		String GPS = joomla.article.GetGPS_PageSource(AppCode, url);

		Log.info(GPS);

		Log.info("login to joomla admin  and navigate to article manager");
		joomla.login.login();
		joomla.article.navigateToArticleManager();
		Log.info("Selecing standalonw sweep games ->Site PLAY&WIN APP");
		joomla.article.search("Standalone Sweeps game");
		joomla.article.selectSiteDropDown(App);
		Log.info("Asserting  contest key and GPS api - contest keys ");
		joomla.article.AssertingJoomlaAndGPSContestList(GPS);

	}

	@AfterClass
	public void tearDown() {
		Log.info("quitting driver and all its instance");
		closeallwindows();
		endTestCase("End");

	}

}
