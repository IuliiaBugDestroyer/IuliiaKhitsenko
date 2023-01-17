package com.epam.tc.hw2;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.util.Collection;
import java.util.stream.Stream;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public abstract class ExerciseBase {

    protected final String url = "https://jdi-testing.github.io/jdi-light/index.html";
    protected WebDriver driver;

    @BeforeClass
    public void setupTest() {
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterClass
    public void teardown() {
        //12. Close Browser
        driver.quit();
    }

    protected static Stream<String> selectWebElementsText(Collection<WebElement> webElements) {
        return webElements.stream().map(x -> x.getText());
    }

    protected static Stream<WebElement> filterElementsByText(Collection<WebElement> webElements, String text) {
        return webElements.stream().filter(x -> x.getText().contains(text));
    }

}
