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
import com.pch.utilities.Action_Wrapper;
import com.pch.utilities.AppData;
import com.pch.utilities.Database_Configuration;
import com.pch.utilities.GroupName;
import com.pch.utilities.Jmeter_Xml_Parser;


public class B_36915_M2_PCHAppscratchcardMatch3_Certificate extends Action_Wrapper{

	Jmeter_Xml_Parser xp = new Jmeter_Xml_Parser();
	JoomlaAdministrator joomla = new JoomlaAdministrator();
	
			
	RFTestPage RF = new RFTestPage();
	String response,GMT,Email;
	String scriptName = this.getClass().getSimpleName();
	HomePage iwhomepage = new HomePage();
	DevicesPage device = new DevicesPage();
	int result;
	
	Database_Configuration DB = new Database_Configuration();

	@BeforeClass
	public void setup() throws Exception {
		startTestCase(scriptName);
		
		createAccessTokenForAllApp("pchapp");
				
		response=RF.createFullRegUserAPI();
		Email = RF.getResponseValueAPI(response, ResponseData.Email);
		GMT=RF.getResponseValueAPI(response, ResponseData.GlobalMemberToken);
		writeInProprtyFile("Encrypted_Email", getEncryptedData("Access_Token_PCHAPP",RF.getResponseValueAPI(response, ResponseData.Email)));
		writeInProprtyFile("Encrypted_GMT", getEncryptedData("Access_Token_PCHAPP",RF.getResponseValueAPI(response, ResponseData.GlobalMemberToken)));
		copyAndRenameFile();
		
		
		openBrowser("chrome");
		joomla.login.login();
		joomla.article.navigateToArticleManager();

		joomla.PA.filterArticleByNames(AppData.Category_ID.PchApp2_Category,AppData.PchApp2.PCHApp_Android_13);
		joomla.PA.editArticle(AppData.PchApp2.PCHApp_Android_13, "publish");
		joomla.PA.featureFieldSelectArticle(AppData.ContentPath.PCHAPP_ScratchCardContentPath);
		joomla.alias.saveAndClose();
		// adding article in content path 
		
		joomla.PA.filterArticleByNames(AppData.Category_ID.ContentPath_Category,AppData.ContentPath.PCHAPP_ScratchCardContentPath);
		joomla.PA.editArticle(AppData.ContentPath.PCHAPP_ScratchCardContentPath, "publish");
		joomla.PA.selectActionArticles(AppData.GamesID.ScratchCard.PCH$1000HorseshoeHundreds_ID);
		joomla.alias.saveAndClose();
		
		//configuring scratch card article in Joomla
		joomla.PA.filterArticleByNames(AppData.Category_ID.ScratchcardGame, AppData.GamesID.ScratchCard.PCH$1000HorseshoeHundreds_Name);
		joomla.PA.editArticle(AppData.GamesID.ScratchCard.PCH$1000HorseshoeHundreds_Name, "publish");
		joomla.article.setPCHGameClassName("matchthree");
		joomla.alias.saveAndClose();
				
		Log.info("configuring IWE article ");
	
		iwhomepage.IWE_Login();
		
		iwhomepage.naviagateToIWEGiveawayList(AppData.IWEConfiguration.IWEGwayAway.CertificateGiveAway);
		iwhomepage.modifygwyawyDate(generateDate("MMM dd, YYYY h:mm:ss a", "todays"),extendedDateandTime("MMM dd, YYYY h:mm:ss a", 1));
		iwhomepage.setComments("Automation testing");
		iwhomepage.ClickButtonSave();
		
		iwhomepage.naviagateToIWEDeviceList(AppData.IWEConfiguration.IWEDeviceList.PCH_IWDeviceID_4321);
		device.setGiveawayGroup(AppData.IWEConfiguration.IWEGiveawayGroup.GiveAwayGroupforCertificate);
		device.setBusinessUnit("PCHApp");
		device.setTokenCatchAll("remove", "");
		device.setPlayRestrictions("1000", "1000", "");
		iwhomepage.setComments("automation testing ");
		iwhomepage.ClickButtonSave();
		
	}

	@Test(groups = { GroupName.RegFoundation, GroupName.Game })
	public void strory_B_37103_MT2_RemoveRedundantDataFromAppLoad()throws Exception {
		Log.info("Start Executing Jmeter Script");
		CallJmeterBuild(scriptName + ".jmx");

		Log.info("content path values is ::");
		String test =returnPropertyValue("\\numbersData","numbersData");
		Log.info(test);
		
		Log.info("counting cash prize for number match 3 ");
		result =searchCountInString(test, "c25");
		Log.info("total count of numbers is "+result);
		Assert.assertEquals(searchCountInString(test, "c25"), 3);
			
	}
	
	
	@AfterClass
	public void tearDown() throws IOException, Exception {
		closeallwindows();
		endTestCase("End");
	}
}

