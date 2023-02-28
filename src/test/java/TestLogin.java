import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestLogin extends BaseTest{
    @Before
    public void createUser(){
        assertTrue(client.createUser(user));
    }

    @Test
    @DisplayName("вход по кнопке «Войти в аккаунт» на главной")
    public void checkMainPageLogin() {
        mainPage.clickProfileEntranceButton();
        loginPage.checkLoginFormAppears();
        loginPage.fillLoginForm(user);
        loginPage.clickEntranceButton();
        mainPage.checkConstructBurgerFormAppears();
        //проверим, что юзер действительно авторизован
        header.clickPersonalAccountButton();
        assertEquals(profilePage.getURL(), driver.getCurrentUrl());
    }

    @Test
    @DisplayName("вход через кнопку «Личный кабинет»")
    public void checkPersonalAccountPageLogin() {
        header.clickPersonalAccountButton();
        loginPage.checkLoginFormAppears();
        loginPage.fillLoginForm(user);
        loginPage.clickEntranceButton();
        mainPage.checkConstructBurgerFormAppears();
        //проверим, что юзер действительно авторизован
        header.clickPersonalAccountButton();
        assertEquals(profilePage.getURL(), driver.getCurrentUrl());
    }

    @Test
    @DisplayName("вход через кнопку в форме регистрации")
    public void checkRegistrationPageLogin() {
        header.clickPersonalAccountButton();
        loginPage.clickRegNewUserLink();
        regPage.clickAlreadyRegistratedEntranceButton();
        loginPage.checkLoginFormAppears();
        loginPage.fillLoginForm(user);
        loginPage.clickEntranceButton();
        mainPage.checkConstructBurgerFormAppears();
        //проверим, что юзер действительно авторизован
        header.clickPersonalAccountButton();
        assertEquals(profilePage.getURL(), driver.getCurrentUrl());
    }

    @Test
    @DisplayName("вход через кнопку в форме восстановления пароля")
    public void checkRestorePasswordPageLogin() {
        header.clickPersonalAccountButton();
        loginPage.clickRestorePasswordLink();
        forgotPasswordPage.clickRememberPassLink();
        loginPage.checkLoginFormAppears();
        loginPage.fillLoginForm(user);
        loginPage.clickEntranceButton();
        mainPage.checkConstructBurgerFormAppears();
        //проверим, что юзер действительно авторизован
        header.clickPersonalAccountButton();
        assertEquals(profilePage.getURL(), driver.getCurrentUrl());
    }

    @After
    public void teardown() {
        client.deleteUser(user);
        driver.quit();
    }
}
