package pageоbject;

import api.pojo.User;
import io.qameta.allure.Step;
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

    private By footerRegisterOfferLink = By.xpath(".//a[text()='Зарегистрироваться']");
    private By footerRestorePasswordLink = By.xpath(".//a[text()='Восстановить пароль']");
    private By inputFieldEmail = By.xpath(".//label[text()='Email']/following-sibling::input");
    private By inputFieldPassword = By.xpath(".//label[text()='Пароль']/following-sibling::input");
    private By loginFormLabel = By.xpath(".//h2[text()='Вход']");
    private By entranceButton = By.xpath(".//button[text()='Войти']");

    @Step("Кликнуть на кнопку Зарегистрироваться на экране логина")
    public void clickRegNewUserLink() {
      //  new WebDriverWait(driver, 2).until(ExpectedConditions.visibilityOfElementLocated(footerRegisterOfferLink));
        driver.findElement(regNewUserLink).click();
    }
    @Step("Кликнуть на кнопку Восстановить пароль на экране логина")
    public void clickRestorePasswordLink() {
      //  new WebDriverWait(driver, 2).until(ExpectedConditions.visibilityOfElementLocated(footerRestorePasswordLink));
        driver.findElement(footerRestorePasswordLink).click();
    }

    @Step("Дождаться перехода на страницу логина")
    public boolean checkLoginFormAppears() {
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(loginFormLabel));
        return driver.findElement(loginFormLabel).isDisplayed();
    }
    @Step("Заполнить форму логин = {user.email}, пароль = {user.password}")
    public void fillLoginForm(User user) {
        driver.findElement(inputFieldEmail).sendKeys(user.getEmail());
        driver.findElement(inputFieldPassword).sendKeys(user.getPassword());
    }
    @Step("Кликнуть на кнопку Войти на экране логина")
    public void clickEntranceButton() {
        driver.findElement(entranceButton).click();
    }

}
