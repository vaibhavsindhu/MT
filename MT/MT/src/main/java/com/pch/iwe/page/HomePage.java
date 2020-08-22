package com.pch.iwe.page;

import static com.pch.utilities.Log.Log;

import java.io.IOException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.pch.utilities.*;

@SuppressWarnings("unused")
public class HomePage extends Action_Wrapper {

	private static By getUsername = By.id("j_username");
	private static By getPassword = By.id("j_password");
	private static By LoginBtn = By.id("submit");
	private static By Giveaways = By.linkText("Giveaways");
	private static By GiveawayGroups = By
			.xpath("//span[text()='Giveaway Groups']");
	private static By Prizes = By.xpath("//button[contains(.,'Prizes')]");
	private static By Devices = By.xpath("//button[contains(.,'Devices')]");
	private static By StartDate = By
			.xpath("//label[text()='Start Date:']/parent::*/parent::*/descendant::input");
	private static By EndDate = By
			.xpath("//label[text()='End Date:']/parent::*/parent::*/descendant::input");
	private static By GiveawayGroupDropDown = By.name("giveawayGroupData.id");
	private static By businessUnitDropDown = By.name("businessUnitData.id");
	private static By commentBox = By.name("comment");
	private static By SaveButton = By.xpath("//*[contains(text(), 'Save')]");
	private static By SaveButton_Alert=By.id("button-1006-btnInnerEl");
	private static By ServerWarningAlert_Save=By.id("messagebox-1001");

	
	private static By retrictionType = By.id("restrictionTypeCombo-inputEl");

	public static void click_Giveaways() throws Exception {
		//holdon(5000);
		Thread.sleep(5000);
		link(MTDriver, Giveaways, "click");
	}

	public static void click_GiveawayGroups() throws Exception {
		//holdon(5000);
		Thread.sleep(5000);
		link(MTDriver, GiveawayGroups, "click");
	}

	public static void click_Prizes() throws Exception {
		//holdon(5000);
		Thread.sleep(5000);
		link(MTDriver, Prizes, "click");
	}

	public static void click_Devices() {
		link(MTDriver, Devices, "click");

	}

	// This method is not required as this can be covered in "naviagateToIWEGiveawayList()" like "naviagateToIWEDeviceList"
	public void editGiveAway(String GiveAwayNo) throws IOException,
			Exception {
		waitForPageToLoad(MTDriver);
		String URL = returnPropertyValue("IWEGiveAwayList") + GiveAwayNo;
		System.out.println(URL);
		MTDriver.get(URL);
		waitForPageToLoad(MTDriver);

	}
// This method should be under device page
	public static void editDevices(String DeviceID) throws IOException,
			Exception {
		waitForPageToLoad(MTDriver);
		String URL = returnPropertyValue("Device ID ::") + DeviceID;
		System.out.println(URL);
		MTDriver.get(URL);
		waitForPageToLoad(MTDriver);

	}


	public void naviagateToIWEGiveawayList(String GiveAwayNo) {
		try {
			MTDriver.navigate().to(returnPropertyValue("IWEGiveAwayList") + GiveAwayNo);
			waitForPageToLoad(MTDriver);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void naviagateToIWEDeviceList(String DeviceID) {
		try {
			waitForPageToLoad(MTDriver);
//			MTDriver.get("https://iwe.qa.pch.com/iwe/#device/edit/" + DeviceID);
			MTDriver.get(returnPropertyValue("IWEEditDevice") + DeviceID);
			waitForPageToLoad(MTDriver);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	
	public void setStartDate(String inputstartDate) throws Exception {
		//holdon(5000);
		Thread.sleep(5000);
		textbox(MTDriver, StartDate, "enter", inputstartDate);
	}

	public void setEndDate(String inputEndDate) throws Exception {

		textbox(MTDriver, EndDate, "enter", inputEndDate);
	}

	/*
	 * This Method is for Modifying the Gwyawy start and End Date
	 */
	public void modifygwyawyDate(String StartDate, String EndDate) {
		try {
			setStartDate(StartDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			setEndDate(EndDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void ClickButtonSave() {
		button(MTDriver, SaveButton, "click");
		   waitForPageToLoad(MTDriver);
			if (MTDriver.findElement(SaveButton_Alert).isDisplayed())
			{
				button(MTDriver,SaveButton_Alert,"click");
				 waitForPageToLoad(MTDriver);
			} else 	
		   waitForPageToLoad(MTDriver);		
	}
	
	public void setComments(String comment) throws Exception {
		textbox(MTDriver, commentBox, "enter", comment);
		waitForPageToLoad(MTDriver);
	}

	public void setGiveawayGroup(String GiveawayGroup) throws Exception {
		textbox(MTDriver, GiveawayGroupDropDown, "enter", GiveawayGroup);
	}

	public void setBusinessUnit(String BusinessUnit) throws Exception {
		textbox(MTDriver, businessUnitDropDown, "enter", BusinessUnit);
	}
	
	public void navigateToIWELoginPage() {
		  try {
		   MTDriver.navigate().to(returnPropertyValue("IWELoginPage"));
		   
		   waitForPageToLoad(MTDriver);
		  } catch (Exception e) {
		   e.printStackTrace();
		  }
	}

	public void IWE_Login() throws Exception {
		navigateToIWELoginPage();
		textbox(MTDriver, getUsername, "enter", returnPropertyValue("IWE_LoginName"));
		textbox(MTDriver, getPassword, "enter", returnPropertyValue("IWE_Password"));
		waitForPageToLoad(MTDriver);
		button(MTDriver, LoginBtn, "click");
		waitForPageToLoad(MTDriver);
		Log.info("User able to Login to IWE");
	}
	
}