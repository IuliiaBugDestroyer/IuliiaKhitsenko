package com.epam.tc.hw3.ex1.pages;

import com.epam.tc.hw3.pages.PageBase;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class IndexPage extends PageBase {

    public IndexPage(WebDriver driver) {
        super(driver, "https://jdi-testing.github.io/jdi-light/index.html");
        frame = new Frame(driver);
    }

    @FindBy(id = "user-icon")
    private WebElement loginForm;

    @FindBy(id = "name")
    private WebElement name;

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(className = "fa-sign-in")
    private WebElement loginButton;

    @FindBy(id = "user-name")
    private WebElement username;

    @FindBy(css = ".m-l8 > li")
    private List<WebElement> tabs;

    @FindBy(className = "icons-benefit")
    private List<WebElement> pictures;

    @FindBy(className = "benefit-txt")
    private List<WebElement> pictText;

    @FindBy(css = ".sidebar-menu > li")
    private List<WebElement> leftSection;

    private Frame frame;

    public WebElement getUsername() {
        return username;
    }

    public List<WebElement> getTabs() {
        return tabs;
    }

    public List<WebElement> getPict() {
        return pictures;
    }

    public List<WebElement> getPictText() {
        return pictText;
    }

    public List<WebElement> getLeftSect() {
        return leftSection;
    }

    public Frame getFrame() {
        return frame;
    }

    public void login(String name, String password) {
        loginForm.click();
        this.name.sendKeys(name);
        this.password.sendKeys(password);
        loginButton.click();
    }

    public void switchTo(WebElement element) {
        driver.switchTo().frame(element);
    }
}
