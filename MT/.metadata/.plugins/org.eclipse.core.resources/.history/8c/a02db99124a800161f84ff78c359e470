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

public class B_38537_MT2_PCHApp_MetaActionBonusGameSupport_FaceBookConnect_ErrorConditions extends Action_Wrapper {
	JoomlaAdministrator joomla = new JoomlaAdministrator();
	MetaActionBonusArticle metaBonus = new MetaActionBonusArticle();
	Database_Configuration DB = new Database_Configuration();
	BonusTriggers Btrigger = new BonusTriggers();
	
	String GMT,response;
	String threadGroup[]={"BonusSubmit-First","BonusSubmit-Second","BonusSubmit-WrongGameID"};
	String threadGroup2[]={"BonusSubmit- TokenMultiplier as -Ive"};
	String threadGroup3[]={"BonusSubmit-TokenMultiplier as 0"};
	//String threadGroup8[]={"BonusSubmit-Missing ClassName"};
	String threadGroup4[]={"BonusSubmit-UnPublished Bonus Article"};
	
	
	RFTestPage RF = new RFTestPage();
	Jmeter_Xml_Parser xp = new Jmeter_Xml_Parser();
	String jmeterScriptName = "B_38537_MT2_PCHApp_MetaActionBonusGameSupport_FaceBookConnect_ErrorConditions";

	
	@BeforeClass
	public void setup() throws Exception {
		
		startTestCase("B_38537_MT2_PCHApp_MetaActionBonusGameSupport_FaceBookConnect_ErrorConditions");
		createAccessTokenForAllApp("pchapp");

		// Configure Bonus Article and Bonus Reward
		
		openBrowser("joomla");
		joomla.login.login();
		joomla.article.navigateToArticleManager();
		joomla.PA.filterArticleByNames("14", "Bonus Reward for FB Connect");
		joomla.PA.editArticle("Bonus Reward for FB Connect", "publish");
		metaBonus.configureBonusAward("1000", "2", "1000");
		joomla.alias.saveAndClose();
		
		//Configure Meta Action Bonus in PCH App article
			
		joomla.PA.filterArticleByNames("99", "PCH App");
		joomla.PA.editArticle("PCH App", "publish");
		joomla.PA.bonusActionArticle("Bonus Reward for FB Connect","Facebook_Connect");
		//joomla.PA.bonusActionArticlesTrigger("Facebook_Connect", 1);
		
		
		joomla.alias.saveAndClose();
		
		//Configure Bonus Trigger Frequency as All time.
		
		joomla.PA.filterArticleByNames("57", "Facebook_Connect");
		joomla.PA.editArticle("Facebook_Connect", "publish");
		Btrigger.configureTriggerOptions("connect", "facebook", "onetime");
	
		joomla.alias.saveAndClose();
		
		//Create Full Reg User
		response=RF.createFullRegUserAPI();
		GMT = RF.getResponseValueAPI(response,ResponseData.GlobalMemberToken);
		Log.info("GMT:"+GMT);
		writeInProprtyFile("Encrypted_Email", getEncryptedData("Access_Token_PCHAPP", RF.getResponseValueAPI(response,ResponseData.Email)));
		writeInProprtyFile("Encrypted_GMT", getEncryptedData("Access_Token_PCHAPP",GMT));
		copyAndRenameFile();
	}
	
	@Test(groups = {GroupName.PCHApp ,GroupName.Regression})
	public void story_B_38537_MT2_PCHApp_MetaActionBonusGameSupport_FaceBookConnect_ErrorConditions() throws Exception {

		//******* First Thread for Bonus Submit
		enableThread(jmeterScriptName+".jmx", threadGroup);
		Log.info("Start Executing Jmeter Script");
		  
		CallJmeterBuild(jmeterScriptName+".jmx"); 
		Log.info("Calling  Jmeter file ");
		 
		Assert.assertEquals("false",xp.Xml_Parser(jmeterScriptName+".xml")); 
		Log.info("Validating assertion ");
		
		//Bonus status validation from Bonus Item table
		Assert.assertTrue(DB.phpctrl_app_bonus_items(GMT,"get Bonus data").contains("{\"34\":{\"status\":3,\"data\":{\"buid\":\"53\"}}}"));
		
				
		//***** Bonus Submit with Token Multiplier as -1 in Bonus Article
		Log.info("creating another full reg user ");
		response=RF.createFullRegUserAPI();
		GMT = RF.getResponseValueAPI(response,ResponseData.GlobalMemberToken);
		Log.info("GMT:"+GMT);
		writeInProprtyFile("Encrypted_Email", getEncryptedData("Access_Token_PCHAPP", RF.getResponseValueAPI(response,ResponseData.Email)));
		writeInProprtyFile("Encrypted_GMT", getEncryptedData("Access_Token_PCHAPP",GMT));
		copyAndRenameFile();
		
		
		
		joomla.article.navigateToArticleManager();
		
		joomla.PA.filterArticleByNames("14", "Bonus Reward for FB Connect");
		joomla.PA.editArticle("Bonus Reward for FB Connect", "publish");
		metaBonus.configureBonusAward("-1", "1", "1000");
		joomla.alias.saveAndClose();
		
		enableThread(jmeterScriptName+".jmx", threadGroup2);
		Log.info("Start Executing Jmeter Script");
		  
		CallJmeterBuild(jmeterScriptName+".jmx"); 
		Log.info("Calling  Jmeter file ");
		 
		Assert.assertEquals("false",xp.Xml_Parser(jmeterScriptName+".xml")); 
		Log.info("Validating assertion ");
		
		//***** Bonus Submit with Token Multiplier as "0" in Bonus Article
		joomla.article.navigateToArticleManager();
		joomla.PA.filterArticleByNames("14", "Bonus Reward for FB Connect");
		joomla.PA.editArticle("Bonus Reward for FB Connect", "publish");
		metaBonus.configureBonusAward("0", "2", "1000");
		joomla.alias.saveAndClose();
		
		enableThread(jmeterScriptName+".jmx", threadGroup3);
		Log.info("Start Executing Jmeter Script");
		  
		CallJmeterBuild(jmeterScriptName+".jmx"); 
		Log.info("Calling  Jmeter file ");
		 
		Assert.assertEquals("false",xp.Xml_Parser(jmeterScriptName+".xml")); 
		Log.info("Validating assertion ");

	
		//***** Bonus Submit with Unpublished Bonus Article.
		joomla.article.navigateToArticleManager();
		
		joomla.PA.filterArticleByNames("14", "Bonus Reward for FB Connect");
		joomla.PA.editArticle("Bonus Reward for FB Connect", "unpublish");
		
		joomla.alias.saveAndClose();
		
		enableThread(jmeterScriptName+".jmx", threadGroup4);
		Log.info("Start Executing Jmeter Script");
		  
		CallJmeterBuild(jmeterScriptName+".jmx"); 
		Log.info("Calling  Jmeter file ");
		 
		Assert.assertEquals("false",xp.Xml_Parser(jmeterScriptName+".xml")); 
		Log.info("Validating assertion ");

	}
	

	@AfterClass
	public void tearDown() throws IOException, Exception {

		joomla.article.navigateToArticleManager();
		joomla.PA.filterArticleByNames("14", "Bonus Reward for FB Connect");
		joomla.PA.editArticle("Bonus Reward for FB Connect", "publish");
		joomla.alias.saveAndClose();
		endTestCase("End");
		closeallwindows();
	}
}