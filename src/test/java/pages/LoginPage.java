package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage {

    @FindBy(id = "username")
    private WebElement usernameInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(id = "login-submit")
    private WebElement loginSubmit;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public HomePage login(String username, String password) {
        waitToBeClickableAndClick(usernameInput);
        usernameInput.sendKeys(username);
        waitToBeClickableAndClick(loginSubmit);

        waitToBeClickableAndClick(passwordInput);
        passwordInput.sendKeys(password);
        waitToBeClickableAndClick(loginSubmit);
        new WebDriverWait(driver, 10).until(ExpectedConditions.urlContains("atlassian.net/wiki"));

        return new HomePage(driver);
    }

    public void navigateToLogin() {
        driver.get("https://nvyu.atlassian.net/wiki/");
        //todo: waitUntilUrlContains?
        PageFactory.initElements(driver, this);
    }
}
