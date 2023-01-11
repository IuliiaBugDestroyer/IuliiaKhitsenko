package com.epam.tc.hw4.ex1.pages;

import com.epam.tc.hw4.ex1.driver.Wait;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class IndexPage {

    WebDriver driver;
    Wait wait;

    private static final String url = "https://jdi-testing.github.io/jdi-light/index.html";  //1. Open URL

    public IndexPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new Wait(driver);
    }

    @FindBy(id = "user-icon")
    private WebElement loginForm;

    @FindBy(id = "name")
    private WebElement name;

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(className = "fa-sign-in")
    private WebElement loginBtn;

    @FindBy(id  = "user-name")
    private WebElement username;

    @FindBy(xpath = "/html/body/header/div/nav/ul[1]/li/a")
    private List<WebElement> tabs;

    @FindBy(className = "icons-benefit")
    private List<WebElement> pict;

    @FindBy(className = "benefit-txt")
    private List<WebElement> pictText;

    @FindBy(xpath = "//*[@id=\"mCSB_1_container\"]/ul/li")
    private List<WebElement> leftSect;

    public WebElement getUsername() {
        return username;
    }

    public List<WebElement> getTabs() {
        return tabs;
    }

    public List<WebElement> getPict() {
        return pict;
    }

    public List<WebElement> getPictText() {
        return pictText;
    }

    public List<WebElement> getLeftSect() {
        return leftSect;
    }

    public void open() {
        driver.navigate().to(url);
    }

    public void login(String name, String password) {
        loginForm.click();
        this.name.sendKeys(name);
        this.password.sendKeys(password);
        loginBtn.click();
    }
}
