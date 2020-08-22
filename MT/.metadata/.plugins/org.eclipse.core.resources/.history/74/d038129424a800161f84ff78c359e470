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

public class B_38537_MT2_PCHApp_MetaActionBonusGameSupport_FaceBookConnect_Continusous extends Action_Wrapper {
	JoomlaAdministrator joomla = new JoomlaAdministrator();
	MetaActionBonusArticle metaBonus = new MetaActionBonusArticle();
	Database_Configuration DB = new Database_Configuration();
	BonusTriggers Btrigger = new BonusTriggers();
	String jmeterScriptName = "B_38537_MT2_PCHApp_MetaActionBonusGameSupport_FaceBookConnect_Continusous";
	String accessToken,response,GMT,Encrypted_Email,Encrypted_GMT,Email;
	RFTestPage RF = new RFTestPage();
	Jmeter_Xml_Parser xp = new Jmeter_Xml_Parser();
	String threadGroup1[]={"ScoreSubmit","Token-History-Credit"};
	String threadGroup2[]={"ScoreSubmit1","Token-History-Credit"};
	//String threadGroup1[]={""};
	
	
	@BeforeClass
	public void setup() throws Exception {
		
		startTestCase("B_38537_MT2_PCHApp_MetaActionBonusGameSupport_FaceBookConnect_Continusous");
		createAccessTokenForAllApp("pchapp");

		
		Log.info("***Creating First user from RF  ***");
		String response = RF.createFullRegUserAPI();
		Email=RF.getResponseValueAPI(response, ResponseData.Email);
		Log.info("full User Email :"+Email);
		GMT=RF.getResponseValueAPI(response, ResponseData.GlobalMemberToken);
		Log.info("full User GMT  :"+GMT);
		Encrypted_Email=getEncryptedData("Access_Token_PCHAPP",Email);
		Encrypted_GMT=getEncryptedData("Access_Token_PCHAPP",GMT);
		
		writeInProprtyFile("Encrypted_Email", Encrypted_Email);
		writeInProprtyFile("Encrypted_GMT", Encrypted_GMT);
		copyAndRenameFile();
		
		
		// Configure Bonus Article and Bonus Reward
			openBrowser("joomla");
		joomla.login.login();
		joomla.article.navigateToArticleManager();
		
		joomla.CG.configureContinuesGame("Mega Token Madness bonus", 2, "3000", 1, "Mega Token Madness");
		
		joomla.CG.configureTokePayablearticle("Mega Token Madness", "150","1000","200","2000");
		
		//Configure Meta Action Bonus in PCH App article
		joomla.PA.filterArticleByNames("99", "PCH App");
		joomla.PA.editArticle("PCH App", "publish");
		joomla.PA.bonusActionArticle("Mega Token Madness bonus", "Facebook_Connect");
		//joomla.PA.bonusActionArticlesTrigger("Facebook_Connect", 2);
		joomla.alias.saveAndClose();
		

		// Configure Bonus Trigger Frequency as All time.
		joomla.PA.filterArticleByNames("57", "Facebook_Connect");
		joomla.PA.editArticle("Facebook_Connect", "publish");
		Btrigger.configureTriggerOptions("connect", "facebook", "onetime");
		joomla.alias.saveAndClose();

				

	}
	
	@Test(groups = {GroupName.PCHApp ,GroupName.Regression})
	public void story_B_38537_MT2_PCHApp_MetaActionBonusGameSupport_FaceBookConnect_Continusous() throws Exception {

		//******* Thread Execution for Bonus Submit for Continuous game as Daily Bonus
			//	enableThread(jmeterScriptName+".jmx", threadGroup);
		enableThread(jmeterScriptName+".jmx", threadGroup1);
				Log.info("Start Executing Jmeter Script");
				  
				CallJmeterBuild(jmeterScriptName+".jmx"); 
				Log.info("Calling  Jmeter file ");
				 
				Assert.assertEquals("false",xp.Xml_Parser(jmeterScriptName+".xml")); 
				Log.info("Validating assertion ");
				
				//Bonus status validation from Bonus Item table
				Assert.assertTrue(DB.phpctrl_app_bonus_items(GMT,"get Bonus data").contains("{\"275\":{\"status\":2"));
				
								
				
				//******* Again Bonus Submit for Continuous game as Daily Bonus to get Status as 3
				enableThread(jmeterScriptName+".jmx", threadGroup2);
				Log.info("Start Executing Jmeter Script");
				  
				CallJmeterBuild(jmeterScriptName+".jmx"); 
				Log.info("Calling  Jmeter file ");
				 
				Assert.assertEquals("false",xp.Xml_Parser(jmeterScriptName+".xml")); 
				Log.info("Validating assertion ");
				
				//Bonus status validation from All Time table
				Assert.assertEquals("{\"fbConnectBonus\":3}", DB.phpctrl_app_bonus_items_Alltime(GMT, "get Bonus data"));
				//Bonus status validation from Bonus Item table
				Assert.assertTrue(DB.phpctrl_app_bonus_items(GMT,"get Bonus data").contains("{\"275\":{\"status\":3"));
				
				
			}
			
			@AfterClass
			public void tearDown() throws IOException, Exception {

				endTestCase("End");
				closeallwindows();
			}
		}