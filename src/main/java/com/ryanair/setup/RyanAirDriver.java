package com.ryanair.setup;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RyanAirDriver {

	static WebDriver driver;

	public static WebDriver getDriver() {

		if (driver == null) 
		{
			driver = new FirefoxDriver();
		}
		return driver;
	}
	


}
