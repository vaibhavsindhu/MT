package com.pch.iwe.page;

import static com.pch.utilities.Log.Log;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.pch.utilities.Action_Wrapper;

public class PrizesPage extends Action_Wrapper {

	private By prizeValue = By.name("prizeValue");
	private By totalNumberOfPrize = By.name("totalNumberOfPrize");
	private By imageUrl = By.name("imageUrl");
	private By changeComment = By.xpath("//label[text()='Change comment:']/parent::*/parent::*/td[2]/input");
	private By Save = By.xpath("//*[contains(text(), 'Save')]");
	private By Cancel = By.xpath("//span[text()='Cancel']");
	private By giveAwayButton = By.xpath("//label[text()='Giveaway:']/parent::*/parent::*/td/descendant::div[2]");
	private By giveAwayTextBox = By.xpath("//label[text()='Giveaway:']/parent::*/parent::*/td/descendant::input");
	private By giveAwayDropDown = By.xpath("//a[@data-qtip='Last Page']/parent::div/parent::div/parent::div/parent::div/div//li");
	private By prizeTypeButton = By.xpath("//label[text()='Prize Type:']/parent::*/parent::*/td/descendant::div[2]");
	private By prizeTypeDropDown = By.xpath("//ul[@class='x-list-plain']/li[text()='Token']/parent::*/li");
	private By description = By.xpath("//textarea[contains(@id='textarea-')]");
	
	public void navigateToGiveawayGroup() throws Exception {
		waitForPageToLoad(MTDriver);
		MTDriver.navigate().to("http://iwe.qa.pch.com/iwe/#prize/edit/4257");
	}

	public void prizeType(String value) {
		button(MTDriver, prizeTypeButton, "click");
		List<WebElement> dropdown = MTDriver.findElements(prizeTypeDropDown);
		for(WebElement sel : dropdown){
			System.out.println(sel.getText());
			if(sel.getText().equalsIgnoreCase(value)){
				sel.click();
			}
		}
	}
	
	public void prizeValue(Integer value) {
		Log.info("Prize Value : " + value);
		textbox(MTDriver, prizeValue, "enter", value.toString());
	}

	public void totalNumberOfPrize(Integer value) {
		Log.info("total Number Of Prize " + value);
		textbox(MTDriver, totalNumberOfPrize, "enter", value.toString());
	}

	public void imageUrl(String value) {
		Log.info("Adding Image URL" + value);
		textbox(MTDriver, imageUrl, "enter", value);
	}

	public void clickSave() {
		Log.info("Clicking Save Button");
		button(MTDriver, Save, "click");
	}

	public void clickCancel() {
		Log.info("Clicking Cancel Button");
		button(MTDriver, Cancel, "click");
	}

	public void setChangeComment(String value) {
		Log.info("Adding Comment : " + value);
		textbox(MTDriver, changeComment, "enter", value);
	}
	
	public void setDescription(String value) {
		Log.info("Adding Comment : " + value);
		textbox(MTDriver, description, "enter", value);
	}
	
	public void clearGiveAwayTextBox(){
		Log.info("clear GiveAway TextBox");
		textbox(MTDriver, giveAwayTextBox, "enter", "");
	}
	
	public void clickGiveAwayButton(){
		Log.info("click GiveAway Button");
		button(MTDriver, giveAwayButton, "click");
	}
	
	@SuppressWarnings("unused")
	public void setGiveAway(){
		List<WebElement> dropdown = MTDriver.findElements(giveAwayDropDown);
		for(WebElement sel : dropdown){
			
		}
	}
	
}
