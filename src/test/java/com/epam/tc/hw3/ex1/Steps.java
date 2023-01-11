package com.epam.tc.hw3.ex1;

import static org.assertj.core.api.Assertions.assertThat;

import com.epam.tc.hw3.ex1.pages.Frame;
import com.epam.tc.hw3.ex1.pages.IndexPage;
import org.openqa.selenium.WebDriver;

public class Steps {
    private WebDriver driver;
    private IndexPage indexPage;
    private Frame frame;

    public Steps(WebDriver driver) {
        this.driver = driver;
        this.indexPage = new IndexPage(this.driver);
        this.frame = new Frame(this.driver);
    }

    public Steps login(String username, String password) {
        indexPage.login(username, password);
        return this;
    }

    public Steps verifyBrowserTitle() {
        String getActualTitle = driver.getTitle();
        assertThat(getActualTitle).isEqualTo("Home Page");
        return this;
    }

    public Steps verifyUsername(String correctUsername) {
        assertThat(indexPage.getUsername().getText()).isEqualTo(correctUsername);
        return this;
    }

    public Steps verifyFourItems() {
        assertThat(indexPage.getTabs().stream().map(x -> x.getText()).toArray())
                .isEqualTo(new String[]{"HOME", "CONTACT FORM", "SERVICE", "METALS & COLORS"});
        return this;
    }

    public Steps verifyFourPictures() {
        int count = indexPage.getPict().size();
        assertThat(count).isEqualTo(4);
        return this;
    }

    public Steps verifyPictTexts() {
        assertThat(indexPage.getPictText().stream().filter(x -> x.getText() != null).count()).isEqualTo(4);
        return this;
    }

    public Steps verifyLeftSect() {
        assertThat(indexPage.getLeftSect().stream().map(x -> x.getText()).toArray())
                .isEqualTo(new String[]{"Home", "Contact form", "Service", "Metals & Colors", "Elements packs"});
        return this;
    }

    public Steps verifyFrame() {
        assertThat(frame.getFrame()).isNotNull();
        return this;
    }

    public Steps switchToFrame() {
        driver.switchTo().frame(frame.getFrame());
        return this;
    }

    public Steps switchToMain() {
        driver.switchTo().parentFrame();

        return this;
    }

    public Steps verifyFrameBtn() {
        assertThat(frame.getFrameBtn()).isNotNull();
        return this;
    }
}
