package com.pch.test.script;

import static com.pch.utilities.Log.Log;
import static com.pch.utilities.Log.endTestCase;
import static com.pch.utilities.Log.startTestCase;

import java.util.Calendar;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.pch.configuration.testpages.RFTestPage;
import com.pch.configuration.testpages.RFTestPage.ResponseData;
import com.pch.joomla.configuration.AliasPage.status;
import com.pch.joomla.configuration.JoomlaAdministrator;
import com.pch.utilities.Action_Wrapper;
import com.pch.utilities.AppData;
import com.pch.utilities.Database_Configuration;
import com.pch.utilities.GroupName;
import com.pch.utilities.Jmeter_Xml_Parser;
import com.pch.utilities.Log;

@SuppressWarnings("unused")
public class B_31687_PCH_APP_MT2_App_Article extends Action_Wrapper{

	Jmeter_Xml_Parser xp = new Jmeter_Xml_Parser();
	JoomlaAdministrator joomla = new JoomlaAdministrator();
	Database_Configuration DB = new Database_Configuration();
	String response;
	RFTestPage RF = new RFTestPage();
	Calendar now = Calendar.getInstance();
	String jmeterScriptName = this.getClass().getSimpleName();
	String enableThread[] = {"Check AppId"};    
	String enableThread1[] = {"Come Next day when all PachApp article unpublished"};
	String enableThread2[] = {"Unpublish all PchApp Article And Flush Database"};

	@BeforeClass
	public void setup() throws Exception {
		startTestCase(jmeterScriptName);
		Log.info("Scenario which will be executed ");
		createAccessTokenForAllApp("pchapp");

		response=RF.createFullRegUserAPI();
		writeInProprtyFile("Encrypted_FullRegEmail", getEncryptedData("Access_Token_PCHAPP", RF.getResponseValueAPI(response,ResponseData.Email)));
		writeInProprtyFile("Encrypted_GMT", getEncryptedData("Access_Token_PCHAPP", RF.getResponseValueAPI(response,ResponseData.GlobalMemberToken)));
		copyAndRenameFile();

		openBrowser("joomla");
		joomla.login.login();
		joomla.article.navigateToArticleManager();


		// Configure Meta Action Bonus in PCH App article
		joomla.PA.filterArticleByNames(AppData.Category_ID.PchApp2_Category,AppData.PchApp2.PCHApp);
		joomla.PA.editArticle(AppData.PchApp2.PCHApp, "publish");		
		joomla.alias.saveAndClose();
		writeInProprtyFile("AppId", "460-"+generateDate("YYYYMMddhh", "todays"));
		copyAndRenameFile();		

	}

	@Test(groups = { GroupName.RegFoundation, GroupName.Game,GroupName.PCHApp })
	public void story_B_31687_PCH_APP_MT2_App_Article() throws Exception {
		Log.info("Start Executing Jmeter Script");

		enableThread(jmeterScriptName+".jmx", enableThread);		
		CallJmeterBuild(jmeterScriptName+".jmx");
		Log.info("calling  jmeter file ");
		Assert.assertEquals("false", xp.Xml_Parser(jmeterScriptName+".xml"));
		Log.info("Validating assertion ");

		/*
		 * Unpublish all PchApp Article
		 */

		joomla.article.navigateToArticleManager();
		joomla.PA.filterArticleByNames(AppData.Category_ID.PchApp2_Category,AppData.PchApp2.PCHApp);
		joomla.PA.editArticle(AppData.PchApp2.PCHApp, "unpublish");
		joomla.alias.saveAndClose();

		writeInProprtyFile("AppId", "460-"+Generate_TodayDate("YYYYMMddhh"));
		copyAndRenameFile();

		Log.info("Start Executing Jmeter Script");
		enableThread(jmeterScriptName+".jmx", enableThread2);
		CallJmeterBuild(jmeterScriptName+".jmx");
		Log.info("calling  jmeter file ");

		Assert.assertEquals("false", xp.Xml_Parser(jmeterScriptName+".xml"));
		Log.info("Validating assertion ");


		/*
		 * Come Next day when all PachApp article unpublished
		 */
		Log.info("Come Next day when all PachApp article unpublished");
		DB.updateDate(Generate_TodayDate("YYYY-MM-dd"), Generate_YesterdayDate("YYYY-MM-dd"));
		enableThread(jmeterScriptName+".jmx", enableThread1);

		Log.info("Start Executing Jmeter Script");

		CallJmeterBuild(jmeterScriptName+".jmx");
		Log.info("calling  jmeter file ");

		Assert.assertEquals("false", xp.Xml_Parser(jmeterScriptName+".xml"));
		Log.info("Validating assertion ");


		/*
		 *  Unpublish all PchApp Article And Flush Database
		 */
		Log.info("Unpublish all PchApp Article And Flush Database");
		DB.flush("PchApp", "DELETE FROM `phpctrl_app_content_tree`");
		enableThread(jmeterScriptName+".jmx", enableThread2);

		Log.info("Start Executing Jmeter Script");

		CallJmeterBuild(jmeterScriptName+".jmx");
		Log.info("calling  jmeter file ");

		Assert.assertEquals("false", xp.Xml_Parser(jmeterScriptName+".xml"));
		Log.info("Validating assertion ");
	}

	@AfterClass
	public void tearDown() {
		joomla.article.navigateToArticleManager();
		joomla.PA.filterArticleByNames(AppData.Category_ID.PchApp2_Category,AppData.PchApp2.PCHApp);
		joomla.PA.editArticle(AppData.PchApp2.PCHApp, "publish");
		joomla.alias.saveAndClose();
		closeallwindows();
		endTestCase("End");

	}
}