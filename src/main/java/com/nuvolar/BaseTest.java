package com.nuvolar;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.net.URL;
import java.util.logging.*;

public class BaseTest {
    private static final Logger logger = Logger.getLogger(BaseTest.class.getName());
    static {
        try {
            LogManager.getLogManager().readConfiguration(
                    BaseTest.class.getResourceAsStream("log4j.properties")
            );
        } catch (Exception e) {
            logger.severe("Failed to load logging configuration: {}" + e.getMessage());        }
    }

    protected WebDriver driver = new FirefoxDriver();

    @BeforeClass(alwaysRun = true)
    public void setUp() {
        Logger.getLogger("").setLevel(Level.INFO); // Set to desired logging level

        Handler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.INFO);
        logger.addHandler(consoleHandler);

        ClassLoader classLoader = getClass().getClassLoader();
        URL driverPath = classLoader.getResource("resources/geckodriver.exe");
        System.setProperty("webdriver.firefox.driver", String.valueOf(driverPath));
        driver.get("https://www.amazon.com");
        logger.info("Driver initialized: " + driver);
        logger.info("Navigated to: " + driver.getCurrentUrl());
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        }
    }