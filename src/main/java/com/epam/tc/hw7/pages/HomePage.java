package com.epam.tc.hw7.pages;

import com.epam.jdi.light.elements.composite.WebPage;
import com.epam.jdi.light.elements.pageobjects.annotations.locators.UI;
import com.epam.jdi.light.ui.html.elements.common.Button;
import com.epam.jdi.light.ui.html.elements.common.Link;
import com.epam.jdi.light.ui.html.elements.common.Text;
import com.epam.jdi.light.ui.html.elements.common.TextField;

public class HomePage extends WebPage {

    @UI("img#user-icon") public static Link userIcon;
    @UI("#user-name") public static Text userName;

    @UI("#name") public static TextField user;

    @UI("#password") public static TextField password;
    @UI("#login-button") public static Button loginButton;

}
