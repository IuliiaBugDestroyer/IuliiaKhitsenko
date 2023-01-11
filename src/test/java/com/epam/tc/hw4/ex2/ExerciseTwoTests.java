package com.epam.tc.hw4.ex2;

import com.epam.tc.hw4.ex2.driver.DriverManager;
import com.epam.tc.hw4.ex2.driver.Wait;
import io.qameta.allure.Feature;
import io.qameta.allure.Stories;
import io.qameta.allure.Story;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ExerciseTwoTests {


    Wait wait;
    Steps steps;

    @BeforeMethod
    @Feature("Main page")
    @Story("Login")
    public void setUp(ITestContext context) {
        var driver = DriverManager.setupDriver();
        this.steps = new Steps(driver);
        wait = new Wait(driver);
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

    @Test(description = "Select checkboxes, check logs")
    @Feature("Different elements page")
    @Stories(value = {@Story(value = "Elements"), @Story(value = "Logs")})
    public void selectCheckboxes() {
        steps.login("Roman", "Jdi1234");
        steps.selectCheckboxes(); //5. Open Different Elements Page, 6. Select checkboxes
        steps.verifyChbxLogs(); //9. Assert that for each checkbox there is an individual log row
    }

    @Test(description = "Select checkboxes, check logs")
    @Feature("Different elements page")
    @Stories(value = {@Story(value = "Elements"), @Story(value = "Logs")})
    public void selectRadioBtn() {
        steps.login("Roman", "Jdi1234");
        steps.selectRadioBtn(); //7. Select radio Selen
        steps.verifyRadioBtnLogs(); //9. Assert that for radio button there is a log row
    }

    @Test(description = "Select dropdown, check logs")
    @Feature("Different elements page")
    @Stories(value = {@Story(value = "Elements"), @Story(value = "Logs")})
    public void selectColor() {
        steps.login("Roman", "Jdi1234");
        steps.selectColor(); //8. Select in dropdown Yellow
        steps.verifyColorLogs(); //9. Assert that for dropdown there is a log row
    }

    @AfterMethod
    public static void teardown() {
        DriverManager.closeBrowser(); //10. Close Browser
    }
}
