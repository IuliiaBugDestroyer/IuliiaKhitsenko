package com.epam.tc.hw3.ex1.pages;

import com.epam.tc.hw3.ex1.driver.Waits;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class IndexPage {

    WebDriver driver;
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

    @FindBy(css = ".m-l8 > li")
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

    public void login(String name, String password) {
        loginForm.click();
        this.name.sendKeys(name);
        this.password.sendKeys(password);
        loginBtn.click();
    }
}
