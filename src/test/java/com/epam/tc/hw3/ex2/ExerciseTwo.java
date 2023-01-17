package com.epam.tc.hw3.ex2;

import com.epam.tc.hw3.ExerciseBase;
import com.epam.tc.hw3.ex2.pages.IndexPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class ExerciseTwo extends ExerciseBase<Steps> {

    @Test
    public void test() {
        steps
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

    @Override
    protected Steps createSteps(WebDriver driver) {
        return new Steps(new IndexPage(driver));
    }
}
