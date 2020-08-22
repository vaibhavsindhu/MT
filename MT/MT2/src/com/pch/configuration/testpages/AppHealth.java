package com.pch.configuration.testpages;

import static com.pch.utilities.Log.Log;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.pch.utilities.Action_Wrapper;

public class AppHealth extends Action_Wrapper {

	/*
	 * Navigate to AppHealth Page
	 * @Return: void
	 */
	public void navigate() {
		Log.info("Navigating to AppHealth page");
		try {
			MTDriver.get("https://" + returnPropertyValue("Base_API_URL_Without_Ver") + "/" + returnPropertyValue("AppHealth_URL"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * @param: service variable contain the name for which service status need to check
	 * @Return: Status of service in String format
	 */
	public String serviceStatus(String service) {

		List<WebElement> element = MTDriver.findElements(By.xpath("html/body"));
		String statusList[] = null;
		for (WebElement e1 : element) {
			statusList = e1.getText().split("\n");
		}
		
		for (int i = 0; i < statusList.length; i++) {
		
			if(statusList[i].toString().contains(service)){
				Log.info("Service Status is "+statusList[i]);
				return statusList[i].split(": ")[1];
			}
		}
		return null;
	}
	
	/*
	 * Check page current url, if page url is not apphealth then it navigate to apphealth page
	 * 
	 * @param: service variable contain the name for which service status need to check
	 * @Return: Status of service in String format
	 */
	public String validateStatus(String service) throws IOException, Exception{
		Log.info("Validating service url :- "+ service);
		
		if(!MTDriver.getCurrentUrl().equalsIgnoreCase(("https://" + returnPropertyValue("Base_API_URL_Without_Ver") + "/"+ returnPropertyValue("AppHealth_URL")))){
			navigate();
		}
		return serviceStatus(service);
	}
}
