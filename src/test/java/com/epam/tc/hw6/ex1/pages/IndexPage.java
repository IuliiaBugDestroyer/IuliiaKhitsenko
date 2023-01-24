package com.epam.tc.hw6.ex1.pages;

import com.epam.tc.hw6.driver.Wait;
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
    private WebElement loginButton;

    @FindBy(id  = "user-name")
    private WebElement username;

    @FindBy(css = ".m-l8 > li")
    private List<WebElement> tabs;

    @FindBy(className = "icons-benefit")
    private List<WebElement> pictures;

    @FindBy(className = "benefit-txt")
    private List<WebElement> pictText;

    @FindBy(css = ".sidebar-menu > li")
    private List<WebElement> leftSection;

    public WebElement getUsername() {
        return username;
    }

    public List<WebElement> getTabs() {
        return tabs;
    }

    public List<WebElement> getPictures() {
        return pictures;
    }

    public List<WebElement> getPictText() {
        return pictText;
    }

    public List<WebElement> getLeftSection() {
        return leftSection;
    }

    public void open() {
        driver.navigate().to(url);
    }

    public void login(String name, String password) {
        loginForm.click();
        this.name.sendKeys(name);
        this.password.sendKeys(password);
        loginButton.click();
    }
}
