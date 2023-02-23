package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SetBrowser {

    public static WebDriver getDriver(String browserName)
    {
        if (browserName.equals("CHROME")) {
            System.setProperty("webdriver.chrome.driver", "/Users/sashun/Documents/WebDriver/bin/chromedriver");
            return new ChromeDriver();
        }

        System.setProperty("webdriver.gecko.driver", "/Users/sashun/Documents/WebDriver/bin/geckodriver");
        return new FirefoxDriver();
    }
}
