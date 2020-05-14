package com.epam.events;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.UIAssertionError;
import com.epam.events.Configuration.Configuration;
import com.epam.events.Pages.AllEventsPage;
import com.epam.events.Pages.EventPage;
import com.epam.events.Pages.TalkPage;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import java.time.LocalDate;
import java.util.ArrayList;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Selenide.*;
import static com.epam.events.Helpers.Helpers.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Asserts {
    private static Configuration cfg = ConfigFactory.create(Configuration.class);
    private static final Logger log = LogManager.getLogger(Asserts.class);

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

    public static void checkElementIsVisible (By element) {
        $(element).shouldBe(visible);
    }

    public static void checkEventsThisWeekMoreThanZero() {
        $$(AllEventsPage.allEventsOnThisWeek).shouldBe(sizeGreaterThan(0));
    }

    public static void checkEventsDatesThisWeek() {
        LocalDate dateOfEvent;
        for (SelenideElement event : $$(AllEventsPage.allEventsOnThisWeek)) {
            dateOfEvent = convert(event.$(AllEventsPage.eventDate).getText());
            assertTrue(dateOfEvent.compareTo(nowDate())>=0 && dateOfEvent.compareTo(getLastDayOfWeek())<=0,
                    "Date of event must be between " + nowDate() +" and " + getLastDayOfWeek() + "\nActual event date is " + dateOfEvent);
        }
    }

    public static void pastEventsCompareWithTab() {
        $$(AllEventsPage.allEventsCards).shouldHaveSize
                (Integer.parseInt($(AllEventsPage.pastEventsCounter).getText()));
    }

    public static void checkEventsDatesInPast() {
        LocalDate dateOfEvent;
        for (SelenideElement event : $$(AllEventsPage.allEventsCards)) {
            dateOfEvent = convert(event.$(AllEventsPage.eventDate).getText());
            assertTrue(dateOfEvent.isBefore(nowDate()), "Date of event must be before " + nowDate() + "\nActual event date is " + dateOfEvent);
        }
    }

    public static void checkFilteredData() {
        ArrayList<String> links;

        links = getTalksLinks();

        for (String link : links) {
            log.info("Open page " + link);
            open(link);
            $(TalkPage.location).shouldBe(visible);

            log.info("Checking location");
            containsText(TalkPage.location,cfg.location());
            log.info("Checking language");
            containsText(TalkPage.language,cfg.language());
            log.info("Checking tag");
            assertTrue(checkDesignTag(), "There is no design tag");
        }
    }

    public static boolean checkDesignTag() {
        $$(TalkPage.tags).shouldHave(sizeGreaterThan(0));
        for(SelenideElement tag: $$(TalkPage.tags)) {
            try {
                tag.shouldHave(text("Design"));
                return true;
            }
            catch (UIAssertionError ex) {
                log.warn("Tag should have text \"Design\". Actual text - "+ tag.getText());
            }
        }
        return false;
    }

    public static void containsText(By element ,String expectedText) {
        $(element).shouldHave(text(expectedText));
    }

    public static void searchForKeyword() {
        int x = 0;
        ArrayList<String> links;

        links = getTalksLinks();


        for (String link : links) {
            x++;
            log.info("Open page " + link);
            open(link);

            containsText(EventPage.title, cfg.keyword());
            log.info(x + " out of " + links.size() + " talks checked");
        }
    }
}
