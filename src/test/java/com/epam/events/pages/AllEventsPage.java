package com.epam.events.pages;

import org.openqa.selenium.By;

public class AllEventsPage {

    public final static By upcomingEventsTab = By.xpath("//li[@class='evnt-tab-item nav-item']/a/span[contains(text(), 'Upcoming')]");
    public final static By upcomingEventsCounter = By.xpath("//ul[@class='evnt-tabs-list nav nav-tabs']/li[1]//span[@class='evnt-tab-counter evnt-label small white']");

    public final static By allEventsCards = By.xpath("//div[contains(@class,'evnt-event-card')]");

    public final static By eventLocation = By.xpath(".//div[@class='evnt-card-heading']//div[contains(@class,'online-cell') and following-sibling::div[contains(@class,'language-cell')]]");
    public final static By eventLanguage = By.xpath(".//div[@class='evnt-card-heading']//div[contains(@class,'language-cell') and preceding-sibling::div[contains(@class,'online-cell')]]");
    public final static By eventName = By.xpath(".//div[@class='evnt-card-body' and preceding-sibling::div[@class='evnt-card-heading']]//div[@class='evnt-event-name' and following-sibling::div[@class='evnt-event-dates']]");
    public final static By eventDate = By.xpath(".//div[@class='evnt-card-body' and preceding-sibling::div[@class='evnt-card-heading']]//div[@class='evnt-event-dates' and preceding-sibling::div[@class='evnt-event-name']]//span[@class='date']");
    public final static By eventRegistrationStatus = By.xpath(".//div[@class='evnt-card-body' and preceding-sibling::div[@class='evnt-card-heading']]//div[@class='evnt-event-dates' and preceding-sibling::div[@class='evnt-event-name']]//span[contains(@class,'status') and preceding-sibling::span[@class='date']]");
    public final static By eventSizeMSpeakers = By.xpath(".//div[@class='evnt-card-footer' and preceding-sibling::div[@class='evnt-card-body']]//div[@class='evnt-speaker']");
    public final static By eventSizeSSpeakers = By.xpath(".//div[@class='evnt-card-body' and preceding-sibling::div[@class='evnt-card-heading']]//div[@class='evnt-dates-cell speakers']/div");


    public final static By thisWeekTitle = By.xpath("//div[@class='evnt-cards-container']/h3[contains(text(), 'This week')]");

    public final static By allEventsOnThisWeek = By.xpath("//div[@class='evnt-cards-container'][1]//div[@class='evnt-events-column cell-3']");


    public final static By pastEventsTab = By.xpath("//li[@class='evnt-tab-item nav-item']/a/span[contains(text(), 'Past')]");
    public final static By pastAllEventsTitle = By.xpath("//div[@class='evnt-cards-container']/h3[contains(text() , 'All Events')]");
    public final static By locationFilter = By.id("filter_location");
    public final static By locationFilterLocationCheckbox(String location)  {return By.xpath("//div[@data-group='"+location+"']//div[@class='evnt-checkbox form-check']");}
    public final static By filerResultMessage = By.xpath("//div[@class='evnt-filters-content-cell evnt-results-cell']");
    public final static By pastEventsCounter = By.xpath("//ul[@class='evnt-tabs-list nav nav-tabs']/li[2]//span[@class='evnt-tab-counter evnt-label small white']");

    public final static By header = By.xpath("//header");
    public final static By loginBottom = By.xpath("//div[@id='modal-app']/div/div");
}
