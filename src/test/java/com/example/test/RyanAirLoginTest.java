package com.example.test;

import static com.example.setup.SeleniumDriver.getDriver;
import static org.fest.assertions.api.Assertions.assertThat;

import org.aspectj.lang.annotation.Before;

import io.github.bonigarcia.wdm.MarionetteDriverManager;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.firefox.MarionetteDriver;

import com.example.pages.RyanAirBookingPage;
import com.example.pages.RyanAirHomePage;

public class RyanAirLoginTest {

	@AfterClass
	public static void tearDown() {
		getDriver().close();
	}

	@Test
	public void userCanLogInSuccessfully() throws InterruptedException {
		//given
	 	RyanAirHomePage homePage = new RyanAirHomePage();
		//when
	 	homePage.open();
	 	homePage.getLogInDialog();
	 	Thread.sleep(2L);
	//	loginPage.login("user", "password");
		//then
	}
}
