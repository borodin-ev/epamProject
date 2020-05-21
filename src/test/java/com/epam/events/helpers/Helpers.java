package com.epam.events.helpers;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import com.epam.events.Hooks;
import com.epam.events.pages.AllEventsPage;
import com.epam.events.pages.TalkPage;
import com.epam.events.pages.TalksLibraryPage;
import com.epam.healenium.SelfHealingDriver;
import com.epam.healenium.annotation.DisableHealing;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.UndeclaredThrowableException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class Helpers extends Hooks {
    private static final Logger log = LogManager.getLogger(Helpers.class);

    public static LocalDate getLastDayOfWeek() {

        log.info("Get date of last day of week");
        return LocalDate.now().with(DayOfWeek.SUNDAY);
    }

    public static LocalDate nowDate() {
        log.info("Get date of today");
        return LocalDate.now();
    }

    public static LocalDate convert(String dateStr) {
        if (dateStr.contains("-")) {
            dateStr = dateStr.substring(dateStr.indexOf("-") + 2);
        }
        log.info("Convert date from string");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy", Locale.US);

        return LocalDate.parse(dateStr, formatter);
    }

    @DisableHealing
    public static void talksLoader(TalksLibraryPage talksLibraryPage) {
        
        log.info("Loading all talks");
        int talksSize = $$(talksLibraryPage.talksCards).size();
        log.info(talksSize + " talks at this moment");
        do {
            try {
                executeJavaScript("arguments[0].scrollIntoView();", $$(talksLibraryPage.talksCards).last());
                $(talksLibraryPage.loader).shouldNot(exist);
//                executeJavaScript("arguments[0].scrollIntoView();", $$(talksLibraryPage.talksCards).first());
            }
            catch (UndeclaredThrowableException ex) {
                executeJavaScript("arguments[0].scrollIntoView();", $$(talksLibraryPage.talksCards).last());
                $(talksLibraryPage.loader).shouldNot(exist);
//                executeJavaScript("arguments[0].scrollIntoView();", $$(talksLibraryPage.talksCards).first());
            }

            if(talksSize == $$(talksLibraryPage.talksCards).size()) {
                log.info("All talks are loaded. Quantity of talks is " + $$(talksLibraryPage.talksCards).size());
                break;
            }
            talksSize = $$(talksLibraryPage.talksCards).size();
            log.info(talksSize + " talks are loaded at the moment.");
        }
        while(true);
    }

    public static ArrayList<String> getTalksLinks(TalksLibraryPage talksLibraryPage) {
        ArrayList<String> links = new ArrayList<>();

        for (SelenideElement talk : $$(talksLibraryPage.talksCards)) {
            links.add(talk.$(By.xpath("./div/a")).getAttribute("href"));
        }
        return links;
    }

    public String takeScreenshot() throws IOException {
        deleteElements();
        BufferedImage image = new AShot().
                shootingStrategy(ShootingStrategies.viewportPasting(100)).
                takeScreenshot(((SelfHealingDriver) WebDriverRunner.getWebDriver()).getDelegate()).getImage();

        File outputFile = new File(createImage());
        ImageIO.write(image, "jpg", outputFile);

        return outputFile.getAbsolutePath();
    }

    public static String createImage() throws IOException {
        String directoryName = "build/ashot";
        File directory = new File(directoryName);
        if (! directory.exists()){
            directory.mkdirs();
        }

        File myObj = new File("build/ashot/image-" + LocalDateTime.now() + ".jpg");
        if (myObj.createNewFile()) {
            log.info("File created: " + myObj.getName());
        } else {
            log.info("File already exists.");
        }
        return myObj.getAbsolutePath();
    }

    public static String eventCardSizeChecker(SelenideElement element) {
        String className;
        className = element.getAttribute("class");

        if(className.contains("size-m")) {
            return "M";
        }
        else {
            return "S";
        }
    }

    private void deleteElements() {
        AllEventsPage allEventsPage = new AllEventsPage();
        if ($(allEventsPage.header).isDisplayed()) {
            executeJavaScript("arguments[0].parentNode.removeChild(arguments[0])", $(allEventsPage.header));
        }
        if ($(allEventsPage.loginBottom).isDisplayed()) {
            executeJavaScript("arguments[0].parentNode.removeChild(arguments[0])", $(allEventsPage.loginBottom));
        }
    }
}
