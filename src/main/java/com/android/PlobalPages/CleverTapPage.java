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
	public static By objEmailCleverTapEvent = By.xpath("(//span[text()='User Login']/following-sibling::span/descendant::span)[2]");
	
	//product viewed
	public static By objProductViewed = By.xpath("(//span[text()='Product Viewed'])[1]");
	
	//product name
	public static By objProductName = By.xpath("(//span[text()='Product Viewed'])[3]/following-sibling::span/descendant::span[2]");
	
	//Product price
	public static By objProductPrice = By.xpath("((//span[text()='Product Viewed'])[3]/following-sibling::span/descendant::span[@title='Price'])[1]");
	
	//Cart product title
	public static By objCartProductTitle = By.xpath("(//span[text()='Added to Cart'])[1]");
	
	//product title
	public static By objProductTitle = By.xpath("(//span[text()='Added to Cart']/following-sibling::span/descendant::span[@title='Product Title'])[1]");
	
	//Cart product variant name
	public static By objCartProductvariantName = By.xpath("(//span[text()='Added to Cart']/following-sibling::span/descendant::span[@title='Variant Name'])[1]");
	
	//Cart product price
	public static By objCartProductPrice = By.xpath("(//span[text()='Added to Cart']/following-sibling::span/descendant::span[@title='Price'])[1]");
	
	//order placed payment pending event name
	public static By objPaymentPending = By.xpath("(//span[text()='Order Placed Payment Pending'])[1]");
	
	//order placed payment pending total product
	public static By objPaymentPendingTotalItem = By.xpath("(//span[text()='Order Placed Payment Pending']/following-sibling::span/descendant::span)[1]");
	
	//order placed payment pending cart amount
	public static By objPaymentPendingCartAmt = By.xpath("((//span[text()='Order Placed Payment Pending'])[3]/following-sibling::span/descendant::span)[3]");
	
	//Select Address
	public static By objSelectAddress = By.xpath("(//span[text()='Select Address'])[3]");
	
	//select address name
	public static By objAddressName = By.xpath("((//span[text()='Select Address'])[5]/following-sibling::span/descendant::span)[1]");
	
	//select address 
	public static By objSelect_Address = By.xpath("(//span[text()='Select Address']/following-sibling::span/descendant::span)[2]");
	
	//select address city
	public static By objAddressCity = By.xpath("(//span[text()='Select Address']/following-sibling::span/descendant::span)[3]");
	
	//select address State
	public static By objAddressState = By.xpath("(//span[text()='Select Address']/following-sibling::span/descendant::span)[4]");
	
	//select address Country
	public static By objAddressCountry = By.xpath("(//span[text()='Select Address']/following-sibling::span/descendant::span)[5]");
	
	//select address Zip code
	public static By objAddressZipCode = By.xpath("(//span[text()='Select Address']/following-sibling::span/descendant::span)[6]");
	
	//select address Mobileno
	public static By objAddressMobileNo = By.xpath("(//span[text()='Select Address']/following-sibling::span/descendant::span)[7]");
	
	//Order Charged Title
	public static By objChargedTitle = By.xpath("(//span[contains(text(),'Charged')])[1]");
	
	//Order Charged ID
	public static By objChargedID = By.xpath("(//span[contains(text(),'Charged')]/following-sibling::span/descendant::span)[2]");
	
	//Order Charged payment mode
	public static By objChargedPaymentMode = By.xpath("(//span[contains(text(),'Charged')]/following-sibling::span/descendant::span)[4]");
	
	//Order Charged price
	public static By objChargedPrice = By.xpath("(//span[contains(text(),'Charged')]/following-sibling::span/descendant::span)[12]");
	
	//My order
	public static By objMyOrderTitle = By.xpath("//*[@resource-id='plobaltestshutterstock.android.staging:id/btn_continue_shopping']");
	
	//User Logout
	public static By objUserLogoutitle = By.xpath("(//span[text()='User Logout'])[1]");
	
	//User Name
	public static By objUsername = By.xpath("(//span[text()='User Logout']/following-sibling::span/descendant::span)[1]");
	
	//User Email
	public static By objUserEmail = By.xpath("(//span[text()='User Logout']/following-sibling::span/descendant::span)[2]");
}
