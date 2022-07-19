package Parallel;

import java.net.HttpURLConnection;
import java.net.URL;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import factory.DriverFactory;
import io.cucumber.java.en.Given;
import pages.Urls_Page;
import util.JSONFileClass;

public class TestCase {
	Urls_Page url;
	JSONFileClass file;
	JSONObject user;

	@Given("^navigate to url and get response status code$")
	public void navigate_to_url_and_get_response_status_code() throws Exception {
		url = new Urls_Page(DriverFactory.getDriver());
		file = new JSONFileClass();
		user = file.readJson();
		JSONArray urlArray = (JSONArray) user.get("Urls");

		for (int i = 0; i < urlArray.size(); i++) {
			JSONObject details = (JSONObject) urlArray.get(i);
			url.getUrl((String) details.get("url"));
			HttpURLConnection c = (HttpURLConnection) new URL((String) details.get("url")).openConnection();
			// set the HEAD request with setRequestMethod
			c.setRequestMethod("HEAD");
			// connection started and get response code
			c.connect();
			int status_code = c.getResponseCode();

			if (status_code == 200) {
				System.out.println("Status Code " + status_code + " is valid.");
			} else {
				System.out.println("Status Code " + status_code + " is invalid.");
				Email email = new SimpleEmail();
				email.setHostName("smtp.gmail.com");
				email.setSmtpPort(587);
				email.setAuthenticator(new DefaultAuthenticator("aayush.qa.test@gmail.com", "smnpilbfviddnfmo"));
				email.setSSLOnConnect(true);
				email.setFrom("aayush.qa.test@gmail.com");
				email.setSubject("Invalid Status Code");
				email.setMsg("Status code of " + details.get("url") + " is : " + status_code);
				email.addTo("harishkahol@gmail.com");
				email.send();
			}

		}
	}

	@Given("^navigate to url and get load time$")
	public void navigate_to_url_and_get_load_time() throws Exception {
		url = new Urls_Page(DriverFactory.getDriver());
		file = new JSONFileClass();
		user = file.readJson();

		JSONArray urlArray = (JSONArray) user.get("Urls");

		for (int i = 0; i < urlArray.size(); i++) {
			JSONObject details = (JSONObject) urlArray.get(i);
			long startTime = System.currentTimeMillis();
			url.getUrl((String) details.get("url"));
			long endTime = System.currentTimeMillis();
			float totalTime = (endTime - startTime) / 1000;

			if (totalTime > 8) {
				System.out.println("Load time " + totalTime + " secs is invalid.");
				Email email = new SimpleEmail();
				email.setHostName("smtp.gmail.com");
				email.setSmtpPort(587);
				email.setAuthenticator(new DefaultAuthenticator("aayush.qa.test@gmail.com", "smnpilbfviddnfmo"));
				email.setSSLOnConnect(true);
				email.setFrom("aayush.qa.test@gmail.com");
				email.setSubject("Invalid load time");
				email.setMsg("Load time of " + details.get("url") + " is : " + totalTime + " secs.");
				email.addTo("harishkahol@gmail.com");
				email.send();
			} else {
				System.out.println("Load time " + totalTime + " secs is valid.");

			}

		}
	}

	@Given("^navigate to url and check login functionality$")
	public void navigate_to_url_and_check_login_functionality() throws Exception {
		url = new Urls_Page(DriverFactory.getDriver());
		file = new JSONFileClass();
		user = file.readJson();
		JSONArray dataArray = (JSONArray) user.get("login");

		for (int i = 0; i < dataArray.size(); i++) {
			JSONObject details = (JSONObject) dataArray.get(i);
			url.getUrl((String) details.get("url"));
			url.enterUserName((String) details.get("username"));
			url.enterPassword((String) details.get("password"));
			url.clickOnSignIn();
			Thread.sleep(4000);
			String dashboard_Url = (String) details.get("dashboard");
			String actual_Url = DriverFactory.getDriver().getCurrentUrl();
			if (dashboard_Url.equalsIgnoreCase(actual_Url)) {
				System.out.println("Login success.");

			} else {
				System.out.println("Login failure.");
				Email email = new SimpleEmail();
				email.setHostName("smtp.gmail.com");
				email.setSmtpPort(587);
				email.setAuthenticator(new DefaultAuthenticator("aayush.qa.test@gmail.com", "smnpilbfviddnfmo"));
				email.setSSLOnConnect(true);
				email.setFrom("aayush.qa.test@gmail.com");
				email.setSubject("Login failure");
				email.setMsg("Login attempt of " + details.get("url") + " is failed.");
				email.addTo("harishkahol@gmail.com");
				email.send();
			}

		}
	}

	@Given("^navigate to url and login and check home page content$")
	public void navigate_to_url_and_login_and_check_home_page_content() throws Exception {
		url = new Urls_Page(DriverFactory.getDriver());
		file = new JSONFileClass();
		user = file.readJson();
		JSONArray dataArray = (JSONArray) user.get("Credential");

		for (int i = 0; i < dataArray.size(); i++) {
			JSONObject details = (JSONObject) dataArray.get(i);
			url.getUrl((String) details.get("url"));
			url.enterUserName((String) details.get("username"));
			url.enterPassword((String) details.get("password"));
			url.clickOnSignIn();
			url.waitForPageLoading();
			String text = "Lusher1234";
			if (DriverFactory.getDriver().getPageSource().contains(text)) {
				System.out.println("Text is present.");
			} else {
				System.out.println("Text is not present.");
				Email email = new SimpleEmail();
				email.setHostName("smtp.gmail.com");
				email.setSmtpPort(587);
				email.setAuthenticator(new DefaultAuthenticator("aayush.qa.test@gmail.com", "smnpilbfviddnfmo"));
				email.setSSLOnConnect(true);
				email.setFrom("aayush.qa.test@gmail.com");
				email.setSubject("Content checker");
				email.setMsg("Provided text is : " + text + " which is not present.");
				email.addTo("harishkahol@gmail.com");
				email.send();
			}

		}
	}

}
