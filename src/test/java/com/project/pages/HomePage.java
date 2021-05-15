package com.project.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.project.basetest.BaseTest;
import com.testng.framework.Browser;
import com.testng.pages.Pages;

import java.util.List;

public class HomePage extends Pages{

	WebDriver driver;

	public HomePage(WebDriver driver) {
		super(driver);
		this.driver=driver;
	}

	@FindBy(linkText = "Log in")
	private WebElement logInBtn;

	@FindBy(id="policyholderUserName")
	private WebElement userName;

	@FindBy(id = "policyholderPassword")
	private WebElement passwordFiled;

	@FindBy(xpath = "//button[text()='Log in']")
	private WebElement logInFormBtn;

	@FindBy(id = "SignIn")
	private WebElement signBtn;

	@FindBy(id = "dsc-menu-header")
	private WebElement header;

	@FindBy(xpath = "//button[@data-testid='logout']")
	private WebElement logoutBtn;


	public HomePage fillSignForm(String a, String  b){

		waitForElementToLoad(userName);
		enterValue(userName,a,"UserName");
		enterValue(passwordFiled,b,"Password");
		return this;
	}

	public HomePage clickOnLogInButton(){
		waitForElementToLoad(logInBtn);
		//captureScreen("Home page");
		clickOnElement(logInBtn,"Log In");
		return this; }

	public HomePage clickOnLogOut(){
		waitForElementToLoad(logoutBtn);
		clickOnElement(logoutBtn,"LogOut");
		return this;
	}


	public HomePage clickOnSignLink(){
		clickOnElement(logInFormBtn,"log in");
		waitForElementToLoad(header);
		return this;

	}


}
