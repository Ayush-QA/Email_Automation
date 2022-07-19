package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import factory.WebDriverUtil;

public class Urls_Page {
	@FindBy(xpath = "//input[@placeholder='Email']")
	WebElement usernamebox;

	@FindBy(xpath = "//input[@placeholder='Password']")
	WebElement passwordbox;

	@FindBy(xpath = "//button[@class='btn']")
	WebElement signIn;

	@FindBy(xpath = "//span[normalize-space()='Projects']")
	WebElement project_btn;

	WebDriver driver;
	WebDriverUtil util;

	public Urls_Page(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public String getUrl(String url) {
		driver.get(url);
		return url;
	}

	public void enterUserName(String username) {
		util = new WebDriverUtil();
		util.waitForElementVisiblity(usernamebox);
		usernamebox.sendKeys(username);
	}

	public void enterPassword(String password) {
		util = new WebDriverUtil();
		util.waitForElementVisiblity(passwordbox);
		passwordbox.sendKeys(password);
	}

	public void clickOnSignIn() throws InterruptedException {
		util = new WebDriverUtil();
		util.waitForElementToBeClickable(signIn);
		util.waitForElementJavaScript(signIn);
	}

	public void waitForPageLoading() throws InterruptedException {
		util = new WebDriverUtil();
		util.waitForElementToBeClickable(project_btn);
	}

}
