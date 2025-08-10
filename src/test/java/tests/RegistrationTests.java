package tests;

import models.RegistrationBodyModel;
import models.RegistrationResponseModel;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.*;
import static io.restassured.http.ContentType.JSON;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegistrationTests extends TestBase {
    RegistrationBodyModel authData = new RegistrationBodyModel();
    @Test
    void successfulRegisterTest() {
        authData.setEmail("eve.holt@reqres.in");
        authData.setPassword("pistol");
        RegistrationResponseModel response = step("Успешная регистрация", () ->
        given()
                .body(authData)
                .contentType(JSON)
                .log().uri()
                .header("x-api-key", "reqres-free-v1")
                .when()
                .post(baseURI + basePath + "/register")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().as(RegistrationResponseModel.class));
        step("Проверка айди", () -> {
            assertEquals(4, response.getId());
        });
        step("Проверка токена", () -> {
            assertEquals("QpwL5tke4Pnpja7X4", response.getToken());
        });
    }
    @Test
    void unsuccessfulRegisterTest() {
        authData.setEmail("eve.holt@reqres.in");

        RegistrationResponseModel response = step("Неуспешная регистрация", () ->
        given()
                .body(authData)
                .contentType(JSON)
                .log().uri()
                .header("x-api-key", "reqres-free-v1")
                .when()
                .post(baseURI + basePath + "/register")
                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .extract().as(RegistrationResponseModel.class));

        step("Проверка ошибки", () -> {
            assertEquals("Missing password", response.getError());
        });
    }
}
