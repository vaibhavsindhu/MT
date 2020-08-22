package com.pch.configuration.testpages;

import static com.pch.utilities.Log.Log;

import org.openqa.selenium.By;

import com.pch.utilities.Action_Wrapper;

public class MidTier_API_Test_Page extends Action_Wrapper {

	public void api(String serviceName, String apiName) {
		Log.info("Clicking API Link Page");
		String api = String.format("//div[@class='leftpane']/h2/a[Text()='%s']/following::li/a[Text()='%s']",serviceName,apiName);
		MTDriver.findElement(By.xpath(api));
	}
	
	public void accessToken(String field, String value) {
		Log.info("Creating Access Token");
		if(field.equalsIgnoreCase("client_id")){
			textbox(MTDriver, By.id("client_id"), "enter", value);
		}else if(field.equalsIgnoreCase("client_secret")) {
			textbox(MTDriver, By.id("client_secret"), "enter", value);
		}
	}
	
	public void submitButton() {
		link(MTDriver, By.id("submitButton"), "click");
	}
}
