package com.epam.events;

import com.codeborne.selenide.WebDriverRunner;
import com.epam.events.Configuration.Configuration;
import com.epam.events.Pages.AllEventsPage;
import com.epam.events.StepDefs.AllEventsPageSteps;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;

public class EventsPortalTest extends Hooks {
    private static Configuration cfg = ConfigFactory.create(Configuration.class);
    private static final Logger log = LogManager.getLogger(EventsPortalTest.class);

    @Test
    @DisplayName("View Upcoming Events Test")
    void viewUpcomingEventsTest () {
        log.info("Open " + cfg.mainpage()+"/events");
        open(cfg.mainpage()+"/events");

        AllEventsPageSteps aeps = new AllEventsPageSteps(WebDriverRunner.getWebDriver());

        aeps.openUpcomingEvents();

        log.info("Compare all event cards to counter in upcoming events tab");
        Asserts.upcomingEventsCompareWithTab();
    }

    @Test
    @DisplayName("Check event card elements")
    void viewEventCards() {
        log.info("Open " + cfg.mainpage()+"/events");
        open(cfg.mainpage()+"/events");

        AllEventsPageSteps aeps = new AllEventsPageSteps(WebDriverRunner.getWebDriver());

        aeps.openUpcomingEvents();

//        TODO порядок элементов
        log.info("Check elements in event cards");
        Asserts.elementsInEventCardChecker();
    }

    @Test
    @DisplayName("Events date validation")
    void eventsDateValidation() {
        log.info("Open " + cfg.mainpage()+"/events");
        open(cfg.mainpage()+"/events");

        AllEventsPageSteps aeps = new AllEventsPageSteps(WebDriverRunner.getWebDriver());

        aeps.openUpcomingEvents();

        log.info("Checking title \"This week\"");
        Asserts.checkElementIsVisible(AllEventsPage.thisWeekTitle);

        log.info("Checking events exist this week");
        Asserts.checkEventsThisWeekMoreThanZero();

        log.info("Check this week event dates are correct");
        Asserts.checkEventsDatesThisWeek();
    }
}
