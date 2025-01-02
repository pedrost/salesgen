package co.boldtec.salesgen.services;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.stereotype.Service;

@Service
public class DotenvConfigService implements IConfigService {

    private final Dotenv dotenv;

    public DotenvConfigService() {
        this.dotenv = Dotenv.load();
    }

    @Override
    public String get(String key) {
        return dotenv.get(key);
    }
}
