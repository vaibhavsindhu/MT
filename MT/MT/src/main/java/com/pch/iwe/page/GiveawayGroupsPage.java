package com.pch.iwe.page;


import static com.pch.utilities.Log.Log;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.pch.utilities.Action_Wrapper;

public class GiveawayGroupsPage extends Action_Wrapper {

	private By changeComment = By.xpath("//label[text()='Change comment:']/parent::*/parent::*/td[2]/input");
	private By giveawaySelectionButton = By.xpath("//table[@id='giveawayIds-triggerWrap']/descendant::td/div[@role='button']");
	private By removeSelectedGiveaway = By.xpath(".//img[@data-qtip='Remove this Giveaway']");
	private By Save  = By.xpath("//*[contains(text(), 'Save')]");
	private By Cancel = By.xpath("//span[text()='Cancel']");
	private By giveawayDropDown = By.xpath("//ul[@class='x-list-plain']/li");
	
	public void navigateToGiveawayGroup() throws Exception {
		//holdon(3000);
		MTDriver.navigate().to("http://iwe.qa.pch.com/iwe/#giveawaygroup/edit/1128");
	}

	
	public void addGiveawaySelection(){
		try {
			//holdon(3000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		button(MTDriver, giveawaySelectionButton, "click");
		
		List<WebElement> dropdown = MTDriver.findElements(giveawayDropDown);
		for(WebElement drop : dropdown){
			drop.click();
			break;
		}
		
	}
	public void clickSave(){
		Log.info("Clicking Save Button");
		button(MTDriver, Save, "click");
	}
	
	public void clickCancel(){
		Log.info("Clicking Cancel Button");
		button(MTDriver, Cancel, "click");
	}
	
	public void setChangeComment(String value){
		Log.info("Adding Comment : "+value);
		textbox(MTDriver, changeComment, "enter", value);
	}
	
	public void removeSelectedGiveawayAll(){
		Log.info("Remove All Selected Giveaway");
		List<WebElement> give = MTDriver.findElements(removeSelectedGiveaway);
		for(WebElement rem :give)
		{
			rem.click();
		}
	}

}
