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
import com.pch.joomla.configuration.MidTierApiConfiguration.ServiceURL;
import com.pch.utilities.AccessToken;
import com.pch.utilities.Action_Wrapper;
import com.pch.utilities.AppData;
import com.pch.utilities.Database_Configuration;
import com.pch.utilities.GroupName;
import com.pch.utilities.Jmeter_Xml_Parser;

public class B_35591_MT2_PWPCH_App_gameInit_ErrorConditions extends Action_Wrapper {
	Jmeter_Xml_Parser xp = new Jmeter_Xml_Parser();
	JoomlaAdministrator joomla = new JoomlaAdministrator();
	RFTestPage RF = new RFTestPage();
	Database_Configuration DB = new Database_Configuration();
	HomePage iwhp = new HomePage();

	String thread[] = { "GameINIT - Email/GMT missing" };
	String thread1[] = { "GameINIT - GAMEID Missing" };
	String thread2[] = { "GameINIT - Invalid GAMEID" };
	String thread3[] = { "GameINIT - No Contest Engine" };
	String thread4[] = { "GameINIT - Expired Lotto Contest Engine" };
	String thread6[] = { "GameINIT - Lotto Service Down_1" };
	String thread7[] = { "GameINIT - Lotto Service Down_2" };
	String thread5[] = {"Precondition - AppLoad"};


	String scriptName = "B_35591_MT2_PW&PCH_App_gameInit_ErrorConditions";
	String accessToken,response,GMT,Encrypted_Email,Encrypted_GMT,Email;

	String appCategory=AppData.Category_ID.PchApp2_Category;
	String appArticle=AppData.PchApp2.PCHApp_iOS_14;

	@BeforeClass
	public void setup() throws Exception {

		startTestCase("B_35591_MT2_PWPCH_App_gameInit_ErrorConditions");
		createAccessTokenForAllApp("pchapp");

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


		// Precondition - Game Load After ContentTree table flush
		enableThread(scriptName + ".jmx", thread5);
		Log.info("Executing GameLoad JmeterThread as precondition.");
		CallJmeterBuild(scriptName + ".jmx");
	}

	@Test (groups = { GroupName.Regression, GroupName.Game })
	public void strory_B_35591_MT2_PWPCH_App_gameInit_ErrorConditions()throws Exception {

		// Scenarios: GameINIT - Email/GMT missing
		enableThread(scriptName + ".jmx", thread);
		Log.info("Executing GameLoad JmeterThread as precondition.");
		CallJmeterBuild(scriptName + ".jmx");

		Assert.assertEquals("false", xp.Xml_Parser(scriptName+".xml"));

		// Scenarios: GameINIT - GAMEID Missing	
		enableThread(scriptName + ".jmx", thread1);
		Log.info("Executing GameLoad JmeterThread as precondition.");
		CallJmeterBuild(scriptName + ".jmx");

		Assert.assertEquals("false", xp.Xml_Parser(scriptName+".xml"));

		// Scenarios: GameINIT - Invalid GAMEID
		enableThread(scriptName + ".jmx", thread2);
		Log.info("Executing GameStart Thread as Valid Scenario.");
		CallJmeterBuild(scriptName + ".jmx");

		Assert.assertEquals("false", xp.Xml_Parser(scriptName+".xml"));

		// 	Scenario: GameINIT - No Contest Engine 

		// Remove Contest from the list.
		openBrowser("joomla");
		joomla.login.login();
		joomla.article.navigateToArticleManager();
		joomla.PA.filterArticleByNames(AppData.Category_ID.LottoGame, "Lotto Game1");
		joomla.PA.editArticle("Lotto Game1", "publish");
		joomla.PA.selectPrizeConfigurations("Lotto", "");
		joomla.alias.saveAndClose();
		closeallwindows();

		enableThread(scriptName + ".jmx", thread3);
		Log.info("Executing Contest not Configured Thread");
		CallJmeterBuild(scriptName + ".jmx");

		Assert.assertEquals("false", xp.Xml_Parser(scriptName+".xml"));

		//	 	Scenario: GameINIT - Expired Lotto Contest Engine 

		// Select Expired Contest from list
		openBrowser("joomla");
		joomla.login.login();
		joomla.article.navigateToArticleManager();
		joomla.PA.filterArticleByNames(AppData.Category_ID.LottoGame,"Lotto Game1");
		joomla.PA.editArticle("Lotto Game1", "publish");
		joomla.PA.selectPrizeConfigurations("Lotto", "343");
		joomla.alias.saveAndClose();

		enableThread(scriptName + ".jmx", thread4);
		Log.info("Executing Expired Contest Thread");
		CallJmeterBuild(scriptName + ".jmx");

		Assert.assertEquals("false", xp.Xml_Parser(scriptName+".xml"));


		// Revert the Admin Config
		joomla.article.navigateToArticleManager();
		joomla.PA.filterArticleByNames(AppData.Category_ID.LottoGame,"Lotto Game1");
		joomla.PA.editArticle("Lotto Game1", "publish");
		joomla.PA.selectPrizeConfigurations("Lotto", "320");
		joomla.alias.saveAndClose();


		// Scenarios: GameINIT - Lotto Service Down_1	

		// Update Lotto Get Game URL
		joomla.MTAPIConfig.navigateToAPIComponents();
		joomla.MTAPIConfig.servicesUrl("Alter URL", ServiceURL.Lotto_Get_Games_URL);
		joomla.MTAPIConfig.saveAndClose();
		GETclearMemCache("Access_Token_PCHAPP");

		enableThread(scriptName + ".jmx", thread6);
		Log.info("Executing GameINIT Thread for Wrong get Lotto Game url.");
		CallJmeterBuild(scriptName + ".jmx");

		Assert.assertEquals("false", xp.Xml_Parser(scriptName+".xml"));

		// Scenarios: GameINIT - Lotto Service Down_2	 Alter URL1

		// Update Lotto Get Game URL
		joomla.MTAPIConfig.navigateToAPIComponents();
		joomla.MTAPIConfig.servicesUrl("Alter URL1", ServiceURL.Lotto_Get_Games_URL);
		joomla.MTAPIConfig.saveAndClose();
		GETclearMemCache("Access_Token_PCHAPP");

		enableThread(scriptName + ".jmx", thread7);
		Log.info("Executing GameINIT Thread for Wrong Get Lotto Game url.");
		CallJmeterBuild(scriptName + ".jmx");

		Assert.assertEquals("false", xp.Xml_Parser(scriptName+".xml"));	
	}

	@AfterClass
	public void tearDown() throws IOException, Exception {

		// Revert Joomla Configuration
		joomla.MTAPIConfig.navigateToAPIComponents();
		joomla.MTAPIConfig.servicesUrl("Valid URL", ServiceURL.Lotto_Get_Games_URL);
		joomla.MTAPIConfig.saveAndClose();
		GETclearMemCache("Access_Token_PCHAPP");

		closeallwindows();
		endTestCase("End");
	}
}