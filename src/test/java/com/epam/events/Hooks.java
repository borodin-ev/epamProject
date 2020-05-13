package com.epam.events;

import com.codeborne.selenide.WebDriverRunner;
import com.epam.events.WebDriverFactory.WebDriverFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.net.MalformedURLException;

public class Hooks {

    @BeforeEach
    public void setUp() throws MalformedURLException {
        WebDriverFactory.create();
    }

    @AfterEach
    public void tearDown() {
        if(WebDriverRunner.getWebDriver()!=null){
            WebDriverRunner.getWebDriver().quit();
        }
    }
}
