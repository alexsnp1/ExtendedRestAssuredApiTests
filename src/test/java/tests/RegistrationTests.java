package tests;

import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import models.RegistrationBodyModel;
import models.RegistrationResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static specs.CreateUserSpec.*;

@Owner("alexsnp")
@Feature("Регистрация на сайте")
@Tag("Req")
public class RegistrationTests extends TestBase {
    RegistrationBodyModel authData = new RegistrationBodyModel();

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Успешная регистрация")
    void successfulRegisterTest() {
        authData.setEmail("eve.holt@reqres.in");
        authData.setPassword("pistol");
        RegistrationResponseModel response = step("Отправка запроса на регистрацию", () ->
        given(RequestSpec)
                .body(authData)
                .when()
                .post("/register")
                .then()
                .spec(responseSpec(200))
                .extract().as(RegistrationResponseModel.class));
        step("Проверка корректности айди", () ->
            assertEquals(4, response.getId()));
        step("Проверка корректности токена", () ->
            assertEquals("QpwL5tke4Pnpja7X4", response.getToken()));
    }
    @Test
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Регистрация без пароля")
    void unsuccessfulRegisterTest() {
        authData.setEmail("eve.holt@reqres.in");
        RegistrationResponseModel response = step("Отправка запроса на регистрацию", () ->
        given(RequestSpec)
                .body(authData)
                .when()
                .post( "/register")
                .then()
                .spec(responseSpec(400))
                .extract().as(RegistrationResponseModel.class));
        step("Проверка: ошибка Missing password", () ->
            assertEquals("Missing password", response.getError()));
    }
}