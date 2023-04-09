import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import order.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static org.apache.http.HttpStatus.SC_CREATED;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class ParameterizedOrderTest {
    private OrderMethod orderMethod;
    private Order order;
    private int statusCode;
    public ParameterizedOrderTest(Order order, int statusCode) {
        this.order = order;
        this.statusCode = statusCode;
    }

    //test data
    @Parameterized.Parameters
    public static Object[][] getTest() {
        return new Object[][]{
                {OrderData.colourGrey(), SC_CREATED},
                {OrderData.colourBlack(), SC_CREATED},
                {OrderData.twoColours(), SC_CREATED},
                {OrderData.withoutColour(), SC_CREATED}
        };
    }

    @Before
    public void setUp() {
        orderMethod = new OrderMethod();
    }

    @Test
    @DisplayName("Проверка создания заказа с разным заполнением поля Colour с наличием номера заказа")
    public void createOrder(){
        ValidatableResponse responseCreate = orderMethod.create(order);
        int actualStatusCode = responseCreate.extract().statusCode();
        int track = responseCreate.extract().path("track");
        assertThat(track, notNullValue());
        assertEquals(statusCode,actualStatusCode);
    }

}