package com.epam.events.Pages;

import org.openqa.selenium.By;

public class TalkPage {
    public static By location = By.cssSelector("div.location");
    public static By language = By.cssSelector("div.language");
    public static By tag = By.xpath("//div[@class='evnt-topics-wrapper']//label[contains(text(), 'Design')]");
}
