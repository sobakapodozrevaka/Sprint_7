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
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CourierCreateTest {
    private CourierMethods courierMethods;
    private Courier courier;
    private int id;

    @Before
    public void setUp() {
        courierMethods = new CourierMethods();
        courier = CourierData.defaultCourier;
    }

    @Test
    @DisplayName("Проверка ответа при создании курьера" )
    public void CreateCourierTest() {
        ValidatableResponse responseCreate = courierMethods.create(courier);
        ValidatableResponse responseLogin = courierMethods.login(Credentials.from(courier));
        id = responseLogin.extract().path("id");
        int actualStatusCode = responseCreate.extract().statusCode();
        boolean isCourierCreated = responseCreate.extract().path("ok");
        assertEquals(actualStatusCode, SC_CREATED);
        assertTrue(isCourierCreated);
    }

    @Test
    @DisplayName("Проверка ответа при создании курьера без логина" )
    public void CreateCourierWithoutLoginTest() {
        courier = CourierData.withoutLogin();
        ValidatableResponse responseCreate = courierMethods.create(courier);
        int actualStatusCode = responseCreate.extract().statusCode();
        String actualMessage = responseCreate.extract().path("message" );
        assertEquals(actualStatusCode, SC_BAD_REQUEST);
        assertEquals("Недостаточно данных для создания учетной записи", actualMessage);
    }

    @Test
    @DisplayName("Проверка ответа при создании двух курьеров с одинаковыми данными" )
    public void СreateDoubleCourierTest(){
        ValidatableResponse responseCreate = courierMethods.create(courier);
        ValidatableResponse responseCreateDouble = courierMethods.create(courier);
        int actualCode = responseCreateDouble.extract().path("code" );

        String actualMessage = responseCreateDouble.extract().path("message" );
        assertEquals(SC_CONFLICT, actualCode);
        assertEquals("Этот логин уже используется. Попробуйте другой.", actualMessage);
    } //тут в документации другое сообщение: "Этот логин уже используется."


    @Test
    @DisplayName("Проверка ответа при создании двух курьеров с одинаковыми логинами" )
    public void СreateCourierWithSameLoginTest(){
        ValidatableResponse responseCreate = courierMethods.create(courier);
        courier = CourierData.sameLogin();
        ValidatableResponse responseCreateSameLogin = courierMethods.create(courier);
        int actualCode = responseCreateSameLogin.extract().path("code" );

        String actualMessage = responseCreateSameLogin.extract().path("message" );
        assertEquals(SC_CONFLICT, actualCode);
        assertEquals("Этот логин уже используется. Попробуйте другой.", actualMessage);
    } //тут в документации другое сообщение: "Этот логин уже используется."

    @After
    public void cleanUp(){
        courierMethods.delete(id);
    }
}