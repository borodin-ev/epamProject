package com.epam.events.Pages;

import org.openqa.selenium.By;

public class TalkPage {
    public final static  By location = By.cssSelector("div.location");
    public final static  By language = By.cssSelector("div.language");
    public final static  By tag = By.xpath("//div[@class='evnt-topics-wrapper']//label[contains(text(), 'Design')]");
    public final static  By title = By.xpath("//h1[@class='evnt-talk-title']");
}
