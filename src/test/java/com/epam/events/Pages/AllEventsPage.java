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
}
