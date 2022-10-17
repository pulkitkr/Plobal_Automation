package com.business.Plobal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.AssertJUnit.assertNotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

import com.Datasheet.RingPay_TestData_DataProvider;
import com.android.PlobalPages.CheckOutPage;
import com.android.PlobalPages.CleverTapPage;
import com.android.PlobalPages.PlobalLoginPage;
import com.android.PlobalPages.ProductPage;
import com.driverInstance.CommandBase;
import com.extent.ExtentReporter;
import com.helger.commons.compare.CompareHelper;
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

	private String productName;

	private int timeout;
	SoftAssert softAssertion = new SoftAssert();
	boolean launch = "" != null;
	/** Retry Count */
	private int retryCount;
	ExtentReporter extent = new ExtentReporter();

	/** LinkedHashMap */
	LinkedHashMap<String, String> actualResult = new LinkedHashMap<String, String>();
	LinkedHashMap<String, String> expectedResult = new LinkedHashMap<String, String>();

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

	public void loginToApp(String invalidEmailId, String invalidPassword, String validEmailId, String validPassword)
			throws Exception {
		extent.HeaderChildNode("Login to App");

		actualResult.put("event", "Login");
		explicitWaitVisibility(PlobalLoginPage.objProfileTab, 20);
		verifyElementPresentAndClick(PlobalLoginPage.objProfileTab, "Profile Tab");

		click(PlobalLoginPage.objLoginBtn, "Login Button");
		Aclick(PlobalLoginPage.objEmailTextField, "Email Text Field");
		type(PlobalLoginPage.objEmailTextField, invalidEmailId, "Email Text Field");

		String wrongUserName = getText(PlobalLoginPage.objEmailTextField);
		Aclick(PlobalLoginPage.objPasswordTextFied, "Password Text Field");
		type(PlobalLoginPage.objPasswordTextFied, invalidPassword, "Password Text Field");
		Aclick(PlobalLoginPage.objLoginButton, "Login Button");

		String toastMsg = getText(PlobalLoginPage.objToastMsg);

		logger.info(toastMsg + " Toast Message is displayed");
		extent.extentLogger("Toast Message", toastMsg + " Toast Message is displayed");

		if (verifyIsElementDisplayed(PlobalLoginPage.objLoginPageTitle, "Login Page")) {

			Aclick(PlobalLoginPage.objEmailTextField, "Email Text Field");
			clearField(PlobalLoginPage.objEmailTextField, "Email Text Field");
			type(PlobalLoginPage.objEmailTextField, validEmailId, "Email Text Field");

			waitTime(3000);
			actualResult.put("Email", getText(PlobalLoginPage.objEmailTextField));

			String userName = getText(PlobalLoginPage.objEmailTextField);
			Aclick(PlobalLoginPage.objPasswordTextFied, "Password Text Field");
			clearField(PlobalLoginPage.objPasswordTextFied, "Password Text Field");
			type(PlobalLoginPage.objPasswordTextFied, validPassword, "Password Text Field");
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
		actualResult.put("Product Viewed", "Product Viewed");
		Swipe("UP", 3);
		String productText = getText(PlobalLoginPage.objCollectionProduct);
		WebElement productElement = getDriver().findElement(PlobalLoginPage.objCollectionProduct);

		verifyElementExist1(productElement, productText);
		verifyElementPresentAndClick(PlobalLoginPage.objCollectionFirstProduct, "Product Image");
		if (getText(ProductPage.objOpenProductText).equals(getText(ProductPage.objSelectedProductText))) {

			actualResult.put("Product", getText(ProductPage.objOpenProductText));
			actualResult.put("price", getText(ProductPage.objProductPrice));

			productName = getText(ProductPage.objSelectedProductText);
			softAssertion.assertEquals(getText(ProductPage.objOpenProductText),
					getText(ProductPage.objSelectedProductText));

			logger.info(getText(ProductPage.objSelectedProductText) + " product Text is displayed");
			extent.extentLogger("Product Name",
					getText(ProductPage.objSelectedProductText) + " product Text is displayed");

			verifyIsElementDisplayed(ProductPage.objShareImage, "Share Button");
			verifyIsElementDisplayed(ProductPage.objWishlistImage, "Wish Button");

			if (verifyIsElementDisplayed(ProductPage.objOutofStock, getText(ProductPage.objOutofStock))) {
				click(ProductPage.objSize, "Select Size Dropdown box");
				verifyElementPresentAndClick(ProductPage.objuserSize(size), "Size selected");
			}

			verifyIsElementDisplayed(ProductPage.objAddToCartBtn, "Add to Cart Button");
			verifyIsElementDisplayed(ProductPage.objBuyNowBtn, "Buy Now Button");
			if (verifyElementDisplayed(ProductPage.objAddToCartBtn) && verifyElementDisplayed(ProductPage.objBuyNowBtn)
					&& verifyElementDisplayed(ProductPage.objShareImage)
					&& verifyElementDisplayed(ProductPage.objWishlistImage)) {
				click(ProductPage.objAddToCartBtn, "ADD TO CART");

				explicitWaitVisibility(ProductPage.objProductBag, 10);
				verifyElementPresentAndClick(ProductPage.objProductBag, "Cart");

				actualResult.put("addtocart", "Added to Cart");
				actualResult.put("product title", getText(ProductPage.objProductCartTitle));
				String variantText = getText(ProductPage.objProductCartVariant);
				variantText = variantText.substring(0, variantText.length() - 1);
				actualResult.put("Variant Name", variantText);
				actualResult.put("Price", getText(ProductPage.objProductCartPrice));
				waitTime(5000);
				verifyElementPresentAndClick(CheckOutPage.objplaceOrderBtn, "Place Order Button");
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

		actualResult.put("payment Pending", "Order Placed Payment Pending");
		String itemCount = getText(ProductPage.objTotalProduct);
		itemCount = itemCount.substring(0, 1);
		actualResult.put("total item", itemCount);

		Swipe("up", 2);
		String amt = getText(ProductPage.objTotalAmt);
		actualResult.put("cart amount", amt);

		logger.info("Navigating to CheckOut Page");
		extent.extentLoggerPass("Payment option", "Navigating to CheckOut Page");
	}

	public void paymentCheckOut(String address) throws Exception {
		extent.HeaderChildNode("CheckOut");

		Swipe("down", 2);
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
				waitTime(5000);
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
				waitTime(5000);
				verifyElementPresentAndClick(CheckOutPage.objDeliveryAddresseditBtn, "Edit address");
				verifyElementPresentAndClick(CheckOutPage.objChooseAddressEditBtn, "Edit address");
				clearField(CheckOutPage.objFirstName, "First Name Field");
				click(CheckOutPage.objFirstName, "First Name Field");
				type(CheckOutPage.objFirstName, "Subbu", "First Name");
				Back(1);
				click(CheckOutPage.objSaveBtn, "Save button");
				verifyElementDisplayed(CheckOutPage.objMessage);
				verifyElementPresentAndClick(CheckOutPage.objOkBtn, "OK Button");
			}
			break;

		case "Confirm_Address":

			actualResult.put("Select Address", "Select Address");
			String name = getText(CheckOutPage.objName);
			actualResult.put("Name", name);
			waitTime(5000);
			verifyElementPresentAndClick(CheckOutPage.objDeliveryAddresseditBtn, "Edit address");
			verifyElementDisplayed(CheckOutPage.objChooseAddress);
			verifyElementPresentAndClick(CheckOutPage.objConfirmBtn, "Confirm Button");

			break;

		default:
			logger.info("invalid Address!!");
			extent.extentLogger("Address", "invalid Address!!");
			break;
		}

		verifyElementPresentAndClick(CheckOutPage.objDeliveryAddresseditBtn, "Edit address");
		waitTime(5000);
		verifyElementPresentAndClick(CheckOutPage.objChooseAddressEditBtn, "Edit address");

		actualResult.put("Address", getText(CheckOutPage.objCheckAddress));
		actualResult.put("City", getText(CheckOutPage.objAddressCity));
		actualResult.put("Province", getText(CheckOutPage.objAddressState));
		actualResult.put("Country", "India");
		actualResult.put("Zipcode", getText(CheckOutPage.objAddressPin));
		actualResult.put("MobileNo", getText(CheckOutPage.objAddressMobileNo));

		click(CheckOutPage.objSaveBtn, "Save button");
		verifyElementDisplayed(CheckOutPage.objMessage);
		verifyElementPresentAndClick(CheckOutPage.objOkBtn, "OK Button");

		actualResult.put("Charged", "Charged ");

		click(CheckOutPage.objPaymentPlusBtn, "Add Payment Button");
		verifyIsElementDisplayed(CheckOutPage.objCreditCardHeader, "Credit Card Title");

		actualResult.put("Payment Mode", getText(CheckOutPage.objCreditCardHeader));

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

		String orderID = getText(CheckOutPage.objOrderId);
		String[] parts = orderID.split("#");
		String part = parts[1];
		actualResult.put("Order Id", "#" + part);

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

		explicitWaitVisibility(ProductPage.objPaymentOptionHeader, 10);
		verifyIsElementDisplayed(ProductPage.objPaymentOptionHeader, "Payment Option tab");
		verifyElementPresentAndClick(ProductPage.objPaymentOptionSelect, getText(ProductPage.objPaymentOptionSelect));
		logger.info("Navigating to CheckOut Page");
		extent.extentLoggerPass("Payment option", "Navigating to CheckOut Page");

		paymentCheckOut(address);
	}

	public void barCodeSearch(String address) throws Exception {
		extent.HeaderChildNode("Search Through Bar Code");

		Aclick(PlobalLoginPage.objSearchProduct, "Search Product Tab");
		explicitWaitVisibility(ProductPage.objProductBelowName, 20);
		verifyElementPresent(ProductPage.objProductBelowName, getText(ProductPage.objProductBelowName));
		softAssertion.assertEquals(getText(ProductPage.objProductHeaderText), getText(ProductPage.objProductBelowName));
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

		explicitWaitVisibility(ProductPage.objPaymentOptionHeader, 10);
		verifyIsElementDisplayed(ProductPage.objPaymentOptionHeader, "Payment Option tab");
		verifyElementPresentAndClick(ProductPage.objPaymentOptionSelect, getText(ProductPage.objPaymentOptionSelect));
		logger.info("Navigating to CheckOut Page");
		extent.extentLoggerPass("Payment option", "Navigating to CheckOut Page");

		paymentCheckOut(address);
		softAssertion.assertAll();
	}

	public void logOut() throws Exception {
		extent.HeaderChildNode("Logout");

		explicitWaitVisibility(PlobalLoginPage.objLogoutProfileTab, 20);
		verifyElementPresentAndClick(PlobalLoginPage.objLogoutProfileTab, "Profile Tab");

		actualResult.put("logout", "User Logout");
		actualResult.put("User Name", getText(CheckOutPage.objUserName));
		actualResult.put("Email", getText(CheckOutPage.objEmail));

		Swipe("UP", 3);
		click(PlobalLoginPage.objLogOut, "Logout Button");
		verifyElementPresent(PlobalLoginPage.objLogOutMsg, getText(PlobalLoginPage.objLogOutMsg));
		verifyElementPresentAndClick(PlobalLoginPage.objLogOutOkBtn, "OK Button");
	}

	public void cleverTap(String cleverTapEmail, String cleverTapPassword, String email) throws Exception {
		extent.HeaderChildNode("Clevertap");

		setPlatform("Web");
		logger.info("platform changed to Web");
		String pf = getPlatform();
		logger.info(pf);
		waitTime(5000);
		new PlobalBusinessLogic("plobal");

		type(CleverTapPage.objEmailField, cleverTapEmail, "Email Field");
		waitTime(7000);
		JSClick(CleverTapPage.objContinueWithEmailBtn, "Continue Button");

		waitTime(7000);
		type(CleverTapPage.objPasswordField, cleverTapPassword, "Password Field");
		waitTime(7000);
		JSClick(CleverTapPage.objLoginBtn, getText(CleverTapPage.objLoginBtn));
		waitTime(7000);
		JSClick(CleverTapPage.objStageMobileAppPlobal, getText(CleverTapPage.objStageMobileAppPlobal));
		waitTime(20000);
		switchFrame_xpath(CleverTapPage.objFrame);
		explicitWaitVisibility(CleverTapPage.objAdvanceBtn, 30);
		JSClick(CleverTapPage.objAdvanceBtn, getText(CleverTapPage.objAdvanceBtn));
		waitTime(10000);
		switchTab(1);
		click(CleverTapPage.objSegmentsTab, getText(CleverTapPage.objSegmentsTab));
		type(CleverTapPage.objItentity, email, "CleverTap ID");
		click(CleverTapPage.objFindBtn, getText(CleverTapPage.objFindBtn));
		String UserCleverTapID = getText(CleverTapPage.objUserId);
		verifyElementPresentAndClick(CleverTapPage.objActivityTab, getText(CleverTapPage.objActivityTab));

		waitTime(5000);
		loginEventsCleverTap();
		waitTime(5000);
		productViewedCleverTap();
		waitTime(5000);
		addToCartCleverTap();
		waitTime(5000);
		orderPlacedPaymentPending();
		waitTime(5000);
		orderSelectAddressCleverTap();
		waitTime(5000);
		orderChargedCleverTap();
		waitTime(5000);
		logoutCleverTap();
		softAssertion.assertAll();
	}

	public void loginEventsCleverTap() throws Exception {
		extent.HeaderChildNode("Login Event");

		ScrollToTheElementWEB(CleverTapPage.objEmailScroll);
		waitTime(4000);
		expectedResult.put("event", "Login");
		expectedResult.put("Email", getText(CleverTapPage.objEmailCleverTapEvent));

		boolean event = expectedResult.get("event").equals(actualResult.get("event"));
		logger.info("Event name 'Login' & their properties(Login)--> where actualResult is " + actualResult.get("event")
				+ " & expectedResult is " + expectedResult.get("event") + " is : " + lowerToUpperBooleanValue(event)
				+ " and is " + stringName(event));
		softAssertion.assertEquals(actualResult.get("event"), expectedResult.get("event"));

		boolean emailVerify = expectedResult.get("Email").equals(actualResult.get("Email"));
		logger.info("Event name 'Login' & their properties(Email)--> where actualResult is " + actualResult.get("Email")
				+ " & expectedResult is " + expectedResult.get("Email") + " is : "
				+ lowerToUpperBooleanValue(emailVerify) + " and is " + stringName(emailVerify));
		softAssertion.assertEquals(actualResult.get("Email"), expectedResult.get("Email"));
		System.out.println("=============================================================================");

	}

	public void productViewedCleverTap() throws Exception {
		extent.HeaderChildNode("Product Viewed Event");

		ScrollToTheElementWEB(CleverTapPage.objProductViewed);

		expectedResult.put("Product Viewed", getText(CleverTapPage.objProductViewed));
		waitTime(4000);
		expectedResult.put("Product", getText(CleverTapPage.objProductName));
		expectedResult.put("price", getText(CleverTapPage.objProductPrice));

		boolean event = expectedResult.get("Product Viewed").equals(actualResult.get("Product Viewed"));
		logger.info("Event name 'Product Viewed' & their properties(Product Viewed)--> where actualResult is "
				+ actualResult.get("Product Viewed") + " & expectedResult is " + expectedResult.get("Product Viewed")
				+ " is : " + lowerToUpperBooleanValue(event) + " and is " + stringName(event));
		softAssertion.assertEquals(actualResult.get("Product Viewed"), expectedResult.get("Product Viewed"));

		boolean product = expectedResult.get("Product").equals(actualResult.get("Product"));
		logger.info("Event name 'Product Viewed' & their proprties(Product)--> where actualResult is "
				+ actualResult.get("Product") + " & expectedResult is " + expectedResult.get("Product") + " is : "
				+ lowerToUpperBooleanValue(product) + " and is " + stringName(product));
		softAssertion.assertEquals(actualResult.get("Product"), expectedResult.get("Product"));

		boolean productPrice = expectedResult.get("price").equals(actualResult.get("price"));
		logger.info("Event name 'Product Viewed' & their properties(Price)--> where actualResult is "
				+ actualResult.get("price") + " & expectedResult is " + expectedResult.get("price") + " is : "
				+ lowerToUpperBooleanValue(productPrice) + " and is " + stringName(productPrice));
		softAssertion.assertEquals(actualResult.get("price"), expectedResult.get("price"));

		System.out.println("=========================================================================");

	}

	public void addToCartCleverTap() throws Exception {
		extent.HeaderChildNode("Add To Cart Event");

		ScrollToTheElementWEB(CleverTapPage.objCartProductTitle);

		expectedResult.put("addtocart", getText(CleverTapPage.objCartProductTitle));
		waitTime(4000);
		expectedResult.put("product title", getText(CleverTapPage.objProductTitle));
		expectedResult.put("Variant Name", getText(CleverTapPage.objCartProductvariantName));
		expectedResult.put("price", getText(CleverTapPage.objCartProductPrice));

		boolean event = expectedResult.get("addtocart").equals(actualResult.get("addtocart"));
		logger.info("Event name 'ADD TO CART' & their properties(add to cart)--> where actualResult is "
				+ actualResult.get("addtocart") + " & expectedResult is " + expectedResult.get("addtocart") + " is : "
				+ lowerToUpperBooleanValue(event) + " and is " + stringName(event));
		softAssertion.assertEquals(actualResult.get("addtocart"), expectedResult.get("addtocart"));

		boolean product = expectedResult.get("product title").equals(actualResult.get("product title"));
		logger.info("Event name 'ADD To Cart' & their properties(product title)--> where actualResult is "
				+ actualResult.get("product title") + " & expectedResult is " + expectedResult.get("product title")
				+ " is : " + lowerToUpperBooleanValue(product) + " and is " + stringName(product));
		softAssertion.assertEquals(actualResult.get("product title"), expectedResult.get("product title"));

		boolean variantName = expectedResult.get("Variant Name").equals(actualResult.get("Variant Name"));
		logger.info("Event name 'ADD To Cart' & their properties(Variant Name)--> where actualResult is "
				+ actualResult.get("Variant Name") + " & expectedResult is " + expectedResult.get("Variant Name")
				+ " is : " + lowerToUpperBooleanValue(variantName) + " and is " + stringName(variantName));
		softAssertion.assertEquals(actualResult.get("Variant Name"), expectedResult.get("Variant Name"));

		boolean productPrice = expectedResult.get("price").equals(actualResult.get("price"));
		logger.info("Event name 'ADD To Cart' & their properties(price) where actualResult is "
				+ actualResult.get("price") + " & expectedResult is " + expectedResult.get("price") + " is : "
				+ lowerToUpperBooleanValue(productPrice) + " and is " + stringName(productPrice));
		softAssertion.assertEquals(actualResult.get("price"), expectedResult.get("price"));

		System.out.println("=========================================================================");

	}

	public void orderPlacedPaymentPending() throws Exception {
		extent.HeaderChildNode("Payment Pending");

		ScrollToTheElementWEB(CleverTapPage.objPaymentPending);

		expectedResult.put("payment Pending", getText(CleverTapPage.objPaymentPending));
		waitTime(4000);
		expectedResult.put("total item", getText(CleverTapPage.objPaymentPendingTotalItem));
		expectedResult.put("cart amount", getText(CleverTapPage.objPaymentPendingCartAmt));

		boolean event = expectedResult.get("payment Pending").equals(actualResult.get("payment Pending"));
		logger.info("Event name 'PAYMENT PENDING' & their properties(payment Pending)--> where actualResult is "
				+ actualResult.get("payment Pending") + " & expectedResult is " + expectedResult.get("payment Pending")
				+ " is : " + lowerToUpperBooleanValue(event) + " and is " + stringName(event));
		softAssertion.assertEquals(actualResult.get("payment Pending"), expectedResult.get("payment Pending"));

		boolean totalQuanity = expectedResult.get("total item").equals(actualResult.get("total item"));
		logger.info("Event name 'PAYMENT PENDING' & their properties(total item)--> where actualResult is "
				+ actualResult.get("total item") + " & expectedResult is " + expectedResult.get("total item") + " is : "
				+ lowerToUpperBooleanValue(totalQuanity) + " and is " + stringName(totalQuanity));
		softAssertion.assertEquals(actualResult.get("total item"), expectedResult.get("total item"));

		boolean cartAMT = expectedResult.get("cart amount").equals(actualResult.get("cart amount"));
		logger.info("Event name 'PAYMENT PENDING' & their properties(cart amount)--> where actualResult is "
				+ actualResult.get("cart amount") + " & expectedResult is " + expectedResult.get("cart amount")
				+ " is : " + lowerToUpperBooleanValue(cartAMT) + " and is " + stringName(cartAMT));
		softAssertion.assertEquals(actualResult.get("cart amount"), expectedResult.get("cart amount"));

		System.out.println("=========================================================================");

	}

	public void orderSelectAddressCleverTap() throws Exception {
		extent.HeaderChildNode("Select Address");

		ScrollToTheElementWEB(CleverTapPage.objSelectAddress);

		expectedResult.put("Select Address", getText(CleverTapPage.objSelectAddress));
		waitTime(4000);
		expectedResult.put("Name", getText(CleverTapPage.objAddressName));
		expectedResult.put("Address", getText(CleverTapPage.objSelect_Address));
		expectedResult.put("City", getText(CleverTapPage.objAddressCity));
		expectedResult.put("Province", getText(CleverTapPage.objAddressState));
		expectedResult.put("Country", getText(CleverTapPage.objAddressCountry));
		expectedResult.put("Zipcode", getText(CleverTapPage.objAddressZipCode));
		expectedResult.put("MobileNo", getText(CleverTapPage.objAddressMobileNo));

		boolean event = expectedResult.get("Select Address").equals(actualResult.get("Select Address"));
		logger.info("Event name 'Select Address' & their properties(Select Address)--> where actualResult is "
				+ actualResult.get("Select Address") + " & expectedResult is " + expectedResult.get("Select Address")
				+ " is : " + lowerToUpperBooleanValue(event) + " and is " + stringName(event));
		softAssertion.assertEquals(actualResult.get("Select Address"), expectedResult.get("Select Address"));

		boolean name = expectedResult.get("Name").equals(actualResult.get("Name"));
		logger.info("Event name 'Select Address' & their properties(Name)--> where actualResult is "
				+ actualResult.get("Select Address") + " & expectedResult is " + expectedResult.get("Select Address")
				+ " is : " + lowerToUpperBooleanValue(name) + " and is " + stringName(name));
		softAssertion.assertEquals(actualResult.get("Name"), expectedResult.get("Name"));

		boolean address = expectedResult.get("Address").equals(actualResult.get("Address"));
		logger.info("Event name 'Select Address' & their properties(Address)--> where actualResult is "
				+ actualResult.get("Address") + " & expectedResult is " + expectedResult.get("Address") + " is : "
				+ lowerToUpperBooleanValue(address) + " and is " + stringName(address));
		softAssertion.assertEquals(actualResult.get("Address"), expectedResult.get("Address"));

		boolean city = expectedResult.get("City").equals(actualResult.get("City"));
		logger.info("Event name 'Select Address' & their properties(City)--> where actualResult is "
				+ actualResult.get("City") + " & expectedResult is " + expectedResult.get("City") + " is : "
				+ lowerToUpperBooleanValue(city) + " and is " + stringName(city));
		softAssertion.assertEquals(actualResult.get("City"), expectedResult.get("City"));

		boolean state = expectedResult.get("Province").equals(actualResult.get("Province"));
		logger.info("Event name 'Select Address' & their properties(Province)--> where actualResult is "
				+ actualResult.get("Province") + " & expectedResult is " + expectedResult.get("Province") + " is : "
				+ lowerToUpperBooleanValue(state) + " and is " + stringName(state));
		softAssertion.assertEquals(actualResult.get("Province"), expectedResult.get("Province"));

		boolean country = expectedResult.get("Country").equals(actualResult.get("Country"));
		logger.info("Event name 'Select Address' & their properties(Country)--> where actualResult is "
				+ actualResult.get("Country") + " & expectedResult is " + expectedResult.get("Country") + " is : "
				+ lowerToUpperBooleanValue(country) + " and is " + stringName(country));
		softAssertion.assertEquals(actualResult.get("Country"), expectedResult.get("Country"));

		boolean pin = expectedResult.get("Zipcode").equals(actualResult.get("Zipcode"));
		logger.info("Event name 'Select Address' & their properties(Zipcode)--> where actualResult is "
				+ actualResult.get("Zipcode") + " & expectedResult is " + expectedResult.get("Zipcode") + " is : "
				+ lowerToUpperBooleanValue(pin) + " and is " + stringName(pin));
		softAssertion.assertEquals(actualResult.get("Zipcode"), expectedResult.get("Zipcode"));

		boolean mobileNo = expectedResult.get("MobileNo").equals(actualResult.get("MobileNo"));
		logger.info("Event name 'Select Address' & their properties(MobileNo)--> where actualResult is "
				+ actualResult.get("MobileNo") + " & expectedResult is " + expectedResult.get("MobileNo") + " is : "
				+ lowerToUpperBooleanValue(mobileNo) + " and is " + stringName(mobileNo));
		softAssertion.assertEquals(actualResult.get("MobileNo"), expectedResult.get("MobileNo"));

		System.out.println("=========================================================================");
	}

	public void orderChargedCleverTap() throws Exception {
		extent.HeaderChildNode("Charged");

		ScrollToTheElementWEB(CleverTapPage.objChargedTitle);

		expectedResult.put("Charged", getText(CleverTapPage.objChargedTitle));
		waitTime(4000);
		expectedResult.put("Order Id", getText(CleverTapPage.objChargedID));
		expectedResult.put("Payment Mode", getText(CleverTapPage.objChargedPaymentMode));

		boolean event = expectedResult.get("Charged").equals(actualResult.get("Charged"));
		logger.info("Event name 'Charged' & their properties(Charged)--> where actualResult is "
				+ actualResult.get("Charged") + " & expectedResult is " + expectedResult.get("Charged") + " is : "
				+ lowerToUpperBooleanValue(event) + " and is " + stringName(event));
		softAssertion.assertEquals(actualResult.get("Charged"), expectedResult.get("Charged"));

		boolean orderID = expectedResult.get("Order Id").equals(actualResult.get("Order Id"));
		logger.info("Event name 'Charged' & their properties(Order Id)--> where actualResult is "
				+ actualResult.get("Order Id") + " & expectedResult is " + expectedResult.get("Order Id") + " is : "
				+ lowerToUpperBooleanValue(orderID) + " and is " + stringName(orderID));
		softAssertion.assertEquals(actualResult.get("Order Id"), expectedResult.get("Order Id"));

		boolean paymentMode = expectedResult.get("Payment Mode").equals(actualResult.get("Payment Mode"));
		logger.info("Event name 'Charged' & their properties(Payment Mode)--> where actualResult is "
				+ actualResult.get("Payment Mode") + " & expectedResult is " + expectedResult.get("Payment Mode")
				+ " is : " + lowerToUpperBooleanValue(paymentMode) + " and is " + stringName(paymentMode));
		softAssertion.assertEquals(actualResult.get("Payment Mode"), expectedResult.get("Payment Mode"));

		System.out.println("=========================================================================");

	}

	public void logoutCleverTap() throws Exception {
		extent.HeaderChildNode("Logout");
		ScrollToTheElementWEB(CleverTapPage.objUserLogoutitle);
		expectedResult.put("logout", getText(CleverTapPage.objUserLogoutitle));
		expectedResult.put("User Name", getText(CleverTapPage.objUsername));
		expectedResult.put("Email", getText(CleverTapPage.objUserEmail));

		boolean event = expectedResult.get("logout").equals(actualResult.get("logout"));
		logger.info("Event name 'logout' & their properties(logout)--> where actualResult is "
				+ actualResult.get("logout") + " & expectedResult is " + expectedResult.get("logout") + " is : "
				+ lowerToUpperBooleanValue(event) + " and is " + stringName(event));
		softAssertion.assertEquals(actualResult.get("logout"), expectedResult.get("logout"));

		boolean userName = expectedResult.get("User Name").equals(actualResult.get("User Name"));
		logger.info("Event name 'User Name' & their properties(Order Id)--> where actualResult is "
				+ actualResult.get("User Name") + " & expectedResult is " + expectedResult.get("User Name") + " is : "
				+ lowerToUpperBooleanValue(userName) + " and is " + stringName(userName));
		softAssertion.assertEquals(actualResult.get("User Name"), expectedResult.get("User Name"));

		boolean userEmail = expectedResult.get("Email").equals(actualResult.get("Email"));
		logger.info("Event name 'Email' & their properties(Order Id)--> where actualResult is "
				+ actualResult.get("Email") + " & expectedResult is " + expectedResult.get("Email") + " is : "
				+ lowerToUpperBooleanValue(userEmail) + " and is " + stringName(userEmail));
		softAssertion.assertEquals(actualResult.get("Email"), expectedResult.get("Email"));

	}
}
