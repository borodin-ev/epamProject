package com.epam.events;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.UIAssertionError;
import com.epam.events.Pages.AllEventsPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class Asserts {

    private static final Logger log = LogManager.getLogger(EventsPortalTest.class);

    public static void upcomingEventsCompareWithTab() {
        $$(AllEventsPage.allEventsCards).shouldHaveSize
                (Integer.parseInt($(AllEventsPage.upcomingEventsCounter).getText()));
    }

    public static void elementsInEventCardChecker() {
        int x = 0;
        log.info("Total event cards = " + $$(AllEventsPage.allEventsCards).size());

        for(SelenideElement event : $$(AllEventsPage.allEventsCards)) {
            x++;
            log.info("Cheking " + x + " card");
            log.info("Checking location");
            event.$(AllEventsPage.eventLocation).shouldBe(visible);
            log.info("Checking language");
            event.$(AllEventsPage.eventLanguage).shouldBe(visible);
            log.info("Checking title");
            event.$(AllEventsPage.eventName).shouldBe(visible);
            log.info("Checking date");
            event.$(AllEventsPage.eventDate).shouldBe(visible);
            log.info("Checking registration status");
            event.$(AllEventsPage.eventRegistrationStatus).shouldBe(visible);
            log.info("Checking speaker");
            speakersChecker(event);
        }
    }

    private static void speakersChecker(SelenideElement event) {
        try {
            event.$(AllEventsPage.eventSpeakerPhoto).parent().shouldHave(and("Have two attributes", attribute("data-name"), attribute("data-job-title")));
        }
        catch (UIAssertionError ex) {
            event.$(AllEventsPage.eventSpeakerPhoto).shouldHave(and("Have two attributes", attribute("data-name"), attribute("data-job-title")));
        }
    }
}
