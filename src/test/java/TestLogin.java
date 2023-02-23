import api.methods.UserAPIMethods;
import api.pojo.User;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pageObject.*;
import utils.SetBrowser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static utils.PageURL.MAIN_PAGE;

public class TestLogin {

    private WebDriver driver;
    private MainPage mainPage;
    private LoginPage loginPage;
    private RegistrationPage regPage;
    private ProfilePage profilePage;
    private ForgotPasswordPage forgotPasswordPage;
    private User user;
    public UserAPIMethods client;

    @Before
    public void setUp() {
        //set browser
        this.driver = SetBrowser.getDriver("CHROME");
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        regPage = new RegistrationPage(driver);
        profilePage = new ProfilePage(driver);
        forgotPasswordPage = new ForgotPasswordPage(driver);
        //generate user
        //  userData = regPage.generateUserData();
        client = new UserAPIMethods();
        user = client.randomUser();
        assertTrue(client.createUser(user));
    }

    @Test
    @DisplayName("вход по кнопке «Войти в аккаунт» на главной")
    public void checkMainPageLogin() {
        driver.get(MAIN_PAGE);
        mainPage.clickProfileEntranceButton();
        assertEquals(loginPage.getURL(), driver.getCurrentUrl());
        loginPage.fillLoginForm(user);
        loginPage.clickEntranceButton();
        assertEquals(mainPage.getURL(), driver.getCurrentUrl());
        //проверим, что юзер действительно авторизован
        mainPage.clickPersonalAccountButton();
        assertEquals(profilePage.getURL(), driver.getCurrentUrl());
    }

    @Test
    @DisplayName("вход через кнопку «Личный кабинет»")
    public void checkPersonalAccountPageLogin() {
        driver.get(MAIN_PAGE);
        mainPage.clickPersonalAccountButton();
        assertEquals(loginPage.getURL(), driver.getCurrentUrl());
        loginPage.fillLoginForm(user);
        loginPage.clickEntranceButton();
        assertEquals(mainPage.getURL(), driver.getCurrentUrl());
        //проверим, что юзер действительно авторизован
        mainPage.clickPersonalAccountButton();
        assertEquals(profilePage.getURL(), driver.getCurrentUrl());
    }

    @Test
    @DisplayName("вход через кнопку в форме регистрации")
    public void checkRegistrationPageLogin() {
        driver.get(MAIN_PAGE);
        mainPage.clickPersonalAccountButton();
        loginPage.clickRegNewUserLink();
        regPage.clickAlreadyRegistratedEntranceButton();
        assertEquals(loginPage.getURL(), driver.getCurrentUrl());
        loginPage.fillLoginForm(user);
        loginPage.clickEntranceButton();
        assertEquals(mainPage.getURL(), driver.getCurrentUrl());
        //проверим, что юзер действительно авторизован
        mainPage.clickPersonalAccountButton();
        assertEquals(profilePage.getURL(), driver.getCurrentUrl());
    }

    @Test
    @DisplayName("вход через кнопку в форме восстановления пароля")
    public void checkRestorePasswordPageLogin() {
        driver.get(MAIN_PAGE);
        mainPage.clickPersonalAccountButton();
        loginPage.clickRestorePasswordLink();
        forgotPasswordPage.clickRememberPassLink();
        assertEquals(loginPage.getURL(), driver.getCurrentUrl());
        loginPage.fillLoginForm(user);
        loginPage.clickEntranceButton();
        assertEquals(mainPage.getURL(), driver.getCurrentUrl());
        //проверим, что юзер действительно авторизован
        mainPage.clickPersonalAccountButton();
        assertEquals(profilePage.getURL(), driver.getCurrentUrl());
    }

    @After
    public void teardown() {
        client.deleteUser(user);
        driver.quit();
    }
}
