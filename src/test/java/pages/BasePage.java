package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {

    protected WebDriver driver;

    public void waitToBeClickableAndClick(WebElement element) throws Error {
        try {
            WebElement el = new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(element));
            el.click();
        } catch (ElementClickInterceptedException exception) {
            // infinite recursion, get rid of it
            waitToBeClickableAndClick(element);
        }
    }

    public void waitForVisibility(WebElement element) throws Error {
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForInvisibilityBy(By locator) throws Error {
        try {
            new WebDriverWait(driver, 3).until(ExpectedConditions.invisibilityOfElementLocated(locator));
        } catch (NoSuchElementException | TimeoutException exception) {}
    }

    public void waitToBePresent(String xpath) throws Error {
        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
    }

    public void waitForJavascriptComplete() {
        new WebDriverWait(driver, 15).until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }

    public void waitUntilUrlContains(String url) {
        new WebDriverWait(driver, 10).until(ExpectedConditions.urlContains(url));
    }
}
