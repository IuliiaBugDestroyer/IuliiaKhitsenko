package com.epam.tc.hw4.ex1.pages;

import com.epam.tc.hw3.driver.Waits;
import com.epam.tc.hw4.driver.Wait;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Frame {

    private WebDriver driver;
    Wait wait;

    public Frame(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new Wait(driver);
    }

    @FindBy(id = "frame")
    private WebElement frame;

    @FindBy(id = "frame-button")
    private List<WebElement> frameButton;

    public WebElement getFrame() {
        return frame;
    }

    public List<WebElement> getFrameButton() {
        return frameButton;
    }
}
