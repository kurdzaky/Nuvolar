package com.nuvolar.elementLocators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(1));
    }
    public WebElement totalPrice() {
        return driver.findElement(By.id("sc-subtotal-amount-activecart"));
    }
    public WebElement buyBoxTotalPrice() {
        return driver.findElement(By.id("sc-subtotal-amount-buybox"));
    }
    public WebElement itemPrice(int itemIndex) {
        return driver.findElement(By.cssSelector(String.format("div[data-item-index='%s']", itemIndex)));
    }
    public WebElement quantityDropdown(int itemIndex) {
        return driver.findElement(By.id(String.format("div[data-item-index='%s'] span.a-dropdown-prompt", itemIndex)));
    }
    public WebElement quantityDropdownOptionsList(int amount) {
        return driver.findElement(By.id(String.format("quantity_%s", amount)));
    }
}
