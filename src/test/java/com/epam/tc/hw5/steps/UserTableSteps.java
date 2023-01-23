package com.epam.tc.hw5.steps;

import static org.assertj.core.api.Assertions.assertThat;

import com.epam.tc.hw5.common.TestContext;
import com.epam.tc.hw5.models.User;
import com.epam.tc.hw5.pages.IndexPage;
import com.epam.tc.hw5.pages.UserTablePage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class UserTableSteps {

    private IndexPage indexPage;
    private UserTablePage userTablePage;

    public UserTableSteps() {
        indexPage = new IndexPage(TestContext.getInstance().get("driver", WebDriver.class));
        userTablePage = new UserTablePage(TestContext.getInstance().get("driver", WebDriver.class));
    }

    @When("I go to User Table page from Service dropdown")
    public void goToUserTablePage() {
        indexPage.redirectToUserTablePage();
    }

    @Then("{string} page should be opened")
    public void checkPageTitle(String string) {
        assertThat(userTablePage.getPageTitle()).isEqualTo(string);
    }

    @And("{int} Number Type Dropdowns should be displayed on Users Table on User Table Page")
    public void numberTypeDropdownsShouldBeDisplayedOnUsersTableOnUserTablePage(int numberOfDropDowns) {
        assertThat(userTablePage.getNumberTypeDropdowns().size()).isEqualTo(numberOfDropDowns);
    }

    @And("{int} Usernames should be displayed on Users Table on User Table Page")
    public void usernamesShouldBeDisplayedOnUsersTableOnUserTablePage(int numberOfUsers) {
        assertThat(userTablePage.getUsers().size()).isEqualTo(numberOfUsers);
    }

    @And("{int} Description texts under images should be displayed on Users Table on User Table Page")
    public void descriptionTextsUnderImagesShouldBeDisplayedOnUsersTableOnUserTablePage(int numberOfDescriptions) {
        assertThat(userTablePage.getDescriptions().size()).isEqualTo(numberOfDescriptions);
    }

    @And("{int} checkboxes should be displayed on Users Table on User Table Page")
    public void checkboxesShouldBeDisplayedOnUsersTableOnUserTablePage(int numberOfCheckboxes) {
        assertThat(userTablePage.getCheckboxes().size()).isEqualTo(numberOfCheckboxes);
    }

    @And("User table should contain following values:")
    public void userTableShouldContainFollowingValues(List<User> expectedValues) {
        assertThat(getUsersFromUserTable(userTablePage.getUserTableRows())).containsAll(expectedValues);
    }

    @And("droplist should contain values in column Type for user Roman")
    public void droplistShouldContainValuesInColumnTypeForUserRoman(DataTable dropDownValuesDataTable) {
        var expectedDropDownValues = dropDownValuesDataTable.asList();
        expectedDropDownValues = expectedDropDownValues.subList(1, expectedDropDownValues.size() - 1);

        var optionsText = getCellsForUser("Roman").get(1).findElements(By.tagName("option"))
                                                  .stream().map(x -> x.getText()).collect(Collectors.toList());

        assertThat(optionsText).containsAll(expectedDropDownValues);
    }

    @When("I select vip checkbox for {string}")
    public void selectVipCheckboxFor(String string) {
        getCellsForUser(string)
            .get(3)
            .findElement(By.cssSelector("input[type='checkbox']"))
            .click();
    }

    @Then("{int} log row has {string} text in log section")
    public void logRowHasTextInLogSection(int number, String text) {
        //        assertThat(userTablePage.getLogs().size()).isEqualTo(number);
        var logs = userTablePage.getLogs();
        assertThat(logs.get(logs.size() - number).getText()).contains(text);
    }

    private static List<User> getUsersFromUserTable(Collection<WebElement> rowElements) {
        var result = new ArrayList<User>();

        for (var cells : getCellsFromTableRow(rowElements)) {
            var number = Integer.parseInt(cells.get(0).getText());
            var user = cells.get(2).getText();
            var description = cells.get(3).findElement(By.className("user-descr"))
                                   .findElement(By.tagName("span")).getText()
                                  .replaceAll("[\\n\\r\\t\\v]+", " ");

            result.add(new User(number, user, description));
        }
        return result;
    }

    private static List<List<WebElement>> getCellsFromTableRow(Collection<WebElement> rowElements) {
        var result = new ArrayList<List<WebElement>>();
        for (var row : rowElements) {
            result.add(row.findElements(By.tagName("td")));
        }
        return result;
    }

    private List<WebElement> getCellsForUser(String username) {
        return getCellsFromTableRow(userTablePage.getUserTableRows())
            .stream()
            .filter(cells -> cells.get(2).getText()
                                  .equalsIgnoreCase(username))
            .findFirst().get();
    }
}
