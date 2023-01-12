package com.epam.tc.hw2.ex2;

import static org.assertj.core.api.Assertions.assertThat;

import com.epam.tc.hw2.ExerciseBase;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class ExerciseTwo extends ExerciseBase {

    @Test
    public void test() {

        driver.get(url); //1. Open test site by URL

        String getActualTitle = driver.getTitle();
        assertThat(getActualTitle).isEqualTo("Home Page"); //2. Assert Browser title

        WebElement login = driver.findElement(By.className("navbar-right"))
                .findElement(By.tagName("li")).findElement(By.tagName("a")); //3. Perform login
        login.click();

        WebElement name = driver.findElement(By.id("name"));
        name.click();
        name.sendKeys("Roman");

        WebElement pass = driver.findElement(By.id("password"));
        pass.click();
        pass.sendKeys("Jdi1234");

        WebElement enter = driver.findElement(By.className("fa-sign-in"));
        enter.click();

        //4. Assert Username in the left-top side of screen that user is loggined
        WebElement username = driver.findElement(By.id("user-name"));
        assertThat(username.getText()).isEqualTo("ROMAN IOVLEV");

        //5. Open through the header menu Service -> Different Elements Page
        driver.findElement(By.className("fa-caret-down")).click();

        driver.findElement(By.linkText("Different elements")).click();

        //6. Select checkboxes
        List<WebElement> check = driver.findElements(By.className("label-checkbox"));
        check.stream()
                .filter(x -> x.getText().equalsIgnoreCase("Water") | x.getText().equalsIgnoreCase("Wind"))
                .map(x -> x.findElement(By.tagName("input")))
                .forEach(x -> x.click());

        //9. Assert that for each checkbox there is an individual log row
        // and value is corresponded to the status of checkbox
        List<WebElement> logsChbx = driver.findElement(By.className("panel-body-list")).findElements(By.tagName("li"));
        assertThat(logsChbx.stream()
                .filter(x -> x.getText().contains("Water: condition changed to true")
                        | x.getText().contains("Wind: condition changed to true")))
                .isNotEmpty();

        List<WebElement> radio = driver.findElements(By.className("label-radio")); //7. Select radio Selen
        radio.stream()
                .filter(x -> x.getText().equalsIgnoreCase("Selen"))
                .map(x -> x.findElement(By.tagName("input")))
                .findFirst().get().click();
        //9. Assert that for radio button there is a log row and value is corresponded to the status of radio button
        List<WebElement> logsRadio = driver.findElement(By.className("panel-body-list")).findElements(By.tagName("li"));
        assertThat(logsRadio.stream().filter(x -> x.getText().contains("metal: value changed to Selen"))).isNotEmpty();

        //8. Select in dropdown Yellow
        WebElement drop = driver.findElement(By.tagName("select"));
        drop.click();
        List<WebElement> colors = drop.findElements(By.tagName("option"));
        colors.stream().filter(x -> x.getText().equalsIgnoreCase("Yellow"))
                .findFirst().get().click();
        //9. Assert that for dropdown there is a log row and value is corresponded to the selected value.
        List<WebElement> logsDrop = driver.findElement(By.className("panel-body-list")).findElements(By.tagName("li"));
        assertThat(logsDrop.stream().filter(x -> x.getText()
                .contains("Colors: value changed to Yellow"))).isNotEmpty();

    }
}
