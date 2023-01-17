package com.epam.tc.hw3.ex2.pages;

import com.epam.tc.hw3.pages.PageBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class IndexPage extends PageBase {

    public IndexPage(WebDriver driver) {
        super(driver, "https://jdi-testing.github.io/jdi-light/index.html");
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

    @FindBy(linkText = "Different elements")
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

    public DifferentElementsPage goToDifferentPage() {
        arrow.click();
        diffPage.click();

        return new DifferentElementsPage(driver);
    }
}
