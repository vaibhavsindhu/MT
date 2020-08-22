package com.pch.joomla.configuration;

import static com.pch.utilities.Log.Log;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.pch.utilities.Action_Wrapper;

public class PchApp extends Action_Wrapper {
	int index = 0;
	private By selectcategory = By.xpath("//select[@name='filter_category_id']");
	private By iwPrizeArticle = By.id("iw_prize_article");
	private By lottoEngineID = By.id("lotto_engine_game_id");
	private By articleState = By.id("art_state");
	private By filterBox = By.id("filter_search");
	private By actionArticle = By.id("action_articles__0");
	private By device = By.id("iw_device_id");
	private By access = By.id("iw_access_key");

	@SuppressWarnings("unused")
	private By ss = By.xpath("//aside[@id='cck1r_button_pch_app_tile_"+index+"']/div[@class='cck_button cck_button_add_pch_app_tile cck_button_add']");
	private By menuLinksArticle = By.id("menu_links_article");
	private By featureFieldCount = By.xpath("//div[@id='cck1_sortable_pch_app_feature']/child::div");
	private By featureFieldDelete = By.xpath("//aside[@id='cck1r_button_pch_app_feature_0']/div[@class='cck_button cck_button_del_pch_app_feature cck_button_del cck_button_first']");

	private By tileFieldCount = By.xpath("//div[@id='cck1_sortable_pch_app_tile']/div");
	private By tileFieldDelete = By.xpath("//aside[@id='cck1r_button_pch_app_tile_0']/div[@class='cck_button cck_button_del_pch_app_tile cck_button_del cck_button_first']");

	private By bonusActionArticlesCount = By.xpath("//div[@id='cck1_sortable_bonus_action_articles']/div");
	private By bonusActionArticlesDelete = By.xpath("//aside[@id='cck1r_button_bonus_action_articles_0']/div[@class='cck_button cck_button_del_bonus_action_articles cck_button_del cck_button_first']");

	private By enableAppIdChkButton=By.id("app_id_check0");
	private By disableAppIdChkButton=By.id("app_id_check1");
	List<WebElement> element;

	public void selectMenuLinkArticle(String value) {
		selectoption(menuLinksArticle, value);
	}

	private WebElement MARK_COMPLETE(){
		return MTDriver.findElement(By.id("mark_complete"));
	}
	/*
	 * Feature Section
	 */
	public void FeatureFieldremove() {
		element = getWebelementList(MTDriver, featureFieldCount, 1);
		for (int i = 0; i < element.size()-1; i++) {
			link(MTDriver, featureFieldDelete, "click");	
		}
	}

	public void featureFieldAdd(Integer index) {		
		link(MTDriver, By.xpath("//aside[@id='cck1r_button_pch_app_feature_"+index+"']/div[@class='cck_button cck_button_add_pch_app_feature cck_button_add']"), "click");
	}

	/**
	 * This function is adding new content path in app article
	 * if it is not present already
	 * @author vsingh
	 * */
	public void featureFieldSelectArticle(String value){

		List<String> selectedFeaturesValues = new ArrayList<String>();
		List<WebElement> selectedFeatures=MTDriver.findElements(By.xpath("//div[@class='cck-line-bottom']//select/option[@selected='selected']"));
		for(WebElement selectedFeature:selectedFeatures){	
			selectedFeaturesValues.add(selectedFeature.getText());			
		}
		if(!selectedFeaturesValues.contains(value)){
			Log.info(value+" Feature not found...going to add this feature");
			index=MTDriver.findElements(By.xpath("//div[@id='cck1_sortable_pch_app_feature']//div[contains(@class,'cck_button cck_button_add_pch_app_feature cck_button_add')]")).size();
			link(MTDriver, By.xpath("//aside[@id='cck1r_button_pch_app_feature_"+(index-1)+"']/div[@class='cck_button cck_button_add_pch_app_feature cck_button_add']"), "click");
			selectoption(By.xpath("//select[@id='pch_app_feature_"+index+"_feature']"), value);
			Log.info(value+" Feature added successfully");
		}else{
			Log.info(value+" Feature already present");
		}
	}
	
	
	/*
	 * Tiles Section
	 */
	public void TileFieldremove(){
		element = getWebelementList(MTDriver, tileFieldCount, 1);
		for (int i = 0; i < element.size()-1; i++) {
			link(MTDriver, tileFieldDelete, "click");	
		}
	}

	public void tileFieldAdd(Integer index){
		link(MTDriver, By.xpath("//aside[@id='cck1r_button_pch_app_tile_"+index+"']/div[@class='cck_button cck_button_add_pch_app_tile cck_button_add']"), "click");
	}

	public void tileFieldSelectArticle(String value, Integer index){
		selectoption(By.xpath("//select[@id='pch_app_tile_"+index+"_tile']"), value);
	}

	/*
	 * Bonus Action Articles
	 */
	public void bonusActionArticlesRemove(){
		element = getWebelementList(MTDriver, bonusActionArticlesCount, 1);
		for (int i = 0; i < element.size()-1; i++) {
			link(MTDriver, bonusActionArticlesDelete, "click");	
		}
	}

	public void bonusActionArticlesAdd(Integer index){
		link(MTDriver, By.xpath("//aside[@id='cck1r_button_bonus_action_articles_"+index+"']/div[@class='cck_button cck_button_add_bonus_action_articles cck_button_add']"), "click");
	}

	/**
	 * This funtion will add new bonus action article  if it is not there already
	 * after adding bonus article it will add given trigger,event type if given
	 * @author vsingh
	 * */
	public void bonusActionArticle(String bonusArticle,String trigger) {	
		int index;
		//get all selected article/triggers
		List<String> selectedActionArticleValues = new ArrayList<String>();
		List<WebElement> SelectedActionArticles=MTDriver.findElements(By.xpath("//div[@id='cck1r_right-a']//select/option[@selected='selected']"));
		for(WebElement selectedArticle:SelectedActionArticles){	
			selectedActionArticleValues.add(selectedArticle.getText());			
		}
		//check given bonus article present or not if not add it
		if(!selectedActionArticleValues.contains(bonusArticle)){
			Log.info(bonusArticle+"Bonus action article not found...going to add action article: "+bonusArticle);
			index=MTDriver.findElements(By.xpath("//div[@id='cck1r_right-a']//div[contains(@class,'button_add')]")).size();
			link(MTDriver, By.xpath("//div[@id='cck1r_forms_bonus_action_articles_"+(index-1)+"']//div[contains(@class,'button_add')]"), "click");			
			SelectValueFromDropDown(By.xpath("//select[@id='bonus_action_articles_"+index+"_bonus_action_article']"), bonusArticle);

			Log.info(bonusArticle+" action article added successfully");
			//add trigger
			Log.info("Adding Trigger");		
			selectoption(By.xpath("//select[@id='bonus_action_articles_"+(index)+"_trigger_article']"), trigger);

		}else{
			//bonus article already 
			Log.info(bonusArticle+" action article already present");	
			String	triggerLocator=String.format("//div[@id='cck1r_right-a']//select/option[@selected='selected' and contains(text(),'%s')]/../../../following-sibling::div[contains(@id,'trigger_article')]//select",bonusArticle);
			Log.info("Going to add Trigger");			
			selectoption(By.xpath(triggerLocator), trigger);		

		}
	}



	public void bonusActionArticlesTrigger(String value, Integer index) {		
		selectoption(By.xpath("//select[@id='bonus_action_articles_"+(index-1)+"_trigger_article']"), value);
	}

	public void bonusActionArticlesType(String bonusArticle,String value) {
		String element=String.format("//select//option[@selected='selected' and text()='%s']/../../../following-sibling::div[contains(@id,'trigger_event_type')]//select", bonusArticle);
		selectByValue(MTDriver, By.xpath(element),	"select", value);
	}

	public void bonusActionArticlesTarget(String bonusArticle,String value) {
		String element=String.format("//select//option[@selected='selected' and text()='%s']/../../../following-sibling::div[contains(@id,'trigger_event_target')]//select", bonusArticle);
		selectByValue(MTDriver, By.xpath(element),	"select", value);
	}

	public void bonusActionArticlesVar(String bonusArticle,String value) {
		String element=String.format("//select//option[@selected='selected' and text()='%s']/../../../following-sibling::div[contains(@id,'trigger_event_var')]//input", bonusArticle);
		textbox(MTDriver, By.xpath(element), "enter", value);
	}

	public void saveArticle(){
		link(MTDriver, By.xpath("//*[@id='toolbar-o_apply']/a"), "click");
		waitForPageToLoad(MTDriver);
	}
	//====================================================================================================================	
	// New Methods for PCHAPP IW/Lotto/Sweeps articles

	public void selectCategory(String Catvalue) {

		//		if(AppName.equalsIgnoreCase("PCHAPP")) {
		selectByValue(MTDriver, selectcategory,	"select", Catvalue);
		//		}
	}

	// Edit Article. and change the Article state
	public void editArticle(String articleName, String articleState) {
		waitForPageToLoad(MTDriver);
		if(articleName.equalsIgnoreCase(getArticleName(articleName))){
			link(MTDriver, By.linkText(articleName) , "click");
		}
		changeArticleState(articleState);		
	}

	public String getArticleName(String articleName){
		return getText(MTDriver,By.xpath(String.format("//a[contains(text(),'%s')]",articleName)));
	}

	// configure IWEDevice option Friends_Invite
	public void configureIWEinJoomla(String deviceID, String accesskey) {
		textbox(MTDriver, device , "enter", deviceID);
		textbox(MTDriver, access, "enter", accesskey);
	}

	// configure Scratch Prize Article
	public void configureScratchPrizeArticle(String prizeArticle) {

//		if(prizeArticle.equalsIgnoreCase("Scratch Prize Group")) {
			selectByValue(MTDriver, By.id("prize_article"),	"select", prizeArticle);
//		} else if(prizeArticle.equalsIgnoreCase("Scratch Prizegroup3")) {
//			selectByValue(MTDriver, By.id("prize_article"),	"select", "117");
//		}

	}

	// Publish or Un-publish Articles
	public void changeArticleState(String State) {

		if(State.equalsIgnoreCase("publish")) {
			selectByValue(MTDriver, articleState,	"select", "1");
		} else if(State.equalsIgnoreCase("unpublish")) {
			selectByValue(MTDriver, articleState,	"select", "0");
		}
	}

	// Select Prizes/Contest Keys from Drop Down in Action Articles 
	public void selectPrizeConfigurations(String prizeType, String prizeValue) {
		if(prizeType.equalsIgnoreCase("IW")) {
			selectByValue(MTDriver, iwPrizeArticle,	"select", prizeValue);
		} else if(prizeType.equalsIgnoreCase("Lotto")) {
			selectByValue(MTDriver, lottoEngineID,	"select", prizeValue);
		}
	}

	/**
	 * This funtion will add new action article in content path if it is not there already
	 * @author vsingh
	 * */
	public void selectActionArticles(String selectValueofArticle) {

		List<String> selectedActionArticleValues = new ArrayList<String>();
		List<WebElement> SelectedActionArticles=MTDriver.findElements(By.xpath("//div[@id='cck1r_right-a']//select/option[@selected='selected']"));
		for(WebElement selectedArticle:SelectedActionArticles){	
			selectedActionArticleValues.add(selectedArticle.getAttribute("value"));			
		}
		if(!selectedActionArticleValues.contains(selectValueofArticle)) {
			Log.info(selectValueofArticle+" Action Article not found...going to add this feature");
			int index=MTDriver.findElements(By.xpath("//div[@id='cck1r_form_action_articles']//div[@class='button-add']")).size();
			link(MTDriver, By.xpath("//img[@id='button_add__action_articles__"+(index-1)+"']"), "click");			
			selectByValue(MTDriver, By.xpath("//select[@id='action_articles__"+index+"']"),	"select", selectValueofArticle);
			Log.info(selectValueofArticle+" Action Article added successfully");
		} else {
			Log.info(selectValueofArticle+" Action Article already present");
		}
	}


	public void addActionArticlesByIndex(int index,String selectValueofArticle) {	

		selectByValue(MTDriver, actionArticle,	"select", selectValueofArticle);
	}


	// Filter Articles By Name
	public void filterArticleByNames(String CatValue, String ArticleName) {
		waitForPageToLoad(MTDriver);
		selectCategory(CatValue);
		textbox(MTDriver, filterBox, "enter", ArticleName);
		clickFilterSearch();
	}

	// Click on Filter Search Button
	public void clickFilterSearch() {
		link(MTDriver, By.xpath("//button[contains(.,'Search')]") , "click");
	}

	public String selectMarkComplete(String markComplete_newValue){
		Select markComplete=new Select(MARK_COMPLETE());
		String markComplete_oldValue=markComplete.getFirstSelectedOption().getText();
		markComplete.selectByValue(markComplete_newValue);
		return markComplete_oldValue;
	}

	/**
	 * This function is to Enable/Disable AppId Check for game calls
	 * @author vsingh
	 * @param: status (enable/disable as per the requirement)
	 * */
	public void updateAppIdCheckForGameCalls(String status){

		try{			
			if(status.equalsIgnoreCase("enable")){
				waitForElementPresent(MTDriver,enableAppIdChkButton);
				MTDriver.findElement(By.id("app_id_check0")).click();
			}else{
				waitForElementPresent(MTDriver,disableAppIdChkButton);
				MTDriver.findElement(By.id("app_id_check1")).click();
			}
		}catch(Exception e){
			Log.info(e.getMessage());
		}
	}
}
