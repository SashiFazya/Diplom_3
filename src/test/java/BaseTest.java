import api.methods.UserAPIMethods;
import api.pojo.User;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import pageоbject.*;
import utils.SetBrowser;

import static utils.PageURL.MAIN_PAGE;

public class BaseTest {
    protected WebDriver driver;
    protected MainPage mainPage;
    protected LoginPage loginPage;
    protected RegistrationPage regPage;
    protected ProfilePage profilePage;
    protected ForgotPasswordPage forgotPasswordPage;
    protected Header header;
    protected User user;
    protected UserAPIMethods client;

    @Before
    public void setUp() {
        this.driver = SetBrowser.getDriver("YANDEX");
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        regPage = new RegistrationPage(driver);
        profilePage = new ProfilePage(driver);
        forgotPasswordPage = new ForgotPasswordPage(driver);
        header = new Header(driver);
        client = new UserAPIMethods();
        user = client.randomUser();

        driver.get(MAIN_PAGE);
    }
}
