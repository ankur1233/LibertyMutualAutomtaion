package com.project.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.project.basetest.BaseTest;
import com.testng.framework.Browser;
import com.testng.pages.Pages;
import org.openqa.selenium.support.ui.Sleeper;

import java.util.List;

public class HomePage extends Pages{

	WebDriver driver;

	public HomePage(WebDriver driver) {
		super(driver);
		this.driver=driver;
	}


	@FindBy(linkText = "Sign in with One Healthcare ID")
	private WebElement logInBtn;

	@FindBy(id="userNameId_input")
	private WebElement userName;

	@FindBy(id = "passwdId_input")
	private WebElement passwordFiled;

	@FindBy(xpath = "//button[text()='Log in']")
	private WebElement logInFormBtn;

	@FindBy(id = "SignIn")
	private WebElement signBtn;

	@FindBy(id = "dsc-menu-header")
	private WebElement header;

	@FindBy(xpath = "//button[@data-testid='logout']")
	private WebElement logoutBtn;

	@FindBy(id="errorMessage-description")
	private WebElement error;

	/*
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
*/

	public HomePage fillSignForm(String a, String  b){

		waitForElementToLoad(userName);
		enterValue(userName,a,"UserName");
		sleep(2);
		clickOnElement(userName,"UserName");
		sleep(2);
		enterValue(passwordFiled,b,"Password");
		sleep(2);
		clickOnElement(passwordFiled,"Pass");
		sleep(2);
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
		clickOnElement(signBtn,"log in");
		sleep(4);
		if(error.isDisplayed()){
			refreshPage();
			waitForElementToLoad(userName);
			fillSignForm("linkdevops","April2021");
			clickOnElement(signBtn,"log in");
		}
		waitForElementToLoad(header);
		return this;

	}


}
