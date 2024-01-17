package page.object;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class LoginPage {
    @AndroidFindBy(id = "etUsername")
    private WebElement usernameField;

    @AndroidFindBy(id = "com.alfabank.qapp:id/etPassword")
    private MobileElement passwordField;

    @AndroidFindBy(id = "com.alfabank.qapp:id/btnConfirm")
    private MobileElement loginButton;

    @AndroidFindBy(xpath = "//android.widget.ImageButton[@content-desc=\"Show password\"]")
    private MobileElement showPassword;

    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"com.alfabank.qapp:id/tvError\"]")
    private MobileElement errorMessage;
// Введены неверные данные

    private AppiumDriver<MobileElement> driver;

    public LoginPage(AppiumDriver<MobileElement> driver) {
        // Используем драйвер из DriverFactory
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
    }

    public void enterUsername(String username) {
        usernameField.sendKeys(username);
    }

    public void enterPassword(String password) {
        passwordField.sendKeys(password);
    }

    public void assertInvalidDataMessage(String password) {
        passwordField.sendKeys(password);
    }

    public HomePage clickLoginButton() {
        loginButton.click();
        return new HomePage(driver);
    }
}

