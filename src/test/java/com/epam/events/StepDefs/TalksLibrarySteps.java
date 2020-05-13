package com.epam.events.StepDefs;

import com.epam.events.Pages.TalksLibraryPage;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class TalksLibrarySteps extends Abstract{
    public TalksLibrarySteps(WebDriver driver) {super(driver);}

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
}
