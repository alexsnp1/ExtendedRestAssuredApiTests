package tests;

import models.CreateUserResponseModel;
import models.UserBodyModel;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static specs.CreateUserSpec.*;

public class UpdateInfoTests extends TestBase {
    UserBodyModel authData = new UserBodyModel();

    @Test
    void successfulUpdateWithPutTest() {
        authData.setName("morpheus");
        authData.setJob("zion resident");
        CreateUserResponseModel response = step("Изменение информации через Put", () ->
        given(UpdateInfoRequestSpec)
                .body(authData)
                .when()
                .put("/users/2")
                .then()
                .spec(UpdateInfoResponseSpec)
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
        given(UpdateInfoRequestSpec)
                .body(authData)
                .when()
                .patch("/users/2")
                .then()
                .spec(UpdateInfoResponseSpec)
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
