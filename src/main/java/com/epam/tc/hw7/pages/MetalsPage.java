package com.epam.tc.hw7.pages;

import com.epam.jdi.light.elements.complex.Checklist;
import com.epam.jdi.light.elements.complex.WebList;
import com.epam.jdi.light.elements.complex.dropdown.Dropdown;
import com.epam.jdi.light.elements.complex.dropdown.DropdownExpand;
import com.epam.jdi.light.elements.complex.dropdown.DropdownSelect;
import com.epam.jdi.light.elements.composite.WebPage;
import com.epam.jdi.light.elements.pageobjects.annotations.locators.JDropdown;
import com.epam.jdi.light.elements.pageobjects.annotations.locators.UI;
import com.epam.jdi.light.ui.html.elements.common.Button;
import com.epam.jdi.light.ui.html.elements.common.Text;
import com.epam.jdi.light.ui.html.elements.complex.MultiSelector;
import com.epam.jdi.light.ui.html.elements.complex.RadioButtons;

public class MetalsPage extends WebPage {
    @UI("#vegetables .dropdown-menu label") public static WebList vegetablesDrop;
    @UI("#vegetables button") public static Button vegetablesButton;

    @UI("#submit-button") public static Button submitButton;

    @UI(".checkbox > input[type='checkbox']") public static Checklist elementsCheckboxes;
    @UI(".radio > input[type='radio']") public static RadioButtons summaryRadioButtons;

    @JDropdown(root = "#colors", value = ".filter-option", list = "li", expand = ".caret")
    public static Dropdown colors;

    @JDropdown(root = "#metals", value = ".filter-option", list = "li", expand = ".caret")
    public static Dropdown metals;

    @UI(".results .summ-res") public static Text summaryResult;
    @UI(".results .col-res") public static Text colorResult;
    @UI(".results .met-res") public static Text metalsResult;
    @UI(".results .sal-res") public static Text vegetablesResult;
    @UI(".results .elem-res") public static Text elementsResult;
}
