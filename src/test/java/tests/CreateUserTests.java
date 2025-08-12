package tests;

import models.UserBodyModel;
import models.CreateUserResponseModel;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static specs.CreateUserSpec.*;

@Tag("Req")
public class CreateUserTests extends TestBase{

    @Test
    void successfulCreateTest() {
        UserBodyModel authData = new UserBodyModel("morpheus", "leader");

        CreateUserResponseModel response = step("Создание пользователя", () ->
                given(RequestSpec)
                        .body(authData)
                        .when()
                        .post("/users")
                        .then()
                        .spec(responseSpec(201))
                        .extract().as(CreateUserResponseModel.class));

        step("Проверка имени", () ->
            assertEquals("morpheus", response.getName()));
        step("Проверка работы", () ->
            assertEquals("leader", response.getJob()));
        step("Проверка айди", () ->
            assertTrue(response.getId() != null && !response.getId().isEmpty()));
        step("Проверка айди даты создания", () ->
            assertTrue(response.getCreatedAt() != null && !response.getCreatedAt().isEmpty()));
    }
}
