package com.epam.events.StepDefs;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.UIAssertionError;
import com.epam.events.Configuration.Configuration;
import com.epam.events.Helpers.Helpers;
import com.epam.events.Pages.TalkPage;
import com.epam.events.Pages.TalksLibraryPage;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class TalksLibrarySteps extends Abstract{
    private final static Configuration cfg = ConfigFactory.create(Configuration.class);
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

    public TalksLibrarySteps chooseCategory() {
        log.info("Choose tag " + cfg.tag());
        $(TalksLibraryPage.categoryFilterCheckbox(cfg.tag())).click();
        $(TalksLibraryPage.categoryFilterButton).click();
        $(TalksLibraryPage.filerResultMessage).shouldBe(visible);

        return this;
    }

    public TalksLibrarySteps chooseLocation() {
        log.info("Choose location - " + cfg.location());
        $(TalksLibraryPage.locationFilterCheckbox(cfg.location())).click();
        $(TalksLibraryPage.locationFilterButton).click();
        $(TalksLibraryPage.filerResultMessage).shouldBe(visible);

        return this;
    }

    public TalksLibrarySteps chooseLanguage() {
        log.info("Choose language - " + cfg.language());
        $(TalksLibraryPage.languageFilterCheckbox(cfg.language())).click();
        $(TalksLibraryPage.languageFilterButton).click();
        $(TalksLibraryPage.filerResultMessage).shouldBe(visible);

        return this;
    }

    public TalksLibrarySteps searchByKeyword() {
        log.info("Search by keyword " + cfg.keyword());
        $(TalksLibraryPage.search).setValue(cfg.keyword()).pressEnter();

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
            $(TalkPage.location).shouldHave(text(cfg.location()));
            log.info("Checking language");
            $(TalkPage.language).shouldHave(text(cfg.language()));
            log.info("Checking tag");
            $(TalkPage.tag).shouldBe(visible);
        }
    }

    public  void checkForKeyword() {
        log.info("Check keyword in talk card");
        ArrayList<String> savedLinks = new ArrayList<>();
        int size = $$(TalksLibraryPage.talksCards).size();
        int cardsCounter = 1;
        int linksCounter = 1;

        log.info("Total talk cards - " + size);
        for (SelenideElement talkCard : $$(TalksLibraryPage.talksCards)) {
            log.info("Checking " + cardsCounter + " card");
            try {
                talkCard.$(TalksLibraryPage.talkCardTitle).shouldHave(text(cfg.keyword()));
            }
            catch (UIAssertionError ex) {
                log.warn("Didn't find keyword \"" + cfg.keyword() + "\" in talk card. Title too long?");
                log.info("Save url of talk and check it later");
                savedLinks.add(talkCard.$(By.xpath("./div/a")).getAttribute("href"));
            }
            cardsCounter++;
        }

        if(!savedLinks.isEmpty()) {
            for (String link : savedLinks) {

                log.info("Open page " + link);
                open(link);

                $(TalkPage.title).shouldHave(text(cfg.keyword()));
                log.info(linksCounter + " out of " + savedLinks.size() + " talks checked");
                linksCounter++;
            }
        }
    }
}
