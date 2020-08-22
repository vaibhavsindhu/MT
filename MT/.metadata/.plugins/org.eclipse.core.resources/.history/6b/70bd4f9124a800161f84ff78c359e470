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
import com.pch.joomla.configuration.MidTierApiConfiguration.ServiceURL;
import com.pch.utilities.Action_Wrapper;
import com.pch.utilities.AppData;
import com.pch.utilities.Database_Configuration;
import com.pch.utilities.GroupName;
import com.pch.utilities.Jmeter_Xml_Parser;

public class B_35596_MT2_scoresubmit_PCHAPP_MultipleSchenario extends Action_Wrapper{

	Jmeter_Xml_Parser xp = new Jmeter_Xml_Parser();
	JoomlaAdministrator joomla = new JoomlaAdministrator();
	Database_Configuration DB = new Database_Configuration();
	
	RFTestPage RF = new RFTestPage();
	String response;
	String scriptName = this.getClass().getSimpleName();
	String threadGroup[] ={"max submission limit is 0"};
	
	@BeforeClass
	public void setup() throws Exception {
		startTestCase(scriptName);
		
		response = RF.createFullRegUserAPI();
		writeInProprtyFile("Email", getEncryptedData("Access_Token_PCHAPP",RF.getResponseValueAPI(response, ResponseData.Email)));
		writeInProprtyFile("GMT", getEncryptedData("Access_Token_PCHAPP",RF.getResponseValueAPI(response, ResponseData.GlobalMemberToken)));
		copyAndRenameFile();
		
		openBrowser("chrome");
		joomla.login.login();
		
		joomla.MTAPIConfig.navigateToAPIComponents();
		joomla.MTAPIConfig.servicesUrl("Valid URL",
		ServiceURL.Instant_Win_Engine); joomla.MTAPIConfig.saveAndClose();
		 
		joomla.article.navigateToArticleManager();
			
		// Configure Content Path in App Article
				joomla.PA.filterArticleByNames(AppData.Category_ID.PchApp2_Category, AppData.PchApp2.PCHApp);
				joomla.PA.editArticle(AppData.PchApp2.PCHApp, "publish");
				joomla.PA.featureFieldSelectArticle(AppData.ContentPath.Continuous_ContentPath);
				joomla.alias.saveAndClose();
				// ensuring if content path article is published and adding article to
				// content path
				joomla.PA.filterArticleByNames(AppData.Category_ID.ContentPath_Category,
						AppData.ContentPath.Continuous_ContentPath);
				joomla.PA.editArticle(AppData.ContentPath.Continuous_ContentPath, "publish");
				joomla.PA.selectActionArticles(AppData.GamesID.SBG.ScoreSubmit1);
				joomla.alias.saveAndClose();

				// configuring game article score submit 1
				joomla.article.navigateToArticleManager();
				joomla.PA.filterArticleByNames(AppData.Category_ID.SBG, AppData.GameName.SBG.ScoreSubmit1);
				joomla.PA.editArticle(AppData.GameName.SBG.ScoreSubmit1, "publish");
				joomla.CG.setMaximumSubmissionAllowed(4);
				joomla.CG.setMaximumDailyTokenPayout("");
				joomla.CG.setMinimumGameplayDuration(1);
				joomla.CG.setTokenPaytableDropDown(AppData.TokenPaytable.TokenPaytable1);
				joomla.alias.saveAndClose();

				joomla.article.navigateToArticleManager();

				joomla.PA.filterArticleByNames(AppData.Category_ID.TokenPaytables, AppData.TokenPaytable.TokenPaytable1);
				joomla.PA.editArticle(AppData.TokenPaytable.TokenPaytable1, "publish");
				joomla.CG.rempoveAllTokenPayable();
				joomla.CG.addTokenPayableCCK();
				joomla.CG.upToPointsScored(150, 1);
				joomla.CG.tokensAwarded("1000", 1);
				joomla.CG.upToPointsScored(200, 2);
				joomla.CG.tokensAwarded("10000", 2);
				joomla.alias.saveAndClose();
				closeallwindows();
		

		

		GETclearMemCache("Access_Token_PCHAPP");
		disableThread(scriptName+".jmx", threadGroup);
	}

	@Test(groups = { GroupName.Game, GroupName.Regression })
	public void story_B_35596_MT2_scoresubmit_PCHAPP_MultipleSchenario() throws Exception {
		
		Log.info("Start Executing Jmeter Script");
		CallJmeterBuild(scriptName+".jmx");
		Log.info("JMX file Called");
		Assert.assertEquals("false", xp.Xml_Parser(scriptName+".xml"));
		Log.info("Assertion Validation Completed");
		
		enableThread(scriptName+".jmx", threadGroup);
		response = RF.createFullRegUserAPI();
		writeInProprtyFile("Email", getEncryptedData("Access_Token_PCHAPP",RF.getResponseValueAPI(response, ResponseData.Email)));
		writeInProprtyFile("GMT", getEncryptedData("Access_Token_PCHAPP",RF.getResponseValueAPI(response, ResponseData.GlobalMemberToken)));
		copyAndRenameFile();

		
		Log.info("Start Executing Jmeter Script");
		CallJmeterBuild(scriptName+".jmx");
		Log.info("JMX file Called");
		Assert.assertEquals("false", xp.Xml_Parser(scriptName+".xml"));
		Log.info("Assertion Validation Completed");
	}

	@AfterClass
	public void tearDown() throws IOException, Exception {
		endTestCase("End");
	}
}