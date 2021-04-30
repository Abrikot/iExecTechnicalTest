package cornaton.maxence.technicaltest.iexec.configuration;

import cornaton.maxence.technicaltest.iexec.service.LocalTaskService;
import cornaton.maxence.technicaltest.iexec.service.LocalTaskServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public LocalTaskService localTaskService() {
        return new LocalTaskServiceImpl();
    }
}
