package com.ryanair.pages;

import static com.ryanair.helper.FormDataHelper.enterText;
import static com.ryanair.setup.RyanAirDriver.getDriver;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class RyanAirHomePage extends RyanAirPage<RyanAirHomePage> {
	Actions action;
	
	@FindBy(how= How.XPATH, using=".//*[@id='myryanair-auth-login']/a/span")
	private WebElement logIn;
	

	@FindBy(how= How.NAME, using="emailAddress")
	private WebElement username;
	
	@FindBy(how= How.NAME, using="password")
	private WebElement password;
	
	@FindBy(how= How.NAME, using="remember")
	private WebElement remember;
	
	@FindBy(how= How.CSS, using="core-btn-primary")
	private WebElement enter;
	
	@FindBy(how = How.CLASS_NAME, using = "core-input")
	private List<WebElement> departureDestinationFields;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='search-container']/div[1]/div/form/div[1]/div[2]/div/div[1]/div[2]/div[2]/div/div[1]/input")
	private WebElement departureFirstAirport;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='search-container']/div[1]/div/form/div[1]/div[2]/div/div[2]/div[3]/div/div/div[2]/popup-content/core-linked-list/div[2]/div[1]/div[3]/span")
	private WebElement destinationFirstAirport;
									
	@FindBy(how = How.XPATH, using = ".//*[@id='flight-search-type-option-one-way']")
	private WebElement flightsFormOneWayRadio;
	
	@FindBy(how = How.CLASS_NAME, using = "dd")
	private WebElement departureDay;
	
	@FindBy(how = How.CLASS_NAME, using = "mm")
	private WebElement departureMonth;
	
	@FindBy(how = How.CLASS_NAME, using = "yyyy")
	private WebElement departureYear;
	
	@FindBy(how = How.CLASS_NAME, using = "dropdown-handle")
	private WebElement passengersDropdown;
	
	@FindBy(how = How.CSS, using = ".core-btn.dec")
	private List<WebElement> substractPassengersButtons;
	
	@FindBy(how = How.CSS, using = ".core-btn.inc")
	private List<WebElement> addPassengersButtons;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='row-dates-pax']/div[2]/div[3]/div/div/div[2]/popup-content/div[6]/div/div[3]/core-inc-dec/button[2]")
	private WebElement addPassengerButtons;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='search-container']/div[1]/div/form/div[3]/button[2]")
	private WebElement flightsFormSubmitButton;
	
	@FindBy(how = How.CSS, using = ".tpl-homepage.homepage")
	private WebElement loadedPageBody;
	

	String popupButton= ".//*[@id='ngdialog1']/div[2]/promo-banner/div/div/div[2]/div/dialog-body/div/div[2]/div/button";;
	
	
	@Override
	protected ExpectedCondition getPageLoadCondition() {
		return ExpectedConditions.titleContains("Official Ryanair website | Cheap flights from Ireland | Ryanair");
	}

	@Override
	public String getPageUrl() {
		return "";
	}
	
	public RyanAirBookingPage startFlightBooking(String departure, String destination, Map<String, Integer> date, int adult, int child) throws InterruptedException{
		logIn();
		Thread.sleep(5000);
		setOneWayTicket();
		setDestinationAndDepartureDate(departure, destination, date.get("day"), date.get("month"), date.get("year"));
		setPassengers(adult, 0, child);
		flightsFormSubmitButton.click();
		
		Thread.sleep(5000);
		
		return new RyanAirBookingPage(getDriver());
	}
	
	private void waitForPageToLoad(){
		waitForVisibility(loadedPageBody, 10);
	}
	
	public void setOneWayTicket(){	
		flightsFormOneWayRadio.click();
	}	
	
	private void setPassengers(Integer... passenger){
		passengersDropdown.click();
		waitForVisibility(addPassengerButtons, 10);
		if(passenger.length>0){
			if(passenger[0]>1){
				for(int i = passenger[0]-1; i>0; i--){
					addPassengersButtons.get(0).click();
				}
			}
			for(int i=1; i<passenger.length; i++){
				for(int j=passenger[i]; j>0; j--){
					addPassengersButtons.get(i).click();
				}
			}
		}
		passengersDropdown.click();
	}
	
	public void logIn(){
		logIn.click();
		
		
		waitForVisibility(username,10);
		enterText(username, "mrryanair@yahoo.com");
		username.sendKeys(Keys.TAB);
		getDriver().switchTo().activeElement().sendKeys("Password1");
		getDriver().switchTo().activeElement().sendKeys(Keys.ENTER);
		getDriver().switchTo().defaultContent();
		//password.click();
		//password.sendKeys("Password1");
		
		//enter.click();
	}
	
	private void setDestinationAndDepartureDate(String departure, String destination,int day, int month, int year){
		enterText(departureDestinationFields.get(0),departure);
		waitForVisibility(departureFirstAirport, 10).click();
		enterText(departureDestinationFields.get(1),destination);
		departureDestinationFields.get(1).click();
		waitForVisibility(destinationFirstAirport, 10).click();
		waitForVisibility(departureDay, 10);
		enterText(departureDay,Integer.toString(day));
		enterText(departureMonth,Integer.toString(month));
		enterText(departureYear,Integer.toString(year));
	}
	
	public RyanAirHomePage open() {
		return new RyanAirHomePage().openPage(RyanAirHomePage.class);
	}
	

}
