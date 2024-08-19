package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import tools.Assisstant;
import tools.PropertiesReader;


public class SauceDemoLoginPage {

    private WebDriver driver;

    public SauceDemoLoginPage(WebDriver driver){
        this.driver = driver ;
    }

    private By emailField = By.xpath("//input[@name='user-name']");
    private By passwordField = By.xpath("//input[@name='password']");
    private By loginButton = By.xpath("//input[@name='login-button']");
    private By errorMessageContainer = By.xpath("//h3[@data-test='error']");
    private String url = new PropertiesReader("configuration.properties").getProperty("testURL");
    private String userName = "standard_user";
    private String password="secret_sauce";
    private By HomePageLogo = By.xpath("//div[@class='app_logo']");

public String wrongUserNameAndPassword = "Epic sadface: Username and password do not match any user in this service";
public String passwordIsRequired = "Epic sadface: Password is required";
public String usernameIsRequired = "Epic sadface: Username is required";

    public void navigateToUrl() {
        driver.navigate().to(url);
    }


    public void enterUsernameAndPassword(String userName , String password) {
        driver.findElement(emailField).sendKeys(userName);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(loginButton).click();
    }

    public void assertErrorMessage(String errorMsg){
        String errorMessage = driver.findElement(errorMessageContainer).getText();
        Assert.assertEquals(errorMessage, errorMsg);
        new Assisstant().takeScreenShoot(driver);
    }


}
