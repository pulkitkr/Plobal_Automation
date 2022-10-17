package com.android.PlobalPages;

import org.openqa.selenium.By;

public class CheckOutPage {
	
	//ChekOut Header
	public static By objCheckOutHeader = By.xpath("//*[@resource-id='plobaltestshutterstock.android.staging:id/textview_title']");
	
	//Delivery Address + button
	public static By objDeliveryAddressPlusBtn  = By.xpath("//*[@resource-id='plobaltestshutterstock.android.staging:id/address_shipping_selected_address_add_imageView']");
	
	//Delivery Address edit button
	public static By objDeliveryAddresseditBtn  = By.xpath("//*[@resource-id='plobaltestshutterstock.android.staging:id/address_shipping_selected_address_edit_imageView']");
	
	//Delivery Address text
	public static By objDeliveryAddressText  = By.xpath("//*[@resource-id='plobaltestshutterstock.android.staging:id/address_shipping_selected_address_title_textview']");
	
	//Add Address Header
	public static By objAddAddressHeader  = By.xpath("//*[@resource-id='plobaltestshutterstock.android.staging:id/add_address_title_textView']");
	
	//Choose Delivery Address
	public static By objChooseAddress  = By.xpath("//*[@resource-id='plobaltestshutterstock.android.staging:id/myaddresses_title_textView']");
	
	//Confirm Button
	public static By objConfirmBtn  = By.xpath("//*[@resource-id='plobaltestshutterstock.android.staging:id/my_addresses_confirm_button']");
	
	//Add Address Header
	public static By objAddAddress = By.xpath("//*[@resource-id='plobaltestshutterstock.android.staging:id/add_address_title_textView']");
	
	//First Name Field
	public static By objFirstName = By.xpath("//*[@resource-id='plobaltestshutterstock.android.staging:id/ui_editText']");
	
	//Last Name Field
	public static By objLastName = By.xpath("(//*[@resource-id='plobaltestshutterstock.android.staging:id/ui_editText'])[2]");
	
	//Contact number Field
	public static By objContactNo = By.xpath("(//*[@resource-id='plobaltestshutterstock.android.staging:id/ui_editText'])[4]");
	
	//Address line Field
	public static By objAddress = By.xpath("(//*[@resource-id='plobaltestshutterstock.android.staging:id/ui_editText'])[5]");
	
	//Country dropdown
	public static By objCountry = By.xpath("//*[@resource-id='plobaltestshutterstock.android.staging:id/ui_spinner']");
	
	//State Field
	public static By objState = By.xpath("(//*[@resource-id='plobaltestshutterstock.android.staging:id/ui_editText'])[7]");
	
	//City Field
	public static By objCity = By.xpath("(//*[@resource-id='plobaltestshutterstock.android.staging:id/ui_editText'])[8]");
	
	//Zip code Field
	public static By objZipCode = By.xpath("(//*[@resource-id='plobaltestshutterstock.android.staging:id/ui_editText'])[9]");
	
	//Invalid Zip code message
	public static By objInvalidZipCodeMsg = By.xpath("(//*[@resource-id='/hierarchy/android.widget.Toast'])[9]");
	
	//Save Button
	public static By objSaveBtn = By.xpath("//*[@resource-id='plobaltestshutterstock.android.staging:id/editaddressactivity_submit_button']");
	
	//Message
	public static By objMessage = By.xpath("//*[@resource-id='android:id/message']");
	
	//Ok Btn
	public static By objOkBtn = By.xpath("//*[@resource-id='android:id/button1']");
	
	//Payment + button
	public static By objPaymentPlusBtn  = By.xpath("//*[@resource-id='plobaltestshutterstock.android.staging:id/payment_add_imageView']");
	
	//Credit Card header
	public static By objCreditCardHeader  = By.xpath("//*[@resource-id='plobaltestshutterstock.android.staging:id/layout_line_order_card_text']");
	
	//Credit Card Number Field
	public static By objCreditCardNumber  = By.xpath("//*[@resource-id='plobaltestshutterstock.android.staging:id/editTxt_card_number']");
	
	//Credit Card First name Field
	public static By objCreditCardFirstName  = By.xpath("//*[@resource-id='plobaltestshutterstock.android.staging:id/editTxt_card_firstname']");
	
	//Credit Card Last name Field
	public static By objCreditCardLastName  = By.xpath("//*[@resource-id='plobaltestshutterstock.android.staging:id/editTxt_card_lastname']");
	
	//Credit Card Month year Field
	public static By objCreditCardExpiryDate  = By.xpath("//*[@resource-id='plobaltestshutterstock.android.staging:id/EditText_expire_date']");
	
	//Invalid card details message
	public static By objInvalidCardDetailsMsg = By.xpath("//*[@resource-id='plobaltestshutterstock.android.staging:id/textinput_error']");
	
	//Credit Card CVV Field
	public static By objCreditCardCVV  = By.xpath("//*[@resource-id='plobaltestshutterstock.android.staging:id/EditText_cvv']");
	
	//ADD Payment Button
	public static By objCreditCardAddpaymentBtn = By.xpath("//*[@resource-id='plobaltestshutterstock.android.staging:id/btn_pay_now']");
	
	//order total amount
	public static By objTotalAmt = By.xpath("//*[@text='Total']/following-sibling::android.widget.TextView");
	
	//Place order Button
	public static By objPlaceOrderBtn  = By.xpath("//*[@resource-id='plobaltestshutterstock.android.staging:id/checkout_confirm_button']");
	
	//Thank you Title
	public static By objThankYouTitle  = By.xpath("//*[@resource-id='plobaltestshutterstock.android.staging:id/textview_title']");
	
	//Thank you Bag
	public static By objThankYouBagText  = By.xpath("//*[@resource-id='plobaltestshutterstock.android.staging:id/thank_you_order_placed_textview']");
	
	//Order Id
	public static By objOrderId  = By.xpath("//*[@resource-id='plobaltestshutterstock.android.staging:id/thank_you_order_number_textview']");
	
	//Continue Shopping Button
	public static By objContinueShoppingBtn  = By.xpath("//*[@resource-id='plobaltestshutterstock.android.staging:id/tv_ok_button']");
	
	//Continue Shopping Button
	public static By objItemCost  = By.xpath("//*[@resource-id='plobaltestshutterstock.android.staging:id/single_checkout_statusbar_item_count_textview']/following-sibling::android.widget.TextView");
	
	//Remove Address
	public static By objRemoveAdd  = By.xpath("//*[@resource-id='plobaltestshutterstock.android.staging:id/myaddresses_item_row_remove']");
	
	//Remove this address message
	public static By objRemoveAddressMsg = By.xpath("//*[@resource-id='android:id/message']");
	
	//Remove this address yes button
	public static By objRemoveAddressYesBtn = By.xpath("//*[@resource-id='android:id/button1']");
	
	//Choose address edit button button
	public static By objChooseAddressEditBtn = By.xpath("//*[@resource-id='plobaltestshutterstock.android.staging:id/myaddresses_item_row_edit']");
	
	//place order button
	public static By objplaceOrderBtn = By.xpath("//*[@resource-id='plobaltestshutterstock.android.staging:id/btn_place_order']");
	
	//Name
	public static By objName = By.xpath("//*[@resource-id='plobaltestshutterstock.android.staging:id/address_shipping_selected_address_textview_name']");
	
	//Address
	public static By objCheckAddress = By.xpath("(//*[@resource-id='plobaltestshutterstock.android.staging:id/add_address_title_textView']/parent::android.widget.RelativeLayout/following-sibling::android.widget.ScrollView/descendant::android.widget.EditText)[5]");
	
	//City
	public static By objAddressCountry = By.xpath("(//*[@resource-id='plobaltestshutterstock.android.staging:id/ui_spinner']");
	
	//State  
	public static By objAddressState = By.xpath("(//*[@resource-id='plobaltestshutterstock.android.staging:id/add_address_title_textView']/parent::android.widget.RelativeLayout/following-sibling::android.widget.ScrollView/descendant::android.widget.EditText)[7]");
	
	//Country
	public static By objAddressCity = By.xpath("(//*[@resource-id='plobaltestshutterstock.android.staging:id/add_address_title_textView']/parent::android.widget.RelativeLayout/following-sibling::android.widget.ScrollView/descendant::android.widget.EditText)[8]");
	
	//ZIp code
	public static By objAddressPin = By.xpath("(//*[@resource-id='plobaltestshutterstock.android.staging:id/add_address_title_textView']/parent::android.widget.RelativeLayout/following-sibling::android.widget.ScrollView/descendant::android.widget.EditText)[9]");
	
	//Mobile no
	public static By objAddressMobileNo = By.xpath("(//*[@resource-id='plobaltestshutterstock.android.staging:id/add_address_title_textView']/parent::android.widget.RelativeLayout/following-sibling::android.widget.ScrollView/descendant::android.widget.EditText)[4]");
	
	//Charged Amt
	public static By objChargedAmt = By.xpath("(//*[@resource-id='plobaltestshutterstock.android.staging:id/single_checkout_statusbar_total_textview']");
	
	//UserName
	public static By objUserName = By.xpath("//*[@resource-id='plobaltestshutterstock.android.staging:id/textview_user_name']");
	
	//UserName
	public static By objEmail = By.xpath("//*[@resource-id='plobaltestshutterstock.android.staging:id/textview_user_email']");
}
