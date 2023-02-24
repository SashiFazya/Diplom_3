package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static utils.PageURL.MAIN_PAGE;

public class MainPage {
    private WebDriver driver;
    private By personalAccountButton = By.xpath(".//p[text()='Личный Кабинет']");
    private By profileEntranceButton = By.xpath(".//button[text()='Войти в аккаунт']");
    private By constructBurgerHeader = By.xpath(".//h1[text()='Соберите бургер']");

    public static final By constructorBunTab = By.xpath(".//span[text()='Булки']/parent::div");
    public static final By constructorSauceTab = By.xpath(".//span[text()='Соусы']/parent::div");
    public static final By constructorFillingTab = By.xpath(".//span[text()='Начинки']/parent::div");
    // Выбранный раздел
    public By selectedSection = By.xpath(".//div[contains(@class, 'tab_tab_type_current__2BEPc')]");


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

    public MainPage chooseTab(By section) {
        driver.findElement(section).click();
        return this;
    }

    public String getSelectedTab() {
        return driver.findElement(selectedSection).getText();
    }
}
