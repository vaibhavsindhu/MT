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

public class B_36918_MT2_PCHApp_MetaActionBonusGameSupport_FriendsInvite_Sweepstakes extends Action_Wrapper {
	JoomlaAdministrator joomla = new JoomlaAdministrator();
	MetaActionBonusArticle metaBonus = new MetaActionBonusArticle();
	Database_Configuration DB = new Database_Configuration();
	BonusTriggers Btrigger = new BonusTriggers();
	MidTierApiConfiguration MTAPIConfig=new MidTierApiConfiguration();
	
	String GMT,response;
	String threadGroup[]={"GameStart - Valid"};

	RFTestPage RF = new RFTestPage();
	Jmeter_Xml_Parser xp = new Jmeter_Xml_Parser();
	String jmeterScriptName = "B_36918_MT2_PCHApp_MetaActionBonusGameSupport_FriendsInvite_Sweepstakes";

	
	@BeforeClass
	public void setup() throws Exception {
		
		startTestCase("B_36918_MT2_PCHApp_MetaActionBonusGameSupport_FriendsInvite_Sweepstakes");
		createAccessTokenForAllApp("pchapp");

		// Configure Bonus Article and Bonus Reward
		
		openBrowser("joomla");
		joomla.login.login();
		joomla.article.navigateToArticleManager();
		
		//Configure Meta Action Bonus in PCH App article
		joomla.PA.filterArticleByNames("99", "PCH App");
		joomla.PA.editArticle("PCH App", "publish");
		joomla.PA.bonusActionArticle("Standalone Sweeps game","Friends_Invite");		
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
	public void story_B_36918_MT2_PCHApp_MetaActionBonusGameSupport_FriendsInvite_Sweepstakes() throws Exception {

		//******* Thread Execution for Bonus Submit for SweepStakes as Daily Bonus
		Log.info("Start Executing Jmeter Script");
		  
		CallJmeterBuild(jmeterScriptName+".jmx"); 
		Log.info("Calling  Jmeter file ");
		 
		Assert.assertEquals("false",xp.Xml_Parser(jmeterScriptName+".xml")); 
		Log.info("Validating assertion ");
		
		//Bonus status validation from Bonus Item table
		Assert.assertTrue(DB.phpctrl_app_bonus_items(GMT,"get Bonus data").contains("{\"324\":{\"status\":3,\"data\":{\"buid\":\"53\"}}}"));
		
		
		//******* Thread Execution for Bonus Submit for SweepStakes as All Time Bonus
		
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

		Log.info("Start Executing Jmeter Script");
		CallJmeterBuild(jmeterScriptName+".jmx"); 
		Log.info("Calling  Jmeter file ");
		 
		Assert.assertEquals("false",xp.Xml_Parser(jmeterScriptName+".xml")); 
		Log.info("Validating assertion ");
		
		//Bonus status validation from All Time table
		Assert.assertEquals("{\"friends\":3}", DB.phpctrl_app_bonus_items_Alltime(GMT, "get Bonus data"));
		//Bonus status validation from Bonus Item table
		Assert.assertTrue(DB.phpctrl_app_bonus_items(GMT,"get Bonus data").contains("{\"324\":{\"status\":3,\"data\":{\"buid\":\"53\"}}}"));

	}
	
	@AfterClass
	public void tearDown() throws IOException, Exception {

		endTestCase("End");
		closeallwindows();
	}
}