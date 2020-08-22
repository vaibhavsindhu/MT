package com.pch.joomla.configuration;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.pch.utilities.Action_Wrapper;

import static com.pch.utilities.Log.Log;

public class ArticleContinuousGame extends Action_Wrapper {
	//ArticleManager articleMng = new ArticleManager();

	private By maximumSubmissionAllowed = By.id("maximum_submission_allowed");
	private By maximumDailyTokenPayout = By.id("maximum_daily_token_payout");
	private By minimumGameplayDuration = By.id("minimum_gameplay_duration");
	private By tokenPaytableDropDown = By.id("token_paytable_article");
	private By removetokenPayablecck = By.xpath("//div[contains(@class,'cck_button_del')]");
	private By addTokenPayableCCK = By.xpath("//aside[contains(@class,'cck_cgx_button_last')]/div[contains(@class,'add')]");
	
	public void setMaximumSubmissionAllowed(Integer value) {
		Log.info("Maximum Submission Allowed : " + value);
		textbox(MTDriver, maximumSubmissionAllowed, "enter", value.toString());
	}

	public void setMaximumDailyTokenPayout(String value) {
		Log.info("Maximum DailyToken Payout : " + value);
		textbox(MTDriver, maximumDailyTokenPayout, "enter", value);
	}

	public void setMinimumGameplayDuration(Integer value) {
		Log.info("Minimum Gameplay Duration : " + value);
		textbox(MTDriver, minimumGameplayDuration, "enter", value.toString());
	}

	public void setTokenPaytableDropDown(String value) {
		SelectValueFromDropDown(tokenPaytableDropDown, value);
	}

	public void rempoveAllTokenPayable() {
		Log.info("Removing Token Payable CCk Fields");
		List<WebElement> element = getWebelementList(MTDriver,removetokenPayablecck, 1);
		for (int i = 0; i < element.size(); i++) {
			link(MTDriver, removetokenPayablecck, "click");
		}
	}

	public void upToPointsScored(Integer value, Integer pos){
		Log.info("Up To Points Scored : " + value);
		textbox(MTDriver, By.id("token_paytable_"+(pos-1)+"_up_to_points_scored"), "enter", value.toString());
	}
	
	public void tokensAwarded(String payableArticle, Integer pos){
		Log.info("Tokens Awarded : " + payableArticle);
		textbox(MTDriver, By.id("token_paytable_"+(pos-1)+"_tokens_awarded"), "enter", payableArticle.toString());
	}

	public void addTokenPayableCCK() {
		Log.info("Adding Token Payable CCk Field");
		link(MTDriver, addTokenPayableCCK, "click");
	}

	public void configureContinuesGame(String article, Integer maxSubmission, String maxDailyTokenPayout,Integer minGamePlayDuration,String paytable){
		new ArticleManager().search(article);
		setMaximumSubmissionAllowed(maxSubmission);
		setMaximumDailyTokenPayout(maxDailyTokenPayout);
		setMinimumGameplayDuration(minGamePlayDuration);
		setTokenPaytableDropDown(paytable);
		new ArticleManager().saveArticle();
	}
	
	
	public void configureTokePayablearticle(String search, String... payableArticle){
		new ArticleManager().search(search);
		rempoveAllTokenPayable();

		for (int i = 0; i < (payableArticle.length/2); i++) {
			upToPointsScored(Integer.parseInt(payableArticle[i]), i+1);
			tokensAwarded(payableArticle[i], i+1);
			addTokenPayableCCK();
		}
		new ArticleManager().saveArticle();
		
	}
}