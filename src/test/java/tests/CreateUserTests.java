package tests;

import io.qameta.allure.restassured.AllureRestAssured;
import models.lombok.LoginBodyLombokModel;
import models.lombok.LoginResponceLombokModel;
import models.pojo.LoginBodyModel;
import models.pojo.LoginResponceModel;
import org.junit.jupiter.api.Test;

import static helpers.CustomAllureListener.withCustomTemplates;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CreateUserTests {

    @Test
    void successfulCreatePojoTest() {
//        String authData = "{\n" +
//                "    \"name\": \"morpheus\",\n" +
//                "    \"job\": \"leader\"\n" +
//                "    }";

        LoginBodyModel authData = new LoginBodyModel();
        authData.setName("morpheus");
        authData.setJob("leader");

        LoginResponceModel responce = given()
                .body(authData)
                .contentType(JSON)
                .log().uri()
                .log().body()
                .log().headers()
                .header("x-api-key", "reqres-free-v1")
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .log().status()
                .log().body()
                .statusCode(201)
//                .body("name", is("morpheus"))
//                .body("job", is("leader"))
                .body("id", not(emptyOrNullString()))
                .body("createdAt", not(emptyOrNullString()))
                .extract().as(LoginResponceModel.class);

        assertEquals("morpheus", responce.getName());
        assertEquals("leader", responce.getJob());
        assertEquals("leader", responce.getJob());
        assertTrue(responce.getId() != null && !responce.getId().isEmpty());
        assertTrue(responce.getCreatedAt() != null && !responce.getCreatedAt().isEmpty());
    }

    @Test
    void successfulCreateLombokTest() {
//        String authData = "{\n" +
//                "    \"name\": \"morpheus\",\n" +
//                "    \"job\": \"leader\"\n" +
//                "    }";

        LoginBodyLombokModel authData = new LoginBodyLombokModel();
        authData.setName("morpheus");
        authData.setJob("leader");

        LoginResponceLombokModel responce = given()
                .body(authData)
                .contentType(JSON)
                .log().uri()
                .log().body()
                .log().headers()
                .header("x-api-key", "reqres-free-v1")
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .log().status()
                .log().body()
                .statusCode(201)
//                .body("name", is("morpheus"))
//                .body("job", is("leader"))
                .body("id", not(emptyOrNullString()))
                .body("createdAt", not(emptyOrNullString()))
                .extract().as(LoginResponceLombokModel.class);

        assertEquals("morpheus", responce.getName());
        assertEquals("leader", responce.getJob());
        assertEquals("leader", responce.getJob());
        assertTrue(responce.getId() != null && !responce.getId().isEmpty());
        assertTrue(responce.getCreatedAt() != null && !responce.getCreatedAt().isEmpty());

    }

    @Test
    void successfulCreateLombokAllureTest() {
//        String authData = "{\n" +
//                "    \"name\": \"morpheus\",\n" +
//                "    \"job\": \"leader\"\n" +
//                "    }";

        LoginBodyLombokModel authData = new LoginBodyLombokModel();
        authData.setName("morpheus");
        authData.setJob("leader");

        LoginResponceLombokModel responce = given()
                .filter(new AllureRestAssured())
                .log().uri()
                .log().body()
                .log().headers()
                .header("x-api-key", "reqres-free-v1")
                .body(authData)
                .contentType(JSON)

                .when()
                .post("https://reqres.in/api/users")
                .then()
                .log().status()
                .log().body()
                .statusCode(201)
//                .body("name", is("morpheus"))
//                .body("job", is("leader"))
                .body("id", not(emptyOrNullString()))
                .body("createdAt", not(emptyOrNullString()))
                .extract().as(LoginResponceLombokModel.class);

        assertEquals("morpheus", responce.getName());
        assertEquals("leader", responce.getJob());
        assertEquals("leader", responce.getJob());
        assertTrue(responce.getId() != null && !responce.getId().isEmpty());
        assertTrue(responce.getCreatedAt() != null && !responce.getCreatedAt().isEmpty());

    }

    @Test
    void successfulCreateLombokCustomAllureTest() {
//        String authData = "{\n" +
//                "    \"name\": \"morpheus\",\n" +
//                "    \"job\": \"leader\"\n" +
//                "    }";

        LoginBodyLombokModel authData = new LoginBodyLombokModel();
        authData.setName("morpheus");
        authData.setJob("leader");

        LoginResponceLombokModel responce = step("Make request", () ->
                given()
                        .filter(withCustomTemplates())
                        .log().uri()
                        .log().body()
                        .log().headers()
                        .header("x-api-key", "reqres-free-v1")
                        .body(authData)
                        .contentType(JSON)

                        .when()
                        .post("https://reqres.in/api/users")
                        .then()
                        .log().status()
                        .log().body()
                        .statusCode(201)
//                .body("name", is("morpheus"))
//                .body("job", is("leader"))
                        .body("id", not(emptyOrNullString()))
                        .body("createdAt", not(emptyOrNullString()))
                        .extract().as(LoginResponceLombokModel.class));

        step("Check response", () -> {
            assertEquals("morpheus", responce.getName());
            assertEquals("leader", responce.getJob());
            assertEquals("leader", responce.getJob());
            assertTrue(responce.getId() != null && !responce.getId().isEmpty());
            assertTrue(responce.getCreatedAt() != null && !responce.getCreatedAt().isEmpty());
        });
    }
}
