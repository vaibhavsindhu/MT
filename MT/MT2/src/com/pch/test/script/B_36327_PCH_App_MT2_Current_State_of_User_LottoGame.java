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

public class B_36327_PCH_App_MT2_Current_State_of_User_LottoGame extends Action_Wrapper {
	Jmeter_Xml_Parser xp = new Jmeter_Xml_Parser();
	JoomlaAdministrator joomla = new JoomlaAdministrator();
	Database_Configuration DB = new Database_Configuration();
	RFTestPage RF = new RFTestPage();
	String response,Email,GMT;
	String threadGroupLottoGameStart[] = {"Lotto Game Start","AppLoad After Game Start"};
	String scriptName = this.getClass().getSimpleName();
	
	
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
		joomla.MTAPIConfig.servicesUrl("Valid URL", ServiceURL.Instant_Win_Engine);
		joomla.MTAPIConfig.saveAndClose();
		 
		joomla.article.navigateToArticleManager();
		
		
		joomla.PA.filterArticleByNames(AppData.Category_ID.PchApp2_Category,AppData.PchApp2.PCHApp);
		joomla.PA.editArticle(AppData.PchApp2.PCHApp, "publish");
		waitForPageToLoad(MTDriver);
		joomla.PA.FeatureFieldremove();
		joomla.PA.TileFieldremove();
		joomla.PA.featureFieldSelectArticle(AppData.ContentPath.Lotto_ContentPath);
		joomla.PA.tileFieldSelectArticle("Tile Lotto", 0);
		joomla.article.saveArticle();
		joomla.article.selectcategoryDropDown("- Select Category -");

		joomla.article.search(AppData.ContentPath.Lotto_ContentPath);
		joomla.ML.removeActionArticle();
	
		joomla.ML.selectActionArticleDropdown("Lotto Game1", 0);
		joomla.article.saveArticle();
	
		closeallwindows();

		disableThread(scriptName+".jmx", threadGroupLottoGameStart);
		
	}

	@Test(groups = { GroupName.Game, GroupName.Regression })
	public void story_B_36327_PCH_App_MT2_Current_State_of_User_LottoGame()
			throws Exception {
		Log.info("Start Executing Jmeter Script");
		CallJmeterBuild(scriptName+".jmx");
		Log.info("JMX file Called");
		Assert.assertEquals("false", xp.Xml_Parser(scriptName+".xml"));
		Log.info("Assertion Validation Completed");
		
		Assert.assertTrue(DB.phpctrl_app_content_status(GMT).contains("326\":{\"status\":1"),"Checking Game Status -- must be 2");		

		
		enableThread(scriptName+".jmx", threadGroupLottoGameStart);

		Log.info("Start Executing Jmeter Script");
		CallJmeterBuild(scriptName+".jmx");
		Log.info("JMX file Called");
		Assert.assertEquals("false", xp.Xml_Parser(scriptName+".xml"));
		Log.info("Assertion Validation Completed");
		
		Assert.assertTrue(DB.phpctrl_app_content_status(GMT).contains("326\":{\"status\":3"),"Checking Game Status -- must be 3");		
	
	}

	@AfterClass
	public void tearDown() {
		closeallwindows();
	}

}
