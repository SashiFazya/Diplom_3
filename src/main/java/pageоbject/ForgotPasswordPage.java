package pageоbject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static utils.PageURL.FORGOT_PASSWORD_PAGE;

public class ForgotPasswordPage {
    private WebDriver driver;
    private By forgotPasswordHeader = By.xpath(".//h2[text()='Восстановление пароля']");
    private By rememberPasswordLink = By.xpath(".//p[text()='Вспомнили пароль?']/a");

    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getURL() {
        new WebDriverWait(driver, 2).until(ExpectedConditions.visibilityOfElementLocated(forgotPasswordHeader));
        return FORGOT_PASSWORD_PAGE;
    }

    @Step("Кликнуть на кнопку Восстановить на экране восстановления пароля")
    public void clickRememberPassLink() {
        driver.findElement(rememberPasswordLink).click();
    }
}
