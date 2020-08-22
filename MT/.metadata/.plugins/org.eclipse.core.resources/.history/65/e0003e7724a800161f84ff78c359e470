package com.pch.configuration.testpages;

import org.openqa.selenium.By;

import com.pch.utilities.AccessToken;

public class SegmentationApi extends AccessToken {
	
	public enum segmentApiType {
		SetByEmail, SetByGmt,SetByMaid, SetByMatchCode
	}
	
	public void navigateToSegmentationApi() {
		try {
			MTDriver.navigate().to(returnPropertyValue("CS_SegmentationAdminPage"));
		} catch (Exception e) {
		e.printStackTrace();
		}
	}
	
	public void selectSegmentMemberType(segmentApiType value) {
		link(MTDriver, By.id("rb"+value.toString()), "click");
	}
	
	//----------------------------Set By Email----------------------------
	public void getSegmentMembershipByEmail(String value){
		textbox(MTDriver, By.id("txtGetByEmail"), "enter", value);
		
		link(MTDriver, By.id("btnGetSegmentsMembershipByEmail"), "click");
	}

	public void setSegmentMembershipByEmailBySegmentName(String email, String... data) {
		textbox(MTDriver, By.id("txtSetByEmailAndNameEmail"), "enter", email);
		for (int i = 0; i < data.length; i++) {
			SelectValueFromDropDown(By.id("lbxSetByEmailAndNameActiveSegments"), data[i]);
		}
		link(MTDriver, By.id("btnSetByEmailAndNameSegmentsMembership"), "click");
	}
	
	public void setSegmentMembershipByEmailBySegmentCode(String email, String... data) {
		textbox(MTDriver, By.id("txtSetByEmailAndCodeEmail"), "enter", email);
		for (int i = 0; i < data.length; i++) {
			SelectValueFromDropDown(By.id("lbxSetByEmailAndCodeActiveSegments"), data[i]);
		}
		link(MTDriver, By.id("btnSetByEmailAndCodeSegmentsMembership"), "click");
	}
	
	//----------------------------Set By GolbalMemberToken----------------------------
	public void getSegmentMembershipByGmt(String value) {
		textbox(MTDriver, By.id("txtGetByGmt"), "enter", value);
		
		link(MTDriver, By.id("btnGetSegmentsMembershipByGmt"), "click");
	}

	public void setSegmentMembershipByGmtBySegmentName(String gmt, String... data) {
		textbox(MTDriver, By.id("txtSetByGmtAndNameGmt"), "enter", gmt);
		for (int i = 0; i < data.length; i++) {
			SelectValueFromDropDown(By.id("lbxSetByGmtAndNameActiveSegments"), data[i]);
		}
		link(MTDriver, By.id("btnSetByGmtAndNameSegmentsMembership"), "click");
	}
	
	public void setSegmentMembershipByGmtBySegmentCode(String gmt, String... data) {
		textbox(MTDriver, By.id("txtSetByGmtAndCodeGmt"), "enter", gmt);
		for (int i = 0; i < data.length; i++) {
			SelectValueFromDropDown(By.id("lbxSetByGmtAndCodeActiveSegments"), data[i]);
		}
		link(MTDriver, By.id("btnSetByGmtAndCodeSegmentsMembership"), "click");
	}
	

	//----------------------------Set By Maid----------------------------
	public void getSegmentMembershipByMaid(String value) {
		textbox(MTDriver, By.id("txtGetByMaid"), "enter", value);
		
		link(MTDriver, By.id("btnGetSegmentsMembershipByMaid"), "click");
	}

	public void setSegmentMembershipByMaidBySegmentName(String maid, String... data) {
		textbox(MTDriver, By.id("txtSetByMaidAndNameMaid"), "enter", maid);
		for (int i = 0; i < data.length; i++) {
			SelectValueFromDropDown(By.id("lbxSetByMaidAndNameActiveSegments"), data[i]);
		}
		link(MTDriver, By.id("btnSetByMaidAndNameSegmentsMembership"), "click");
	}
	
	public void setSegmentMembershipByMaidBySegmentCode(String maid, String... data) {
		textbox(MTDriver, By.id("txtSetByMaidAndCodeMaid"), "enter", maid);
		for (int i = 0; i < data.length; i++) {
			SelectValueFromDropDown(By.id("lbxSetByMaidAndCodeActiveSegments"), data[i]);
		}
		link(MTDriver, By.id("btnSetByMaidAndCodeSegmentsMembership"), "click");
	}

	//----------------------------Set By MatchCode----------------------------
	public void getSegmentMembershipByMatchCode(String value) {
		textbox(MTDriver, By.id("txtGetByMatchCode"), "enter", value);
			
		link(MTDriver, By.id("btnGetSegmentsMembershipByMatchCode"), "click");
	}

	public void setSegmentMembershipByMatchCodeBySegmentName(String matchCode, String... data) {
		textbox(MTDriver, By.id("txtSetByMatchCodeAndName"), "enter", matchCode);
		for (int i = 0; i < data.length; i++) {
			SelectValueFromDropDown(By.id("lbxSetByMatchCodeAndNameActiveSegments"), data[i]);
		}
		link(MTDriver, By.id("btnSetByMatchCodeAndNameSegmentsMembership"), "click");
	}
		
	public void setSegmentMembershipByMatchCodeBySegmentCode(String matchCode, String... data) {
		textbox(MTDriver, By.id("txtSetByMatchCodeAndCode"), "enter", matchCode);
		for (int i = 0; i < data.length; i++) {
			SelectValueFromDropDown(By.id("lbxSetByMatchCodeAndCodeActiveSegments"), data[i]);
		}
		link(MTDriver, By.id("btnSetByMatchCodeAndCodeSegmentsMembership"), "click");
	}

}