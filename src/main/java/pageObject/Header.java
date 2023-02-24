package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Header {

    private WebDriver driver;

    public Header(WebDriver driver) {
        this.driver = driver;
    }

    private By constructorButton = By.xpath(".//header//p[text()='Конструктор']");
    private By burgerLogo = By.className("AppHeader_header__logo__2D0X2");

    public void clickConstructorButton() {
        driver.findElement(constructorButton).click();
    }

    public void clickBurgerLogo() {
        driver.findElement(burgerLogo).click();
    }

}
