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

public class B_38538_MT2_PCHAPP_MetaActionBonusGameSupport_FullRegComplete_Sweepstakes extends Action_Wrapper{
	JoomlaAdministrator joomla = new JoomlaAdministrator();
	MetaActionBonusArticle metaBonus = new MetaActionBonusArticle();
	Database_Configuration DB = new Database_Configuration();
	BonusTriggers Btrigger = new BonusTriggers();
	String jmeterScriptName = this.getClass().getSimpleName();
	String accessToken,response,GMT,Encrypted_Email,Encrypted_GMT,Email;
	RFTestPage RF = new RFTestPage();
	Jmeter_Xml_Parser xp = new Jmeter_Xml_Parser();	


	@BeforeClass
	public void setup() throws Exception {

		String bonusRewardArticle="Bonus Reward for Full Registration";
		String triggerArticle="FullReg_Complete";

		startTestCase(jmeterScriptName);
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
		joomla.PA.filterArticleByNames("14", bonusRewardArticle);
		joomla.PA.editArticle(bonusRewardArticle, "publish");
		metaBonus.configureBonusAward("3000", "1", "5000");
		joomla.alias.saveAndClose();

		// Configure Bonus Trigger Frequency as All time.
		joomla.PA.filterArticleByNames("57", triggerArticle);
		joomla.PA.editArticle(triggerArticle, "publish");
		Btrigger.configureTriggerOptions("complete", "FullReg", "onetime");
		joomla.alias.saveAndClose();


		// Configure Standalone Sweeps game in PCH App article
		joomla.PA.filterArticleByNames("99", "PCH App");
		joomla.PA.editArticle("PCH App", "publish");
		joomla.PA.bonusActionArticle("Standalone Sweeps game", triggerArticle);
	//	joomla.PA.bonusActionArticlesTrigger(triggerArticle, 1);

		joomla.alias.saveAndClose();


	}

	@Test(groups = {GroupName.PCHApp ,GroupName.Regression})
	public void story_B_38537_MT2_PCHApp_MetaActionBonusGameSupport_FaceBookConnect_Sweepstakes() throws Exception {

		//************** First Thread for Bonus Submit  ********************************		
		Log.info("Start Executing Jmeter Script");

		CallJmeterBuild(jmeterScriptName+".jmx"); 
		Log.info("Calling  Jmeter file ");

		Assert.assertEquals("false",xp.Xml_Parser(jmeterScriptName+".xml")); 
		Log.info("Validating assertion ");

		//Bonus status validation from All Time table
		Assert.assertEquals("{\"fullRegBonus\":3}", DB.phpctrl_app_bonus_items_Alltime(GMT, "get Bonus data"));
		//Bonus status validation from Bonus Item table
		Assert.assertTrue(DB.phpctrl_app_bonus_items(GMT,"get Bonus data").contains("status\":3"));

	}


	@AfterClass
	public void tearDown() throws IOException, Exception {

		endTestCase("End");
		closeallwindows();
	}
}
