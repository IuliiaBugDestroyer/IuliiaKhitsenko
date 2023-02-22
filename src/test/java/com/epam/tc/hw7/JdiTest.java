package com.epam.tc.hw7;

import static com.epam.jdi.light.elements.init.PageFactory.initElements;
import static com.epam.tc.hw7.pages.JdiSite.homePage;
import static com.epam.tc.hw7.pages.JdiSite.metalsPage;
import static com.epam.tc.hw7.pages.MetalsPage.colorResult;
import static com.epam.tc.hw7.pages.MetalsPage.elementsResult;
import static com.epam.tc.hw7.pages.MetalsPage.metalsResult;
import static com.epam.tc.hw7.pages.MetalsPage.summaryResult;
import static com.epam.tc.hw7.pages.MetalsPage.vegetablesResult;

import com.epam.jdi.light.driver.WebDriverUtils;
import com.epam.tc.hw7.pages.JdiSite;
import java.util.Collection;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class JdiTest {

    @BeforeSuite(alwaysRun = true)
    public void beforeSuite() {
        initElements(JdiSite.class);
    }

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod() {
        homePage.open();
        homePage.loginIfRequired();
    }

    @AfterSuite(alwaysRun = true)
    public void afterSuite() {
        WebDriverUtils.killAllSeleniumDrivers();
    }

    @Test(dataProvider = "metalsPageData", dataProviderClass = DataProviders.class)
    public void loginTest(MetalsPageTestData data) {

        metalsPage.open();

        metalsPage.form.fill(data.summary, data.elements, data.color, data.metals, data.vegetables);


        metalsPage.form.submit();

        verifyResultLog(data.summary, data.elements, data.color, data.metals, data.vegetables);
    }

    private void verifyResultLog(Collection<Integer> summaries, Collection<String> elements, String color, String metals, Collection<String> vegetables) {
        summaryResult.assertThat().text("Summary: " + summaries.stream().reduce(0, Integer::sum));
        colorResult.assertThat().text("Color: " + color);
        metalsResult.assertThat().text("Metal: " + metals);
        vegetablesResult.assertThat().text("Vegetables: " + String.join(", ", vegetables));
        elementsResult.assertThat().text("Elements: " + String.join(", ", elements));
    }
}
