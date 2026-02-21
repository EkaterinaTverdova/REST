package tests;

import io.restassured.specification.RequestSpecification;
import org.testng.annotations.DataProvider;
import utils.PropertyReader;
import validators.RateValidator;

import static enums.Currency.*;

public class BaseTest {
    public static final String BASE_URL_VARIABLE = PropertyReader.getProperty("api.url_variable");
    public static final String BASE_URL = PropertyReader.getProperty("api.url");
    public final RateValidator validator = new RateValidator();

    private RequestSpecification spec;

    @DataProvider(name = "currencies")
    public Object[][] currencyProvider() {
        return new Object[][]{
                {RUB},
                {EUR},
                {USD}
        };
    }
}
