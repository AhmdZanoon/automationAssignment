package mobileTests;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import tools.Assisstant;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;

public class SaucelabMobileTest {

    AppiumDriver driver;
    private final By userNameField = AppiumBy.accessibilityId("test-Username");
    private final By passwordField = AppiumBy.accessibilityId("test-Password");
    private final By loginButton = AppiumBy.accessibilityId("test-LOGIN");

    private final By productsHeader = AppiumBy.androidUIAutomator("new UiSelector().text(\"PRODUCTS\")");
    private final By errorMessage = AppiumBy.androidUIAutomator("new UiSelector().text(\"Sorry, this user has been locked out.\")");
    private final By sideMenue = AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.ImageView\").instance(1)");
    private final By  logoutButton = AppiumBy.accessibilityId("test-LOGOUT");
    @BeforeClass
    public void setup() {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("appuim:deviceName", "emulator-55545");
        caps.setCapability("appium:platformName", "android");
        caps.setCapability("appium:automationName", "UiAutomator2");
        caps.setCapability("appium:platformVersion", "15");
        caps.setCapability("appium:appPackage", "com.swaglabsmobileapp");
        caps.setCapability("appium:appActivity", "com.swaglabsmobileapp.MainActivity");
        try {
            driver = new AndroidDriver(new URI("http://127.0.0.1:4723").toURL(), caps);
        } catch (URISyntaxException | MalformedURLException e) {
            throw new RuntimeException(e);
        }


    }


    @Test(description = "Positive Scenario : logging with Correct user Name And Password")
    public void _01loginWithValidCredentials() {
        new WebDriverWait(driver, Duration.ofSeconds(60)).until(ExpectedConditions.presenceOfElementLocated((loginButton)));
        driver.findElement(userNameField).sendKeys("standard_user");
        driver.findElement(passwordField).sendKeys("secret_sauce");
        driver.findElement(loginButton).click();
        new WebDriverWait(driver, Duration.ofSeconds(60)).until(ExpectedConditions.presenceOfElementLocated(productsHeader));
        driver.findElement(productsHeader).isDisplayed();
        new Assisstant().takeScreenShoot(driver);
        driver.findElement(sideMenue).click();
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfElementLocated((logoutButton)));
        driver.findElement(logoutButton).click();
        new Assisstant().takeScreenShoot(driver);
    }

    @Test(description = "Negative Scenario : logging with Blocked User")
    public void _02loginWithBlockedCredentials() {
        new WebDriverWait(driver, Duration.ofSeconds(60)).until(ExpectedConditions.presenceOfElementLocated((loginButton)));
        driver.findElement(userNameField).sendKeys("locked_out_user");
        driver.findElement(passwordField).sendKeys("secret_sauce");
        driver.findElement(loginButton).click();
        new WebDriverWait(driver, Duration.ofSeconds(60)).until(ExpectedConditions.presenceOfElementLocated(errorMessage));
        driver.findElement(errorMessage).isDisplayed();
        new Assisstant().takeScreenShoot(driver);
    }


    @AfterClass
    public void tearDown() {
        driver.quit();
    }

}
