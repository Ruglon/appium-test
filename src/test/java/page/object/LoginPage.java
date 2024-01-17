package page.object;

import helper.WaitHelper;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class LoginPage {
    @AndroidFindBy(id = "etUsername")
    private MobileElement usernameField;

    @AndroidFindBy(id = "com.alfabank.qapp:id/etPassword")
    private MobileElement passwordField;

    @AndroidFindBy(id = "com.alfabank.qapp:id/btnConfirm")
    private MobileElement loginButton;

    @AndroidFindBy(xpath = "//android.widget.ImageButton[@content-desc=\"Show password\"]")
    private MobileElement showPassword;

    @AndroidFindBy(xpath = "driver.findElement(By.xpath(\"//android.widget.TextView[@resource-id='com.alfabank.qapp:id/tvError' and contains(@text, '\" + %s + \"')]\"))")
    private MobileElement errorMessage;
// Введены неверные данные

    private AppiumDriver<MobileElement> driver;
    private WaitHelper waitHelper;

    public LoginPage(AppiumDriver<MobileElement> driver) {
        // Используем драйвер из DriverFactory
        this.driver = driver;
        waitHelper = new WaitHelper(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
    }

    public void enterUsername(String username) {
        usernameField.sendKeys(username);
    }

    public void enterPassword(String password) {
        passwordField.sendKeys(password);
    }

    public void assertInvalidDataMessage(String message) throws InterruptedException {
        String messageActual = String.format("//android.widget.TextView[@resource-id=" +
                "'com.alfabank.qapp:id/tvError' and contains(@text, '%s')]", message);
        try {
            waitHelper.waitForElementToBeVisibleByLocator(By.xpath(messageActual), 15);
        } catch (TimeoutException e){
            System.out.println("Error message for invalid values not displayed");
        }
    }

    public void passwordIsHidden(String password){
        String passwordValue = passwordField.getAttribute(password);

        // Проверяем, что в поле только символы "*"
        Assertions.assertTrue(passwordValue.matches("\\*+"), "Пароль не скрыт символами '*'");

        // Проверяем, что количество символов "*" соответствует длине пароля
        Assertions.assertEquals(password.length(), passwordValue.length(), "Длина скрытого пароля не соответствует длине введенного пароля");

    }

    public void fieldIsEmpty(String field){
        MobileElement element;
        String textEmpty = "";

        switch (field) {
            case "Login":
                element = usernameField;
                textEmpty = element.getText();
                break;
            case "Password":
                element = passwordField;
                textEmpty = element.getText();
                break;
            default:
                throw new IllegalArgumentException("No such element: " + field);
        }

        Assertions.assertTrue(textEmpty.isEmpty(), "Field not empty: " + textEmpty);

    }

    public void clickLoginButton() {
        loginButton.click();;
    }

    public HomePage clickLoginButtonValid() {
        loginButton.click();
        return new HomePage(driver);
    }
}

