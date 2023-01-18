package com.epam.tc.hw4.driver;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.function.Function;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Wait {
    private WebDriverWait webDriverWait;

    public Wait(WebDriver webDriver) {
        webDriverWait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
    }

    public Boolean waitUntilCondition(Function<WebDriver, Boolean> p) {
        return webDriverWait.ignoring(NoSuchElementException.class).until(p);
    }
}
