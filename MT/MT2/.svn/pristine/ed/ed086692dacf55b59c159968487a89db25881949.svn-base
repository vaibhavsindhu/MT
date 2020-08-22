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
import com.pch.joomla.configuration.JoomlaAdministrator;
import com.pch.utilities.Action_Wrapper;
import com.pch.utilities.GroupName;
import com.pch.utilities.Jmeter_Xml_Parser;

public class B_29907_MT2_TokenBankServices_TokenBalance_MiniRegUser extends Action_Wrapper {
	Jmeter_Xml_Parser xp = new Jmeter_Xml_Parser();
	RFTestPage RF = new RFTestPage();
	JoomlaAdministrator joomla = new JoomlaAdministrator();
	String accessToken, response;
	String scriptName = "B_29907_MT2_TokenBankServices_TokenBalance_MiniRegUser";
	String threadGroup[]={"Mini Reg Case without Token"};
	String threadGroup1[]={"Mini Reg With Token"};
	String threadGroup2[]={"Token Credit"};
	String threadGroup3[]={"Article Unpublished"};
	
	@BeforeClass
	public void setup() throws Exception {
		
		startTestCase("B_29907_MT2_TokenBankServices_TokenBalance_MiniRegUser");
		createAccessTokenForAllApp("pchapp");
		
		response = RF.createMiniRegUserAPI();
		writeInProprtyFile("Encrypted_Email", getEncryptedData("Access_Token_PCHAPP", RF.getResponseValueAPI(response,ResponseData.Email)));
		writeInProprtyFile("Encrypted_GMT", getEncryptedData("Access_Token_PCHAPP", RF.getResponseValueAPI(response,ResponseData.GlobalMemberToken)));

		copyAndRenameFile();
		enableThread(scriptName+".jmx", threadGroup);
	}

	@Test(groups = { GroupName.TokenBank, GroupName.Regression })
	public void story_B_29907_MT2_TokenBankServices_TokenBalance_MiniRegUser() throws Exception {
		Log.info("Start Executing Jmeter Script");
		
		CallJmeterBuild(scriptName+".jmx");
		Log.info("calling  jmeter file ");

		Assert.assertEquals("false", xp.Xml_Parser(scriptName+".xml"));
		Log.info("Validating Assertion for WithOut Token balance ");
		
		// Credit Some token and Make Token balance API call 

		Log.info("calling Token Credit API to Credit Some token ");
		enableThread(scriptName+".jmx", threadGroup2);
		CallJmeterBuild(scriptName+".jmx");
		
		// Make Token Balance API call
		Log.info("calling Token Balance API to Validate the Token Credit");
		enableThread(scriptName+".jmx", threadGroup1);
		CallJmeterBuild(scriptName+".jmx");
				
		// Negative Scenarios for Unpublished App Article
		enableThread(scriptName+".jmx", threadGroup3);
		
		openBrowser("joomla");
		joomla.login.login();
		joomla.article.navigateToArticleManager();
		joomla.article.selectcategoryDropDown("AppConfig");
		joomla.article.search("PCHAPP");
		joomla.article.appConfArticleStatus("unpublish");
		joomla.article.saveArticle();

		GETclearMemCache("Access_Token_PCHAPP");
		
		CallJmeterBuild(scriptName+".jmx");
		Log.info("=======  Calling  jmeter file for Unpublished Article Scenarios =======");

		Assert.assertEquals("false", xp.Xml_Parser(scriptName+".xml"));
		Log.info("Validating assertion ");
		
	}

	@AfterClass
	public void tearDown() throws IOException, Exception {

		joomla.article.navigateToArticleManager();
		joomla.article.selectcategoryDropDown("AppConfig");
		joomla.article.search("PCHAPP");
		joomla.article.appConfArticleStatus("publish");
		joomla.article.saveArticle();
		closeallwindows();
		GETclearMemCache("Access_Token_PCHAPP");
		endTestCase("End");

	}
}