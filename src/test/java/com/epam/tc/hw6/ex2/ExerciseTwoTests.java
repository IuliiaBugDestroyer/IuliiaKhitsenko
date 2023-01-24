package com.epam.tc.hw6.ex2;

import com.epam.tc.hw6.driver.DriverFabric;
import io.qameta.allure.Feature;
import io.qameta.allure.Stories;
import io.qameta.allure.Story;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ExerciseTwoTests {

    Steps steps;

    @BeforeMethod
    @Parameters({"isLocal", "hub", "browser"})

    @Feature("Main page")
    @Story("Login")
    public void setUp(@Optional("false") final boolean isLocal,
                      final String hub,
                      final String browser) {
        var driver = DriverFabric.getWebDriver(isLocal, hub, browser);
        this.steps = new Steps(driver);
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
        steps.selectRadioButton(); //7. Select radio Selen
        steps.verifyRadioButtonLogs(); //9. Assert that for radio button there is a log row
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
    public void teardown() {
        this.steps.flush(); //10. Close Browser
    }
}
