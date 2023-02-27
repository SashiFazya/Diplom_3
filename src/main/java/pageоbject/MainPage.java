package pageоbject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static utils.PageURL.MAIN_PAGE;

public class MainPage {
    private WebDriver driver;
    private By profileEntranceButton = By.xpath(".//button[text()='Войти в аккаунт']");
    private By constructBurgerHeader = By.xpath(".//h1[text()='Соберите бургер']");


    private By constructorBunTab = By.xpath(".//span[text()='Булки']/parent::div");
    private By constructorSauceTab = By.xpath(".//span[text()='Соусы']/parent::div");
    private By constructorFillingTab = By.xpath(".//span[text()='Начинки']/parent::div");
    // Выбранный раздел
    public By selectedSection = By.xpath(".//div[contains(@class, 'tab_tab_type_current__2BEPc')]");

    public By getConstructorBunTab() {
        return constructorBunTab;
    }

    public By getConstructorSauceTab() {
        return constructorSauceTab;
    }

    public By getConstructorFillingTab() {
        return constructorFillingTab;
    }

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getURL() {
        checkConstructBurgerFormAppears();
        return MAIN_PAGE;
    }

    @Step("Дождаться перехода на главную страницу")
    public boolean checkConstructBurgerFormAppears() {
        new WebDriverWait(driver, 2).until(ExpectedConditions.visibilityOfElementLocated(constructBurgerHeader));
        return driver.findElement(constructBurgerHeader).isDisplayed();
    }

    @Step("Кликнуть на кнопку Войти в аккаунт на главном экране")
    public void clickProfileEntranceButton() {
        driver.findElement(profileEntranceButton).click();
    }

    @Step("На главном экране в конструкторе выбрать раздел {section}")
    public MainPage chooseTab(By section) {
        driver.findElement(section).click();
        return this;
    }

    @Step("Получить активную вкладку")
    public String getSelectedTab() {
        return driver.findElement(selectedSection).getText();
    }
}
