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
import com.pch.utilities.AppData;
import com.pch.utilities.GroupName;
import com.pch.utilities.Jmeter_Xml_Parser;

public class B_38436_MT2_PCHAPP_AppLoadfor_UnknownUser_AppLoadPreviouslyDoneByFullREgUser extends Action_Wrapper{

	Jmeter_Xml_Parser xp = new Jmeter_Xml_Parser();
	JoomlaAdministrator joomla = new JoomlaAdministrator();
			
	RFTestPage RF = new RFTestPage();
	String response,GMT,Email;
	String scriptName = this.getClass().getSimpleName();

	String Thread[]={"AppLoadByFullRegUser"};
	String Thread1[]={"AppLoad"};
/*	String Th1[]={"AppLoad2"};
	String Th2[]={"AppLoad3"};*/

	
	//String Thread2[]={"AppLoad game status"};
	
	
	//Database_Configuration DB = new Database_Configuration();

	@BeforeClass
	public void setup() throws Exception {
		startTestCase(scriptName);
		createAccessTokenForAllApp("pchapp");

	
		// Configure Content Path in App Article
		
		openBrowser("chrome");
		joomla.login.login();
		joomla.article.navigateToArticleManager();
	
		
	/*	joomla.PA.filterArticleByNames(AppData.Category_PchApp2.PchApp2_Category, AppData.PchApp2.PCHApp);
		joomla.PA.editArticle(AppData.PchApp2.PCHApp, "publish");
		joomla.PA.featureFieldSelectArticle(AppData.ContentPath.Continuous_ContentPath);
		joomla.alias.saveAndClose();*/
		
		joomla.PA.filterArticleByNames(AppData.Category_ID.ContentPath_Category,AppData.ContentPath.Continuous_ContentPath);
		joomla.PA.editArticle(AppData.ContentPath.Continuous_ContentPath, "publish");
	   // joomla.PA.selectActionArticles(AppData.Games.Continuous.ScoreSubmit1);
		joomla.alias.saveAndClose();
	

		}

	@Test(groups = {GroupName.Game })
	public void strory_B_38436_MT2_PCHAPP_AppLoadfor_UnknownUser_AppLoadPreviouslyDoneByFullREgUser()throws Exception {
		
		response = RF.createFullRegUserAPI();
		Email=RF.getResponseValueAPI(response, ResponseData.Email);
		GMT=RF.getResponseValueAPI(response, ResponseData.GlobalMemberToken);
		writeInProprtyFile("Encrypted_Email", getEncryptedData("Access_Token_PCHAPP", Email));
		writeInProprtyFile("Encrypted_GMT", getEncryptedData("Access_Token_PCHAPP", GMT));
		copyAndRenameFile();
		
		// validating if app load successful by full reg user 
		
		
		enableThread(scriptName+".jmx", Thread);
	
		Log.info("Start Executing Jmeter Script");
		CallJmeterBuild(scriptName + ".jmx");
		Log.info("Validating assertion ");
		Assert.assertEquals("false", xp.Xml_Parser(scriptName + ".xml"));
					
		
		// validating appload by unknown user 

		response = RF.createFullRegUserAPI();
		Email=RF.getResponseValueAPI(response, ResponseData.Email);
		GMT=RF.getResponseValueAPI(response, ResponseData.GlobalMemberToken);
		writeInProprtyFile("Encrypted_Email", getEncryptedData("Access_Token_PCHAPP", Email));
		writeInProprtyFile("Encrypted_GMT", getEncryptedData("Access_Token_PCHAPP", GMT));
		copyAndRenameFile();
		
		enableThread(scriptName+".jmx", Thread1);
	
		Log.info("Start Executing Jmeter Script");
		CallJmeterBuild(scriptName + ".jmx");
		Log.info("Validating assertion ");
		Assert.assertEquals("false", xp.Xml_Parser(scriptName + ".xml"));
		
		
		
		response = RF.createFullRegUserAPI();
		Email=RF.getResponseValueAPI(response, ResponseData.Email);
		GMT=RF.getResponseValueAPI(response, ResponseData.GlobalMemberToken);
		writeInProprtyFile("Encrypted_Email", getEncryptedData("Access_Token_PCHAPP", Email));
		writeInProprtyFile("Encrypted_GMT", getEncryptedData("Access_Token_PCHAPP", GMT));
		copyAndRenameFile();
		
		/*enableThread(scriptName+".jmx", Th1);
	
		Log.info("Start Executing Jmeter Script");
		CallJmeterBuild(scriptName + ".jmx");
		Log.info("Validating assertion ");
		Assert.assertEquals("false", xp.Xml_Parser(scriptName + ".xml"));
		
		
		
		
		response = RF.createFullRegUserAPI();
		Email=RF.getResponseValueAPI(response, ResponseData.Email);
		GMT=RF.getResponseValueAPI(response, ResponseData.GlobalMemberToken);
		writeInProprtyFile("Encrypted_Email", getEncryptedData("Access_Token_PCHAPP", Email));
		writeInProprtyFile("Encrypted_GMT", getEncryptedData("Access_Token_PCHAPP", GMT));
		copyAndRenameFile();
		
		enableThread(scriptName+".jmx", Th2);
	
		Log.info("Start Executing Jmeter Script");
		CallJmeterBuild(scriptName + ".jmx");
		Log.info("Validating assertion ");
		Assert.assertEquals("false", xp.Xml_Parser(scriptName + ".xml"));*/
		
		/*
					

		Log.info("content path values is ::");
		Log.info(returnPropertyValue("\\contentPathData","contentPathData"));
		
		Log.info("Verifying if  added content path present article present at app load");
		Assert.assertTrue(returnPropertyValue("\\contentPathData","contentPathData").contains("Continuous Content Path"),"app load is not successfull as added content path do not exist at  app load");
		Assert.assertTrue(returnPropertyValue("\\contentPathData","contentPathData").contains("Score Submit 1"),"app load is not successfull as added games do not exist at app load ");
		Assert.assertTrue(returnPropertyValue("\\contentPathData","contentPathData").contains("460-"),"app load is not successfull as added content path do not exist at  app load");
		Log.info(" app load is successfull");*/
		
		// validating game status 
		
		/*enableThread(scriptName+".jmx", Thread2);
		
		Log.info("Start Executing Jmeter Script");
		CallJmeterBuild(scriptName + ".jmx");

					

		Log.info("content path values is ::");
		Log.info(returnPropertyValue("\\contentPathData","contentPathData"));
		
		Log.info("Verifying if  added content path present article present at app load");
		Assert.assertFalse(returnPropertyValue("\\contentPathData","contentPathData").contains("status")," game status exist");
		Log.info(" game status is not present and app load is successfull with unknown user ");*/
						
		}
		
	@AfterClass
	public void tearDown() throws IOException, Exception {
		closeallwindows();
		endTestCase("End");
	}
}

