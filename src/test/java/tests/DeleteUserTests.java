package tests;

import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.*;
import static specs.CreateUserSpec.*;

@Owner("alexsnp")
@Feature("Удаление пользователя")
@Tag("Req")
public class DeleteUserTests  extends TestBase {

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Успешное удаление пользователя")
    void successfulDeleteTest() {
        step("Отправка запроса на удаление пользователя", () ->
        given(RequestSpec)
                .when()
                .delete("/users/2")
                .then()
                .spec(responseSpec(204)));
    }
}