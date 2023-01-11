package com.epam.tc.hw3.ex2;

import com.epam.tc.hw3.ex2.driver.DriverManager;
import com.epam.tc.hw3.ex2.driver.Waits;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ExerciseTwo {
    WebDriver driver;
    Waits waits;
    private String url = "https://jdi-testing.github.io/jdi-light/index.html";  //1. Open URL

    @BeforeClass
    public void initTest() {
        driver.get(url);
    }

    public ExerciseTwo() {
        this.driver = DriverManager.setupDriver();
        PageFactory.initElements(this.driver, this);
        waits = new Waits(this.driver);
    }

    @Test
    public void test() {
        new Steps(this.driver)
                .verifyBrowserTitle() //2. Assert Browser title
                .login("Roman", "Jdi1234") //3. Perform login
                .verifyUsername("ROMAN IOVLEV") //4. Assert Username in the left-top side of screen
                .selectCheckboxes() //5. Open Different Elements Page, 6. Select checkboxes
                .verifyChbxLogs() //9. Assert that for each checkbox there is an individual log row
                .selectRadioBtn() //7. Select radio Selen
                .verifyRadioBtnLogs() //9. Assert that for radio button there is a log row
                .selectColor() //8. Select in dropdown Yellow
                .verifyColorLogs(); //9. Assert that for dropdown there is a log row
    }

    @AfterClass
    public static void teardown() {
        DriverManager.closeBrowser(); //10. Close Browser
    }
}
