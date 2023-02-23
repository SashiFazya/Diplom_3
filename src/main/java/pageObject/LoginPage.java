package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static utils.PageURL.LOGIN_PAGE;

public class LoginPage {
    private WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }
    public String getURL() {
        checkLoginFormAppears();
        return LOGIN_PAGE;
    }
    private By regNewUserLink = By.xpath(".//*[@href='/register']");

    private By footerRegisterOffer = By.xpath(".//*[contains(text(), 'новый пользователь?')]");
    private By inputFieldEmail = By.xpath(".//label[text()='Email']/following-sibling::input");
    private By inputFieldPassword = By.xpath(".//label[text()='Пароль']/following-sibling::input");
    private By loginFormLabel = By.xpath(".//h2[text()='Вход']");
    private By entranceButton = By.xpath(".//button[text()='Войти']");

    public void clickRegNewUserButton() {
        new WebDriverWait(driver, 2).until(ExpectedConditions.visibilityOfElementLocated(footerRegisterOffer));
        driver.findElement(regNewUserLink).click();
    }

    public boolean checkLoginFormAppears() {
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(loginFormLabel));
        return driver.findElement(loginFormLabel).isDisplayed();
    }

    public void fillLoginForm(String email, String password) {
        driver.findElement(inputFieldEmail).sendKeys(email);
        driver.findElement(inputFieldPassword).sendKeys(password);
    }

    public void clickEntranceButton() {
        driver.findElement(entranceButton).click();
    }
}
