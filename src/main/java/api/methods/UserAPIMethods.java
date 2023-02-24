package api.methods;

import api.pojo.LoginData;
import api.pojo.User;
import com.github.javafaker.Faker;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_OK;

public class UserAPIMethods {
    private static String email;
    private static String password;
    private static String name;
    private static Faker faker = new Faker();

    private static final String CREATE_USER_URL = "/api/auth/register";
    private static final String DELETE_USER_URL = "/api/auth/user";
    private static final String LOGIN_USER_URL = "/api/auth/login";
    private static final String BASE_URL = "https://stellarburgers.nomoreparties.site";

    public static User randomUser() {
        password = faker.internet().password(true);
        name = faker.name().username();
        email = name + (int) (Math.random() * 100) + "@yandex.ru";

        return new User(email, password, name);
    }

    public boolean createUser(User user) {

        return given()
                .baseUri(BASE_URL)
                .header("Content-Type", ContentType.JSON)
                .body(user).log().all()
                .when().log().all()
                .post(CREATE_USER_URL)
                .then().log().all()
                .assertThat().extract().statusCode() == SC_OK;
    }

    public String getUserToken(User user) {
        LoginData loginData = new LoginData(user);
        return given()
                .baseUri(BASE_URL)
                .header("ContentType", ContentType.JSON)
                .body(loginData).log().all()
                .when()
                .post(LOGIN_USER_URL)
                .path("accessToken");
    }

    public boolean loginUser(User user) {
        LoginData loginData = new LoginData(user);
        return given()
                .baseUri(BASE_URL)
                .header("ContentType", ContentType.JSON)
                .body(loginData).log().all()
                .when()
                .post(LOGIN_USER_URL)
                .then()
                .assertThat().extract().statusCode() == SC_OK;
    }

    public boolean deleteUser(User user) {
        if (loginUser(user))
            return given()
                    .baseUri(BASE_URL)
                    .header("ContentType", ContentType.JSON)
                    .header("Authorization", getUserToken(user)).log().all()
                    .delete(DELETE_USER_URL)
                    .then()
                    .assertThat().extract().statusCode() == SC_OK;
        else return false;
    }
}
