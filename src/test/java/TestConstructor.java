import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pageоbject.MainPage;
import utils.SetBrowser;

import static org.junit.Assert.assertTrue;
import static utils.PageURL.MAIN_PAGE;

@RunWith(Parameterized.class)
public class TestConstructor extends SetBrowser{
    private WebDriver driver = getDriver("YANDEX");
    private MainPage mainPage = new MainPage(driver);
    private final By tabButton;
    private final String tabName;
    private By constructorSauceTab = mainPage.getConstructorSauceTab();
    private By constructorBunTab = mainPage.getConstructorBunTab();
    private By constructorFillingTab = mainPage.getConstructorFillingTab();

    @Before
    public void setUp() {
        driver.get(MAIN_PAGE);
    }
    public TestConstructor(String tabName) {

        this.tabName = tabName;
        switch (tabName) {
            case ("Соусы"):
                this.tabButton = constructorSauceTab;
                break;
            case ("Начинки"):
                this.tabButton = constructorFillingTab;
                break;
            default:
                this.tabButton = constructorBunTab;
                break;
        }
    }

    @Parameterized.Parameters(name = "Тест {index}: активная кладка {0}")
    public static Object[][] data() {
        return new Object[][]{
                {"Соусы"},
                {"Булки"},
                {"Начинки"}
        };
    }

    @Test
    public void checkConstructorTabTransfers() {
        //булки некликабельны при загрузке страницы
        if (tabName.equals("Булки")) {
            mainPage.chooseTab(constructorFillingTab);
        }
        mainPage.chooseTab(tabButton);
        assertTrue("Переход к разделу не осуществлен", mainPage.getSelectedTab().contains(tabName));
    }

    @After
    public void teardown() {
        driver.quit();
    }
}
