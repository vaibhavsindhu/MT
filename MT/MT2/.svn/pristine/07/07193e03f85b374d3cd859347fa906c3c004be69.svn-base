package com.pch.test.script;

import static com.pch.utilities.Log.Log;
import static com.pch.utilities.Log.startTestCase;

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

public class B_36327_PCH_App_MT2_Current_State_of_User_ContinuousGame_Token_Submit extends Action_Wrapper {
	Jmeter_Xml_Parser xp = new Jmeter_Xml_Parser();
	JoomlaAdministrator joomla = new JoomlaAdministrator();
	Database_Configuration DB = new Database_Configuration();
	String scriptName = this.getClass().getSimpleName();
	RFTestPage RF = new RFTestPage();
	String response,Email,GMT;
	String threadGroupSecondTokenSubmit[] = {"Token Submit 2nd","AppLoad After second token submit"};

	
	
	@BeforeClass
	public void setup() throws Exception {
		startTestCase(scriptName);
		createAccessTokenForAllApp("pchapp");

		response = RF.createFullRegUserAPI();
		GMT=RF.getResponseValueAPI(response, ResponseData.GlobalMemberToken);
		writeInProprtyFile("GMT", getEncryptedData("Access_Token_PCHAPP", GMT));
		writeInProprtyFile("Encrypted_Email", getEncryptedData("Access_Token_PCHAPP",RF.getResponseValueAPI(response, ResponseData.Email)));
		writeInProprtyFile("Encrypted_GMT", getEncryptedData("Access_Token_PCHAPP",GMT));
		copyAndRenameFile();
		
		openBrowser("chrome");
		joomla.login.login();
		
		joomla.MTAPIConfig.navigateToAPIComponents();
		joomla.MTAPIConfig.servicesUrl("Valid URL",ServiceURL.Instant_Win_Engine);
		joomla.MTAPIConfig.saveAndClose();
		 
		joomla.article.navigateToArticleManager();
		
		
		joomla.article.search(AppData.PchApp2.PCHApp_iOS_14);
		waitForPageToLoad(MTDriver);
		joomla.PA.FeatureFieldremove();
		joomla.PA.TileFieldremove();
		joomla.PA.featureFieldSelectArticle(AppData.ContentPath.Continuous_ContentPath);
		joomla.PA.tileFieldSelectArticle("Tile Lotto", 0);
		joomla.article.saveArticle();
		joomla.article.selectcategoryDropDown("- Select Category -");

		joomla.article.search(AppData.ContentPath.Continuous_ContentPath);
		joomla.ML.removeActionArticle();
		joomla.ML.selectActionArticleDropdown("Score Submit 1", 0);
		joomla.article.saveArticle();		
		joomla.CG.configureContinuesGame("Score Submit 1", 2, "3000", 1, "token paytable1");
		closeallwindows();

		disableThread(scriptName+".jmx", threadGroupSecondTokenSubmit);
		
	}

	@Test(groups = { GroupName.Game, GroupName.Regression })
	public void story_B_36327_PCH_App_MT2_Current_State_of_User_ContinuousGame_Token_Submit() throws Exception {
		
		Log.info("Start Executing Jmeter Script");
		CallJmeterBuild(scriptName+".jmx");
		Log.info("JMX file Called");
		Assert.assertEquals("false", xp.Xml_Parser(scriptName+".xml"));
		Log.info("Assertion Validation Completed");
		
		Assert.assertTrue(DB.phpctrl_app_content_status(GMT).contains("328\":{\"status\":2"),"Checking Game Status -- must be 2");		

		
		enableThread(scriptName+".jmx", threadGroupSecondTokenSubmit);

		Log.info("Start Executing Jmeter Script");
		CallJmeterBuild(scriptName+".jmx");
		Log.info("JMX file Called");
		Assert.assertEquals("false", xp.Xml_Parser(scriptName+".xml"));
		Log.info("Assertion Validation Completed");
		
		Assert.assertTrue(DB.phpctrl_app_content_status(GMT).contains("328\":{\"status\":3"),"Checking Game Status -- must be 3");		

		
	}

	@AfterClass
	public void tearDown() {
		closeallwindows();
	}

}
