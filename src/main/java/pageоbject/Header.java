package pageоbject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Header {

    private WebDriver driver;

    public Header(WebDriver driver) {
        this.driver = driver;
    }

    private By constructorButton = By.xpath(".//header//p[text()='Конструктор']");
    private By burgerLogo = By.className("AppHeader_header__logo__2D0X2");
    private By personalAccountButton = By.xpath(".//p[text()='Личный Кабинет']");

    @Step("Кликнуть на кнопку Конструктор в верхнем меню")
    public void clickConstructorButton() {
        driver.findElement(constructorButton).click();
    }

    @Step("Кликнуть на логотип StellarBurgers в верхнем меню")
    public void clickBurgerLogo() {
        driver.findElement(burgerLogo).click();
    }

    @Step("Кликнуть на кнопку Личный кабинет в верхнем меню")
    public void clickPersonalAccountButton() {
        driver.findElement(personalAccountButton).click();
    }

}
