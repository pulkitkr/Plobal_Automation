package com.android.PlobalPages;

import org.openqa.selenium.By;

public class CleverTapPage {

	//Email Field
	public static By objEmailField = By.xpath("//input[@id='account_email']");
	
	//Continue with Email Button
	public static By objContinueWithEmailBtn = By.xpath("//button[@type='submit']");
	
	//Password Field
	public static By objPasswordField = By.xpath("//input[@id='account_password']");
	
	//Login Button
	public static By objLoginBtn = By.xpath("//button[contains(text(),'Log in')]");
	
	//Stage mobile app plobal
	public static By objStageMobileAppPlobal = By.xpath("//span[text()='[Stage] Mobile App - Plobal']");
	
	//Advance Button
	public static By objAdvanceBtn = By.xpath("/html/body/div[1]/div[1]/section/div/div/div/div/div[1]/div/a[2]/button");
	
	//frame
	public static By objFrame = By.xpath("//iframe[@title='[Stage] Mobile App - Plobal']");
	
	//Segments tab
	public static By objSegmentsTab = By.xpath("//div[contains(text(),'Segments')]");
	
	//By identity
	public static By objItentity = By.xpath("//input[@placeholder='Email/Identity/CleverTap ID']");
	
	//Identity find button
	public static By objFindBtn = By.xpath("//a[@class='samplemode white_button MT10']");
	
	//Clever tap id
	public static By objUserId = By.xpath("//span[@id='more-identy']");
	
	//Activity Tab
	public static By objActivityTab = By.xpath("//li[contains(text(),'Activity')]");
	
	//email scroll
	public static By objEmailScroll = By.xpath("(//span[text()='User Login'])[1]");
	
	//Email event clevertap
	public static By objEmailCleverTapEvent = By.xpath("/html/body/div[7]/div[3]/div/div/div[12]/div/div[2]/div[1]/div[3]/div/div[1]/div[1]/div/table/tbody/tr/td[2]/span[2]/span[2]");
	
	//product viewed
	public static By objProductViewed = By.xpath("(//span[text()='Product Viewed'])[1]");
	
	//product name
	public static By objProductName = By.xpath("((//span[text()='Product Viewed'])[1]/following-sibling::span/descendant::span)[2]");
	
	//Product price
	public static By objProductPrice = By.xpath("(//span[text()='Product Viewed']/following-sibling::span/descendant::span[@title='Price'])[1]");
}
