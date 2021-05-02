package cornaton.maxence.technicaltest.iexec.configuration;

import cornaton.maxence.technicaltest.iexec.exceptions.UndefinedPropertyException;

public enum ConfigurationProperty {
    DB_HOST("127.0.0.1"),
    DB_NAME("iexec"),
    DB_PORT("27017"),
    BLOCKCHAIN_ID("5"), // We work by default on Goerli whose chainId is 5.
    BLOCKCHAIN_CONTRACT_TASKCOUNT_URL("0xf837B595Fb53B3e8a1feBE0846d8a0e53f44e72a"),
    BLOCKCHAIN_PRIVATE_KEY,
    BLOCKCHAIN_GAS_PRICE("2000000000"),
    BLOCKCHAIN_GAS_LIMIT("1000000000");

    private final String defaultValue;
    private final boolean noDefaultValue;

    ConfigurationProperty(String defaultValue) {
        this.defaultValue = defaultValue;
        this.noDefaultValue = false;
    }

    ConfigurationProperty() {
        this.defaultValue = null;
        this.noDefaultValue = true;
    }

    public String getDefaultValue() throws UndefinedPropertyException {
        if (noDefaultValue) {
            throw new UndefinedPropertyException("Property " + this.name() + " has no default value. Please provide one in the application properties.");
        }
        return defaultValue;
    }
}
