package com.ryanair.test;

import static com.ryanair.setup.RyanAirDriver.getDriver;
import static org.fest.assertions.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.LinkedHashMap;
import java.util.Map;

import org.aspectj.lang.annotation.Before;

import io.github.bonigarcia.wdm.MarionetteDriverManager;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ryanair.pages.RyanAirAddInsBookingPage;
import com.ryanair.pages.RyanAirBookingPage;
import com.ryanair.pages.RyanAirHomePage;

import com.ryanair.pages.RyanAirPaymentDetailsPage;

public class RyanAirPaymentTest {
	private int day = 20;
	private int month = 01;
	private int year = 2017;
	private int adults = 2;
	private int children=0;
	private Map<String, Integer> date = new LinkedHashMap<String, Integer>();
	private final String ERROR_MESSAGE = "As your payment was not authorised we could not complete your reservation."
									+ " Please ensure that the information was correct or use a new payment to try again";
	private final String ERROR_MESSAGE_2 = "Our systems have detected an identical reservation.";

	@AfterClass
	public static void tearDown() {
		getDriver().close();
	}

	@Test
	public void userCannotBookFlightWithIncorrectPaymentDetailsTest() throws InterruptedException {
		date.put("day", day);
		date.put("month", month);
		date.put("year", year);
		
	 	RyanAirHomePage ryanAirHomePage = new RyanAirHomePage();
	 	RyanAirBookingPage ryanAirBookingPage =  ryanAirHomePage.open().startFlightBooking("Dublin", "Berlin", date, adults, children);
	 	RyanAirAddInsBookingPage addInsBookingPage = ryanAirBookingPage.setFlightClass();
	 	RyanAirPaymentDetailsPage ryanAirPaymentDetailsPage =  addInsBookingPage.goToCheckOut();
	 	ryanAirPaymentDetailsPage.completePayment("5555 5555 5555 5557", "10/18", "265");
	 
	 	assertTrue(ryanAirPaymentDetailsPage.getErrorText().startsWith(ERROR_MESSAGE) || ryanAirPaymentDetailsPage.getErrorText().startsWith(ERROR_MESSAGE_2)); 
	}
}


