package page.object;

import helper.WaitHelper;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Вход в Alfa-Test выполнен\"]")
    private MobileElement homePageWelcome;

    private AppiumDriver<MobileElement> driver;
    private WaitHelper waitHelper;


    public HomePage(AppiumDriver<MobileElement> driver) {
        // Используем драйвер из DriverFactory
        this.driver = driver;
        waitHelper = new WaitHelper(driver);

        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }
    public void checkHomePage() {
        waitHelper.waitForElementToBeVisible(homePageWelcome, 15);
    }
}
