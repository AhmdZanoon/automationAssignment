package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import tools.Assisstant;

public class SauceDemoProductsPage {

    private WebDriver driver;

    public SauceDemoProductsPage(WebDriver driver) {
        this.driver = driver;
    }

    private By secondaryHeader = By.xpath("//div[@data-test='secondary-header']//span");

    private By filterDropDown = By.xpath("//select[@data-test='product-sort-container']");

    private By addProductToCart(int optionOrder) {
        return By.xpath("(//div[@data-test='inventory-list']//button)[" + optionOrder + "]");
    }

    private By shopingCartLink = By.xpath("//a[@data-test='shopping-cart-link']");
    private By shoppingCartIcon = By.xpath("//a[@data-test='shopping-cart-link']//span");

    public void assertThatILandProductsPage() {
        driver.findElement(secondaryHeader).isDisplayed();
        Assert.assertEquals(driver.findElement(secondaryHeader).getText(),"Products");
        new Assisstant().takeScreenShoot(driver);
    }


    public void filterProductsByHighPriceToLow() {
        new Select(driver.findElement(filterDropDown)).selectByValue("hilo");
    }

    public void selectMostExpensiveTwoProducts(int productOrder) {
        driver.findElement(addProductToCart(productOrder)).click();
    }

    public void assertThatCartIconHasNumberOfProducts(String totalNumberOfProducts){
     Assert.assertEquals(driver.findElement(shoppingCartIcon).getText(),totalNumberOfProducts);

    }

    public void goToCartPage() {
        driver.findElement(shopingCartLink).click();
    }
}
