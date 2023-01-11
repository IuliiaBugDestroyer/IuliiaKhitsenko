package com.epam.tc.hw3.ex1.driver;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.function.Function;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Waits {
    private WebDriverWait webDriverWait;

    public Waits(WebDriver webDriver) {
        webDriverWait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
    }

    public Boolean waitUntilCondition(Function<WebDriver, Boolean> p) {
        return webDriverWait.ignoring(NoSuchElementException.class).until(p);
    }
}
