package webTests.cucumberSteps;

import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import pages.*;
import tools.Assisstant;

public class Steps {
    private WebDriver driver;


    @Given("I Open The Browser")
    public void I_Open_The_Browser() {
        this.driver = new Helper().browserSetup();
    }

    @When("I Navigate To Url")
    public void I_Navigate_To_Url() {
        new SauceDemoLoginPage(driver).navigateToUrl();
    }
    //*******************************************************************//
    @When("I Enter Wrong User Name And Password")
    public void I_Enter_Wrong_Credentials() {
        new SauceDemoLoginPage(driver).enterUsernameAndPassword("wrongUsername","wrongPassword");
    }

    @Then("I Receive Wrong User name Error Message")
    public void System_Display_Error_Message() {
        new SauceDemoLoginPage(driver).assertErrorMessage(new SauceDemoLoginPage(driver).wrongUserNameAndPassword);
    }


    //*******************************************************************//
    @When("I Enter Only Password")
    public void I_Enter_Only_Password() {
        new SauceDemoLoginPage(driver).enterUsernameAndPassword("","secret_Sauce");
    }
    @Then("I Receive Missing User name is Required Error Message")
    public void System_Display_Username_Is_Required_Error_Message() {
        new SauceDemoLoginPage(driver).assertErrorMessage(new SauceDemoLoginPage(driver).usernameIsRequired);
    }
    //*******************************************************************//
    @When("I Enter Only User Name")
    public void I_Enter_Only_Username() {
        new SauceDemoLoginPage(driver).enterUsernameAndPassword("wrongUsername","");
    }
    @Then("I Receive Missing Password is Required Error Message")
    public void System_Display_Password_Is_Required_Error_Message() {
        new SauceDemoLoginPage(driver).assertErrorMessage(new SauceDemoLoginPage(driver).passwordIsRequired);
    }
    //*******************************************************************//
    @When("I Enter Correct User Name And Password")
    public void I_Enter_Correct_Credentials() {
        new SauceDemoLoginPage(driver).enterUsernameAndPassword("standard_user","secret_sauce");
    }
    @Then("I Should Land Products Page And Products Appear")
    public void assertThatProductsAppeared(){
        new SauceDemoProductsPage(driver).assertThatILandProductsPage();
    }

    @When("I Click Filter Icon")
    public void filterProductsByPriceHighToLow(){
        new SauceDemoProductsPage(driver).filterProductsByHighPriceToLow();
    }
    @When("I Click Add To Cart")
    public void addTheMostTwoProductsToCart(){
        new SauceDemoProductsPage(driver).selectMostExpensiveTwoProducts(1);
        new SauceDemoProductsPage(driver).selectMostExpensiveTwoProducts(2);
    }

    @Then("Shopping Cart Will Have Red Icon With Total Added Products")
    public void assertThatCartIconHasTotalNumberOfChosenProducts(){
        new SauceDemoProductsPage(driver).assertThatCartIconHasNumberOfProducts("2");
    }

    @When("I Click Shopping Cart Icon")
    public void clickShoppingCart(){
        new SauceDemoProductsPage(driver).goToCartPage();
    }

    @Then("I Should Land Cart Page")
    public void assertThatIamInCartPage(){
        new SauceDemoCartPage(driver).assertThatILandedCartPage();
    }

    @And("I Should Found The Products I chose in Products page")
    public void checkProductsInCart(){
        new SauceDemoCartPage(driver).checkProductsInCart();
    }

    @When("I Click Checkout Button")
    public void clickCheckout(){
        new SauceDemoCartPage(driver).clickCheckout();
    }

    @Then("I Should Land Checkout: Your Information")
    public void assertThatIamLandedInCheckOutYourInformation(){
        new SauceDemoCheckoutInformationPage(driver).assertThatILandedCheckOutInformationPage();
    }

    @When("I Fill My information")
    public void fillMyInformation(){
        new SauceDemoCheckoutInformationPage(driver).fillCheckoutDetails();
    }

    @And("I click Continue")
    public void clickContinue(){
        new SauceDemoCheckoutInformationPage(driver).clickContinue();
    }

    @Then("I Should Land Checkout: Overview")
    public void assertThatILandedCheckoutPage(){
        new SauceDemoCheckoutOverviewPage(driver).assertThatILandedCheckOutOverviewPage();
    }

    @And("I Should Find Items Total Price Without Taxes")
    public void assertTotal(){
        new SauceDemoCheckoutOverviewPage(driver).assertTotalWithoutTax("Item total: $79.98");
    }

    @And("I Should Find Url Matches checkout-step-two.html")
    public void assertUrl(){
        new SauceDemoCheckoutOverviewPage(driver).assertUrl("https://www.saucedemo.com/checkout-step-two.html");
    }


    @When("I Click Finish")
    public void clickFinish(){
        new SauceDemoCheckoutOverviewPage(driver).clickFinish();
    }

    @Then("I Should Land Checkout Complete Page")
    public void assertThatILandedCheckoutComplete(){
        new SauceDemoCheckoutCompletePage(driver).assertThatILandedCheckOutCompletePage();
    }

    @And("I Should See Thank You Message")
    public void assertThankYouAndDispatchedMessages(){
        new SauceDemoCheckoutCompletePage(driver).assertMessages( new SauceDemoCheckoutCompletePage(driver).thankYouMessageContainer , "Thank you for your order!");
        new SauceDemoCheckoutCompletePage(driver).assertMessages( new SauceDemoCheckoutCompletePage(driver).orderDispatchedMessageContainer , "Your order has been dispatched, and will arrive just as fast as the pony can get there!");
    }








    @After()
    public void closeBrowser() {
        new Assisstant().takeScreenShoot(driver);
        driver.quit();
    }
}
