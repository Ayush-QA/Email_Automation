package factory;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.paulhammant.ngwebdriver.NgWebDriver;

public class WebDriverUtil {

	public void waitForElementToBeClickable(WebElement element) {
		WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), 10);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public void page_Load_Method() {
		JavascriptExecutor jsDriver = (JavascriptExecutor) DriverFactory.getDriver();
		NgWebDriver ngDriver = new NgWebDriver(jsDriver);
		ngDriver.withRootSelector("\"app-root\"").waitForAngularRequestsToFinish();
	}

	public void waitForElementVisiblity(WebElement element) {
		WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), 60);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void waitForElementJavaScript(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) DriverFactory.getDriver();
		js.executeScript("arguments[0].click();", element);
	}

}
