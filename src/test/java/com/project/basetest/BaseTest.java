package com.project.basetest;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.testng.framework.Browser;
import com.testng.utils.AppLogger;

public class BaseTest extends Browser {
	
	static Logger log = Logger.getLogger(BaseTest.class);
	
	@BeforeClass
	@Parameters({"BROWSER","URL"})
	public void  setUpApp(@Optional("chrome") String browser, String url) {
		AppLogger.initializeLogger();
		launchBrowser(browser, url);	
	}

	@AfterClass
	public void killApp() {	
		getDriver().quit();
	}
	
	
			
			
			

}
