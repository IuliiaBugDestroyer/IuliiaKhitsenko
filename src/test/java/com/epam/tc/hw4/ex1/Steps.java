package com.epam.tc.hw4.ex1;

import static org.assertj.core.api.Assertions.assertThat;

import com.epam.tc.hw4.ex1.pages.Frame;
import com.epam.tc.hw4.ex1.pages.IndexPage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public class Steps {
    private WebDriver driver;
    private IndexPage indexPage;
    private Frame frame;

    public Steps(WebDriver driver) {
        this.driver = driver;
        this.indexPage = new IndexPage(this.driver);
        indexPage.open();
        this.frame = new Frame(this.driver);
    }

    @Step("User login")
    public void login(String username, String password) {
        indexPage.login(username, password);
    }

    @Step("Verify Home Page title")
    public void verifyBrowserTitle() {
        String getActualTitle = driver.getTitle();
        assertThat(getActualTitle).isEqualTo("Home Page");
    }

    @Step("Verify username")
    public void verifyUsername(String correctUsername) {
        assertThat(indexPage.getUsername().getText()).isEqualTo(correctUsername);
    }

    @Step("Verify four tabs in header")
    public void verifyFourItems() {
        assertThat(indexPage.getTabs().stream().map(x -> x.getText()).toArray())
                .isEqualTo(new String[]{"HOME", "CONTACT FORM", "SERVICE", "METALS & COLORS"});
    }

    @Step("Verify four pictures on page")
    public void verifyFourPictures() {
        int count = indexPage.getPict().size();
        assertThat(count).isEqualTo(4);
    }

    @Step("Verify picture texts")
    public void verifyPictTexts() {
        assertThat(indexPage.getPictText().stream().filter(x -> x.getText() != null).count()).isEqualTo(4);
    }

    @Step("Verify items in left menu")
    public void verifyLeftSect() {
        assertThat(indexPage.getLeftSect().stream().map(x -> x.getText()).toArray())
                .isEqualTo(new String[]{"Home", "Contact form", "Service", "Metals & Colors", "Elements packs"});
    }

    @Step("Verify frame")
    public void verifyFrame() {
        assertThat(frame.getFrame()).isNotNull();
    }

    @Step("Switch to frame")
    public void switchToFrame() {
        driver.switchTo().frame(frame.getFrame());
    }

    @Step("Switch back to Index page")
    public void switchToMain() {
        driver.switchTo().parentFrame();
    }

    @Step("Verify frame button")
    public void verifyFrameBtn() {
        assertThat(frame.getFrameBtn()).isNotNull();
    }
}
