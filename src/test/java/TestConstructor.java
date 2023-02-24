import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;

import static org.junit.Assert.assertTrue;
import static pageObject.MainPage.*;

@RunWith(Parameterized.class)
public class TestConstructor extends BaseTest {
    private final By tabButton;
    private final String tabName;

    public TestConstructor(By tabButton, String tabName) {
        this.tabButton = tabButton;
        this.tabName = tabName;
    }

    @Parameterized.Parameters(name = "Тест {index}: активная кладка {1}")
    public static Object[][] data() {
        return new Object[][]{
                {constructorSauceTab, "Соусы"},
                {constructorBunTab, "Булки"},
                {constructorFillingTab, "Начинки"}
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
