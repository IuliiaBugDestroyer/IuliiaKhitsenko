package com.epam.tc.hw7.pages;

import com.epam.jdi.light.elements.common.Label;
import com.epam.jdi.light.elements.common.UIElement;
import com.epam.jdi.light.elements.complex.WebList;
import com.epam.jdi.light.elements.composite.WebPage;
import com.epam.jdi.light.elements.pageobjects.annotations.locators.UI;
import com.epam.jdi.light.ui.html.elements.common.Button;
import com.epam.jdi.light.ui.html.elements.common.Text;
import com.epam.jdi.light.ui.html.elements.complex.RadioButtons;
import java.util.List;

public class MetalsPage extends WebPage {
    @UI("#colors .dropdown-menu li span") public static WebList colorsDrop;
    @UI("#metals .dropdown-menu li span") public static WebList metalsDrop;
    @UI("#vegetables .dropdown-menu label") public static WebList vegetablesDrop;
    @UI("#colors button") public static Button colorButton;
    @UI("#metals button .caret") public static Button metalButton;
    @UI("#vegetables button") public static Button vegetablesButton;
    @UI("#submit-button") public static Button submitButton;

    @UI(".checkbox > label") public static List<Label> checkboxesLabels;
    @UI(".radio > label") public static RadioButtons radioButton;

    @UI(".results .summ-res") public static Text summaryResult;
    @UI(".results .col-res") public static Text colorResult;
    @UI(".results .met-res") public static Text metalsResult;
    @UI(".results .sal-res") public static Text vegetablesResult;
    @UI(".results .elem-res") public static Text elementsResult;
}
