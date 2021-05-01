package cornaton.maxence.technicaltest.iexec.configuration;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import cornaton.maxence.technicaltest.iexec.dao.LocalTaskRepository;
import cornaton.maxence.technicaltest.iexec.dao.MongoDbLocalTaskRepository;
import cornaton.maxence.technicaltest.iexec.service.DatabaseLocalTaskServiceImpl;
import cornaton.maxence.technicaltest.iexec.service.LocalTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

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

    @Bean
    @Autowired
    public LocalTaskRepository localTaskRepository(MongoOperations operations) {
        return new MongoDbLocalTaskRepository(operations);
    }

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
}
