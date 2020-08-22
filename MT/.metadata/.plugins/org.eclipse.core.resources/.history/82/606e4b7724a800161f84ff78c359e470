package com.pch.joomla.configuration;

import static com.pch.utilities.Log.Log;

import java.io.IOException;

import org.openqa.selenium.By;

import com.pch.utilities.Action_Wrapper;

public class UserManager extends Action_Wrapper {
	ArticleManager article = new ArticleManager();
	public void navigateToUserManager() {
		Log.info("Navigating to User Manager");
		try {
			MTDriver.navigate().to(returnPropertyValue("Joomla_URL")+returnPropertyValue("Joomla_UserManager"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void searchuser(String value) {
		Log.info("Searching for user  :- " + value);
		textbox(MTDriver, By.id("filter_search"), "enter", value);
		link(MTDriver, By.xpath("//*[@id='filter-bar']/div[1]/button[1]"), "click");
		waitForPageToLoad(MTDriver);
		link(MTDriver, By.xpath("//*[@id='adminForm']/table/tbody/tr/td[2]/a"), "click");
	}
	public void unCheckAssignUserGroup(){
		Log.info("UnChecking User Permission");
		for (int i = 1; i < 15; i++) {
			if(i!=11){
				if(MTDriver.findElement(By.id("1group_"+i+"")).isSelected()){
					link(MTDriver, By.id("1group_"+i+""), "click");
				}
			}
		}
	}
	public void assignUserGroup(String type) {
		Log.info("Changing User Permission : " +type);
		unCheckAssignUserGroup();
		if(type.equalsIgnoreCase("Public")){
			link(MTDriver, By.id("1group_1"), "click");
		}
		if(type.equalsIgnoreCase("Manager")){
			link(MTDriver, By.id("1group_6"), "click");
		}
		if(type.equalsIgnoreCase("Administrator")){
			link(MTDriver, By.id("1group_7"), "click");
		}
		if(type.equalsIgnoreCase("Casino Admin")){
			link(MTDriver, By.id("1group_10"), "click");
		}
		if(type.equalsIgnoreCase("Live To Win Admin")){
			link(MTDriver, By.id("1group_12"), "click");
		}
		if(type.equalsIgnoreCase("PCHApp Admin")){
			link(MTDriver, By.id("1group_14"), "click");
		}
		if(type.equalsIgnoreCase("Play and Win Admin")){
			link(MTDriver, By.id("1group_9"), "click");
		}
		if(type.equalsIgnoreCase("Real Bucks Admin")){
			link(MTDriver, By.id("1group_13"), "click");
		}
		if(type.equalsIgnoreCase("Skill Based Game Admin")){
			link(MTDriver, By.id("1group_11"), "click");
		}
		if(type.equalsIgnoreCase("Registered")){
			link(MTDriver, By.id("1group_2"), "click");
		}
		if(type.equalsIgnoreCase("Author")){
			link(MTDriver, By.id("1group_3"), "click");
		}
		if(type.equalsIgnoreCase("Editor")){
			link(MTDriver, By.id("1group_4"), "click");
		}
		if(type.equalsIgnoreCase("Publisher")){
			link(MTDriver, By.id("1group_5"), "click");
		}
		if(type.equalsIgnoreCase("Super Users")){
			link(MTDriver, By.id("1group_8"), "click");	
		}
	}
	
	public void changeUserGroup(String groupName) throws IOException, Exception{
		navigateToUserManager();
		searchuser(returnPropertyValue("Joomla_Username_Name"));
		assignUserGroup(groupName);
		article.saveArticle();
	}
}
