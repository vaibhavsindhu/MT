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


public class B_38435_MT2_PCHApp_AppIdValidation_Endpoint extends Action_Wrapper{

	Jmeter_Xml_Parser xp = new Jmeter_Xml_Parser();
	JoomlaAdministrator joomla = new JoomlaAdministrator();
			
	RFTestPage RF = new RFTestPage();
	String response,GMT,Email;
	String scriptName = this.getClass().getSimpleName();
	String valid[]={"Valid AppID"};
	String INvalid[]={"InValid AppID"};
	String appLoad[]={"Precondition - AppLoad"};
	
	
	@BeforeClass
	public void setup() throws Exception {
		startTestCase(scriptName);
		createAccessTokenForAllApp("pchapp");

		//Create Full Reg User
		response=RF.createFullRegUserAPI();
		GMT = RF.getResponseValueAPI(response,ResponseData.GlobalMemberToken);
		writeInProprtyFile("Encrypted_Email", getEncryptedData("Access_Token_PCHAPP", RF.getResponseValueAPI(response,ResponseData.Email)));
		writeInProprtyFile("Encrypted_GMT", getEncryptedData("Access_Token_PCHAPP",GMT));
		writeInProprtyFile("appid", "460-"+generateDate("yyyyMMddhh", "todays"));
		copyAndRenameFile();

		openBrowser("chrome");
		joomla.login.login();
		joomla.article.navigateToArticleManager();
		 
		// Publish App Article
		joomla.PA.filterArticleByNames(AppData.Category_ID.PchApp2_Category,AppData.PchApp2.PCHApp);
		joomla.PA.editArticle(AppData.PchApp2.PCHApp, "publish");
		joomla.alias.saveAndClose();
		
		enableThread(scriptName + ".jmx", valid);
	}

	@Test(groups = {GroupName.Regression, GroupName.Game})
	public void strory_B_38435_MT2_PCHApp_AppIdValidation_Endpoint()throws Exception {

		Log.info("Start Executing Jmeter Script");
		CallJmeterBuild(scriptName + ".jmx");
		Log.info("Validating assertion ");
		Assert.assertEquals("false", xp.Xml_Parser(scriptName + ".xml"));
		
		// UNPublish App Article
		
		joomla.PA.filterArticleByNames(AppData.Category_ID.PchApp2_Category,AppData.PchApp2.PCHApp);
		joomla.PA.editArticle(AppData.PchApp2.PCHApp, "unpublish");
		joomla.alias.saveAndClose();
		
		enableThread(scriptName + ".jmx", INvalid);
		
		Log.info("Start Executing Jmeter Script");
		CallJmeterBuild(scriptName + ".jmx");
		Log.info("Validating assertion ");
		Assert.assertEquals("false", xp.Xml_Parser(scriptName + ".xml"));
		
		// App Load Call
		
		// Publish App Article
	
		joomla.PA.filterArticleByNames(AppData.Category_ID.PchApp2_Category,AppData.PchApp2.PCHApp);
		joomla.PA.editArticle(AppData.PchApp2.PCHApp, "publish");
		joomla.alias.saveAndClose();
				
		enableThread(scriptName + ".jmx", appLoad);
		Log.info("Start Executing Jmeter Script");
		CallJmeterBuild(scriptName + ".jmx");
		Log.info("Validating assertion ");
		Assert.assertEquals("false", xp.Xml_Parser(scriptName + ".xml"));		
		
		enableThread(scriptName + ".jmx", valid);
		Log.info("Start Executing Jmeter Script");
		CallJmeterBuild(scriptName + ".jmx");
		Log.info("Validating assertion ");
		Assert.assertEquals("false", xp.Xml_Parser(scriptName + ".xml"));
	
		
		// App Id with Future Date
		writeInProprtyFile("appid", "460-"+generateDate("yyyyMMddhh", "tomorrow"));
		copyAndRenameFile();
		enableThread(scriptName + ".jmx", INvalid);
		
		Log.info("Start Executing Jmeter Script");
		CallJmeterBuild(scriptName + ".jmx");
		Log.info("Validating assertion ");
		Assert.assertEquals("false", xp.Xml_Parser(scriptName + ".xml"));
		
		// Invalid App Id with Todays Date
		writeInProprtyFile("appid", "123-"+generateDate("yyyyMMddhh", "todays"));
		copyAndRenameFile();
		enableThread(scriptName + ".jmx", INvalid);
		
		Log.info("Start Executing Jmeter Script");
		CallJmeterBuild(scriptName + ".jmx");
		Log.info("Validating assertion ");
		Assert.assertEquals("false", xp.Xml_Parser(scriptName + ".xml"));
	
		// Invalid format
		writeInProprtyFile("appid", "123"+generateDate("yyyyMMddhh", "todays"));
		copyAndRenameFile();
		enableThread(scriptName + ".jmx", INvalid);
		
		Log.info("Start Executing Jmeter Script");
		CallJmeterBuild(scriptName + ".jmx");
		Log.info("Validating assertion ");
		Assert.assertEquals("false", xp.Xml_Parser(scriptName + ".xml"));
		
		// App Id with yesterdays Date
		writeInProprtyFile("appid", "460-"+generateDate("yyyyMMddhh", "yesterdays"));
		copyAndRenameFile();
		enableThread(scriptName + ".jmx", INvalid);
		
		Log.info("Start Executing Jmeter Script");
		CallJmeterBuild(scriptName + ".jmx");
		Log.info("Validating assertion ");
		Assert.assertEquals("false", xp.Xml_Parser(scriptName + ".xml"));
		
	}
		
	@AfterClass
	public void tearDown() throws IOException, Exception {
		closeallwindows();
		endTestCase("End");
	}
}


