package com.epam.tc.hw4;

import java.util.Collection;
import java.util.stream.Stream;
import org.openqa.selenium.WebElement;

public class StepsBase {
    protected static Stream<String> selectWebElementsText(Collection<WebElement> webElements) {
        return webElements.stream().map(x -> x.getText());
    }

    protected static Stream<WebElement> filterElementsByText(Collection<WebElement> webElements, String text) {
        return webElements.stream().filter(x -> x.getText().contains(text));
    }
}
