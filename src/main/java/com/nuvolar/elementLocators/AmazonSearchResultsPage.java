package com.nuvolar.elementLocators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AmazonSearchResultsPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public AmazonSearchResultsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(1));
    }

    public WebElement item(int itemIndex) {
        return wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector(String.format("div[data-cel-widget='search_result_%s']", itemIndex)))));
    }
}
