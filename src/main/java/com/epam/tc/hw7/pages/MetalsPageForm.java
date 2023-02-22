package com.epam.tc.hw7.pages;

import com.epam.jdi.light.elements.complex.Checklist;
import com.epam.jdi.light.elements.complex.WebList;
import com.epam.jdi.light.elements.complex.dropdown.Dropdown;
import com.epam.jdi.light.elements.pageobjects.annotations.locators.JDropdown;
import com.epam.jdi.light.elements.pageobjects.annotations.locators.UI;
import com.epam.jdi.light.ui.html.elements.common.Button;
import com.epam.jdi.light.ui.html.elements.complex.RadioButtons;
import java.util.Collection;
import java.util.stream.Collectors;

public class MetalsPageForm {
    @UI(".checkbox > input[type='checkbox']")
    public static Checklist elementsCheckboxes;
    @UI(".radio > input[type='radio']")
    public static RadioButtons summaryRadioButtons;

    @JDropdown(root = "#colors", value = ".filter-option", list = "li", expand = ".caret")
    public static Dropdown colors;

    @JDropdown(root = "#metals", value = ".filter-option", list = "li", expand = ".caret")
    public static Dropdown metals;

    @UI("#vegetables .dropdown-menu label")
    public static WebList vegetablesDrop;
    @UI("#vegetables button")
    public static Button vegetablesButton;

    @UI("#submit-button")
    public static Button submitButton;

    public void fill(Collection<Integer> summaries, Collection<String> elements, String color, String metal,
                     Collection<String> vegetables) {
        selectSummary(summaries);
        fillElements(elements);
        colors.select(color);
        metals.select(metal);
        selectVegetables(vegetables);
    }

    private void fillElements(Collection<String> elements) {
        elements.forEach(x -> elementsCheckboxes.select(x));
    }

    private void selectSummary(Collection<Integer> summaries) {
        summaries.forEach(x -> summaryRadioButtons.select(x.toString()));
    }

    private void selectVegetables(Collection<String> vegetables) {
        vegetablesButton.click();

        vegetablesDrop.stream().filter(x -> x.getText().equalsIgnoreCase("Vegetables")).findFirst().get().click();
        vegetablesDrop.stream().filter(x -> vegetables.contains(x.getText())).collect(Collectors.toList())
                      .forEach(x -> x.click());
    }

    public void submit() {
        submitButton.click();
    }
}
