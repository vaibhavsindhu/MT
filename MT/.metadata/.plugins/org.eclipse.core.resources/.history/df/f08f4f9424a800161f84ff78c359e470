package com.pch.test.script;

import static com.pch.utilities.Log.Log;
import static com.pch.utilities.Log.endTestCase;
import static com.pch.utilities.Log.startTestCase;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.pch.joomla.configuration.DataPassThrough;
import com.pch.joomla.configuration.JoomlaAdministrator;
import com.pch.utilities.Action_Wrapper;
import com.pch.utilities.GroupName;
import com.pch.utilities.Jmeter_Xml_Parser;

public class B_36414_MT2_All_Apps_SetupJoomlaConfigured_Passthrough_of_Data_JSONFormat extends Action_Wrapper {
	Jmeter_Xml_Parser xp = new Jmeter_Xml_Parser();
	JoomlaAdministrator joomla = new JoomlaAdministrator();
	DataPassThrough DPT = new DataPassThrough();
	String scriptName = this.getClass().getSimpleName();

	String thread[] = { "RF POST REQUEST" };
	String thread1[] = { "Segment GET REQUEST" };
	String thread2[] = { "Passthrough Article Expire" };
	String thread3[] = { "Target URL Missing" };

	String RFtargetURL = "http://centralservices.qa.pch.com/rfapi_v8/Svc/membermanagement.svc/json/api/members/memberdetailwithemailgmt";
	String segmentationURL = "http://centralservices.qa.pch.com/SegmentationApi/svc/segmentation.svc/json/api/membersegments/gmt/d0e5bd33-f389-4b46-bbdb-b6820470ee35";

	@BeforeClass
	public void setup() throws Exception {
		
		startTestCase(scriptName);
		createAccessTokenForAllApp("pchapp");

		openBrowser("chrome");

		joomla.login.login();
		joomla.article.navigateToArticleManager();

	
// Configure Pass through Article for POST.
		joomla.PA.filterArticleByNames("98", "RF Passthrough");
		joomla.PA.editArticle("RF Passthrough", "publish");
		DPT.configureDataPassThrough(RFtargetURL, "POST", "JSON");
		joomla.alias.saveAndClose();
		
	}

	@Test (groups = { GroupName.Regression, GroupName.DPassThrough})
	public void strory_B_36414_MT2_All_Apps_SetupJoomlaConfigured_Passthrough_of_Data_JSONFormat()throws Exception {

// Scenarios: RF POST REQUEST
		enableThread(scriptName + ".jmx", thread);
		Log.info("Executing RF POST REQUEST JmeterThread as precondition.");
		CallJmeterBuild(scriptName + ".jmx");
	
		Assert.assertEquals("false", xp.Xml_Parser(scriptName+".xml"));

// Scenarios: Segment GET REQUEST
		
		// Configure Pass through Article for GET.
		joomla.PA.filterArticleByNames("98", "Segmentation");
		joomla.PA.editArticle("Segmentation", "publish");
		DPT.configureDataPassThrough(segmentationURL, "GET", "JSON");
		joomla.alias.saveAndClose();
		
		enableThread(scriptName + ".jmx", thread1);
		Log.info("Executing Segment GET REQUEST Thread as Valid Scenario.");
		CallJmeterBuild(scriptName + ".jmx");
		
		Assert.assertEquals("false", xp.Xml_Parser(scriptName+".xml"));
		
// 	Scenario: Passthrough Article Expire
		
		// Expire the Pass through article
		joomla.PA.filterArticleByNames("98", "Segmentation");
		joomla.PA.editArticle("Segmentation", "unpublish");
		joomla.alias.saveAndClose();

		enableThread(scriptName + ".jmx", thread2);
		Log.info("Executing Passthrough Article Expire Thread as Valid Scenario.");
		CallJmeterBuild(scriptName + ".jmx");
		Assert.assertEquals("false", xp.Xml_Parser(scriptName+".xml"));
		
// Revert the changes and remove the target URL

		joomla.PA.filterArticleByNames("98", "Segmentation");
		joomla.PA.editArticle("Segmentation", "publish");
		DPT.configureDataPassThrough("", "GET", "JSON");
		joomla.alias.saveAndClose();
	
		enableThread(scriptName + ".jmx", thread3);
		Log.info("Executing Target URL Missing Thread ");
		CallJmeterBuild(scriptName + ".jmx");
		
		Assert.assertEquals("false", xp.Xml_Parser(scriptName+".xml"));
	}

	@AfterClass
	public void tearDown() throws IOException, Exception {
		
// Revert Joomla Configuration
		joomla.PA.filterArticleByNames("98", "Segmentation");
		joomla.PA.editArticle("Segmentation", "publish");
		DPT.configureDataPassThrough(segmentationURL, "GET", "JSON");
		joomla.alias.saveAndClose();
		
		closeallwindows();
		endTestCase("End");
	}
}