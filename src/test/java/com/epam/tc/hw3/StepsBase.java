package com.epam.tc.hw3;

import com.epam.tc.hw3.pages.PageBase;
import java.util.Collection;
import java.util.stream.Stream;
import org.openqa.selenium.WebElement;

public abstract class StepsBase {

    protected StepsBase(PageBase page) {
        page.open();
    }

    protected static Stream<String> selectWebElementsText(Collection<WebElement> webElements) {
        return webElements.stream().map(x -> x.getText());
    }

    protected static Stream<WebElement> filterElementsByText(Collection<WebElement> webElements, String text) {
        return webElements.stream().filter(x -> x.getText().contains(text));
    }
}
