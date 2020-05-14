package com.epam.events;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.UIAssertionError;
import com.epam.events.Configuration.Configuration;
import com.epam.events.Pages.AllEventsPage;
import com.epam.events.Pages.EventPage;
import com.epam.events.Pages.TalkPage;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import java.time.LocalDate;
import java.util.ArrayList;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Selenide.*;
import static com.epam.events.Helpers.Helpers.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Asserts {
    private static Configuration cfg = ConfigFactory.create(Configuration.class);
    private static final Logger log = LogManager.getLogger(Asserts.class);




















}
