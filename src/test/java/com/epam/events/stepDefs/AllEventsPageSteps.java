package com.epam.events.stepDefs;

import com.codeborne.selenide.SelenideElement;
import com.epam.events.configuration.Configuration;
import com.epam.events.pages.AllEventsPage;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import java.time.LocalDate;
import java.util.Random;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.epam.events.helpers.Helpers.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AllEventsPageSteps extends Abstract {

    private final Logger log = LogManager.getLogger(Abstract.class);
    private final Configuration cfg = ConfigFactory.create(Configuration.class);
    public AllEventsPageSteps(WebDriver driver) {super(driver);}
    AllEventsPage allEventsPage = new AllEventsPage();

    public AllEventsPageSteps openPage() {
        log.info("Open " + cfg.mainpage()+cfg.events());
        open(cfg.mainpage()+cfg.events());

        return this;
    }

    public AllEventsPageSteps openUpcomingEvents() {
        log.info("Click on upcoming events tab");
        $(allEventsPage.upcomingEventsTab).click();
        $(allEventsPage.thisWeekTitle).shouldBe(visible);

        return this;
    }

    public AllEventsPageSteps openPastEvents() {
        log.info("Click on past events tab");
        $(allEventsPage.pastEventsTab).click();
        $(allEventsPage.pastAllEventsTitle).shouldBe(visible);

        return this;
    }

    public AllEventsPageSteps chooseLocationFilter(String location) {
        log.info("Click on location filter");
        $(allEventsPage.locationFilter).click();

        log.info("Choose Canada location");
        $(allEventsPage.locationFilterLocationCheckbox(location)).click();
        $(allEventsPage.filerResultMessage).shouldBe(visible);

        return this;
    }

    public EventPageSteps openRandomEvent() {
        Random rnd = new Random();

        log.info("Open random event");
        $$(allEventsPage.allEventsCards)
                .get(rnd.nextInt($$(allEventsPage.allEventsCards).size()))
                .scrollIntoView(true)
                .click();

        return new EventPageSteps(driver);
    }

    /*Asserts*/

    public void upcomingEventsCompareWithTab() {
        log.info("Compare all event cards to counter in upcoming events tab");
        $$(allEventsPage.allEventsCards).shouldHaveSize
                (Integer.parseInt($(allEventsPage.upcomingEventsCounter).getText()));
    }

    public AllEventsPageSteps pastEventsCompareWithTab() {
        log.info("Compare all event cards to counter in past events tab");
        $$(allEventsPage.allEventsCards).shouldHaveSize
                (Integer.parseInt($(allEventsPage.pastEventsCounter).getText()));

        return this;
    }

    public void checkElementsInEventCard() {
        int x = 0;
        log.info("Check elements in event cards");
        log.info("Total event cards = " + $$(allEventsPage.allEventsCards).size());

        for(SelenideElement event : $$(allEventsPage.allEventsCards)) {
            x++;
            log.info("Checking " + x + " card");
            log.info("Checking location");
            event.$(allEventsPage.eventLocation).shouldBe(visible);
            log.info("Checking language");
            event.$(allEventsPage.eventLanguage).shouldBe(visible);
            log.info("Checking title");
            event.$(allEventsPage.eventName).shouldBe(visible);
            log.info("Checking date");
            event.$(allEventsPage.eventDate).shouldBe(visible);
            log.info("Checking registration status");
            event.$(allEventsPage.eventRegistrationStatus).shouldBe(visible);
            log.info("Checking speaker");
            if(eventCardSizeChecker(event).equals("M")) {
                event.$(allEventsPage.eventSizeMSpeakers).shouldBe(visible);
            }
            else {
                event.$(allEventsPage.eventSizeSSpeakers).shouldBe(visible);
            }
        }
    }

    public AllEventsPageSteps checkEventsThisWeekMoreThanZero() {
        $(allEventsPage.thisWeekTitle).should(exist);
        log.info("Checking events exist this week");
        $$(allEventsPage.allEventsOnThisWeek).shouldBe(sizeGreaterThan(0));

        return this;
    }

    public void checkEventsDatesThisWeek() {
        log.info("Check this week event dates are correct");
        LocalDate dateOfEvent;
        for (SelenideElement event : $$(allEventsPage.allEventsOnThisWeek)) {
            dateOfEvent = convert(event.$(allEventsPage.eventDate).getText());
            assertTrue(dateOfEvent.compareTo(nowDate())>=0 && dateOfEvent.compareTo(getLastDayOfWeek())<=0,
                    "Date of event must be between " + nowDate() +" and " + getLastDayOfWeek() + "\nActual event date is " + dateOfEvent);
        }
    }

    public void checkEventsDatesInPast() {
        log.info("Check events dates are in past");
        LocalDate dateOfEvent;
        for (SelenideElement event : $$(allEventsPage.allEventsCards)) {
            dateOfEvent = convert(event.$(allEventsPage.eventDate).getText());
            assertTrue(dateOfEvent.isBefore(nowDate()), "Date of event must be before " + nowDate() + "\nActual event date is " + dateOfEvent);
        }
    }
}
