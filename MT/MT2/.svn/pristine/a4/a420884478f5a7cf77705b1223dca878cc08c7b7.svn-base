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

public class B_38537_MT2_PCHApp_MetaActionBonusGameSupport_FaceBookConnect_AllTime extends Action_Wrapper {
	JoomlaAdministrator joomla = new JoomlaAdministrator();
	MetaActionBonusArticle metaBonus = new MetaActionBonusArticle();
	Database_Configuration DB = new Database_Configuration();
	BonusTriggers Btrigger = new BonusTriggers();
	String scriptName = this.getClass().getSimpleName();
	String accessToken,response,GMT,Encrypted_Email,Encrypted_GMT,Email;
	RFTestPage RF = new RFTestPage();
	Jmeter_Xml_Parser xp = new Jmeter_Xml_Parser();
	
	
	@BeforeClass
	public void setup() throws Exception {
		
		startTestCase(scriptName);
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
		joomla.PA.filterArticleByNames("14", "Bonus Reward for FB Connect");
		joomla.PA.editArticle("Bonus Reward for FB Connect", "publish");
		metaBonus.configureBonusAward("1000", "2", "1000");
		joomla.alias.saveAndClose();

		// Configure Bonus Trigger Frequency as All time.
		joomla.PA.filterArticleByNames("57", "Facebook_Connect");
		joomla.PA.editArticle("Facebook_Connect", "publish");
		Btrigger.configureTriggerOptions("connect", "facebook", "onetime");
		joomla.alias.saveAndClose();

		// Configure Meta Action Bonus in PCH App article
		joomla.PA.filterArticleByNames("99", "PCH App");
		joomla.PA.editArticle("PCH App", "publish");
		joomla.PA.bonusActionArticle("Bonus Reward for FB Connect", "Facebook_Connect");
		joomla.alias.saveAndClose();

	}
	
	@Test(groups = {GroupName.PCHApp ,GroupName.Regression})
	public void story_B_38537_MT2_PCHApp_MetaActionBonusGameSupport_FaceBookConnect_AllTime() throws Exception {

		//************** First Thread for Bonus Submit  ********************************
	//	enableThread(jmeterScriptName+".jmx", threadGroup1);
		Log.info("Start Executing Jmeter Script");
		  
		CallJmeterBuild(scriptName+".jmx"); 
		Log.info("Calling  Jmeter file ");
		 
		Assert.assertEquals("false",xp.Xml_Parser(scriptName+".xml")); 
		Log.info("Validating assertion ");
		
		//Bonus status validation from All Time table
		Assert.assertEquals("{\"fbConnectBonus\":3}", DB.phpctrl_app_bonus_items_Alltime(GMT, "get Bonus data"));
		//Bonus status validation from Bonus Item table
		Assert.assertTrue(DB.phpctrl_app_bonus_items(GMT,"get Bonus data").contains("status\":3"));
		
			
	/*	
	
		//****************************second Thread for Bonus Submit with Different Token Multiplier  *************************
		
		enableThread(jmeterScriptName + ".jmx", threadGroup2);
		joomla.article.navigateToArticleManager();
		joomla.PA.filterArticleByNames("14", "Bonus Reward for FB Connect");
		joomla.PA.editArticle("Bonus Reward for FB Connect", "publish");
		metaBonus.configureBonusAward("2000", "4", "1000");
		joomla.alias.saveAndClose();

		Log.info("**** Creating Third user from RF ****");

		response = RF.createFullRegUserAPI();
		Email = RF.getResponseValueAPI(response, ResponseData.Email);
		GMT = RF.getResponseValueAPI(response, ResponseData.GlobalMemberToken);
		Log.info("Third user email is " + Email);
	
		writeInProprtyFile("Encrypted_Email", getEncryptedData("Access_Token_PCHAPP", RF.getResponseValueAPI(response,ResponseData.Email)));
		writeInProprtyFile("Encrypted_GMT", getEncryptedData("Access_Token_PCHAPP",GMT));
		copyAndRenameFile();
		
		Log.info("Start Executing Jmeter Script");
		CallJmeterBuild(jmeterScriptName+".jmx"); 
		Log.info("Calling  Jmeter file ");
		Assert.assertEquals("false",xp.Xml_Parser(jmeterScriptName+".xml")); 
		Log.info("Validating assertion ");
			
		//Bonus status validation from All Time table
		Assert.assertEquals("{\"fbConnectBonus\":3}", DB.phpctrl_app_bonus_items_Alltime(GMT, "get Bonus data"));
		//Bonus status validation from Bonus Item table
		Assert.assertTrue(DB.phpctrl_app_bonus_items(GMT,"get Bonus data").contains("status\":3"));
				*/
		}
	
	

	@AfterClass
	public void tearDown() throws IOException, Exception {

		endTestCase("End");
		closeallwindows();
	}
}