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
import com.pch.utilities.AppData;
import com.pch.utilities.Database_Configuration;
import com.pch.utilities.GroupName;
import com.pch.utilities.Jmeter_Xml_Parser;

/**
 * @author SPAL
 * This Test class is for user story
 * "https://www8.v1host.com/PCH/story.mvc/Summary?oidToken=Story%3A1390476"
 * */

public class B_39296_MT2_PCHAppAppIdCheckBonusSubmitService extends Action_Wrapper{
	JoomlaAdministrator joomla = new JoomlaAdministrator();
	MetaActionBonusArticle metaBonus = new MetaActionBonusArticle();
	Database_Configuration DB = new Database_Configuration();
	BonusTriggers Btrigger = new BonusTriggers();
	String jmeterScriptName = this.getClass().getSimpleName();
	private String response;
	RFTestPage RF = new RFTestPage();
	Jmeter_Xml_Parser xp = new Jmeter_Xml_Parser();	
	String threadGroup[]={"AppLoadForAndroidArticle","BonusSubmit_AppIdChkEnabled_ValidAppId", "BonusSubmit_AppIdChkEnabled_InvalidAppId"};
	String threadGroup1[]={"BonusSubmit_AppIdChkDisabled_ValidAppId", "BonusSubmit_AppIdChkDisabled_InvalidAppId"};
	
	String appconfig_article="PCHAPP";
	String app_article=AppData.PchApp2.PCHApp_Android_13;
	String bonus_article="Bonus Reward for FB Connect";
	String trigger_article="Facebook_Connect";
	
	@BeforeClass
	public void setup() throws Exception {

		startTestCase(jmeterScriptName);

		createAccessTokenForAllApp("pchapp");

		response = RF.createFullRegUserAPI();
		writeInProprtyFile("Encrypted_Email", getEncryptedData("Access_Token_PCHAPP", RF.getResponseValueAPI(response,ResponseData.Email)));
		writeInProprtyFile("Encrypted_GMT", getEncryptedData("Access_Token_PCHAPP", RF.getResponseValueAPI(response,ResponseData.GlobalMemberToken)));
		copyAndRenameFile();
		response = RF.createFullRegUserAPI();
		writeInProprtyFile("Encrypted_Email1", getEncryptedData("Access_Token_PCHAPP", RF.getResponseValueAPI(response,ResponseData.Email)));
		writeInProprtyFile("Encrypted_GMT1", getEncryptedData("Access_Token_PCHAPP", RF.getResponseValueAPI(response,ResponseData.GlobalMemberToken)));
		copyAndRenameFile();

		response = RF.createFullRegUserAPI();
		writeInProprtyFile("Encrypted_Email2", getEncryptedData("Access_Token_PCHAPP", RF.getResponseValueAPI(response,ResponseData.Email)));
		writeInProprtyFile("Encrypted_GMT2", getEncryptedData("Access_Token_PCHAPP", RF.getResponseValueAPI(response,ResponseData.GlobalMemberToken)));
		copyAndRenameFile();

		// Configure App Articles
		openBrowser("joomla");
		joomla.login.login();		

		// Open appconfig article PCHAPP and enable AppId chk
		joomla.article.navigateToArticleManager();
		joomla.PA.filterArticleByNames(AppData.Category_ID.AppConfig, appconfig_article);
		joomla.PA.editArticle(appconfig_article, "publish");
		joomla.PA.updateAppIdCheckForGameCalls("enable");
		joomla.alias.saveAndClose();

		joomla.article.navigateToArticleManager();
		joomla.PA.filterArticleByNames("14", bonus_article);
		joomla.PA.editArticle(bonus_article, "publish");
		metaBonus.configureBonusAward("1000", "2", "1000");
		joomla.alias.saveAndClose();

		// Configure Bonus Trigger Frequency as All time.
		joomla.PA.filterArticleByNames("57",trigger_article );
		joomla.PA.editArticle(trigger_article, "publish");
		Btrigger.configureTriggerOptions("connect", "facebook", "onetime");
		joomla.alias.saveAndClose();



		// Configure Meta Action Bonus in PCH App 1.3 Android article
		joomla.article.navigateToArticleManager();
		joomla.PA.filterArticleByNames("99",app_article );
		joomla.PA.editArticle(app_article, "publish");
		joomla.PA.bonusActionArticle(bonus_article, trigger_article);	
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
		joomla.PA.filterArticleByNames("68", appconfig_article);
		joomla.PA.editArticle(appconfig_article, "publish");
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
