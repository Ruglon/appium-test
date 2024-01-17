package config;

import org.aeonbits.owner.ConfigFactory;

/**
 * Класс для доступа к данным файлов .properties
 */
public class ConfigReader {

    /**
     * Чтение значений emulator.properties
     */
    public static final EmulatorConfig emulatorConfig = ConfigFactory.create(EmulatorConfig.class, System.getProperties());
    /**
     * Чтение значений  для test.properties
     */
    public static final TestConfig testConfig = ConfigFactory.create(TestConfig.class, System.getProperties());
}