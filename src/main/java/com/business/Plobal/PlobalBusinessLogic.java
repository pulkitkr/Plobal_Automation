package com.business.Plobal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.AssertJUnit.assertNotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

import com.Datasheet.RingPay_TestData_DataProvider;
import com.android.PlobalPages.CheckOutPage;
import com.android.PlobalPages.PlobalLoginPage;
import com.android.PlobalPages.ProductPage;
import com.driverInstance.CommandBase;
import com.extent.ExtentReporter;
import com.propertyfilereader.PropertyFileReader;
import com.utility.LoggingUtils;
import com.utility.Utilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;
import io.restassured.module.jsv.JsonSchemaValidator;
//import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

public class PlobalBusinessLogic extends Utilities {

	public PlobalBusinessLogic(String Application) throws InterruptedException {
		new CommandBase(Application);
		init();
	}

	RingPay_TestData_DataProvider dataProvider = new RingPay_TestData_DataProvider();
	private int timeout;
	SoftAssert softAssertion = new SoftAssert();
	boolean launch = "" != null;
	/** Retry Count */
	private int retryCount;
	ExtentReporter extent = new ExtentReporter();

	/** The Constant logger. */
//		final static Logger logger = Logger.getLogger("rootLogger");
	static LoggingUtils logger = new LoggingUtils();

	/** The Android driver. */
	public AndroidDriver<AndroidElement> androidDriver;

	public static boolean relaunchFlag = false;
	public static boolean appliTools = false;

	public static boolean PopUp = false;

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public int getRetryCount() {
		return retryCount;
	}

	public void setRetryCount(int retryCount) {
		this.retryCount = retryCount;

	}

	/**
	 * Initiate Property File.
	 *
	 * @param byLocator the by locator
	 */

	public void init() {

		PropertyFileReader handler = new PropertyFileReader("properties/Execution.properties");
		setTimeout(Integer.parseInt(handler.getproperty("TIMEOUT")));
		setRetryCount(Integer.parseInt(handler.getproperty("RETRY_COUNT")));
		logger.info(
				"Loaded the following properties" + " TimeOut :" + getTimeout() + " RetryCount :" + getRetryCount());
	}

	public void TearDown() {
		getDriver().quit();
	}

	public void plobalWelcome() throws Exception {
		extent.HeaderChildNode("Welcome To Plobal App");

		explicitWaitVisibility(PlobalLoginPage.objWelcomeHeader, 20);
		verifyElementPresentAndClick(PlobalLoginPage.objYesBtn, "Yes Button");
		String welcomeFamilyText = getText(PlobalLoginPage.objWelcomeToFamilyText);
		verifyIsElementDisplayed(PlobalLoginPage.objWelcomeToFamilyText, welcomeFamilyText);
		click(PlobalLoginPage.objDoneBtn, "Done Button");

		verifyElementPresent(PlobalLoginPage.objHomePageLogo, "ESCAWAY Logo");
		logger.info("Navigated to HomePage");
		extent.extentLogger("Home", "Navigated to HomePage");
	}

	public void loginToApp() throws Exception {
		extent.HeaderChildNode("Login to App");
		explicitWaitVisibility(PlobalLoginPage.objProfileTab, 20);
		verifyElementPresentAndClick(PlobalLoginPage.objProfileTab, "Profile Tab");

		click(PlobalLoginPage.objLoginBtn, "Login Button");
		Aclick(PlobalLoginPage.objEmailTextField, "Email Text Field");
		type(PlobalLoginPage.objEmailTextField, "abc@gmail.com", "Email Text Field");
		String wrongUserName = getText(PlobalLoginPage.objEmailTextField);
		Aclick(PlobalLoginPage.objPasswordTextFied, "Password Text Field");
		type(PlobalLoginPage.objPasswordTextFied, "1UB7p90IVxlFRmBv2ig8ag", "Password Text Field");
		Aclick(PlobalLoginPage.objLoginButton, "Login Button");

		String toastMsg = getText(PlobalLoginPage.objToastMsg);

		logger.info(toastMsg + " Toast Message is displayed");
		extent.extentLogger("Toast Message", toastMsg + " Toast Message is displayed");

		if (verifyIsElementDisplayed(PlobalLoginPage.objLoginPageTitle, "Login Page")) {

			Aclick(PlobalLoginPage.objEmailTextField, "Email Text Field");
			clearField(PlobalLoginPage.objEmailTextField, "Email Text Field");
			type(PlobalLoginPage.objEmailTextField, "test@mailnesia.com", "Email Text Field");
			String userName = getText(PlobalLoginPage.objEmailTextField);
			Aclick(PlobalLoginPage.objPasswordTextFied, "Password Text Field");
			clearField(PlobalLoginPage.objPasswordTextFied, "Password Text Field");
			type(PlobalLoginPage.objPasswordTextFied, "1UB7p90IVxlFRmBv2ig8ag==", "Password Text Field");
			Aclick(PlobalLoginPage.objLoginButton, "Login Button");
			verifyIsElementDisplayed(PlobalLoginPage.objusercreated("test@mailnesia.com"),
					"User Logged in by " + userName + "");
			Aclick(PlobalLoginPage.objHomePageTab, "Home Page Tab");

			logger.info("Navigated Search Product Page");
			extent.extentLoggerPass("SearchProduct", "Navigated Search Product Page");
		} else {
			logger.info("Failed to Navigate Search Product Page");
			extent.extentLoggerFail("SearchProduct", "Failed to Navigate Search Product Page");
		}
	}

	public void productItem(String size) throws Exception {
		extent.HeaderChildNode("Product");

		explicitWaitVisibility(PlobalLoginPage.objHomePageLogo, 20);
		Swipe("UP", 3);
		String productText = getText(PlobalLoginPage.objCollectionProduct);
		WebElement productElement = getDriver().findElement(PlobalLoginPage.objCollectionProduct);
		verifyElementExist1(productElement, productText);
		verifyElementPresentAndClick(PlobalLoginPage.objCollectionFirstProduct, "Product Image");
		if (getText(ProductPage.objOpenProductText).equals(getText(ProductPage.objSelectedProductText))) {
			softAssertion.assertEquals(getText(ProductPage.objOpenProductText),
					getText(ProductPage.objSelectedProductText));

			logger.info(getText(ProductPage.objSelectedProductText) + " product Text is displayed");
			extent.extentLogger("Product Name",
					getText(ProductPage.objSelectedProductText) + " product Text is displayed");

			verifyIsElementDisplayed(ProductPage.objShareImage, "Share Button");
			verifyIsElementDisplayed(ProductPage.objWishlistImage, "Wish Button");

			if (verifyIsElementDisplayed(ProductPage.objOutofStock,
					getText(ProductPage.objOutofStock))) {
				click(ProductPage.objSize, "Select Size Dropdown box");
				verifyElementPresentAndClick(ProductPage.objuserSize(size), "Size selected");
			}

			verifyIsElementDisplayed(ProductPage.objAddToCartBtn, "Add to Cart Button");
			verifyIsElementDisplayed(ProductPage.objBuyNowBtn, "Buy Now Button");
			if (verifyElementDisplayed(ProductPage.objAddToCartBtn) && verifyElementDisplayed(ProductPage.objBuyNowBtn)
					&& verifyElementDisplayed(ProductPage.objShareImage)
					&& verifyElementDisplayed(ProductPage.objWishlistImage)) {
				click(ProductPage.objBuyNowBtn, "BUY NOW");
			}
		} else {
			logger.info(getText(ProductPage.objSelectedProductText) + " is different from "
					+ getText(ProductPage.objOpenProductText));
			extent.extentLoggerFail("Product Text", getText(ProductPage.objSelectedProductText) + " is different from "
					+ getText(ProductPage.objOpenProductText));
		}
		softAssertion.assertAll();
	}

	public void paymentOptions() throws Exception {
		extent.HeaderChildNode("Payment option");

		explicitWaitVisibility(ProductPage.objPaymentOptionHeader, 10);
		verifyIsElementDisplayed(ProductPage.objPaymentOptionHeader, "Payment Option tab");
		verifyElementPresentAndClick(ProductPage.objPaymentOptionSelect, getText(ProductPage.objPaymentOptionSelect));
		logger.info("Navigating to CheckOut Page");
		extent.extentLoggerPass("Payment option", "Navigating to CheckOut Page");
	}

	public void paymentCheckOut(String address) throws Exception {
		extent.HeaderChildNode("CheckOut");

		String checkoutHeader = getText(CheckOutPage.objCheckOutHeader);
		verifyIsElementDisplayed(CheckOutPage.objCheckOutHeader, checkoutHeader);

		switch (address) {
		case "Remove":
			if (verifyIsElementDisplayed(CheckOutPage.objDeliveryAddressPlusBtn)) {
				click(CheckOutPage.objDeliveryAddressPlusBtn, "Plus Button");
				verifyIsElementDisplayed(CheckOutPage.objAddAddress);
				type(CheckOutPage.objFirstName, "Subbu", "First Name");
				click(CheckOutPage.objLastName, "Last Name");
				type(CheckOutPage.objLastName, "Vr", "Last Name");
				click(CheckOutPage.objContactNo, "Contact Number");
				type(CheckOutPage.objContactNo, "7070707070", "Contact Number");
				click(CheckOutPage.objAddress, "Address");
				type(CheckOutPage.objAddress, "Test", "Address");
				Back(1);
				click(CheckOutPage.objCountry, "Country Drop down box");
				scrollToElement("text", "India").click();
				click(CheckOutPage.objState, "State");
				type(CheckOutPage.objState, "Karnataka", "State");
				Back(1);
				click(CheckOutPage.objCity, "City");
				type(CheckOutPage.objCity, "Bangalore", "City");
				Back(1);
				click(CheckOutPage.objZipCode, "Zip Code");
				type(CheckOutPage.objZipCode, "560078", "Zip Code");
				Back(1);
				click(CheckOutPage.objSaveBtn, "Save button");
				verifyElementDisplayed(CheckOutPage.objMessage);
				verifyElementPresentAndClick(CheckOutPage.objOkBtn, "OK Button");
			} else {
				verifyElementPresentAndClick(CheckOutPage.objDeliveryAddresseditBtn, "Edit address");
				verifyElementPresentAndClick(CheckOutPage.objRemoveAdd, "Remove Address Button");
				verifyIsElementDisplayed(CheckOutPage.objRemoveAddressMsg,
						"Are you sure you want to remove this address?");
				verifyElementPresentAndClick(CheckOutPage.objRemoveAddressYesBtn, "Yes Button");
				click(CheckOutPage.objDeliveryAddressPlusBtn, "Plus Button");
				verifyIsElementDisplayed(CheckOutPage.objAddAddress);
				type(CheckOutPage.objFirstName, "Subbu", "First Name");
				click(CheckOutPage.objLastName, "Last Name");
				type(CheckOutPage.objLastName, "Vr", "Last Name");
				click(CheckOutPage.objContactNo, "Contact Number");
				type(CheckOutPage.objContactNo, "7070707070", "Contact Number");
				click(CheckOutPage.objAddress, "Address");
				type(CheckOutPage.objAddress, "Test", "Address");
				Back(1);
				click(CheckOutPage.objCountry, "Country Drop down box");
				scrollToElement("text", "India").click();
				click(CheckOutPage.objState, "State");
				type(CheckOutPage.objState, "Karnataka", "State");
				Back(1);
				click(CheckOutPage.objCity, "City");
				type(CheckOutPage.objCity, "Bangalore", "City");
				Back(1);
				click(CheckOutPage.objZipCode, "Zip Code");
				type(CheckOutPage.objZipCode, "560078", "Zip Code");
				Back(1);
				click(CheckOutPage.objSaveBtn, "Save button");
				verifyElementDisplayed(CheckOutPage.objMessage);
				verifyElementPresentAndClick(CheckOutPage.objOkBtn, "OK Button");
			}
			break;

		case "Edit":
			if (verifyIsElementDisplayed(CheckOutPage.objDeliveryAddressPlusBtn)) {
				click(CheckOutPage.objDeliveryAddressPlusBtn, "Plus Button");
				verifyIsElementDisplayed(CheckOutPage.objAddAddress);
				type(CheckOutPage.objFirstName, "Subbu", "First Name");
				click(CheckOutPage.objLastName, "Last Name");
				type(CheckOutPage.objLastName, "Vr", "Last Name");
				click(CheckOutPage.objContactNo, "Contact Number");
				type(CheckOutPage.objContactNo, "7070707070", "Contact Number");
				click(CheckOutPage.objAddress, "Address");
				type(CheckOutPage.objAddress, "Test", "Address");
				Back(1);
				click(CheckOutPage.objCountry, "Country Drop down box");
				scrollToElement("text", "India").click();
				click(CheckOutPage.objState, "State");
				type(CheckOutPage.objState, "Karnataka", "State");
				Back(1);
				click(CheckOutPage.objCity, "City");
				type(CheckOutPage.objCity, "Bangalore", "City");
				Back(1);
				click(CheckOutPage.objZipCode, "Zip Code");
				type(CheckOutPage.objZipCode, "560078", "Zip Code");
				Back(1);
				click(CheckOutPage.objSaveBtn, "Save button");
				verifyElementDisplayed(CheckOutPage.objMessage);
				verifyElementPresentAndClick(CheckOutPage.objOkBtn, "OK Button");
			} else {
				verifyElementPresentAndClick(CheckOutPage.objDeliveryAddresseditBtn, "Edit address");
				verifyElementPresentAndClick(CheckOutPage.objChooseAddressEditBtn, "Edit address");
				clearField(CheckOutPage.objFirstName, "First Name Field");
				click(CheckOutPage.objFirstName, "First Name Field");
				type(CheckOutPage.objFirstName, "PK", "First Name");
//				click(CheckOutPage.objLastName, "Last Name");
//				type(CheckOutPage.objLastName, "Vr", "Last Name");
//				click(CheckOutPage.objContactNo, "Contact Number");
//				type(CheckOutPage.objContactNo, "7070707070", "Contact Number");
//				click(CheckOutPage.objAddress, "Address");
//				type(CheckOutPage.objAddress, "Test", "Address");
//				Back(1);
//				click(CheckOutPage.objCountry, "Country Drop down box");
//				scrollToElement("text", "India").click();
//				click(CheckOutPage.objState, "State");
//				type(CheckOutPage.objState, "Karnataka", "State");
//				Back(1);
//				click(CheckOutPage.objCity, "City");
//				type(CheckOutPage.objCity, "Bangalore", "City");
//				Back(1);
//				click(CheckOutPage.objZipCode, "Zip Code");
//				type(CheckOutPage.objZipCode, "560078", "Zip Code");
				Back(1);
				click(CheckOutPage.objSaveBtn, "Save button");
				verifyElementDisplayed(CheckOutPage.objMessage);
				verifyElementPresentAndClick(CheckOutPage.objOkBtn, "OK Button");
			}
			break;

		case "Confirm_Address":

			verifyElementPresentAndClick(CheckOutPage.objDeliveryAddresseditBtn, "Edit address");
			verifyElementDisplayed(CheckOutPage.objChooseAddress);
			verifyElementPresentAndClick(CheckOutPage.objConfirmBtn, "Confirm Button");

			break;

		default:
			logger.info("invalid Address!!");
			extent.extentLogger("Address", "invalid Address!!");
			break;
		}

		click(CheckOutPage.objPaymentPlusBtn, "Add Payment Button");
		verifyIsElementDisplayed(CheckOutPage.objCreditCardHeader, "Credit Card Title");
		click(CheckOutPage.objCreditCardNumber, "Credit Card Number");
		type(CheckOutPage.objCreditCardNumber, "1", "Credit Card Number");
		Back(1);
		click(CheckOutPage.objCreditCardFirstName, "Credit Card First name");
		type(CheckOutPage.objCreditCardFirstName, "Subbu", "Credit Card First name");
		click(CheckOutPage.objCreditCardLastName, "Credit Card Last name");
		type(CheckOutPage.objCreditCardLastName, "Vr", "Credit Card Last name");
		Back(1);
		click(CheckOutPage.objCreditCardExpiryDate, "Credit Card Expiry Date");

		type(CheckOutPage.objCreditCardExpiryDate, "11/45", "Credit Card Expiry Date");
		if (verifyElementPresent(CheckOutPage.objInvalidCardDetailsMsg,
				getText(CheckOutPage.objInvalidCardDetailsMsg))) {
			clearField(CheckOutPage.objCreditCardExpiryDate, "Credit Card Expiry Date");
			type(CheckOutPage.objCreditCardExpiryDate, "11/22", "Credit Card Expiry Date");
		}

		click(CheckOutPage.objCreditCardCVV, "Credit Card CVV");
		type(CheckOutPage.objCreditCardCVV, "123", "Credit Card CVV");
		Back(1);
		verifyElementPresentAndClick(CheckOutPage.objCreditCardAddpaymentBtn, "Add Payment Button");
		String itemCost = getText(CheckOutPage.objItemCost);
		scrollToElement("text", "Order Details");
		String totalAmt = getText(CheckOutPage.objTotalAmt);
		softAssertion.assertEquals(itemCost, totalAmt);
		click(CheckOutPage.objPlaceOrderBtn, "Place Order Button");
		verifyElementPresent(CheckOutPage.objThankYouTitle, "Thank You Page");
		logger.info(getText(CheckOutPage.objThankYouBagText) + " " + getText(CheckOutPage.objOrderId));
		extent.extentLogger("Order id",
				getText(CheckOutPage.objThankYouBagText) + " " + getText(CheckOutPage.objOrderId));
		verifyElementPresentAndClick(CheckOutPage.objContinueShoppingBtn, "Continue Shopping Button");
		verifyElementPresent(PlobalLoginPage.objHomePageLogo, "ESCAWAY Logo");
		logger.info("Navigated to HomePage");
		extent.extentLogger("Home", "Navigated to HomePage");
		softAssertion.assertAll();
	}

	public void searchProduct(String address) throws Exception {
		extent.HeaderChildNode("Search Product and Place order scenario");
		Aclick(PlobalLoginPage.objSearchProduct, "Search Product Tab");
		explicitWaitVisibility(PlobalLoginPage.objAllowPopup, 10);
		String popUpText = getText(PlobalLoginPage.objAllowPopup);
		if (verifyIsElementDisplayed(PlobalLoginPage.objAllowPopup, popUpText)) {
			Aclick(PlobalLoginPage.objAllowBtn, "Allow Button");
		}
		int count = 1;
		for (int i = 0; i < count; i++) {
			click(PlobalLoginPage.objSearchBar, "Search Bar");
			waitTime(2000);
			if (verifyElementPresent(PlobalLoginPage.objSearchBar, "Search Bar")) {
				count++;
				break;
			}
		}
		waitTime(4000);
		click(PlobalLoginPage.objSearchBar, "Search Bar");
		type(PlobalLoginPage.objSearchBar, "gold stone ring", "Search Bar");
		explicitWaitVisibility(PlobalLoginPage.objProductName, 10);
		waitTime(5000);
		String prouctName = getText(PlobalLoginPage.objProductName);
		if (verifyIsElementDisplayed(PlobalLoginPage.objProductName, prouctName)) {
			click(PlobalLoginPage.objProductName, prouctName);
		}
		explicitWaitVisibility(PlobalLoginPage.objShareImage, 10);
		verifyIsElementDisplayed(PlobalLoginPage.objShareImage, "Share Button");
		verifyIsElementDisplayed(PlobalLoginPage.objWishlistImage, "Wish Button");
		verifyIsElementDisplayed(PlobalLoginPage.objAddToCartBtn, "Add to Cart Button");
		verifyIsElementDisplayed(PlobalLoginPage.objBuyNowBtn, "Buy Now Button");
		if (verifyElementDisplayed(PlobalLoginPage.objAddToCartBtn)
				&& verifyElementDisplayed(PlobalLoginPage.objBuyNowBtn)
				&& verifyElementDisplayed(PlobalLoginPage.objShareImage)
				&& verifyElementDisplayed(PlobalLoginPage.objWishlistImage)) {
			click(PlobalLoginPage.objBuyNowBtn, "BUY NOW");
		}
		paymentOptions();
		paymentCheckOut(address);
	}

	public void logOut() throws Exception {
		extent.HeaderChildNode("Logout");
		explicitWaitVisibility(PlobalLoginPage.objLogoutProfileTab, 20);
		verifyElementPresentAndClick(PlobalLoginPage.objLogoutProfileTab, "Profile Tab");
		Swipe("UP", 3);
		click(PlobalLoginPage.objLogOut, "Logout Button");
		verifyElementPresent(PlobalLoginPage.objLogOutMsg, getText(PlobalLoginPage.objLogOutMsg));
		verifyElementPresentAndClick(PlobalLoginPage.objLogOutOkBtn, "OK Button");
	}
}
