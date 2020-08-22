package com.pch.joomla.configuration;

import static com.pch.utilities.Log.Log;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.pch.utilities.Action_Wrapper;

public class Menu_Links extends Action_Wrapper {

	private By jsonData = By.id("json_data");
	private By groupButtonCount = By.xpath("//div[@class='collection-group-button']");
	@SuppressWarnings("unused")
	private By actionArticleDropDown = By.xpath("//div[@class='collection-group-form']");
	private By unlockValue = By.id("unlock_value");
	private By unlockUnits = By.id("unlock_units");
	private By exposeContentPath = By.id("expose_lotto_contet_path");
	@SuppressWarnings("unused")
	private By cashPrizeValueAddButton =By.xpath(".//*[@class='button-add-prize_values']");
	private By cashPrizeValueDeleteButtonCount = By.xpath(".//*[@id='sortable_prize_values']/div");
	private By tokenPrizeValueDeleteButtonCount = By.xpath(".//*[@id='sortable_token_values']/div");

	List<WebElement> element;
	
	public void removeActionArticle(){
		element = getWebelementList(MTDriver, groupButtonCount, 1);
		for (int i = 0; i < element.size()-1; i++) {
			link(MTDriver, By.id("button_del__action_articles__"+i), "click");	
		}
		waitForPageToLoad(MTDriver);
		link(MTDriver, By.xpath("//*[@id='toolbar-o_apply']/a"), "click");
		waitForPageToLoad(MTDriver);
	}
	
	public void addActionArticleCCk(Integer... pos){
		element = getWebelementList(MTDriver, groupButtonCount, 1);
		
		if(pos.length==1) {
			link(MTDriver, By.id("button_add__action_articles__"+pos[0]), "click");
		}			
		link(MTDriver, By.id("button_add__action_articles__"+(element.size()-1)), "click");	
	}
	
	public void selectActionArticleDropdown(String value, Integer pos) {
		SelectValueFromDropDown(By.id("action_articles__"+pos), value);

	}
	
	
	public void addJsonData(String value){
		textbox(MTDriver, jsonData, "enter", value);
	}
	
	public void unlockValue(String value){
		textbox(MTDriver, unlockValue, "enter", value);
	}
	
	public void unlockUnits(String value){
		selectByValue(MTDriver, unlockUnits, "select", value);
	}
	
	public void exposeLottoContentPath(String value){
		textbox(MTDriver, exposeContentPath, "enter", value);
	}
				
	
	
	
	/*public void addCashPrize_prizeArticle(int pos ,String value) {
		element = getWebelementList(MTDriver, cashPrizeValueAddButton, 1);

		if (pos == 1) {
			By xpath=By.id("button_add__prize_values__" + 0);
			Log.info("value fo spath "+xpath);
			link(MTDriver, By.id("button_add__prize_values__0"),"click");
								
		}
		else {
		link(MTDriver, By.id("button_add__prize_values__" + element.size()),"click");
		}
		
	}*/
	
	
public void AddCashPrize_prizeArticle(int totalPrize) {
	waitForPageToLoad(MTDriver);
		
		for (int i = 0; i < totalPrize; i++) 
		{
			link(MTDriver, By.id("button_add__prize_values__"+i), "click");	
		
		}
	}
	
	public void SetCashPrize_prizeArticle(int pos,String value){
		waitForPageToLoad(MTDriver);
			Log.info("Providing Value at text box");
			textbox(MTDriver, By.id("prize_values__"+pos), "enter", value);
		}
	
	
public void AddTokenPrize_prizeArticle(int totalPrize) {
		
		for (int i = 0; i < totalPrize; i++) 
		{
			link(MTDriver, By.id("button_add__token_values__"+i), "click");	
		
		}
	}
	
	public void SetTokenPrize_prizeArticle(int pos,String value){
			Log.info("providing  value at text box ");
			textbox(MTDriver, By.id("token_values__"+pos), "enter", value);
		}
	
	/**
	 * this method is to remove cash prize value from prize article at joomla
	 */
	public void removeCashPrizeValueArticle(){
		element = getWebelementList(MTDriver, cashPrizeValueDeleteButtonCount, 1);
		for (int i = 0; i < element.size()-1; i++) {
			waitForPageToLoad(MTDriver);
			link(MTDriver, By.id("button_del__prize_values__"+i), "click");	
		}
		
	}
	
	
	/**
	 * this method is to remove token prize values from prize article at joomla
	 */
	public void removeTokenPrizeValueArticle(){
		element = getWebelementList(MTDriver, tokenPrizeValueDeleteButtonCount, 1);
		for (int i = 0; i < element.size()-1; i++) {
			waitForPageToLoad(MTDriver);
			link(MTDriver, By.id("button_del__token_values__"+i), "click");	
		}
		
	}
	
	
	
	
}
	
