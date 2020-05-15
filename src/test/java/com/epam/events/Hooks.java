package com.epam.events;

import com.codeborne.selenide.WebDriverRunner;
import com.epam.events.StepDefs.AllEventsPageSteps;
import com.epam.events.StepDefs.TalksLibrarySteps;
import com.epam.events.WebDriverFactory.WebDriverFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.net.MalformedURLException;

public class Hooks {
    AllEventsPageSteps allEventsPageSteps;
    TalksLibrarySteps talksLibrarySteps;

    @BeforeEach
    public void setUp() throws MalformedURLException {
        WebDriverRunner.setWebDriver(WebDriverFactory.create());

        allEventsPageSteps = new AllEventsPageSteps(WebDriverRunner.getWebDriver());
        talksLibrarySteps = new TalksLibrarySteps(WebDriverRunner.getWebDriver());
    }

    @AfterEach
    public void tearDown() {
        if(WebDriverRunner.getWebDriver()!=null){
            WebDriverRunner.getWebDriver().quit();
        }
    }
}
