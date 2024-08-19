package tools;

public class PropertiesConfiguration {

    public static String browser;
    public static String headless;
    public static String environemnt;
    public static String testURL;
    public static String webStagingURL;
    //=======================Endpoints======================
    public static void loadTestConfigurations() {
        PropertiesReader reader = new PropertiesReader( "config.properties");
        browser = reader.getProperty("browser");
        headless = reader.getProperty("headless");
        environemnt = reader.getProperty("environment");
        testURL = reader.getProperty("testURL");
        webStagingURL = reader.getProperty("webStagingURL");
    }
}
