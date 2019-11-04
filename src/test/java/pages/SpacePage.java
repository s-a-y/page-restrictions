package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SpacePage extends BasePage {

    @FindBy(linkText = "createGlobalItem")
    private WebElement createButton;

    @FindBy(xpath = "//*[@id=\"create-dialog\"]/div/div[2]/button")
    private WebElement dialogCreateButton;

    @FindBy(css = ".akEditor .ak-editor-content-area textarea[0]")
    private WebElement pageTitleTextarea;

    @FindBy(id = "publish-button")
    private WebElement publishButton;

    public SpacePage(WebDriver driver) {
//        if (!driver.getCurrentUrl().endsWith("/all-updates")) {
//            throw new IllegalStateException("Unexpected page");
//        }

        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public PublishedPage navigateToPublishedPage(String name) {

       /*

        Here should be logic for navigation: it is out of scope.

        */

        driver.get("/wiki/spaces/" + name + "/overview");
        return new PublishedPage(driver);
    }
}
