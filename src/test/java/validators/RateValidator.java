package validators;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import tests.BaseTest;
import utils.PropertyReader;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasKey;
import static org.testng.Assert.assertTrue;

public class RateValidator {
    private RequestSpecification spec;

    public static Response getRate() {
        return given()
                .log().all()
                .when()
                .get(BaseTest.BASE_URL);
    }

    public void validateSchame() {
        getRate()
                .then()
                .body(matchesJsonSchemaInClasspath("schemas/rate_schema.json"));
    }

    public void validateRegex(final String responceBody) {
        String regex = "\"scale\"\\s*:\\s*\\d+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(responceBody);
        assertTrue(matcher.find(), "regex " + regex);
    }

    public void validateHeaders() {
        getRate()
                .then()
                .header("Content-Type", containsString(PropertyReader.getProperty("api.Content_Type")));
    }

    public void validateKeys() {
        getRate()
                .then()
                .body("$", hasKey("amount"))
                .body("$", hasKey("grow"))
                .body("$", hasKey("delta"));
    }
}
