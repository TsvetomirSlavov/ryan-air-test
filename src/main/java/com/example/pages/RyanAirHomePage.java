package com.example.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class RyanAirHomePage extends RyanAirPage<RyanAirHomePage> {
	
	@FindBy(id = "login_field")
	WebElement loginField;

	@FindBy(name = "password")
	WebElement passwordField;

	@FindBy(name = "commit")
	WebElement commitButton;

	@FindBy(className = "flash-error")
	WebElement errorBox;
	
	@FindBy(id = "myryanair-auth-login")
	WebElement logInButton;
	
	@Override
	protected ExpectedCondition getPageLoadCondition() {
		return ExpectedConditions.titleContains("Official Ryanair website | Cheap flights from Ireland | Ryanair");
	}

	@Override
	public String getPageUrl() {
		return "";
	}
	
	public void getLogInDialog() {
		logInButton.click();
	}
	
	public void login(String login, String password) {
		loginField.sendKeys(login);
		passwordField.sendKeys(password);
		commitButton.click();

	}

	public boolean isLoginError() {
		return errorBox.isDisplayed();
	}

	public String getErrorMessage() {
		return errorBox.getText();
	}

	public RyanAirBookingPage goToLoginPage() {
		return new RyanAirBookingPage().openPage(RyanAirBookingPage.class);
	}

	public RyanAirHomePage open() {
		return new RyanAirHomePage().openPage(RyanAirHomePage.class);
	}

}
