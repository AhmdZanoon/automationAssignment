package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import tools.Assisstant;

public class SauceDemoCartPage {
    private WebDriver driver;
    public SauceDemoCartPage(WebDriver driver){
        this.driver = driver ;
    }

    private By secondaryHeader = By.xpath("//div[@data-test='secondary-header']//span");
    private By productNameContainer (int productNumber){
    return    By.xpath("(//div[@data-test='inventory-item-name'])["+productNumber+"]");
    }

    private By checkoutButton = By.xpath("//button[@data-test='checkout']");

    public void assertThatILandedCartPage() {
        Assert.assertEquals(driver.findElement(secondaryHeader).getText(),"Your Cart");
        new Assisstant().takeScreenShoot(driver);
    }

    public void checkProductsInCart(){
        Assert.assertEquals(driver.findElement(productNameContainer(1)).getText(),"Sauce Labs Fleece Jacket");
        Assert.assertEquals(driver.findElement(productNameContainer(2)).getText(),"Sauce Labs Backpack");
        new Assisstant().takeScreenShoot(driver);
    }

    public void clickCheckout(){
        driver.findElement(checkoutButton).click();
    }
}

