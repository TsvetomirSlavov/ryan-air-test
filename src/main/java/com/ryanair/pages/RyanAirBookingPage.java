package com.ryanair.pages;

import static com.ryanair.setup.RyanAirDriver.getDriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class RyanAirBookingPage extends RyanAirPage<RyanAirHomePage> {
	
	public RyanAirBookingPage(WebDriver driver){
		super();
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(how= How.XPATH, using=".//*[@id='outbound']/div/div[3]/div/flights-table/div[2]/div/div/div[2]/div[1]/div/span")
	private WebElement regularFlight; 
	
	@FindBy(how= How.XPATH, using=".//*[@id='continue']")
	private WebElement button;
	
	
	
	public RyanAirAddInsBookingPage setFlightClass() throws InterruptedException{
		
		waitForVisibility(regularFlight, 10).click();
		regularFlight.click();
		Thread.sleep(5000);
		waitForVisibility(button, 10).click();
		return new RyanAirAddInsBookingPage(getDriver());
	}
	
	@Override
	protected ExpectedCondition getPageLoadCondition() {
		return ExpectedConditions.titleContains("Official Ryanair website | Cheap flights from Ireland | Ryanair");
	}

	@Override
	public String getPageUrl() {
		return "/booking/home";
	}
	

}
