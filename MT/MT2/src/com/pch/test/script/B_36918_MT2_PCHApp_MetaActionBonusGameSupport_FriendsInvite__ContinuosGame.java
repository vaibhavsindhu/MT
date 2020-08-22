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

public class B_36918_MT2_PCHApp_MetaActionBonusGameSupport_FriendsInvite__ContinuosGame extends Action_Wrapper {
	JoomlaAdministrator joomla = new JoomlaAdministrator();
	MetaActionBonusArticle metaBonus = new MetaActionBonusArticle();
	Database_Configuration DB = new Database_Configuration();
	BonusTriggers Btrigger = new BonusTriggers();
	MidTierApiConfiguration MTAPIConfig=new MidTierApiConfiguration();
	
	String GMT,response;
	String threadGroup[]={"ScoreSubmit"};
	String threadGroup2[]={"ScoreSubmit1"};
	String threadGroup1[]={"Token-History-Credit"};
//	String threadGroup3[]={"Token-History-Credit1"};
	
	RFTestPage RF = new RFTestPage();
	Jmeter_Xml_Parser xp = new Jmeter_Xml_Parser();
	String jmeterScriptName = "B_36918_MT2_PCHApp_MetaActionBonusGameSupport_FriendsInvite__ContinuosGame";

	
	@BeforeClass
	public void setup() throws Exception {
		
		startTestCase("B_36918_MT2_PCHApp_MetaActionBonusGameSupport_FriendsInvite__ContinuosGame");
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
		joomla.PA.bonusActionArticle("Mega Token Madness bonus","Friends_Invite");		
		joomla.alias.saveAndClose();
		
		//Configure Bonus Trigger Frequency as All time.
		joomla.PA.filterArticleByNames("57", "Friends_Invite");
		joomla.PA.editArticle("Friends_Invite", "publish");
		Btrigger.configureTriggerOptions("invite", "friends", "daily");
		joomla.alias.saveAndClose();
		
		//Create Full Reg User
		response=RF.createFullRegUserAPI();
		GMT = RF.getResponseValueAPI(response,ResponseData.GlobalMemberToken);
		writeInProprtyFile("Encrypted_Email", getEncryptedData("Access_Token_PCHAPP", RF.getResponseValueAPI(response,ResponseData.Email)));
		writeInProprtyFile("Encrypted_GMT", getEncryptedData("Access_Token_PCHAPP",GMT));
		copyAndRenameFile();
	
	}
	
	@Test(groups = {GroupName.PCHApp ,GroupName.Regression})
	public void story_B_36918_MT2_PCHApp_MetaActionBonusGameSupport_FriendsInvite__ContinuosGame() throws Exception {

		//******* Thread Execution for Bonus Submit for Continuous game as Daily Bonus
		enableThread(jmeterScriptName+".jmx", threadGroup);
		Log.info("Start Executing Jmeter Script");
		  
		CallJmeterBuild(jmeterScriptName+".jmx"); 
		Log.info("Calling  Jmeter file ");
		 
		Assert.assertEquals("false",xp.Xml_Parser(jmeterScriptName+".xml")); 
		Log.info("Validating assertion ");
		
		//Bonus status validation from Bonus Item table
		Assert.assertTrue(DB.phpctrl_app_bonus_items(GMT,"get Bonus data").contains("{\"275\":{\"status\":2"));
		
		//***** Second Thread to Validate the Token History
		enableThread(jmeterScriptName+".jmx", threadGroup1);
		Log.info("Start Executing Jmeter Script");
		  
		CallJmeterBuild(jmeterScriptName+".jmx"); 
		Log.info("Calling  Jmeter file ");
		 
		Assert.assertEquals("false",xp.Xml_Parser(jmeterScriptName+".xml")); 
		Log.info("Validating assertion ");
		
		
		//******* Again Bonus Submit for Continuous game as Daily Bonus to get Status as 3
		enableThread(jmeterScriptName+".jmx", threadGroup2);
		Log.info("Start Executing Jmeter Script");
		  
		CallJmeterBuild(jmeterScriptName+".jmx"); 
		Log.info("Calling  Jmeter file ");
		 
		Assert.assertEquals("false",xp.Xml_Parser(jmeterScriptName+".xml")); 
		Log.info("Validating assertion ");
		
		//Bonus status validation from Bonus Item table
		Assert.assertTrue(DB.phpctrl_app_bonus_items(GMT,"get Bonus data").contains("{\"275\":{\"status\":3"));
		
		//*****Thread to Validate the Token History
		enableThread(jmeterScriptName+".jmx", threadGroup1);
		Log.info("Start Executing Jmeter Script");
				  
		CallJmeterBuild(jmeterScriptName+".jmx"); 
		Log.info("Calling  Jmeter file ");
				 
		Assert.assertEquals("false",xp.Xml_Parser(jmeterScriptName+".xml")); 
		Log.info("Validating assertion");
		
		
		
		//******* Thread Execution for Bonus Submit for Continuous game as All Time Bonus
		
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

		enableThread(jmeterScriptName+".jmx", threadGroup);
		Log.info("Start Executing Jmeter Script");
		  
		CallJmeterBuild(jmeterScriptName+".jmx"); 
		Log.info("Calling  Jmeter file ");
		 
		Assert.assertEquals("false",xp.Xml_Parser(jmeterScriptName+".xml")); 
		Log.info("Validating assertion ");
		
		//Bonus status validation from All Time table
		Assert.assertEquals("{\"friends\":2}", DB.phpctrl_app_bonus_items_Alltime(GMT, "get Bonus data"));
		//Bonus status validation from Bonus Item table
		Assert.assertTrue(DB.phpctrl_app_bonus_items(GMT,"get Bonus data").contains("{\"275\":{\"status\":2"));
		
		//***** Second Thread to Validate the Token History
		enableThread(jmeterScriptName+".jmx", threadGroup1);
		Log.info("Start Executing Jmeter Script");
		  
		CallJmeterBuild(jmeterScriptName+".jmx"); 
		Log.info("Calling  Jmeter file ");
		 
		Assert.assertEquals("false",xp.Xml_Parser(jmeterScriptName+".xml")); 
		Log.info("Validating assertion ");
		
		
		//******* Again Bonus Submit for Continuous game as Daily Bonus to get Status as 3
		enableThread(jmeterScriptName+".jmx", threadGroup2);
		Log.info("Start Executing Jmeter Script");
		  
		CallJmeterBuild(jmeterScriptName+".jmx"); 
		Log.info("Calling  Jmeter file ");
		 
		Assert.assertEquals("false",xp.Xml_Parser(jmeterScriptName+".xml")); 
		Log.info("Validating assertion ");
		
		//Bonus status validation from All Time table
		Assert.assertEquals(DB.phpctrl_app_bonus_items_Alltime(GMT, "get Bonus data"),"{\"friends\":3}");
		//Bonus status validation from Bonus Item table
		Assert.assertTrue(DB.phpctrl_app_bonus_items(GMT,"get Bonus data").contains("{\"275\":{\"status\":3"));
		
		//*****Thread to Validate the Token History
		enableThread(jmeterScriptName+".jmx", threadGroup1);
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