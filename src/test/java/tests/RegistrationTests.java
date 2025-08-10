package tests;

import models.RegistrationBodyModel;
import models.RegistrationResponseModel;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static specs.CreateUserSpec.*;

public class RegistrationTests extends TestBase {
    RegistrationBodyModel authData = new RegistrationBodyModel();
    @Test
    void successfulRegisterTest() {
        authData.setEmail("eve.holt@reqres.in");
        authData.setPassword("pistol");
        RegistrationResponseModel response = step("Успешная регистрация", () ->
        given(RegistrationRequestSpec)
                .body(authData)
                .when()
                .post("/register")
                .then()
                .spec(successfulRegistrationResponseSpec)
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
        given(RegistrationRequestSpec)
                .body(authData)
                .when()
                .post( "/register")
                .then()
                .spec(unsuccessfulRegistrationResponseSpec)
                .extract().as(RegistrationResponseModel.class));

        step("Проверка ошибки", () -> {
            assertEquals("Missing password", response.getError());
        });
    }
}
