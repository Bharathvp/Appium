package com.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class WebBase {
	AndroidDriver<MobileElement> driver;

	@BeforeTest
	public void setUp() throws IOException,MalformedURLException, InterruptedException {

		DesiredCapabilities capabilities;

		capabilities = new DesiredCapabilities();
		capabilities.setCapability("automationName","Appium");
		capabilities.setCapability("platformName","Android");
		capabilities.setCapability("platformVersion","4.2.2");
		capabilities.setCapability("deviceName","QO3C06C02153");
		capabilities.setCapability("browserName","Chrome");
		driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		/*System.out.println("Web Automation");
		driver.get("https://www.google.com");*/
	}

	@Test
	public void work() throws InterruptedException, FileNotFoundException
	{
		System.out.println("Web Automation");
		Thread.sleep(7000);
		//driver.findElement(By.name("Ok")).click();
		//Thread.sleep(7000);
		Properties op = new Properties();
		FileInputStream obj = new FileInputStream(System.getProperty("user.dir")+"\\src\\object_repository\\config.properties");
		driver.get("https://www.google.com");
	}
}
