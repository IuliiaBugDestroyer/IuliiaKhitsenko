package com.epam.tc.hw3.ex1;

import static org.assertj.core.api.Assertions.assertThat;

import com.epam.tc.hw3.StepsBase;
import com.epam.tc.hw3.ex1.pages.IndexPage;
import java.util.List;

public class ExerciseOneSteps extends StepsBase {
    private IndexPage indexPage;

    public ExerciseOneSteps(IndexPage page) {
        super(page);

        this.indexPage = page;
    }

    public ExerciseOneSteps login(String username, String password) {
        indexPage.login(username, password);
        return this;
    }

    public ExerciseOneSteps verifyBrowserTitle() {
        String getActualTitle = indexPage.getTitle();
        assertThat(getActualTitle).isEqualTo("Home Page");
        return this;
    }

    public ExerciseOneSteps verifyUsername(String correctUsername) {
        assertThat(indexPage.getUsername().getText()).isEqualTo(correctUsername);
        return this;
    }

    public ExerciseOneSteps verifyFourItems() {
        assertThat(selectWebElementsText(indexPage.getTabs()))
            .isEqualTo(List.of("HOME", "CONTACT FORM", "SERVICE", "METALS & COLORS"));
        return this;
    }

    public ExerciseOneSteps verifyFourPictures() {
        int count = indexPage.getPict().size();
        assertThat(count).isEqualTo(4);
        return this;
    }

    public ExerciseOneSteps verifyPictTexts() {
        assertThat(selectWebElementsText(indexPage.getPictText())
            .count()).isEqualTo(4);
        return this;
    }

    public ExerciseOneSteps verifyLeftSection() {
        assertThat(selectWebElementsText(indexPage.getLeftSect()))
            .isEqualTo(List.of("Home", "Contact form", "Service", "Metals & Colors", "Elements packs"));
        return this;
    }

    public ExerciseOneSteps verifyFrame() {
        assertThat(indexPage.getFrame().getFrameWebElement()).isNotNull();
        return this;
    }

    public ExerciseOneSteps switchToFrame() {
        indexPage.switchTo(indexPage.getFrame().getFrameWebElement());
        return this;
    }

    public ExerciseOneSteps switchToMain() {
        indexPage.getFrame().switchToMain();

        return this;
    }

    public ExerciseOneSteps verifyFrameButton() {
        assertThat(indexPage.getFrame().getFrameBtn()).isNotNull();
        return this;
    }
}
