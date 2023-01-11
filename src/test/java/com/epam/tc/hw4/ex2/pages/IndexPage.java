package com.epam.tc.hw4.ex2.pages;

import com.epam.tc.hw4.ex2.driver.Wait;
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

    @FindBy(className = "fa-caret-down")
    private WebElement arrow;

    @FindBy(xpath = "//div[@id='mCSB_1']/div[@id='mCSB_1_container']/ul/li[@index='3']/ul/li[@index='8']/a")
    private WebElement diffPage;

    public WebElement getUsername() {
        return username;
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

    public void redirect() {
        arrow.click();
        diffPage.click();
    }
}

