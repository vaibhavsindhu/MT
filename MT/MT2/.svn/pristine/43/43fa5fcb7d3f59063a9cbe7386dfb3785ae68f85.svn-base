package com.pch.test.script;

import static com.pch.utilities.Log.Log;
import static com.pch.utilities.Log.endTestCase;
import static com.pch.utilities.Log.startTestCase;

import java.io.IOException;
import java.util.Calendar;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.pch.configuration.testpages.RFTestPage;
import com.pch.configuration.testpages.RFTestPage.ResponseData;
import com.pch.joomla.configuration.JoomlaAdministrator;
import com.pch.utilities.Action_Wrapper;
import com.pch.utilities.GroupName;
import com.pch.utilities.Jmeter_Xml_Parser;

public class B_29886_MT2_ArticleDrivenConfig_Fusion_API_Expiration_error_message extends Action_Wrapper{

	Jmeter_Xml_Parser xp = new Jmeter_Xml_Parser();
	JoomlaAdministrator joomla = new JoomlaAdministrator();
    Calendar now = Calendar.getInstance();
	RFTestPage RF = new RFTestPage();
	String response;
	
	@BeforeClass
	public void setup() throws Exception {
		startTestCase("B-29886_MT2_ArticleDrivenConfig_Fusion API  Expiration _error message");
		createAccessTokenForAllApp("pchapp");

		response = RF.createFullRegUserAPI();
		writeInProprtyFile("Encrypted_GMT", getEncryptedData("Access_Token_PCHAPP", RF.getResponseValueAPI(response,ResponseData.GlobalMemberToken)));
		
		copyAndRenameFile();
		openBrowser("chrome");
		joomla.login.login();
		joomla.article.navigateToArticleManager();
		joomla.article.search("PCHAPP");
		joomla.article.appConfigFusionAPI_AddField();
		joomla.article.appConfigFusionAPI_RemoveAllField();
		joomla.article.appConfigFusionAPI_SegmentSourceKey(returnPropertyValue("PCHAPP_Segment_Source_Key"), 1);
		joomla.article.appConfigFusionAPI_LineupId(returnPropertyValue("PCHAPP_Lineup_Id"), 1);
		joomla.article.appConfigFusionAPI_MailId(returnPropertyValue("PCHAPP_Mail_Id"), 1);
		joomla.article.saveArticle();
		closeallwindows();
		
	}

	@Test(groups = { GroupName.Fusion,GroupName.Regression, "Sprint 15" })
	public void storyB_29886_MT2_ArticleDrivenConfig_Fusion_API_Expiration_error_message() throws Exception {
		
		Log.info("Start Executing Jmeter Script");
		CallJmeterBuild("B_29886_MT2_ArticleDrivenConfig_Fusion_API_Expiration_error_message.jmx");
		Log.info("calling  jmeter file ");
		Assert.assertEquals("false", xp.Xml_Parser("B_29886_MT2_ArticleDrivenConfig_Fusion_API_Expiration_error_message.xml"));
		Log.info("Validating assertion ");

		openBrowser("chrome");
		joomla.login.login();
		joomla.article.navigateToArticleManager();
		joomla.article.search("PCHAPP");
		scrollDown();
		joomla.article.appConfigFusionAPI_clickDateIcon(1);
		joomla.article.appConfigFusionAPI_ExpirationDate(2015, now.get(Calendar.MONTH), (now.get(Calendar.DATE)+1));
		joomla.article.saveArticle();
		closeallwindows();
		
		Log.info("Start Executing Jmeter Script");
		CallJmeterBuild("B_29886_MT2_ArticleDrivenConfig_Fusion_API_Expiration_error_message.jmx");
		Log.info("calling  jmeter file ");
		Assert.assertEquals("false", xp.Xml_Parser("B_29886_MT2_ArticleDrivenConfig_Fusion_API_Expiration_error_message.xml"));
		Log.info("Validating assertion ");

	}

	@AfterClass
	public void tearDown() throws IOException, Exception {
		openBrowser("chrome");
		joomla.login.login();
		joomla.article.navigateToArticleManager();
		joomla.article.search("PCHAPP");
		joomla.article.appConfigFusionAPI_RemoveAllField();
		joomla.article.appConfigFusionAPI_SegmentSourceKey(returnPropertyValue("PCHAPP_Segment_Source_Key"), 1);
		joomla.article.appConfigFusionAPI_LineupId(returnPropertyValue("PCHAPP_Lineup_Id"), 1);
		joomla.article.appConfigFusionAPI_MailId(returnPropertyValue("PCHAPP_Mail_Id"), 1);
		joomla.article.appConfigFusionAPI_clickDateIcon(1);
		joomla.article.appConfigFusionAPI_ExpirationDate(now.get(Calendar.YEAR), (now.get(Calendar.MONTH)+1), (now.get(Calendar.DATE)));
		joomla.article.saveArticle();
		closeallwindows();
		endTestCase("End");

	}
}

