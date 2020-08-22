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
import com.pch.joomla.configuration.MidTierApiConfiguration;
import com.pch.joomla.configuration.MidTierApiConfiguration.ServiceURL;
import com.pch.utilities.Action_Wrapper;
import com.pch.utilities.Database_Configuration;
import com.pch.utilities.GroupName;
import com.pch.utilities.Jmeter_Xml_Parser;

public class B_36918_MT2_PCHApp_MetaActionBonusGameSupport_FriendsInvite_AllTime extends Action_Wrapper {
	JoomlaAdministrator joomla = new JoomlaAdministrator();
	MetaActionBonusArticle metaBonus = new MetaActionBonusArticle();
	Database_Configuration DB = new Database_Configuration();
	BonusTriggers Btrigger = new BonusTriggers();
	MidTierApiConfiguration MTAPIConfig=new MidTierApiConfiguration();
	
	String GMT,response;
	String threadGroup[]={"BonusSubmit"};
	String threadGroup1[]={"TokenHistory -1000"};
	String threadGroup2[]={"BonusSubmit - TokenMultiplier"};
	String threadGroup3[]={"Token-History-TokenMutiplier-2000"};
	String threadGroup4[]={"BonusSubmit - TokenMultiplier2"};
	String threadGroup5[]={"Token-History-TokenMutiplier-8000"};
		
	RFTestPage RF = new RFTestPage();
	Jmeter_Xml_Parser xp = new Jmeter_Xml_Parser();
	String jmeterScriptName = "B_36918_MT2_PCHApp_MetaActionBonusGameSupport_FriendsInvite_AllTime";

	
	@BeforeClass
	public void setup() throws Exception {
		
		startTestCase("B_36918_MT2_PCHApp_MetaActionBonusGameSupport_FriendsInvite_AllTime");
		createAccessTokenForAllApp("pchapp");

		// Configure Bonus Article and Bonus Reward
		
		openBrowser("joomla");
		joomla.login.login();
		joomla.article.navigateToArticleManager();
		joomla.PA.filterArticleByNames("14", "Bonus Reward for Facebook Friends");
		joomla.PA.editArticle("Bonus Reward for Facebook Friends", "publish");
		metaBonus.configureBonusAward("1000", "2", "1000");
		joomla.alias.saveAndClose();
		
		//Configure Meta Action Bonus in PCH App article
		joomla.PA.filterArticleByNames("99", "PCH App");
		joomla.PA.editArticle("PCH App", "publish");
		joomla.PA.bonusActionArticle("Bonus Reward for Facebook Friends","Friends_Invite");		
		joomla.alias.saveAndClose();
		
		//Configure Bonus Trigger Frequency as All time.
		joomla.PA.filterArticleByNames("57", "Friends_Invite");
		joomla.PA.editArticle("Friends_Invite", "publish");
		Btrigger.configureTriggerOptions("invite", "friends", "onetime");
		joomla.alias.saveAndClose();
		
		//Create Full Reg User
		response=RF.createFullRegUserAPI();
		GMT = RF.getResponseValueAPI(response,ResponseData.GlobalMemberToken);
		writeInProprtyFile("Encrypted_Email", getEncryptedData("Access_Token_PCHAPP", RF.getResponseValueAPI(response,ResponseData.Email)));
		writeInProprtyFile("Encrypted_GMT", getEncryptedData("Access_Token_PCHAPP",GMT));
		copyAndRenameFile();		
	
	}
	
	@Test(groups = {GroupName.PCHApp ,GroupName.Regression})
	public void story_B_36918_MT2_PCHApp_MetaActionBonusGameSupport_FriendsInvite_AllTime() throws Exception {

		//******* First Thread for Bonus Submit
		enableThread(jmeterScriptName+".jmx", threadGroup);
		Log.info("Start Executing Jmeter Script");
		  
		CallJmeterBuild(jmeterScriptName+".jmx"); 
		Log.info("Calling  Jmeter file ");
		 
		Assert.assertEquals("false",xp.Xml_Parser(jmeterScriptName+".xml")); 
		Log.info("Validating assertion ");
		
		//Bonus status validation from All Time table
		Assert.assertEquals("{\"friends\":3}", DB.phpctrl_app_bonus_items_Alltime(GMT, "get Bonus data"));
		
		//Bonus status validation from Bonus Item table
		Assert.assertTrue(DB.phpctrl_app_bonus_items(GMT,"get Bonus data").contains("{\"32\":{\"status\":3,\"data\":{\"buid\":\"53\"}}}"));
		
		//***** Second Thread to Validate the Token History
		enableThread(jmeterScriptName+".jmx", threadGroup1);
		Log.info("Start Executing Jmeter Script");
		  
		CallJmeterBuild(jmeterScriptName+".jmx"); 
		Log.info("Calling  Jmeter file ");
		 
		Assert.assertEquals("false",xp.Xml_Parser(jmeterScriptName+".xml")); 
		Log.info("Validating assertion ");
		
		//******* Third Thread for Bonus Submit with Different Token Multiplier
		joomla.article.navigateToArticleManager();
		joomla.PA.filterArticleByNames("14", "Bonus Reward for Facebook Friends");
		joomla.PA.editArticle("Bonus Reward for Facebook Friends", "publish");
		metaBonus.configureBonusAward("2000", "1", "1000");
		joomla.alias.saveAndClose();
		
		// Create a New User
		response=RF.createFullRegUserAPI();
		GMT = RF.getResponseValueAPI(response,ResponseData.GlobalMemberToken);
		writeInProprtyFile("Encrypted_Email", getEncryptedData("Access_Token_PCHAPP", RF.getResponseValueAPI(response,ResponseData.Email)));
		writeInProprtyFile("Encrypted_GMT", getEncryptedData("Access_Token_PCHAPP",GMT));
		copyAndRenameFile();
		
		enableThread(jmeterScriptName+".jmx", threadGroup2);
		Log.info("Start Executing Jmeter Script");
		  
		CallJmeterBuild(jmeterScriptName+".jmx"); 
		Log.info("Calling  Jmeter file ");
		 
		Assert.assertEquals("false",xp.Xml_Parser(jmeterScriptName+".xml")); 
		Log.info("Validating assertion ");
		
		//Bonus status validation from All Time table
		Assert.assertEquals("{\"friends\":3}", DB.phpctrl_app_bonus_items_Alltime(GMT, "get Bonus data"));
		
		//Bonus status validation from Bonus Item table
		Assert.assertTrue(DB.phpctrl_app_bonus_items(GMT,"get Bonus data").contains("{\"32\":{\"status\":3,\"data\":{\"buid\":\"53\"}}}"));
		
		//***** Forth Thread to Validate the Token History
		enableThread(jmeterScriptName+".jmx", threadGroup3);
		Log.info("Start Executing Jmeter Script");
		  
		CallJmeterBuild(jmeterScriptName+".jmx"); 
		Log.info("Calling  Jmeter file ");
		 
		Assert.assertEquals("false",xp.Xml_Parser(jmeterScriptName+".xml")); 
		Log.info("Validating assertion ");
		
		
		//******* Fifth Thread for Bonus Submit with Different Token Multiplier
		joomla.article.navigateToArticleManager();
		joomla.PA.filterArticleByNames("14", "Bonus Reward for Facebook Friends");
		joomla.PA.editArticle("Bonus Reward for Facebook Friends", "publish");
		metaBonus.configureBonusAward("2000", "4", "1000");
		joomla.alias.saveAndClose();
				
		// Create a New User
		response=RF.createFullRegUserAPI();
		GMT = RF.getResponseValueAPI(response,ResponseData.GlobalMemberToken);
		writeInProprtyFile("Encrypted_Email", getEncryptedData("Access_Token_PCHAPP", RF.getResponseValueAPI(response,ResponseData.Email)));
		writeInProprtyFile("Encrypted_GMT", getEncryptedData("Access_Token_PCHAPP",GMT));
		copyAndRenameFile();
				
		enableThread(jmeterScriptName+".jmx", threadGroup4);
		Log.info("Start Executing Jmeter Script");
				  
		CallJmeterBuild(jmeterScriptName+".jmx"); 
		Log.info("Calling  Jmeter file ");
				 
		Assert.assertEquals("false",xp.Xml_Parser(jmeterScriptName+".xml")); 
		Log.info("Validating assertion ");
				
		//Bonus status validation from All Time table
		Assert.assertEquals("{\"friends\":3}", DB.phpctrl_app_bonus_items_Alltime(GMT, "get Bonus data"));
				
		//Bonus status validation from Bonus Item table
		Assert.assertTrue(DB.phpctrl_app_bonus_items(GMT,"get Bonus data").contains("{\"32\":{\"status\":3,\"data\":{\"buid\":\"53\"}}}"));
				
		//***** Sixth Thread to Validate the Token History
		enableThread(jmeterScriptName+".jmx", threadGroup5);
		Log.info("Start Executing Jmeter Script");
			  
		CallJmeterBuild(jmeterScriptName+".jmx"); 
		Log.info("Calling  Jmeter file ");
				 
		Assert.assertEquals("false",xp.Xml_Parser(jmeterScriptName+".xml")); 
		Log.info("Validating assertion ");

	}
	

	@AfterClass
	public void tearDown() throws IOException, Exception {

		endTestCase("End");
		closeallwindows();
	}
}