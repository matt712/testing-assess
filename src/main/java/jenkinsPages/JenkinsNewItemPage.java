package jenkinsPages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class JenkinsNewItemPage {

	private WebDriver driver;
	@FindBy(id="name")
	private WebElement entryBox;
	@FindBy(className="hudson_model_FreeStyleProject")
	private WebElement freestyleBox;
	@FindBy(id="ok-button")
	private WebElement entryButton;
	
	public void enterNameOfItem() {
		entryBox.click();
	}
	public void clickFreeBox() {
		freestyleBox.click();
	}
	public void clickEntryButton() {
		entryButton.click();
	}
}
