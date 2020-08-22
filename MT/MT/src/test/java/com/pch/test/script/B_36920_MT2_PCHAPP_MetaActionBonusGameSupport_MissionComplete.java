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

public class B_36920_MT2_PCHAPP_MetaActionBonusGameSupport_MissionComplete extends Action_Wrapper {
	JoomlaAdministrator joomla = new JoomlaAdministrator();
	MetaActionBonusArticle metaBonus = new MetaActionBonusArticle();
	Database_Configuration DB = new Database_Configuration();
	BonusTriggers Btrigger = new BonusTriggers();
	String jmeterScriptName = this.getClass().getSimpleName();
	private String response,GMT,Encrypted_Email,Encrypted_GMT,email;
	RFTestPage RF = new RFTestPage();
	Jmeter_Xml_Parser xp = new Jmeter_Xml_Parser();
	
	String threadGroup1[]={"BonusSubmit"};
	String threadGroup2[]={"BonusSubmit WithOut MissionCom"};
	String threadGroup3[]={"BonusPlayedError"};
	String threadGroup4[]={"AppLoad1"};
	String threadGroup5[]={"GameComplete"};
	String threadGroup6[]={"TokenHistory -2000"};

	
	@BeforeClass
	public void setup() throws Exception {
		
		startTestCase(jmeterScriptName);
		createAccessTokenForAllApp("pchapp");

		Log.info("***Creating Full Reg user***");
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

		joomla.article.navigateToArticleManager();
		joomla.PA.filterArticleByNames("14", "Bonus Reward for Mission Complete");
		joomla.PA.editArticle("Bonus Reward for Mission Complete", "publish");
		metaBonus.configureBonusAward("2000", "2", "4000");
		joomla.alias.saveAndClose();


		// Configure Meta Action Bonus in PCH App article
		joomla.PA.filterArticleByNames("99", "PCH App");
		joomla.PA.editArticle("PCH App", "publish");
		joomla.PA.bonusActionArticle("Bonus Reward for Mission Complete","Content_complete");		
		joomla.PA.bonusActionArticlesVar("Bonus Reward for Mission Complete","1");
		
		joomla.PA.featureFieldSelectArticle("PCHAPP Sweeps Content Path");
		joomla.alias.saveAndClose();

		//Configure Action article in Mission
		joomla.PA.filterArticleByNames("73", "PCHAPP Sweeps Content Path");
		joomla.PA.editArticle("PCHAPP Sweeps Content Path", "publish");
		joomla.ML.removeActionArticle();
		joomla.ML.selectActionArticleDropdown("Standalone Sweepstake Game 1", 0);
		joomla.alias.saveAndClose();

	//	DB.flush("PchApp", "DELETE FROM `phpctrl_app_content_tree`");
		
		
	}
	
	@Test(groups = {GroupName.Game ,GroupName.Regression})
	public void story_B_36920_MT2_PCHAPP_MetaActionBonusGameSupport_MissionComplete() throws Exception {

		//************** App Load  **************
		enableThread(jmeterScriptName+".jmx", threadGroup4);
		Log.info("Start Executing Jmeter Script");
		  
		CallJmeterBuild(jmeterScriptName+".jmx"); 
		Log.info("Calling  Jmeter file ");
		 
		Assert.assertEquals("false",xp.Xml_Parser(jmeterScriptName+".xml")); 
		Log.info("Validating assertion ");
		
		
		// Bonus Submit Without Completing the Mission
		enableThread(jmeterScriptName+".jmx", threadGroup2);
		Log.info("Start Executing Jmeter Script");
		  
		CallJmeterBuild(jmeterScriptName+".jmx"); 
		Log.info("Calling  Jmeter file ");
		 
		Assert.assertEquals("false",xp.Xml_Parser(jmeterScriptName+".xml")); 
		Log.info("Validating assertion ");
		
		// Game Complete
		enableThread(jmeterScriptName+".jmx", threadGroup5);
		Log.info("Start Executing Jmeter Script");
		  
		CallJmeterBuild(jmeterScriptName+".jmx"); 
		Log.info("Calling  Jmeter file ");
		 
		Assert.assertEquals("false",xp.Xml_Parser(jmeterScriptName+".xml")); 
		Log.info("Validating assertion ");
		
		// Bonus Submit after Completing the Mission
		enableThread(jmeterScriptName+".jmx", threadGroup1);
		Log.info("Start Executing Jmeter Script");
		  
		CallJmeterBuild(jmeterScriptName+".jmx"); 
		Log.info("Calling  Jmeter file ");
		 
		Assert.assertEquals("false",xp.Xml_Parser(jmeterScriptName+".xml")); 
		Log.info("Validating assertion ");

		// Validate Token History for USER
		enableThread(jmeterScriptName+".jmx", threadGroup6);
		Log.info("Start Executing Jmeter Script");
		  
		CallJmeterBuild(jmeterScriptName+".jmx"); 
		Log.info("Calling  Jmeter file ");
		 
		Assert.assertEquals("false",xp.Xml_Parser(jmeterScriptName+".xml")); 
		Log.info("Validating assertion ");
		
		// Bonus reSubmit
		enableThread(jmeterScriptName+".jmx", threadGroup3);
		Log.info("Start Executing Jmeter Script");
		  
		CallJmeterBuild(jmeterScriptName+".jmx"); 
		Log.info("Calling  Jmeter file ");
		 
		Assert.assertEquals("false",xp.Xml_Parser(jmeterScriptName+".xml")); 
		Log.info("Validating assertion ");
		
	}
	@AfterClass
	public void tearDown() {
		closeallwindows();
		endTestCase("End");

	}
}


