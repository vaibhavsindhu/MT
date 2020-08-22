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
import com.pch.joomla.configuration.ArticleManager;
import com.pch.joomla.configuration.JoomlaAdministrator;
import com.pch.utilities.AccessToken;
import com.pch.utilities.Action_Wrapper;
import com.pch.utilities.Database_Configuration;
import com.pch.utilities.GroupName;
import com.pch.utilities.Jmeter_Xml_Parser;

public class B_31681_MT2_PCHAPP_SweepsArticle extends Action_Wrapper {
	JoomlaAdministrator joomla = new JoomlaAdministrator();
	String accessToken;
	Database_Configuration DB = new Database_Configuration();
	RFTestPage RF = new RFTestPage();
	Jmeter_Xml_Parser xp = new Jmeter_Xml_Parser();
	ArticleManager articleMnr = new ArticleManager();

	String Email, response;
	String topColor = "topColor1#";
	String bottomColor = "";
	String buttonColor = "buttonColor236767";
	String buttonText = "value_buttonText";

	@BeforeClass
	public void setup() throws Exception {

		startTestCase("B_31681_MT2_PCHAPP_SweepsArticle");
		createAccessTokenForAllApp("pchapp");

		accessToken = AccessToken.getAccessToken("PCHAPP");
		writeInProprtyFile("Access_Token_PCHAPP", accessToken);

		writeInProprtyFile("topColor", topColor);
		writeInProprtyFile("bottomColor", bottomColor);
		writeInProprtyFile("buttonColor", buttonColor);
		writeInProprtyFile("buttonText", buttonText);

		
		response=RF.createFullRegUserAPI();
		writeInProprtyFile("Encrypted_Email", getEncryptedData("Access_Token_PCHAPP", RF.getResponseValueAPI(response,ResponseData.Email)));
		writeInProprtyFile("Encrypted_GMT",	getEncryptedData("Access_Token_PCHAPP",	RF.getResponseValueAPI(response, ResponseData.GlobalMemberToken)));
		copyAndRenameFile();
		
		// Login to Joomla Admin
		openBrowser("chrome");
		joomla.login.login();
		
		// Configure Sweeps Action Articles
		joomla.article.navigateToArticleManager();
		joomla.PA.filterArticleByNames("", "New PCH App Sweepstakes Game1");
		joomla.PA.editArticle("New PCH App Sweepstakes Game1", "publish");
		articleMnr.UpdateSweepStakeArticle(topColor, bottomColor, buttonColor,buttonText);
		
		// Configure Sweeps Content Path
//		joomla.article.navigateToArticleManager();
		joomla.PA.filterArticleByNames("73", "PCHAPP Sweeps Content Path");
		joomla.PA.editArticle("PCHAPP Sweeps Content Path", "publish");
		joomla.PA.selectActionArticles("426");
		joomla.alias.saveAndClose();	
		
				
		// Configure Content Path in APP Article
		joomla.PA.filterArticleByNames("", "PCH APP old QA");
		joomla.PA.editArticle("PCH APP old QA", "publish");
		joomla.PA.featureFieldSelectArticle("PCHAPP Sweeps Content Path");
		joomla.alias.saveAndClose();
		closeallwindows();
				
		// Clear Content Tree Table from DataBase
		DB.flush("PchApp", "DELETE FROM `phpctrl_app_content_tree`");
		
		

	}

	@Test(groups = { GroupName.Regression })
	public void story_B_31681_MT2_PCHAPP_SweepsArticle() throws Exception {
		Log.info(" setting up joomla configuration ");
//		openBrowser("chrome");
//		joomla.login.login();
//
//		joomla.article.navigateToArticleManager();
//		joomla.article.search("New PCH App Sweepstakes Game1");
		

		Log.info("Start Executing Jmeter Script");

		CallJmeterBuild("B_31681_MT2_PCHAPP_SweepsArticle.jmx");
		Log.info("Calling  Jmeter file ");

		Assert.assertEquals("false",
				xp.Xml_Parser("B_31681_MT2_PCHAPP_SweepsArticle.xml"));
		Log.info("Validating assertion ");

	}

	@AfterClass
	public void tearDown() throws IOException, Exception {

		closeallwindows();
		endTestCase("End");
	}
}