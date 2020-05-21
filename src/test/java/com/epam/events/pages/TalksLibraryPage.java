package com.epam.events.pages;

import org.openqa.selenium.By;

public class TalksLibraryPage {

    public final  By moreFiltersButton = By.xpath("//div[@href='#collapseMoreFilters']");
    public final  By categoryFilterButton = By.id("filter_category");
    public  By categoryFilterCheckbox(String tagName) { return By.xpath("//label[@data-value='"+tagName+"']");}

    public final  By locationFilterButton = By.id("filter_location");
    public  By locationFilterCheckbox(String location) { return By.xpath("//label[@data-value='"+location+"']");}

    public final  By languageFilterButton = By.id("filter_language");
    public  By languageFilterCheckbox(String language) { return By.xpath("//label[@data-value='"+language.toUpperCase()+"']");}

    public final  By filerResultMessage = By.xpath("//div[@class='evnt-filters-content-cell evnt-results-cell']/p[contains(text(), ' results found for:')]");

    public final  By talksCards = By.xpath("//div[@class='evnt-talks-column cell-6']");
    public final  By talkCardTitle = By.xpath(".//div[@class='evnt-talk-name']/h1/span");

    public final  By loader = By.xpath("//div[@class='evnt-cards-loading']");

    public final  By search = By.xpath("//input[@placeholder='Search by Talk Name']");

    public final  By talksContainer = By.xpath("//div[@class='evnt-talks-wrapper']/div");
}
