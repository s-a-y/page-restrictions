package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage {

    @FindBy(id = "profileGlobalItem")
    private WebElement profile;

  //  @FindBy(xpath = "//a[@href='/wiki/logout.action']")
    @FindBy(xpath = "//*[@id='profileGlobalItem']/div/span/div/span/span")
    private WebElement logOutButton;


    public HomePage(WebDriver driver) {

        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void logOut() {
        waitToBePresent("//*[@id='profileGlobalItem']");
        waitToBeClickableAndClick(profile);
        waitToBePresent("//a[@href='/wiki/logout.action']");
        waitToBeClickableAndClick(logOutButton);

        waitUntilUrlContains("id.atlassian.com/login");
    }

    public PublishedPage navigateToPublishedPage(String url) {
        driver.get(url);
        waitForVisibility(driver.findElement(By.id("title-text")));
        waitForJavascriptComplete();
        return new PublishedPage(driver);
    }


}
