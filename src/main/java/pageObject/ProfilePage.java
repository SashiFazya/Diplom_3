package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static utils.PageURL.PROFILE_PAGE;

public class ProfilePage {
    private WebDriver driver;
    private By profilePageDescription = By.xpath(".//nav/p");
    private By profileLogoutButton = By.xpath(".//button[text()='Выход']");

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }

    public String getURL() {
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(profilePageDescription));
        return PROFILE_PAGE;
    }

    public void clickProfileLogoutButton() {
        driver.findElement(profileLogoutButton).click();
    }
}
