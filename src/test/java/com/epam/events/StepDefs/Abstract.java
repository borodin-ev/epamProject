package com.epam.events.StepDefs;

import com.epam.events.Configuration.Configuration;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public abstract class Abstract {
    WebDriver driver;
    Abstract(WebDriver driver) {this.driver = driver;}
    static final Logger log = LogManager.getLogger(Abstract.class);
}
