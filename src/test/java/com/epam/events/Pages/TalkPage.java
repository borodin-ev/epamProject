package com.epam.events.Pages;

import org.openqa.selenium.By;

public class TalkPage {
    public static By location = By.xpath("//div[@class='evnt-card-cell details-cell']/div[2]/span");
    public static By language = By.xpath("//div[@class='evnt-card-cell details-cell']/div[3]/span");
    public static By tags = By.xpath("//div[@class='evnt-topics-wrapper']//label");
}
