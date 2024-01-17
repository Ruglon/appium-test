package config;

import org.aeonbits.owner.Config;

/**
 * Чтение ключей из test.properties
 */
@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties", //читаем env
        "file:src/test/resources/configs/tests.properties", //читаем из файла
})
public interface TestConfig extends Config {
    @Key("updateScreenshots")
    @DefaultValue("false")
    boolean isScreenshotsNeedToUpdate();

    @Key("deviceHost")
    String deviceHost();

    @Key("login")
    String login();

    @Key("pass")
    String password();

    @Key("illegal_login")
    String illegalLogin();

    @Key("illegal_pass")
    String illegalPass();

    @Key("invalid_symbols_login")
    String invalidSymbolsLogin();

}
