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

public class B_38538_MT2_PCHAPP_MetaActionBonusGameSupport_FullRegComplete_Lotto extends Action_Wrapper{
	JoomlaAdministrator joomla = new JoomlaAdministrator();
	MetaActionBonusArticle metaBonus = new MetaActionBonusArticle();
	Database_Configuration DB = new Database_Configuration();
	BonusTriggers Btrigger = new BonusTriggers();
	
	String GMT,response;
	//String threadGroup[]={"GameStart - FriendInvite Bonus"};

	RFTestPage RF = new RFTestPage();
	Jmeter_Xml_Parser xp = new Jmeter_Xml_Parser();
	String jmeterScriptName = this.getClass().getSimpleName();
	String bonusRewardArticle="Bonus Reward for Full Registration";
	String triggerArticle="FullReg_Complete";
	
	@BeforeClass
	public void setup() throws Exception {
		
		startTestCase(jmeterScriptName);
		createAccessTokenForAllApp("pchapp");

		// Configure Bonus Article and Bonus Reward
		
		openBrowser("joomla");
		joomla.login.login();
		joomla.article.navigateToArticleManager();
		
		//Configure Meta Action Bonus in PCH App article
		joomla.PA.filterArticleByNames("99", "PCH App");
		joomla.PA.editArticle("PCH App", "publish");
		joomla.PA.bonusActionArticle("Lotto Bonus",triggerArticle);
		//joomla.PA.bonusActionArticlesTrigger(triggerArticle, 1);
		joomla.alias.saveAndClose();
		
		//Configure Bonus Trigger Frequency as All time.
		joomla.PA.filterArticleByNames("57", triggerArticle);
		joomla.PA.editArticle(triggerArticle, "publish");
		Btrigger.configureTriggerOptions("complete", "FullReg", "onetime");
		joomla.alias.saveAndClose();
		
		//DB.flush("PchApp", "DELETE FROM `phpctrl_app_content_tree`");
		
		//Create Full Reg User
		response=RF.createFullRegUserAPI();
		GMT = RF.getResponseValueAPI(response,ResponseData.GlobalMemberToken);
		writeInProprtyFile("Encrypted_Email", getEncryptedData("Access_Token_PCHAPP", RF.getResponseValueAPI(response,ResponseData.Email)));
		writeInProprtyFile("Encrypted_GMT", getEncryptedData("Access_Token_PCHAPP",GMT));
		copyAndRenameFile();
	}
	
	@Test(groups = {GroupName.PCHApp ,GroupName.Regression})
	public void story_B_38538_MT2_PCHAPP_MetaActionBonusGameSupport_FullRegComplete_Lotto() throws Exception {

		//******* Thread Execution for Bonus Submit for Lotto as Daily Bonus
		Log.info("Start Executing Jmeter Script");
		  
		CallJmeterBuild(jmeterScriptName+".jmx"); 
		Log.info("Calling  Jmeter file ");
		 
		Assert.assertEquals("false",xp.Xml_Parser(jmeterScriptName+".xml")); 
		Log.info("Validating assertion ");
		
		//Bonus status validation from Bonus Item table
		Assert.assertTrue(DB.phpctrl_app_bonus_items(GMT,"get Bonus data").contains("{\"29\":{\"status\":3,\"data\":{\"buid\":\"53\"}}}"));
		
		
		

	}
	
	@AfterClass
	public void tearDown() throws IOException, Exception {

		endTestCase("End");
		closeallwindows();
	}
}
