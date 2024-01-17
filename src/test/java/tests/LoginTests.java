package tests;

import config.ConfigReader;
import factory.DriverFactory;
import factory.EmulatorDriver;
import helper.WaitHelper;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import jdk.jfr.Description;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import page.object.HomePage;
import page.object.LoginPage;

public class LoginTests {

    private AppiumDriver<MobileElement> driver;
    private WaitHelper waitHelper;
    private LoginPage loginPage;
    private HomePage homePage;


    @BeforeEach
    public void setUp() {
        DriverFactory.initializeDriver();
        driver = DriverFactory.getDriver();
        waitHelper = new WaitHelper(driver);
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);

    }

    @AfterEach
    public void quitDriver(){
        DriverFactory.quitDriver();
    }
//
//    @BeforeEach
//    public void startDriver() {
//        step("Открыть приложение", (Allure.ThrowableRunnableVoid));
//    }

    @Description("Login with valid data")
    @Test
    public void testLogin() {
        loginPage.enterUsername(ConfigReader.testConfig.login());
        loginPage.enterPassword(ConfigReader.testConfig.password());
        loginPage.clickLoginButton();
        homePage.checkHomePage();
    }

//    @Description("Login with invalid data")
//    @Test
//    public void testLoginInvalid() {
//        loginPage.enterUsername(ConfigReader.testConfig.illegalLogin());
//        loginPage.enterPassword(ConfigReader.testConfig.password());
//        loginPage.clickLoginButton();
//        homePage.checkHomePage();
//    }

//    @Description("Check invisible password button")
//    @Test
//    public void testInvisibleButton() {
//        loginPage.enterUsername(ConfigReader.testConfig.login());
//        loginPage.enterPassword(ConfigReader.testConfig.password());
//        loginPage.clickLoginButton();
//    }
//
//    @Description("Check Invalid symbols")
//    @Test
//    public void testInvalidSymbols() {
//        loginPage.enterUsername(ConfigReader.testConfig.login());
//        loginPage.enterPassword(ConfigReader.testConfig.password());
//        loginPage.clickLoginButton();
//    }
//
//    @Description("Check message ExceptValue displayed if invalid symbols were pasted")
//    @Test
//    public void testExceptValue() {
//        loginPage.enterUsername(ConfigReader.testConfig.login());
//        loginPage.enterPassword(ConfigReader.testConfig.password());
//        loginPage.clickLoginButton();
//    }

}
