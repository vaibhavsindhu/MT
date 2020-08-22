package com.pch.test.script;

import static com.pch.utilities.Log.Log;
import static com.pch.utilities.Log.endTestCase;
import static com.pch.utilities.Log.startTestCase;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.pch.configuration.testpages.Lotto_Contest_Admin;
import com.pch.configuration.testpages.RFTestPage;
import com.pch.joomla.configuration.JoomlaAdministrator;
import com.pch.joomla.configuration.MidTierApiConfiguration.ServiceURL;
import com.pch.utilities.Action_Wrapper;
import com.pch.utilities.GroupName;
import com.pch.utilities.Jmeter_Xml_Parser;

public class B_33333_MT2_Lotto_Services_SPLIT_lottoGameDetails extends Action_Wrapper {

	Jmeter_Xml_Parser xp = new Jmeter_Xml_Parser();
	RFTestPage RF = new RFTestPage();
	JoomlaAdministrator joomla = new JoomlaAdministrator();
	Lotto_Contest_Admin LCA = new Lotto_Contest_Admin();
    Calendar now = Calendar.getInstance();

    String threadGroup[]= {"Invalid Service URL"};
    
	@BeforeClass
	public void setup() throws Exception {

		startTestCase("B-33333 [MT2] Lotto Services- SPLIT_lottoGameDetails");
	//	createAccessTokenForAllApp();

		openBrowser("joomla");
		joomla.login.login();
		joomla.article.navigateToArticleManager();
		joomla.article.search("$1500 Lotto Ace of Spades");
		joomla.article.selectLottoEngineGame(320);
		joomla.article.saveArticle();
		
		LCA.navigateToLottoAdminContestDetails(320);
		LCA.getContestDetail("End date");
				
		SimpleDateFormat sdf = new SimpleDateFormat("M/d/yyyy");
		Assert.assertTrue(sdf.parse(Generate_TodayDate()).before(sdf.parse(LCA.getContestDetail("End date"))),"Assert End Date of Lotto Engine Game Expired");
		disableThread("B_33333_MT2_Lotto_Services_SPLIT_lottoGameDetails.jmx", threadGroup);
	}

	@Test(groups = {GroupName.Regression, GroupName.Lotto})
	public void story_B_33333_MT2_Lotto_Services_SPLIT_lottoGameDetails() throws Exception {
		Log.info("Start Executing Jmeter Script");
		
		CallJmeterBuild("B_33333_MT2_Lotto_Services_SPLIT_lottoGameDetails.jmx");
		Log.info("calling  jmeter file ");
		Assert.assertEquals("false", xp.Xml_Parser("B_33333_MT2_Lotto_Services_SPLIT_lottoGameDetails.xml"));
		Log.info("Validating assertion ");
		
		joomla.MTAPIConfig.navigateToAPIComponents();
		joomla.MTAPIConfig.servicesUrl("Alter URL", ServiceURL.Lotto_Get_Games_URL);
		joomla.MTAPIConfig.saveAndClose();
		GETclearMemCache("Access_Token_PLAYANDWIN");
		
		enableThread("B_33333_MT2_Lotto_Services_SPLIT_lottoGameDetails.jmx", threadGroup);
		CallJmeterBuild("B_33333_MT2_Lotto_Services_SPLIT_lottoGameDetails.jmx");
		Log.info("calling  jmeter file ");
		Assert.assertEquals("false", xp.Xml_Parser("B_33333_MT2_Lotto_Services_SPLIT_lottoGameDetails.xml"));
		Log.info("Validating assertion ");

	}

	@AfterClass
	public void tearDown() throws IOException, Exception {
		joomla.MTAPIConfig.navigateToAPIComponents();
		joomla.MTAPIConfig.servicesUrl("Valid URL", ServiceURL.Lotto_Get_Games_URL);
		joomla.MTAPIConfig.saveAndClose();
		closeallwindows();
		GETclearMemCache("Access_Token_PLAYANDWIN");
		endTestCase("End");

	}
	
}