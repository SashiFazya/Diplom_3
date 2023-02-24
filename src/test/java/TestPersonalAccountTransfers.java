import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static utils.PageURL.MAIN_PAGE;

public class TestPersonalAccountTransfers extends BaseTest {

    @Before
    public void createUser() {
        assertTrue(client.createUser(user));
    }

    @Before
    public void loginUser() {
        driver.get(MAIN_PAGE);
        mainPage.clickProfileEntranceButton();
        loginPage.fillLoginForm(user);
        loginPage.clickEntranceButton();
        assertEquals(mainPage.getURL(), driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Проверка перехода по клику на «Личный кабинет»")
    public void checkTransferClickPersonalAccountButton() {
        mainPage.clickPersonalAccountButton();
        assertEquals(profilePage.getURL(), driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор:" +
            "по клику на «Конструктор»")
    public void checkTransferClickConstructorButton() {
        mainPage.clickPersonalAccountButton();
        assertEquals(profilePage.getURL(), driver.getCurrentUrl());
        header.clickConstructorButton();
        assertEquals(mainPage.getURL(), driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор:" +
            "по клику на логотип Stellar Burgers")
    public void checkTransferClickBurgerLogo() {
        mainPage.clickPersonalAccountButton();
        assertEquals(profilePage.getURL(), driver.getCurrentUrl());
        header.clickBurgerLogo();
        assertEquals(mainPage.getURL(), driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Выход по кнопке «Выйти» в личном кабинете")
    public void checkLogout() {
        mainPage.clickPersonalAccountButton();
        assertEquals(profilePage.getURL(), driver.getCurrentUrl());
        profilePage.clickProfileLogoutButton();
        assertEquals(loginPage.getURL(), driver.getCurrentUrl());
    }

    @After
    public void teardown() {
        client.deleteUser(user);
        driver.quit();
    }
}
