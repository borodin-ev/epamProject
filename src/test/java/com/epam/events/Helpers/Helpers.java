package com.epam.events.Helpers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Helpers {
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
}
