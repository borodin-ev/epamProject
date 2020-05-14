package com.epam.events.Pages;

import org.openqa.selenium.By;

public class EventPage {
    public static By registrationButton = By.xpath("//*[@id='home']//div[@class='evnt-reg-wrapper']/button");
    public static By eventProgram = By.id("agenda");

    public static By eventDate = By.xpath("//li[@class='evnt-day-tab active']/span");
    public static By eventTime = By.xpath("//div[@class='evnt-agenda-wrapper']//div[@class='evnt-timeline-cell agenda-time']/span");
    public static By eventLocation = By.xpath("//div[@class='evnt-content-cell details']/div[1]/span");
}
