package com.epam.tc.hw6.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.WebDriverManagerException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;
import lombok.NonNull;
import lombok.experimental.UtilityClass;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.RemoteWebDriver;

@UtilityClass
public class DriverFactory {

    public static WebDriver getWebDriver(final boolean isLocal, final String hub, @NonNull final String browser) {
        WebDriver driver;
        if (isLocal) {
            driver =  WebDriverManager.getInstance(browser).create();
        } else {
            Capabilities caps = getCapabilities(browser);
            URL hubAddress = getUrl(hub);
            driver =  new RemoteWebDriver(hubAddress, caps);
        }

        driver.manage().window().maximize();

        return driver;
    }

    private static URL getUrl(String hub) {
        try {
            return new URL(hub);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Hub URL looks malformed", e);
        }
    }

    private static MutableCapabilities getCapabilities(String browserName) {
        switch (browserName.toLowerCase(Locale.ROOT)) {
            case BrowserType.FIREFOX:
                return new FirefoxOptions();
            case BrowserType.CHROME:
                return new ChromeOptions();
            default:
                String errorMessage = String.format(
                    "The browser name '%s' is not recognized", browserName);
                throw new WebDriverManagerException(errorMessage);
        }
    }
}
