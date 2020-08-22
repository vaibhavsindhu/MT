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
import com.pch.configuration.testpages.RFTestPage.ResponseData;
import com.pch.joomla.configuration.JoomlaAdministrator;
import com.pch.utilities.Action_Wrapper;
import com.pch.utilities.AppData;
import com.pch.utilities.Database_Configuration;
import com.pch.utilities.GroupName;
import com.pch.utilities.Jmeter_Xml_Parser;

public class B_35593_MT2_PCHAPP_GameComplete extends Action_Wrapper{

	Jmeter_Xml_Parser xp = new Jmeter_Xml_Parser();
	JoomlaAdministrator joomla = new JoomlaAdministrator();
	Database_Configuration DB = new Database_Configuration();
	
	RFTestPage RF = new RFTestPage();
	String response,GMT;
	String scriptName = this.getClass().getSimpleName();
	
	@BeforeClass
	public void setup() throws Exception {
		startTestCase(scriptName);
		createAccessTokenForAllApp("pchapp");
		
		response = RF.createFullRegUserAPI();
		writeInProprtyFile("Encrypted_Email", getEncryptedData("Access_Token_PCHAPP",RF.getResponseValueAPI(response, ResponseData.Email)));
		writeInProprtyFile("Encrypted_GMT", getEncryptedData("Access_Token_PCHAPP",RF.getResponseValueAPI(response, ResponseData.GlobalMemberToken)));
		GMT=RF.getResponseValueAPI(response, ResponseData.GlobalMemberToken);
		copyAndRenameFile();
		
		openBrowser("chrome");
		joomla.login.login();
		
		joomla.article.navigateToArticleManager();
		joomla.article.search(AppData.PchApp2.PCHApp_iOS_14);
		waitForPageToLoad(MTDriver);
		joomla.PA.FeatureFieldremove();
		joomla.PA.TileFieldremove();
		joomla.PA.featureFieldSelectArticle(AppData.ContentPath.Continuous_ContentPath);
		joomla.PA.tileFieldSelectArticle("Tile Lotto", 0);
		joomla.article.saveArticle();

		joomla.article.selectcategoryDropDown("- Select Category -");

		joomla.article.search(AppData.ContentPath.Continuous_ContentPath);
		joomla.ML.removeActionArticle();
		joomla.article.selectActionArticleDropdown(AppData.GamesID.Lotto.LottoGame1, 0);
		joomla.ML.addActionArticleCCk();
		joomla.article.selectActionArticleDropdown(AppData.GamesID.SweepStake.StandaloneSweepstakeGame1, 1);
		joomla.ML.addActionArticleCCk();
		joomla.article.selectActionArticleDropdown(AppData.GamesID.SBG.ScoreSubmit1, 2);
		joomla.ML.addActionArticleCCk();
		joomla.article.selectActionArticleDropdown(AppData.GamesID.PCHGames.MMPlusBonus, 3);
		joomla.ML.addActionArticleCCk();
		joomla.article.selectActionArticleDropdown(AppData.GamesID.InstantWin.PCHAPPIWGame2, 4);
		joomla.article.saveArticle();
			
	}

	@Test(groups = { GroupName.Game, GroupName.Regression })
	public void story_B_35593_MT2_PCHAPP_GameComplete() throws Exception {
	
		Log.info("Start Executing Jmeter Script");
		CallJmeterBuild(scriptName+".jmx");
		Log.info("JMX file Called");
		Assert.assertEquals("false", xp.Xml_Parser(scriptName+".xml"),"Validating Jmeter XML file Result");
		Log.info("Assertion Validation Completed");
	
		Assert.assertTrue(DB.phpctrl_app_content_status(GMT).contains("\"328\":{\"status\":3,"),"Score Submit Status must be 3");

		Assert.assertTrue(DB.phpctrl_app_content_status(GMT).contains("\"359\":{\"status\":3,"),"1MM Gold Seal Status must be 3");

		Assert.assertTrue(DB.phpctrl_app_content_status(GMT).contains("\"326\":{\"status\":3,"),"Lotto Game1 Status must be 3");

		Assert.assertTrue(DB.phpctrl_app_content_status(GMT).contains("\"425\":{\"status\":3,"),"PCHAPP-IWGame2 Status must be 3");

		Assert.assertTrue(DB.phpctrl_app_content_status(GMT).contains("\"279\":{\"status\":3,"),"Standalone Sweepstake Game 1 Status must be 3");

	}

	@AfterClass
	public void tearDown() throws IOException, Exception {
		closeallwindows();

		endTestCase("End");
	}
}