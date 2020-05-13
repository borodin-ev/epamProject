package com.epam.events;

import com.epam.events.Pages.AllEventsPage;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class Asserts {

    public static void upcomingEventsCompareWithTab() {
        $$(AllEventsPage.allEventsCards).shouldHaveSize
                (Integer.parseInt($(AllEventsPage.upcomingEventsCounter).getText()));
    }
}
