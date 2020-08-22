package com.pch.test.script;

import static com.pch.utilities.Log.endTestCase;
import static com.pch.utilities.Log.startTestCase;
import static com.pch.utilities.Log.Log;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.pch.utilities.AccessToken;
import com.pch.utilities.Action_Wrapper;
import com.pch.utilities.GroupName;
import com.pch.utilities.Jmeter_Xml_Parser;


public class B_31137_MT2_Leveling_Services_leveling_details extends Action_Wrapper {

	Jmeter_Xml_Parser xp = new Jmeter_Xml_Parser();
	String accessToken;
	
	String LevelInfo_0="bronze";
	String LevelInfo_2500="silver";
	String LevelInfo_100000="gold";
	String LevelInfo_500000="platinum";

	String LevelInfo_50000000="diamond";
	String LevelInfo_1000000="emerald";
	String LevelInfo_200000000="black_diamond";
	String LevelInfo_5000000="sapphire";
	
	String LevelText_Bronze= "<p class=\"token-level-feedback-header\">Welcome to the PCHRewards Loyalty Program, <span class=\"token-level-name\">%s!</span></p><p class=\"token-level-feedback\">We're so excited to recognize you as a Bronze member.</p><div class=\"token-level-feedback-expanded\">  <p class=\"token-level-feedback\">We want to make sure that you take full advantage of all the benefits that PCH has to offer. So please, take a look around, earn more Tokens and you can elevate to Silver Status in no time.</p><p class=\"token-level-feedback\">The PCHRewards Loyalty Program is a unique way for us to reward our most dedicated players ... because at PCH, the more you play, the more it could pay!</p></div>";
	String LevelText_black_diamond = "<p class=\"token-level-feedback-header\">Congratulations, %s!  You Did It!</p><p class=\"token-level-feedback\">You are among an elite group of the ultimate Players who get to enjoy the BEST PCHRewards benefits available!</p><div class=\"token-level-feedback-expanded\">  <ul class=\"token-level-feedback-list\"><li>Amazing Daily 25,000 PCHRewards Welcome Tokens!</li><li>7X Thank You Wheel Spin!</li><li>Incredible $500.00 Daily Token Leader Reward!</li><li>Unlock An Exciting Black Diamond Prize Opportunity in our PCHRewards Token Exchange!</li></ul><p class=\"token-level-feedback\">We're giving you the MAXIMUM opportunities to win with PCHRewards to say Thank You for being one of our best players!  Please enjoy them with our gratitude ... and keep earning those Tokens to go for the BIG Daily Token Leader reward because the more you play, the more it could pay!</p></div>";
	String LevelText_diamond = "<p class=\"token-level-feedback-header\">%s, this is indeed a triumph! </p><p class=\"token-level-feedback\">You've earned your Diamond status with all your remarkable activity, so please enjoy all the benefits you've been striving to achieve! </p><div class=\"token-level-feedback-expanded\">   <ul class=\"token-level-feedback-list\"><li>Amazing Daily 15,000 PCHRewards Welcome Tokens!</li><li>6X Thank You Wheel Spin!</li><li>Incredible $400.00 Daily Token Leader Reward!</li><li>Unlock An Exciting Diamond Prize Opportunity in our PCHRewards Token Exchange!</li> </ul><p class=\"token-level-feedback\">The more you play, the more it could pay -- and you're so close to achieving the MAXIMUM OPPORTUNITIES TO WIN with PCHRewards!  Keep earning those Tokens ... we'd love to welcome you to the ELITE Black Diamond tier soon!</p></div>";
	String LevelText_emerald = "<p class=\"token-level-feedback-header\">%s, you're already half way to the Top!  </p><p class=\"token-level-feedback\">You've proven that you're really in it to win it, and with Emerald Status you've unlocked even more great benefits! </p> <div class=\"token-level-feedback-expanded\">  <ul class=\"token-level-feedback-list\"><li>Amazing Daily 5,000 PCHRewards Welcome Tokens!</li><li>4X Thank You Wheel Spin!</li><li>Incredible $300.00 Daily Token Leader Reward!</li><li>Unlock An Exciting Emerald Prize Opportunity in our PCHRewards Token Exchange!</li></ul><p class=\"token-level-feedback\">Those benefits are outstanding -- and just imagine, you can unlock even more when you reach Sapphire Status.  So keep going ... because the more you play, the more it could pay! </p></div>";
	String LevelText_gold = "<p class=\"token-level-feedback-header\">Way to go, %s!</p><p class=\"token-level-feedback\">Playing more has earned you Gold Status and unlocked the next tier of PCHRewards benefits, so make sure you take advantage of all that we're offering!</p><div class=\"token-level-feedback-expanded\">  <ul class=\"token-level-feedback-list\"><li>Amazing Daily 500 PCHRewards Welcome Tokens!</li><li>2X Thank You Wheel Spin!</li><li>Incredible $150.00 Daily Token Leader Reward!</li><li>Unlock An Exciting Gold Prize Opportunity in our PCHRewards Token Exchange!</li></ul><p class=\"token-level-feedback\">Remember, the more you play, the more it could pay, so keep earning those Tokens and you could elevate to Platinum Status sooner than you think!</p></div>";
	String LevelText_platinum="<p class=\"token-level-feedback-header\">%s, you are amazing! </p><p class=\"token-level-feedback\">You've shown us you want to win by playing often -- and your Platinum Status has unlocked even bigger benefits! </p> <div class=\"token-level-feedback-expanded\">  <ul class=\"token-level-feedback-list\"><li>Amazing Daily 1,000 PCHRewards Welcome Tokens!</li><li>3X Thank You Wheel Spin!</li><li>Incredible $250.00 Daily Token Leader Reward!</li><li>Unlock An Exciting Platinum Prize Opportunity in our PCHRewards Token Exchange!</li></ul><p class=\"token-level-feedback\">You're getting closer and closer to the top so don't give up now!  Remember, the more you play, the more it could pay, so keep earning those Tokens and you could enjoy the benefits of Emerald Status sooner than you think!</p></div>";
	String LevelText_sapphire="<p class=\"token-level-feedback-header\">Wow, %s, what an achievement!</p><p class=\"token-level-feedback\">There's no stopping you now!  As a Sapphire Player you're past the halfway point and have unlocked the next tier of extraordinary benefits! </p> <div class=\"token-level-feedback-expanded\">  <ul class=\"token-level-feedback-list\"><li>Amazing Daily 10,000 PCHRewards Welcome Tokens!</li><li>5X Thank You Wheel Spin!</li><li>Incredible $350.00 Daily Token Leader Reward!</li><li>Unlock An Exciting Sapphire Prize Opportunity in our PCHRewards Token Exchange!</li></ul><p class=\"token-level-feedback\">You're SO close to that coveted Diamond Status, so please play every day to MAXIMIZE your Token Earnings and elevate as fast as you can. Remember, the more you play ... the more it could pay! </p></div>";
	String LevelText_silver="<p class=\"token-level-feedback-header\">You're on the right track, %s! </p><p class=\"token-level-feedback\">You've started growing your Token bank and have already unlocked the benefits of Silver Status! </p><div class=\"token-level-feedback-expanded\">  <ul class=\"token-level-feedback-list\"><li>Amazing Daily 250 PCHRewards Welcome Tokens!</li><li>1X Thank You Wheel Spin!</li><li>Incredible $100.00 Daily Token Leader Reward!</li><li>Unlock An Exciting Silver Prize Opportunity in our PCHRewards Token Exchange!</li></ul><p class=\"token-level-feedback\">The more you play, the more it could pay...so keep earning those Tokens and you could reach Gold Status sooner than you think!</p></div>";
	
	
	@BeforeClass
	public void setup() throws Exception {
		
		startTestCase("[MT2] Leveling Services- QA Only");
		createAccessTokenForAllApp("playnwin");

		accessToken=AccessToken.getAccessToken("PLAYANDWIN");
		writeInProprtyFile("Access_Token_PLAYANDWIN",  accessToken);
		copyAndRenameFile();
	}

	@Test(groups = { GroupName.Levelling })
	public void BackLogItem_B_31137_MT2_Leveling_Services_leveling_details() throws Exception {
		
		Log.info("Start Executing Jmeter Script");
		
		CallJmeterBuild("B_31137_MT2_Leveling_Services_leveling_details.jmx");
		Log.info("JMX file Called");
		
		Log.info("Verifying Level Info");
		Assert.assertEquals(LevelInfo_0, returnPropertyValue("\\LevelInfo","LevelInfo_0"));
		Assert.assertEquals(LevelInfo_2500, returnPropertyValue("\\LevelInfo","LevelInfo_2500"));
		Assert.assertEquals(LevelInfo_100000, returnPropertyValue("\\LevelInfo","LevelInfo_100000"));
		Assert.assertEquals(LevelInfo_500000, returnPropertyValue("\\LevelInfo","LevelInfo_500000"));
		Assert.assertEquals(LevelInfo_1000000, returnPropertyValue("\\LevelInfo","LevelInfo_1000000"));
		Assert.assertEquals(LevelInfo_5000000, returnPropertyValue("\\LevelInfo","LevelInfo_5000000"));
		Assert.assertEquals(LevelInfo_50000000, returnPropertyValue("\\LevelInfo","LevelInfo_50000000"));
		Assert.assertEquals(LevelInfo_200000000, returnPropertyValue("\\LevelInfo","LevelInfo_200000000"));
		
		Log.info("Verifying Level Text");
		Assert.assertEquals(LevelText_Bronze, returnPropertyValue("\\LevelInfo","LevelText_bronze"));
		Assert.assertEquals(LevelText_black_diamond, returnPropertyValue("\\LevelInfo","LevelText_black_diamond"));
		Assert.assertEquals(LevelText_diamond, returnPropertyValue("\\LevelInfo","LevelText_diamond"));
		Assert.assertEquals(LevelText_emerald, returnPropertyValue("\\LevelInfo","LevelText_emerald"));
		Assert.assertEquals(LevelText_gold, returnPropertyValue("\\LevelInfo","LevelText_gold"));
		Assert.assertEquals(LevelText_platinum, returnPropertyValue("\\LevelInfo","LevelText_platinum"));
		Assert.assertEquals(LevelText_sapphire, returnPropertyValue("\\LevelInfo","LevelText_sapphire"));
		Assert.assertEquals(LevelText_silver, returnPropertyValue("\\LevelInfo","LevelText_silver"));
		
		
		Assert.assertEquals("false", xp.Xml_Parser("B_31137_MT2_Leveling_Services_leveling_details.xml"));
		Log.info("Assetion Validation Completed");
	}

	@AfterClass
	public void tearDown() {
		endTestCase("End");

	}
}
