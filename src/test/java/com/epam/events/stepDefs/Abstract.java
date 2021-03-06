package com.epam.events.stepDefs;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public abstract class Abstract {
    final WebDriver driver;
    Abstract(WebDriver driver) {this.driver = driver;}
    static final Logger log = LogManager.getLogger(Abstract.class);
}
