package com.pch.joomla.configuration;

import org.openqa.selenium.By;


import com.pch.utilities.Action_Wrapper;

public class BonusTriggers extends Action_Wrapper {

	private By TriggerType = By.id("trigger_type");
	private By TriggerTarget = By.id("trigger_target");
	private By TriggerFrequency = By.id("trigger_frequency");
	
	// To Configure Bonus Trigger options
	public void configureTriggerOptions(String triggerType, String trigerTarget, String frequency) {
		textbox(MTDriver, TriggerType, "enter", triggerType);
		textbox(MTDriver, TriggerTarget, "enter", trigerTarget);
		selectByValue(MTDriver, TriggerFrequency, "select", frequency);
	}

}
