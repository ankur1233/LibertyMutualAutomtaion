package com.testng.pages;

import org.apache.log4j.Logger;
import org.apache.xmlbeans.impl.piccolo.xml.AttributesHolder;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class Pages  {
	
	static Logger log = Logger.getLogger(Pages.class);
	private WebDriverWait waitDriver;
	private  WebDriver driver;
	public Pages(WebDriver driver) {		
		PageFactory.initElements(driver, this);
	this.driver= driver;
		waitDriver = new WebDriverWait(driver, 30);
	}
	
	// common method for all web appliation


	public void waitForPageTOLoadNu(){
		sleep((long) 3000);
		List<WebElement> ele;
		ele= this.driver.findElements(By.xpath("div[aria-label=\"animation\"]"));
		if(ele.size()>0) {
			this.waitDriver.until(ExpectedConditions.
					invisibilityOf(this.driver.
							findElement(By.xpath("div[aria-label=\"animation\"]"))));
		}
	}


	public void clickOnElement(WebElement ele, String eleName) {
		ele.click();	
		log.info("Click On "+ eleName);
		log.debug(ele);
	}
	

	public void enterValue(WebElement ele, String value,String eleName) {
		ele.sendKeys(value);	
		log.info("Value Enter To Filed "+ eleName);
		log.debug(ele);
	}


	public void waitForElementToLoad(WebElement ele) {
		sleep(3L);
		this.waitDriver.until(ExpectedConditions.visibilityOf(ele));
	}

	public void sleep(Long value){
		try {
			Thread.sleep(value);
		}catch (Exception e){


		}

		}


	public void scrollToElement(WebElement element) {
		JavascriptExecutor jse = (JavascriptExecutor)this.driver;
		// Now scroll to this element
		jse.executeScript("arguments[0].scrollIntoView(true);", element);

	}


	public String  getText(WebElement ele, String eleName) {
		String value =ele.getText();
		log.info("Get value"+ ele.getText());
		return value;
	}
	
	
	public void keyBordEvent(WebElement ele,Keys key) {
		ele.sendKeys(key);	
		log.info("KeyBord Event:");
	}
	
	
		

}
