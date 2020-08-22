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

public class B_36916_MT2_PCHApp_Scratchcard_NumberMatch_CertificateCashstar_CashStar extends Action_Wrapper{

	Jmeter_Xml_Parser xp = new Jmeter_Xml_Parser();
	JoomlaAdministrator joomla = new JoomlaAdministrator();
	Database_Configuration DB = new Database_Configuration();
	HomePage iwhomepage = new HomePage();
	DevicesPage device = new DevicesPage();
	
	RFTestPage RF = new RFTestPage();
	String response;
	String scriptName = this.getClass().getSimpleName();
	HomePage homepage = new HomePage();
	String BusinessUnit = "PCHAPP";
 

	@BeforeClass
	public void setup() throws Exception {
		startTestCase(scriptName);
		createAccessTokenForAllApp("pchapp");
		response = RF.createFullRegUserAPI();
		writeInProprtyFile("Encrypted_Email", getEncryptedData("Access_Token_PCHAPP",RF.getResponseValueAPI(response, ResponseData.Email)));
		writeInProprtyFile("Encrypted_GMT", getEncryptedData("Access_Token_PCHAPP",RF.getResponseValueAPI(response, ResponseData.GlobalMemberToken)));
		copyAndRenameFile();
		
		openBrowser("chrome");
		joomla.login.login(); 
		joomla.MTAPIConfig.navigateToAPIComponents();
		joomla.MTAPIConfig.servicesUrl("Valid URL",ServiceURL.Instant_Win_Engine); 
		joomla.MTAPIConfig.saveAndClose();
		 
		joomla.article.navigateToArticleManager();
		
		// Configure  Edit Scratch Card
		joomla.PA.filterArticleByNames(AppData.Category_ID.ScratchcardGame, AppData.GamesID.ScratchCard.PCHAPP$1000FortuneCat_Name);
		joomla.PA.editArticle(AppData.GamesID.ScratchCard.PCHAPP$1000FortuneCat_Name, "publish");
		joomla.article.setPCHGameClassName("matchnumbers");
		joomla.PA.configureIWEinJoomla(AppData.IWEConfiguration.IWEDeviceList.PCH_IWDeviceID_4321, AppData.IWEConfiguration.IWEDeviceList.PCH_IWDeviceID_4321_AccessKey);
		joomla.PA.configureScratchPrizeArticle(AppData.PrizeGroup.ScratchPrizegrp_ID);
		joomla.actions.selectFilterArticle(AppData.Filters.noFilter);
		joomla.alias.saveAndClose();

		
		// Configure Content Path
		joomla.PA.filterArticleByNames(AppData.Category_ID.ContentPath_Category,AppData.ContentPath.PCHAPP_ScratchCardContentPath);
		joomla.PA.editArticle(AppData.ContentPath.PCHAPP_ScratchCardContentPath, "publish");
		joomla.PA.selectActionArticles(AppData.GamesID.ScratchCard.PCHAPP$1000FortuneCat_ID);
		joomla.alias.saveAndClose();
		
		// Configure Content Path in App Article
		joomla.PA.filterArticleByNames(AppData.Category_ID.PchApp2_Category,AppData.PchApp2.PCHApp_Android_13);
		joomla.PA.editArticle(AppData.PchApp2.PCHApp_Android_13, "publish");
		joomla.PA.featureFieldSelectArticle(AppData.ContentPath.PCHAPP_ScratchCardContentPath);
		joomla.alias.saveAndClose();
		
		// Configure Cash Star Prize Value.

		joomla.PA.filterArticleByNames(AppData.Category_ID.IW, AppData.PrizeGroup.ScratchPrizegrp_Name);
		joomla.PA.editArticle(AppData.PrizeGroup.ScratchPrizegrp_Name, "publish");
		joomla.ML.AddCashPrize_prizeArticle(1);
		Log.info("Adding Certificate Prize ");
		joomla.ML.SetCashPrize_prizeArticle(0, "25");
		joomla.alias.saveAndClose();
		
		// Configure GWYAWAY group/Token Catch All
		iwhomepage.IWE_Login();
		iwhomepage.naviagateToIWEDeviceList(AppData.IWEConfiguration.IWEDeviceList.PCH_IWDeviceID_4321);
		device.setGiveawayGroup(AppData.IWEConfiguration.IWEGiveawayGroup.GiverAwayGroupforCashStar);
		device.setBusinessUnit("PCHApp");
		device.setTokenCatchAll("remove", "");
		device.setPlayRestrictions("1000", "1000", "");
		iwhomepage.setComments("automation testing ");
		iwhomepage.ClickButtonSave();	
		
		// Configure Gwyaway "MidTierCashStargroup"
		iwhomepage.naviagateToIWEGiveawayList(AppData.IWEConfiguration.IWEGwayAway.CashStarGiveAway);
		iwhomepage.modifygwyawyDate(generateDate("MMM dd, YYYY h:mm:ss a", "todays"),extendedDateandTime("MMM dd, YYYY h:mm:ss a", 1));
		iwhomepage.setComments("Automation testing");
		iwhomepage.ClickButtonSave();
		
		Log.info("IWE configuration are successfully changed");
		closeallwindows();

	}

	@Test(groups = { GroupName.IWE, GroupName.Regression })
	public void Story_B_36916_MT2_PCHApp_Scratchcard_NumberMatch_CertificateCashstar_CashStar() throws Exception {
		
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