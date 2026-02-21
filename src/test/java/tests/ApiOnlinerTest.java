package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.PropertyReader;

import static enums.Currency.RUB;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ApiOnlinerTest extends BaseTest{
    @Test(dataProvider = "currencies")
    public void checkRates(enums.Currency currency) {
        String url = BASE_URL_VARIABLE.formatted(currency);
        String responceBody = given()
                .log().all()
                .when()
                .get(url)
                .then()
                .log().all()
                .statusCode(200)
                .header("Content-Type", containsString(PropertyReader.getProperty("api.Content_Type")))
                .body("$", hasKey("amount"))
                .body("scale", equalTo(1))
                .extract()
                .asString();
        System.out.println("Ответ на запрос: " + responceBody);
    }
}
