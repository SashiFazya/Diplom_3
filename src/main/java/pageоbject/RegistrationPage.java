package pageоbject;

import api.pojo.User;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static utils.PageURL.REGISTRATION_PAGE;

public class RegistrationPage {
    private WebDriver driver;

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getURL() {
        checkRegistrationFormAppears();
        return REGISTRATION_PAGE;
    }

    private By registrationFormLabel = By.xpath(".//h2[text()='Регистрация']");
    private By inputFieldName = By.xpath(".//label[text()='Имя']/following-sibling::input");
    private By inputFieldEmail = By.xpath(".//label[text()='Email']/following-sibling::input");
    private By inputFieldPassword = By.xpath(".//label[text()='Пароль']/following-sibling::input");
    private By inputPassErrorText = By.xpath(".//label[text()='Пароль']/../../p");
    private By registrationButton = By.xpath(".//button[text()='Зарегистрироваться']");
    private By alreadyRegistratedEntranceButton = By.className("Auth_link__1fOlj");

    public boolean checkRegistrationFormAppears() {
        return driver.findElement(registrationFormLabel).isDisplayed();
    }

    @Step("Заполнить данные пользователя на экране регистрации {user.email}")
    public void fillRegistrationForm(User user) {
        driver.findElement(inputFieldName).sendKeys(user.getName());
        driver.findElement(inputFieldEmail).sendKeys(user.getEmail());
        driver.findElement(inputFieldPassword).sendKeys(user.getPassword());
    }

    public void clickAlreadyRegistratedEntranceButton() {
        driver.findElement(alreadyRegistratedEntranceButton).click();
    }

    @Step("Кликнуть на кнопку Уже зарегистрированы? Войти")
    public void clickRegistrationButton() {
        driver.findElement(registrationButton).click();
    }

    @Step("Получить текст ошибки на странице регистрации")
    public String checkWrongPassAlert() {
        return driver.findElement(inputPassErrorText).getText();
    }

}
