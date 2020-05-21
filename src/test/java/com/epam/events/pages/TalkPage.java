package com.epam.events.pages;

import org.openqa.selenium.By;

public class TalkPage {
    public final  By location = By.cssSelector("div.location");
    public final  By language = By.cssSelector("div.language");
    public  By tag (String tagName) { return By.xpath("//div[@class='evnt-topics-wrapper']//label[contains(text(), '"+tagName+"')]");}
    public final  By title = By.xpath("//h1[@class='evnt-talk-title']");
}
