package com.epam.tc.hw3.ex1;

import com.epam.tc.hw3.ExerciseBase;
import com.epam.tc.hw3.ex1.pages.IndexPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class ExerciseOne extends ExerciseBase<ExerciseOneSteps> {

    @Test
    public void test() {
        steps
                .verifyBrowserTitle() //2. Assert Browser title
                .login("Roman", "Jdi1234") //3. Perform login
                .verifyUsername("ROMAN IOVLEV") //4. Assert Username is loggined
                .verifyFourItems()//5. Assert that there are 4 items on the header section are displayed
                .verifyFourPictures() //6. Assert that there are 4 images on the Index Page
                .verifyPictTexts() //7. Assert that there are 4 texts on the Index Page under icons
                .verifyFrame() //8. Assert that there is the iframe with “Frame Button” exist
                .switchToFrame() // 9. Switch to the iframe and check that there is “Frame Button”
                .verifyFrameButton()
                .switchToMain() //10. Switch to original window back
                .verifyLeftSection(); //11. Assert that there are 5 items in the Left Section are displayed
    }

    @Override
    protected ExerciseOneSteps createSteps(WebDriver driver) {
        return new ExerciseOneSteps(new IndexPage(driver));
    }
}
