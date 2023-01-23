package com.epam.tc.hw5.pages;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DifferentElementsPage {

    public DifferentElementsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public List<WebElement> getCheckboxes() {
        return checkboxes;
    }

    public List<WebElement> getLogs() {
        return logs;
    }

    public List<WebElement> getRadio() {
        return radio;
    }

    public List<WebElement> getColor() {
        return color;
    }

    @FindBy(className = "label-checkbox")
    private List<WebElement> checkboxes;

    @FindBy(css = ".logs li")
    private List<WebElement> logs;

    @FindBy(className = "label-radio")
    private List<WebElement> radio;

    @FindBy(tagName = "select")
    private WebElement drop;

    @FindBy(tagName = "option")
    private List<WebElement> color;

    public void openColorDrop() {
        drop.click();
    }
}
