package com.pch.configuration.testpages;

import static com.pch.utilities.Log.Log;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.By;

import com.pch.utilities.Action_Wrapper;
import com.pch.utilities.HttpUtils;

public class RFTestPage extends Action_Wrapper {

	String response,responseJSON,responseWithOutJSON,responseResult[];
	public static String New_Email = randomString(7, 8) + Date() + "@pchmail.com";
	public static String ExternalNewEmail = randomString(7, 8) + Date() + "@pchmail.com";
	public static String First_Name = randomString(5, 6) + Date();
	public static String Last_Name = randomString(5, 6) + Date();
	boolean useLetters = false;
	boolean useNumbers = true;
	int stringLength = 15;

	String ExternalID = RandomStringUtils.random(stringLength, useLetters, useNumbers) ;

	public void naviagateToCentralService() {
		try {
				MTDriver.navigate().to(returnPropertyValue("Central_Services"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void clickMiniUser() {
		link(MTDriver, By.xpath(".//*[@id='lstServices']/optgroup[2]/option[2]"), "click");
		waitForPageToLoad(MTDriver);
	}
	
	public void clickFullRegUser() {
		link(MTDriver, By.xpath(".//*[@id='lstServices']/optgroup[2]/option[3]"), "click");
		waitForPageToLoad(MTDriver);
	}
	
	public void clickPreRegUser() {
		link(MTDriver, By.xpath(".//*[@id='lstServices']/optgroup[2]/option[1]"), "click");
		waitForPageToLoad(MTDriver);
	}
	
	public void clickFBWithEmail() {
		link(MTDriver, By.xpath(".//*[@id='lstServices']/optgroup[9]/option[1]"), "click");
		waitForPageToLoad(MTDriver);
	}


	public void title(String value) {
		textbox(MTDriver, By.id("req_Title"), "enter", value);
	}
	
	public enum ChangeApplicationData {
		Spectrum,PayOnline
	}
	
	public void changeApplicatio(ChangeApplicationData data){
		SelectValueFromDropDown(By.xpath(".//*[@id='ddlstPlatform']"), data.toString());
	}
	
	
	public void firstName(String fName) {
		textbox(MTDriver, By.id("req_FirstName"), "enter", fName);
	}

	public void lastName(String lName) {
		textbox(MTDriver, By.id("req_LastName"), "enter", lName);
	}

	public void address(String value) {
		textbox(MTDriver, By.id("req_Address1"), "enter", value);
	}
	
	public void city(String value) {
		textbox(MTDriver, By.id("req_City"), "enter", value);
	}
	
	public void state(String value) {
		textbox(MTDriver, By.id("req_State"), "enter", value);
	}
	
	public void zipCode(String value) {
		textbox(MTDriver, By.id("req_ZipCode"), "enter", value);
	}
	
	public void country(String value) {
		textbox(MTDriver, By.id("req_Country"), "enter", value);
	}
	
	public void email(String value) {
		textbox(MTDriver, By.id("req_Email"), "enter", value);
	}

	public void enterPCHemail(String value) {
		textbox(MTDriver, By.id("req_ExternalEmail"), "enter", value);
		textbox(MTDriver, By.id("req_PchEmail"), "enter", value);
	}
	
	public void ConfirmEmail(String value) {
		textbox(MTDriver, By.id("req_ConfirmEmail"), "enter", value);
	}

	public void password(String value) {
		textbox(MTDriver, By.id("req_Password"), "enter", value);
	}

	public void confirmPassword(String value) {
		textbox(MTDriver, By.id("req_ConfirmPassword"), "enter", value);
	}

	public void enterExternalID(String value) {
		textbox(MTDriver, By.id("req_ExternalId"), "enter", value);
	}
	

	public void testApi() {
		link(MTDriver, By.id("btnSubmitRequest"), "click");
		waitForPageToLoad(MTDriver);
	}
	
	public enum ResponseData {
		AuthTicket, ExternalTicket, AddressId, CarrierRoute, FirstName, LastName, Email, GlobalMemberToken, MatchCode
	}
	
	public String getTicketValue(ResponseData data) {
		response = MTDriver.findElement(By.xpath(".//*[@id='status-message']")).getText();
		//Upper part without JSON
		responseResult=response.split("Response: ")[0].split("\n");
		for (int i = 0; i < responseResult.length-1; i++) {
			if(responseResult[i].contains(data.toString())) {
				Log.info(data.toString()+" :- "+responseResult[i].split(":")[1]);
				return (responseResult[i].split(":")[1]);
			}
		}
		return null;
	}
	//=====================================================================
	public static String getResponseHeader(HttpResponse Response, String HeaderName) {
		
			Header[] headers = Response.getAllHeaders();
			for (Header header : headers) {
				System.out.println("Key : " + header.getName()
	                           + " ,Value : " + header.getValue());

			}
			System.out.println("\nGet Response Header By Key ...\n");
			HeaderName = Response.getFirstHeader(HeaderName).getValue();
		
			if (HeaderName == null) {
				System.out.println("Key "+ HeaderName +"is not found!");
			} else {
				System.out.println("HeaderValue - " + HeaderName);
			}
			System.out.println("\n Done");
			return HeaderName;
		
	}
	
	public String getResponseValue(ResponseData data, String... type) {
		response = MTDriver.findElement(By.xpath(".//*[@id='status-message']")).getText();
		responseJSON= response.split("Response: ")[1];
		try {
			JSONObject jsonObject = new JSONObject(responseJSON);
			if(type.length>0&&type[0].equals("int")){
				Log.info(data.toString() + " :- "+jsonObject.getJSONObject("MemberDetailStatusResponse").getJSONObject("MemberDetailResponse").getInt(data.toString()));
				Integer getValue =jsonObject.getJSONObject("MemberDetailStatusResponse").getJSONObject("MemberDetailResponse").getInt(data.toString());
				return getValue.toString();
			}
			Log.info(data.toString() + " :- "+jsonObject.getJSONObject("MemberDetailStatusResponse").getJSONObject("MemberDetailResponse").getString(data.toString()));
			return (jsonObject.getJSONObject("MemberDetailStatusResponse").getJSONObject("MemberDetailResponse").getString(data.toString()));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
	

	public void CreateFullRegUser() throws Exception {
		
		naviagateToCentralService();
		clickFullRegUser();
		firstName(First_Name);
		lastName(Last_Name);
		email(New_Email);
		ConfirmEmail(New_Email);
		testApi();
		waitForPageToLoad(MTDriver);
		Thread.sleep(4444);	
		Thread.sleep(4444);	
		New_Email = randomString(7, 8) + Date() + "@pchmail.com";
}
	
	public void CreateSilverRegUser() throws Exception {
		
		naviagateToCentralService();
		clickPreRegUser();
		email(New_Email);
		ConfirmEmail(New_Email);
		testApi();
		waitForPageToLoad(MTDriver);
		Thread.sleep(4444);	
		Thread.sleep(4444);	
		New_Email = randomString(7, 8) + Date() + "@pchmail.com";
}
	
	
	public void CreateMiniRegUser() throws Exception {
		
		naviagateToCentralService();
		clickMiniUser();
		email(New_Email);
		testApi();
		waitForPageToLoad(MTDriver);
		Thread.sleep(4444);	
		Thread.sleep(4444);	
		New_Email = randomString(7, 8) + Date() + "@pchmail.com";
}
	

	
	
	public void CreateFBRegUser() throws Exception {
		
		naviagateToCentralService();
		clickFBWithEmail();
		enterExternalID(ExternalID);
		enterPCHemail(ExternalNewEmail);
		testApi();
		waitForPageToLoad(MTDriver);
		Thread.sleep(4444);	
		Thread.sleep(4444);	
}
	
	@SuppressWarnings("unused")
	public String forgotPasswordAccessCode(String email) {
		Log.info("Getting Access Code for user " + email);
		HttpUtils.sendPOSTRequest(returnPropertyValue("RF_forgotPassword"),""
				+ "{\"Application\": {"
				+ "\"AppCode\": \"PCHCOM\","
				+ "\"PlatformCode\": \"PCHCOMHOME\"},"
				+ "\"Source\": {"
				+ "\"TrackingToken\": \"\","
				+ "\"SourceCode\": \"PCHCOM\","
				+ "\"ForeignSource\": \"\","
				+ "\"OriginatingUrl\": \"http://centralservices.qa.pch.com/RFApi_V8/Svc/TestPage.aspx\","
				+ "\"MediaChannel\": \"\","
				+ "\"EmailSubCode\":\"\"},"
				+ "\"IpAddress\": {"
				+ "\"ServerIp\": \"\","
				+ "\"ClientIp\": \"24.193.86.57\"},"
					+ "\"Client\": {"
					+ "\"UserAgentId\": \"975138\","
					+ "\"DeviceType\": \"DESKTOP\"},"
					+ "\"Email\": \""+email+"\","
					+ "\"ResetUrl\": \"http://www.pch.com\"}");
	
		URL url = null;
		try {
			url = new URL(returnPropertyValue("RF_forgotPasswordAccessToken")+email+"%22&eventCode=%22STRRESETPWD%22");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		response=HttpUtils.sendGETRequest(url);
		
		try {
			JSONObject jsonObject = new JSONObject(response);
			JSONArray jsonArray = jsonObject.getJSONArray("d");
			for (int i = 0; i < jsonArray.length(); i++) {
			    JSONObject jsonobject = jsonArray.getJSONObject(i);
				Log.info("Access Code "+(jsonobject.getString("Accesscode")));
			    return jsonobject.getString("Accesscode");
			}
		
		
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return email;
	}
	
	public void forgotPasswordExpireAccessCode(String email, String AccessCode) {
		Log.info("Expiring Access Code of forgot password for user " + email +"AccessCode "+AccessCode);
		URL url = null;
		try {
			url = new URL(returnPropertyValue("Central_Services")+"/ExpireAccessCode?email=%22"+email+"%22&accessCode=%22"+AccessCode+"%22&eventCode=%22STRRESETPWD%22");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		HttpUtils.sendGETRequest(url);
	}
	
	
	public String createFullRegUserAPI() {
		Log.info("Creating Full Reg User");
		response=HttpUtils.sendPOSTRequest(returnPropertyValue("RF_FullUser"),"{\"Application\": {\"AppCode\": \"LOTTO\",\"PlatformCode\": \"LOTTO\"},\"Source\": {\"TrackingToken\": \"\",\"SourceCode\": \"LOTTO\",\"ForeignSource\": \"\",\"OriginatingUrl\": \"http://centralservices.qa.pch.com/RFApi_V8/Svc/TestPage.aspx\",\"MediaChannel\": \"ACQ\",\"EmailSubCode\": \"\"},\"IpAddress\": {\"ServerIp\": \"\",\"ClientIp\": \"24.193.86.57\"},\"Client\": {\"UserAgentId\": \"975138\",\"DeviceType\": \"DESKTOP\"},\"SkipEmailValidation\": {\"Email\": \"\",\"ErrorCode\": null},\"Fields\": [{"
				+ "\"FieldCode\": \"11\","
				+ "\"FieldValue\": \"Mr.\"}, {"
				+ "\"FieldCode\": \"12\","
				+ "\"FieldValue\": \""+First_Name+"\" }, {"
				+ "\"FieldCode\": \"13\","
				+ "\"FieldValue\": \""+Last_Name+"\"}, {"
				+ "\"FieldCode\": \"14\","
				+ "\"FieldValue\": \"382 Channel Drive\"}, {"
				+ "\"FieldCode\": \"15\","
				+ "\"FieldValue\": \"\"}, {"
				+ "\"FieldCode\": \"16\","
				+ "\"FieldValue\": \"Port Washington\"}, {"
				+ "\"FieldCode\": \"17\","
				+ "\"FieldValue\": \"NY\"}, {"
				+ "\"FieldCode\": \"18\","
				+ "\"FieldValue\": \"11050\"}, {"
				+ "\"FieldCode\": \"19\","
				+ "\"FieldValue\": \"USA\"}, {"
				+ "\"FieldCode\": \"20\","
				+ "\"FieldValue\": \""+New_Email+"\"}, {"
				+ "\"FieldCode\": \"21\","
				+ "\"FieldValue\": \""+New_Email+"\"}, {"
				+ "\"FieldCode\": \"22\","
				+ "\"FieldValue\": \"pch123\"}, {"
				+ "\"FieldCode\": \"23\","
				+ "\"FieldValue\": \"pch123\"}, {"
				+ "\"FieldCode\": \"24\","
				+ "\"FieldValue\": \"04/13/1980\"}],"
				+ "\"SubscriptionData\": {\"SubscriptionCodes\": [\"LOTTO\"]},\"RegistrationValidationOption\": {\"AddressValidation\": true,\"DeliveryPointValidation\": true,\"EmailValidation\": true},\"ConfirmUrl\": \"http://www.lotto.pch.com\"}");
		
		System.out.println(response);
		New_Email = randomString(7, 8) + Date() + "@pchmail.com";
		First_Name = randomString(5, 6) + Date();
		Last_Name = randomString(5, 6) + Date();
		return response;
	}
	

	public String getResponseValueAPI(String response, ResponseData data) {
		try {
			JSONObject jsonObject = new JSONObject(response);
			Object obj = jsonObject.getJSONObject("MemberDetailStatusResponse").getJSONObject("MemberDetailResponse").get(data.toString());
			if(obj instanceof Integer) {
				Log.info(data.toString() + " :- " + obj);
				return obj.toString();
			}
			Log.info(data.toString() + " :- "+jsonObject.getJSONObject("MemberDetailStatusResponse").getJSONObject("MemberDetailResponse").get(data.toString()));
			return (jsonObject.getJSONObject("MemberDetailStatusResponse").getJSONObject("MemberDetailResponse").getString(data.toString()));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String createMiniRegUserAPI() {
		response=HttpUtils.sendPOSTRequest(returnPropertyValue("RF_MiniUser"),"{\"Application\": {\"AppCode\": \"LOTTOBLAST\",\"PlatformCode\": \"LOTTOBLAST\"},\"Source\": {\"TrackingToken\": \"\",\"SourceCode\": \"LOTTOBLAST\",\"ForeignSource\": \"\",\"OriginatingUrl\": \"http://centralservices.qa.pch.com/RFApi_V8/Svc/TestPage.aspx\",\"MediaChannel\": \"ACQ\",\"EmailSubCode\": \"\"},\"IpAddress\": {\"ServerIp\": \"10.1.1.246\",\"ClientIp\": \"24.193.86.57\"},"
				+ "\"Client\": {\"UserAgentId\": \"975138\",\"DeviceType\": \"DESKTOP\"},\"SkipEmailValidation\": {\"Email\": \"\",\"ErrorCode\": null},\"Fields\": ["
				+ "{"
				+ "\"FieldCode\": \"20\","
				+ "\"FieldValue\": \""+New_Email+"\""
				+ "},{\"FieldCode\": \"22\",\"FieldValue\": \"pch123\"}],"
				+ "\"RegistrationValidationOption\": {\"AddressValidation\": false,\"DeliveryPointValidation\": false,\"EmailValidation\": true}}");
		System.out.println(response);
		New_Email = randomString(7, 8) + Date() + "@pchmail.com";
		return response;
	}
	
	
	public String createSilverUserAPI() {
		Log.info("Creating Silver User");
		response=HttpUtils.sendPOSTRequest(returnPropertyValue("RF_SilveUser"),"{\"Application\": {\"AppCode\": \"LOTTO\",\"PlatformCode\": \"LOTTO\"},\"Source\": {\"TrackingToken\": \"\",\"SourceCode\": \"LOTTO\",\"ForeignSource\": \"\",\"OriginatingUrl\": \"http://centralservices.qa.pch.com/RFApi_V8/Svc/TestPage.aspx\",\"MediaChannel\": \"ACQ\",\"EmailSubCode\": \"\"},\"IpAddress\": {\"ServerIp\": \"\",\"ClientIp\": \"24.193.86.57\"},\"Client\": {\"UserAgentId\": \"975138\",\"DeviceType\": \"DESKTOP\"},\"SkipEmailValidation\": {\"Email\": \"\",\"ErrorCode\": null},\"Fields\": [{"
				+ "\"FieldCode\": \"11\","
				+ "\"FieldValue\": \"Mr.\"}, {"
				+ "\"FieldCode\": \"12\","
				+ "\"FieldValue\": \""+First_Name+"\" }, {"
				+ "\"FieldCode\": \"13\","
				+ "\"FieldValue\": \""+Last_Name+"\"}, {"
				+ "\"FieldCode\": \"14\","
				+ "\"FieldValue\": \"382 Channel Drive\"}, {"
				+ "\"FieldCode\": \"15\","
				+ "\"FieldValue\": \"\"}, {"
				+ "\"FieldCode\": \"16\","
				+ "\"FieldValue\": \"Port Washington\"}, {"
				+ "\"FieldCode\": \"17\","
				+ "\"FieldValue\": \"NY\"}, {"
				+ "\"FieldCode\": \"18\","
				+ "\"FieldValue\": \"11050\"}, {"
				+ "\"FieldCode\": \"19\","
				+ "\"FieldValue\": \"USA\"}, {"
				+ "\"FieldCode\": \"20\","
				+ "\"FieldValue\": \""+New_Email+"\"}, {"
				+ "\"FieldCode\": \"21\","
				+ "\"FieldValue\": \""+New_Email+"\"}, {"
				+ "\"FieldCode\": \"24\","
				+ "\"FieldValue\": \"04/13/1980\"}],"
				+ "\"SubscriptionData\": {\"SubscriptionCodes\": [\"LOTTO\"]},\"RegistrationValidationOption\": {\"AddressValidation\": true,\"DeliveryPointValidation\": true,\"EmailValidation\": true},\"ConfirmUrl\": \"http://www.appsupport.pch.com\"}");
		
		System.out.println(response);
		New_Email = randomString(7, 8) + Date() + "@pchmail.com";
		First_Name = randomString(5, 6) + Date();
		Last_Name = randomString(5, 6) + Date();
		return response;
		
	}
	
	public String createFBUserAPI() {
		Log.info("Creating FB User");
		response=HttpUtils.sendPOSTRequest(returnPropertyValue("RF_FBUser"),"{\"Application\":{\"AppCode\":\"LOTTOBLAST\",\"PlatformCode\":\"LOTTOBLAST\"},\"Source\":{\"TrackingToken\":\"\",\"SourceCode\":\"LOTTOBLAST\",\"ForeignSource\":\"\",\"OriginatingUrl\":\"http://centralservices.qa.pch.com/RFApi_V8/Svc/TestPage.aspx\",\"MediaChannel\":\"ACQ\",\"EmailSubCode\":\"\"},\"IpAddress\":{\"ServerIp\":\"10.1.1.246\",\"ClientIp\":\"24.193.86.57\"},\"Client\":{\"UserAgentId\":\"975138\",\"DeviceType\":\"DESKTOP\"},\"SkipEmailValidation\":{\"Email\":\"\",\"ErrorCode\":null},\"Fields\":[],\"ExternalId\":\"272823859478648\","
				+ "\"ExternalEmail\":\""+New_Email+"\","
				+ "\"PchEmail\":\""+New_Email+"\"}");
		
		System.out.println(response);
		New_Email = randomString(7, 8) + Date() + "@pchmail.com";
		return response;
		
	}
	
}