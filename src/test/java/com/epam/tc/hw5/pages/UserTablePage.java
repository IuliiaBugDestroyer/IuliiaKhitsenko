package com.epam.tc.hw5.pages;

import com.epam.tc.hw4.driver.Wait;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UserTablePage {
    private WebDriver driver;
    Wait wait;

    public UserTablePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new Wait(driver);
    }

    public List<WebElement> getNumberTypeDropdowns() {
        return numberTypeDropdowns;
    }

    @FindBy(css = "tbody > tr")
    private List<WebElement> numberTypeDropdowns;

    public List<WebElement> getUsers() {
        return users;
    }

    @FindBy(css = "td > a")
    private List<WebElement> users;

    public List<WebElement> getDescriptions() {
        return descriptions;
    }

    @FindBy(css = ".user-descr")
    private List<WebElement> descriptions;

    public List<WebElement> getCheckboxes() {
        return checkboxes;
    }

    @FindBy(css = "input[type='checkbox']")
    private List<WebElement> checkboxes;

    public String getPageTitle() {
        return driver.getTitle();
    }

    @FindBy(css = "#user-table > tbody > tr")
    private List<WebElement> userTableRows;

    public List<WebElement> getUserTableRows() {
        return userTableRows;
    }

    public List<WebElement> getLogs() {
        return logs;
    }

    @FindBy(css = ".logs li")
    private List<WebElement> logs;
}
