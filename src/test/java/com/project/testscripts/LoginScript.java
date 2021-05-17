package com.project.testscripts;


import com.project.basetest.BaseTest;
import com.project.pages.HomePage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginScript extends BaseTest {

    HomePage homePage;

    @BeforeMethod
    public void loadPage(){
        homePage = new HomePage(driver);
    }

    @Test(enabled = true,priority = 0)
    public void signInToLibertyMutual(){

    homePage.clickOnLogInButton();

    homePage.fillSignForm("linkdevops","April2021").
            clickOnSignLink();

    homePage.clickOnLogOut();
    }


}
