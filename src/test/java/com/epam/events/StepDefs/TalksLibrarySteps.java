package com.epam.events.StepDefs;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.UIAssertionError;
import com.epam.events.Configuration.Configuration;
import com.epam.events.Helpers.Helpers;
import com.epam.events.Pages.EventPage;
import com.epam.events.Pages.TalkPage;
import com.epam.events.Pages.TalksLibraryPage;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.epam.events.Helpers.Helpers.containsText;
import static com.epam.events.Helpers.Helpers.getTalksLinks;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TalksLibrarySteps extends Abstract{
    private static Configuration cfg = ConfigFactory.create(Configuration.class);
    public TalksLibrarySteps(WebDriver driver) {super(driver);}

    public TalksLibrarySteps openPage() {

        log.info("Open " + cfg.mainpage()+cfg.talkLibrary());
        open(cfg.mainpage()+cfg.talkLibrary());

        return this;
    }

    public TalksLibrarySteps openMoreFilters() {
        log.info("Open more filters");
        $(TalksLibraryPage.loader).shouldNot(exist);
        $(TalksLibraryPage.moreFiltersButton).click();
        $(TalksLibraryPage.locationFilterButton).shouldBe(visible);

        return this;
    }

    public TalksLibrarySteps openCategoryFilter() {
        log.info("Open category filter");
        $(TalksLibraryPage.categoryFilterButton).click();

        return this;
    }

    public TalksLibrarySteps openLocationFilter() {
        log.info("Open location filter");
        $(TalksLibraryPage.locationFilterButton).click();

        return this;
    }

    public TalksLibrarySteps openLanguageFilter() {
        log.info("Open language filter");
        $(TalksLibraryPage.languageFilterButton).click();

        return this;
    }

    public TalksLibrarySteps chooseDesign() {
        log.info("Choose design");
        $(TalksLibraryPage.categoryFilterDesignCheckbox).click();
        $(TalksLibraryPage.categoryFilterButton).click();
        $(TalksLibraryPage.filerResultMessage).shouldBe(visible);

        return this;
    }

    public TalksLibrarySteps chooseBelarus() {
        log.info("Choose Belarus");
        $(TalksLibraryPage.locationFilterBelarusCheckbox).click();
        $(TalksLibraryPage.locationFilterButton).click();
        $(TalksLibraryPage.filerResultMessage).shouldBe(visible);

        return this;
    }

    public TalksLibrarySteps chooseEnglish() {
        log.info("Choose english");
        $(TalksLibraryPage.languageFilterEnglishCheckbox).click();
        $(TalksLibraryPage.languageFilterButton).click();
        $(TalksLibraryPage.filerResultMessage).shouldBe(visible);

        return this;
    }

    public TalksLibrarySteps searchByKeyword(String keyword) {
        log.info("Search by keyword " + keyword);
        $(TalksLibraryPage.search).setValue(keyword).pressEnter();
//        $(TalksLibraryPage.popularTalksTitle).shouldNot(exist);

        $$(TalksLibraryPage.talksContainer).shouldHave(CollectionCondition.size(1));
        return this;
    }

    public TalksLibrarySteps loadAllTalks() {
        Helpers.talksLoader();

        return this;
    }

    /*Asserts*/

    public void checkFilteredData() {
        log.info("Check filtered data");
        ArrayList<String> links;

        links = Helpers.getTalksLinks();

        for (String link : links) {
            log.info("Open page " + link);
            open(link);
            $(TalkPage.location).shouldBe(visible);

            log.info("Checking location");
            Helpers.containsText(TalkPage.location,cfg.location());
            log.info("Checking language");
            Helpers.containsText(TalkPage.language,cfg.language());
            log.info("Checking tag");
            $(TalkPage.tag).shouldBe(visible);
        }
    }

    public  void searchForKeyword() {
        log.info("Check keyword in every talk");
        int x = 0;
        ArrayList<String> links;

        links = getTalksLinks();


        for (String link : links) {
            x++;
            log.info("Open page " + link);
            open(link);

            containsText(EventPage.title, cfg.keyword());
            log.info(x + " out of " + links.size() + " talks checked");
        }
    }
}
