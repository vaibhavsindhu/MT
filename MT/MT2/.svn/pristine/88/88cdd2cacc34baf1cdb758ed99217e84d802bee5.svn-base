package com.pch.joomla.configuration;

import static com.pch.utilities.Log.Log;

import org.openqa.selenium.By;

import com.pch.utilities.Action_Wrapper;

public class AdminLogin extends Action_Wrapper {
	
	public void navigateTLogin() {
		Log.info("Navigating to User Manager");
		try {
			browserURL("Joomla_URL");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void userName(String value) {
		Log.info("Entering Username in Textbox");
		textbox(MTDriver, By.xpath("//*[@id='mod-login-username']"), "enter",value);
	}

	public void password(String value) {
		Log.info("Entering Password in textbox");
		textbox(MTDriver, By.xpath("//*[@id='mod-login-password']"), "enter",value);
	}

	public void clickSignInbutton() {
		Log.info("Clicking Sign In Button");
		link(MTDriver,By.xpath(".//*[@id='form-login']/fieldset/div[1]/div/div/a"),"click");
	}

	public void login() {
		Log.info("Open Joomla Login Page");
		try {
			browserURL("Joomla_URL");
			userName(returnPropertyValue("Joomla_Username"));
			password(returnPropertyValue("Joomla_Password"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		clickSignInbutton();
		waitForPageToLoad(MTDriver);
		Log.info("User able to Login to Joomla");
	}
	
	public void login(String username, String password) {
		Log.info("Open Joomla Login Page");
		try {
			browserURL("Joomla_URL");
			Log.info("Getting Login with Username " +username);
			userName(username);
			password(password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		clickSignInbutton();
		waitForPageToLoad(MTDriver);
		Log.info("User able to Login to Joomla");
	}
	
	public void clickLogoutButton() {
		Log.info("Clicking Logout Button");
		waitForPageToLoad(MTDriver);
		link(MTDriver,By.xpath("//a[text()='Log out']"),"click");
		waitForPageToLoad(MTDriver);

	}
	
}