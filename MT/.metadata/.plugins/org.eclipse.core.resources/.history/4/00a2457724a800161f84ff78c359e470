package com.pch.joomla.configuration;

import static com.pch.utilities.Log.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.pch.utilities.Action_Wrapper;

public class ArticleManager extends Action_Wrapper {
	
	public void navigateToArticleManager() {
		Log.info("Navigating to Article Manager");
		try {
			MTDriver.get(returnPropertyValue("Joomla_URL")+returnPropertyValue("Joomla_Article_Manager"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void search(String... search_text) {
		Log.info("Searching for artcile  :- " + search_text[0]);
		textbox(MTDriver, By.xpath("//*[@id='filter_search']"), "enter", search_text[0]);
		waitForPageToLoad(MTDriver);
		link(MTDriver, By.xpath("//*[@id='filter-bar']/div[1]/button[1]"), "click");
		waitForPageToLoad(MTDriver);
		if(search_text.length==1) {
			link(MTDriver, By.xpath(".//*[@id='adminForm']/table/tbody/tr/td[2]/a"), "click");
		}
		else if(search_text.length==2 && search_text[1].equalsIgnoreCase("ALIAS")) {
			link(MTDriver, By.xpath("html/body/div[3]/div[4]/div/form/table/tbody/tr[1]/td[2]/p/a"), "click");
		}else if(search_text.length==2 && search_text[1].equalsIgnoreCase("Article id")) {
			waitForElement(MTDriver, By.xpath("html/body/div[3]/div[4]/div/form/table/tbody/tr[1]/td[12]"), 10);
		}else if(search_text.length==2 && search_text[1].equalsIgnoreCase("search")) {
			waitForElement(MTDriver, By.xpath("html/body/div[3]/div[4]/div/form/table/tbody/tr[1]/td[12]"), 10);
		}
	}
	
	public void appConfigDataEncryption(String action){
		Log.info("Action Performing on Data Encryption Text Box  :-" + action);
		if(action.equalsIgnoreCase("Alter Key"))
			textbox(MTDriver, By.id("blowfish_hash_key"), "enter", "AC-6FA97d03_569E5bD92");
		else if(action.equalsIgnoreCase("clear"))
			textbox(MTDriver, By.id("blowfish_hash_key"), "enter", "");
		else if(action.equalsIgnoreCase("original Key"))
			textbox(MTDriver, By.id("blowfish_hash_key"), "enter", "EC-6FA97d03_569E5bD91");
	}
	
	// This method is to Publish or Unpublish the AppConfig Article
	public void appConfArticleStatus(String Status) {
		waitForPageToLoad(MTDriver);
		Log.info("Publish or Unpublish App Config Article  :-" + Status);
		
		if(Status.equalsIgnoreCase("publish"))
			selectByValue(MTDriver, By.id("art_state"), "select", "1");
		else if(Status.equalsIgnoreCase("unpublish"))
			selectByValue(MTDriver, By.id("art_state"), "select", "0");
	}
	
	public void appConfigFusionAPI(String textfield){
		
		if(textfield.equalsIgnoreCase("lineup id")){
		//	textbox(MTDriver, By.id("lineup_id"), "enter", value);
		}else if(textfield.equalsIgnoreCase("mail id")){
		//	textbox(MTDriver, By.id("mail_id"), "enter", value);
		}
	}
	
	// Update RegFoundation Configuration in Joomla --> App Config --> PLAYNWIN APP
	public void updateRFConfiguration(String updateField, String value) {
		
		if(updateField.equalsIgnoreCase("Appcode")) {
			textbox(MTDriver, By.id("appcode"), "enter", value);
		} else if(updateField.equalsIgnoreCase("Source Code")) {
			textbox(MTDriver, By.id("source_code"), "enter", value);
		} else if(updateField.equalsIgnoreCase("Platform Code")) {
			textbox(MTDriver, By.id("platform_code"), "enter", value);
		} else if(updateField.equalsIgnoreCase("Login Name")) {
			textbox(MTDriver, By.id("login_name"), "enter", value);
		} else if(updateField.equalsIgnoreCase("BUID")) {
			textbox(MTDriver, By.id("business_unit_id"), "enter", value);
		} else if(updateField.equalsIgnoreCase("IWE_deviceID")) {
			textbox(MTDriver, By.id("iw_device_id"), "enter", value);
		} else if(updateField.equalsIgnoreCase("AccessKey")) {
			textbox(MTDriver, By.id("iw_access_key"), "enter", value);
		} else if(updateField.equalsIgnoreCase("ActivityID")) {
			textbox(MTDriver, By.id("iw_activity_id"), "enter", value);
		} else if(updateField.equalsIgnoreCase("IWETokenType")) {
			textbox(MTDriver, By.id("iw_token_type"), "enter", value);
		}
	}
	
	public void saveArticle() {
		Log.info("Saving the Article");
		waitForPageToLoad(MTDriver);
		waitForPageToLoad(MTDriver);
		link(MTDriver, By.xpath("//*[@id='toolbar-o_apply']/a"), "click");
		waitForPageToLoad(MTDriver);
		link(MTDriver, By.xpath("//*[@id='toolbar-o_cancel']/a"), "click");
		link(MTDriver, By.xpath("//*[@id='filter-bar']/div[1]/button[2]"), "click");
	}
	
	public void appConfig_BlowfishKey(String value){
		textbox(MTDriver, By.id("blowfish_hash_key"), "enter", value);
	}
	
	public void appConfigFusionAPI_SegmentSourceKey(String value, int index){
		textbox(MTDriver, By.id("fusion_configuration_"+(index-1)+"_segment_source_key"), "enter", value);
	}
	
	public void appConfigFusionAPI_LineupId(String value, int index){
		textbox(MTDriver, By.id("fusion_configuration_"+(index-1)+"_lineup_id"), "enter", value);
	}
	
	public void appConfigFusionAPI_MailId(String value, int index){
		textbox(MTDriver, By.id("fusion_configuration_"+(index-1)+"_mail_id"), "enter", value);
	}
	
	public void appConfigFusionAPI_RemoveAllField(){
		List<WebElement> element =MTDriver.findElements(By.xpath(".//*[@id='cck1_sortable_fusion_configuration']/div"));
			for (int i = 0; i < element.size(); i++) {
				link(MTDriver, By.xpath(".//*[@id='cck1r_button_fusion_configuration_0']/div[1]"), "click");
			}
	}
	
	public void appConfigFusionAPI_AddField(){
		List<WebElement> element =MTDriver.findElements(By.xpath(".//*[@id='cck1_sortable_fusion_configuration']/div"));
		int size = element.size() -1;
		link(MTDriver, By.xpath(".//*[@id='cck1r_button_fusion_configuration_"+size+"']/div[2]"), "click");
	}
	
	public void appConfigFusionAPI_clickDateIcon(int index) {
		link(MTDriver, By.xpath(".//*[@id='fusion_configuration_"+(index-1)+"_expiration_date-trigger']"), "click");
	}
	
	
	public static String getMonthString(int month) {
	    String[] monthNames = {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
	    return monthNames[month];
	}
	public void appConfigFusionAPI_ExpirationDate(int year, int month, int date) throws InterruptedException {
		waitForPageToLoad(MTDriver);
		Thread.sleep(4222);
		
		link(MTDriver, By.xpath("/html/body/table/tbody/tr/td/div/div[1]/table/tbody/tr/td/div/div"), "click");
		WebDriverWait wait = new WebDriverWait(MTDriver, 120);

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/table/tbody/tr/td/div/div[4]/table/tbody/tr/td/table[1]/tbody/tr[1]/td/input")));
		textbox(MTDriver, By.xpath("/html/body/table/tbody/tr/td/div/div[4]/table/tbody/tr/td/table[1]/tbody/tr[1]/td/input"), "enter", String.valueOf(year));
		waitForPageToLoad(MTDriver);

		Thread.sleep(4222);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='minwidth-body']/table/tbody/tr/td/div/div[4]/table/tbody/tr/td/table[2]/tbody/tr/td/div[text()=\""+getMonthString(month)+"\"]")));

		link(MTDriver, By.xpath(".//*[@id='minwidth-body']/table/tbody/tr/td/div/div[4]/table/tbody/tr/td/table[2]/tbody/tr/td/div[text()=\""+getMonthString(month)+"\"]"), "click");
		Thread.sleep(4222);
		

		waitForPageToLoad(MTDriver);
		int dateFormat =((year*100+month+1)*100)+date;
		System.out.println("*********************************"+dateFormat);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='minwidth-body']/descendant::div[@class=\"DynarchCalendar-body\"]/table[@class=\"DynarchCalendar-bodyTable\"]/tbody/tr/td/div[@dyc-date=\""+dateFormat+"\"]")));

		link(MTDriver, By.xpath(".//*[@id='minwidth-body']/descendant::div[@class=\"DynarchCalendar-body\"]/table[@class=\"DynarchCalendar-bodyTable\"]/tbody/tr/td/div[@dyc-date=\""+dateFormat+"\"]"), "click");
	
	}

	public void searchArticle(String search){
		waitForPageToLoad(MTDriver);
		Log.info("Searching for artcile  :- " + search);
		textbox(MTDriver, By.xpath("//*[@id='filter_search']"), "enter", search);
		link(MTDriver, By.xpath("//*[@id='filter-bar']/div[1]/button[1]"), "click");
	}
	
	//Article Manager --> Dropdown list Select Category
	public void selectcategoryDropDown(String value){
		waitForPageToLoad(MTDriver);
		Log.info("Changing Category Drop Down value to "+value);
		selectoption(By.xpath(".//*[@id='filter-bar']/div[2]/select[2]"), value);
	}
	
	public void selectPrizeArticleDropDown(String value)
	{
		
		Log.info("selecting prize article ");
		//selectoption(By.xpath("//*[@id='prize_article')"), value);
		selectoption(By.id("prize_article"), value);

	}
	
	
	public void selectSiteDropDown(String value) throws InterruptedException{
		selectoption(By.xpath(".//*[@id='site']"), value);
		//Thread.sleep(50000);
	}

	
	
	public void selectcontestDropDown(String value) throws InterruptedException{
		WebDriverWait wait = new WebDriverWait(MTDriver, 45);
		Thread.sleep(10000);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='contest']")));
		selectoption(By.xpath(".//*[@id='contest']"), value);
	}
	
	public void AssertingJoomlaAndGPSContestList(String GPS)
			throws InterruptedException {
		WebElement selectedElement = MTDriver.findElement(By
				.xpath(".//*[@id='contest']"));
		Select select = new Select(selectedElement);
		List<WebElement> allOptions = select.getOptions();

		for (WebElement option : allOptions) {
			Log.info("contest key " + option.getText() + "exist at joomla ");

			String str = option.getText();
			String[] temp;

			String delimiter = " —";
			/* given string will be split by the argument delimiter provided. */
			temp = str.split(delimiter);
			
			
			Log.info("contest key after delimit "+temp[0]);
			
			if( GPS.contains(temp[0])) {
				Log.info(temp[0] );
				Assert.assertTrue(true);
			}

		}
	}
	
	public String GetGPS_PageSource(String AppCode, String URL )
	 {


			String GPSbaseUrl=URL+"/"+AppCode+"/"+Generate_Futuredate(0)+"/"+Generate_Futuredate(30);
			Log.info("GPS base url is :" +GPSbaseUrl);
			MTDriver.navigate().to(GPSbaseUrl);
			String GPS=MTDriver.getPageSource();
			return GPS;
				
	
		}
	
	public String GetWinner_PageSource(String URL )
	 {			
		  String GetWinnerData=MTDriver.getPageSource();
			return GetWinnerData;
				
		}
	
	public void clickSubmenuCategory(){
		waitForPageToLoad(MTDriver);
		link(MTDriver, By.xpath("html/body/div[3]/div[2]/div/ul/li[2]/a"),"click");
	}
	
	
	//This is a duplicate method: SPAL 
	public void articleStatus(String value){
		waitForPageToLoad(MTDriver);
		WebElement element = MTDriver.findElement(By.xpath(".//*[@id='adminForm']/table/tbody/tr/td[3]/a/span"));
		if(element.getAttribute("class").equalsIgnoreCase("state publish") && value.equalsIgnoreCase("publish")){
			
		}else if(element.getAttribute("class").equalsIgnoreCase("state unpublish") && value.equalsIgnoreCase("publish")){
			element.click();
		}else if(element.getAttribute("class").equalsIgnoreCase("state unpublish") && value.equalsIgnoreCase("unpublish")){
			
		}else if(element.getAttribute("class").equalsIgnoreCase("state publish") && value.equalsIgnoreCase("unpublish")){
			element.click();
		}
		waitForPageToLoad(MTDriver);
	}
	
	public void appConfig_LevelAPISecretKey(String value){
		textbox(MTDriver, By.id("level_api_secret_key"), "enter", value);
	}



	public String  getValue_IWEWinnerDataUrl(int userOrder )
		{
	
		String value=MTDriver.findElement((By.xpath("/winners/winner["+userOrder+"]/prize/prizeValue"))).getText(); 
		return value;
		}

// This is a duplicate method: SPAL 
	/*
	 * Change Lotto Game engine Id
	 */
	public void selectLottoEngineGame(Integer id){
		selectByValue(MTDriver, By.xpath(".//*[@id='lotto_engine_game_id']"), "select", id.toString());
	}
	/*
	 * Standalone Sweepstake
	 * return the TC and V value
	 */
	public ArrayList<String> BMH_Configuration(int Index, String action) {
		Log.info("BMH configuration :-"+action);
		ArrayList<String> arr = new ArrayList<String>();
		List<WebElement> element = MTDriver.findElements(By.xpath("//div[@id='cck1_sortable_bmh_configuration']/child::div"));

		if(action.equalsIgnoreCase("get value")){
			Log.info("TV Value :- "+getAttribute(MTDriver, By.id("bmh_configuration_"+Index+"_bmh_tc"),"value"));
			arr.add(getAttribute(MTDriver, By.id("bmh_configuration_"+Index+"_bmh_tc"),"value"));
			
			Log.info("V Value :-"+getAttribute(MTDriver, By.id("bmh_configuration_"+Index+"_bmh_v"),"value"));
			arr.add(getAttribute(MTDriver, By.id("bmh_configuration_"+Index+"_bmh_v"),"value"));
		}else if(action.equalsIgnoreCase("remove")){
			for (int i = 0; i < element.size(); i++) {
				link(MTDriver, By.xpath("//aside[@id='cck1r_button_bmh_configuration_0']/div[@class='cck_button cck_button_del_bmh_configuration cck_button_del cck_button_first']"), "click");
			}
		}
		else if(action.equalsIgnoreCase("add")){
			link(MTDriver, By.xpath("//aside[@id='cck1r_button_bmh_configuration_0']/div[@class='cck_button cck_button_add_bmh_configuration cck_button_add']"), "click");
		}
		return arr;
}
	
	public void ConfigureTCvalues(String Action, String TC, String V) {
		if(Action.equalsIgnoreCase("Remove")) {
			Log.info("Removing TC Value:-");
			cleartext(By.xpath("//input[@id='bmh_configuration_0_bmh_tc']"));
			Log.info("Removing V Value:-");
			cleartext(By.xpath("//input[@id='bmh_configuration_0_bmh_v']"));
		} else if(Action.equalsIgnoreCase("add")) {
			Log.info("Add TC Value:-");
			textbox(MTDriver, By.xpath("//input[@id='bmh_configuration_0_bmh_tc']"), "enter", TC);
			Log.info("Add V Value:-");
			textbox(MTDriver, By.xpath("//input[@id='bmh_configuration_0_bmh_v']"), "enter", V);
		}
	}

	public void ConfigureTCvalues() throws IOException, Exception{
		BMH_Configuration(0, "remove");
		BMH_Configuration(0, "add");
		BMH_Configuration(0, "add");
		textbox(MTDriver, By.xpath("//input[@id='bmh_configuration_0_bmh_tc']"), "enter", returnPropertyValue("TC_0"));
		textbox(MTDriver, By.xpath("//input[@id='bmh_configuration_0_bmh_v']"), "enter", returnPropertyValue("V_0"));
		textbox(MTDriver, By.xpath("//input[@id='bmh_configuration_1_bmh_tc']"), "enter", returnPropertyValue("TC_1"));
		textbox(MTDriver, By.xpath("//input[@id='bmh_configuration_1_bmh_v']"), "enter", returnPropertyValue("V_1"));
		textbox(MTDriver, By.xpath("//input[@id='bmh_configuration_2_bmh_tc']"), "enter", returnPropertyValue("TC_2"));
		textbox(MTDriver, By.xpath("//input[@id='bmh_configuration_2_bmh_v']"), "enter", returnPropertyValue("V_2"));

	}


	//AppConfig Leaderboard enable/disable maximum limit 
	public void appConfig_EnableDailyLimitCheck(String value) {
		Log.info("Enable Daily Limit Check -> " + value );
		if (value.equalsIgnoreCase("yes")) {
			link(MTDriver, By.id("leaderboard_daily_token_limit0"), "click");			
		}else {
			link(MTDriver, By.id("leaderboard_daily_token_limit1"), "click");
		}
	}
	
	//AppConfig Leaderboard set maximum limit
	public void appConfig_DailyTokenLimit(Integer... value){
		if(value.length==0){
			Log.info("Clearing Daily Token Limit textbox");
			textbox(MTDriver, By.id("maximum_daily_token_payout"), "enter", "");
		}else {
			Log.info("Setting Daily Token Limit -> "+value[0].toString());
			textbox(MTDriver, By.id("maximum_daily_token_payout"), "enter", value[0].toString());	
		}
		
	}
	
	public void UpdateSweepStakeArticle(String topColor,String bottomColor,String buttonColor,String buttonTextColor) throws InterruptedException
	{
	textbox(MTDriver, By.id("top_color"), "enter", topColor);
	textbox(MTDriver, By.id("bottom_color"),"enter", bottomColor);
	textbox(MTDriver, By.id("button_color"), "enter", buttonColor);
	textbox(MTDriver, By.id("button_text"), "enter", buttonTextColor);
	saveArticle();
	Thread.sleep(5222);
	
}

public void Update_100KCashtoTheRescue(String credentials) throws InterruptedException
{
	textbox(MTDriver, By.id("credentials"), "enter", credentials);
	saveArticle();
	Thread.sleep(5222);
}
	
	// BMH Submission Method : Async or Sync from AppConfig Article
	public void appConfig_BMHSubmissionMethod(String type){
		Log.info("Changing BMH Submission Method  "+type);
		if (type.equalsIgnoreCase("async")) {
			selectoption(By.id("bmh_submission_method"), "Async");
		}else if (type.equalsIgnoreCase("sync")) {
			selectoption(By.id("bmh_submission_method"), "Sync");
		}
	}
	
	public void taxiSubmissionMethod(String status){
		Log.info("Changing Welcome Submission Method :- "+status);
		if (status.equalsIgnoreCase("async")) {
			link(MTDriver, By.id("welcome_submission_method1"), "click");
		}else if (status.equalsIgnoreCase("sync")) {
			link(MTDriver, By.id("welcome_submission_method0"), "click");
		}else if (status.equalsIgnoreCase("Disabled")) {
			link(MTDriver, By.id("welcome_submission_method2"), "click");
		}
	}
	
	
	public void taxiAPIWelcome(String value, String operationType){
		if(operationType.equals("clear")){
			textbox(MTDriver, By.id("taxi_api_welcome"), "enter", "");
		}else if(operationType.equals("set")){
			textbox(MTDriver, By.id("taxi_api_welcome"), "enter", value);
		}
	}
	
	public void setTAXIConfig(String status, String operationType) throws IOException, Exception{
		taxiSubmissionMethod(status);
		taxiAPIWelcome(returnPropertyValue("Taxi_API_Welcome"),operationType);
	}
	public void setPCHGameClassName(String value)
	{
		textbox(MTDriver, By.id("pch_game_classname"), "enter", value);
	
	}
	
	public void selectActionArticleDropdown(String value, Integer pos) {
		selectByValue(MTDriver, By.id("action_articles__"+pos), "select", value); 
	}
	
}