package com.epam.tc.hw3.ex2.pages;

import com.epam.tc.hw3.pages.PageBase;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DifferentElementsPage extends PageBase {

    public DifferentElementsPage(WebDriver driver) {
        super(driver, "");
        init();
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
    private WebElement dropdown;

    @FindBy(tagName = "option")
    private List<WebElement> color;

    public void openColorDrop() {
        dropdown.click();
        waits.waitUntilCondition(ExpectedConditions.visibilityOfAllElements(color));
    }
}
