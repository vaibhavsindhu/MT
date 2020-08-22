package com.pch.test.script;

import static com.pch.utilities.Log.Log;
import static com.pch.utilities.Log.endTestCase;
import static com.pch.utilities.Log.startTestCase;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.pch.configuration.testpages.RFTestPage;
import com.pch.configuration.testpages.RFTestPage.ResponseData;
import com.pch.joomla.configuration.BonusTriggers;
import com.pch.joomla.configuration.JoomlaAdministrator;
import com.pch.joomla.configuration.MetaActionBonusArticle;
import com.pch.utilities.Action_Wrapper;
import com.pch.utilities.Database_Configuration;
import com.pch.utilities.GroupName;
import com.pch.utilities.Jmeter_Xml_Parser;

/**
 * @author SPAL
 * This Test class is for user story
 * "https://www8.v1host.com/PCH/story.mvc/Summary?oidToken=Story%3A1390115"
 * */

public class B_39284_MT2_PCHAppAppIdCheckGameCompleteService extends Action_Wrapper{
	JoomlaAdministrator joomla = new JoomlaAdministrator();
	MetaActionBonusArticle metaBonus = new MetaActionBonusArticle();
	Database_Configuration DB = new Database_Configuration();
	BonusTriggers Btrigger = new BonusTriggers();
	String jmeterScriptName = this.getClass().getSimpleName();
	private String response;
	RFTestPage RF = new RFTestPage();
	Jmeter_Xml_Parser xp = new Jmeter_Xml_Parser();	
	String threadGroup[]={"AppLoadForAndroidArticle","GameComplete_AppIdChkEnabled_ValidAppId", "GameComplete_AppIdChkEnabled_InvalidAppId"};
	String threadGroup1[]={"GameComplete_AppIdChkDisabled_ValidAppId", "GameComplete_AppIdChkDisabled_InvalidAppId"};
	@BeforeClass
	public void setup() throws Exception {

		startTestCase(jmeterScriptName);
		
		createAccessTokenForAllApp("pchapp");

		response = RF.createFullRegUserAPI();
		writeInProprtyFile("Encrypted_Email", getEncryptedData("Access_Token_PCHAPP", RF.getResponseValueAPI(response,ResponseData.Email)));
		writeInProprtyFile("Encrypted_GMT", getEncryptedData("Access_Token_PCHAPP", RF.getResponseValueAPI(response,ResponseData.GlobalMemberToken)));
		copyAndRenameFile();
		
		// Configure App Articles
		openBrowser("joomla");
		joomla.login.login();		

		// Open appconfig article PCHAPP and enable AppId chk
		joomla.article.navigateToArticleManager();
		joomla.PA.filterArticleByNames("68", "PCHAPP");
		joomla.PA.editArticle("PCHAPP", "publish");
		joomla.PA.updateAppIdCheckForGameCalls("enable");
		joomla.alias.saveAndClose();

		// publish score submit action article
		joomla.article.navigateToArticleManager();
		joomla.PA.filterArticleByNames("108", "Score Submit 1");
		joomla.PA.editArticle("Score Submit 1", "publish");
		joomla.CG.setMaximumSubmissionAllowed(3);
		joomla.alias.saveAndClose();

		//add action articles in content path
		joomla.article.navigateToArticleManager();
		joomla.PA.filterArticleByNames("104", "Continuous Content Path");
		joomla.PA.editArticle("Continuous Content Path", "publish");
		joomla.PA.selectActionArticles("328");
		joomla.alias.saveAndClose();

		//Adding content path to app article
		joomla.article.navigateToArticleManager();
		joomla.PA.filterArticleByNames("99", "PCH App 1.3 Android");
		joomla.PA.editArticle("PCH App 1.3 Android", "publish");
		joomla.PA.featureFieldSelectArticle("Continuous Content Path");
		joomla.alias.saveAndClose();

	}

	@Test(groups = {GroupName.PCHApp ,GroupName.Regression})
	public void MT2_PCHAppAppIdCheckScoreSubmitService() throws Exception {	
		
		//enable thread for disabled AppId chk
		enableThread(jmeterScriptName+".jmx", threadGroup);
		Log.info("Start Executing Jmeter Script");

		CallJmeterBuild(jmeterScriptName+".jmx"); 
		Log.info("Calling  Jmeter file ");

		Assert.assertEquals("false",xp.Xml_Parser(jmeterScriptName+".xml")); 
		Log.info("Validating assertion ");

		// Configure App Articles disable AppId Chk
		openBrowser("joomla");
		joomla.login.login();
		
		// Open appconfig article PCHAPP and enable AppId chk
		joomla.article.navigateToArticleManager();
		joomla.PA.filterArticleByNames("68", "PCHAPP");
		joomla.PA.editArticle("PCHAPP", "publish");
		joomla.PA.updateAppIdCheckForGameCalls("disable");
		joomla.alias.saveAndClose();
		
		//enable thread for disabled AppId chk
		enableThread(jmeterScriptName+".jmx", threadGroup1);
		Log.info("Start Executing Jmeter Script");

		CallJmeterBuild(jmeterScriptName+".jmx"); 
		Log.info("Calling  Jmeter file ");

		Assert.assertEquals("false",xp.Xml_Parser(jmeterScriptName+".xml")); 
		Log.info("Validating assertion ");
	}

	@AfterClass
	public void tearDown() {
		closeallwindows();
		endTestCase("End");

	}

}
