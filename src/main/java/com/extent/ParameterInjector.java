package com.extent;

//import static com.jayway.restassured.RestAssured.given;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.testng.xml.XmlSuite;
//import com.jayway.restassured.response.Response;
import org.testng.IAlterSuiteListener;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ParameterInjector implements IAlterSuiteListener {

	@Override
	public void alter(List<XmlSuite> suites) {
		XmlSuite suite = suites.get(0);
		Map<String, String> params = new HashMap<>();

		// Pass environment data
		params.put("browserType", suite.getParameter("browserType"));
		params.put("userType", suite.getParameter("userType"));
		params.put("NonsubscribedUserName", suite.getParameter("NonsubscribedUserName"));
		params.put("runModule", suite.getParameter("runModule"));
		params.put("runMode", suite.getParameter("runMode"));
		System.out.println("Browser type : " + suite.getParameter("browserType"));

		if (suite.getParameter("url").equals("newpwa")) {
			params.put("url", "https://newpwa.zee5.com/");
		} else if (suite.getParameter("url").equals("prod")) {
			params.put("url", "https://www.zee5.com/");
		} else if (suite.getParameter("url").equals("preprod")) {
			params.put("url", "https://pwa-preprod2.zee5.com/");
		} else if (suite.getParameter("url").equals("pwauat6")) {
			params.put("url", "https://pwauat6.zee5.com/");
		} else if (suite.getParameter("url").equals("url")) {
			params.put("url", "https://plobal-test-shutterstock.myshopify.com/admin\"");
		}

		// Pass region specific data
		Response regionResponse = RestAssured.given().urlEncodingEnabled(false).when()
				.get("https://xtra.zee5.com/country");
		String region = regionResponse.getBody().jsonPath().getString("state_code");
		System.out.println("Region : " + region);
		if (region.equals("KA")) {
			params.put("searchModuleSearchKey", "Kamali");
			if (suite.getFileName().contains("WEB_Mixpanel")) {
				params.put("ClubUserName", suite.getParameter("ClubUserName"));
				params.put("SettingsNonSubscribedUserName", suite.getParameter("SettingsNonSubscribedUserName"));
			}
			params.put("dfpAdContent", "Jodi Hakki");
			params.put("ClubUserName", suite.getParameter("ClubUserName"));
			params.put("freeContentURL",
					"https://newpwa.zee5.com/tvshows/details/paaru/0-6-1179/paaru-gets-tipsy-paaru-highlights/0-1-249189");
			params.put("comboOfferMovie", "Radhe - Your Most Wanted Bhai");
			if (suite.getParameter("url").equals("newpwa")) {
				params.put("DeeplinkConsumption",
						"https://newpwa.zee5.com/movies/details/radhe-your-most-wanted-bhai/0-0-399328");
				params.put("DeeplinkSubscription", "https://newpwa.zee5.com/myaccount/subscription");
			} else {
				params.put("DeeplinkConsumption",
						"https://www.zee5.com/movies/details/radhe-your-most-wanted-bhai/0-0-399328");
				params.put("DeeplinkSubscription", "https://www.zee5.com/myaccount/subscription");
			}
			params.put("TVODUserName", suite.getParameter("TVODUserName"));
		}
		if (region.equals("MH")) {
			params.put("searchModuleSearchKey", "Kundali Bhagya");
			if (suite.getFileName().contains("WEB_Mixpanel")) {
				params.put("ClubUserName", suite.getParameter("ClubUserName"));
			}
			params.put("dfpAdContent", "Jodi Hakki");
			params.put("RSVODUserPassword499", suite.getParameter("RSVODUserPassword499"));
			if (suite.getParameter("url").equals("newpwa")) {
				params.put("DeeplinkConsumption",
						"https://newpwa.zee5.com/movies/details/radhe-your-most-wanted-bhai/0-0-399328");

			} else {
				params.put("DeeplinkConsumption",
						"https://www.zee5.com/movies/details/radhe-your-most-wanted-bhai/0-0-399328");
			}
		}
		suite.setParameters(params);
	}
}
