package cornaton.maxence.technicaltest.iexec.configuration;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import cornaton.maxence.technicaltest.iexec.dao.MongoDbLocalTaskRepository;
import cornaton.maxence.technicaltest.iexec.dao.TaskRepository;
import cornaton.maxence.technicaltest.iexec.exceptions.UndefinedPropertyException;
import cornaton.maxence.technicaltest.iexec.model.LocalTask;
import cornaton.maxence.technicaltest.iexec.service.BlockchainTaskService;
import cornaton.maxence.technicaltest.iexec.service.BlockchainTaskServiceImpl;
import cornaton.maxence.technicaltest.iexec.service.DatabaseLocalTaskServiceImpl;
import cornaton.maxence.technicaltest.iexec.service.LocalTaskService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.web3j.crypto.Credentials;
import org.web3j.generated.contracts.TaskCounter;
import org.web3j.protocol.Web3j;
import org.web3j.tx.RawTransactionManager;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.StaticGasProvider;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.function.Function;

/**
 * Configure the application by providing some beans and some properties.
 */
@Configuration
@EnableMongoRepositories
public class ApplicationConfiguration extends AbstractMongoClientConfiguration {
    /**
     * List of all property providers. They are declared in the significant order.
     * For instance, a property that is declared as an environment variable and as a Java property will take the environment variable value.
     */
    private static final List<Function<ConfigurationProperty, String>> propertyProviders = Arrays.asList(
            property -> System.getenv(property.name()),
            property -> System.getProperty(property.name().toLowerCase(Locale.ROOT).replaceAll("_", "."))
    );
    protected final Logger log = LogManager.getLogger(this.getClass());

    @Bean
    public LocalTaskService localTaskService() {
        return new DatabaseLocalTaskServiceImpl();
    }

    @Override
    protected String getDatabaseName() {
        return getRequiredProperty(ConfigurationProperty.DB_NAME);
    }

    @Override
    public MongoClient mongoClient() {
        final String dbHost = getRequiredProperty(ConfigurationProperty.DB_HOST);
        final String dbPort = getRequiredProperty(ConfigurationProperty.DB_PORT);
        return MongoClients.create("mongodb://" + dbHost + ":" + dbPort + "/" + getDatabaseName());
    }

    /**
     * Loop through all declared property providers to find the value of given property.
     * If no provider declares that value, a default value is returned instead.
     *
     * @param property Property whose value should be retrieved.
     * @return The value of the property found in the first provider or a default value if no provider declares that value.
     * @throws UndefinedPropertyException If a property has no default value and no provider has been able to provide its value.
     */
    private String getProperty(ConfigurationProperty property) throws UndefinedPropertyException {
        return propertyProviders
                .stream()
                .map(provider -> provider.apply(property))
                .filter(Objects::nonNull)
                .findFirst()
                .orElseGet(property::getDefaultValue);
    }

    /**
     * Loop through all declared property providers to find the value of given property.
     * If no provider declares that value, a default value is returned instead.
     * If no default value is declared, then the application is killed.
     *
     * @param property Property whose value should be retrieved.
     * @return The value of the property found in the first provider or a default value if no provider declares that value.
     */
    private String getRequiredProperty(ConfigurationProperty property) {
        try {
            return getProperty(property);
        } catch (UndefinedPropertyException e) {
            log.error("Can't find a required property. Exiting the app...");
            log.error(e);
            System.exit(-1);
            return null;
        }
    }

    @Bean
    @Autowired
    public TaskRepository<LocalTask> localTaskRepository(MongoOperations operations) {
        return new MongoDbLocalTaskRepository(operations);
    }

    @Bean
    @Autowired
    public TransactionManager transactionManager(Web3j web3j) {
        System.out.println(System.getenv());
        final String privateKey = getRequiredProperty(ConfigurationProperty.BLOCKCHAIN_PRIVATE_KEY);
        final long chainId = Long.parseLong(getRequiredProperty(ConfigurationProperty.BLOCKCHAIN_ID));
        return new RawTransactionManager(web3j, Credentials.create(privateKey), chainId);
    }

    @Bean
    public BlockchainTaskService blockchainTaskService() {
        return new BlockchainTaskServiceImpl();
    }

    @Bean
    @Autowired
    public TaskCounter taskCounter(Web3j web3j, TransactionManager transactionManager) {
        final String contractUrl = getRequiredProperty(ConfigurationProperty.BLOCKCHAIN_CONTRACT_TASKCOUNT_URL);
        final String gasPrice = getRequiredProperty(ConfigurationProperty.BLOCKCHAIN_GAS_PRICE);
        final String gasLimit = getRequiredProperty(ConfigurationProperty.BLOCKCHAIN_GAS_LIMIT);
        return TaskCounter.load(
                contractUrl,
                web3j,
                transactionManager,
                new StaticGasProvider(new BigInteger(gasPrice), new BigInteger(gasLimit))
        );
    }
}
