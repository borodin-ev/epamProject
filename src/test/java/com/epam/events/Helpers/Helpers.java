package com.epam.events.Helpers;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import com.epam.events.Hooks;
import com.epam.events.Pages.TalksLibraryPage;
import com.epam.events.WebDriverFactory.WebDriverFactory;
import com.epam.healenium.SelfHealingDriver;
import com.epam.healenium.SelfHealingEngine;
import com.epam.healenium.annotation.DisableHealing;
import com.epam.healenium.handlers.proxy.SelfHealingProxyInvocationHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
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
    public static void talksLoader() {
        log.info("Loading all talks");
        int talksSize = $$(TalksLibraryPage.talksCards).size();
        log.info(talksSize + " talks at this moment");
        do {
            try {
                executeJavaScript("arguments[0].scrollIntoView();", $$(TalksLibraryPage.talksCards).last());
                $(TalksLibraryPage.loader).shouldNot(exist);
//                executeJavaScript("arguments[0].scrollIntoView();", $$(TalksLibraryPage.talksCards).first());
            }
            catch (UndeclaredThrowableException ex) {
                executeJavaScript("arguments[0].scrollIntoView();", $$(TalksLibraryPage.talksCards).last());
                $(TalksLibraryPage.loader).shouldNot(exist);
//                executeJavaScript("arguments[0].scrollIntoView();", $$(TalksLibraryPage.talksCards).first());
            }

            if(talksSize == $$(TalksLibraryPage.talksCards).size()) {
                log.info("All talks are loaded. Quantity of talks is " + $$(TalksLibraryPage.talksCards).size());
                break;
            }
            talksSize = $$(TalksLibraryPage.talksCards).size();
            log.info(talksSize + " talks are loaded at the moment.");
        }
        while(true);
    }

    public static ArrayList<String> getTalksLinks() {
        ArrayList<String> links = new ArrayList<>();

        for (SelenideElement talk : $$(TalksLibraryPage.talksCards)) {
            links.add(talk.$(By.xpath("./div/a")).getAttribute("href"));
        }
        return links;
    }

    public String takeScreenshot() throws IOException {
        BufferedImage image = new AShot().
                shootingStrategy(ShootingStrategies.viewportPasting(100)).
                takeScreenshot(WebDriverRunner.getWebDriver()).getImage();

        File outputFile = new File(createImage());
        ImageIO.write(image, "jpg", outputFile);

        return outputFile.getAbsolutePath();
    }

    public static String createImage() throws IOException {
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
            return "m";
        }
        else {
            return "s";
        }
    }
}
