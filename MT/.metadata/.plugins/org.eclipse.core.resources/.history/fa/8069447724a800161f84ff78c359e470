package com.pch.joomla.configuration;

import org.openqa.selenium.By;

import com.pch.utilities.Action_Wrapper;

public class ArticleActions extends Action_Wrapper {
	private By jsonData = By.id("json_data");
	private By rulesFactsID = By.id("rules_facts_id");
	private By pchGameClassName = By.id("pch_game_classname");

	private By featureIcon2x_Upload = By.id("feature2x");
	private By featureIcon2x_Delete = By.id("feature2x_delete");

	private By featureIcon4x_Upload = By.id("feature4x");
	private By featureIcon4x_Delete = By.id("feature4x_delete");

	private By tileIcon2x_Upload = By.id("tile2x");
	private By tileIcon2x_Delete = By.id("tile2x_delete");

	private By tileIcon4x_Upload = By.id("tile4x");
	private By tileIcon4x_Delete = By.id("tile4x_delete");

	private By topColor = By.id("top_color");
	private By bottomColor = By.id("bottom_color");
	private By buttonColor = By.id("button_color");
	private By buttonText = By.id("button_text");
	private By crmN = By.id("crmn");
	private By description = By.id("description");
	private By actionType = By.id("action_type");
	private By action = By.id("action");
	private By actionURL = By.id("action_url");
	private By credentials = By.id("credentials");

	public void addJsonData(String value) {
		textbox(MTDriver, jsonData, "enter", value);
	}

	public void addRulesFactsId(String value) {
		textbox(MTDriver, rulesFactsID, "enter", value);
	}
	public void addPCHGameClassName(String value) {
		textbox(MTDriver, pchGameClassName, "enter", value);
	}
	public void addTopColor(String value) {
		textbox(MTDriver, topColor, "enter", value);
	}

	public void addBottomColor(String value) {
		textbox(MTDriver, bottomColor, "enter", value);
	}

	public void addButtonColor(String value) {
		textbox(MTDriver, buttonColor, "enter", value);
	}

	public void addButtonText(String value) {
		textbox(MTDriver, buttonText, "enter", value);
	}

	public void addcrmN(String value) {
		textbox(MTDriver, crmN, "enter", value);
	}

	public void addDescription(String value) {
		textbox(MTDriver, description, "enter", value);
	}

	public void addActionType(String value) {
		selectByValue(MTDriver, actionType, "select", value);
	}

	public void addAction(String value) {
		selectByValue(MTDriver, action, "select", value);
	}

	public void addActionURL(String value) {
		textbox(MTDriver, actionURL, "enter", value);
	}

	public void addCredentials(String value) {
		textbox(MTDriver, credentials, "enter", value);
	}

	public void featureIcon2x(String type) {
		switch (type) {
			case "upload":
				link(MTDriver, featureIcon2x_Upload, "click");
				fileUploader("2x_feature.jpg");
			break;
			case "Delete":
				link(MTDriver, featureIcon2x_Delete, "click");
			break;
		};
	}
	
	public void featureIcon4x(String type) {
		switch (type) {
			case "upload":
				link(MTDriver, featureIcon4x_Upload, "click");
				fileUploader("4x_feature.jpg");
			break;
			case "Delete":
				link(MTDriver, featureIcon4x_Delete, "click");
			break;
		};
	}
	
	
	public void tileIcon2x(String type) {
		switch (type) {
			case "upload":
				link(MTDriver, tileIcon2x_Upload, "click");
				fileUploader("2x_title.jpg");
			break;
			case "Delete":
				link(MTDriver, tileIcon2x_Delete, "click");
			break;
		};
	}
	
	public void tileIcon4x(String type) {
		switch (type) {
			case "upload":
				link(MTDriver, tileIcon4x_Upload, "click");
				fileUploader("4x_title.jpg");
			break;
			case "Delete":
				link(MTDriver, tileIcon4x_Delete, "click");
			break;
		};
	}
	
	public void setFeatureTileIcon_continuous()
	{
		featureIcon2x("upload");
		featureIcon4x("upload");
		tileIcon2x("upload");
		tileIcon4x("upload");
		addTopColor("##gggf");
		addBottomColor("#000000");
		addButtonColor("#abce4ac");
	}
	
	/*
	 * This method is to Select or unselect the filter article from dropdown
	 */
	public void selectFilterArticle(String Value) {
		selectByValue(MTDriver,By.id("filter_article"), "select", Value );
		
	}
	
}
