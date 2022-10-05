package com.android.PlobalPages;

import org.openqa.selenium.By;

public class ProductPage {

	//Selected product Name
	public static By objSelectedProductText = By.xpath("(//*[@resource-id='plobaltestshutterstock.android.staging:id/txt_product_title'])[1]");
	
	//opened product text
	public static By objOpenProductText = By.xpath("//*[@resource-id='plobaltestshutterstock.android.staging:id/txt_product_title']");
	
	//Add to cart Button
	public static By objAddToCartBtn = By.xpath("//*[@resource-id='plobaltestshutterstock.android.staging:id/btn_bottom_add_to_cart']");
	
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
}