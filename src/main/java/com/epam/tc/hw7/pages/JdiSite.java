package com.epam.tc.hw7.pages;

import com.epam.jdi.light.elements.pageobjects.annotations.JSite;
import com.epam.jdi.light.elements.pageobjects.annotations.Title;
import com.epam.jdi.light.elements.pageobjects.annotations.Url;

@JSite("https://jdi-testing.github.io/jdi-light/")
public class JdiSite {

    @Url("/")
    public static HomePage homePage;

    @Url("/metals-colors") @Title("Metal and Colors")
    public static MetalsPage metalsPage;

}
