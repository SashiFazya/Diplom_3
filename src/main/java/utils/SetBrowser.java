package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class SetBrowser {

    public static WebDriver getDriver(String browserName) {
        if (browserName.equals("CHROME")) {
            System.setProperty("webdriver.chrome.driver", "src/main/resources/browserDrivers/chromedriver");
            return new ChromeDriver();
        } else if (browserName.equals("YANDEX")) {
            System.setProperty("webdriver.chrome.driver", "src/main/resources/browserDrivers/chromeForYandexdriver");
            ChromeOptions options = new ChromeOptions();
            options.setBinary("/Applications/Yandex.app/Contents/MacOS/Yandex");
            return new ChromeDriver(options);
        } else {
            System.out.println("Unsupported browser. Chrome will be used instead");
            System.setProperty("webdriver.chrome.driver", "src/main/resources/browserDrivers/chromedriver");
            return new ChromeDriver();
        }
    }
}
