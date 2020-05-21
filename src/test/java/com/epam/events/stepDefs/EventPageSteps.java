package com.epam.events.stepDefs;


import com.epam.events.pages.EventPage;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Condition.*;

import static com.codeborne.selenide.Selenide.$;

public class EventPageSteps extends Abstract {
    public EventPageSteps(WebDriver driver) {super(driver);}

    public void checkEventPageDetails() {
        log.info("Check registration button is visible");
        $(EventPage.registrationButton).shouldBe(visible);
        log.info("Check event program is visible");
        $(EventPage.eventProgram).shouldBe(visible);
        log.info("Check event date is visible");
        $(EventPage.eventDate).shouldBe(visible);
        log.info("Check event time is visible");
        $(EventPage.eventTime).shouldBe(visible);
    }
}
