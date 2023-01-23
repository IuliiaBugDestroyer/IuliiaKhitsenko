package com.epam.tc.hw5.steps;

import com.epam.tc.hw5.common.TestContext;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Hook {

    @Before
    public static void setupDriver() {
        if (TestContext.getInstance().get("driver", WebDriver.class) == null) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            //            options.addArguments("disable-infobars"); // disabling infobars
            //            options.addArguments("--disable-extensions"); // disabling extensions
            //            options.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
            //            options.addArguments("--no-sandbox"); // Bypass OS security model
            //            options.setHeadless(true);
            //            options.addArguments("--remote-debugging-port=9222");
            var driver = new ChromeDriver(options);
            driver.manage().window().maximize();
            driver.manage().timeouts().getImplicitWaitTimeout();
            TestContext.getInstance().set("driver", driver);
        }
    }

    @After
    public static void closeBrowser() {
        var driver = TestContext.getInstance().get("driver", WebDriver.class);

        if (TestContext.getInstance().get("driver", WebDriver.class) != null) {
            driver.quit();
        }

        TestContext.getInstance().clear();
    }
}
