package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    private final WebDriver driver;

    @FindBy(id = "username")
    private WebElement usernameInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(id = "login-submit")
    private WebElement loginSubmit;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForVisibility(WebElement element) throws Error{
        new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOf(element));

    }

    public void waitToBeClickableAndClick(WebElement element) throws Error{

        WebDriverWait wait= new WebDriverWait(driver,10);
        WebElement el=wait.until(ExpectedConditions.elementToBeClickable(element));
        el.click();

    }

    public HomePage login(String username, String password) {
        waitToBeClickableAndClick(usernameInput);
        usernameInput.sendKeys(username);
        waitToBeClickableAndClick(loginSubmit);

        waitToBeClickableAndClick(passwordInput);
        passwordInput.sendKeys(password);
        waitToBeClickableAndClick(loginSubmit);
        new WebDriverWait(driver,10).until(ExpectedConditions.urlContains("atlassian.net/wiki"));

        return new HomePage(driver);
    }

    public void navigateToLogin() {
        driver.get("https://nvyu.atlassian.net/wiki/");
        PageFactory.initElements(driver, this);
    }
}
