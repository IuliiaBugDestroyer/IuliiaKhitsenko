package com.epam.tc.hw7;

import static com.epam.jdi.light.elements.init.UIFactory.$;

import com.epam.jdi.light.driver.WebDriverUtils;
import com.epam.jdi.light.elements.init.PageFactory;
import com.epam.tc.hw7.pages.JdiSite;
import com.epam.tc.hw7.pages.MetalsPage;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class JdiTest {

    @BeforeSuite(alwaysRun = true)
    public void beforeSuite() {
        PageFactory.initSite(JdiSite.class);
    }

    @AfterSuite(alwaysRun = true)
    public void afterSuite() {
        WebDriverUtils.killAllSeleniumDrivers();
    }

    @Test(dataProvider = "jsonData")
    public void loginSimpleTest(Map<String, JsonObject> data) {
        JdiSite.homePage.open();
        $("img#user-icon").click();
        $("#name").sendKeys("Roman");
        $("#password").sendKeys("Jdi1234");
        $("#login-button").click();
        $("#user-name").is().displayed();

        JdiSite.metalsPage.open();

        for (var obj : data.values()) {
            $(".radio > label")
                .getAll().stream().filter(x -> obj.summary.contains(Integer.parseInt(x.getText())))
                .forEach(y -> y.click());
            $(".checkbox > label")
                .getAll().stream().filter(x -> obj.elements.contains(x.getText())).forEach(y -> y.click());

            $("#colors button").get().click();
            $("#colors .dropdown-menu li span")
                .getAll().stream().filter(x -> obj.color.equalsIgnoreCase(x.getText())).forEach(y -> y.click());

            $("#metals button .caret").get().click();
            $("#metals .dropdown-menu li span")
                .getAll().stream().filter(x -> obj.metals.equalsIgnoreCase(x.getText())).forEach(y -> y.click());

            $("#vegetables button").get().click();
            $("#vegetables .dropdown-menu label")
                .getAll().stream().filter(x -> x.getText().equalsIgnoreCase("Vegetables"))
                .findFirst().get().click();
            $("#vegetables .dropdown-menu label")
                .getAll().stream().filter(x -> obj.vegetables.contains(x.getText())).forEach(y -> y.click());

            $("#submit-button").click();

            $(".results .summ-res").assertThat().text("Summary: " + obj.summary.stream().reduce(0, Integer::sum));
            $(".results .col-res").assertThat().text("Color: " + obj.color);
            $(".results .met-res").assertThat().text("Metal: " + obj.metals);
            $(".results .sal-res").assertThat().text("Vegetables: " + String.join(", ", obj.vegetables));
            $(".results .elem-res").assertThat().text("Elements: " + String.join(", ", obj.elements));

            MetalsPage.refresh();
        }
    }

    @DataProvider
    public static Object[][] jsonData() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return new Object[][] {
            {mapper.readValue(new File("JDI_ex8_metalsColorsDataSet.json"),
                new TypeReference<Map<String, JsonObject>>() {
                })}
        };
    }
}
