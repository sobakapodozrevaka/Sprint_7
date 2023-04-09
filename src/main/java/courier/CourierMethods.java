package courier;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class CourierMethods {

    public static final String BASE_URL = "https://qa-scooter.praktikum-services.ru";
    private static String PATH = "api/v1/courier/";
    private static String PATH_LOGIN = "api/v1/courier/login";

    public CourierMethods() {
        RestAssured.baseURI = BASE_URL;
    }

    //Создание курьера
    public ValidatableResponse create(Courier courier){
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(courier)
                .when()
                .post(PATH)
                .then();
        }

        //Авторизация курьера
        public ValidatableResponse login(Credentials credentials){
            return given()
                    .header("Content-type", "application/json")
                    .and()
                    .body(credentials)
                    .when()
                    .post(PATH_LOGIN)
                    .then();
        }

       //Удаление курьера
        public ValidatableResponse delete(int id){
            return given()
                    .header("Content-type", "application/json")
                    .when()
                    .delete(PATH +id)
                    .then();
        }

    }

