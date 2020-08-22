package com.pch.iwe.page;

import static com.pch.utilities.Log.Log;

import java.io.IOException;

import org.openqa.selenium.By;

import com.pch.utilities.*;

public class DevicesPage extends Action_Wrapper {

	private static By GiveawayGroupDropDown = By.name("giveawayGroupData.id");
	@SuppressWarnings("unused")
	private static By TokenCatchAll = By.xpath("//input[@name='giveawayTokenCatchAllData.id']");
	private static By TokenCatchAllchkbox = By
			.xpath("//label[text()='Giveaway Token Catchall:']/parent::*/parent::*/descendant::input");
	private static By businessUnitDropDown = By.name("businessUnitData.id");
	private static By commentBox = By.name("comment");
	private static By SaveButton = By.xpath("//span[text()='Save']");
	private static By noOfWin = By.xpath("//input[contains(@name,'noOfWinsAllowed')]");
	private static By noOfRequest = By.xpath("//input[contains(@name,'noOfRequestAllowed')]");
	
	@SuppressWarnings("unused")
	private static By retrictionType = By.id("restrictionTypeCombo-inputEl");

	/*
	 * Device Page Methods
	 * 
	 */

	// Method to Navigate to specefic Device ID
	public void naviagateToIWEDeviceList(String DeviceID) {
		try {
			waitForPageToLoad(MTDriver);
			MTDriver.get("https://iwe.qa.pch.com/iwe/#device/edit/" + DeviceID);
			waitForPageToLoad(MTDriver);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
// Modify Play Restrictions on Edit Device Page
	public void setPlayRestrictions(String requestAllowed, String WinsAllowed, String restrictionType) {
		textbox(MTDriver, noOfRequest, "enter", requestAllowed);
		textbox(MTDriver, noOfWin, "enter", WinsAllowed);
		Log.info("Success: Play restrictions Configured");
	}
	
	
	// "Add" or "remove" Token Catch ALL on Device Page
	
	public void setTokenCatchAll(String Action, String GiveawayGroup) throws Exception {
		if (Action.equalsIgnoreCase("remove")) {
//			MTDriver.findElement(TokenCatchAll).clear();
			checkBox(MTDriver, TokenCatchAllchkbox, "uncheck");
		} else if (Action.equalsIgnoreCase("add")) {
			checkBox(MTDriver, TokenCatchAllchkbox, "check");
			MTDriver.findElement(GiveawayGroupDropDown).clear();
			MTDriver.findElement(GiveawayGroupDropDown).sendKeys(GiveawayGroup);
			Log.info("Success: GiveAway group configured successfuly");
		}
	}
	
	public void ClickButtonSave() {

		button(MTDriver, SaveButton, "click");
		waitForPageToLoad(MTDriver);
		Log.info("Success: Clicked on Save Button");
	}

	public void setComments(String comment) throws Exception {

		MTDriver.findElement(commentBox).sendKeys(comment);
		Log.info("Success: Updated Comments before saving the config");
	}
	

	public void setGiveawayGroup(String GiveawayGroup) throws Exception {
		textbox(MTDriver, GiveawayGroupDropDown, "enter", GiveawayGroup);
		waitForPageToLoad(MTDriver);
		selectItemFromDropdown(By.xpath("//*[contains(text(), '"+GiveawayGroup+"')]"),0);
		Log.info("Success: GiveAway group configured successfuly");
	}

	public void setBusinessUnit(String BusinessUnit) throws Exception {
		MTDriver.findElement(businessUnitDropDown).clear();
		MTDriver.findElement(businessUnitDropDown).sendKeys(BusinessUnit);
		Log.info("Success: BU ID selected successfuly");
	}
	
	public static void editDevices(String DeviceID) throws IOException,
			Exception {
		waitForPageToLoad(MTDriver);
		String URL = returnPropertyValue("Device ID ::") + DeviceID;
		System.out.println(URL);
		MTDriver.get(URL);
		waitForPageToLoad(MTDriver);

	}

	
	
	/*
	 * Device Page Methods Ends
	 */
}