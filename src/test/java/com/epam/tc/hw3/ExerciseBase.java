package com.epam.tc.hw3;

import com.epam.tc.hw3.driver.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public abstract class ExerciseBase<T extends StepsBase> {

    protected T steps;

    protected abstract T createSteps(WebDriver driver);

    @BeforeClass
    public void setup() {
        steps = createSteps(DriverManager.getDriver());
    }

    @AfterClass
    public void tearDown() {
        DriverManager.closeDriver();
    }
}
