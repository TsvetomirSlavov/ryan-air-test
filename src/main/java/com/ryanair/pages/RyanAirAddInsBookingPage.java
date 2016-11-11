package com.ryanair.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import static com.ryanair.setup.RyanAirDriver.getDriver;


public class RyanAirAddInsBookingPage extends RyanAirPage<RyanAirHomePage>{
	
	
	public RyanAirAddInsBookingPage(WebDriver driver){
		super();
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(how= How.CSS, using=".core-btn-primary.core-btn-block.core-btn-medium.btn-text")
	private WebElement continueButton;
	
	@FindBy(how= How.CSS, using=".core-btn-ghost.seat-prompt-popup-footer-btn")
	private WebElement popupButton;
	
	public RyanAirPaymentDetailsPage goToCheckOut(){
		waitForVisibility(continueButton, 10).click();
		waitForVisibility(popupButton, 10).click();
		return new RyanAirPaymentDetailsPage(getDriver());
	}



	@Override
	protected ExpectedCondition getPageLoadCondition() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPageUrl() {
		// TODO Auto-generated method stub
		return null;
	}
	
}