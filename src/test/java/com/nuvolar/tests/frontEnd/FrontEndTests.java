package com.nuvolar.tests.frontEnd;

import com.nuvolar.BaseTest;
import com.nuvolar.elementLocators.AmazonItemPage;
import com.nuvolar.elementLocators.AmazonLandingPage;
import com.nuvolar.elementLocators.AmazonSearchResultsPage;
import com.nuvolar.elementLocators.CartPage;

import com.nuvolar.tests.Scenario;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class FrontEndTests extends BaseTest {
    AmazonSearchResultsPage amazonSearchResults = new AmazonSearchResultsPage(driver);
    AmazonLandingPage amazonLanding = new AmazonLandingPage(driver);
    AmazonItemPage amazonItem = new AmazonItemPage(driver);
    CartPage cartPage = new CartPage(driver);

    @Scenario(description = "Search Men Hats And Add it to cart then assert price and amount of items is " +
            "correct search for women hat and add toC cart assert that price and amount is correct change " +
            "quantity of hats added in step3 then assert that quantity and price are updated",
            steps = { "1)Go to https://www.amazon.com - page is opened and all elements are loaded",
            "2)Search for hats for men",
            "3) Add first hat to Cart with quantity 2 - ",
            "4) Open cart and assert total price and quantity are correct",
            "5) Search for hats for women",
            "6) Add first hat to Cart with quantity 1",
            "7) Open cart and assert total price and quantity are correct",
            "8) Change the quantity for item selected at step 3 from 2 to 1 item in Cart",
            "9) Assert total price and quantity are changed correctly"})
    @Test
    public void verifyPriceAndAmountOfItemsAddedToCartIsCorrectAndUpdatesAccordinglyToNumberAndPriceOfAddedItem() throws InterruptedException {

        amazonLanding.searchTextField().sendKeys("hats for men");
        amazonLanding.searchButton().click();
Thread.sleep(5000);
        amazonSearchResults.item(2).click();

        amazonItem.quantityDropdown().click();
        amazonItem.quantityOption(1).click(); //index 0 equals to option 1 etc
        amazonItem.addCartButton().click();
        String manHatPrice = amazonItem.itemPrice().getText();
        amazonItem.cartButton().click();

        String itemPriceInCartBox = cartPage.itemPrice(1).getText();
        String totalPriceBuyBox = cartPage.buyBoxTotalPrice().getText();
        String totalPriceShoppingBox = cartPage.totalPrice().getText();
        String itemQuantity = cartPage.quantityDropdown(1).getText();

        boolean itemsEquality = manHatPrice.equals(itemPriceInCartBox);
        boolean totalPricesEquality = totalPriceBuyBox.equals(totalPriceShoppingBox);
        boolean quantityEquality = itemQuantity.equals("2");
        int expectedTotalPrice = Integer.parseInt(manHatPrice) * Integer.parseInt(itemQuantity);

        assertTrue(itemsEquality, "Error: items in itemPage and cartPage are not matched");
        assertTrue(totalPricesEquality, "Error: totalPrices values are not matched");
        assertTrue(quantityEquality, "Error: quantity values are not matched");
        assertEquals(Integer.parseInt(totalPriceBuyBox), expectedTotalPrice, "Error: total price is not matched with expected");

        amazonLanding.searchTextField().sendKeys("hats for women");
        amazonLanding.searchButton().click();

        amazonSearchResults.item(2).click();
        String womenHatPrice = amazonItem.itemPrice().getText();
        amazonItem.quantityDropdown().click();
        amazonItem.quantityOption(0).click();
        amazonItem.cartButton().click();

        String updatedTotalPriceShoppingBox = cartPage.totalPrice().getText();
        String itemForWomenQuantity = cartPage.quantityDropdown(1).getText();
        int updatedExpectedTotalPrice = expectedTotalPrice + (Integer.parseInt(womenHatPrice) * Integer.parseInt(itemForWomenQuantity));

        assertEquals(updatedExpectedTotalPrice, Integer.parseInt(updatedTotalPriceShoppingBox), "Error: Actual and expected total amount are not matched");
        assertEquals(Integer.parseInt(itemForWomenQuantity), 1, "Error: Actual and expected quantity for woman's hat are not matched");

        cartPage.quantityDropdown(2).click();
        cartPage.quantityDropdownOptionsList(1).click();

        String finalUpdatedTotalPrice = cartPage.totalPrice().getText();
        int finalExpectedTotalPrice = updatedExpectedTotalPrice - Integer.parseInt(manHatPrice);

        assertEquals(finalExpectedTotalPrice, Integer.parseInt(finalUpdatedTotalPrice), "Error: expected and actual final prices are not matched");
        assertEquals(Integer.parseInt(cartPage.quantityDropdown(2).getText()), 1, "Error: expected and actual final quantities are not matched");
    }
}