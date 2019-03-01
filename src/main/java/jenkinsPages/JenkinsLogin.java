package jenkinsPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class JenkinsLogin 
{
	@FindBy(id="j_username")
	private WebElement username;
	@FindBy(name="j_password")
	private WebElement password;
	@FindBy(name="Submit")
	private WebElement submit;
	
	public void enterUser() {
		username.sendKeys("admin");
	}
	public void enterPassword() {
		password.sendKeys("admin");
	}
	public void hitSubmit() {
		submit.click();
	}
}
