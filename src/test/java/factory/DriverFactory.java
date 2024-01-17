package factory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.URL;

import static config.ConfigReader.emulatorConfig;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DriverFactory {

    private static AppiumDriver<MobileElement> driver;

    private static String getAbsolutePath() {
        File file = new File(emulatorConfig.app());
        assertTrue(file.exists(), emulatorConfig.app() + " not found");

        return file.getAbsolutePath();
    }



    /**
     * ЗАДАЁМ КОНФИГУРАЦИЮ ДРАЙВЕРА
     */
    public static void initializeDriver() {
        try {
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability(MobileCapabilityType.PLATFORM_NAME, emulatorConfig.platformName());
            caps.setCapability(MobileCapabilityType.DEVICE_NAME, emulatorConfig.deviceName());
            caps.setCapability(MobileCapabilityType.APP, getAbsolutePath());

            /**
             * Инициализирует связь между тестами и приложением
             */
            driver = new AndroidDriver<MobileElement>(new URL(emulatorConfig.remoteURL()), caps);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Предоставление доступа к эксземпляру драйвера
     */

    public static AppiumDriver<MobileElement> getDriver() {
        return driver;
    }

    /**
     * Завершение работы драйвера
     */

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}
