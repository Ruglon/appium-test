package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import config.ConfigReader;
import factory.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import page.object.HomePage;
import page.object.LoginPage;

@Epic("Login Tests Epic")
@Feature("Invalid Login Features")
public class LoginTests {

    private AppiumDriver<MobileElement> driver;
    private LoginPage loginPage;
    private HomePage homePage;


    @BeforeEach
    public void setUp() {
        DriverFactory.initializeDriver();
        driver = DriverFactory.getDriver();
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);

    }

    @AfterEach
    public void quitDriver(){
        DriverFactory.quitDriver();
    }

    @Test
    @Description("Login with valid data")
    public void testLogin() {
        loginPage.enterUsername(ConfigReader.testConfig.login());
        loginPage.enterPassword(ConfigReader.testConfig.password());
        loginPage.clickLoginButtonValid();
        homePage.checkHomePage();
    }

    @Test
    @Description("Sign in with invalid \"Login\" field")
    public void testLoginInvalid() throws InterruptedException {
        loginPage.enterUsername(ConfigReader.testConfig.illegalLogin());
        loginPage.enterPassword(ConfigReader.testConfig.password());
        loginPage.clickLoginButton();
        loginPage.assertInvalidDataMessage("Введены неверные данные");
    }

    @Test
    @Description("Illegal symbols not allowed for \"Login\" field")
    public void testIllegalSymbolsForLogin() {
        loginPage.enterUsername(ConfigReader.testConfig.invalidSymbolsLogin());
        loginPage.fieldIsEmpty("Login");
    }

    @Test
    @Description("Sign in with invalid \"Password\" field")
    public void testInvalidPassword() throws InterruptedException {
        loginPage.enterUsername(ConfigReader.testConfig.login());
        loginPage.enterPassword(ConfigReader.testConfig.illegalPass());
        loginPage.clickLoginButton();
        loginPage.assertInvalidDataMessage("Введены неверные данные");
    }

    @Test
    @Description("Illegal symbols not allowed for \"Password\" field")
    public void testIllegalSymbolsForPassword() {
        loginPage.enterPassword(ConfigReader.testConfig.invalidSymbolsLogin());
        loginPage.fieldIsEmpty("Password");
    }

}
