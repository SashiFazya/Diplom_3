import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestRegistration extends BaseTest{
    @Test
    @DisplayName("Успешная регистрация")
    public void checkRegistrationSuccess() {
        header.clickPersonalAccountButton();
        loginPage.checkLoginFormAppears();
        loginPage.clickRegNewUserLink();
        regPage.checkRegistrationFormAppears();
        regPage.fillRegistrationForm(user);
        regPage.clickRegistrationButton();
        assertEquals("User не зарегистрирован", loginPage.getURL(), driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Неуспешная регистрация: минимальный пароль — шесть символов")
    public void checkRegistrationFailShortPassword() {
        user.setPassword("pass");

        header.clickPersonalAccountButton();
        loginPage.checkLoginFormAppears();
        loginPage.clickRegNewUserLink();
        regPage.checkRegistrationFormAppears();
        regPage.fillRegistrationForm(user);
        regPage.clickRegistrationButton();
        assertEquals(regPage.getURL(), driver.getCurrentUrl());
        assertEquals("Тут должна быть ошибка", "Некорректный пароль", regPage.checkWrongPassAlert());
    }

    @After
    public void teardown() {
        client.deleteUser(user);
        driver.quit();
    }
}
