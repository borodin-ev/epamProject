package com.epam.events.webDriverFactory;

import com.epam.events.configuration.Configuration;
import com.epam.healenium.SelfHealingDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.IOException;
import java.net.URI;

public class WebDriverFactory {
    private static final Logger log = LogManager.getLogger(WebDriverFactory.class);
    private final static Configuration cfg = org.aeonbits.owner.ConfigFactory.create(Configuration.class);
    public static String port = "";

    enum Browsers {
        CHROME,
        FIREFOX,
        REMOTE
    }

        public static SelfHealingDriver create() throws IOException {
        WebDriver delegate;

        Browsers browser = Browsers.valueOf(cfg.browser().toUpperCase());

        com.codeborne.selenide.Configuration.timeout = 8000;
        com.codeborne.selenide.Configuration.startMaximized = true;
        com.codeborne.selenide.Configuration.screenshots = false;
        com.codeborne.selenide.Configuration.savePageSource = false;

        switch (browser) {
            case CHROME:
                log.info("Creating chrome driver");
                WebDriverManager.chromedriver().setup();

                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.setHeadless(true);

                delegate = new ChromeDriver(chromeOptions);
                delegate.manage().window().maximize();
                return SelfHealingDriver.create(delegate);
            case FIREFOX:
                log.info("Creating firefox driver");
                WebDriverManager.firefoxdriver().setup();

                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.setHeadless(true);

                delegate = new FirefoxDriver(firefoxOptions);
                delegate.manage().window().maximize();
                return SelfHealingDriver.create(delegate);
            case REMOTE:
                log.info("Creating remote driver");
                DesiredCapabilities capabilities = new DesiredCapabilities();
                capabilities.setBrowserName("chrome");
                capabilities.setVersion("81.0");
                capabilities.setCapability("enableVNC", true);
                capabilities.setCapability("enableVideo", false);
                capabilities.setCapability("pageLoadingStrategy", "normal");

                RemoteWebDriver remDriver = new RemoteWebDriver(
                        URI.create("http://localhost:"+port+"/wd/hub").toURL(),
                        capabilities);

                remDriver.manage().window().maximize();

                return SelfHealingDriver.create(remDriver);
            default:
                throw new IllegalArgumentException("Wrong browser argument. Choose among CHROME, FIREFOX and REMOTE");
        }
    }
}
