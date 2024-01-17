package helper;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitHelper {

    private AppiumDriver<MobileElement> driver;

    public WaitHelper(AppiumDriver<MobileElement> driver) {
        this.driver = driver;
    }

    public MobileElement waitForElementToBeVisible(MobileElement locator, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        return (MobileElement) wait.until(ExpectedConditions.visibilityOf(locator));
    }

    public MobileElement waitForElementToBeVisibleByLocator(By locator, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        return (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public MobileElement waitForElementToBeClickable(MobileElement locator, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        return (MobileElement) wait.until(ExpectedConditions.visibilityOf(locator));
    }

}
