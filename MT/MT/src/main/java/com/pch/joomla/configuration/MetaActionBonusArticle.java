package com.pch.joomla.configuration;

import org.openqa.selenium.By;

import com.pch.utilities.Action_Wrapper;

public class MetaActionBonusArticle extends Action_Wrapper {

	private By pchClassName = By.id("pch_game_classname");
	private By tokenMultiplier = By.id("token_multiplier");
	private By maxEntryAllowed = By.id("max_entries_allowed");
	private By maxTokenBonus = By.id("max_token_bonus");
	
	// To Configure Token Multiplier and max token Allowed
	public void configureBonusAward(String TokenMultiplier, String MaxEntryAllowed, String MaxTokenBonus) {
		textbox(MTDriver, tokenMultiplier, "enter", TokenMultiplier);
		textbox(MTDriver, maxEntryAllowed, "enter", MaxEntryAllowed);
		textbox(MTDriver, maxTokenBonus, "enter", MaxTokenBonus);
	}
	
	public void actionOnPCHGameClassName (String action) {
		textbox(MTDriver, pchClassName, "enter", action);
	}
	
	
}
