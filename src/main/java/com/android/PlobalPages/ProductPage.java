package com.android.PlobalPages;

import org.openqa.selenium.By;

public class ProductPage {

	//Selected product Name
	public static By objSelectedProductText = By.xpath("(//*[@resource-id='plobaltestshutterstock.android.staging:id/txt_product_title'])[1]");
	
	//opened product text
	public static By objOpenProductText = By.xpath("//*[@resource-id='plobaltestshutterstock.android.staging:id/txt_product_title']");
	
	//Add to cart Button
	public static By objAddToCartBtn = By.xpath("//*[@resource-id='plobaltestshutterstock.android.staging:id/btn_bottom_add_to_cart' or text()='GO TO CART']");
	
	//Buy Now Button
	public static By objBuyNowBtn = By.xpath("//*[@resource-id='plobaltestshutterstock.android.staging:id/btn_bottom_buy_now']");
	
	//Payment option Header
	public static By objPaymentOptionHeader = By.xpath("//*[@resource-id='plobaltestshutterstock.android.staging:id/payment_options_title_textview']");
	
	//Payment option select
	public static By objPaymentOptionSelect= By.xpath("//*[@resource-id='plobaltestshutterstock.android.staging:id/payment_list_item_TextView']");
	
	//share button
	public static By objShareImage= By.xpath("//*[@resource-id='plobaltestshutterstock.android.staging:id/pdp_share_like_share_iv']");
	
	//Wish button
	public static By objWishlistImage= By.xpath("//*[@resource-id='plobaltestshutterstock.android.staging:id/imageview_wish_icon']");
	
	//Select Size
	public static By objSize= By.xpath("//*[@resource-id='plobaltestshutterstock.android.staging:id/spinnerText']");
	
	//Out of stock
	public static By objOutofStock= By.xpath("//*[@resource-id='plobaltestshutterstock.android.staging:id/btn_bottom_add_to_cart']");
	
	//Size
	public static By objuserSize(String size) {
		return By.xpath("//*[@text='" + size + "']");
	}
	
	//Cart page header
	public static By objCartrHeader = By.xpath("//*[@resource-id='plobaltestshutterstock.android.staging:id/textview_title']");
	
	//Product Name in cart page
	public static By objProductName = By.xpath("//*[@resource-id='plobaltestshutterstock.android.staging:id/txtview_product_name']");
	
	//Item price on cart page
	public static By objProductPriceHeader = By.xpath("//*[@resource-id='plobaltestshutterstock.android.staging:id/txtview_total']");
	
	//Cart image
	public static By objProductBag = By.xpath("//*[@resource-id='plobaltestshutterstock.android.staging:id/cart_custom_menu_imageView']");
	
	//Cart Product name
	public static By objProductCartName = By.xpath("//*[@resource-id='plobaltestshutterstock.android.staging:id/txtview_product_name']");
	
	//Barcode Scan
	public static By objProductHeaderText = By.xpath("//*[@resource-id='plobaltestshutterstock.android.staging:id/textview_title']");
	
	//Product Below Name
	public static By objProductBelowName = By.xpath("//*[@resource-id='plobaltestshutterstock.android.staging:id/txt_product_title']");
	
	//Product Price
	public static By objProductPrice = By.xpath("//*[@resource-id='plobaltestshutterstock.android.staging:id/txt_price_discounted']");
	
	//Product card title
	public static By objProductCartTitle = By.xpath("//*[@resource-id='plobaltestshutterstock.android.staging:id/txtview_product_name']");
	
	//Product card variant name
	public static By objProductCartVariant = By.xpath("//*[@resource-id='plobaltestshutterstock.android.staging:id/shopping_cart_item_variant_option_1']");
	
	//Product card price
	public static By objProductCartPrice = By.xpath("//*[@resource-id='plobaltestshutterstock.android.staging:id/txt_price_discounted']");
	
	//total product in cart
	public static By objTotalProduct = By.xpath("//*[@resource-id='plobaltestshutterstock.android.staging:id/single_checkout_statusbar_item_count_textview']");
	
	//total cart amount
	public static By objTotalAmt = By.xpath("//*[@resource-id='plobaltestshutterstock.android.staging:id/summary_order_details_total_value_TextView']");
}