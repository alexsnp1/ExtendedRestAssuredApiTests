package tests;

import models.lombok.CreateUserBodyModel;
import models.lombok.CreateUserResponseModel;
import org.junit.jupiter.api.Test;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static specs.CreateUserSpec.LoginRequestSpec;
import static specs.CreateUserSpec.loginResponseSpec;

public class CreateUserTests extends TestBase{

    @Test
    void successfulCreateWithSpecsTest() {

        CreateUserBodyModel authData = new CreateUserBodyModel();
        authData.setName("morpheus");
        authData.setJob("leader");

        CreateUserResponseModel response = step("Создание пользователя", () ->
                given(LoginRequestSpec)
                        .body(authData)

                        .when()
                        .post(baseURI + basePath + "/users")
                        .then()
                        .spec(loginResponseSpec)
                        .extract().as(CreateUserResponseModel.class));

        step("Проверка имени", () -> {
            assertEquals("morpheus", response.getName());
        });
        step("Проверка работы", () -> {
            assertEquals("leader", response.getJob());
        });
        step("Проверка айди", () -> {
            assertTrue(response.getId() != null && !response.getId().isEmpty());
        });
        step("Проверка айди даты создания", () -> {
            assertTrue(response.getCreatedAt() != null && !response.getCreatedAt().isEmpty());
        });
    }
}
