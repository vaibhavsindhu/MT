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
import com.pch.iwe.page.DevicesPage;
import com.pch.iwe.page.HomePage;
import com.pch.joomla.configuration.JoomlaAdministrator;
import com.pch.utilities.Action_Wrapper;
import com.pch.utilities.Database_Configuration;
import com.pch.utilities.GroupName;
import com.pch.utilities.Jmeter_Xml_Parser;

public class B_35592_MT2_PCHApp_gameStart_IWGame extends Action_Wrapper {
	Jmeter_Xml_Parser xp = new Jmeter_Xml_Parser();
	JoomlaAdministrator joomla = new JoomlaAdministrator();
	RFTestPage RF = new RFTestPage();
	Database_Configuration DB = new Database_Configuration();
	HomePage iwhp = new HomePage();
	DevicesPage iwDp = new DevicesPage();

	String thread[] = { "GameStart - Valid" };
	String thread1[] = { "GameStart - Game Not Configured" };
	String thread2[] = { "GameStart - IWE Already Played" };
	String thread3[] = { "GameStart - Missing GameID in Request" };
	String thread4[] = { "GameStart - Wrong GameID in Request" };
	String thread5[] = {"Precondition - AppLoad"};


	String scriptName = "B_35592_MT2_PCHApp_gameStart_IWGame";
	String GMT;

	@BeforeClass
	public void setup() throws Exception {
		
		startTestCase("B_35592_MT2_PCHApp_gameStart_IWGame");
		openBrowser("chrome");
		RF.CreateFullRegUser();
		writeInProprtyFile("GMT", getEncryptedData("Access_Token_PCHAPP", RF.getResponseValue(ResponseData.GlobalMemberToken)));
		writeInProprtyFile("EMAIL", getEncryptedData("Access_Token_PCHAPP", RF.getResponseValue(ResponseData.Email)));

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
		
		
// Configure Prize Articles
		joomla.PA.filterArticleByNames("96", "PCHAPP-IWGame");
		joomla.PA.editArticle("PCHAPP-IWGame", "publish");
		joomla.PA.selectPrizeConfigurations("IW", "423");
		joomla.alias.saveAndClose();
		
// Configure Action Articles
		joomla.PA.filterArticleByNames("", "PCHAPP IW Game Content Path");
		joomla.PA.editArticle("PCHAPP IW Game Content Path", "publish");
		joomla.PA.selectActionArticles("421");
		joomla.alias.saveAndClose();
		
// Configure Content Path in APP Article
		joomla.PA.filterArticleByNames("", "PCH APP old QA");
		joomla.PA.editArticle("PCH APP old QA", "publish");
		joomla.PA.featureFieldSelectArticle("PCHAPP IW Game Content Path");
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
	public void strory_B_35592_MT2_PCHApp_gameStart_IWGame()throws Exception {

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
		
// Scenarios: GameStart - GameStart - Valid
		enableThread(scriptName + ".jmx", thread);
		Log.info("Executing GameStart Thread as Valid Scenario.");
		CallJmeterBuild(scriptName + ".jmx");
		
		Assert.assertEquals("false", xp.Xml_Parser(scriptName+".xml"));
		
// 	Scenario: GameStart - Game Not Configured
		
		// Remove Action article from Content Path Article
		openBrowser("joomla");
		joomla.login.login();
		joomla.article.navigateToArticleManager();
		joomla.PA.filterArticleByNames("", "PCHAPP IW Game Content Path");
		joomla.PA.editArticle("PCHAPP IW Game Content Path", "publish");
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
		joomla.PA.filterArticleByNames("", "PCHAPP IW Game Content Path");
		joomla.PA.editArticle("PCHAPP IW Game Content Path", "publish");
		joomla.PA.selectActionArticles("421");
		joomla.alias.saveAndClose();
		DB.flush("PchApp", "DELETE FROM `phpctrl_app_content_tree`");
		
		//Populating Content Tree
		enableThread(scriptName + ".jmx", thread5);
		Log.info("Executing GameLoad JmeterThread as precondition.");
		CallJmeterBuild(scriptName + ".jmx");

// Scenarios: GameStart - IWE Already Played		

		iwhp.IWE_Login();
		iwhp.naviagateToIWEDeviceList("4321");
		iwDp.setPlayRestrictions("1", "1", "");
		iwhp.setComments("For testing");
		iwhp.ClickButtonSave();
		closeallwindows();
		
		enableThread(scriptName + ".jmx", thread2);
		Log.info("Executing GameStart Thread for IW already Played.");
		CallJmeterBuild(scriptName + ".jmx");
		
		Assert.assertEquals("false", xp.Xml_Parser(scriptName+".xml"));
	}

	@AfterClass
	public void tearDown() throws IOException, Exception {
		
// Revert Joomla Configuration
		openBrowser("joomla");
		joomla.login.login();
		joomla.article.navigateToArticleManager();
		
		joomla.PA.filterArticleByNames("", "PCHAPP IW Game Content Path");
		joomla.PA.editArticle("PCHAPP IW Game Content Path", "publish");
		joomla.PA.selectActionArticles("421");
		joomla.alias.saveAndClose();
		
// Revert IW Configuration
		openBrowser("chrome");
		iwhp.IWE_Login();
		iwhp.naviagateToIWEDeviceList("4321");
		iwDp.setPlayRestrictions("1000", "1000", "");
		iwhp.setComments("For testing");
		iwhp.ClickButtonSave();
		closeallwindows();
		endTestCase("End");
	}
}