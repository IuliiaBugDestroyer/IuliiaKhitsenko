package com.epam.tc.hw2.ex1;

import com.epam.tc.hw2.ExerciseBase;
import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ExerciseOne extends ExerciseBase {

    @Test
    public void test() {
        SoftAssert softAssert = new SoftAssert();

        driver.get(url); //1. Open test site by URL
        String getActualTitle = driver.getTitle();

        softAssert.assertEquals(getActualTitle, "Home Page"); //2. Assert Browser title

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

        WebElement username = driver.findElement(By.id("user-name")); //4. Assert Username is loggined
        softAssert.assertEquals(username.getText(), "ROMAN IOVLEV");

        //5. Assert that there are 4 items on the header section are displayed, and they have proper texts
        List<WebElement> tabs = driver
                .findElements(By.cssSelector(".m-l8 > li"));

        var tabsText = selectWebElementsText(tabs).collect(Collectors.toList());
        var expectedTabsText = List.of("HOME", "CONTACT FORM", "SERVICE", "METALS & COLORS");
        softAssert.assertEquals(tabsText, expectedTabsText);

        //6. Assert that there are 4 images on the Index Page, and they are displayed
        List<WebElement> pict = driver
                .findElements(By.className("icons-benefit"));
        int count = pict.size();
        softAssert.assertEquals(count, 4);

        //7. Assert that there are 4 texts on the Index Page under icons, and they have proper text
        List<WebElement> iconsTextsElements = driver
                .findElements(By.className("benefit-txt"));
        softAssert.assertEquals(iconsTextsElements.size(), 4);

        //8. Assert that there is the iframe with “Frame Button” exist
        WebElement frame = driver.findElement(By.id("frame"));
        softAssert.assertNotNull(frame);

        // 9. Switch to the iframe and check that there is “Frame Button” in the iframe
        driver.switchTo().frame(frame);
        WebElement frameButton = driver.findElement(By.id("frame-button"));
        softAssert.assertNotNull(frameButton);

        //10. Switch to original window back
        driver.switchTo().parentFrame();

        //Assert that there are 5 items in theLeft Section are displayed, and they have proper text
        List<WebElement> leftSectionElements = driver.findElements(By.cssSelector(".sidebar-menu > li"));

        var leftSectionElementsTexts = selectWebElementsText(leftSectionElements).collect(Collectors.toList());
        var expectedElements = List.of("Home", "Contact form", "Service", "Metals & Colors", "Elements packs");

        softAssert.assertEquals(leftSectionElementsTexts, expectedElements);

        softAssert.assertAll();
    }
}

