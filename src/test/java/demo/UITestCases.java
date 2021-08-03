package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class UITestCases {
	WebDriver driver;

	@Test(description = "UIRegister page positive", groups = { "UI_Regression", "UI_Positive_TestCases" })
	public void UIRegisterPage_positive() throws InterruptedException {
		String baseUrl = "https://coindcx.com/signup";
		System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\Drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(baseUrl);
		String testTitle = "CoinDCX - Crypto Exchange | Buy, Sell and Trade Bitcoins & top Altcoins";
		String pageTitle = driver.getTitle();
		Assert.assertEquals(pageTitle, testTitle, "Title are not matched");
		Thread.sleep(30000);
		WebElement txt_firstName = driver.findElement(By.name("firstName"));
		txt_firstName.sendKeys("Bala");
		WebElement txt_lastName = driver.findElement(By.name("lastName"));
		txt_lastName.sendKeys("Shankar");
		WebElement txt_Email = driver.findElement(By.name("email"));
		txt_Email.sendKeys("balashankar.parthipan@gmail.com");
		WebElement txt_password = driver.findElement(By.name("password"));
		txt_password.sendKeys("Password@2021");
		Thread.sleep(10000);
		WebElement txt_password_msg1 = driver.findElement(By.xpath("//p[@class='-c-green ng-star-inserted']"));
		String txt_password_msg = txt_password_msg1.getText();
		Assert.assertEquals(txt_password_msg, "Password strength strong", "Password strength strong are not matched");
		WebElement txt_phone = driver.findElement(By.name("phone"));
		txt_phone.sendKeys("9600661437");
		WebElement a_TermsOfService = driver.findElement(By.xpath("//a[@class='-c-orange -underline']"));
		String termsOfService = a_TermsOfService.getAttribute("href");
		System.out.println("termsOfService" + termsOfService);
		Assert.assertEquals(termsOfService, "https://coindcx.com/assets/pdf/User%20Terms%20and%20Conditions.pdf",
				"Anchor href are not matched");
		new WebDriverWait(driver, 20).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath(
				"//iframe[starts-with(@name, 'a-') and starts-with(@src, 'https://www.google.com/recaptcha')]")));
		new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.id("recaptcha-anchor"))).click();
		driver.switchTo().defaultContent();
		Thread.sleep(60000);
		WebElement btn_Register = driver.findElement(By.xpath("//button[@type='submit' and span='Register']"));
		btn_Register.click();
		String verifyOTPPageTitle = driver.getTitle();
		System.out.println("verifyOTPPageTitle" + verifyOTPPageTitle);
		Assert.assertEquals(verifyOTPPageTitle, "CoinDCX: Sign Up", "Title are not matched");
	}

	@Test(description = "UIRegister page negative", groups = { "UI_Regression", "UI_Negative_TestCases" })
	public void UIRegisterPage_negative() throws InterruptedException {
		String baseUrl = "https://coindcx.com/signup";
		System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\Drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(baseUrl);

		String testTitle = "CoinDCX - Crypto Exchange | Buy, Sell and Trade Bitcoins & top Altcoins";
		String pageTitle = driver.getTitle();
		Assert.assertEquals(pageTitle, testTitle, "Title are not matched");
		Thread.sleep(30000);

		WebElement txt_phone = driver.findElement(By.name("phone"));

		new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.name("firstName"))).click();
		WebElement txt_firstName = driver.findElement(By.name("firstName"));
		txt_firstName.sendKeys("Bala");
		WebElement txt_lastName = driver.findElement(By.name("lastName"));
		txt_lastName.sendKeys("Shankar");
		WebElement txt_Email = driver.findElement(By.name("email"));
		txt_Email.sendKeys("balashankar.parthipangmail.com");
		txt_phone.click();
		WebElement Err_Invaild_mail_id = driver.findElement(By.xpath("//mat-error[text()='Invalid Email ID']"));
		Assert.assertEquals(Err_Invaild_mail_id.getText(), "Invalid Email ID",
				"Email id error message are not matched");
		txt_Email.clear();
		txt_Email.sendKeys("balashankar.parthipan@gmail.com");
		WebElement txt_password = driver.findElement(By.name("password"));
		txt_password.sendKeys("Bala@2021");
		txt_phone.click();
		WebElement Err_Password = driver.findElement(By.xpath("//p[@class='-c-yellow ng-star-inserted']"));
		Assert.assertEquals(Err_Password.getText(), "Password should not contain your name",
				"Password should not contain your name error message are not matched");
		txt_password.clear();
		txt_password.sendKeys("pass");
		txt_phone.click();
		WebElement Err_Password_check = driver
				.findElement(By.xpath("//mat-error[text()='Atleast 1 uppercase, 1 number, 8 characters.']"));
		Assert.assertEquals(Err_Password_check.getText(), "Atleast 1 uppercase, 1 number, 8 characters.",
				"Atleast 1 uppercase, 1 number, 8 characters error message are not matched");
		txt_password.clear();
		txt_password.sendKeys("Password@2021");
		Thread.sleep(10000);
		txt_phone.sendKeys("96006");
		txt_firstName.click();
		WebElement Err_Phonenumber = driver.findElement(By.xpath("//div[span='Invalid phone number']"));
		Assert.assertEquals(Err_Phonenumber.getText(), "Invalid phone number",
				"Invalid phone number error message are not matched");
		txt_phone.clear();
		txt_phone.sendKeys("9600661437");
	}

	@BeforeMethod
	public void beforeMethod() {
		System.out.println("Starting Test On Chrome Browser");
	}

	@AfterMethod
	public void afterMethod() {
		driver.close();
		System.out.println("Finished Test On Chrome Browser");
	}

}
