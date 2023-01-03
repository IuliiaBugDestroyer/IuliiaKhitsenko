package com.epam.tc.hw2.ex1;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ExerciseOne {

    private WebDriver driver;

    @BeforeClass
    void setupTest() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterClass
    void teardown() {
        driver.quit(); //12. Close Browser
    }

    @Test
    public void test() {
        SoftAssert softAssert = new SoftAssert();

        driver.get("https://jdi-testing.github.io/jdi-light/index.html"); //1. Open test site by URL
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
        softAssert.assertEquals(tabs.stream().map(x -> x.getText()).toArray(),
                new String[] {"HOME", "CONTACT FORM", "SERVICE", "METALS & COLORS"});

        //6. Assert that there are 4 images on the Index Page, and they are displayed
        List<WebElement> pict = driver
                .findElements(By.className("icons-benefit"));
        int count = pict.size();
        softAssert.assertEquals(count, 4);

        //7. Assert that there are 4 texts on the Index Page under icons, and they have proper text
        List<WebElement> pictText = driver
                .findElements(By.className("benefit-txt"));
        softAssert.assertEquals(pictText.stream().filter(x -> x.getText() != null).count(), 4);

        WebElement frame = driver
                .findElement(By.id("frame")); //8. Assert that there is the iframe with “Frame Button” exist
        softAssert.assertNotNull(frame);

        driver.switchTo().frame(frame); // 9. Switch to the iframe and check that there is “Frame Button” in the iframe
        WebElement frameBtn = driver.findElement(By.id("frame-button"));
        softAssert.assertNotNull(frameBtn);

        driver.switchTo().parentFrame(); //10. Switch to original window back

        //Assert that there are 5 items in theLeft Sectionare displayed, and they have proper text
        List<WebElement> leftSect = driver
                .findElements(By.xpath("//*[@id=\"mCSB_1_container\"]/ul/li"));
        softAssert.assertEquals(leftSect.stream().map(x -> x.getText())
                .toArray(), new String[] {"Home", "Contact form", "Service", "Metals & Colors", "Elements packs"});

        softAssert.assertAll();
    }

}

