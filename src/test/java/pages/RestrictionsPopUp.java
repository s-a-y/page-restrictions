package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class RestrictionsPopUp extends BasePage {

    private static String URL_MATCH = "/pages";

    @FindBy(xpath = "//div[@data-test-id='restrictions-value-container']")
    private WebElement editRestrictionsDropDown;

    @FindBy(xpath = "//div[@data-test-id='user-and-group-search']")
    private WebElement restrictionsAddUserField;

    @FindBy(xpath = "//div[@data-test-id='user-and-group-search']/descendant::input")
    private WebElement restrictionsAddUserInputField;


    @FindBy(xpath = "//*[contains(text(),'Can view')]")
    private WebElement editUserPermissionsDropDown;

    @FindBy(id = "react-select-7-input")
    private WebElement canViewOrEditDropdown;

    @FindBy(css= "button.css-mfylpx")
    private WebElement addButton;

    @FindBy(xpath = "//*[contains(text(),'Apply')]")
    private WebElement applyButton;

    @FindBy(xpath = "//*[contains(text(),'Cancel')]")
    private WebElement cancelButton;

    @FindBy(xpath = "//div[@data-test-id='restrictions-dialog-modal']")
    private WebElement restrictionsDialog;



    public RestrictionsPopUp(WebDriver driver) {
        if (!driver.getCurrentUrl().contains(URL_MATCH)) {
            throw new IllegalStateException("Unexpected page");
        }

        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public boolean isEditRestrictionsDropDownDisplayed() {

        waitForVisibility(editRestrictionsDropDown);
        return editRestrictionsDropDown.isDisplayed();
    }

    public boolean isEditUserPermissionsDropDownDisplayed() {
        waitForVisibility(editUserPermissionsDropDown);
        return editUserPermissionsDropDown.isDisplayed();
    }

    public void clickAddButton() {
        addButton.click();
    }


    public PublishedPage clickApplyButton(){
        waitToBeClickableAndClick(applyButton);
       // new WebDriverWait(driver,10).until(ExpectedConditions.invisibilityOf(restrictionsDialog));
        return new PublishedPage(driver);
    }

    public PublishedPage clickCancelButton(){
        waitToBeClickableAndClick(cancelButton);
    //    new WebDriverWait(driver,10).until(ExpectedConditions.invisibilityOf(restrictionsDialog));
        return new PublishedPage(driver);
    }

    public boolean isCancelButtonEnabled() {

        waitForVisibility(cancelButton);
        return cancelButton.isEnabled();
    }

    public PublishedPage setPermissions(String permission) {
        waitForVisibility(editRestrictionsDropDown);
        if (permission.equalsIgnoreCase(editRestrictionsDropDown.getText())) {
            return clickCancelButton();
        }
        else {
            editRestrictionsDropDown.click();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ie) {
            }

            waitToBeClickableAndClick(driver.findElement(By.xpath("//*[contains(text(),'" + permission + "')]")));
            return clickApplyButton();
        }
    }

    public String checkSelectedPermissions(){
        waitForVisibility(editRestrictionsDropDown);
        return editRestrictionsDropDown.getText();
    }

    public PublishedPage setUserRestrictions(String name, String permission, String userPermission){
        waitToBeClickableAndClick(editRestrictionsDropDown);
        waitToBeClickableAndClick(driver.findElement(By.xpath("//*[contains(text(),'" + permission + "')]")));
        waitToBeClickableAndClick(restrictionsAddUserField);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ie) {
        }

        restrictionsAddUserInputField.sendKeys(name);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException ie) {
        }

        restrictionsAddUserInputField.sendKeys(Keys.ENTER);

        waitToBeClickableAndClick(editUserPermissionsDropDown);
        waitToBeClickableAndClick(driver.findElement(By.xpath("//*[contains(text(),'" + userPermission + "')]")));
        clickAddButton();
        return clickApplyButton();
    }

    public boolean isUserAdded( String name) {
        waitToBeClickableAndClick(restrictionsAddUserField);
        WebElement addedUser=driver.findElement(By.xpath("//tr[contains(@class,'TableRow')]//div[contains(text(),'"+name+"')]"));
        return addedUser.isDisplayed();
    }

    public String checkAddedUsePermissions( String name) {

        String className= driver.findElement(By.xpath("//tr[contains(@class,'TableRow')]//div[contains(text(),'"+name+"')]/ancestor::tr[contains(@class,'TableRow')]")).getAttribute("class");

        return driver.findElement(By.xpath("//tr[contains(@class,'"+className+"')]//div[contains(@class,'singleValue')]/span")).getText();
    }
}
