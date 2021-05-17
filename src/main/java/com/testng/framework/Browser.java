package com.testng.framework;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.io.Files;

/**
 * Class to setup Browser configuration and Actions.
 */
public class Browser {

	static Logger log = Logger.getLogger(Browser.class);

	protected static WebDriver driver;
	protected WebDriverWait waitDriver;
	protected Actions actionDriver;
	protected String parentHandle;
	protected Browsers browser;
	protected JavascriptExecutor jsDriver;
	private int dynamicWait = 60;
	private String downloadFolderPath;

	public Browser() {
		this(null);
	}

	public Browser(WebDriver driver) {
		this.driver = driver;
		if (driver != null)
			initialize();
	}

	public void launchBrowser(String browser,String url) {
		launchBrowser(getBrowserFromString(browser),url);
	}

	private Browsers getBrowserFromString(String browser) {
		if (browser.equalsIgnoreCase("firefox")) {
			return Browsers.FireFox;
		}
		return Browsers.Chrome;
	}

	public Browsers getBrowser() {
		return browser;
	}

	/**
	 * Launch Browser
	 * 
	 * @param browser
	 * @throws MalformedURLException
	 */
	public void launchBrowser(Browsers browser,String url) {
		String osArch = System.getProperty("os.arch");
		String osName = System.getProperty("os.name");
		log.info("Browser : " + browser);
		log.info("OS Arch : " + osArch);
		log.info("OS Name : " + osName);

		if (browser == Browsers.Chrome) {

			if(osArch.equalsIgnoreCase("Windows")) {

				System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");

				//WebDriverManager.chromedriver().setup();

				Map<String, Object> prefs = new HashMap<String, Object>();

				prefs.put("profile.default_content_setting_values.notifications", 2);

				// Create object of ChromeOption class
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--disable-web-security");
				//chrome_options.add_argument("--disable-web-security")
				options.addArguments("--disable-site-isolation-trials");

				// Set the experimental option
				options.setExperimentalOption("prefs", prefs);
				driver = new ChromeDriver(options);

			}else {
				System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
				ChromeOptions options = new ChromeOptions();
				options.addArguments("test-type");
				options.addArguments("--disable-web-security");
				options.addArguments("--disable-site-isolation-trials");
				DesiredCapabilities dr = null;

				dr = DesiredCapabilities.chrome();

				dr.setBrowserName("chrome");

				dr.setPlatform(Platform.IOS);


				dr.setCapability(ChromeOptions.CAPABILITY, options);
				driver = new ChromeDriver();





			}

		} else if (browser == Browsers.FireFox) {
			if (osArch.endsWith("64")) {
				System.setProperty("webdriver.gecko.driver", "drivers\\geckodriver.exe");
			} else {
				
			}
			DesiredCapabilities capabilities = DesiredCapabilities.firefox();
			capabilities.setCapability("marionette", true);
			driver = new FirefoxDriver(capabilities);

			driver.manage().window().maximize();

		}

		
		maximize();
		initialize();
		
		//load application
		openURL(url);
	}
	
	
	private void initialize() {
		actionDriver = new Actions(driver);
		waitDriver = new WebDriverWait(driver, dynamicWait);// By Default			// Dynamic Timeout
		// is 60 Seconds
		parentHandle = driver.getWindowHandle();
		jsDriver = (JavascriptExecutor) this.driver;
	}



	public void openURL(String URL) {
		log.info("Opening URL : " + URL);
		driver.get(URL);
	}

	/**
	 * Return current driver instance
	 * 
	 * @return
	 */
	public static WebDriver getDriver() {
		return driver;
	}

	public void refresh() {
		log.info("Refreshing Page.");
		driver.navigate().refresh();
	}

	public void back() {
		log.info("Navigating To Back");
		driver.navigate().back();
	}

	public void closeChildWindow() {
		driver.close();
		driver.switchTo().window(parentHandle);
	}

	public void maximize() {
		log.info("Maximize window");
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
	}


	public static  void getScreenShot(String screenName) {
		 File src =((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);           
		try {
			System.out.println(System.getProperty("user.dir")+File.separator+"Screenshots"+
		File.separator);
		Files.copy(src, new File(System.getProperty("user.dir")+File.separator+"Screenshots"+
		File.separator+screenName+".png"));                             
		} catch (IOException e)
		 
		{
		  System.out.println(e.getMessage()) ;
		 }
		
		  }
		
	
		

}
