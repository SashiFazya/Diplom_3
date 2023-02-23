package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static utils.PageURL.LOGIN_PAGE;
import static utils.PageURL.MAIN_PAGE;

public class MainPage {
    private WebDriver driver;
    private By personalAccountButton = By.xpath(".//p[text()='Личный Кабинет']");
    private By profileEntranceButton = By.xpath(".//button[text()='Войти в аккаунт']");
    private By constructBurgerHeader = By.xpath(".//h1[text()='Соберите бургер']");
    private By constructorButton = By.xpath(".//p[text()='Конструктор']");
    private By burgerLogo = By.className("AppHeader_header__logo__2D0X2");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getURL() {
        checkConstructBurgerFormAppears();
        return MAIN_PAGE;
    }
    public void clickPersonalAccountButton() {
        driver.findElement(personalAccountButton).click();
    }

    public boolean checkConstructBurgerFormAppears() {
        new WebDriverWait(driver, 2).until(ExpectedConditions.visibilityOfElementLocated(constructBurgerHeader));
        return driver.findElement(constructBurgerHeader).isDisplayed();
    }
    public void clickProfileEntranceButton() {
        driver.findElement(profileEntranceButton).click();
    }
}
