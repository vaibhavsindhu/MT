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

public class B_35177_MT2_IswinnerService_support_for_newInstantWinCCKform extends Action_Wrapper {
	Jmeter_Xml_Parser xp = new Jmeter_Xml_Parser();
	JoomlaAdministrator joomla = new JoomlaAdministrator();
	RFTestPage RF = new RFTestPage();
	Database_Configuration DB = new Database_Configuration();
	HomePage iwhomepage = new HomePage();
	DevicesPage device = new DevicesPage();

	String thread[] = { "isWinner - GameID" };
	String thread1[] = { "isWinner - DeviceID & Access Key" };
	String thread2[] = {"Precondition - AppLoad"};

	String scriptName = this.getClass().getSimpleName();
	String GMT, response;

	@BeforeClass
	public void setup() throws Exception {
		
		startTestCase(scriptName);
		
		createAccessTokenForAllApp("pchapp");
		response = RF.createFullRegUserAPI();
		writeInProprtyFile("Encrypted_Email", getEncryptedData("Access_Token_PCHAPP",RF.getResponseValueAPI(response, ResponseData.Email)));
		writeInProprtyFile("Encrypted_GMT", getEncryptedData("Access_Token_PCHAPP",RF.getResponseValueAPI(response, ResponseData.GlobalMemberToken)));
		copyAndRenameFile();

		openBrowser("chrome");

// Configure GWYAWAY group/Token Catch All
		Log.info("configuring IWE article ");
		iwhomepage.IWE_Login();
		
		iwhomepage.naviagateToIWEGiveawayList(AppData.IWEConfiguration.IWEGwayAway.CertificateGiveAway);
		iwhomepage.modifygwyawyDate(generateDate("MMM dd, YYYY h:mm:ss a", "todays"),extendedDateandTime("MMM dd, YYYY h:mm:ss a", 1));
		iwhomepage.setComments("Automation testing");
		iwhomepage.ClickButtonSave();
				
// Configure Gwyaway 
		iwhomepage.naviagateToIWEDeviceList(AppData.IWEConfiguration.IWEDeviceList.PCH_IWDeviceID_4321);
		device.setGiveawayGroup(AppData.IWEConfiguration.IWEGiveawayGroup.GiveAwayGroupforCertificate);
		device.setBusinessUnit("PCHApp");
		device.setTokenCatchAll("remove", "");
		device.setPlayRestrictions("1000", "1000", "");
		iwhomepage.setComments("automation testing ");
		iwhomepage.ClickButtonSave();

		joomla.login.login();
		joomla.article.navigateToArticleManager();

// Configure Prize Articles
		joomla.PA.filterArticleByNames(AppData.Category_ID.InstantWin, AppData.GameName.InstantWin.PCHAPPIWGame);
		joomla.PA.editArticle(AppData.GameName.InstantWin.PCHAPPIWGame, "publish");
		joomla.PA.selectPrizeConfigurations("IW", "423");
		joomla.alias.saveAndClose();

// Precondition - Game Load After ContentTree Change
		enableThread(scriptName + ".jmx", thread2);
		Log.info("Executing GameLoad JmeterThread as precondition.");
		CallJmeterBuild(scriptName + ".jmx");
	}

	@Test (groups = { GroupName.Regression, GroupName.InstantWin })
	public void strory_B_35177_MT2_IswinnerService_support_for_newInstantWinCCKform()throws Exception {

// Scenarios: isWinner - GameID
		enableThread(scriptName + ".jmx", thread);
		Log.info("Executing isWinner Thread as Valid Scenario.");
		CallJmeterBuild(scriptName + ".jmx");
		
		Assert.assertEquals("false", xp.Xml_Parser(scriptName+".xml"));
		
// 	Scenario: isWinner - DeviceID & Access Key

		enableThread(scriptName + ".jmx", thread1);
		Log.info("Executing GameStart Thread for IW already Played.");
		CallJmeterBuild(scriptName + ".jmx");
		
		Assert.assertEquals("false", xp.Xml_Parser(scriptName+".xml"));
	}

	@AfterClass
	public void tearDown() throws IOException, Exception {
	closeallwindows();
	endTestCase("End");
	}
}