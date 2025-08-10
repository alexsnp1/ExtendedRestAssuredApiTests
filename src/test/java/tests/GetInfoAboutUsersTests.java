package tests;

import models.CreateUserResponseModel;
import models.GetInfoResponseModel;
import models.GetSingleUserInfoResponseModel;
import models.UserData;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static specs.CreateUserSpec.*;

public class GetInfoAboutUsersTests extends TestBase  {

    @Test
    void GetListUsersTest() {

        GetInfoResponseModel response = step("Получение списка пользователей", () ->
        given(GetListUsersRequestSpec)
                .when()
                .get("/users")
                .then()
                .spec(GetInfoResponseSpec)
                .extract().as(GetInfoResponseModel.class));
        step("Проверка номера страницы", () -> {
            assertEquals(2, response.getPage());
        });
        step("Проверка отображения порядковых номеров пользователей", () -> {
            List<Integer> actualIds = response.getData()
                    .stream()
                    .map(UserData::getId)
                    .toList();
            assertEquals(List.of(7, 8, 9, 10, 11, 12), actualIds);
        });
        step("Проверка конкретного пользователя", () -> {
            assertTrue(response.getData().stream()
                    .anyMatch(u -> u.getEmail().equals("george.edwards@reqres.in")
                            && u.getFirst_name().equals("George")
                            && u.getLast_name().equals("Edwards")
                            && u.getAvatar().equals("https://reqres.in/img/faces/11-image.jpg")));
        });
    }

    @Test
    void GetSingleUserTest() {
        GetSingleUserInfoResponseModel response = step("Получение информации о пользователе", () ->
        given(GetSingleUserRequestSpec)
                .when()
                .get("/users/11")
                .then()
                .spec(GetInfoResponseSpec)
                .extract().as(GetSingleUserInfoResponseModel.class));
        step("Проверка конкретного пользователя", () -> {
            UserData u = response.getData();
            assertEquals(11, u.getId());
            assertEquals("george.edwards@reqres.in", u.getEmail());
            assertEquals("George", u.getFirst_name());
            assertEquals("Edwards", u.getLast_name());
            assertEquals("https://reqres.in/img/faces/11-image.jpg", u.getAvatar());
        });


    }
    @Test
    void unsuccessfulGetSingleUserTest() {
        GetSingleUserInfoResponseModel response = step("Получение информации и пользователе c ошибкой", () ->
        given(GetSingleUserRequestSpec)
                .when()
                .get("/users/23")
                .then()
                .spec(unsuccessfulGetInfoResponseSpec)
                .extract().as(GetSingleUserInfoResponseModel.class));
    }
}