package tests;

import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import models.GetInfoResponseModel;
import models.GetSingleUserInfoResponseModel;
import models.UserData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static specs.CreateUserSpec.*;

@Owner("alexsnp")
@Feature("Получение данных о пользователе")
@Tag("Req")
public class GetInfoAboutUsersTests extends TestBase  {

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Проверка списка пользователей второй страницы")
    void GetListUsersTest() {
        GetInfoResponseModel response = step("Запрос списка пользователей (page=2)", () ->
        given(RequestSpec)
                .when()
                .queryParam("page", 2)
                .get("/users")
                .then()
                .spec(responseSpec(200))
                .extract().as(GetInfoResponseModel.class));
        step("Проверка: номер страницы должен быть 2", () ->
            assertEquals(2, response.getPage()));
        step("Проверка: список ID пользователей", () -> {
            List<Integer> actualIds = response.getData()
                    .stream()
                    .map(UserData::getId)
                    .toList();
            assertEquals(List.of(7, 8, 9, 10, 11, 12), actualIds);
        });
        step("Проверка полей пользователя с ID=11", () ->
            assertTrue(response.getData().stream()
                    .anyMatch(u -> u.getEmail().equals("george.edwards@reqres.in")
                            && u.getFirst_name().equals("George")
                            && u.getLast_name().equals("Edwards")
                            && u.getAvatar().equals("https://reqres.in/img/faces/11-image.jpg"))));
    }
    @Test
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Проверка данных пользователя по ID")
    void GetSingleUserTest() {
        GetSingleUserInfoResponseModel response = step("Отправка запроса на получение пользователя ID=11", () ->
        given(RequestSpec)
                .when()
                .get("/users/11")
                .then()
                .spec(responseSpec(200))
                .extract().as(GetSingleUserInfoResponseModel.class));
        step("Проверка полей пользователя с ID=11", () -> {
            UserData u = response.getData();
            assertEquals(11, u.getId());
            assertEquals("george.edwards@reqres.in", u.getEmail());
            assertEquals("George", u.getFirst_name());
            assertEquals("Edwards", u.getLast_name());
            assertEquals("https://reqres.in/img/faces/11-image.jpg", u.getAvatar());
        });
    }
    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Проверка данных пользователя по несуществующему ID")
    void unsuccessfulGetSingleUserTest() {
        GetSingleUserInfoResponseModel response = step("Отправка запроса на получение пользователя ID=23", () ->
        given(RequestSpec)
                .when()
                .get("/users/23")
                .then()
                .spec(responseSpec(404))
                .extract().as(GetSingleUserInfoResponseModel.class));
    }
}