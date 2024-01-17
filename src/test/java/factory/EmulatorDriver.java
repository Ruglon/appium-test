package factory;

import config.ConfigReader;
import helper.ApkInfoHelper;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class EmulatorDriver {
    protected static AppiumDriver driver;
    //чтение пропертей
    private static final String DEVICE_NAME = ConfigReader.emulatorConfig.deviceName();
    private static final String PLATFORM_NAME = ConfigReader.emulatorConfig.platformName();
    private static String APP_PACKAGE = ConfigReader.emulatorConfig.appPackage();
    private static String APP_ACTIVITY = ConfigReader.emulatorConfig.appActivity();
    private static final String APP = ConfigReader.emulatorConfig.app();
    private static final String URLstr = ConfigReader.emulatorConfig.remoteURL();

    /**
     * Валидация URL ссылки из пропертей
     */
    public static URL getUrl() {
        try {
            return new URL(URLstr);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Получаем абсолютный путь от рутового путя
     * @param filePath путь к файлу из корня прокта
     */
    private static String getAbsolutePath(String filePath) {
        File file = new File(filePath);
        assertTrue(file.exists(), filePath + " not found");//проверяем что файл существует

        return file.getAbsolutePath();
    }

    /**
     * Получаем AppPackage и AppActivity из чтения apk файла
     */
    private static void initPackageAndActivity() {
        ApkInfoHelper helper = new ApkInfoHelper();
        //тернарное условие, если app_package не задано в пропертях, достаем из из apk
        APP_PACKAGE = APP_PACKAGE.isEmpty() ? helper.getAppPackageFromApk() : APP_PACKAGE;
        APP_ACTIVITY = APP_ACTIVITY.isEmpty() ? helper.getAppMainActivity() : APP_ACTIVITY;
    }

    public static void startEmulator() throws IOException, InterruptedException {
        String emulatorPath = "C:\\Users\\vital\\AppData\\Local\\Android\\Sdk\\emulator\\emulator";
        String[] command = {emulatorPath, "-avd", DEVICE_NAME};
        ProcessBuilder pb = new ProcessBuilder(command);
        Process process = pb.start();
        process.waitFor();
    }

    /**
     * Создает appium сессиюю AndroidDriver
     *
     * @return
     */
    public static AppiumDriver initializeDriver() {
        initPackageAndActivity();
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS,true);
        desiredCapabilities.setCapability("deviceName", DEVICE_NAME);
        desiredCapabilities.setCapability("platformName", PLATFORM_NAME);

        desiredCapabilities.setCapability("appPackage", APP_PACKAGE);
        desiredCapabilities.setCapability("appActivity", APP_ACTIVITY);

        desiredCapabilities.setCapability("app", getAbsolutePath(APP));
        driver = new AndroidDriver<>(getUrl(), desiredCapabilities);
        return driver;
    }
}
