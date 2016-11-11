package com.ryanair.pages;

import static com.ryanair.setup.RyanAirDriver.getDriver;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class RyanAirPage<T> {

	private static final String BASE_URL = "https://www.ryanair.com/ie/en";
	private static final int LOAD_TIMEOUT = 30;
	private static final int REFRESH_RATE = 2;

	public T openPage(Class<T> clazz) {
		T page = PageFactory.initElements(getDriver(), clazz);
		getDriver().get(BASE_URL + getPageUrl());
		ExpectedCondition pageLoadCondition = ((RyanAirPage) page).getPageLoadCondition();
		waitForPageToLoad(pageLoadCondition);
		return page;
	}

	private void waitForPageToLoad(ExpectedCondition pageLoadCondition) {
		Wait wait = new FluentWait(getDriver())
				.withTimeout(LOAD_TIMEOUT, TimeUnit.SECONDS)
				.pollingEvery(REFRESH_RATE, TimeUnit.SECONDS);

		wait.until(pageLoadCondition);
	}
	
	 protected boolean isElementClickable(WebElement element, int timeout) {
	        return new WebDriverWait(getDriver(), timeout)
	                .until(ExpectedConditions.elementToBeClickable(element)) != null;
	    }
	 
	 public void closePopUp(String xpath){
		 getDriver().findElement(By.xpath(xpath)).click();
		 Alert  a= getDriver().switchTo().alert();
		 
		 if(a.getText() == "Continue to search")
		   a.dismiss();
		 else
		   a.accept();
		 
	 }
	
	protected abstract ExpectedCondition getPageLoadCondition();

	public WebElement waitForVisibility(WebElement element, int timeOut){
		return new WebDriverWait(getDriver(), timeOut).until(ExpectedConditions.visibilityOf(element));
	}
	
	public abstract String getPageUrl();
}
