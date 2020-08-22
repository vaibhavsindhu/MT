package com.pch.configuration.testpages;

import static com.pch.utilities.Log.Log;

import java.io.File;

import org.openqa.selenium.By;

import com.pch.utilities.Action_Wrapper;

public class Lotto_Contest_Admin {

	/*
	 * Navigate to Contest Details page using contest id
	 */
	public void navigateToLottoAdminContestDetails(int contestId) {

		Log.info("Navigate To Lotto Admin Contest Details page");
		try {
			Action_Wrapper.MTDriver.navigate().to(Action_Wrapper.returnPropertyValue("Lotto_Contest_Detail") + File.separator + contestId);
			Action_Wrapper.waitForPageToLoad(Action_Wrapper.MTDriver);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * Return the Contest Detail key value
	 */	
	public String getContestDetail(String value){
		String xpath = String.format("//html/body/div[1]/div[2]/div/ul[1]/li/label[text()='%s:']/parent::*/span", value);
		Log.info("Contest Detail xpath "+xpath);
		String detailString  = Action_Wrapper.waitForElement(Action_Wrapper.MTDriver, By.xpath(xpath), 10).getText().toString();
		Log.info(value+" :- "+detailString);
		return detailString;
	}
	
}
