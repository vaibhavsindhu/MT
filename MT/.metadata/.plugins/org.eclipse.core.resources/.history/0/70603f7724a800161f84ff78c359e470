package com.pch.iwe.page;

import org.openqa.selenium.By;

import com.pch.utilities.Action_Wrapper;

public class BusinessUnit extends Action_Wrapper  {
	
	public enum BUType {
		PCHApp(53), PlayAndApp(31);
		private Integer value;

		private BUType(Integer value) {
			this.value = value;
		}
	}
	
	private By LeaderBoardEnable = By.xpath("//input[contains(@id,'checkbox')]");
	private By ChangeComment = By.xpath("//label[text()='Change comment:']/parent::*/parent::*/td[2]/input");
	private By Save  = By.xpath("//*[contains(text(), 'Save')]");
	private By Cancel = By.xpath("//span[text()='Cancel']");
	
	public void navigateToBU(BUType type){
		MTDriver.navigate().to("https://iwe.qa.pch.com/iwe/#businessunit/edit/"+type.value);
	}
	
	
	public void checkLeardBoardStatus(String status) {
		if (status.equalsIgnoreCase("true")) {
			if(getAttribute(MTDriver, LeaderBoardEnable, "aria-checked").equalsIgnoreCase("false")){
				link(MTDriver, LeaderBoardEnable, "click");
			}
		}else if (status.equalsIgnoreCase("false")) {
			if(getAttribute(MTDriver, LeaderBoardEnable, "aria-checked").equalsIgnoreCase("true")){
				link(MTDriver, LeaderBoardEnable, "click");
			}
		} 
	}
	
	public void clickSave(){
		button(MTDriver, Save, "click");
	}
	
	public void clickCancel(){
		button(MTDriver, Cancel, "click");
	}
	
	public void setChangeComment(String value){
		textbox(MTDriver, ChangeComment, "enter", value);
	}
	
	public void changeLeaderBoardStatus(BUType type, String status){
		navigateToBU(type);	
		waitForPageToLoad(MTDriver);
		checkLeardBoardStatus(status);
		clickSave();
		
	}

}
