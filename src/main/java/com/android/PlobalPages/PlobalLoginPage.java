package com.android.PlobalPages;

import org.openqa.selenium.By;

public class PlobalLoginPage {

	// Welcome header
	public static By objWelcomeHeader = By.xpath("//*[@text='NEVER MISS ON YOUR SCENTS!']");

	// Welcome page yes button
	public static By objYesBtn = By.xpath("//*[@text='Yes']");

	// Welcome to family
	public static By objWelcomeToFamilyText = By.xpath("//*[@text='WELCOME TO THE FAMILY!']");

	// Done Button
	public static By objDoneBtn = By.xpath("//*[@text='Done']");

	// HomePage Header logo
	public static By objHomePageLogo = By
			.xpath("//*[@resource-id='plobaltestshutterstock.android.staging:id/imageView_app_logo']");

	// Search Product
	public static By objSearchProduct = By.xpath(
			"//*[@resource-id='plobaltestshutterstock.android.staging:id/home_screen_collections_popular_product_searchView']");

	// Profile Tab
	public static By objProfileTab = By.xpath(
			"(//*[@class='android.widget.HorizontalScrollView']/child::android.widget.LinearLayout/descendant::android.widget.ImageView)[4]");
	
	// logout Profile Tab
		public static By objLogoutProfileTab = By.xpath(
				"(//*[@class='android.widget.HorizontalScrollView']/child::android.widget.LinearLayout/descendant::android.widget.ImageView)[4]");

	// Login Btn
	public static By objLoginBtn = By
			.xpath("//*[@resource-id='plobaltestshutterstock.android.staging:id/btn_myprofile_login']");

	// Login Page Header
	public static By objLoginPageHeader = By
			.xpath("//*[@resource-id='plobaltestshutterstock.android.staging:id/textview_title']");

	// Email id
	public static By objEmailTextField = By
			.xpath("//*[@resource-id='plobaltestshutterstock.android.staging:id/editTxt_edit_username']");

	// Pasword
	public static By objPasswordTextFied = By
			.xpath("//*[@resource-id='plobaltestshutterstock.android.staging:id/editTxt_password']");

	// Login Button
	public static By objLoginButton = By
			.xpath("//*[@resource-id='plobaltestshutterstock.android.staging:id/btn_login']");

	// User created or not
	public static By objusercreated(String user) {
		return By.xpath("//*[@text='" + user + "']");
	}

	// HomePage Tab
	public static By objHomePageTab = By.xpath(
			"(//*[@class='android.widget.HorizontalScrollView']/child::android.widget.LinearLayout/descendant::android.widget.ImageView)[1]");

	// Allow to take pictures popup
	public static By objAllowPopup = By
			.xpath("//*[@resource-id='com.android.permissioncontroller:id/permission_message']");

	// Allow Button
	public static By objAllowBtn = By
			.xpath("//*[@text='Allow' or @text='WHILE USING THE APP' or @text='ALLOW' or @text='While using the app']");

	// collection product
	public static By objCollectionProduct = By.xpath(
			"//*[@resource-id='plobaltestshutterstock.android.staging:id/home_viewtype_collectionproduct_title_text']");

	// Select First product
	public static By objCollectionFirstProduct = By.xpath(
			"(//*[@resource-id='plobaltestshutterstock.android.staging:id/home_viewtype_collectionproduct_title_text']/parent::android.widget.RelativeLayout/following-sibling::android.widget.RelativeLayout/descendant::android.widget.ImageView)[3]");

	// Search Bar
	public static By objSearchBar = By.xpath("//*[@text='Search products']");

	// Gold ring Product
	public static By objProductName = By
			.xpath("//*[@resource-id='plobaltestshutterstock.android.staging:id/txt_product_title']");

	// share button
	public static By objShareImage = By
			.xpath("//*[@resource-id='plobaltestshutterstock.android.staging:id/pdp_share_like_share_iv']");

	// Wish button
	public static By objWishlistImage = By
			.xpath("//*[@resource-id='plobaltestshutterstock.android.staging:id/imageview_wish_icon']");

	// Add to cart Button
	public static By objAddToCartBtn = By
			.xpath("//*[@resource-id='plobaltestshutterstock.android.staging:id/btn_bottom_add_to_cart']");

	// Buy Now Button
	public static By objBuyNowBtn = By
			.xpath("//*[@resource-id='plobaltestshutterstock.android.staging:id/btn_bottom_buy_now']");

	// Toast message
	public static By objToastMsg = By.xpath("//*[@text='Unidentified customer']");
	
	// Login page title
	public static By objLoginPageTitle = By.xpath("//*[@resource-id='plobaltestshutterstock.android.staging:id/TextView_app_name']");
	
	// Logout
	public static By objLogOut = By.xpath("//*[@resource-id='plobaltestshutterstock.android.staging:id/textview_logout']");
	
	// Logout message
	public static By objLogOutMsg = By.xpath("//*[@resource-id='android:id/message']");
	
	// Logout Ok button
	public static By objLogOutOkBtn = By.xpath("//*[@resource-id='android:id/button1']");
}
