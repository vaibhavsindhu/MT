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
import com.pch.joomla.configuration.BonusTriggers;
import com.pch.joomla.configuration.JoomlaAdministrator;
import com.pch.joomla.configuration.MetaActionBonusArticle;
import com.pch.utilities.Action_Wrapper;
import com.pch.utilities.Database_Configuration;
import com.pch.utilities.GroupName;
import com.pch.utilities.Jmeter_Xml_Parser;

public class B_35881_MT2_PCHApp_MetaActionBonusGameSupport_DaysInteract_ContinuosGame extends Action_Wrapper {
	JoomlaAdministrator joomla = new JoomlaAdministrator();
	MetaActionBonusArticle metaBonus = new MetaActionBonusArticle();
	Database_Configuration DB = new Database_Configuration();
	BonusTriggers Btrigger = new BonusTriggers();
	
	String GMT,response;
	String appLoadDay1[]={"AppLoad - days1"};
	String appLoadDay2[]={"AppLoad - days2"};
	String bnsSubmit2secondDay[]={"BonusSubmit2 - 2nd Day"};
	String bnsSubmit1Day[]={"BonusSubmit -1st Day"};
	String bnsSubmit2[]={"BonusSubmit2 -1st Day"};
	String bnsSubmit2Day[]={"BonusSubmit - 2nd Day"};
	String bnsSubmit3Day[]={"BonusSubmit - 3rd Day"};
	
	RFTestPage RF = new RFTestPage();
	Jmeter_Xml_Parser xp = new Jmeter_Xml_Parser();
	String jmeterScriptName = "B_35881_MT2_PCHApp_MetaActionBonusGameSupport_DaysInteract_ContinuosGame";

	
	@BeforeClass
	public void setup() throws Exception {
		
		startTestCase("B_35881_MT2_PCHApp_MetaActionBonusGameSupport_DaysInteract_ContinuosGame");
		createAccessTokenForAllApp("pchapp");

		// Configure Bonus Article and Bonus Reward
		
		openBrowser("joomla");
		joomla.login.login();
		joomla.article.navigateToArticleManager();
		joomla.CG.configureContinuesGame("Mega Token Madness bonus", 2, "3000", 1, "Mega Token Madness");
		joomla.CG.configureTokePayablearticle("Mega Token Madness", "150","1000","200","2000");
		
	
		//Configure Meta Action Bonus in PCH App article
		joomla.PA.filterArticleByNames("99", "PCH App");
		joomla.PA.editArticle("PCH App", "publish");
		joomla.PA.bonusActionArticle("Mega Token Madness bonus", "Days_Interact");
	//	joomla.PA.bonusActionArticlesTrigger("Days_Interact", 4);
	//	joomla.PA.bonusActionArticlesType("interact", 4);
	//	joomla.PA.bonusActionArticlesTarget("days", 4);
		joomla.alias.saveAndClose();
		
		//Configure Bonus Trigger Frequency as All time.
		joomla.PA.filterArticleByNames("57", "Days_Interact");
		joomla.PA.editArticle("Days_Interact", "publish");
		Btrigger.configureTriggerOptions("interact", "days", "daily");
		joomla.alias.saveAndClose();
		
		//Create Full Reg User
		response=RF.createFullRegUserAPI();
		GMT = RF.getResponseValueAPI(response,ResponseData.GlobalMemberToken);
		writeInProprtyFile("Encrypted_Email", getEncryptedData("Access_Token_PCHAPP", RF.getResponseValueAPI(response,ResponseData.Email)));
		writeInProprtyFile("Encrypted_GMT", getEncryptedData("Access_Token_PCHAPP",GMT));
		copyAndRenameFile();
	}
	
	@Test(groups = {GroupName.PCHApp ,GroupName.Regression})
	public void story_B_35881_MT2_PCHApp_MetaActionBonusGameSupport_DaysInteract_ContinuosGame() throws Exception {

		// First App Load
		enableThread(jmeterScriptName+".jmx", appLoadDay1);
		Log.info("Start Executing Jmeter Script");
		
		CallJmeterBuild(jmeterScriptName+".jmx");
		Log.info("Calling  Jmeter file ");
		
		Assert.assertEquals("false",xp.Xml_Parser(jmeterScriptName+".xml")); 
		Log.info("Validating assertion ");
		
		//Repeat Visit Days Validation in "phpctrl_app_bonus_items_daily" table
		Assert.assertTrue(DB.phpctrl_app_bonus_items_daily(GMT,"get Bonus data").contains("\"repeatVisitDays\":1"));
		
		//******* Thread for Bonus Submit
		enableThread(jmeterScriptName+".jmx", bnsSubmit1Day);
		Log.info("Start Executing Jmeter Script");
		  
		CallJmeterBuild(jmeterScriptName+".jmx"); 
		Log.info("Calling  Jmeter file ");
		 
		Assert.assertEquals("false",xp.Xml_Parser(jmeterScriptName+".xml")); 
		Log.info("Validating assertion ");
		
		//Bonus status validation from Bonus Item table
		Assert.assertTrue(DB.phpctrl_app_bonus_items(GMT,"get Bonus data").contains("{\"275\":{\"status\":2"));
		//Repeat Visit Days Validation in "phpctrl_app_bonus_items_daily" table
		Assert.assertTrue(DB.phpctrl_app_bonus_items_daily(GMT,"get Bonus data").contains("\"repeatVisitDays\":1"));

		
		//******* Again Bonus Submit for Continuous game as Daily Bonus to get Status as 3
		enableThread(jmeterScriptName+".jmx", bnsSubmit2);
		Log.info("Start Executing Jmeter Script");
		  
		CallJmeterBuild(jmeterScriptName+".jmx"); 
		Log.info("Calling  Jmeter file ");
		 
		Assert.assertEquals("false",xp.Xml_Parser(jmeterScriptName+".xml")); 
		Log.info("Validating assertion ");
		
		//Bonus status validation from Bonus Item table
		Assert.assertTrue(DB.phpctrl_app_bonus_items(GMT,"get Bonus data").contains("{\"275\":{\"status\":3"));
		
		// ************* App Load after Completing the Bonus ***********************
		enableThread(jmeterScriptName+".jmx", appLoadDay1);
		Log.info("Start Executing Jmeter Script");
				
		CallJmeterBuild(jmeterScriptName+".jmx");
		Log.info("Calling  Jmeter file ");
				
		Assert.assertEquals("false",xp.Xml_Parser(jmeterScriptName+".xml")); 
		Log.info("Validating assertion ");
				
		//Repeat Visit Days Validation in "phpctrl_app_bonus_items_daily" table
		Assert.assertTrue(DB.phpctrl_app_bonus_items_daily(GMT,"get Bonus data").contains("\"repeatVisitDays\":1"));
		
		// change the "date_played" to yesterday in Both the Table
		DB.phpctrl_app_bonus_items_daily(GMT, "update date played");
		DB.phpctrl_app_bonus_items(GMT, "update date played");
		
	
		// Second Visit App Load
		enableThread(jmeterScriptName+".jmx", appLoadDay2);
		Log.info("Start Executing Jmeter Script");
		
		CallJmeterBuild(jmeterScriptName+".jmx");
		Log.info("Calling  Jmeter file ");
		
		Assert.assertEquals("false",xp.Xml_Parser(jmeterScriptName+".xml")); 
		Log.info("Validating assertion ");
		
		//Repeat Visit Days Validation in "phpctrl_app_bonus_items_daily" table
		Assert.assertTrue(DB.phpctrl_app_bonus_items_daily(GMT,"get Bonus data").contains("\"repeatVisitDays\":2"));
		
		
		//******* Thread for Bonus Submit -2
		enableThread(jmeterScriptName+".jmx", bnsSubmit2Day);
		Log.info("Start Executing Jmeter Script");
				  
		CallJmeterBuild(jmeterScriptName+".jmx"); 
		Log.info("Calling  Jmeter file ");
				 
		Assert.assertEquals("false",xp.Xml_Parser(jmeterScriptName+".xml")); 
		Log.info("Validating assertion ");
				
		//Bonus status validation from Bonus Item table
		Assert.assertTrue(DB.phpctrl_app_bonus_items(GMT,"get Bonus data").contains("{\"275\":{\"status\":2"));
		//Repeat Visit Days Validation in "phpctrl_app_bonus_items_daily" table
		Assert.assertTrue(DB.phpctrl_app_bonus_items_daily(GMT,"get Bonus data").contains("\"repeatVisitDays\":2"));
		
		
		
		//******* Again Bonus Submit for Continuous game as Daily Bonus to get Status as 3
		enableThread(jmeterScriptName+".jmx", bnsSubmit2secondDay);
		Log.info("Start Executing Jmeter Script");
		  
		CallJmeterBuild(jmeterScriptName+".jmx"); 
		Log.info("Calling  Jmeter file ");
		 
		Assert.assertEquals("false",xp.Xml_Parser(jmeterScriptName+".xml")); 
		Log.info("Validating assertion ");
		
		//Bonus status validation from Bonus Item table
		Assert.assertTrue(DB.phpctrl_app_bonus_items(GMT,"get Bonus data").contains("{\"275\":{\"status\":3"));
		
		// ************* App Load after Completing the Bonus ***********************
		enableThread(jmeterScriptName+".jmx", appLoadDay2);
		Log.info("Start Executing Jmeter Script");
				
		CallJmeterBuild(jmeterScriptName+".jmx");
		Log.info("Calling  Jmeter file ");
				
		Assert.assertEquals("false",xp.Xml_Parser(jmeterScriptName+".xml")); 
		Log.info("Validating assertion ");
				
		//Repeat Visit Days Validation in "phpctrl_app_bonus_items_daily" table
		Assert.assertTrue(DB.phpctrl_app_bonus_items_daily(GMT,"get Bonus data").contains("\"repeatVisitDays\":2"));
				
	}
	

	@AfterClass
	public void tearDown() throws IOException, Exception {

		endTestCase("End");
		closeallwindows();
	}
}