package com.epam.tc.hw7.pages;

import com.epam.jdi.light.elements.composite.WebPage;
import com.epam.jdi.light.elements.pageobjects.annotations.locators.UI;
import com.epam.jdi.light.ui.html.elements.common.Text;

public class MetalsPage extends WebPage {

    public static MetalsPageForm form;

    @UI(".results .summ-res")
    public static Text summaryResult;
    @UI(".results .col-res")
    public static Text colorResult;
    @UI(".results .met-res")
    public static Text metalsResult;
    @UI(".results .sal-res")
    public static Text vegetablesResult;
    @UI(".results .elem-res")
    public static Text elementsResult;
}
