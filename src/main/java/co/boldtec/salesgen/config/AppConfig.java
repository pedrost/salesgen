package co.boldtec.salesgen.config;

import co.boldtec.salesgen.services.ConfigService;
import co.boldtec.salesgen.services.IConfigService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public ConfigService configService(IConfigService dotenvConfigService) {
        return new ConfigService(dotenvConfigService);
    }
}
