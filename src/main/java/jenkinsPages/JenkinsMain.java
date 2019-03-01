package jenkinsPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class JenkinsMain {

	@FindBy(id="jenkins-name-icon")
	private WebElement jenkinsLogo;
	@FindBy(linkText="New Item")
	private WebElement NewItemText;
	
	public String checkForLogo() {
		return jenkinsLogo.getTagName();
	}
	public void clickNewItem() {
		NewItemText.click();
	}
}
