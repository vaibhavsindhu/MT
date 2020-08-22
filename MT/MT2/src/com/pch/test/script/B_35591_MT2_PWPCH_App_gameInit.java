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
import com.pch.iwe.page.HomePage;
import com.pch.joomla.configuration.JoomlaAdministrator;
import com.pch.utilities.AccessToken;
import com.pch.utilities.Action_Wrapper;
import com.pch.utilities.AppData;
import com.pch.utilities.Database_Configuration;
import com.pch.utilities.GroupName;
import com.pch.utilities.Jmeter_Xml_Parser;

public class B_35591_MT2_PWPCH_App_gameInit extends Action_Wrapper {
	Jmeter_Xml_Parser xp = new Jmeter_Xml_Parser();
	JoomlaAdministrator joomla = new JoomlaAdministrator();
	RFTestPage RF = new RFTestPage();
	Database_Configuration DB = new Database_Configuration();
	HomePage iwhp = new HomePage();

	String thread[] = { "GameINIT - Pick 7" };
	String thread1[] = { "GameINIT - Pick 6" };
	String thread2[] = {"Precondition - AppLoad"};
	String accessToken,response,GMT,Encrypted_Email,Encrypted_GMT,Email;
	String appCategory=AppData.Category_ID.PchApp2_Category;
	String appArticle=AppData.PchApp2.PCHApp_iOS_14;


	String scriptName = "B_35591_MT2_PW&PCH_App_gameInit";

	@BeforeClass
	public void setup() throws Exception {

		startTestCase("B_35591_MT2_PWPCH_App_gameInit");

		Log.info("***Creating First user from RF  ***");
		String response = RF.createFullRegUserAPI();
		Email=RF.getResponseValueAPI(response, ResponseData.Email);
		Log.info("full User Email :"+Email);
		GMT=RF.getResponseValueAPI(response, ResponseData.GlobalMemberToken);
		Log.info("full User GMT  :"+GMT);
		Encrypted_Email=getEncryptedData("Access_Token_PCHAPP",Email);
		Encrypted_GMT=getEncryptedData("Access_Token_PCHAPP",GMT);

		writeInProprtyFile("Encrypted_Email", Encrypted_Email);
		writeInProprtyFile("Encrypted_GMT", Encrypted_GMT);
		copyAndRenameFile();

		
		openBrowser("joomla");
		joomla.login.login();
		joomla.article.navigateToArticleManager();

		// Configure Bonus Article and Bonus Reward
		openBrowser("joomla");
		joomla.login.login();
		joomla.article.navigateToArticleManager();
		// Configure Meta Action Bonus in PCH App article
		joomla.PA.filterArticleByNames(appCategory,appArticle );
		joomla.PA.editArticle(appArticle, "publish");
		//	joomla.PA.bonusActionArticle("Bonus Reward for FB Connect", "Facebook_Connect");
		joomla.alias.saveAndClose();


		// Configure Contest Key in Action Articles
		joomla.PA.filterArticleByNames(AppData.Category_ID.LottoGame, "Lotto Game1");
		joomla.PA.editArticle("Lotto Game1", "publish");
		joomla.PA.selectPrizeConfigurations("Lotto", "320");
		joomla.alias.saveAndClose();
		// Configure Action Articles
		joomla.PA.filterArticleByNames(AppData.Category_ID.ContentPath_Category, AppData.ContentPath.Lotto_ContentPath);
		joomla.PA.editArticle(AppData.ContentPath.Lotto_ContentPath, "publish");
		joomla.PA.selectActionArticles(AppData.GamesID.Lotto.LottoGame1);
		joomla.alias.saveAndClose();

		// Configure Content Path in APP Article
		joomla.PA.filterArticleByNames(appCategory, appArticle);
		joomla.PA.editArticle(appArticle, "publish");
		joomla.PA.featureFieldSelectArticle(AppData.ContentPath.Lotto_ContentPath);
		joomla.alias.saveAndClose();
		closeallwindows();

		
		enableThread(scriptName + ".jmx", thread2);
		Log.info("Executing GameLoad JmeterThread as precondition.");
		CallJmeterBuild(scriptName + ".jmx");
	}

	@Test (groups = { GroupName.Regression, GroupName.Game, GroupName.PCHApp })
	public void strory_B_35591_MT2_PWPCH_App_gameInit()throws Exception {

		// Scenarios: GameINIT - Pick 6
		enableThread(scriptName + ".jmx", thread1);
		Log.info("Executing GameINIT JmeterThread for PICK 6.");
		CallJmeterBuild(scriptName + ".jmx");

		Assert.assertEquals("false", xp.Xml_Parser(scriptName+".xml"));


		// Scenarios: GameINIT - Pick 7


		// Admin Configuration for pick 7
		openBrowser("joomla");
		joomla.login.login();
		joomla.article.navigateToArticleManager();
		// Configure Contest Key in Action Articles
		joomla.PA.filterArticleByNames(AppData.Category_ID.LottoGame, "Lotto Game1");
		joomla.PA.editArticle("Lotto Game1", "publish");
		joomla.PA.selectPrizeConfigurations("Lotto", "321");
		joomla.alias.saveAndClose();


		enableThread(scriptName + ".jmx", thread);
		Log.info("Executing GameINIT JmeterThread for PICK 7.");
		CallJmeterBuild(scriptName + ".jmx");

		Assert.assertEquals("false", xp.Xml_Parser(scriptName+".xml"));

	}

	@AfterClass
	public void tearDown() throws IOException, Exception {

		closeallwindows();
		endTestCase("End");
	}
}