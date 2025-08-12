package tests;

import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import models.UserBodyModel;
import models.CreateUserResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.emptyOrNullString;
import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static specs.CreateUserSpec.*;

@Owner("alexsnp")
@Feature("Создание пользователя")
@Tag("Req")
public class CreateUserTests extends TestBase{

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Успешное создание пользователя")
    void successfulCreateTest() {
        UserBodyModel authData = new UserBodyModel("morpheus", "leader");
        CreateUserResponseModel response = step("Отправка запроса на создание пользователя", () ->
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
                assertThat(response.getId(), not(emptyOrNullString())));
        step("Проверка айди даты создания", () ->
                assertThat(response.getCreatedAt(), not(emptyOrNullString())));
    }
}