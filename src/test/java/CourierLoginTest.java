import courier.Courier;
import courier.CourierMethods;
import courier.CourierData;
import courier.Credentials;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.apache.http.HttpStatus.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CourierLoginTest {
    private CourierMethods courierMethods;
    private Courier courier;
    private int id;


    @Before
    public void setUp() {
        courierMethods = new CourierMethods();
        courier = CourierData.defaultCourier;
    }


    @Test
    @DisplayName("Проверка ответа при авторизации курьера" )
    public void LoginCourierTest(){
        ValidatableResponse responseCreate = courierMethods.create(courier);
        ValidatableResponse responseLogin = courierMethods.login(Credentials.from(courier));
        id = responseLogin.extract().path("id");
        int actualStatusCode = responseLogin.extract().statusCode();
        assertEquals(actualStatusCode,SC_OK);
        assertThat(id, notNullValue());
    }


    @Test
    @DisplayName("Проверка ответа при авторизации с неверными данными")
    public void LoginIncorrectCredentialsTest(){
        courier = CourierData.incorrectCredentials();
        ValidatableResponse responseLogin = courierMethods.login(Credentials.from(courier));
        int actualStatusCode = responseLogin.extract().statusCode();
        String actualMessage = responseLogin.extract().path("message" );
        assertEquals(SC_NOT_FOUND, actualStatusCode);
        assertEquals("Учетная запись не найдена",actualMessage);
    }


    @Test
    @DisplayName("Проверка ответа при авторизации без указания пароля")
    public void LoginWithoutLoginTest(){
        courier = CourierData.withoutLogin();
        ValidatableResponse responseLogin = courierMethods.login(Credentials.from(courier));
        int actualStatusCode = responseLogin.extract().statusCode();
        String actualMessage = responseLogin.extract().path("message" );
        assertEquals(SC_BAD_REQUEST, actualStatusCode);
        assertEquals("Недостаточно данных для входа", actualMessage);
    }
    //в документации сообщение: "Недостаточно данных для создания учетной записи"

    @After
    public void cleanUp(){
        courierMethods.delete(id);
    }
}