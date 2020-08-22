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
import com.pch.joomla.configuration.MidTierApiConfiguration.ServiceURL;
import com.pch.utilities.Action_Wrapper;
import com.pch.utilities.AppData;
import com.pch.utilities.Database_Configuration;
import com.pch.utilities.GroupName;
import com.pch.utilities.Jmeter_Xml_Parser;

public class B_36408_PCHAPP_MT2_scratchcard_Numbermatch_cash extends Action_Wrapper {

	Jmeter_Xml_Parser xp = new Jmeter_Xml_Parser();
	JoomlaAdministrator joomla = new JoomlaAdministrator();
	Database_Configuration DB = new Database_Configuration();
	DevicesPage iwDp = new DevicesPage();
	RFTestPage RF = new RFTestPage();
	String response;
	String scriptName = "B_36408_PCHAPP_MT2_scratchcard_Numbermatch_cash";
	HomePage iwhp = new HomePage();

	String startDate = GetTodayDate().toString();
		  

	@BeforeClass
	public void setup() throws Exception {
		startTestCase("B_36408_PCHAPP_MT2_scratchcard_Numbermatch_cash");
		
		createAccessTokenForAllApp("pchapp");

		response = RF.createFullRegUserAPI();
		writeInProprtyFile("Email", getEncryptedData("Access_Token_PCHAPP",RF.getResponseValueAPI(response, ResponseData.Email)));
		writeInProprtyFile("GMT", getEncryptedData("Access_Token_PCHAPP",RF.getResponseValueAPI(response, ResponseData.GlobalMemberToken)));
		copyAndRenameFile();
			
		openBrowser("chrome");
		
		
	}

	@Test(groups = { GroupName.Game, GroupName.Regression })
	public void Story_B_36408_PCHAPP_MT2_scratchcard_Numbermatch_cash() throws Exception {
		
		joomla.login.login();
		joomla.MTAPIConfig.navigateToAPIComponents();
		joomla.MTAPIConfig.servicesUrl("Valid URL", ServiceURL.Instant_Win_Engine);
		joomla.MTAPIConfig.saveAndClose();

		joomla.article.navigateToArticleManager();
		joomla.PA.filterArticleByNames(AppData.Category_ID.PchApp2_Category, AppData.PchApp2.PCHApp);
		joomla.PA.editArticle(AppData.PchApp2.PCHApp, "publish");

		joomla.PA.featureFieldSelectArticle(AppData.ContentPath.PCHAPP_ScratchCardContentPath);
		joomla.alias.saveAndClose();

		// ensuring if content path article is published and adding article to
		// content path

		joomla.PA.filterArticleByNames(AppData.Category_ID.ContentPath_Category,
		AppData.ContentPath.PCHAPP_ScratchCardContentPath);
		joomla.PA.editArticle(AppData.ContentPath.PCHAPP_ScratchCardContentPath, "publish");
		joomla.PA.selectActionArticles(AppData.GamesID.ScratchCard.PCHAPP$1000FortuneCat_ID);
		joomla.alias.saveAndClose();

		joomla.article.navigateToArticleManager();

		joomla.PA.filterArticleByNames(AppData.Category_ID.ScratchcardGame,
		AppData.GameName.scratchCard.PCHAPP$1000FortuneCat);
		joomla.PA.editArticle(AppData.GameName.scratchCard.PCHAPP$1000FortuneCat, "publish");
		joomla.article.selectPrizeArticleDropDown("Scratch Prize Group");
		joomla.alias.saveAndClose();
		
	/*	
		joomla.article.selectcategoryDropDown("- Pch App");
		joomla.article.search("PCH APP old QA","search");
		joomla.article.articleStatus("publish");
		joomla.article.search("PCH APP old QA","ALIAS");
		joomla.alias.finishPublishingDate("");
		joomla.alias.saveAndClose();*/
		 
/*
		joomla.article.search("PCH APP old QA");
		waitForPageToLoad(MTDriver);
		joomla.PA.FeatureFieldremove();
		joomla.PA.TileFieldremove();
		joomla.PA.featureFieldSelectArticle("PCHAPP ScratchCard Content Path");
		joomla.PA.tileFieldSelectArticle("Tile Lotto", 0);
		joomla.article.saveArticle();*/
		
		

	//	joomla.article.search(AppData.GameName.);
	/*	waitForPageToLoad(MTDriver);
		joomla.ML.removeActionArticle();
		joomla.ML.selectActionArticleDropdown("$1000 Fortune Cat",0);
		joomla.article.saveArticle();
		
		joomla.article.search("PCHAPP $1000 Fortune Cat");
		waitForPageToLoad(MTDriver);		
		joomla.article.selectPrizeArticleDropDown("Scratch Prize Group");
		joomla.article.saveArticle();*/
		

	
	    joomla.PA.filterArticleByNames(AppData.Category_ID.IW, "Scratch Prize Group");
		waitForPageToLoad(MTDriver);
		joomla.PA.editArticle("Scratch Prize Group", "publish");
		joomla.ML.removeCashPrizeValueArticle();
		joomla.article.saveArticle();
		joomla.PA.filterArticleByNames(AppData.Category_ID.IW, "Scratch Prize Group");
		waitForPageToLoad(MTDriver);
		joomla.PA.editArticle("Scratch Prize Group", "publish");
		//joomla.article.search("Scratch Prize Group");		
		joomla.ML.AddCashPrize_prizeArticle(2);
		Log.info("adding cash prize ");
		joomla.ML.SetCashPrize_prizeArticle(0, "20");
		joomla.ML.SetCashPrize_prizeArticle(1, "10");
		joomla.ML.SetCashPrize_prizeArticle(2, "50");
		joomla.article.saveArticle();
		
	
		
		Log.info("setting up token prize values ");
		
		//joomla.article.search("Scratch Prize Group");
		 joomla.PA.filterArticleByNames(AppData.Category_ID.IW, "Scratch Prize Group");
			waitForPageToLoad(MTDriver);
			joomla.PA.editArticle("Scratch Prize Group", "publish");
			joomla.ML.removeTokenPrizeValueArticle();
			joomla.article.saveArticle();
			
			
			//joomla.article.search("Scratch Prize Group");
			 joomla.PA.filterArticleByNames(AppData.Category_ID.IW, "Scratch Prize Group");
				waitForPageToLoad(MTDriver);
				joomla.PA.editArticle("Scratch Prize Group", "publish");
			joomla.ML.AddTokenPrize_prizeArticle(2);

			
			Log.info("adding cash prize ");
			joomla.ML.SetTokenPrize_prizeArticle(0, "1000");
			joomla.ML.SetTokenPrize_prizeArticle(1, "100");
			joomla.ML.SetTokenPrize_prizeArticle(2, "50");
			joomla.article.saveArticle();
					
			
			Log.info("IWE configuration for cash ");
			
			// Configure GWYAWAY group/Token Catch All
			iwhp.IWE_Login();
			iwhp.naviagateToIWEDeviceList("4321");
			iwhp.setGiveawayGroup("MidTierCashgwyGroup");
			iwDp.setTokenCatchAll("remove", "");
			iwDp.setPlayRestrictions("1000", "1000", "");
			iwhp.setComments("Automation testing");
			iwhp.ClickButtonSave();

//			homepage.navigateToIWELoginPage();
//
	//		homepage.IWE_Login();
//			homepage.naviagateToIWEDeviceList("4321");
//
//			Log.info("Setting Business unit  ");
//			homepage.setBusinessUnit("PCHApp");
//
//			Log.info("Setting give away group ");
//			homepage.setGiveawayGroup("MidTierCashgwyGroup");
//
//			Log.info("providing comments");
//			homepage.setComments("test123");
//
//			homepage.ClickButtonSave();

			Log.info("IWE configuration are successfully changed");
//			
//			Log.info("start date is " + startDate);
//			Log.info("End Date is " + endDate);
	
			// Configure Gwyaway "MTAutomationGwyAwy"
			iwhp.naviagateToIWEGiveawayList(returnPropertyValue("IWEMTAutomationGWAYID"));
			iwhp.modifygwyawyDate(Generate_TodayDate("MMM dd, YYYY h:mm:ss a"),extendedDateandTime("MMM dd, YYYY h:mm:ss a", 1));
			iwhp.setComments("Automation testing");
			iwhp.ClickButtonSave();
//			
//			
//			homepage.naviagateToIWEGiveawayList("2234");
//
//			Log.info("Setting End date ");
//			homepage.setEndDate(endDate);
//
//			Log.info("setting start date ");
//			homepage.setStartDate(startDate);
//
//			Log.info("providing comments ");
//			homepage.setComments("test123");
//			homepage.ClickButtonSave();

			Log.info("IWE configuration are successfully changed");
			closeallwindows();
		
		

		
		
		Log.info("Start Executing Jmeter Script");
		CallJmeterBuild(scriptName+".jmx");
		Log.info("JMX file Called");
		Assert.assertEquals("false", xp.Xml_Parser(scriptName+".xml"));
		Log.info("Assertion Validation Completed");
		
		
		/*Log.info("***Checking for Silver User***");
		response = RF.createSilverUserAPI();
		writeInProprtyFile("Email", getEncryptedData("Access_Token_PCHAPP",RF.getResponseValueAPI(response, ResponseData.Email)));
		writeInProprtyFile("GMT", getEncryptedData("Access_Token_PCHAPP",RF.getResponseValueAPI(response, ResponseData.GlobalMemberToken)));
		copyAndRenameFile();
		
		Log.info("Start Executing Jmeter Script");
		CallJmeterBuild(scriptName+".jmx");
		Log.info("JMX file Called");
		Assert.assertEquals("false", xp.Xml_Parser(scriptName+".xml"));
		Log.info("Assertion Validation Completed");
		
		
		
		Log.info("***Checking for Mini User***");
		response = RF.createMiniRegUserAPI();
		writeInProprtyFile("Email", getEncryptedData("Access_Token_PCHAPP",RF.getResponseValueAPI(response, ResponseData.Email)));
		writeInProprtyFile("GMT", getEncryptedData("Access_Token_PCHAPP",RF.getResponseValueAPI(response, ResponseData.GlobalMemberToken)));
		copyAndRenameFile();
		
		Log.info("Start Executing Jmeter Script");
		CallJmeterBuild(scriptName+".jmx");
		Log.info("JMX file Called");
		Assert.assertEquals("false", xp.Xml_Parser(scriptName+".xml"));
		Log.info("Assertion Validation Completed");
		*/
	}

	@AfterClass
	public void tearDown() throws IOException, Exception {
		endTestCase("End");
		closeallwindows();
	}
}