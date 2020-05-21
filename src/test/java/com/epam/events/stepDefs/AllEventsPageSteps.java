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

    public AllEventsPageSteps openPage() {
        log.info("Open " + cfg.mainpage()+cfg.events());
        open(cfg.mainpage()+cfg.events());

        return this;
    }

    public AllEventsPageSteps openUpcomingEvents() {
        log.info("Click on upcoming events tab");
        $(AllEventsPage.upcomingEventsTab).click();
        $(AllEventsPage.thisWeekTitle).shouldBe(visible);

        return this;
    }

    public AllEventsPageSteps openPastEvents() {
        log.info("Click on past events tab");
        $(AllEventsPage.pastEventsTab).click();
        $(AllEventsPage.pastAllEventsTitle).shouldBe(visible);

        return this;
    }

    public AllEventsPageSteps chooseCanadaLocationFilter() {
        log.info("Click on location filter");
        $(AllEventsPage.locationFilter).click();

        log.info("Choose Canada location");
        $(AllEventsPage.locationFilterCanadaCheckbox).click();
        $(AllEventsPage.filerResultMessage).shouldBe(visible);

        return this;
    }

    public EventPageSteps openRandomEvent() {
        Random rnd = new Random();

        log.info("Open random event");
        $$(AllEventsPage.allEventsCards)
                .get(rnd.nextInt($$(AllEventsPage.allEventsCards).size()))
                .scrollIntoView(true)
                .click();

        return new EventPageSteps(driver);
    }

    /*Asserts*/

    public void upcomingEventsCompareWithTab() {
        log.info("Compare all event cards to counter in upcoming events tab");
        $$(AllEventsPage.allEventsCards).shouldHaveSize
                (Integer.parseInt($(AllEventsPage.upcomingEventsCounter).getText()));
    }

    public AllEventsPageSteps pastEventsCompareWithTab() {
        log.info("Compare all event cards to counter in past events tab");
        $$(AllEventsPage.allEventsCards).shouldHaveSize
                (Integer.parseInt($(AllEventsPage.pastEventsCounter).getText()));

        return this;
    }

    public void checkElementsInEventCard() {
        int x = 0;
        log.info("Check elements in event cards");
        log.info("Total event cards = " + $$(AllEventsPage.allEventsCards).size());

        for(SelenideElement event : $$(AllEventsPage.allEventsCards)) {
            x++;
            log.info("Checking " + x + " card");
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
            if(eventCardSizeChecker(event).equals("M")) {
                event.$(AllEventsPage.eventSizeMSpeakers).shouldBe(visible);
            }
            else {
                event.$(AllEventsPage.eventSizeSSpeakers).shouldBe(visible);
            }
        }
    }

    public AllEventsPageSteps checkEventsThisWeekMoreThanZero() {
        $(AllEventsPage.thisWeekTitle).should(exist);
        log.info("Checking events exist this week");
        $$(AllEventsPage.allEventsOnThisWeek).shouldBe(sizeGreaterThan(0));

        return this;
    }

    public void checkEventsDatesThisWeek() {
        log.info("Check this week event dates are correct");
        LocalDate dateOfEvent;
        for (SelenideElement event : $$(AllEventsPage.allEventsOnThisWeek)) {
            dateOfEvent = convert(event.$(AllEventsPage.eventDate).getText());
            assertTrue(dateOfEvent.compareTo(nowDate())>=0 && dateOfEvent.compareTo(getLastDayOfWeek())<=0,
                    "Date of event must be between " + nowDate() +" and " + getLastDayOfWeek() + "\nActual event date is " + dateOfEvent);
        }
    }

    public void checkEventsDatesInPast() {
        log.info("Check events dates are in past");
        LocalDate dateOfEvent;
        for (SelenideElement event : $$(AllEventsPage.allEventsCards)) {
            dateOfEvent = convert(event.$(AllEventsPage.eventDate).getText());
            assertTrue(dateOfEvent.isBefore(nowDate()), "Date of event must be before " + nowDate() + "\nActual event date is " + dateOfEvent);
        }
    }
}
