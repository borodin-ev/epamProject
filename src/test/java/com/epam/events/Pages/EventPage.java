package com.epam.events.Pages;

import org.openqa.selenium.By;

public class EventPage {
    public static By registrationButton = By.xpath("//*[@id='home']//button[contains(text() , 'Wish to attend')]");
    public static By eventProgram = By.xpath("//div[@class='evnt-panel-wrapper']");

    public static By eventDateTime = By.xpath("//div[@class='evnt-content-cell details']/div[2]/span");
    public static By eventLocation = By.xpath("//div[@class='evnt-content-cell details']/div[1]/span");

    public static By title = By.xpath("//h1[@class='evnt-talk-title']");
}
