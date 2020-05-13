package com.epam.events.StepDefs;

import com.epam.events.Configuration.Configuration;
import com.epam.events.Pages.AllEventsPage;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;

import java.util.Random;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class AllEventsPageSteps extends Abstract {
    public AllEventsPageSteps(WebDriver driver) {super(driver);}
    private static Configuration cfg = ConfigFactory.create(Configuration.class);

    public AllEventsPageSteps openUpcomingEvents() {
        log.info("Click on upcoming events tab");
        $(AllEventsPage.upcomingEventsTab).click();

        return this;
    }

}
