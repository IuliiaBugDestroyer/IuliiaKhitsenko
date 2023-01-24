package com.epam.tc.hw6.ex2;

import static org.assertj.core.api.Assertions.assertThat;

import com.epam.tc.hw6.StepsBase;
import com.epam.tc.hw6.ex2.pages.DifferentElementsPage;
import com.epam.tc.hw6.ex2.pages.IndexPage;
import io.qameta.allure.Step;
import java.util.function.Predicate;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Steps extends StepsBase {
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
        indexPage.redirectToDifferentElementsPage();
        Predicate<WebElement> predicate =
            (s) -> s.getText().equalsIgnoreCase("Water") | s.getText().equalsIgnoreCase("Wind");
        differentElementsPage.getCheckboxes().stream()
                             .filter(predicate)
                             .map(x -> x.findElement(By.tagName("input")))
                             .forEach(x -> x.click());
    }

    public void verifyChbxLogs() {
        assertThat(
            filterElementsByText(differentElementsPage.getLogs(), "Water: condition changed to true")).isNotEmpty();
        assertThat(
            filterElementsByText(differentElementsPage.getLogs(), "Wind: condition changed to true")).isNotEmpty();
    }

    public void selectRadioButton() {
        indexPage.redirectToDifferentElementsPage();
        filterElementsByText(differentElementsPage.getRadio(), "Selen")
            .map(x -> x.findElement(By.tagName("input")))
            .findFirst().get().click();
    }

    public void verifyRadioButtonLogs() {
        assertThat(filterElementsByText(differentElementsPage.getLogs(), "metal: value changed to Selen")).isNotEmpty();
    }

    public void selectColor() {
        indexPage.redirectToDifferentElementsPage();
        differentElementsPage.openColorDrop();
        filterElementsByText(differentElementsPage.getColor(), "Yellow")
            .findFirst().get().click();
    }

    public void verifyColorLogs() {
        assertThat(filterElementsByText(differentElementsPage.getLogs(), "Colors: value changed to Yellow"))
            .isNotEmpty();
    }

    @Override
    public void flush() {
        this.driver.quit();
    }
}
