package com.epam.tc.hw7;

import static com.epam.jdi.light.elements.init.PageFactory.initElements;
import static com.epam.tc.hw7.pages.JdiSite.homePage;
import static com.epam.tc.hw7.pages.JdiSite.metalsPage;
import static com.epam.tc.hw7.pages.MetalsPage.colorResult;
import static com.epam.tc.hw7.pages.MetalsPage.colors;
import static com.epam.tc.hw7.pages.MetalsPage.elementsCheckboxes;
import static com.epam.tc.hw7.pages.MetalsPage.elementsResult;
import static com.epam.tc.hw7.pages.MetalsPage.metals;
import static com.epam.tc.hw7.pages.MetalsPage.metalsResult;
import static com.epam.tc.hw7.pages.MetalsPage.submitButton;
import static com.epam.tc.hw7.pages.MetalsPage.summaryRadioButtons;
import static com.epam.tc.hw7.pages.MetalsPage.summaryResult;
import static com.epam.tc.hw7.pages.MetalsPage.vegetablesButton;
import static com.epam.tc.hw7.pages.MetalsPage.vegetablesDrop;
import static com.epam.tc.hw7.pages.MetalsPage.vegetablesResult;

import com.epam.jdi.light.driver.WebDriverUtils;
import com.epam.tc.hw7.pages.JdiSite;
import java.util.stream.Collectors;
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

        data.summary.forEach(x -> summaryRadioButtons.select(x.toString()));

        data.elements.forEach(x -> elementsCheckboxes.select(x));

        colors.select(data.color);

        metals.select(data.metals);

        vegetablesButton.click();
        vegetablesDrop.stream().filter(x -> x.getText().equalsIgnoreCase("Vegetables"))
                      .findFirst().get().click();
        vegetablesDrop.stream().filter(x -> data.vegetables.contains(x.getText())).collect(Collectors.toList())
                      .forEach(y -> y.click());

        submitButton.click();

        summaryResult.assertThat().text("Summary: " + data.summary.stream().reduce(0, Integer::sum));
        colorResult.assertThat().text("Color: " + data.color);
        metalsResult.assertThat().text("Metal: " + data.metals);
        vegetablesResult.assertThat().text("Vegetables: " + String.join(", ", data.vegetables));
        elementsResult.assertThat().text("Elements: " + String.join(", ", data.elements));
    }
}
