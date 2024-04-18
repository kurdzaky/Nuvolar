package com.nuvolar.elementLocators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AmazonItemPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    public AmazonItemPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(1));
    }

public WebElement quantityDropdown() {
    return wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("a-autoid-16"))));
}
public WebElement quantityOption(int numberOfItems) {
    return wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id(String.format("quantity_%s", numberOfItems)))));
}
    public WebElement addCartButton() {
        return driver.findElement(By.id("add-to-cart-button"));
    }
    public WebElement itemPrice() {
        return driver.findElement(By.cssSelector("span.priceToPay span.a-price-whole"));
    }
    public WebElement cartButton() {
        return driver.findElement(By.id("nav-cart-count"));
    }
}