package com.epam.tc.hw3.ex2;

import static org.assertj.core.api.Assertions.assertThat;

import com.epam.tc.hw3.StepsBase;
import com.epam.tc.hw3.ex2.pages.DifferentElementsPage;
import com.epam.tc.hw3.ex2.pages.IndexPage;
import org.openqa.selenium.By;

public class Steps extends StepsBase {
    private IndexPage indexPage;
    private DifferentElementsPage differentElementsPage;

    public Steps(IndexPage indexPage) {
        super(indexPage);
        this.indexPage = indexPage;
    }

    public Steps login(String username, String password) {
        indexPage.login(username, password);
        return this;
    }

    public Steps verifyBrowserTitle() {
        String getActualTitle = indexPage.getTitle();
        assertThat(getActualTitle).isEqualTo("Home Page");
        return this;
    }

    public Steps verifyUsername(String correctUsername) {
        assertThat(indexPage.getUsername().getText()).isEqualTo(correctUsername);
        return this;
    }

    public Steps selectCheckboxes() {
        differentElementsPage = indexPage.goToDifferentPage();
        differentElementsPage.getCheckboxes().stream()
                             .filter(x -> x.getText().equalsIgnoreCase("Water") | x.getText().equalsIgnoreCase("Wind"))
                             .map(x -> x.findElement(By.tagName("input")))
                             .forEach(x -> x.click());
        return this;
    }

    public Steps verifyChbxLogs() {
        assertThat(
            filterElementsByText(differentElementsPage.getLogs(), "Water: condition changed to true")).isNotEmpty();
        assertThat(
            filterElementsByText(differentElementsPage.getLogs(), "Wind: condition changed to true")).isNotEmpty();
        return this;
    }

    public Steps selectRadioBtn() {
        filterElementsByText(differentElementsPage.getRadio(), "Selen")
            .map(x -> x.findElement(By.tagName("input")))
            .findFirst().get().click();
        return this;
    }

    public Steps verifyRadioBtnLogs() {
        assertThat(filterElementsByText(differentElementsPage.getLogs(), "metal: value changed to Selen")).isNotEmpty();
        return this;
    }

    public Steps selectColor() {
        filterElementsByText(differentElementsPage.getColor(), "Yellow")
            .findFirst().get().click();

        return this;
    }

    public Steps verifyColorLogs() {
        assertThat(filterElementsByText(differentElementsPage.getLogs(), "Colors: value changed to Yellow"))
            .isNotEmpty();
        return this;
    }
}
