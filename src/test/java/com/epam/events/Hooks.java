package com.epam.events;

import com.codeborne.selenide.WebDriverRunner;
import com.epam.events.Configuration.Configuration;
import com.epam.events.Helpers.StartSelenoid;
import com.epam.events.StepDefs.AllEventsPageSteps;
import com.epam.events.StepDefs.TalksLibrarySteps;
import com.epam.events.WebDriverFactory.WebDriverFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;

public class Hooks {
    private static final Logger log = LogManager.getLogger(Hooks.class);
    private final static Configuration cfg = org.aeonbits.owner.ConfigFactory.create(Configuration.class);
    AllEventsPageSteps allEventsPageSteps;
    TalksLibrarySteps talksLibrarySteps;

    @BeforeAll
    public static void remoteSetUp() throws IOException, InterruptedException {
        if (cfg.browser().toUpperCase().equals("REMOTE")) {
            log.info("Launching selenoid");
            WebDriverFactory.port = StartSelenoid.start();
        }
    }

    @BeforeEach
    public void setUp() throws IOException, InterruptedException {
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
