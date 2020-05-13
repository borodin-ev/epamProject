package com.epam.events.Pages;

import org.openqa.selenium.By;

public class AllEventsPage {

    public static By upcomingEventsTab = By.xpath("//span[@class='evnt-tab-text desktop'][contains(text(), 'Upcoming Events')]");
    public static By upcomingEventsCounter = By.xpath("//ul[@class='evnt-tabs-list nav nav-tabs']/li[1]//span[@class='evnt-tab-counter evnt-label small white']");

    public static By allEventsCards = By.xpath("//div[contains(@class,'evnt-event-card')]");



}
