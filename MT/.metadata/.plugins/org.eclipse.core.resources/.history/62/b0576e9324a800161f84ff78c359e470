package com.pch.test.script;

import static com.pch.utilities.Log.Log;
import static com.pch.utilities.Log.endTestCase;
import static com.pch.utilities.Log.startTestCase;

import java.io.IOException;
import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.pch.configuration.testpages.RFTestPage;
import com.pch.configuration.testpages.RFTestPage.ResponseData;
import com.pch.iwe.page.HomePage;
import com.pch.joomla.configuration.JoomlaAdministrator;
import com.pch.utilities.AccessToken;
import com.pch.utilities.Action_Wrapper;
import com.pch.utilities.Database_Configuration;
import com.pch.utilities.GroupName;
import com.pch.utilities.Jmeter_Xml_Parser;

public class B_35592_MT2_PCHApp_gameStart_SweepStakes extends Action_Wrapper {
	Jmeter_Xml_Parser xp = new Jmeter_Xml_Parser();
	ArrayList<String> bMHKeys = new ArrayList<String>();
	JoomlaAdministrator joomla = new JoomlaAdministrator();
	RFTestPage RF = new RFTestPage();
	Database_Configuration DB = new Database_Configuration();
	HomePage iwhp = new HomePage();

	String thread[] = { "GameStart - Valid" };
	String thread1[] = { "GameStart - Game Not Configured" };
	String thread2[] = { "GameStart - TC&V Key Not Configured" };
	String thread3[] = { "GameStart - Missing GameID in Request" };
	String thread4[] = { "GameStart - Wrong GameID in Request" };
	String thread5[] = {"Precondition - AppLoad"};
	String thread6[] = { "GameStart - Negative Quantity in Request" };
	String thread7[] = { "GameStart - Submit Quantity > than available" };


	String scriptName = "B_35592_MT2_PCHApp_gameStart_SweepStakes";
	String GMT;

	@BeforeClass
	public void setup() throws Exception {
		
		startTestCase("B_35592_MT2_PCHApp_gameStart_SweepStakes");
		createAccessTokenForAllApp("pchapp");
		
		writeInProprtyFile("Access_Token_PCHAPP",AccessToken.getAccessToken("PCHAPP","new"));
		copyAndRenameFile();

		openBrowser("chrome");
		RF.CreateFullRegUser();
		writeInProprtyFile("GMT", getEncryptedData("Access_Token_PCHAPP", RF.getResponseValue(ResponseData.GlobalMemberToken)));
		writeInProprtyFile("EMAIL", getEncryptedData("Access_Token_PCHAPP", RF.getResponseValue(ResponseData.Email)));

		RF.CreateFullRegUser();
		writeInProprtyFile("GMT_1", getEncryptedData("Access_Token_PCHAPP", RF.getResponseValue(ResponseData.GlobalMemberToken)));
		writeInProprtyFile("EMAIL_1", getEncryptedData("Access_Token_PCHAPP", RF.getResponseValue(ResponseData.Email)));
		
		copyAndRenameFile();

		joomla.login.login();
		joomla.article.navigateToArticleManager();

		joomla.article.selectcategoryDropDown("- Pch App");
		joomla.article.search("Pch App Test 1", "search");
		joomla.article.articleStatus("unpublish");
		joomla.article.search("PCH App", "search");
		joomla.article.articleStatus("unpublish");
		joomla.article.search("PCH APP old QA", "search");
		joomla.article.articleStatus("publish");
		joomla.article.search("PCH APP old QA", "ALIAS");
		joomla.alias.finishPublishingDate("");
		joomla.alias.saveAndClose();
		
// Configure BMH Submission Method in Admin App Config Article
		joomla.PA.filterArticleByNames("68", "PCHAPP");
		joomla.PA.editArticle("PCHAPP", "publish");
		joomla.article.appConfig_BMHSubmissionMethod("sync");
		joomla.article.saveArticle();
		
		
// Configure/Store Contest Key values from Action Articles
		joomla.PA.filterArticleByNames("76", "Pch Sweeps");
		joomla.PA.editArticle("Pch Sweeps", "publish");
		joomla.article.ConfigureTCvalues("add", "202YU1012P", "20060702");
		bMHKeys=joomla.article.BMH_Configuration(0,"get value");
		writeInProprtyFile("TC_0", bMHKeys.get(0));
		writeInProprtyFile("V_0", bMHKeys.get(1));
		copyAndRenameFile();
		joomla.alias.saveAndClose();
		
// Configure Action Articles
		joomla.PA.filterArticleByNames("", "PCHAPP Sweeps Content Path");
		joomla.PA.editArticle("PCHAPP Sweeps Content Path", "publish");
		joomla.PA.selectActionArticles("333");
		joomla.alias.saveAndClose();
		
// Configure Content Path in APP Article
		joomla.PA.filterArticleByNames("", "PCH APP old QA");
		joomla.PA.editArticle("PCH APP old QA", "publish");
		joomla.PA.featureFieldSelectArticle("PCHAPP Sweeps Content Path");
		joomla.alias.saveAndClose();
		closeallwindows();
		
// Clear Content Tree Table from DataBase
		DB.flush("PchApp", "DELETE FROM `phpctrl_app_content_tree`");
		
// Precondition - Game Load After ContentTree table flush
		enableThread(scriptName + ".jmx", thread5);
		Log.info("Executing GameLoad JmeterThread as precondition.");
		CallJmeterBuild(scriptName + ".jmx");
	}

	@Test (groups = { GroupName.Regression, GroupName.Game })
	public void strory_B_35592_MT2_PCHApp_gameStart_SweepStakes()throws Exception {

		
// Scenarios: GameStart - GameStart - Valid
		enableThread(scriptName + ".jmx", thread);
		Log.info("Executing GameStart Thread as Valid Scenario.");
		CallJmeterBuild(scriptName + ".jmx");
				
		Assert.assertEquals("false", xp.Xml_Parser(scriptName+".xml"));

		
// Scenarios: GameStart - Negative Quantity in Request	
		enableThread(scriptName + ".jmx", thread6);
		Log.info("Executing GameStart Thread as for Negative Quantity.");
		CallJmeterBuild(scriptName + ".jmx");
				
		Assert.assertEquals("false", xp.Xml_Parser(scriptName+".xml"));		
		
// Scenarios: GameStart - Missing GameID in Request
		enableThread(scriptName + ".jmx", thread3);
		Log.info("Executing GameLoad JmeterThread as precondition.");
		CallJmeterBuild(scriptName + ".jmx");
	
		Assert.assertEquals("false", xp.Xml_Parser(scriptName+".xml"));
		
// Scenarios: GameStart - Wrong GameID in Request	
		enableThread(scriptName + ".jmx", thread4);
		Log.info("Executing GameLoad JmeterThread as precondition.");
		CallJmeterBuild(scriptName + ".jmx");
		
		Assert.assertEquals("false", xp.Xml_Parser(scriptName+".xml"));

		
// Scenarios: GameStart - Submit Quantity more than available	
		enableThread(scriptName + ".jmx", thread7);
		Log.info("Executing GameStart Thread as Quantity Exceed maximum available.");
		CallJmeterBuild(scriptName + ".jmx");
				
		Assert.assertEquals("false", xp.Xml_Parser(scriptName+".xml"));
		
// 	Scenario: GameStart - Game Not Configured
		
		// Remove Action article from Content Path Article
		openBrowser("joomla");
		joomla.login.login();
		joomla.article.navigateToArticleManager();
		joomla.PA.filterArticleByNames("", "PCHAPP Sweeps Content Path");
		joomla.PA.editArticle("PCHAPP Sweeps Content Path", "publish");
		joomla.PA.selectActionArticles("");
		joomla.alias.saveAndClose();
		closeallwindows();
		
		// Clear Content Tree Table from DataBase
		DB.flush("PchApp", "DELETE FROM `phpctrl_app_content_tree`");
		
		enableThread(scriptName + ".jmx", thread5);
		Log.info("Executing GameLoad JmeterThread to Populate Content tree.");
		
		enableThread(scriptName + ".jmx", thread1);
		Log.info("Executing Game not Configured Thread");
		CallJmeterBuild(scriptName + ".jmx");
		
		Assert.assertEquals("false", xp.Xml_Parser(scriptName+".xml"));
		
		// Revert the Admin Config
		openBrowser("joomla");
		joomla.login.login();
		joomla.article.navigateToArticleManager();
		joomla.PA.filterArticleByNames("", "PCHAPP Sweeps Content Path");
		joomla.PA.editArticle("PCHAPP Sweeps Content Path", "publish");
		joomla.PA.selectActionArticles("333");
		joomla.alias.saveAndClose();
		DB.flush("PchApp", "DELETE FROM `phpctrl_app_content_tree`");
		
		//Populating Content Tree
		enableThread(scriptName + ".jmx", thread5);
		Log.info("Executing GameLoad JmeterThread as precondition.");
		CallJmeterBuild(scriptName + ".jmx");

// Scenarios: GameStart - TC & V Key Not Setup
		
		// Configure Contest Key in Action Articles
		joomla.PA.filterArticleByNames("76", "Pch Sweeps");
		joomla.PA.editArticle("Pch Sweeps", "publish");
		//Removing TC & V values
		joomla.article.ConfigureTCvalues("remove", "", "");
		joomla.alias.saveAndClose();
		closeallwindows();
		
		enableThread(scriptName + ".jmx", thread2);
		Log.info("Executing GameStart Thread for TC&V Key are Not Configured.");
		CallJmeterBuild(scriptName + ".jmx");
		
		Assert.assertEquals("false", xp.Xml_Parser(scriptName+".xml"));
	}

	@AfterClass
	public void tearDown() throws IOException, Exception {
		
// Revert Joomla Configuration
		openBrowser("joomla");
		joomla.login.login();
		joomla.article.navigateToArticleManager();

		joomla.PA.filterArticleByNames("76", "Pch Sweeps");
		joomla.PA.editArticle("Pch Sweeps", "publish");
		//Revert TC & V values
		joomla.article.ConfigureTCvalues("add", "202YU1012P", "20060702");
		joomla.alias.saveAndClose();
		
		closeallwindows();
		endTestCase("End");
	}
}