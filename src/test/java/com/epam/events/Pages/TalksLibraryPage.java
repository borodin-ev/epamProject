package com.epam.events.Pages;

import org.openqa.selenium.By;

public class TalksLibraryPage {

    public final static  By moreFiltersButton = By.xpath("//div[@href='#collapseMoreFilters']");
    public final static  By categoryFilterButton = By.id("filter_category");
    public static  By categoryFilterCheckbox(String tagName) { return By.xpath("//label[@data-value='"+tagName+"']");}

    public final static  By locationFilterButton = By.id("filter_location");
    public static  By locationFilterCheckbox(String location) { return By.xpath("//label[@data-value='"+location+"']");}

    public final static  By languageFilterButton = By.id("filter_language");
    public static  By languageFilterCheckbox(String language) { return By.xpath("//label[@data-value='"+language.toUpperCase()+"']");}

    public final static  By filerResultMessage = By.xpath("//div[@class='evnt-filters-content-cell evnt-results-cell']/p[contains(text(), ' results found for:')]");

    public final static  By talksCards = By.xpath("//div[@class='evnt-talks-column cell-6']");
    public final static  By talkCardTitle = By.xpath(".//div[@class='evnt-talk-name']/h1/span");

    public final static  By loader = By.xpath("//div[@class='evnt-cards-loading']");

    public final static  By search = By.xpath("//input[@placeholder='Search by Talk Name']");

    public final static  By talksContainer = By.xpath("//div[@class='evnt-talks-wrapper']/div");
}
