package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

    private final WebDriver driver;

    @FindBy(id = "profileGlobalItem")
    private WebElement profile;

    @FindBy(xpath = "//a[@href='/wiki/logout.action']")
    private WebElement logOutButton;


    public HomePage(WebDriver driver) {

        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void waitToBeClickableAndClick(WebElement element) throws Error{

        WebDriverWait wait= new WebDriverWait(driver,10);
        WebElement el=wait.until(ExpectedConditions.elementToBeClickable(element));
        el.click();

    }

    public void waitForVisibility(WebElement element) throws Error{
        new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOf(element));

    }

    public void logOut(){
        waitToBeClickableAndClick(profile);
        waitToBeClickableAndClick(logOutButton);
        new WebDriverWait(driver,10).until(ExpectedConditions.urlContains("id.atlassian.com/login"));

    }

    public PublishedPage navigateToPublishedPage(String url) {
        driver.get(url);
        waitForVisibility(driver.findElement(By.id("title-text")));
        new WebDriverWait(driver,15).until(webDriver -> ((JavascriptExecutor)webDriver).executeScript("return document.readyState").equals("complete"));
        return new PublishedPage(driver);
    }


}
