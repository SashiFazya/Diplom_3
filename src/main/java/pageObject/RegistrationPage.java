package pageObject;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import api.pojo.User;

import java.util.List;

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

    public boolean checkRegistrationFormAppears() {
        return driver.findElement(registrationFormLabel).isDisplayed();
    }

    public void fillRegistrationForm(User user) {
        driver.findElement(inputFieldName).sendKeys(user.getName());
        driver.findElement(inputFieldEmail).sendKeys(user.getEmail());
        driver.findElement(inputFieldPassword).sendKeys(user.getPassword());
    }

    public void clickRegistrationButton() {
        driver.findElement(registrationButton).click();
    }
    public String checkWrongPassAlert(){
        return driver.findElement(inputPassErrorText).getText();
    }
    public List<String> generateUserData() {
        Faker faker = new Faker();

        String password = faker.internet().password(true);
        String name = faker.name().username();
        String email = name + (int) (Math.random() * 1000) + "@yandex.ru";
        return List.of(name, email, password);
    }
}
