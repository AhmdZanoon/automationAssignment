package webTests.cucumberTestRunner;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(
        features = "src/test/java/webTests/features",
        glue = {"webTests/cucumberSteps"},
        plugin = {"pretty", "html:test-output/DefaultReport/DefaultReport.html" , "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"},
        monochrome = true
)
public class TestRunner extends AbstractTestNGCucumberTests {
}
