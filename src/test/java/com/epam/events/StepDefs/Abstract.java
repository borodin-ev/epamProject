package com.epam.events.StepDefs;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public abstract class Abstract {
    WebDriver driver;
    static final Logger log = LogManager.getLogger(Abstract.class);
    Abstract(WebDriver driver) {this.driver = driver;}
}
