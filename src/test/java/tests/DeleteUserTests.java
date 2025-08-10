package tests;

import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.*;
import static specs.CreateUserSpec.*;

public class DeleteUserTests  extends TestBase {

    @Test
    void successfulDeleteTest() {
        step("Удаление пользователя", () -> {
        given(DeleteUserRequestSpec)
                .when()
                .delete("/users/2")
                .then()
                .spec(DeleteUserResponseSpec);
        });
    }
}
