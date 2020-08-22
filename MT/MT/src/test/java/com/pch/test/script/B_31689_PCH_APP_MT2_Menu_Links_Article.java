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
import com.pch.joomla.configuration.JoomlaAdministrator;
import com.pch.utilities.Action_Wrapper;
import com.pch.utilities.GroupName;
import com.pch.utilities.Jmeter_Xml_Parser;

public class B_31689_PCH_APP_MT2_Menu_Links_Article extends Action_Wrapper {
	
	String response;
	RFTestPage RF = new RFTestPage();
	JoomlaAdministrator joomla = new JoomlaAdministrator();
	Jmeter_Xml_Parser xp = new Jmeter_Xml_Parser();
	String threadGroup[] = {"Without Menu Link"};
	
	@BeforeClass
	public void setup() throws Exception {
		startTestCase("B_31689_PCH_APP_MT2_Menu_Links_Article");
		createAccessTokenForAllApp("pchapp");

		response=RF.createFullRegUserAPI();
		writeInProprtyFile("Encrypted_FullRegEmail", getEncryptedData("Access_Token_PCHAPP", RF.getResponseValueAPI(response,ResponseData.Email)));
		writeInProprtyFile("Encrypted_GMT", getEncryptedData("Access_Token_PCHAPP", RF.getResponseValueAPI(response,ResponseData.GlobalMemberToken)));
		copyAndRenameFile();
		
		disableThread("B_31689_PCH_APP_MT2_Menu_Links_Article.jmx", threadGroup);
		
		openBrowser("joomla");
		joomla.login.login();
		joomla.article.navigateToArticleManager();
		
		joomla.article.selectcategoryDropDown("- Pch App");
		joomla.article.search("Pch App Test 1","search");
		joomla.article.articleStatus("unpublish");
		joomla.article.search("PCH App","search");
		joomla.article.articleStatus("unpublish");
		joomla.article.search("PCH APP old QA","search");
		joomla.article.articleStatus("publish");
		joomla.article.search("PCH APP old QA","ALIAS");
		joomla.alias.finishPublishingDate("");
		joomla.alias.saveAndClose();
		
		
		joomla.article.search("PCH APP old QA");
		joomla.PA.selectMenuLinkArticle("Menu Links");
		joomla.article.saveArticle();
		
		waitForPageToLoad(MTDriver);
		joomla.article.selectcategoryDropDown("PchApp");
		
		joomla.article.search("menu-links");
		joomla.ML.removeActionArticle();
		joomla.ML.selectActionArticleDropdown("FAQ", 0);
		joomla.article.saveArticle();

		joomla.article.search("FAQ");
		joomla.actions.addActionType("crosspromo");
		joomla.actions.addAction("openwebview");
		joomla.actions.addActionURL("http://pchapps.custhelp.com/app/home");
		joomla.actions.addCredentials("100");
		
		joomla.actions.addcrmN("crmN detail");
		joomla.actions.addDescription("Contact for help");

		joomla.actions.addRulesFactsId("22");
		joomla.actions.addPCHGameClassName("33");
		joomla.actions.featureIcon2x("upload");
		joomla.actions.featureIcon4x("upload");
		joomla.actions.tileIcon2x("upload");
		joomla.actions.tileIcon4x("upload");
		joomla.actions.addTopColor("#ffffff");
		joomla.actions.addBottomColor("#000000");
		joomla.actions.addButtonColor("#bce4ac");
		joomla.actions.addButtonText("Button Text");
		joomla.article.saveArticle();
		
	}

	@Test(groups = { GroupName.RegFoundation, GroupName.Game })
	public void story_B_31689_PCH_APP_MT2_Menu_Links_Article() throws Exception {
		Log.info("Start Executing Jmeter Script");
		
		CallJmeterBuild("B_31689_PCH_APP_MT2_Menu_Links_Article.jmx");
		Log.info("calling  jmeter file ");

		Assert.assertEquals("false", xp.Xml_Parser("B_31689_PCH_APP_MT2_Menu_Links_Article.xml"));
		Log.info("Validating assertion ");
		
		enableThread("B_31689_PCH_APP_MT2_Menu_Links_Article.jmx", threadGroup);
		joomla.article.navigateToArticleManager();
		joomla.article.search("PCH APP old QA");
		joomla.PA.selectMenuLinkArticle("- Select an Article -");
		joomla.article.saveArticle();
		
		CallJmeterBuild("B_31689_PCH_APP_MT2_Menu_Links_Article.jmx");
		Log.info("calling  jmeter file ");

		Assert.assertEquals("false", xp.Xml_Parser("B_31689_PCH_APP_MT2_Menu_Links_Article.xml"));
		Log.info("Validating assertion ");
	}

	@AfterClass
	public void tearDown() {
		closeallwindows();
		endTestCase("End");
	}
}