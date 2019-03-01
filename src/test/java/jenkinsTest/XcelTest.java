package jenkinsTest;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
import jenkinsPages.JenkinsUserCreate;

public class XcelTest {

	WebDriver driver;
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
	public void testUserCreation() throws IOException, InterruptedException {
		FileInputStream xcelIn = new FileInputStream("C:\\Users\\Admin\\Downloads\\AssessmentFriday.xlsx");
		XSSFWorkbook worker = new XSSFWorkbook(xcelIn);
		XSSFSheet worksheet = worker.getSheetAt(0);
		String[][] anArray = new String[worksheet.getPhysicalNumberOfRows()][6];
		for(int i =0; i<worksheet.getPhysicalNumberOfRows(); i++) {
			for(int j = 0; j<6; j++) {
				if(worksheet.getRow(i).getCell(j) == null) {
					anArray[i][j] = "bloop";
				}else {
				anArray[i][j] = worksheet.getRow(i).getCell(j).toString();
				}
				System.out.println(i + " " + j + ",");
			}
		}
		xcelIn.close();
		driver.manage().window().maximize();
		driver.get("http://35.197.228.178:8080/");
		JenkinsLogin jenkin = PageFactory.initElements(driver, JenkinsLogin.class);
		JenkinsMain jenkin2 = PageFactory.initElements(driver, JenkinsMain.class);
		JenkinsUserCreate jenkin3 = PageFactory.initElements(driver, JenkinsUserCreate.class);
		jenkin.enterUser();
		jenkin.enterPassword();
		jenkin.hitSubmit();
		WebElement elly = (new WebDriverWait(driver,10)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"tasks\"]/div[4]/a[1]/img")));
		elly.click();
		elly = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"main-panel\"]/div[16]/a/img")));
		elly.click();
		for(int i = 1; i<worksheet.getPhysicalNumberOfRows(); i++) {
			elly = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"tasks\"]/div[3]/a[2]")));
			elly.click();
			Thread.sleep(1000);
			jenkin3.enterUsername(anArray[i][0]);
			jenkin3.enterPassword(anArray[i][2]);
			jenkin3.enterName(anArray[i][1]);
			jenkin3.enterEmail(anArray[i][4]);
			jenkin3.clickSubmit();
			Thread.sleep(1000);
			elly = driver.findElement(By.xpath("//*[@id=\"main-panel\"]/h1"));
			anArray[i][5] = "True";
		}
		for(int i = 0; i<worksheet.getPhysicalNumberOfRows(); i++) {
			for(int j=0; j<6; j++) {
				XSSFRow row = worksheet.getRow(i);
				XSSFCell cell = row.getCell(j);
				if(cell == null) {
					cell = row.createCell(j);
				}
				cell.setCellValue(anArray[i][j]);
			}
		}
		FileOutputStream xcelOut = new FileOutputStream("C:\\Users\\Admin\\Documents\\TestResults.xlsx");
		worker.write(xcelOut);
		xcelOut.flush();
		xcelOut.close();
	}

}
