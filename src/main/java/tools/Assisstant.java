package tools;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class Assisstant {

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMddHHmmssSSS");
    String tenDigitString = dateFormat.format(new Date());
    ByteArrayOutputStream consoleOutput = new ByteArrayOutputStream();


    @Step
    public void takeScreenShoot(WebDriver driver) {
        Allure.addAttachment("screenshot_"+tenDigitString, new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
    }
    public void setUpLog() {
        System.setOut(new PrintStream(consoleOutput));
        System.setErr(new PrintStream(consoleOutput));
    }




    public void attachLogToReport() {
        // Attach console output to Allure report
        Allure.addAttachment("Console Log", consoleOutput.toString());
    }


    }

