package com.epam.tc.hw3.ex2;

import static org.assertj.core.api.Assertions.assertThat;

import com.epam.tc.hw3.ex2.pages.DifferentElementsPage;
import com.epam.tc.hw3.ex2.pages.IndexPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class Steps {
    private WebDriver driver;
    private IndexPage indexPage;
    private DifferentElementsPage differentElementsPage;

    public Steps(WebDriver driver) {
        this.driver = driver;
        this.indexPage = new IndexPage(this.driver);
        this.differentElementsPage = new DifferentElementsPage(this.driver);
    }

    public Steps login(String username, String password) {
        IndexPage indexPage = PageFactory.initElements(driver, IndexPage.class);
        indexPage.login(username, password);
        return this;
    }

    public Steps verifyBrowserTitle() {
        String getActualTitle = driver.getTitle();
        assertThat(getActualTitle).isEqualTo("Home Page");
        return this;
    }

    public Steps verifyUsername(String correctUsername) {
        assertThat(indexPage.getUsername().getText()).isEqualTo(correctUsername);
        return this;
    }

    public Steps selectCheckboxes() {
        indexPage.redirect();
        differentElementsPage.getCheckboxes().stream()
                .filter(x -> x.getText().equalsIgnoreCase("Water") | x.getText().equalsIgnoreCase("Wind"))
                .map(x -> x.findElement(By.tagName("input")))
                .forEach(x -> x.click());
        return this;
    }

    public Steps verifyChbxLogs() {
        assertThat(differentElementsPage.getLogs().stream()
                .filter(x -> x.getText().contains("Water: condition changed to true")
                        | x.getText().contains("Wind: condition changed to true")))
                .isNotEmpty();
        return this;
    }

    public Steps selectRadioBtn() {
        differentElementsPage.getRadio().stream()
                .filter(x -> x.getText().equalsIgnoreCase("Selen"))
                .map(x -> x.findElement(By.tagName("input")))
                .findFirst().get().click();
        return this;
    }

    public Steps verifyRadioBtnLogs() {
        assertThat(differentElementsPage.getLogs().stream()
                .filter(x -> x.getText().contains("metal: value changed to Selen")))
                .isNotEmpty();
        return this;
    }

    public Steps selectColor() {
        differentElementsPage.openColorDrop();
        differentElementsPage.getColor().stream().filter(x -> x.getText().equalsIgnoreCase("Yellow"))
                .findFirst().get().click();
        return this;
    }

    public Steps verifyColorLogs() {
        assertThat(differentElementsPage.getLogs().stream().filter(x -> x.getText()
                .contains("Colors: value changed to Yellow")))
                .isNotEmpty();
        return this;
    }
}
