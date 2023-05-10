import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import order.*;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ReturnOrderTest {
    private OrderMethod orderMethod;

    @Before
    public void setUp() {
        orderMethod = new OrderMethod();
    }

    @Test
    @DisplayName("Проверка ответа при возвращении списка заказа")
    public void returnOrderListTest() {
        ValidatableResponse responseOrderList = orderMethod.returnOrderList();
        ArrayList actualList = responseOrderList.extract().path("orders");
        int ordersSize = actualList.size();
        boolean actual = ordersSize > 0;
        int actualStatusCode = responseOrderList.extract().statusCode();
        assertEquals(SC_OK, actualStatusCode);
        assertTrue(actual);
    }
}