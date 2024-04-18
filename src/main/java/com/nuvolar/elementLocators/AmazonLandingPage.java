package com.nuvolar.elementLocators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AmazonLandingPage {
    private final WebDriver driver;
    public WebElement searchButton;
    private final WebDriverWait wait;

    public AmazonLandingPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(1));
    }
    public WebElement searchTextField() {
        return wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("twotabsearchtextbox"))));
    }
    public WebElement searchButton() {
        return searchButton = driver.findElement(By.id("nav-search-submit-button"));
    }
}