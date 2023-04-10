package order;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class OrderMethod {

    public static final String BASE_URL = "https://qa-scooter.praktikum-services.ru";
    private static String PATH = "/api/v1/orders";

    public OrderMethod() {RestAssured.baseURI = BASE_URL;
    }

    public ValidatableResponse create(Order order){
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(order)
                .when()
                .post(PATH)
                .then();
    }
    public ValidatableResponse returnOrderList(){
        return given()
                .header("Content-type", "application/json")
                .and()
                .when()
                .get(PATH)
                .then();
    }

}