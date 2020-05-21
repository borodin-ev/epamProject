package com.epam.events;

import com.epam.events.helpers.Watcher;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(Watcher.class)
public class EventsPortalTest extends Hooks {

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
        String location = "Canada";

        allEventsPageSteps.openPage().
                openPastEvents().
                chooseLocationFilter(location).
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
        String category= "Design";
        String location = "Belarus";
        String language = "English";

        talksLibrarySteps.openPage().
                chooseCategory(category).
                openMoreFilters().
                chooseLocation(location).
                chooseLanguage(language).
                checkFilteredData(category,location,language);
    }

    @Test
    @DisplayName("Check search by keyword")
    void searchReportsByKeyword() {
        String keyword = "azure";

        talksLibrarySteps.openPage().
                searchByKeyword(keyword).
                checkForKeywordInResults(keyword);
    }
}
