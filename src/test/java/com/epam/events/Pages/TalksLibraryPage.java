package com.epam.events.Pages;

import org.openqa.selenium.By;

public class TalksLibraryPage {

    public static By moreFiltersButton = By.xpath("//div[@href='#collapseMoreFilters']");
    public static By categoryFilterButton = By.id("filter_category");
    public static By categoryFilterCheckbox(String tagName) { return By.xpath("//label[@data-value='"+tagName+"']");}

    public static By locationFilterButton = By.id("filter_location");
    public static By locationFilterCheckbox(String location) { return By.xpath("//label[@data-value='"+location+"']");}

    public static By languageFilterButton = By.id("filter_language");
    public static By languageFilterCheckbox(String language) { return By.xpath("//label[@data-value='"+language.toUpperCase()+"']");}

    public static By filerResultMessage = By.xpath("//div[@class='evnt-filters-content-cell evnt-results-cell']/p[contains(text(), ' results found for:')]");

    public static By talksCards = By.xpath("//div[@class='evnt-talks-column cell-6']");
    public static By talkCardTitle = By.xpath(".//div[@class='evnt-talk-name']/h1/span");

    public static By loader = By.xpath("//div[@class='evnt-cards-loading']");

    public static By search = By.xpath("//input[@placeholder='Search by Talk Name']");

    public static By talksContainer = By.xpath("//div[@class='evnt-talks-wrapper']/div");
}
