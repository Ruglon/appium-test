package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import config.ConfigReader;
import factory.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.qameta.allure.Description;
import page.object.HomePage;
import page.object.LoginPage;

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

    @Description("Login with valid data")
    @Test
    public void testLogin() {
        loginPage.enterUsername(ConfigReader.testConfig.login());
        loginPage.enterPassword(ConfigReader.testConfig.password());
        loginPage.clickLoginButtonValid();
        homePage.checkHomePage();
    }

    @Description("Sign in with invalid \"Login\" field")
    @Test
    public void testLoginInvalid() throws InterruptedException {
        loginPage.enterUsername(ConfigReader.testConfig.illegalLogin());
        loginPage.enterPassword(ConfigReader.testConfig.password());
        loginPage.clickLoginButton();
        loginPage.assertInvalidDataMessage("Введены неверные данные");
    }

    @Description("Illegal symbols not allowed for \"Login\" field")
    @Test
    public void testIllegalSymbolsForLogin() {
        loginPage.enterUsername(ConfigReader.testConfig.invalidSymbolsLogin());
        loginPage.fieldIsEmpty("Login");
    }

    @Description("Sign in with invalid \"Password\" field")
    @Test
    public void testInvalidPassword() throws InterruptedException {
        loginPage.enterUsername(ConfigReader.testConfig.login());
        loginPage.enterPassword(ConfigReader.testConfig.illegalPass());
        loginPage.clickLoginButton();
        loginPage.assertInvalidDataMessage("Введены неверные данные");
    }

    @Description("Illegal symbols not allowed for \"Password\" field")
    @Test
    public void testIllegalSymbolsForPassword() {
        loginPage.enterPassword(ConfigReader.testConfig.invalidSymbolsLogin());
        loginPage.fieldIsEmpty("Password");
    }

}
