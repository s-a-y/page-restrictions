package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EditingPage {

    private final WebDriver driver;

    @FindBy(css = ".akEditor .ak-editor-content-area textarea[0]")
    private WebElement pageTitleTextarea;

    @FindBy(id = "publish-button")
    private WebElement publishButton;

    public EditingPage(WebDriver driver) {
//        if (!driver.getCurrentUrl().contains("/pages/edit-v2")) {
//            throw new IllegalStateException("Unexpected page");
//        }

        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public PublishedPage fillAndSave(String name) {
        
        pageTitleTextarea.sendKeys(name);
        publishButton.click();

        return new PublishedPage(driver);
    }
}
