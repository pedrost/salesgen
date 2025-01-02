package co.boldtec.salesgen.services;

import org.springframework.stereotype.Service;

@Service
public class ConfigService {

    private final IConfigService configService;

    public ConfigService(IConfigService configService) {
        this.configService = configService;
    }

    public String get(String key) {
        return configService.get(key);
    }
}
