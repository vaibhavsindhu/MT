package com.pch.joomla.configuration;

import static com.pch.utilities.Log.Log;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.Assert;

import com.pch.utilities.Action_Wrapper;

public class ManageOauthClient extends Action_Wrapper {
	
	public void navigate(){
		Log.info("Navigating to Manage Oauth Client page");
		try {
			MTDriver.get(returnPropertyValue("Joomla_URL")+returnPropertyValue("Joomla_ManageOauthClient"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void clickToolbarMenu(String value) {
		Log.info("Clicking toolbox menu : " + value);
		if (value.equalsIgnoreCase("options"))
			value = "popup-options";
		if (value.equalsIgnoreCase("Save & Close"))
			value = "save";
		link(MTDriver, By.id("toolbar-" + value), "click");
		waitForPageToLoad(MTDriver);
	}

	public void addClient_ClientName(String value) {
		Log.info("Adding client name : "+value);
		textbox(MTDriver, By.id("jform_name"), "enter", value);
	}
	
	public void addClient_ClientId(String value) {
		Log.info("Adding client id : "+value);
		textbox(MTDriver, By.id("jform_id"), "enter", value);
	}
	
	public void addClient_GenerateKey() {
		Log.info("Generating Secret Key");
		link(MTDriver, By.id("generateKey"), "click");
		waitForPageToLoad(MTDriver);
	}
	
/*	public void manageOuthClientTable(){
		webTable(By.xpath(".//*[@id='element-box']/descendant::table/child::tbody"), "value","",3);
	}
*/	
	public void changeDisplayTableDataCount(){
		Log.info("Changing Display option of manage auth client table to ALL");
		SelectValueFromDropDown(By.id("limit"), "All");
//		selectoption(By.id("limit"), "All");
	}
	
	public void editClient(String value) {
		Log.info("Opening "+value +" in edit mode");
		MTDriver.findElement(By.xpath("//td/a[contains(text(),'"+value+"')]/parent::td/parent::tr/td[1]/input")).click();
		clickToolbarMenu("edit");
	}
	
	public void deleteClient(String value) {
		Log.info("Deleting Client with name "+value);
		MTDriver.findElement(By.xpath("//td/a[contains(text(),'"+value+"')]/parent::td/parent::tr/td[1]/input")).click();
		clickToolbarMenu("delete");
		try {
			waitForPageToLoad(MTDriver);
			Thread.sleep(2222);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Alert_accept();
		waitForPageToLoad(MTDriver);
	}
	
	public void pageUI() {
		Log.info("Validating Manage Outh Client page UI");
		Assert.assertTrue(validateElementPresent(MTDriver,  By.xpath(".//*[@id='element-box']/div")));
		Assert.assertTrue(validateElementPresent(MTDriver,  By.id("toolbar-delete")));
		Assert.assertTrue(validateElementPresent(MTDriver,  By.id("toolbar-edit")));
		Assert.assertTrue(validateElementPresent(MTDriver,  By.id("toolbar-new")));
		Assert.assertTrue(validateElementPresent(MTDriver,  By.id("toolbar-popup-options")));
		
	}
	
	public void addNewClientPageUI(){
		Log.info("Validating Add New Client page UI");
		Assert.assertTrue(validateElementPresent(MTDriver,  By.id("jform_id")));
		Assert.assertTrue(validateElementPresent(MTDriver,  By.id("jform_name")));
		Assert.assertTrue(validateElementPresent(MTDriver,  By.id("generateKey")));
		Assert.assertTrue(elementIsClickable(MTDriver, By.id("generateKey")));

		
		Assert.assertTrue(validateElementPresent(MTDriver,  By.id("toolbar-save")));
		Assert.assertTrue(validateElementPresent(MTDriver,  By.id("toolbar-cancel")));

	}
	
	public void validateAddClientErrorMessage(String... data){
		Log.info("Validating Error Message At Add Client Page");
		waitForPageToLoad(MTDriver);
		if(data.length==0){
			Assert.assertTrue(pageSource().contains("Field required: Client Name"));
			Assert.assertTrue(pageSource().contains("Field required: Client Id"));
			Assert.assertTrue(pageSource().contains("Field required: Secret Key"));
		}
		else if (data[0].equalsIgnoreCase("Client Name")) {
			Assert.assertTrue(pageSource().contains("Field required: Client Name"));
		}else if (data[0].equalsIgnoreCase("Client Id")) {
			Assert.assertTrue(pageSource().contains("Field required: Client Id"));
		}else if (data[0].equalsIgnoreCase("Secret Key")) {
			Assert.assertTrue(pageSource().contains("Field required: Secret Key"));
		}
	}
	
	public void setExpireTime(String value) throws IOException, Exception{
		MTDriver.navigate().to(returnPropertyValue("Joomla_URL")+returnPropertyValue("Joomla_Oath_Configuration"));
		waitForPageToLoad(MTDriver);
		Log.info("Setting Life Time Set Expire time : "+value);
//		Thread.sleep(5555);
//		MTDriver.switchTo().frame(MTDriver.findElement(By.xpath(".//*[@id='sbox-content']/iframe")));
		textbox(MTDriver, By.xpath(".//*[@id='jform_access_token_life']"), "enter", value);
		link(MTDriver, By.xpath(".//*[@id='component-form']/fieldset/div[1]/button[1]"), "click");
		waitForPageToLoad(MTDriver);
//		link(MTDriver, By.xpath(".//*[@id='component-form']/fieldset/div[1]/button[2]"), "click");
	}


}
