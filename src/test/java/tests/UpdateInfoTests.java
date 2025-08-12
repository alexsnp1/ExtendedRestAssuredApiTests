package tests;

import models.CreateUserResponseModel;
import models.UserBodyModel;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.emptyOrNullString;
import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static specs.CreateUserSpec.*;

@Tag("Req")
public class UpdateInfoTests extends TestBase {
    UserBodyModel authData = new UserBodyModel("morpheus", "zion resident");

    @Test
    void successfulUpdateWithPutTest() {
        CreateUserResponseModel response = step("Изменение информации через Put", () ->
        given(RequestSpec)
                .body(authData)
                .when()
                .put("/users/2")
                .then()
                .spec(responseSpec(200))
                .extract().as(CreateUserResponseModel.class));
        step("Проверка имени", () ->
            assertEquals("morpheus", response.getName()));
        step("Проверка работы", () ->
            assertEquals("zion resident", response.getJob()));
        step("Проверка айди даты изменения", () ->
            assertThat(response.getUpdatedAt(), not(emptyOrNullString())));
    }

    @Test
    void successfulUpdateWithPatchTest() {
        CreateUserResponseModel response = step("Изменение информации через Patch", () ->
        given(RequestSpec)
                .body(authData)
                .when()
                .patch("/users/2")
                .then()
                .spec(responseSpec(200))
                .extract().as(CreateUserResponseModel.class));
        step("Проверка имени", () ->
            assertEquals("morpheus", response.getName()));
        step("Проверка работы", () ->
            assertEquals("zion resident", response.getJob()));
        step("Проверка айди даты изменения", () ->
                assertThat(response.getUpdatedAt(), not(emptyOrNullString())));


    }
}
