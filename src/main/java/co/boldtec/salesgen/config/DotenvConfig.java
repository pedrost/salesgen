package co.boldtec.salesgen.config;

import co.boldtec.salesgen.services.IConfigService;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.stereotype.Service;

@Service
public class DotenvConfig implements IConfigService {

    private final Dotenv dotenv;

    public DotenvConfig() {
        dotenv = Dotenv.load();
    }

    @Override
    public String getConfigValue(String key) {
        return dotenv.get(key);
    }
}

