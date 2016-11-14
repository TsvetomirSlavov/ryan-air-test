package com.ryanair.pages;

import static com.ryanair.helper.FormDataHelper.selectFromDropDownByVisibleText;
import static com.ryanair.helper.FormDataHelper.enterText;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RyanAirPaymentDetailsPage extends RyanAirPage<RyanAirPaymentDetailsPage> {
	
	public RyanAirPaymentDetailsPage(WebDriver driver){
		super();
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(how= How.CSS, using=".error")
	private WebElement errorBox;
	
	@FindBy(how= How.CSS, using=".error>.info-text")
	private WebElement errorInfo;
	
	@FindBy(how= How.CSS, using=".tpl-homepage.homepage")
	private WebElement loadedPageBody;
	
	@FindBy(how= How.ID, using="sa.nameAddressLine1")
	private WebElement addressLine1;
	
	@FindBy(how= How.ID, using="sa.nameAddressLine2")
	private WebElement addressLine2;
	
	@FindBy(how= How.ID, using="sa.nameCity")
	private WebElement city;
	
	@FindBy(how= How.ID, using="sa.nameCountry")
	private WebElement country;
	
	@FindBy(how= How.ID, using="sa.namePostcode")
	private WebElement postcode;
	
	@FindBy(how= How.CSS, using="[id^=acceptTerms]")
	private WebElement acceptTerms;
	
	@FindBy(how= How.CSS, using=".core-btn-primary.core-btn-medium")
	private WebElement payButton;
	
	@FindBy(how= How.CSS, using="[id^=title]")
	private List<WebElement> titles;
	
	@FindBy(how= How.CSS, using="[id^=firstName]")
	private List<WebElement> firstNames;
	
	@FindBy(how= How.CSS, using="[id^=lastName]")
	private List<WebElement> lastNames;

	@FindBy(how= How.CSS, using=".desktop-date>.form-field>.core-select>select[name='day']")
	private List<WebElement> childrenDayOfBirth;
	
	@FindBy(how= How.CSS, using=".desktop-date>.form-field>.core-select>select[name='month']")
	private List<WebElement> childrenMonthOfBirth;
	
	@FindBy(how= How.CSS, using=".desktop-date>.form-field>.core-select>select[name='year']")
	private List<WebElement> childrenYearOfBirth;
	
	@FindBy(how= How.XPATH, using="//*[@id=\"checkout\"]/div/form/div[1]/div[2]/div[2]/contact-details-form/div/div[1]/div[3]/div/div[1]/div/select")
	private WebElement phoneCountryCode;
	
	@FindBy(how= How.XPATH, using="//*[@id=\"checkout\"]/div/form/div[1]/div[2]/div[2]/contact-details-form/div/div[1]/div[3]/div/div[2]/input")
	private WebElement phoneNumber;
	
	@FindBy(how= How.CSS, using="[id^=cardNumber]")
	private WebElement cardNumber;
	
	@FindBy(how= How.CSS, using="[id^=cardType]")
	private WebElement cardType;
	
	@FindBy(how= How.CSS, using="[id^=expiryMonth]")
	private WebElement expiryMonth;
	
	@FindBy(how= How.CSS, using=".expiry-year-select")
	private WebElement expiryYear;
	
	@FindBy(how= How.CSS, using=".card-security-code>input")
	private WebElement securityCode;
	
	@FindBy(how= How.CSS, using=".core-input.cardholder")
	private WebElement cardholdersName;
	
	public void completePayment(String cardNumber, String expiryDate, String ccv){
		getPassengers();
		setContactDetails("Ireland", "87123456");
		setPaymentAndBilling(cardNumber, expiryDate, ccv, "MasterCard", "Ryan Air","Cabra", "Castleblayney", "Monaghan", "12-fsd", "Ireland");
		acceptTerms.click();
		payButton.click();
	}
	
	private void waitForPageToLoad(){
		waitForVisibility(loadedPageBody, 10);
	}
	
	public void getPassengers(){		
		waitForVisibility(titles.get(0), 10);
		
		for(WebElement x : titles){
			new Select(x).selectByIndex(1);
		}
		
		for(int i=0; i<firstNames.size(); i++){
			enterText(firstNames.get(i),"MrRaynAir" + String.valueOf(Character.toChars(65+i)));
			enterText(lastNames.get(i),"MRRyanAir" + String.valueOf(Character.toChars(65+i)));
		}
		
		for(int i=0; i<childrenDayOfBirth.size(); i++){
			new Select(childrenDayOfBirth.get(i)).selectByIndex(1);
			new Select(childrenMonthOfBirth.get(i)).selectByIndex(1);
			new Select(childrenYearOfBirth.get(i)).selectByIndex(1);
		}
	}
	
	/**
	 * This method fills contact details with data
	 * 
	 */
	public void setContactDetails(String country, String phoneNr){		
		new Select(phoneCountryCode).selectByVisibleText(country);
		enterText(phoneNumber,phoneNr);
	}
	
	public void setPaymentAndBilling(String cardNumberVal, String expiry, String ccv, String cardProvider, String cardHolder, String address1, String address2, String cityName, String postCode, String countryName){
			
		String cardNumberValue = "";
		
		String [] tab = cardNumberVal.split(" ");
			for(String s : tab){
				cardNumberValue+=s;
			}
			
		enterText(cardNumber,cardNumberValue);
		
		String [] expiryDate = expiry.split("/");
		selectFromDropDownByVisibleText(cardType,cardProvider);
		selectFromDropDownByVisibleText(expiryMonth,expiryDate[0]);
		selectFromDropDownByVisibleText(expiryYear,"20"+expiryDate[1]);
		enterText(securityCode,ccv);
	
		enterText(cardholdersName,cardHolder);
		
		enterText(addressLine1,address1);
		enterText(addressLine2,address2);
		enterText(city,cityName);
		enterText(postcode,postCode);
		selectFromDropDownByVisibleText(country,countryName);
		payButton.click();
		
		
	}
	
	public String getErrorText(){	
		waitForVisibility(errorBox, 10);
		return errorInfo.getText();
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
