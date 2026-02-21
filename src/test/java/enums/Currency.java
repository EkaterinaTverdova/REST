package enums;

public enum Currency {
    RUB("RUB"),
    USD("USD"),
    EUR("EUR");
    private final String code;

    Currency(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
