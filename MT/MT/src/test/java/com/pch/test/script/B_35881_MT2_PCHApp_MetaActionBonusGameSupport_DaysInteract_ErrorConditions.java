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

public class B_35881_MT2_PCHApp_MetaActionBonusGameSupport_DaysInteract_ErrorConditions extends Action_Wrapper {
	JoomlaAdministrator joomla = new JoomlaAdministrator();
	MetaActionBonusArticle metaBonus = new MetaActionBonusArticle();
	Database_Configuration DB = new Database_Configuration();
	BonusTriggers Btrigger = new BonusTriggers();
	
	String GMT,response;
	String threadGroup[]={"BonusSubmit-First"};
	String threadGroup1[]={"BonusSubmit-Second"};
//	String threadGroup2[]={"BonusSubmit- Friends as -1"};
	String threadGroup3[]={"BonusSubmit-WrongGameID"};
	String threadGroup4[]={"BonusSubmit- TokenMultiplier as -Ive"};
	String threadGroup5[]={"BonusSubmit-TokenMultiplier as 0"};
	String threadGroup6[]={"BonusSubmit-Max Entry allowed 0"};
	String threadGroup7[]={"BonusSubmit- Max Entry allowed as -1"};
	String threadGroup8[]={"BonusSubmit-Missing ClassName"};
	String threadGroup9[]={"BonusSubmit-UnPublished Bonus Article"};
	
	
	RFTestPage RF = new RFTestPage();
	Jmeter_Xml_Parser xp = new Jmeter_Xml_Parser();
	String jmeterScriptName = "B_35881_MT2_PCHApp_MetaActionBonusGameSupport_DaysInteract_ErrorConditions";

	
	@BeforeClass
	public void setup() throws Exception {
		
		startTestCase("B_35881_MT2_PCHApp_MetaActionBonusGameSupport_DaysInteract_ErrorConditions");
		createAccessTokenForAllApp("pchapp");

		// Configure Bonus Article and Bonus Reward
		
		openBrowser("joomla");
		joomla.login.login();
		joomla.article.navigateToArticleManager();
		joomla.PA.filterArticleByNames("14", "Bonus Reward for Daily Return Bonus");
		joomla.PA.editArticle("Bonus Reward for Daily Return Bonus", "publish");
		metaBonus.configureBonusAward("1000", "2", "5000");
		joomla.alias.saveAndClose();
		
		//Configure Meta Action Bonus in PCH App article
		joomla.PA.filterArticleByNames("99", "PCH App");
		joomla.PA.editArticle("PCH App", "publish");
		joomla.PA.bonusActionArticle("Bonus Reward for Daily Return Bonus", "Days_Interact");
	/*	joomla.PA.bonusActionArticlesTrigger("Days_Interact", 4);
		joomla.PA.bonusActionArticlesType("interact", 4);
		joomla.PA.bonusActionArticlesTarget("days", 4);*/
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
	public void story_B_35881_MT2_PCHApp_MetaActionBonusGameSupport_DaysInteract_ErrorConditions() throws Exception {

		//******* First Thread for Bonus Submit
		enableThread(jmeterScriptName+".jmx", threadGroup);
		Log.info("Start Executing Jmeter Script");
		  
		CallJmeterBuild(jmeterScriptName+".jmx"); 
		Log.info("Calling  Jmeter file ");
		 
		Assert.assertEquals("false",xp.Xml_Parser(jmeterScriptName+".xml")); 
		Log.info("Validating assertion ");
		
		//Bonus status validation from Bonus Item table
		Assert.assertTrue(DB.phpctrl_app_bonus_items(GMT,"get Bonus data").contains("{\"27\":{\"status\":3,\"data\":{\"buid\":\"53\"}}}"));
		
		//***** Second Bonus Submit with Same User
		enableThread(jmeterScriptName+".jmx", threadGroup1);
		Log.info("Start Executing Jmeter Script");
		  
		CallJmeterBuild(jmeterScriptName+".jmx"); 
		Log.info("Calling  Jmeter file ");
		 
		Assert.assertEquals("false",xp.Xml_Parser(jmeterScriptName+".xml")); 
		Log.info("Validating assertion ");
		
		response=RF.createFullRegUserAPI();
		GMT = RF.getResponseValueAPI(response,ResponseData.GlobalMemberToken);
		writeInProprtyFile("Encrypted_Email", getEncryptedData("Access_Token_PCHAPP", RF.getResponseValueAPI(response,ResponseData.Email)));
		writeInProprtyFile("Encrypted_GMT", getEncryptedData("Access_Token_PCHAPP",GMT));
		copyAndRenameFile();
		
		//***** Bonus Submit with wrong game id in the Request
		enableThread(jmeterScriptName+".jmx", threadGroup3);
		Log.info("Start Executing Jmeter Script");
		  
		CallJmeterBuild(jmeterScriptName+".jmx"); 
		Log.info("Calling  Jmeter file ");
		 
		Assert.assertEquals("false",xp.Xml_Parser(jmeterScriptName+".xml")); 
		Log.info("Validating assertion ");
		
		//***** Bonus Submit with Token Multiplier as -1 in Bonus Article
		joomla.article.navigateToArticleManager();
		joomla.PA.filterArticleByNames("14", "Bonus Reward for Daily Return Bonus");
		joomla.PA.editArticle("Bonus Reward for Daily Return Bonus", "publish");
		metaBonus.configureBonusAward("-1", "3", "1000");
		joomla.alias.saveAndClose();
		
		enableThread(jmeterScriptName+".jmx", threadGroup4);
		Log.info("Start Executing Jmeter Script");
		  
		CallJmeterBuild(jmeterScriptName+".jmx"); 
		Log.info("Calling  Jmeter file ");
		 
		Assert.assertEquals("false",xp.Xml_Parser(jmeterScriptName+".xml")); 
		Log.info("Validating assertion ");
		
		//***** Bonus Submit with Token Multiplier as "0" in Bonus Article
		joomla.article.navigateToArticleManager();
		joomla.PA.filterArticleByNames("14", "Bonus Reward for Daily Return Bonus");
		joomla.PA.editArticle("Bonus Reward for Daily Return Bonus", "publish");
		metaBonus.configureBonusAward("0", "2", "1000");
		joomla.alias.saveAndClose();
		
		enableThread(jmeterScriptName+".jmx", threadGroup5);
		Log.info("Start Executing Jmeter Script");
		  
		CallJmeterBuild(jmeterScriptName+".jmx"); 
		Log.info("Calling  Jmeter file ");
		 
		Assert.assertEquals("false",xp.Xml_Parser(jmeterScriptName+".xml")); 
		Log.info("Validating assertion ");
		
		//***** Bonus Submit with Max Entry allowed is configured as "0" in Bonus Article.
		joomla.article.navigateToArticleManager();
		joomla.PA.filterArticleByNames("14", "Bonus Reward for Daily Return Bonus");
		joomla.PA.editArticle("Bonus Reward for Daily Return Bonus", "publish");
		metaBonus.configureBonusAward("1000", "0", "1000");
		joomla.alias.saveAndClose();
		
		enableThread(jmeterScriptName+".jmx", threadGroup6);
		Log.info("Start Executing Jmeter Script");
		  
		CallJmeterBuild(jmeterScriptName+".jmx"); 
		Log.info("Calling  Jmeter file ");
		 
		Assert.assertEquals("false",xp.Xml_Parser(jmeterScriptName+".xml")); 
		Log.info("Validating assertion ");
		
		//***** Bonus Submit with Max Entry allowed is configured as "-1" in Bonus Article.
		joomla.article.navigateToArticleManager();
		joomla.PA.filterArticleByNames("14", "Bonus Reward for Daily Return Bonus");
		joomla.PA.editArticle("Bonus Reward for Daily Return Bonus", "publish");
		metaBonus.configureBonusAward("1000", "-1", "1000");
		joomla.alias.saveAndClose();
		
		enableThread(jmeterScriptName+".jmx", threadGroup7);
		Log.info("Start Executing Jmeter Script");
		  
		CallJmeterBuild(jmeterScriptName+".jmx"); 
		Log.info("Calling  Jmeter file ");
		 
		Assert.assertEquals("false",xp.Xml_Parser(jmeterScriptName+".xml")); 
		Log.info("Validating assertion ");
		
		//***** Bonus Submit with PCH Game ClassName missing in Bonus Article.
		joomla.article.navigateToArticleManager();
		joomla.PA.filterArticleByNames("14", "Bonus Reward for Daily Return Bonus");
		joomla.PA.editArticle("Bonus Reward for Daily Return Bonus", "publish");
		metaBonus.configureBonusAward("1000", "2", "1000");
		metaBonus.actionOnPCHGameClassName(""); // Removing the className
		joomla.alias.saveAndClose();
		
		enableThread(jmeterScriptName+".jmx", threadGroup8);
		Log.info("Start Executing Jmeter Script");
		  
		CallJmeterBuild(jmeterScriptName+".jmx"); 
		Log.info("Calling  Jmeter file ");
		 
		Assert.assertEquals("false",xp.Xml_Parser(jmeterScriptName+".xml")); 
		Log.info("Validating assertion ");
		
		//***** Bonus Submit with Unpublished Bonus Article.
		joomla.article.navigateToArticleManager();
		joomla.PA.filterArticleByNames("14", "Bonus Reward for Daily Return Bonus");
		joomla.PA.editArticle("Bonus Reward for Daily Return Bonus", "unpublish");
		metaBonus.configureBonusAward("1000", "2", "1000");
		metaBonus.actionOnPCHGameClassName("bonusreward");
		joomla.alias.saveAndClose();
		
		enableThread(jmeterScriptName+".jmx", threadGroup9);
		Log.info("Start Executing Jmeter Script");
		  
		CallJmeterBuild(jmeterScriptName+".jmx"); 
		Log.info("Calling  Jmeter file ");
		 
		Assert.assertEquals("false",xp.Xml_Parser(jmeterScriptName+".xml")); 
		Log.info("Validating assertion ");

	}
	

	@AfterClass
	public void tearDown() throws IOException, Exception {

		joomla.article.navigateToArticleManager();
		joomla.PA.filterArticleByNames("14", "Bonus Reward for Daily Return Bonus");
		joomla.PA.editArticle("Bonus Reward for Daily Return Bonus", "publish");
		joomla.alias.saveAndClose();
		endTestCase("End");
		closeallwindows();
	}
}