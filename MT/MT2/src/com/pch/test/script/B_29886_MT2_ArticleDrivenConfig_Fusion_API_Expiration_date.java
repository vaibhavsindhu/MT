package com.pch.test.script;

import static com.pch.utilities.Log.Log;
import static com.pch.utilities.Log.endTestCase;
import static com.pch.utilities.Log.startTestCase;

import java.io.IOException;
import java.util.Calendar;

import org.openqa.selenium.By;
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

public class B_29886_MT2_ArticleDrivenConfig_Fusion_API_Expiration_date extends Action_Wrapper{

	Jmeter_Xml_Parser xp = new Jmeter_Xml_Parser();
	JoomlaAdministrator joomla = new JoomlaAdministrator();
	String enableThreadList[] = {"Single Expiration Date"};
	String enableThreadList1[]={"1st  Future Date < 2nd future date"};
	String enableThreadList2[]={"1st  Past Date < 2nd future date"};
	String enableThreadList3[]={"1st  future Date > 2nd immediate future date"};
    Calendar now = Calendar.getInstance();
	RFTestPage RF = new RFTestPage();
	String response;
	
	@BeforeClass
	public void setup() throws Exception {
		startTestCase("B_29886_MT2_ArticleDrivenConfig_Fusion_API_Expiration_date");
		createAccessTokenForAllApp("pchapp");

		response = RF.createFullRegUserAPI();
		writeInProprtyFile("Encrypted_Email", getEncryptedData("Access_Token_PCHAPP", RF.getResponseValueAPI(response,ResponseData.Email)));
		writeInProprtyFile("Encrypted_GMT", getEncryptedData("Access_Token_PCHAPP", RF.getResponseValueAPI(response,ResponseData.GlobalMemberToken)));
		copyAndRenameFile();
		
		openBrowser("chrome");
		joomla.login.login();
		joomla.article.navigateToArticleManager();
		joomla.article.search("PCHAPP");
		joomla.article.appConfigFusionAPI_RemoveAllField();
		joomla.article.appConfigFusionAPI_SegmentSourceKey(returnPropertyValue("PCHAPP_Segment_Source_Key"), 1);
		joomla.article.appConfigFusionAPI_LineupId(returnPropertyValue("PCHAPP_Lineup_Id"), 1);
		joomla.article.appConfigFusionAPI_MailId(returnPropertyValue("PCHAPP_Mail_Id"), 1);
		joomla.article.appConfigFusionAPI_clickDateIcon(1);
		joomla.article.appConfigFusionAPI_ExpirationDate(now.get(Calendar.YEAR), now.get(Calendar.MONTH), (now.get(Calendar.DATE)+1));
		joomla.article.saveArticle();
		closeallwindows();
		
		enableThread("B_29886_MT2_ArticleDrivenConfig_Fusion_API_Expiration_date.jmx", enableThreadList);
	}

	@Test(groups = { GroupName.Fusion,GroupName.Regression,"Sprint 15" })
	public void story_B_29886_MT2_ArticleDrivenConfig_Fusion_API_Expiration_date() throws Exception {
		
		/*
		 * Single Expiration Date
		 */
		Log.info("Start Executing Jmeter Script");
		CallJmeterBuild("B_29886_MT2_ArticleDrivenConfig_Fusion_API_Expiration_date.jmx");
		Log.info("calling  jmeter file ");
		Assert.assertEquals("false", xp.Xml_Parser("B_29886_MT2_ArticleDrivenConfig_Fusion_API_Expiration_date.xml"));
		Log.info("Validating assertion ");

		/*
		 * 1st  Future Date < 2nd future date
		 */
		Log.info("1st  Future Date < 2nd future date");
		openBrowser("chrome");
		joomla.login.login();
		joomla.article.navigateToArticleManager();
		joomla.article.search("PCHAPP");
		   
		joomla.article.appConfigFusionAPI_RemoveAllField();
		joomla.article.appConfigFusionAPI_SegmentSourceKey(returnPropertyValue("PCHAPP_Segment_Source_Key"), 1);
		joomla.article.appConfigFusionAPI_LineupId(returnPropertyValue("PCHAPP_Lineup_Id"), 1);
		joomla.article.appConfigFusionAPI_MailId(returnPropertyValue("PCHAPP_Mail_Id"), 1);
		joomla.article.appConfigFusionAPI_clickDateIcon(1);
		joomla.article.appConfigFusionAPI_ExpirationDate(now.get(Calendar.YEAR), now.get(Calendar.MONTH), (now.get(Calendar.DATE)+1));

		joomla.article.appConfigFusionAPI_AddField();
		
		link(MTDriver, By.xpath("//*[@id='toolbar-o_apply']/a"), "click");
		waitForPageToLoad(MTDriver);
		joomla.article.appConfigFusionAPI_SegmentSourceKey("LP999LP", 2);
		joomla.article.appConfigFusionAPI_LineupId("174917",2);
		joomla.article.appConfigFusionAPI_MailId("3666036",2);
		joomla.article.appConfigFusionAPI_clickDateIcon(2);
		joomla.article.appConfigFusionAPI_ExpirationDate((now.get(Calendar.YEAR)+1), now.get(Calendar.MONTH), (now.get(Calendar.DATE)+1));
		joomla.article.saveArticle();
		closeallwindows();

		enableThread("B_29886_MT2_ArticleDrivenConfig_Fusion_API_Expiration_date.jmx", enableThreadList1);
		
		Log.info("Start Executing Jmeter Script");
		CallJmeterBuild("B_29886_MT2_ArticleDrivenConfig_Fusion_API_Expiration_date.jmx");
		Log.info("calling  jmeter file ");
		Assert.assertEquals("false", xp.Xml_Parser("B_29886_MT2_ArticleDrivenConfig_Fusion_API_Expiration_date.xml"));
		Log.info("Validating assertion ");
		
		
		/*
		 * 1st  Past Date < 2nd future date
		 */
		Log.info("1st  Past Date < 2nd future date");
		enableThread("B_29886_MT2_ArticleDrivenConfig_Fusion_API_Expiration_date.jmx", enableThreadList2);

		openBrowser("chrome");
		joomla.login.login();
		joomla.article.navigateToArticleManager();
		joomla.article.search("PCHAPP");
		
		
		joomla.article.appConfigFusionAPI_RemoveAllField();
		joomla.article.appConfigFusionAPI_SegmentSourceKey(returnPropertyValue("PCHAPP_Segment_Source_Key"), 1);
		joomla.article.appConfigFusionAPI_LineupId(returnPropertyValue("PCHAPP_Lineup_Id"), 1);
		joomla.article.appConfigFusionAPI_MailId(returnPropertyValue("PCHAPP_Mail_Id"), 1);
		joomla.article.appConfigFusionAPI_clickDateIcon(1);
		joomla.article.appConfigFusionAPI_ExpirationDate(2014, now.get(Calendar.MONTH), (now.get(Calendar.DATE)));

		joomla.article.appConfigFusionAPI_AddField();
		
		link(MTDriver, By.xpath("//*[@id='toolbar-o_apply']/a"), "click");
		waitForPageToLoad(MTDriver);
		joomla.article.appConfigFusionAPI_SegmentSourceKey("LP999LP", 2);
		joomla.article.appConfigFusionAPI_LineupId("174917",2);
		joomla.article.appConfigFusionAPI_MailId("3666036",2);
		joomla.article.appConfigFusionAPI_clickDateIcon(2);
		joomla.article.appConfigFusionAPI_ExpirationDate((now.get(Calendar.YEAR)+1), now.get(Calendar.MONTH), (now.get(Calendar.DATE)+1));
		joomla.article.saveArticle();
		closeallwindows();
		
		
		
		Log.info("Start Executing Jmeter Script");
		CallJmeterBuild("B_29886_MT2_ArticleDrivenConfig_Fusion_API_Expiration_date.jmx");
		Log.info("calling  jmeter file ");
		Assert.assertEquals("false", xp.Xml_Parser("B_29886_MT2_ArticleDrivenConfig_Fusion_API_Expiration_date.xml"));
		Log.info("Validating assertion ");
		
		
		
		/*
		 * 1st  future Date > 2nd immediate future date
		 */
		enableThread("B_29886_MT2_ArticleDrivenConfig_Fusion_API_Expiration_date.jmx", enableThreadList3);

		openBrowser("chrome");
		joomla.login.login();
		joomla.article.navigateToArticleManager();
		joomla.article.search("PCHAPP");
		
		joomla.article.appConfigFusionAPI_clickDateIcon(1);
		joomla.article.appConfigFusionAPI_ExpirationDate((now.get(Calendar.YEAR)), now.get(Calendar.MONTH), (now.get(Calendar.DATE)+3));
		link(MTDriver, By.xpath("//*[@id='toolbar-o_apply']/a"), "click");
		waitForPageToLoad(MTDriver);
		joomla.article.appConfigFusionAPI_clickDateIcon(2);
		joomla.article.appConfigFusionAPI_ExpirationDate((now.get(Calendar.YEAR)), now.get(Calendar.MONTH), (now.get(Calendar.DATE)+1));
		joomla.article.saveArticle();
		closeallwindows();
		
		Log.info("Start Executing Jmeter Script");
		CallJmeterBuild("B_29886_MT2_ArticleDrivenConfig_Fusion_API_Expiration_date.jmx");
		Log.info("calling  jmeter file ");
		Assert.assertEquals("false", xp.Xml_Parser("B_29886_MT2_ArticleDrivenConfig_Fusion_API_Expiration_date.xml"));
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

