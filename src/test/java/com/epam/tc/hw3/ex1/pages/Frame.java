package com.epam.tc.hw3.ex1.pages;

import com.epam.tc.hw3.driver.Waits;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Frame {
    private WebDriver driver;
    private Waits waits;

    public Frame(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        waits = new Waits(driver);
    }

    @FindBy(id = "frame")
    private WebElement frame;

    @FindBy(id = "frame-button")
    private List<WebElement> frameButton;

    public WebElement getFrameWebElement() {
        return frame;
    }

    public List<WebElement> getFrameBtn() {
        return frameButton;
    }

    public void switchToMain() {
        driver.switchTo().parentFrame();
    }
}
