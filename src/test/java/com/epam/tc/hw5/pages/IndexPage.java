package com.epam.tc.hw5.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class IndexPage {

    private WebDriver driver;

    public IndexPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
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

    @FindBy(className = "fa-caret-down")
    private WebElement arrow;

    @FindBy(linkText = "Different elements")
    private WebElement diffPage;
    @FindBy(linkText = "User Table")
    private WebElement userTablePage;

    public void redirectToUserTablePage() {
        arrow.click();
        userTablePage.click();
    }

    public WebElement getUsername() {
        return username;
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public void login(String name, String password) {
        loginForm.click();
        this.name.sendKeys(name);
        this.password.sendKeys(password);
        loginButton.click();
    }

    public void redirectToDifferentElementsPage() {
        arrow.click();
        diffPage.click();
    }
}

