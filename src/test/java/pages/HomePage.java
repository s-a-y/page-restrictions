package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {

        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public PublishedPage navigateToPublishedPage(String url) {
        driver.get(url);
       // waitForVisibility(driver.findElement(By.id("title-text")));
        waitForJavascriptComplete();
        return new PublishedPage(driver);
    }


}
