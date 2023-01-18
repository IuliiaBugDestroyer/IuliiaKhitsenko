package com.epam.tc.hw3.pages;

import com.epam.tc.hw3.driver.Waits;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class PageBase {

    protected final WebDriver driver;
    protected Waits waits;
    protected final String url;

    protected PageBase(WebDriver driver, String url) {
        this.driver = driver;
        this.url = url;
        waits = new Waits(driver);
    }

    public void open() {
        driver.get(url);
        init();
    }

    protected void init() {
        PageFactory.initElements(driver, this);
    }

    public String getTitle() {
        return driver.getTitle();
    }
}
