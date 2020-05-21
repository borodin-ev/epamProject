package com.epam.events.stepDefs;


import com.epam.events.pages.EventPage;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Condition.*;

import static com.codeborne.selenide.Selenide.$;

public class EventPageSteps extends Abstract {
    public EventPageSteps(WebDriver driver) {super(driver);}
    EventPage eventPage = new EventPage();

    public void checkEventPageDetails() {
        log.info("Check registration button is visible");
        $(eventPage.registrationButton).shouldBe(visible);
        log.info("Check event program is visible");
        $(eventPage.eventProgram).shouldBe(visible);
        log.info("Check event date is visible");
        $(eventPage.eventDate).shouldBe(visible);
        log.info("Check event time is visible");
        $(eventPage.eventTime).shouldBe(visible);
    }
}
