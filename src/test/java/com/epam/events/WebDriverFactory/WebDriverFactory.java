package com.epam.events.WebDriverFactory;

import com.codeborne.selenide.WebDriverRunner;
import com.epam.events.Configuration.Configuration;
import com.epam.healenium.SelfHealingDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URI;

public class WebDriverFactory {
    private SelfHealingDriver driver;
    private final static Configuration cfg = org.aeonbits.owner.ConfigFactory.create(Configuration.class);

    enum Browsers {
        CHROME,
        FIREFOX,
        REMOTE
    }

    public static void create() throws MalformedURLException {

        Browsers browser = Browsers.valueOf(cfg.browser().toUpperCase());

        WebDriver delegate;
        WebDriverFactory wdf = new WebDriverFactory();

        switch (browser) {
            case CHROME:
                WebDriverManager.chromedriver().setup();

                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.setHeadless(false);

                delegate = new ChromeDriver(chromeOptions);

                wdf.driver = SelfHealingDriver.create(delegate);
                break;
            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();

                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.setHeadless(true);

                delegate = new FirefoxDriver(firefoxOptions);

                wdf.driver = SelfHealingDriver.create(delegate);
                break;
            case REMOTE:
                DesiredCapabilities capabilities = new DesiredCapabilities();
                capabilities.setBrowserName("chrome");
                capabilities.setVersion("81.0");
                capabilities.setCapability("enableVNC", true);
                capabilities.setCapability("enableVideo", false);
                capabilities.setCapability("pageLoadingStrategy", "normal");

                RemoteWebDriver remDriver = new RemoteWebDriver(
                        URI.create("http://localhost:4444/wd/hub").toURL(),
                        capabilities);

                wdf.driver = SelfHealingDriver.create(remDriver);
                break;

            default:
                break;
        }
//        wdf.driver.manage().window().maximize();
        wdf.driver.manage().window().setSize(new Dimension(1200, 800));
        WebDriverRunner.setWebDriver(wdf.driver);
        com.codeborne.selenide.Configuration.timeout = 8000;
        com.codeborne.selenide.Configuration.screenshots = false;
        com.codeborne.selenide.Configuration.savePageSource = false;
    }
}
