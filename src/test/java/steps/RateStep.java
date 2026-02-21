package steps;

import tests.BaseTest;

import static io.restassured.RestAssured.given;

public class RateStep {
    public String getOnlinerResponse(int code) {
        return given()
                .log().all()
                .when()
                .get(BaseTest.BASE_URL)
                .then()
                .log().all()
                .statusCode(code)
                .extract()
                .body()
                .asString();
    }
}
