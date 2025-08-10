package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.filter.log.LogDetail.STATUS;
import static io.restassured.http.ContentType.JSON;

public class CreateUserSpec {
    public static RequestSpecification CreateUserRequestSpec = with()
            .filter(withCustomTemplates())
            .log().uri()
            .log().body()
            .log().headers()
            .header("x-api-key", "reqres-free-v1")
            .contentType(JSON);

    public static ResponseSpecification CreateUserResponseSpec = new ResponseSpecBuilder()
            .expectStatusCode(201)
            .log(STATUS)
            .log(BODY)
            .build();

    public static RequestSpecification DeleteUserRequestSpec = with()
            .filter(withCustomTemplates())
            .log().uri()
            .log().headers()
            .header("x-api-key", "reqres-free-v1");

    public static ResponseSpecification DeleteUserResponseSpec = new ResponseSpecBuilder()
            .expectStatusCode(204)
            .log(STATUS)
            .log(BODY)
            .build();

    public static RequestSpecification RegistrationRequestSpec = with()
            .filter(withCustomTemplates())
            .log().uri()
            .log().headers()
            .header("x-api-key", "reqres-free-v1")
            .contentType(JSON);
    public static ResponseSpecification unsuccessfulRegistrationResponseSpec = new ResponseSpecBuilder()
            .expectStatusCode(400)
            .log(STATUS)
            .log(BODY)
            .build();
    public static ResponseSpecification successfulRegistrationResponseSpec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .log(STATUS)
            .log(BODY)
            .build();

    public static RequestSpecification UpdateInfoRequestSpec = with()
            .filter(withCustomTemplates())
            .log().uri()
            .log().headers()
            .header("x-api-key", "reqres-free-v1")
            .contentType(JSON);

    public static ResponseSpecification UpdateInfoResponseSpec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .log(STATUS)
            .log(BODY)
            .build();
}
