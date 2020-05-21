package com.epam.events.pages;

import org.openqa.selenium.By;

public class EventPage {
    public final  By registrationButton = By.xpath("//*[@id='home']//div[@class='evnt-reg-wrapper']/button");
    public final  By eventProgram = By.id("agenda");

    public final  By eventDate = By.xpath("//li[@class='evnt-day-tab active']/span");
    public final  By eventTime = By.xpath("//div[@class='evnt-agenda-wrapper']//div[@class='evnt-timeline-cell agenda-time']/span");
//    public final  By eventLocation = By.xpath("//div[@class='evnt-content-cell details']/div[1]/span");
}
