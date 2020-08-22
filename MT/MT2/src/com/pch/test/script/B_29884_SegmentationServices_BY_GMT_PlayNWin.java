package com.pch.test.script;

import static com.pch.utilities.Log.Log;
import static com.pch.utilities.Log.endTestCase;
import static com.pch.utilities.Log.startTestCase;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.pch.configuration.testpages.RFTestPage;
import com.pch.configuration.testpages.SegmentationApi;
import com.pch.configuration.testpages.RFTestPage.ResponseData;
import com.pch.configuration.testpages.SegmentationApi.segmentApiType;
import com.pch.utilities.Action_Wrapper;
import com.pch.utilities.GroupName;
import com.pch.utilities.Jmeter_Xml_Parser;

public class B_29884_SegmentationServices_BY_GMT_PlayNWin extends Action_Wrapper {

	Jmeter_Xml_Parser xp = new Jmeter_Xml_Parser();
	SegmentationApi segment = new SegmentationApi();
	RFTestPage RF = new RFTestPage();
	String scriptName = this.getClass().getSimpleName();
	String response, GMT;

	// Threads Declaration:
	String noSegment[]={"HTTPR-whenUserIsNotPartOfAnySegmentation"};
	String singleSegment[]={"HTTPR-User has single segmentation-GMT"};
	String multipleSegment[]={"HTTPR-User has multiple segmentation-GMT"};
	String singleSegmentWithSName[]={"HTTPR-User has single segmentation"};
	String multipleSegmentWithSName[]={"HTTPR-User has multiple segmentation"};
	
	@BeforeClass
	public void setup() throws Exception {
		
		startTestCase(scriptName);
		createAccessTokenForAllApp("Playandwin");
		
		response=RF.createFullRegUserAPI();
		GMT = RF.getResponseValueAPI(response,ResponseData.GlobalMemberToken);
		writeInProprtyFile("Encrypted_Email", getEncryptedData("Access_Token_PLAYANDWIN", RF.getResponseValueAPI(response,ResponseData.Email)));
		writeInProprtyFile("Encrypted_GMT", getEncryptedData("Access_Token_PLAYANDWIN",GMT));
		copyAndRenameFile();
	}

	@Test(groups = { GroupName.Segmentation,GroupName.Regression })
	public void BacklogItem_B_29884_SegmentationServices_BY_GMT() throws Exception {
		
		enableThread(scriptName + ".jmx", noSegment);
		Log.info("Start Executing Jmeter Script");
		CallJmeterBuild(scriptName+".jmx");
		Log.info("JMX file Called with Thread NoSegment");

		Assert.assertEquals("false", xp.Xml_Parser(scriptName+".xml"));
		Log.info("Assetion Validation Completed");
		
		// Assign Single Segment to the User
		openBrowser("chrome");
		segment.navigateToSegmentationApi();
		segment.selectSegmentMemberType(segmentApiType.SetByGmt);
		segment.setSegmentMembershipByGmtBySegmentCode(GMT, "WLO");
		
		enableThread(scriptName + ".jmx", singleSegment);
	
		Log.info("Start Executing Jmeter Script");
		CallJmeterBuild(scriptName+".jmx");
		Log.info("JMX file Called with Thread SinfgleSegment");

		Assert.assertEquals("false", xp.Xml_Parser(scriptName+".xml"));
		Log.info("Assetion Validation Completed");
		
		// Multiple Segment to the User
		segment.selectSegmentMemberType(segmentApiType.SetByGmt);
		segment.setSegmentMembershipByGmtBySegmentCode(GMT, "AB");
		segment.setSegmentMembershipByGmtBySegmentCode(GMT, "ACM");
		
		enableThread(scriptName + ".jmx", multipleSegment);
		
		Log.info("Start Executing Jmeter Script");
		CallJmeterBuild(scriptName+".jmx");
		Log.info("JMX file Called with Thread MultipleSegment");

		Assert.assertEquals("false", xp.Xml_Parser(scriptName+".xml"));
		Log.info("Assetion Validation Completed");
		
		// Segmentation by Segment Name

		
		response=RF.createFullRegUserAPI();
		GMT = RF.getResponseValueAPI(response,ResponseData.GlobalMemberToken);
		writeInProprtyFile("Encrypted_Email", getEncryptedData("Access_Token_PLAYANDWIN", RF.getResponseValueAPI(response,ResponseData.Email)));
		writeInProprtyFile("Encrypted_GMT", getEncryptedData("Access_Token_PLAYANDWIN",GMT));
		copyAndRenameFile();
		
		segment.selectSegmentMemberType(segmentApiType.SetByGmt);
		segment.setSegmentMembershipByGmtBySegmentName(GMT, "ACTIVE_PLAYWIN_L60");
		
		enableThread(scriptName + ".jmx", singleSegmentWithSName);
		
		Log.info("Start Executing Jmeter Script");
		CallJmeterBuild(scriptName+".jmx");
		Log.info("JMX file Called with Thread singleSegment by Segment Name");

		Assert.assertEquals("false", xp.Xml_Parser(scriptName+".xml"));
		Log.info("Assetion Validation Completed");
	
		//Multiple Segment by Segment Name
		segment.setSegmentMembershipByGmtBySegmentName(GMT, "ACTIVE_SEARCH_L60");
		segment.setSegmentMembershipByGmtBySegmentName(GMT, "ACTIVE_FP_L60");
		
		enableThread(scriptName + ".jmx", multipleSegmentWithSName);
		
		Log.info("Start Executing Jmeter Script");
		CallJmeterBuild(scriptName+".jmx");
		Log.info("JMX file Called with Thread MultiSegment by Segment Name");

		Assert.assertEquals("false", xp.Xml_Parser(scriptName+".xml"));
		Log.info("Assetion Validation Completed");
		
	}

	@AfterClass
	public void tearDown() {
		closeallwindows();
		endTestCase("End");

	}
}
