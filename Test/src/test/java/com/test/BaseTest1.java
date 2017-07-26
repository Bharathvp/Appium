package com.test;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.libraries.Log;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class BaseTest1 {
	//WebDriver driver;
	AndroidDriver<MobileElement> driver;
	/*Process p;
	// Set path of your node.exe file.
	// Progra~1 represents Program Files folder.
	String nodePath = "C:/Program Files (x86)/Appium/node.exe";
	// Set path of your appium.js file.
	String appiumJSPath = "C:/Program Files (x86)/Appium/node_modules/appium/bin/appium.js";
	String cmd = nodePath + " " + appiumJSPath;
	// This method Is responsible for starting appium server.
	public void appiumStart() throws IOException, InterruptedException {
		// Execute command string to start appium server.
		p = Runtime.getRuntime().exec(cmd);
		// Provide wait time of 10 mins to start appium server properly.
		// If face any error(Could not start a new session...) then Increase
		// this time to 15 or 20 mins.
		Thread.sleep(20000);
		if (p != null) {
			System.out.println("Appium server Is started now.");
		}
	}
	// This method Is responsible for stopping appium server.
	public void appiumStop() throws IOException {
		if (p != null) {
			p.destroy();
		}
		System.out.println("Appium server Is stopped now.");
	}
	@BeforeTest
	public void launch() throws IOException, InterruptedException{
		appiumStop();
		appiumStart();
	}*/
	@BeforeTest
	public void setUp() throws IOException, InterruptedException {
		//Runtime.getRuntime().exec("cmd /c start C:\\startappium.bat");
		//Thread.sleep(7000L);
		String appPath=System.getProperty("user.dir")+"\\apk\\Tikkle-release-1.3_40_.apk"; 
		DesiredCapabilities capabilities = new DesiredCapabilities();
		//capabilities.setCapability("automationName", "Appium");
		capabilities.setCapability("automationName",ExcelLibrary.getExcelData(".\\Excel\\config.xlsx", "config", 1, 0));
		capabilities.setCapability("deviceName", ExcelLibrary.getExcelData(".\\Excel\\config.xlsx", "config", 1, 1));
		capabilities.setCapability("app", appPath);

		//capabilities.setCapability(CapabilityType.BROWSER_NAME, "Android");

		// Set android VERSION desired capability. Set your mobile device's OS version.
		capabilities.setCapability("platformVersion", ExcelLibrary.getExcelData(".\\Excel\\config.xlsx", "config", 1, 2));

		// Set android platformName desired capability. It's Android in our case here.
		capabilities.setCapability("platformName", ExcelLibrary.getExcelData(".\\Excel\\config.xlsx", "config", 1, 3));

		// Set your application's appPackage if you are using any other app.l
		capabilities.setCapability("appPackage", ExcelLibrary.getExcelData(".\\Excel\\config.xlsx", "config", 1, 4));

		// Set your application's appPackage if you are using any other app.
		capabilities.setCapability("appActivity", ExcelLibrary.getExcelData(".\\Excel\\config.xlsx", "config", 1, 5));

		// It will launch Tikkle app in android device.
		driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	@Test
	public void Sum() {
		String title="";
		if(title.equals(""))
		{
			Log.info("======= Click on NEXT ======");
			driver.findElement(By.id("next")).click();

			Log.info("======= Click on NEXT1 ======");
			driver.findElement(By.name("next")).click();

			Log.info("======= Click on Start ======");
			driver.findElement(By.name("start")).click();

			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

			//driver1.get(ExcelLibrary.getExcelData(".\\Excel\\data.xlsx", "data", 1, 0));

			Log.info("====== Enter Email Id =======");
			System.out.println("val "+ExcelLibrary.getExcelData(".\\Excel\\data.xlsx", "data", 1, 0));
			driver.findElement(By.id("user_email")).sendKeys(ExcelLibrary.getExcelData(".\\Excel\\data.xlsx", "data", 1, 0));

			Log.info("====== Enter Password =======");
			driver.findElement(By.id("user_password")).sendKeys(ExcelLibrary.getExcelData(".\\Excel\\data.xlsx", "data", 1, 1));

			Log.info("======= Click on Login ======");
			driver.findElement(By.id("login_button")).click();



			try {
				Log.info("======= Click on Ok Button ======");
				Thread.sleep(30000);
				driver.findElement(By.name("OK")).click();
			} catch (Exception e1) {
				driver.findElement(By.name("OK")).click();
				e1.printStackTrace();
			}


			//driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			try {
				Log.info("====== Click on More Option ======");
				Thread.sleep(30000);
				//WebDriverWait wait = new WebDriverWait(driver, 10);
				//wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.ImageView[conatins(@content-desc,'More options')]"))).click();
				driver.findElement(By.xpath("//android.widget.ImageView[contains(@content-desc,'More options')]")).click();
			} catch (InterruptedException e) {
				driver.findElement(By.xpath("//android.widget.ImageView[contains(@content-desc,'More options')]")).click();
				e.printStackTrace();
			}


			Log.info("====== Click on Settings =======");
			driver.findElement(By.name("Settings")).click();	

			try {
				scrollTo();
			} catch (IOException e) {
				e.printStackTrace();
			}

			Log.info("==== Clicking on LogOut Button =====");
			driver.findElement(By.id("logout")).click();

		}else {			
			Assert.assertEquals("Tickle not Login", "Tickle Logged in");
		}			 
	}

	public void swipeVertically()
	{		
		Log.info("==== Swipping Vertically ======");
		Dimension size = driver.manage().window().getSize();
		System.out.println(size);

		int starty = (int) ((size.height)*0.80);

		int endy = (int) (size.height * 0.20);

		int startx = size.width / 2;
		System.out.println("starty = " + starty + " ,endy = " + endy + " , startx = " + startx);

		//Swipe from Top to Bottom.
		driver.swipe(startx, endy, startx, starty, 3000);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void scrollTo() throws IOException
	{
		Log.info("==== Scrolling Vertically  ======");
		Dimension dimensions = driver.manage().window().getSize();
		System.out.println("Size of screen= " +dimensions);
		int Startpoint = (int) (dimensions.getHeight() * 0.5);
		System.out.println("Size of scrollStart= " +Startpoint );
		int scrollEnd = (int) (dimensions.getHeight() * 0.2);
		System.out.println("Size of cscrollEnd= " + scrollEnd);             
		driver.swipe(0, Startpoint,0,scrollEnd,1000);    
	}

	@Test(enabled=false)
	public void sample(){
		System.out.println(ExcelLibrary.getExcelData(".\\Excel\\config.xlsx", "config", 1, 0));

	}

	@AfterTest
	public void End() throws IOException {
		driver.quit();
	}
}
