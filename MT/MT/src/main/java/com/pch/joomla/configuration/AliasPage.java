package com.pch.joomla.configuration;

import static com.pch.utilities.Log.Log;

import org.openqa.selenium.By;

import com.pch.utilities.Action_Wrapper;

public class AliasPage extends Action_Wrapper {

	private By finishPublishing = By.xpath("html/body/div[3]/div[3]/div/form/div[2]/div/div[2]/div/fieldset/ul/li[5]/input");
	private By startPublishing = By.xpath("html/body/div[3]/div[3]/div/form/div[2]/div/div[2]/div/fieldset/ul/li[4]/input");
	private By status = By.id("jform_state");

	public AliasPage(){
		waitForPageToLoad(MTDriver);
	}
	public enum status {
		Published, Unpublished, Archived, Trashed;
	}

	public void startPublishingDate(String value) {
		waitForPageToLoad(MTDriver);
		Log.info("Start Publishing Date " + value);
		textbox(MTDriver, startPublishing, "enter", value);
	}

	public void finishPublishingDate(String value) {
		waitForPageToLoad(MTDriver);

		Log.info("Finish Publishing Date " + value);
		textbox(MTDriver, finishPublishing, "enter", value);
	}

	public void status(status value) {
		Log.info("Changing article status " + value.toString());
		selectoption(status, value.toString());
	}
	
	public void save(){
		
	}
	public void saveAndClose() {
		button(MTDriver, By.xpath("html/body/div[3]/div[1]/div/div[1]/ul/li[2]/a"), "click");
		waitForPageToLoad(MTDriver);
	}

}
