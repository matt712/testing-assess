package jenkinsTest;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import jenkinsPages.JenkinsLogin;
import jenkinsPages.JenkinsMain;
import jenkinsPages.JenkinsNewItemPage;

public class TestJenkins {

	private WebDriver driver;
	
	@Before
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "C:/Users/Admin/Downloads/chromedriver.exe");
		driver = new ChromeDriver();
	}
	@After
	public void tearDown() {
		driver.quit();
	}
	@Test
	public void testLogInToJenkins() {
		driver.manage().window().maximize();
		driver.get("http://35.197.228.178:8080/");
		JenkinsLogin jenkin = PageFactory.initElements(driver, JenkinsLogin.class);
		JenkinsMain jenkin2 = PageFactory.initElements(driver, JenkinsMain.class);
		jenkin.enterUser();
		jenkin.enterPassword();
		jenkin.hitSubmit();
		assertEquals("img", jenkin2.checkForLogo());
	}
	@Test
	public void testItemCreation() {
		driver.manage().window().maximize();
		driver.get("http://35.197.228.178:8080/");
		JenkinsLogin jenkin = PageFactory.initElements(driver, JenkinsLogin.class);
		JenkinsMain jenkin2 = PageFactory.initElements(driver, JenkinsMain.class);
		JenkinsNewItemPage jenkin3 = PageFactory.initElements(driver, JenkinsNewItemPage.class);
		jenkin.enterUser();
		jenkin.enterPassword();
		jenkin.hitSubmit();
		jenkin2.clickNewItem();
		WebElement elly = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.id("name")));
		elly.sendKeys("hello");
		jenkin3.clickFreeBox();
		jenkin3.clickEntryButton();
		elly = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.id("yui-gen38-button")));
		elly.click();
		elly = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"tasks\"]/div[1]/a[2]")));
		elly.click();
		elly = (new WebDriverWait(driver,10)).until(ExpectedConditions.presenceOfElementLocated(By.linkText("hello")));
		assertEquals("a", elly.getTagName());
	}
}
