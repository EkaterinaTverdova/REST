package tests;

import org.testng.annotations.Test;
import steps.RateStep;

public class ApiTest extends BaseTest {
    private final RateStep step = new RateStep();
    String response = step.getOnlinerResponse(200);

    @Test
    public void checkOnliner() {
        validator.validateSchame();
    }

    @Test
    public void checkOnlinerRegex() {
        validator.validateRegex(response);
    }

    @Test
    public void checkOnlinerHeaders() {
        validator.validateHeaders();
    }

    @Test
    public void checkOnlinerKeys() {
        validator.validateKeys();
    }
}
