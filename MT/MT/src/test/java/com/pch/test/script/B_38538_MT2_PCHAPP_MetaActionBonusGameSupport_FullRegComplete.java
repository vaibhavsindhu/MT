package com.pch.test.script;

import static com.pch.utilities.Log.Log;
import static com.pch.utilities.Log.endTestCase;
import static com.pch.utilities.Log.startTestCase;

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

public class B_38538_MT2_PCHAPP_MetaActionBonusGameSupport_FullRegComplete extends
Action_Wrapper {
	JoomlaAdministrator joomla = new JoomlaAdministrator();
	MetaActionBonusArticle metaBonus = new MetaActionBonusArticle();
	Database_Configuration DB = new Database_Configuration();
	BonusTriggers Btrigger = new BonusTriggers();
	String jmeterScriptName = this.getClass().getSimpleName();
	private String response,GMT,Encrypted_Email,Encrypted_GMT,email;
	RFTestPage RF = new RFTestPage();
	Jmeter_Xml_Parser xp = new Jmeter_Xml_Parser();
	
	
	//String threadGroup1[]={"BonusSubmit","TokenHistory -3000","AppLoad1"};
	//String threadGroup2[]={"BonusSubmit - TokenMultiplier2","TokenHistory -2000","AppLoad2"};

	
	@BeforeClass
	public void setup() throws Exception {
		
		startTestCase(jmeterScriptName);
		createAccessTokenForAllApp("pchapp");

		
		Log.info("***Creating First user from RF  ***");
		response = RF.createFullRegUserAPI();
		email=RF.getResponseValueAPI(response, ResponseData.Email);
		Log.info("full User Email :"+email);
		GMT=RF.getResponseValueAPI(response, ResponseData.GlobalMemberToken);
		Log.info("full User GMT  :"+GMT);
		Encrypted_Email=getEncryptedData("Access_Token_PCHAPP",email);
		Encrypted_GMT=getEncryptedData("Access_Token_PCHAPP",GMT);
		
		writeInProprtyFile("Encrypted_Email", Encrypted_Email);
		writeInProprtyFile("Encrypted_GMT", Encrypted_GMT);
		copyAndRenameFile();
		
		
		// Configure Bonus Article and Bonus Reward
		openBrowser("joomla");
		joomla.login.login();

		String bonusRewardArticle="Bonus Reward for Full Registration";
		String triggerArticle="FullReg_Complete";
		
		joomla.article.navigateToArticleManager();
		joomla.PA.filterArticleByNames("14", bonusRewardArticle);
		joomla.PA.editArticle(bonusRewardArticle, "publish");
		metaBonus.configureBonusAward("3000", "1", "5000");
		joomla.alias.saveAndClose();

		// Configure Bonus Trigger Frequency as All time.
		joomla.PA.filterArticleByNames("57", triggerArticle);
		joomla.PA.editArticle(triggerArticle, "publish");
		Btrigger.configureTriggerOptions("complete", "FullReg", "onetime");
		joomla.alias.saveAndClose();

		// Configure Meta Action Bonus in PCH App article
		joomla.PA.filterArticleByNames("99", "PCH App");
		joomla.PA.editArticle("PCH App", "publish");
		joomla.PA.bonusActionArticle(bonusRewardArticle, triggerArticle);
		//joomla.PA.bonusActionArticlesTrigger(triggerArticle, 0);
		
		joomla.alias.saveAndClose();

	}
	
	@Test(groups = {GroupName.PCHApp ,GroupName.Regression})
	public void MT2_PCHAPP_MetaActionBonusGameSupport_FullRegComplete() throws Exception {
		
	

		//************** First Thread for Bonus Submit  ********************************
	//	enableThread(jmeterScriptName+".jmx", threadGroup1);
		Log.info("Start Executing Jmeter Script");
		  
		CallJmeterBuild(jmeterScriptName+".jmx"); 
		Log.info("Calling  Jmeter file ");
		 
		Assert.assertEquals("false",xp.Xml_Parser(jmeterScriptName+".xml")); 
		Log.info("Validating assertion ");
		
		//Bonus status validation from All Time table
		Assert.assertEquals("{\"fullRegBonus\":3}", DB.phpctrl_app_bonus_items_Alltime(GMT, "get Bonus data"));
		//Bonus status validation from Bonus Item table
		Assert.assertTrue(DB.phpctrl_app_bonus_items(GMT,"get Bonus data").contains("status\":3"));
		
			
		
	/*
		//****************************second Thread for Bonus Submit with Different Token Multiplier  *************************
		
	//	enableThread(jmeterScriptName + ".jmx", threadGroup2);
		joomla.article.navigateToArticleManager();
		joomla.PA.filterArticleByNames("14", "Bonus Reward for FB Connect");
		joomla.PA.editArticle("Bonus Reward for FB Connect", "publish");
		metaBonus.configureBonusAward("2000", "4", "1000");
		joomla.alias.saveAndClose();

		Log.info("**** Creating Third user from RF ****");

		response = RF.createFullRegUserAPI();
		email = RF.getResponseValueAPI(response, ResponseData.Email);
		GMT = RF.getResponseValueAPI(response, ResponseData.GlobalMemberToken);
		Log.info("Third user email is " + email);
	
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
	public void tearDown() {
		closeallwindows();
		endTestCase("End");

	}
}


