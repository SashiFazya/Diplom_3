package pageоbject;

import io.qameta.allure.Step;
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
        waitForLoadingProfilePage();
        return PROFILE_PAGE;
    }

    @Step("Дождаться загрузки личного кабинета")
    public void waitForLoadingProfilePage(){
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(profilePageDescription));
    }

    @Step("Кликнуть на кнопку Выйти в Личном кабинете")
    public void clickProfileLogoutButton() {
        driver.findElement(profileLogoutButton).click();
    }
}
