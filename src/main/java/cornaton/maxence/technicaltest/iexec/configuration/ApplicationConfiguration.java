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
import org.springframework.beans.factory.annotation.Value;
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
import java.util.Objects;

@Configuration
@EnableMongoRepositories
public class ApplicationConfiguration extends AbstractMongoClientConfiguration {
    @Value("${db.name}")
    private String dbName;

    @Value("${db.host:127.0.0.1}")
    private String defaultDbHost;

    @Value("${db.port:27017}")
    private String defaultDbPort;

    @Bean
    public LocalTaskService localTaskService() {
        return new DatabaseLocalTaskServiceImpl();
    }

    private static final String contractUrl = System.getenv().getOrDefault("WEB3J_CONTRACT_URL", "0xf837B595Fb53B3e8a1feBE0846d8a0e53f44e72a");

    @Override
    protected String getDatabaseName() {
        return dbName;
    }

    @Override
    public MongoClient mongoClient() {
        final String dbHost = getDatabaseHost();
        return MongoClients.create("mongodb://" + dbHost + ":" + defaultDbPort + "/" + getDatabaseName());
    }

    private static final List<String> databaseHostProviders = Arrays.asList(
            System.getenv("DB_HOST"),
            System.getProperty("DB_HOST")
    );

    private String getDatabaseHost() {
        return databaseHostProviders
                .stream()
                .filter(Objects::nonNull)
                .findFirst()
                .orElse(defaultDbHost);
    }

    private static final long chainId = Long.parseLong(System.getenv().getOrDefault("WEB3J_CHAIN_ID", "5"));

    @Bean
    @Autowired
    public TaskRepository<LocalTask> localTaskRepository(MongoOperations operations) {
        return new MongoDbLocalTaskRepository(operations);
    }

    @Bean
    @Autowired
    public TransactionManager transactionManager(Web3j web3j) {
        return new RawTransactionManager(web3j, Credentials.create(contractUrl), chainId);
    }

    @Bean
    public BlockchainTaskService blockchainTaskService() {
        return new BlockchainTaskServiceImpl();
    }

    @Bean
    @Autowired
    public TaskCounter taskCounter(Web3j web3j, TransactionManager transactionManager) {
        return TaskCounter.load(contractUrl, web3j, transactionManager, new StaticGasProvider(BigInteger.valueOf(2000000000), BigInteger.valueOf(2000000000)));
    }
}
