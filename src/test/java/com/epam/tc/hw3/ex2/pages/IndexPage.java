package com.epam.tc.hw3.ex2.pages;

import com.epam.tc.hw3.ex2.driver.Waits;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class IndexPage {
    Waits waits;

    public IndexPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        waits = new Waits(driver);
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
