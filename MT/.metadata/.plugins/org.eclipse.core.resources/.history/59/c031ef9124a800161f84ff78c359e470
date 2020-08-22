package com.pch.test.script;

import static com.pch.utilities.Log.Log;
import static com.pch.utilities.Log.endTestCase;
import static com.pch.utilities.Log.startTestCase;
//import com.pch.utilities.AppData;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.pch.configuration.testpages.RFTestPage;
import com.pch.joomla.configuration.JoomlaAdministrator;
import com.pch.utilities.Action_Wrapper;
import com.pch.utilities.AppData;
import com.pch.utilities.GroupName;
import com.pch.utilities.Jmeter_Xml_Parser;

public class B_38436_MT2_PCHAPP_ApplaodforUnknownUser_ClientVersionPlatform extends Action_Wrapper{

	Jmeter_Xml_Parser xp = new Jmeter_Xml_Parser();
	JoomlaAdministrator joomla = new JoomlaAdministrator();
			
	RFTestPage RF = new RFTestPage();
	String response,GMT,Email;
	String scriptName = this.getClass().getSimpleName();
	
	String Thread1[]={"AppLoad"};
	String Thread2[]={"AppLoad game status"};


	@BeforeClass
	public void setup() throws Exception {
		startTestCase(scriptName);
		createAccessTokenForAllApp("pchapp");

	
		openBrowser("chrome");
		joomla.login.login();
		joomla.article.navigateToArticleManager();
		
// Configure Content Path in App Article
		
		joomla.PA.filterArticleByNames(AppData.Category_ID.PchApp2_Category, AppData.PchApp2.PCHApp_Android_20);
		joomla.PA.editArticle(AppData.PchApp2.PCHApp_Android_20, "publish");
		joomla.PA.featureFieldSelectArticle(AppData.ContentPath.Continuous_ContentPath);
		joomla.alias.saveAndClose();
		// ensuring if content path article is published and adding article to content path 
		joomla.PA.filterArticleByNames(AppData.Category_ID.ContentPath_Category,AppData.ContentPath.Continuous_ContentPath);
		joomla.PA.editArticle(AppData.ContentPath.Continuous_ContentPath, "publish");
	    joomla.PA.selectActionArticles(AppData.GamesID.SBG.ScoreSubmit1);
		joomla.alias.saveAndClose();
		
	
	}

	@Test(groups = {GroupName.Game })
	public void strory_B_38436_MT2_PCHAPP_AppLoadfor_UnknownUser()throws Exception {
		
		
		
		// validating if app load successful 
		
		enableThread(scriptName+".jmx", Thread1);
	
		Log.info("Start Executing Jmeter Script");
		CallJmeterBuild(scriptName + ".jmx");
		Log.info("Validating assertion ");
		Assert.assertEquals("false", xp.Xml_Parser(scriptName + ".xml"));
					

		Log.info("content path values is ::");
		Log.info(returnPropertyValue("\\contentPathData","contentPathData"));
		
		Log.info("Verifying if  added content path present article present at app load");
		Assert.assertTrue(returnPropertyValue("\\contentPathData","contentPathData").contains("Continuous Content Path"),"app load is not successfull as added content path do not exist at  app load");
		Assert.assertTrue(returnPropertyValue("\\contentPathData","contentPathData").contains("458-"),"app load is not successfull as added content path do not exist at  app load");
		Assert.assertTrue(returnPropertyValue("\\contentPathData","contentPathData").contains("Score Submit 1"),"app load is not successfull as added games do not exist at app load ");
		Log.info(" app load is successfull");
		
		// validating game status 
		
		enableThread(scriptName+".jmx", Thread2);
		
		Log.info("Start Executing Jmeter Script");
		CallJmeterBuild(scriptName + ".jmx");

		Log.info("content path values is ::");
		Log.info(returnPropertyValue("\\contentPathData","contentPathData"));
		
		Log.info("Verifying if  added content path present article present at app load");
		Assert.assertFalse(returnPropertyValue("\\contentPathData","contentPathData").contains("status")," game status exist");
		Log.info(" game status is not present and app load is successfull with unknown user ");
		
		
		}
		
	@AfterClass
	public void tearDown() throws IOException, Exception {
		closeallwindows();
		endTestCase("End");
	}
}

