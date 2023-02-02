package com.epam.tc.hw7;

import static com.epam.jdi.light.elements.init.PageFactory.initElements;
import static com.epam.tc.hw7.pages.HomePage.loginButton;
import static com.epam.tc.hw7.pages.HomePage.password;
import static com.epam.tc.hw7.pages.HomePage.user;
import static com.epam.tc.hw7.pages.HomePage.userIcon;
import static com.epam.tc.hw7.pages.HomePage.userName;
import static com.epam.tc.hw7.pages.JdiSite.homePage;
import static com.epam.tc.hw7.pages.MetalsPage.checkboxesLabels;
import static com.epam.tc.hw7.pages.MetalsPage.colorButton;
import static com.epam.tc.hw7.pages.MetalsPage.colorResult;
import static com.epam.tc.hw7.pages.MetalsPage.colorsDrop;
import static com.epam.tc.hw7.pages.MetalsPage.elementsResult;
import static com.epam.tc.hw7.pages.MetalsPage.metalButton;
import static com.epam.tc.hw7.pages.MetalsPage.metalsDrop;
import static com.epam.tc.hw7.pages.MetalsPage.metalsResult;
import static com.epam.tc.hw7.pages.MetalsPage.radioButton;
import static com.epam.tc.hw7.pages.MetalsPage.submitButton;
import static com.epam.tc.hw7.pages.MetalsPage.summaryResult;
import static com.epam.tc.hw7.pages.MetalsPage.vegetablesButton;
import static com.epam.tc.hw7.pages.MetalsPage.vegetablesDrop;
import static com.epam.tc.hw7.pages.MetalsPage.vegetablesResult;

import com.epam.jdi.light.driver.WebDriverUtils;
import com.epam.tc.hw7.pages.JdiSite;
import com.epam.tc.hw7.pages.MetalsPage;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class JdiTest {

    @BeforeSuite(alwaysRun = true)
    public void beforeSuite() {
        initElements(JdiSite.class);
        homePage.open();
    }

    @AfterSuite(alwaysRun = true)
    public void afterSuite() {
        WebDriverUtils.killAllSeleniumDrivers();
    }

    @Test(dataProvider = "jsonData")
    public void loginTest(Map<String, MetalsPageTestData> data) {
        userIcon.click();
        user.sendKeys("Roman");
        password.sendKeys("Jdi1234");
        loginButton.click();
        userName.assertThat().displayed();
        JdiSite.metalsPage.open();

        for (var obj : data.values()) {
            radioButton.list()
                       .getAll().stream().filter(x -> obj.summary.contains(Integer.parseInt(x.getText())))
                       .forEach(y -> y.click());
            checkboxesLabels.stream().filter(x -> obj.elements.contains(x.getText())).forEach(y -> y.click());

            colorButton.click();
            colorsDrop.stream().filter(x -> obj.color.equalsIgnoreCase(x.getText())).collect(Collectors.toList())
                      .forEach(y -> y.click());

            metalButton.click();
            metalsDrop.stream().filter(x -> obj.metals.equalsIgnoreCase(x.getText())).collect(Collectors.toList())
                      .forEach(y -> y.click());

            vegetablesButton.click();
            vegetablesDrop.stream().filter(x -> x.getText().equalsIgnoreCase("Vegetables"))
                          .findFirst().get().click();
            vegetablesDrop.stream().filter(x -> obj.vegetables.contains(x.getText())).collect(Collectors.toList())
                          .forEach(y -> y.click());

            submitButton.click();

            summaryResult.assertThat().text("Summary: " + obj.summary.stream().reduce(0, Integer::sum));
            colorResult.assertThat().text("Color: " + obj.color);
            metalsResult.assertThat().text("Metal: " + obj.metals);
            vegetablesResult.assertThat().text("Vegetables: " + String.join(", ", obj.vegetables));
            elementsResult.assertThat().text("Elements: " + String.join(", ", obj.elements));

            MetalsPage.refresh();
        }
    }

    @DataProvider
    public static Object[][] jsonData() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return new Object[][] {
            {mapper.readValue(new File("src/test/resources/JDI_ex8_metalsColorsDataSet.json"),
                new TypeReference<Map<String, MetalsPageTestData>>() {
                })}
        };
    }
}
