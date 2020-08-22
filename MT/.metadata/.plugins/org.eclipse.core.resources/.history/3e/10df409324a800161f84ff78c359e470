package com.pch.test.script;

import static com.pch.utilities.Log.Log;
import static com.pch.utilities.Log.endTestCase;
import static com.pch.utilities.Log.startTestCase;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.pch.joomla.configuration.DataPassThrough;
import com.pch.joomla.configuration.JoomlaAdministrator;
import com.pch.utilities.Action_Wrapper;
import com.pch.utilities.GroupName;
import com.pch.utilities.Jmeter_Xml_Parser;

public class B_36414_MT2_All_Apps_SetupJoomlaConfigured_Passthrough_of_Data_XMLFormat extends Action_Wrapper {
	Jmeter_Xml_Parser xp = new Jmeter_Xml_Parser();
	JoomlaAdministrator joomla = new JoomlaAdministrator();
	DataPassThrough DPT = new DataPassThrough();
	String scriptName = this.getClass().getSimpleName();

	String thread[] = { "IWE POST REQUEST" };
	String thread1[] = { "IWE GET REQUEST" };
	String thread2[] = { "Passthrough Article Expire" };
	String thread3[] = { "Target URL Missing" };

	String TokenBalanceURL = "http://iwe.qa.pch.com/iwe/services/tokenbank/balance";
	String isWinnerURL = "https://iwe.qa.pch.com/iwe/services/isWinner/a121ecf0643c8330f9f1ae48da1de641/53/1723/spal@pch.com";

	@BeforeClass
	public void setup() throws Exception {
		
		startTestCase("B_36414_MT2_All_Apps_SetupJoomlaConfigured_Passthrough_of_Data_XMLFormat");
		createAccessTokenForAllApp("pchapp");
		openBrowser("chrome");
		joomla.login.login();
		joomla.article.navigateToArticleManager();

	
		// Configure Pass through Article for POST.
		joomla.PA.filterArticleByNames("98", "IWE Token Balance");
		joomla.PA.editArticle("IWE Token Balance", "publish");
		DPT.configureDataPassThrough(TokenBalanceURL, "POST", "XML");
		joomla.alias.saveAndClose();
		
	}

	@Test (groups = { GroupName.Regression, GroupName.DPassThrough})
	public void strory_B_36414_MT2_All_Apps_SetupJoomlaConfigured_Passthrough_of_Data_XMLFormat()throws Exception {

		// Scenarios: IWE POST REQUEST
		enableThread(scriptName + ".jmx", thread);
		Log.info("Executing IWE POST REQUEST JmeterThread as precondition.");
		CallJmeterBuild(scriptName + ".jmx");
	
		Assert.assertEquals("false", xp.Xml_Parser(scriptName+".xml"));

		// Scenarios: IWE GET REQUEST
		
		// Configure Pass through Article for GET.
		joomla.PA.filterArticleByNames("98", "IW isWinner");
		joomla.PA.editArticle("IW isWinner", "publish");
		DPT.configureDataPassThrough(isWinnerURL, "GET", "JSON");
		joomla.alias.saveAndClose();
		
		enableThread(scriptName + ".jmx", thread1);
		Log.info("Executing IWE GET REQUEST Thread as Valid Scenario.");
		CallJmeterBuild(scriptName + ".jmx");
		
		Assert.assertEquals("false", xp.Xml_Parser(scriptName+".xml"));
		
		// 	Scenario: Passthrough Article Expire
		
		// Expire the Pass through article
		joomla.PA.filterArticleByNames("98", "IW isWinner");
		joomla.PA.editArticle("IW isWinner", "unpublish");
		joomla.alias.saveAndClose();

		enableThread(scriptName + ".jmx", thread2);
		Log.info("Executing Passthrough Article Expire Thread as Valid Scenario.");
		CallJmeterBuild(scriptName + ".jmx");
		Assert.assertEquals("false", xp.Xml_Parser(scriptName+".xml"));
		
		// Revert the changes and remove the target URL

		joomla.PA.filterArticleByNames("98", "IW isWinner");
		joomla.PA.editArticle("IW isWinner", "publish");
		DPT.configureDataPassThrough("", "GET", "JSON");
		joomla.alias.saveAndClose();
	
		enableThread(scriptName + ".jmx", thread3);
		Log.info("Executing Target URL Missing Thread ");
		CallJmeterBuild(scriptName + ".jmx");
		
		Assert.assertEquals("false", xp.Xml_Parser(scriptName+".xml"));
	}

	@AfterClass
	public void tearDown() throws IOException, Exception {
		
		// Revert Joomla Configuration
		joomla.PA.filterArticleByNames("98", "IW isWinner");
		joomla.PA.editArticle("IW isWinner", "publish");
		DPT.configureDataPassThrough(isWinnerURL, "GET", "JSON");
		joomla.alias.saveAndClose();
		
		closeallwindows();
		endTestCase("End");
	}
}