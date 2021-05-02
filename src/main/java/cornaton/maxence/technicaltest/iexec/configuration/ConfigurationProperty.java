package cornaton.maxence.technicaltest.iexec.configuration;

public enum ConfigurationProperty {
    DB_HOST("127.0.0.1"),
    DB_NAME("iexec"),
    DB_PORT("27017"),
    BLOCKCHAIN_ID("5"), // We work by default on Goerli whose chainId is 5.
    BLOCKCHAIN_CONTRACT_URL("0xf837B595Fb53B3e8a1feBE0846d8a0e53f44e72a");

    private final String defaultValue;

    ConfigurationProperty(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getDefaultValue() {
        return defaultValue;
    }
}
