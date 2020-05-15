package com.epam.events;

import com.epam.events.Configuration.Configuration;
import com.epam.events.Helpers.Watcher;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(Watcher.class)
public class EventsPortalTest extends Hooks {
    private final Configuration cfg = ConfigFactory.create(Configuration.class);

    @Test
    @DisplayName("View Upcoming Events Test")
    void viewUpcomingEventsTest () {
        allEventsPageSteps.openPage().
                openUpcomingEvents().
                upcomingEventsCompareWithTab();
    }

    @Test
    @DisplayName("Check event card elements")
    void viewEventCards() {
        allEventsPageSteps.openPage().
                openUpcomingEvents().
                checkElementsInEventCard();
    }

    @Test
    @DisplayName("Events date validation")
    void eventsDateValidation() {
        allEventsPageSteps.openPage().
                openUpcomingEvents().
                checkEventsThisWeekMoreThanZero().
                checkEventsDatesThisWeek();
    }

    @Test
    @DisplayName("Check event dates in Canada")
    void viewPastEventsInCanada() {
        allEventsPageSteps.openPage().
                openPastEvents().
                openLocationFilter().
                chooseCanadaLocation().
                pastEventsCompareWithTab().
                checkEventsDatesInPast();
    }

    @Test
    @DisplayName("Check event page details")
    void viewEventDetails() {
        allEventsPageSteps.openPage().
                openUpcomingEvents().
                openRandomEvent().
                checkEventPageDetails();
    }

    @Test
    @DisplayName("Check filters")
    void reportCategoryFilter() {
        talksLibrarySteps.openPage().
                openCategoryFilter().
                chooseCategory().
                openMoreFilters().
                openLocationFilter().
                chooseLocation().
                openLanguageFilter().
                chooseLanguage().
                loadAllTalks().
                checkFilteredData();
    }

    @Test
    @DisplayName("Check search by keyword")
    void searchReportsByKeyword() {
        talksLibrarySteps.openPage().
                searchByKeyword(cfg.keyword()).
                loadAllTalks().
                searchForKeyword();
    }
}
