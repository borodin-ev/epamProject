package com.epam.events.Pages;

import org.openqa.selenium.By;

public class AllEventsPage {

    public static By upcomingEventsTab = By.xpath("//span[@class='evnt-tab-text desktop'][contains(text(), 'Upcoming Events')]");
    public static By upcomingEventsCounter = By.xpath("//ul[@class='evnt-tabs-list nav nav-tabs']/li[1]//span[@class='evnt-tab-counter evnt-label small white']");

    public static By allEventsCards = By.xpath("//div[contains(@class,'evnt-event-card')]");

    public static By eventLocation = By.xpath(".//div[@class='evnt-event-details-table']//p/span");
    public static By eventLanguage = By.xpath(".//p[@class='language']");
    public static By eventName = By.xpath(".//div[@class='evnt-event-name']//span");
    public static By eventDate = By.xpath(".//div[@class='evnt-dates-cell dates']/p/span[1]");
    public static By eventRegistrationStatus = By.xpath(".//div[@class='evnt-dates-cell dates']/p/span[2]");
    public static By eventSpeakerPhoto = By.xpath(".//div[@class='evnt-photo-wrapper']");

    public static By thisWeekTitle = By.xpath("//div[@class='evnt-cards-container']/h3[contains(text(), 'This week')]");
    public static By allEventsOnThisWeek = By.xpath("//div[@class='evnt-cards-container'][1]//div[@class='evnt-events-column cell-3']");


    public static By pastEventsTab = By.xpath("//span[@class='evnt-tab-text desktop'][contains(text(), 'Past Events')]");
    public static By pastAllEventsTitle = By.xpath("//div[@class='evnt-cards-container']/h3[contains(text() , 'All Events')]");
    public static By locationFilter = By.id("filter_location");
    public static By locationFilterCanadaCheckbox = By.xpath("//div[@data-group='Canada']//div[@class='evnt-checkbox form-check']");
    public static By filerResultMessage = By.xpath("//div[@class='evnt-filters-content-cell evnt-results-cell']/p[contains(text(), ' results found for:')]");
    public static By pastEventsCounter = By.xpath("//ul[@class='evnt-tabs-list nav nav-tabs']/li[2]//span[@class='evnt-tab-counter evnt-label small white']");
}
