package jenkinsPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class JenkinsUserCreate {

	@FindBy(id="username")
	private WebElement userBox;
	@FindBy(name="password1")
	private WebElement passbox1;
	@FindBy(name="password2")
	private WebElement passbox2;
	@FindBy(name="fullname")
	private WebElement nameBox;
	@FindBy(name="email")
	private WebElement mailbox;
	@FindBy(id="yui-gen1-button")
	private WebElement button;
	
	public void enterUsername(String string) {
		userBox.sendKeys(string);
	}
	public void enterPassword(String string) {
		passbox1.sendKeys(string);
		passbox2.sendKeys(string);
	}
	public void enterName(String str) {
		nameBox.sendKeys(str);
	}
	public void enterEmail(String str) {
		mailbox.sendKeys(str);
	}
	public void clickSubmit() {
		button.click();
	}
}
