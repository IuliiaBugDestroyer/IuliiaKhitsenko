package com.epam.tc.hw3.ex1;

import com.epam.tc.hw3.ex1.driver.DriverManager;
import com.epam.tc.hw3.ex1.driver.Waits;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ExerciseOne {

    WebDriver driver;
    Waits waits;
    private String url = "https://jdi-testing.github.io/jdi-light/index.html";  //1. Open URL

    @BeforeClass
    public void initTest() {
        driver.get(url);
    }

    public ExerciseOne() {
        this.driver = DriverManager.setupDriver();
        PageFactory.initElements(this.driver, this);
        waits = new Waits(this.driver);
    }

    @Test
    public void test() {
        new Steps(this.driver)
                .verifyBrowserTitle() //2. Assert Browser title
                .login("Roman", "Jdi1234") //3. Perform login
                .verifyUsername("ROMAN IOVLEV") //4. Assert Username is loggined
                .verifyFourItems()//5. Assert that there are 4 items on the header section are displayed
                .verifyFourPictures() //6. Assert that there are 4 images on the Index Page
                .verifyPictTexts() //7. Assert that there are 4 texts on the Index Page under icons
                .verifyFrame() //8. Assert that there is the iframe with “Frame Button” exist
                .switchToFrame() // 9. Switch to the iframe and check that there is “Frame Button”
                .verifyFrameBtn()
                .switchToMain() //10. Switch to original window back
                .verifyLeftSect(); //11. Assert that there are 5 items in the Left Section are displayed
    }

    @AfterClass
    public static void teardown() {
        DriverManager.closeBrowser(); //12. Close Browser
    }
}
