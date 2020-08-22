package com.pch.joomla.configuration;

import static com.pch.utilities.Log.Log;

import java.io.IOException;

import org.openqa.selenium.By;

import com.pch.utilities.Action_Wrapper;

public class MidTierApiConfiguration extends Action_Wrapper {
	// Page Locators
	By RFtext_box = By.xpath("//*[@id='jform_rf_url']");
	By validationAPI = By.id("jform_valid_url");
	By LevelingText_box = By.id("jform_levelapi_url");
	By SegmentText_box = By.id("jform_seg_base_url");
	By DeviceAnalyzerText_box = By.id("jform_da_base_url");
	By IWEtext_box = By.id("jform_iwe_url");
	By save = By.xpath(".//*[@id='component-form']/fieldset/div[1]/button[1]");
	By savendClose = By.xpath("/html/body/form/fieldset/div[1]/button[1]");
	
	
	
	By serviceurl = By.xpath("//dl[@id='config-tabs-com_pchmidtier_api_configuration']/dt[9]/span/h3/a");
	By lottoStoreGame = By.id("jform_lotto_store_game_url");
	By lottoSubmittedNumbers = By.id("jform_lotto_submitted_numbers_url");
	By lottoWinningNumbers = By.id("jform_lotto_winning_numbers_url");
	By LottoGetGames = By.id("jform_lotto_get_games_url");
	By BHM = By.id("jform_bmh_api_url");
	By RulesFacts_base_URL = By.id("jform_rulesfacts_url");
	By FpasswordResetURL = By.id("jform_forgot_reset_url");
	By Taxi_Eamil_URL = By.id("jform_email_taxi_base_url");

	public enum ServiceURL {
		Registration_Foundation, Reset_Password, RF_Media,Segmentation, Device_Analyzer,Instant_Win_Engine,Profile,Fusion,Fusion_Place_Order,TAXI_EMAIL,Leveling,Device_Analyzer_Base_URL,Segmentation_Base_URL,Lotto_Store_Game_URL,Lotto_Submitted_Numbers_URL,Lotto_Winning_Numbers_URL,Lotto_Get_Games_URL,BillMeHandler,RulesFacts_base_URL,Taxi_Email,RFValidation_url; 
	}
	
	
	public void navigateToAPIComponents() throws IOException, Exception {
		Log.info("Navigating to com_pchmidtier_api_configuration");
		MTDriver.navigate().to(returnPropertyValue("Joomla_URL")+returnPropertyValue("Joomla_API_Components"));
		clickServiceTAB();
		waitForPageToLoad(MTDriver);
	}
	

	public void servicesUrl(String action, String serviceName) throws IOException, Exception {
		
		if(serviceName.equalsIgnoreCase("Reg Foundation")) {
			Log.info("Perform action on MT2 Service API URL's  :-" + action);
			if(action.equalsIgnoreCase("Alter URL"))
				textbox(MTDriver, RFtext_box, "enter", returnPropertyValue("InValidRF_URL"));
			else if(action.equalsIgnoreCase("Valid URL"))
				textbox(MTDriver, RFtext_box, "enter", returnPropertyValue("ValidRF_URL"));
		}
	}
	
	public void servicesUrl(String action, ServiceURL serviceName) throws IOException, Exception {
		
		if(serviceName.toString().equalsIgnoreCase("Registration_Foundation")){
			if(action.equalsIgnoreCase("Alter URL"))
				textbox(MTDriver, RFtext_box, "enter", returnPropertyValue("InValidRF_URL"));
			else if(action.equalsIgnoreCase("Valid URL"))
				textbox(MTDriver, RFtext_box, "enter", returnPropertyValue("ValidRF_URL"));
		}
		else if(serviceName.toString().equalsIgnoreCase("Device_Analyzer_Base_URL")){
			if(action.equalsIgnoreCase("Alter URL"))
				textbox(MTDriver, DeviceAnalyzerText_box, "enter", returnPropertyValue("InValid_AppHealth_Device_Analyzer_URL"));
			else if(action.equalsIgnoreCase("Valid URL"))
				textbox(MTDriver, DeviceAnalyzerText_box, "enter", returnPropertyValue("Valid_AppHealth_Device_Analyzer_URL"));
		}
		else if(serviceName.toString().equalsIgnoreCase("Segmentation_Base_URL")){
			if(action.equalsIgnoreCase("Alter URL"))
				textbox(MTDriver, SegmentText_box, "enter", returnPropertyValue("InValid_AppHealth_Segmentation_URL"));
			else if(action.equalsIgnoreCase("Valid URL"))
				textbox(MTDriver, SegmentText_box, "enter", returnPropertyValue("Valid_AppHealth_Segmentation_URL"));
		}
		else if(serviceName.toString().equalsIgnoreCase("Instant_Win_Engine")) {
			if(action.equalsIgnoreCase("Alter URL"))
				textbox(MTDriver, IWEtext_box, "enter", returnPropertyValue("InValid_IWE_URL"));
			else if(action.equalsIgnoreCase("Alter URL1"))
				textbox(MTDriver, IWEtext_box, "enter", returnPropertyValue("InValid_IWE_URL1"));
			else if(action.equalsIgnoreCase("Valid URL"))
				textbox(MTDriver, IWEtext_box, "enter", returnPropertyValue("Valid_IWE_URL"));
		}
		else if(serviceName.toString().equalsIgnoreCase("Leveling")) {
			if(action.equalsIgnoreCase("Alter URL"))
				textbox(MTDriver, LevelingText_box, "enter", returnPropertyValue("InValid_Levelling"));
			else if(action.equalsIgnoreCase("Valid URL"))
				textbox(MTDriver, LevelingText_box, "enter", returnPropertyValue("Valid_Levelling"));
		}
		else if(serviceName.toString().equalsIgnoreCase("Lotto_Store_Game_URL")) {
			if(action.equalsIgnoreCase("Alter URL"))
				textbox(MTDriver, lottoStoreGame, "enter", returnPropertyValue("InValid_Lotto_Store_Game_URL"));
			else if(action.equalsIgnoreCase("Valid URL"))
				textbox(MTDriver, lottoStoreGame, "enter", returnPropertyValue("Valid_Lotto_Store_Game_URL"));
		}
		else if(serviceName.toString().equalsIgnoreCase("Lotto_Submitted_Numbers_URL")) {
			if(action.equalsIgnoreCase("Alter URL"))
				textbox(MTDriver, lottoSubmittedNumbers, "enter", returnPropertyValue("InValid_Submitted_Numbers_Game_URL"));
			else if(action.equalsIgnoreCase("Valid URL"))
				textbox(MTDriver, lottoSubmittedNumbers, "enter", returnPropertyValue("Valid_Submitted_Numbers_Game_URL"));
		}
		else if(serviceName.toString().equalsIgnoreCase("Lotto_Winning_Numbers_URL")) {
			if(action.equalsIgnoreCase("Alter URL"))
				textbox(MTDriver, lottoWinningNumbers, "enter", returnPropertyValue("InValid_Winning_Numbers_Game_URL"));
			else if(action.equalsIgnoreCase("Valid URL"))
				textbox(MTDriver, lottoWinningNumbers, "enter", returnPropertyValue("Valid_Winning_Numbers_Game_URL"));
		}
		else if(serviceName.toString().equalsIgnoreCase("Lotto_Get_Games_URL")) {
			if(action.equalsIgnoreCase("Alter URL"))
				textbox(MTDriver, LottoGetGames, "enter", returnPropertyValue("InValid_Lotto_Get_Games_URL"));
			else if(action.equalsIgnoreCase("Alter URL1"))
				textbox(MTDriver, LottoGetGames, "enter", returnPropertyValue("InValid_Lotto_Get_Games_URL1"));
			else if(action.equalsIgnoreCase("Valid URL"))
				textbox(MTDriver, LottoGetGames, "enter", returnPropertyValue("Valid_Lotto_Get_Games_URL"));
			
		} 
		else if(serviceName.toString().equalsIgnoreCase("BillMeHandler")) {
			if(action.equalsIgnoreCase("Alter URL"))
				textbox(MTDriver, BHM, "enter", returnPropertyValue("Invalid_BMH"));
			else if(action.equalsIgnoreCase("Valid URL"))
				textbox(MTDriver, BHM, "enter", returnPropertyValue("Valid_BMH"));
			
		} 
		else if(serviceName.toString().equalsIgnoreCase("RulesFacts_base_URL")) {
			if(action.equalsIgnoreCase("Alter URL"))
				textbox(MTDriver, RulesFacts_base_URL, "enter", returnPropertyValue("InValid_RulesFacts_base_URL"));
			else if(action.equalsIgnoreCase("Valid URL"))
				textbox(MTDriver, RulesFacts_base_URL, "enter", returnPropertyValue("Valid_RulesFacts_base_URL"));
		} 
		else if(serviceName.toString().equalsIgnoreCase("Reset_Password")) {
			if(action.equalsIgnoreCase("Alter URL"))
				textbox(MTDriver, FpasswordResetURL, "enter", "");
			else if(action.equalsIgnoreCase("Valid URL"))
				textbox(MTDriver, FpasswordResetURL, "enter", returnPropertyValue("ValidForgotPassword_URL"));
		}
		else if(serviceName.toString().equalsIgnoreCase("RFValidation_url")) {
			if(action.equalsIgnoreCase("Alter URL"))
				textbox(MTDriver, validationAPI, "enter", returnPropertyValue("InValid_RFValidationAPI"));
			else if(action.equalsIgnoreCase("Valid URL"))
				textbox(MTDriver, validationAPI, "enter", returnPropertyValue("Valid_RFValidationAPI"));
		}
		else if(serviceName.toString().equalsIgnoreCase("Taxi_Email")) {
			if(action.equalsIgnoreCase("Alter URL"))
				textbox(MTDriver, Taxi_Eamil_URL, "enter", returnPropertyValue("InValid_Taxi_Email_URL"));
			else if(action.equalsIgnoreCase("Valid URL"))
				textbox(MTDriver, Taxi_Eamil_URL, "enter", returnPropertyValue("Valid_Taxi_Email_URL"));
		}
		
	}
	
/*	
	public void saveAndClose() {
		Log.info("Save and Close the Article");
		link(MTDriver, save, "click");
		waitForPageToLoad(MTDriver);
		link(MTDriver, savendClose, "click");
	}*/
	
	public void saveAndClose() {
		Log.info("Click at SaveandClose button to close the midtier api configuration Article");
		ClickButton(savendClose);
		 
	}

	
	
	public void clickServiceTAB() {
		Log.info("Click on Services URL TAB");
		link(MTDriver, serviceurl, "click");
	}
	
}
