package com.epam.tc.hw4.ex1;

import com.epam.tc.hw4.ex1.driver.DriverManager;
import com.epam.tc.hw4.ex1.driver.Wait;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ExerciseOneTests {

    WebDriver driver;
    Wait wait;
    Steps steps;

    @BeforeMethod
    @Feature("Main page")
    @Story("Login")
    public void setUp(ITestContext context) {
        this.driver = DriverManager.setupDriver();
        this.steps = new Steps(driver);
        wait = new Wait(this.driver);
        context.setAttribute("driver", driver);
    }

    @Test(description = "Open Index page")
    @Feature("Main page")
    @Story("Login")
    public void openIndexPage() {
        steps.verifyBrowserTitle(); //2. Assert Browser title
    }

    @Test(description = "Login user")
    @Feature("Main page")
    @Story("Login")
    public void login() {
        steps.login("Roman", "Jdi1234"); //3. Perform login
        steps.verifyUsername("ROMAN IOVLEV"); //4. Assert Username is loggined
    }

    @Test(description = "Verify header")
    @Feature("Main page")
    @Story("Login")
    public void verifyHeaderSect() {
        steps.verifyFourItems(); //5. Assert that there are 4 items on the header section are displayed
    }

    @Test(description = "Verify pictures")
    @Feature("Main page")
    @Story("Page content")
    public void verifyFourPicturesSect() {
        steps.verifyFourPictures(); //6. Assert that there are 4 images on the Index Page
    }

    @Test(description = "Verify picture texts")
    @Feature("Main page")
    @Story("Page content")
    public void verifyPictTexts() {
        steps.verifyPictTexts(); //7. Assert that there are 4 texts on the Index Page under icons
    }

    @Test(description = "Verify frame")
    @Feature("Main page")
    @Story("Page content")
    public void verifyFrame() {
        steps.verifyFrame(); //8. Assert that there is the iframe with “Frame Button” exist
    }

    @Test(description = "Switch to frame and check button")
    @Feature("Frame")
    @Story("Frame content")
    public void verifyFrameBtn() {
        steps.switchToFrame(); // 9. Switch to the iframe and check that there is “Frame Button”
        steps.verifyFrameBtn();
    }

    @Test(description = "Switch to main page")
    @Feature("Main page")
    @Story("Page content")
    public void switchToMain() {
        steps.switchToMain(); //10. Switch to original window back
    }

    @Test(description = "Verify left section")
    @Feature("Main page")
    @Story("Page content")
    public void verifyLeftSection() {
        steps.verifyLeftSect(); //11. Assert that there are 5 items in the Left Section are displayed
    }

    @AfterMethod
    public static void teardown() {
        DriverManager.closeBrowser(); //12. Close Browser
    }
}
