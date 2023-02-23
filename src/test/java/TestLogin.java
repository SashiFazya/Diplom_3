import api.methods.UserAPIMethods;
import api.pojo.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import io.qameta.allure.junit4.DisplayName;
import org.openqa.selenium.WebDriver;
import pageObject.LoginPage;
import pageObject.MainPage;
import pageObject.RegistrationPage;
import utils.SetBrowser;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestLogin{

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
        //generate user
        //  userData = regPage.generateUserData();
        user = UserAPIMethods.randomUser();
        client = new UserAPIMethods();
    }

    @Test
    @DisplayName("вход по кнопке «Войти в аккаунт» на главной")
    public void checkRegistrationSuccess() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        RegistrationPage regPage = new RegistrationPage(driver);
        List<String> userData = regPage.generateUserData();

        mainPage.clickPersonalAccountButton();
        assertEquals(loginPage.getURL(), driver.getCurrentUrl());
        loginPage.clickRegNewUserButton();
        assertEquals(regPage.getURL(), driver.getCurrentUrl());
        regPage.fillRegistrationForm(user);
        regPage.clickRegistrationButton();
        assertEquals(loginPage.getURL(), driver.getCurrentUrl());
    }
    @After
    public void teardown() {
        client.deleteUser(user);
        // Закрыть браузер
        driver.quit();
    }
}
