package com.epam.tc.hw4;

import com.epam.tc.hw4.ex2.Steps;
import com.epam.tc.hw4.driver.DriverManager;
import com.epam.tc.hw4.driver.Wait;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FailedTest {
    WebDriver driver;
    Wait wait;
    Steps steps;

    @BeforeMethod
    public void setUp(ITestContext context) {
        this.driver = DriverManager.setupDriver();
        this.steps = new Steps(driver);
        wait = new Wait(this.driver);
        context.setAttribute("driver", driver);
    }

    @Test(description = "Open Index page")
    @Feature("Main page")
    @Story("Fail")
    public void openIndexPage() {
        steps.fail();
    }

    @AfterMethod
    public static void teardown() {
        DriverManager.closeBrowser();
    }
}
