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
import com.pch.utilities.Database_Configuration;
import com.pch.utilities.GroupName;
import com.pch.utilities.Jmeter_Xml_Parser;

public class B_35597_MT2_tokensubmit_PCHAPP_ErrorSchenario extends Action_Wrapper{

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
		
		joomla.PA.filterArticleByNames("99", "PCH App");
		joomla.PA.editArticle("PCH App", "publish");
		joomla.PA.featureFieldSelectArticle("Continuous Content Path");
		joomla.alias.saveAndClose();				
		
		// ensuring if content path article is published and adding article to content path 
		joomla.PA.filterArticleByNames("73", "Continuous Content Path");
		joomla.PA.editArticle("Continuous Content Path", "publish");
	    joomla.PA.selectActionArticles("328");
		joomla.alias.saveAndClose();
		
		joomla.article.navigateToArticleManager();
		joomla.PA.filterArticleByNames("108", "Score Submit 1");
		joomla.PA.editArticle("Score Submit 1", "publish");
		//joomla.article.search("Score Submit 1");
		joomla.CG.setMaximumSubmissionAllowed(4);
		joomla.CG.setMaximumDailyTokenPayout("");
		joomla.CG.setMinimumGameplayDuration(1);
		joomla.alias.saveAndClose();
		
		
				
		closeallwindows();
		GETclearMemCache("Access_Token_PCHAPP");
		//DB.flush("PchApp", "DELETE FROM `phpctrl_app_content_tree`");
		//disableThread(scriptName+".jmx", threadGroup);
		
	}

	@Test(groups = { GroupName.Game, GroupName.Regression })
	public void story_B_35597_MT2_tokensubmit_PCHAPP_ErrorSchenario() throws Exception {
		
		Log.info("****** Start Executing Jmeter Script - Thread 1 ************");
		CallJmeterBuild(scriptName+".jmx");
		Log.info("JMX file Called");
		Assert.assertEquals("false", xp.Xml_Parser(scriptName+".xml"));
		Log.info("Assertion Validation Completed");
		
		enableThread(scriptName+".jmx", threadGroup);
		response = RF.createFullRegUserAPI();
		writeInProprtyFile("Email", getEncryptedData("Access_Token_PCHAPP",RF.getResponseValueAPI(response, ResponseData.Email)));
		writeInProprtyFile("GMT", getEncryptedData("Access_Token_PCHAPP",RF.getResponseValueAPI(response, ResponseData.GlobalMemberToken)));
		copyAndRenameFile();
	
	
}

	@AfterClass
	public void tearDown() throws IOException, Exception {
		endTestCase("End");
	}
}


/*joomla.article.selectcategoryDropDown("- Pch App");
joomla.article.search("PCH APP old QA","search");
joomla.article.articleStatus("publish");
joomla.article.search("PCH APP old QA","ALIAS");
joomla.alias.finishPublishingDate(""); joomla.alias.saveAndClose();
 

joomla.article.search("PCH APP old QA");
waitForPageToLoad(MTDriver);
joomla.PA.FeatureFieldremove();
joomla.PA.TileFieldremove();
joomla.PA.featureFieldSelectArticle("Lotto Content Path", 0);
joomla.PA.tileFieldSelectArticle("Tile Lotto", 0);
joomla.article.saveArticle();
joomla.article.selectcategoryDropDown("- Select Category -");

joomla.article.search("Lotto Content Path");
joomla.ML.removeActionArticle();
joomla.ML.selectActionArticleDropdown("Score Submit 1", 0);
joomla.article.saveArticle();


joomla.article.navigateToArticleManager();
joomla.article.search("Score Submit 1");
joomla.CG.setMaximumSubmissionAllowed(4);
joomla.CG.setMaximumDailyTokenPayout("");
joomla.CG.setMinimumGameplayDuration(1);
joomla.article.saveArticle();

	*/