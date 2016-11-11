package com.example.pages;

import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Page object representing Ryan air's login page.
 */
public class RyanAirBookingPage extends RyanAirPage<RyanAirBookingPage> {

	@Override
	protected ExpectedCondition getPageLoadCondition() {
		return ExpectedConditions.titleContains("Official Ryanair website | Cheap flights from Ireland | Ryanair");
	}

	@Override
	public String getPageUrl() {
		return "/booking/home";
	}

}
