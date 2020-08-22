package com.pch.joomla.configuration;

import org.openqa.selenium.By;

import com.pch.utilities.Action_Wrapper;

public class DataPassThrough extends Action_Wrapper {

	private By trgetURL = By.id("target_url");
	private By method = By.id("method");
	private By contentType = By.id("content_type");
	
	// Method to Configure any Passthrough details
	public void configureDataPassThrough(String targetURL, String Method, String ContenType) {
		textbox(MTDriver, trgetURL, "enter", targetURL);
		selectByValue(MTDriver, method,"select", Method);
		selectByValue(MTDriver, contentType,"select", ContenType);
	}
	
	
}
