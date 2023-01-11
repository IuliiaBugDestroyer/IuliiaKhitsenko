package com.epam.tc.hw4.ex2;

import static org.assertj.core.api.Assertions.assertThat;

import com.epam.tc.hw4.ex2.pages.DifferentElementsPage;
import com.epam.tc.hw4.ex2.pages.IndexPage;
import io.qameta.allure.Step;
import org.assertj.core.api.Fail;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Steps {
    private WebDriver driver;
    private IndexPage indexPage;
    private DifferentElementsPage differentElementsPage;

    public Steps(WebDriver driver) {
        this.driver = driver;
        this.indexPage = new IndexPage(this.driver);
        indexPage.open();
        this.differentElementsPage = new DifferentElementsPage(this.driver);
    }

    @Step("User login")
    public void login(String username, String password) {
        indexPage.login(username, password);
    }

    @Step("Verify Home Page title")
    public void verifyBrowserTitle() {
        String getActualTitle = driver.getTitle();
        assertThat(getActualTitle).isEqualTo("Home Page");
    }

    @Step("Verify username")
    public void verifyUsername(String correctUsername) {
        assertThat(indexPage.getUsername().getText()).isEqualTo(correctUsername);
    }

    @Step("Open Different elements page")
    public void selectCheckboxes() {
        indexPage.redirect();
        differentElementsPage.getCheckboxes().stream()
                .filter(x -> x.getText().equalsIgnoreCase("Water") | x.getText().equalsIgnoreCase("Wind"))
                .map(x -> x.findElement(By.tagName("input")))
                .forEach(x -> x.click());
    }

    public void verifyChbxLogs() {
        assertThat(differentElementsPage.getLogs().stream()
                .filter(x -> x.getText().contains("Water: condition changed to true")
                        | x.getText().contains("Wind: condition changed to true")))
                .isNotEmpty();
    }

    public void selectRadioBtn() {
        indexPage.redirect();
        differentElementsPage.getRadio().stream()
                .filter(x -> x.getText().equalsIgnoreCase("Selen"))
                .map(x -> x.findElement(By.tagName("input")))
                .findFirst().get().click();
    }

    public void verifyRadioBtnLogs() {
        assertThat(differentElementsPage.getLogs().stream()
                .filter(x -> x.getText().contains("metal: value changed to Selen")))
                .isNotEmpty();
    }

    public void selectColor() {
        indexPage.redirect();
        differentElementsPage.openColorDrop();
        differentElementsPage.getColor().stream().filter(x -> x.getText().equalsIgnoreCase("Yellow"))
                .findFirst().get().click();
    }

    public void verifyColorLogs() {
        assertThat(differentElementsPage.getLogs().stream().filter(x -> x.getText()
                .contains("Colors: value changed to Yellow")))
                .isNotEmpty();
    }

    public void fail() {
        Fail.fail("Expected failure");
    }
}
