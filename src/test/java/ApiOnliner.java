import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertTrue;

public class ApiOnliner {
    @DataProvider(name = "currencies")
    public Object[][] currencyProvider() {
        return new Object[][]{
                {"RUB"},
                {"EUR"},
                {"USD"}
        };
    }

    @Test(dataProvider = "currencies")
    public void checkRates(String currency) {
        String url = "https://kurs.onliner.by/sdapi/kurs/api/bestrate?currency=%s&type=nbrb".formatted(currency);
        String responceBody = given()
                .log().all()
                .when()
                .get(url)
                .then()
                .log().all()
                .statusCode(200)
                .header("Content-Type", containsString("application/json"))
                .body("$", hasKey("amount"))
                .body("scale", equalTo(1))
                .extract()
                .toString();
        System.out.println("Ответ на запрос: " + responceBody);

        String regex = "amount\":\\s*\"\\d+,\\d{4}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(responceBody);
        //assertTrue(matcher.find());
    }
}
