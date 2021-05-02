package cornaton.maxence.technicaltest.iexec.configuration;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import cornaton.maxence.technicaltest.iexec.dao.MongoDbLocalTaskRepository;
import cornaton.maxence.technicaltest.iexec.dao.TaskRepository;
import cornaton.maxence.technicaltest.iexec.model.LocalTask;
import cornaton.maxence.technicaltest.iexec.service.BlockchainTaskService;
import cornaton.maxence.technicaltest.iexec.service.BlockchainTaskServiceImpl;
import cornaton.maxence.technicaltest.iexec.service.DatabaseLocalTaskServiceImpl;
import cornaton.maxence.technicaltest.iexec.service.LocalTaskService;
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

    @Bean
    public LocalTaskService localTaskService() {
        return new DatabaseLocalTaskServiceImpl();
    }

    @Override
    protected String getDatabaseName() {
        return getProperty(ConfigurationProperty.DB_NAME);
    }

    @Override
    public MongoClient mongoClient() {
        final String dbHost = getProperty(ConfigurationProperty.DB_HOST);
        final String dbPort = getProperty(ConfigurationProperty.DB_PORT);
        return MongoClients.create("mongodb://" + dbHost + ":" + dbPort + "/" + getDatabaseName());
    }

    /**
     * Loop through all declared property providers to find the value of given property.
     * If no provider declares that value, a default value is returned instead.
     *
     * @param property Property whose value should be retrieved.
     * @return The value of the property found in the first provider or a default value if no provider declares that value.
     */
    private String getProperty(ConfigurationProperty property) {
        return propertyProviders
                .stream()
                .map(provider -> provider.apply(property))
                .filter(Objects::nonNull)
                .findFirst()
                .orElse(property.getDefaultValue());
    }

    @Bean
    @Autowired
    public TaskRepository<LocalTask> localTaskRepository(MongoOperations operations) {
        return new MongoDbLocalTaskRepository(operations);
    }

    @Bean
    @Autowired
    public TransactionManager transactionManager(Web3j web3j) {
        final String contractUrl = getProperty(ConfigurationProperty.BLOCKCHAIN_CONTRACT_URL);
        final long chainId = Long.parseLong(getProperty(ConfigurationProperty.BLOCKCHAIN_ID));
        return new RawTransactionManager(web3j, Credentials.create(contractUrl), chainId);
    }

    @Bean
    public BlockchainTaskService blockchainTaskService() {
        return new BlockchainTaskServiceImpl();
    }

    @Bean
    @Autowired
    public TaskCounter taskCounter(Web3j web3j, TransactionManager transactionManager) {
        final String contractUrl = getProperty(ConfigurationProperty.BLOCKCHAIN_CONTRACT_URL);
        return TaskCounter.load(contractUrl, web3j, transactionManager, new StaticGasProvider(BigInteger.valueOf(2000000000), BigInteger.valueOf(2000000000)));
    }
}
