package helpers;

public class FileLoader {
    protected static ProjectProperties properties;

    static {
        if (properties == null) {
            properties = new ProjectProperties();
            properties.loadProperties("testConfig.properties");
        }
    }

    public static String getExecutionMode() {
        return properties.getRequiredProperty("executionMode");
    }

    public static String getSeleniumHubURL() {
        return properties.getRequiredProperty("selenium.hub.url");
    }

    public static String getBrowser() {
        return properties.getRequiredProperty("browser");
    }

    public static String getBaseURL() {
        return properties.getRequiredProperty("baseUrl");
    }

    public static Long getTimeout() {
        return Long.parseLong(properties.getRequiredProperty("timeout"));
    }

}
