import api.methods.UserAPIMethods;
import api.pojo.User;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pageObject.LoginPage;
import pageObject.MainPage;
import pageObject.RegistrationPage;
import utils.SetBrowser;

import static org.junit.Assert.assertEquals;
import static utils.PageURL.MAIN_PAGE;

public class TestRegistration {

    private WebDriver driver;
    private MainPage mainPage;
    private LoginPage loginPage;
    private RegistrationPage regPage;
    private User user;
    private UserAPIMethods client;

    @Before
    public void setUp() {
        //set browser
        this.driver = SetBrowser.getDriver("CHROME");

        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        regPage = new RegistrationPage(driver);

        user = UserAPIMethods.randomUser();
        client = new UserAPIMethods();
    }

    @Test
    @DisplayName("Успешная регистрация")
    public void checkRegistrationSuccess() {
        driver.get(MAIN_PAGE);
        mainPage.clickPersonalAccountButton();
        assertEquals(loginPage.getURL(), driver.getCurrentUrl());
        loginPage.clickRegNewUserLink();
        assertEquals(regPage.getURL(), driver.getCurrentUrl());
        regPage.fillRegistrationForm(user);
        regPage.clickRegistrationButton();
        assertEquals("User не зарегистрирован", loginPage.getURL(), driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Неуспешная регистрация: минимальный пароль — шесть символов")
    public void checkRegistrationFailShortPassword() {
        user.setPassword("pass");

        driver.get(MAIN_PAGE);
        mainPage.clickPersonalAccountButton();
        assertEquals(loginPage.getURL(), driver.getCurrentUrl());
        loginPage.clickRegNewUserLink();
        assertEquals(regPage.getURL(), driver.getCurrentUrl());
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
