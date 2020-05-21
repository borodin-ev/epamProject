package com.epam.events.stepDefs;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.UIAssertionError;
import com.epam.events.configuration.Configuration;
import com.epam.events.helpers.Helpers;
import com.epam.events.pages.TalkPage;
import com.epam.events.pages.TalksLibraryPage;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class TalksLibrarySteps extends Abstract{
    private final static Configuration cfg = ConfigFactory.create(Configuration.class);
    public TalksLibrarySteps(WebDriver driver) {super(driver);}
    TalksLibraryPage talksLibraryPage = new TalksLibraryPage();

    public TalksLibrarySteps openPage() {
        log.info("Open " + cfg.mainpage()+cfg.talkLibrary());
        open(cfg.mainpage()+cfg.talkLibrary());

        return this;
    }

    public TalksLibrarySteps openMoreFilters() {
        log.info("Open more filters");
        $(talksLibraryPage.loader).shouldNot(exist);
        $(talksLibraryPage.moreFiltersButton).click();
        $(talksLibraryPage.locationFilterButton).shouldBe(visible);

        return this;
    }

    public TalksLibrarySteps chooseCategory(String tag) {
        log.info("Open category filter");
        $(talksLibraryPage.categoryFilterButton).click();

        log.info("Choose category " + tag);
        $(talksLibraryPage.categoryFilterCheckbox(tag)).click();
        $(talksLibraryPage.categoryFilterButton).click();
        $(talksLibraryPage.filerResultMessage).shouldBe(visible);

        return this;
    }

    public TalksLibrarySteps chooseLocation(String location) {
        log.info("Open location filter");
        $(talksLibraryPage.locationFilterButton).click();

        log.info("Choose location - " + location);
        $(talksLibraryPage.locationFilterCheckbox(location)).click();
        $(talksLibraryPage.locationFilterButton).click();
        $(talksLibraryPage.filerResultMessage).shouldBe(visible);

        return this;
    }

    public TalksLibrarySteps chooseLanguage(String language) {
        log.info("Open language filter");
        $(talksLibraryPage.languageFilterButton).click();

        log.info("Choose language - " + language);
        $(talksLibraryPage.languageFilterCheckbox(language)).click();
        $(talksLibraryPage.languageFilterButton).click();
        $(talksLibraryPage.filerResultMessage).shouldBe(visible);

        return this;
    }

    public TalksLibrarySteps searchByKeyword(String keyword) {
        log.info("Search by keyword " + keyword);
        $(talksLibraryPage.search).setValue(keyword).pressEnter();

        $$(talksLibraryPage.talksContainer).shouldHave(CollectionCondition.size(1));
        return this;
    }

    /*Asserts*/

    public void checkFilteredData(String tag, String location, String language) {
        TalkPage talkPage = new TalkPage();
        Helpers.talksLoader(talksLibraryPage);

        log.info("Check filtered data");
        ArrayList<String> links;

        links = Helpers.getTalksLinks(talksLibraryPage);

        for (String link : links) {
            log.info("Open page " + link);
            open(link);
            $(talkPage.location).shouldBe(visible);

            log.info("Checking location");
            $(talkPage.location).shouldHave(text(location));
            log.info("Checking language");
            $(talkPage.language).shouldHave(text(language));
            log.info("Checking tag");
            $(talkPage.tag(tag)).shouldBe(visible);
        }
    }

    public  void checkForKeywordInResults(String keyword) {
        Helpers.talksLoader(talksLibraryPage);

        log.info("Check keyword in talk card");
        ArrayList<String> savedLinks = new ArrayList<>();
        int size = $$(talksLibraryPage.talksCards).size();
        int cardsCounter = 1;
        int linksCounter = 1;

        log.info("Total talk cards - " + size);
        for (SelenideElement talkCard : $$(talksLibraryPage.talksCards)) {
            log.info("Checking " + cardsCounter + " card");
            try {
                talkCard.$(talksLibraryPage.talkCardTitle).shouldHave(text(keyword));
            }
            catch (UIAssertionError ex) {
                log.warn("Didn't find keyword \"" + keyword + "\" in talk card. Title too long?");
                log.info("Save url of talk and check it later");
                savedLinks.add(talkCard.$(By.xpath("./div/a")).getAttribute("href"));
            }
            cardsCounter++;
        }

        if(!savedLinks.isEmpty()) {
            TalkPage talkPage = new TalkPage();
            for (String link : savedLinks) {

                log.info("Open page " + link);
                open(link);

                $(talkPage.title).shouldHave(text(keyword));
                log.info(linksCounter + " out of " + savedLinks.size() + " talks checked");
                linksCounter++;
            }
        }
    }
}
