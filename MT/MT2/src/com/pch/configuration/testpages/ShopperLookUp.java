package com.pch.configuration.testpages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.record.PageBreakRecord.Break;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.pch.utilities.Action_Wrapper;

@SuppressWarnings("unused")
public class ShopperLookUp extends Action_Wrapper{
	
	//login to shopperlookup
	public static void loginShopperLookUp() throws Exception{
		if(MTDriver.getTitle().equalsIgnoreCase("default")) {
			return;
		}
		MTDriver.get("data:,");
		if(getEnvironment().equalsIgnoreCase("QA"))
			Runtime.getRuntime().exec("src/com/pch/resources/shopper.exe "+returnPropertyValue("Shopper_Look_Up_Url")+" "+returnPropertyValue("Shopper_Look_Up_Login")+" "+returnPropertyValue("Shopper_Look_Up_Password"));
		else {
			Runtime.getRuntime().exec("src/com/pch/resources/shopperSTG.exe "+returnPropertyValue("Shopper_Look_Up_Url")+" "+returnPropertyValue("Shopper_Look_Up_Login")+" "+returnPropertyValue("Shopper_Look_Up_Password"));

		}
		while(!(MTDriver.getTitle().equalsIgnoreCase("default"))) {
			//holdon();
			
		}
	}
	
	//search button
	public static void search() {
		link(MTDriver, By.xpath(".//*[@id='ButtonSearch']"), "click");
	}

	//reset button
	public static void reset_field() {
		link(MTDriver, By.xpath(".//*[@id='ButtonClear']"), "click");
	}

	// Criteria Selecting Panel
	public static void criteria(String field, String value) {
		if (field.equalsIgnoreCase("Email / Md5Hash Email")) {
			textbox(MTDriver, By.xpath(".//*[@id='TextBoxShopper']"), "enter", value);
		} else if (field.equalsIgnoreCase("GlobalMemberToken")) {
			textbox(MTDriver, By.xpath(".//*[@id='TextBoxGmt']"), "enter", value);
		} else if (field.equalsIgnoreCase("On-Line Order")) {
			textbox(MTDriver, By.xpath(".//*[@id='TextBoxOnlineOrder']"), "enter", value);
		} else if (field.equalsIgnoreCase("Off-Line Order")) {
			textbox(MTDriver, By.xpath(".//*[@id='TextBoxOfflineOrder']"), "enter", value);
		} else if (field.equalsIgnoreCase("Customer Id")) {
			textbox(MTDriver, By.xpath(".//*[@id='TextBoxCustomerId']"), "enter", value);
		} else if (field.equalsIgnoreCase("Last Name")) {
			textbox(MTDriver, By.xpath(".//*[@id='TextBoxLastName']"), "enter", value);
		} else if (field.equalsIgnoreCase("State")) {
			SelectValueFromDropDown(By.xpath(".//*[@id='ComboBoxStates']"), value);
		} else if (field.equalsIgnoreCase("Zip Code")) {
			textbox(MTDriver, By.xpath(".//*[@id='TextBoxZipCode']"), "enter", value);
		}
	}

	//Click Menu tab link
	public static void clickMenuTab(String tabName){
		String xpath = String.format("//table[@tabid='UltraWebTab1']/descendant::td[text()='%s']", tabName);
		link(MTDriver, By.xpath(xpath), "click");
	}
	
	
	/*
	 * Get Contest Entries Data from the table
	 * @forms:- Used to get total number of Contest Entries
	 * @latestEntryDate:- Store the latest contest entry date
	 */
	public static String contestEntriesData(String value){
		List<WebElement> forms =  MTDriver.findElements(By.xpath("//*[@id='UpdatePanelGrid']/descendant::tr"));
		for (int i = 2; i <= forms.size(); i++) {
			String xpath = String.format("//*[@id='UpdatePanelGrid']/descendant::tr["+i+"]/td[@title='%s']", value);
			WebElement element = waitForElement(MTDriver, By.xpath(xpath),0);
			return element.getText();
		}
		return null;
	}
		

	public static String searchByEmailContest(String email, String searchString) throws IOException, Exception {
		loginShopperLookUp();
		waitForPageToLoad(MTDriver);
		criteria("Email / Md5Hash Email", email);
		waitForPageToLoad(MTDriver);
		search();
		waitForPageToLoad(MTDriver);
		clickMenuTab("Contest Entries");
		return contestEntriesData(searchString);
	}
}