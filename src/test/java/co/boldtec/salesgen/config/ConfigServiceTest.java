package co.boldtec.salesgen.config;

import co.boldtec.salesgen.services.ConfigService;
import co.boldtec.salesgen.services.IConfigService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ConfigServiceTest {

    @Mock
    private IConfigService dotenvLib;

    @InjectMocks
    private ConfigService configService;

    @Test
    void getConfigValue_ShouldReturnCorrectValue() {
        // Given
        String key = "OPEN_AI_API_KEY";
        String expectedValue = "sk-pj45dl3sdlgn...";
        when(dotenvLib.get(key)).thenReturn(expectedValue);

        // When
        String result = configService.get(key);

        // Then
        assertEquals(expectedValue, result, "The config value should match the mock value.");
    }

    @Test
    void getConfigValue_ShouldHandleEmptyValue() {
        // Given
        String key = "EMPTY_CONFIG_KEY";
        String expectedValue = ""; // Simulating an empty value
        when(dotenvLib.get(key)).thenReturn(expectedValue);

        // When
        String result = configService.get(key);

        // Then
        assertEquals(expectedValue, result, "The config value should be empty as mocked.");
    }
}
