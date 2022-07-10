package factory;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import com.paulhammant.ngwebdriver.NgWebDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

	public WebDriver driver;

	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

	/**
	 * This method is used to initialize the thradlocal driver on the basis of given
	 * browser
	 * 
	 * @param browser
	 * @return this will return tldriver.
	 */
	public WebDriver init_driver(String browser) {

		System.out.println("browser value is: " + browser);

		if (browser.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			tlDriver.set(new ChromeDriver());
		}

		else if (browser.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			tlDriver.set(new FirefoxDriver());
		}

		else if (browser.equals("safari")) {
			tlDriver.set(new SafariDriver());
		}

		else {
			System.out.println("Please pass the correct browser value: " + browser);
		}
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		JavascriptExecutor jsDriver = (JavascriptExecutor) getDriver();
		NgWebDriver ngDriver = new NgWebDriver(jsDriver);
		ngDriver.withRootSelector("\"app-root\"").waitForAngularRequestsToFinish();
		getDriver().manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		return getDriver();
	}

	/**
	 * this is used to get the driver with ThreadLocal
	 * 
	 * @return
	 */
	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}

	public static synchronized NgWebDriver getNgDriver() {
		JavascriptExecutor jsDriver = (JavascriptExecutor) DriverFactory.getDriver();
		NgWebDriver ngDriver = new NgWebDriver(jsDriver);
		return ngDriver;
	}
}
