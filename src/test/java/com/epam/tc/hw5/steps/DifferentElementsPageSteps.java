package com.epam.tc.hw5.steps;

import static org.assertj.core.api.Assertions.assertThat;

import com.epam.tc.hw5.common.TestContext;
import com.epam.tc.hw5.pages.DifferentElementsPage;
import com.epam.tc.hw5.pages.IndexPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.util.Collection;
import java.util.function.Predicate;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DifferentElementsPageSteps {

    private DifferentElementsPage differentElementsPage;
    private IndexPage indexPage;

    public DifferentElementsPageSteps() {
        differentElementsPage = new DifferentElementsPage(TestContext.getInstance().get("driver", WebDriver.class));
        indexPage = new IndexPage(TestContext.getInstance().get("driver", WebDriver.class));
    }

    @Given("I open JDI GitHub site")
    public void openUrl() {
        TestContext.getInstance().get("driver", WebDriver.class)
                   .get("https://jdi-testing.github.io/jdi-light/index.html");
        assertThat(indexPage.getPageTitle()).isEqualTo("Home Page");
    }

    @Then("I login with the username {string} and password {string}")
    public void login(String name, String password) {
        indexPage.login(name, password);
        assertThat(indexPage.getUsername().getText()).isEqualTo("ROMAN IOVLEV");
    }

    @When("I open Different Elements Page from Service dropdown in header")
    public void redirectToDiffPage() {
        indexPage.redirectToDifferentElementsPage();
    }

    @And("I click on {string} and {string} checkboxes")
    public void clickOnCheckboxes(String string, String string2) {
        Predicate<WebElement> predicate =
            (s) -> s.getText().equalsIgnoreCase(string) | s.getText().equalsIgnoreCase(string2);
        differentElementsPage.getCheckboxes().stream()
                             .filter(predicate)
                             .forEach(x -> x.click());
    }

    @And("I click on {string} radiobutton")
    public void selectRadiobutton(String radiobtn) {
        differentElementsPage.getRadio().stream()
                             .filter(s -> s.getText().equalsIgnoreCase(radiobtn))
                             .findFirst().get().click();
    }

    @And("I select {string} color in dropdown")
    public void selectColorInDropdown(String color) {
        differentElementsPage.openColorDrop();
        differentElementsPage.getColor().stream()
                             .filter(s -> s.getText().equalsIgnoreCase(color))
                             .findFirst().get().click();
    }

    @Then("Checkbox Water should be selected and logged")
    public void checkboxWaterAndIsCorrespondingToSelected() {
        assertThat(logsContainsEntry(differentElementsPage.getLogs(), "Water: condition changed to true")).isTrue();
    }

    @And("Checkbox Wind should be selected and logged")
    public void checkboxWindIsCorrespondingToSelected() {
        assertThat(logsContainsEntry(differentElementsPage.getLogs(), "Wind: condition changed to true")).isTrue();
    }

    @And("Radiobutton Selen should be selected and logged")
    public void radioButtonSelenIsCorrespondingToSelected() {
        assertThat(logsContainsEntry(differentElementsPage.getLogs(), "metal: value changed to Selen")).isTrue();
    }

    @And("Dropdown Yellow should be selected and logged")
    public void dropdownYellowIsCorrespondingToSelected() {
        assertThat(logsContainsEntry(differentElementsPage.getLogs(), "Colors: value changed to Yellow")).isTrue();
    }

    private static boolean logsContainsEntry(Collection<WebElement> logs, String expectedEntry) {
        for (var logElement : logs) {
            if (logElement.getText().contains(expectedEntry)) {
                return true;
            }
        }
        return false;
    }
}
