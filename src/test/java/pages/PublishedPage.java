package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PublishedPage {

    private final WebDriver driver;

    private static String URL_MATCH = "/pages";

    @FindBy(xpath = "//button[@data-test-id='restrictions.dialog.button']")
    private WebElement restrictionsButton;

    @FindBy(id ="title-text")
    private WebElement titleText;

    @FindBy(xpath = "//h2/span[contains(text(),'This is a restricted page')]")
    private WebElement restrictedTitle;



    public PublishedPage(WebDriver driver) {
        if (!driver.getCurrentUrl().contains(URL_MATCH)) {
            throw new IllegalStateException("Unexpected page");
        }

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

    public boolean isRestrictionButtonDisplayed() {

        waitForVisibility(restrictionsButton);
        return restrictionsButton.isDisplayed();
    }

    public String title(){

        new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOf(titleText));
        return titleText.getText();

    }

    public boolean isRestrictedTitleShown(){
        return restrictedTitle.isDisplayed();
    }

    public RestrictionsPopUp clickRestrictionButton() {
        waitForVisibility(restrictionsButton);
        waitToBeClickableAndClick(restrictionsButton);
        return new RestrictionsPopUp(driver);
    }

}
