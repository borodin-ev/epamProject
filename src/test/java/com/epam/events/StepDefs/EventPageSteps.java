package com.epam.events.StepDefs;

import com.epam.events.Helpers.Helpers;
import com.epam.events.Pages.EventPage;
import org.openqa.selenium.WebDriver;

public class EventPageSteps extends Abstract {
    public EventPageSteps(WebDriver driver) {super(driver);}

    public void checkEventPageDetails() {
        log.info("Check registration button is visible");
        Helpers.checkElementIsVisible(EventPage.registrationButton);
        log.info("Check event program is visible");
        Helpers.checkElementIsVisible(EventPage.eventProgram);
        log.info("Check event date is visible");
        Helpers.checkElementIsVisible(EventPage.eventDate);
        log.info("Check event time is visible");
        Helpers.checkElementIsVisible(EventPage.eventTime);
//        log.info("Check event location is visible");
//        Helpers.checkElementIsVisible(EventPage.eventLocation);
    }
}
