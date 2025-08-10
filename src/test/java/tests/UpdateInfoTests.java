package tests;

import models.CreateUserResponseModel;
import models.UserBodyModel;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.*;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UpdateInfoTests extends TestBase {
    UserBodyModel authData = new UserBodyModel();

    @Test
    void successfulUpdateWithPutTest() {
        authData.setName("morpheus");
        authData.setJob("zion resident");
        CreateUserResponseModel response = step("Изменение информации через Put", () ->
        given()
                .body(authData)
                .contentType(JSON)
                .log().uri()
                .header("x-api-key", "reqres-free-v1")
                .when()
                .put(baseURI + basePath + "/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().as(CreateUserResponseModel.class));
        step("Проверка имени", () -> {
            assertEquals("morpheus", response.getName());
        });
        step("Проверка работы", () -> {
            assertEquals("zion resident", response.getJob());
        });
        step("Проверка айди даты изменения", () -> {
            assertTrue(response.getUpdatedAt() != null && !response.getUpdatedAt().isEmpty());
        });

    }
    @Test
    void successfulUpdateWithPatchTest() {
        authData.setName("morpheus");
        authData.setJob("zion resident");
        CreateUserResponseModel response = step("Изменение информации через Patch", () ->
        given()
                .body(authData)
                .contentType(JSON)
                .log().uri()
                .header("x-api-key", "reqres-free-v1")
                .when()
                .patch(baseURI + basePath + "/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().as(CreateUserResponseModel.class));
        step("Проверка имени", () -> {
            assertEquals("morpheus", response.getName());
        });
        step("Проверка работы", () -> {
            assertEquals("zion resident", response.getJob());
        });
        step("Проверка айди даты изменения", () -> {
            assertTrue(response.getUpdatedAt() != null && !response.getUpdatedAt().isEmpty());
        });


    }
}
