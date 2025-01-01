package co.boldtec.salesgen.usecases;

import co.boldtec.salesgen.domain.interfaces.IOpenAIClient;
import co.boldtec.salesgen.domain.requests.OpenAiPromptRequest;
import co.boldtec.salesgen.domain.requests.StartupPptxRequest;
import co.boldtec.salesgen.domain.interfaces.IStartupPptxRequest;
import lombok.extern.slf4j.Slf4j;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class GenerateStartupStructFromTextUseCase {

    private final IOpenAIClient openAIClient;
    private final ObjectMapper objectMapper;

    public GenerateStartupStructFromTextUseCase(IOpenAIClient openAIClient) {
        this.openAIClient = openAIClient;
        this.objectMapper = new ObjectMapper();  // Initialize ObjectMapper for JSON parsing
    }

    public IStartupPptxRequest generatePptxDataFromText(String inputText) {

        List<OpenAiPromptRequest> messages = new ArrayList<>();
        messages.add(new OpenAiPromptRequest("system", readDefaultPrompt()));
        messages.add(new OpenAiPromptRequest("user", inputText));

        String response = openAIClient.sendPrompt(messages);

        try {

            return objectMapper.readValue(response, StartupPptxRequest.class);
        } catch (IOException e) {
            log.error("Error parsing JSON response into StartupPptxRequest: {}", e.getMessage(), e);
            return null;
        }
    }

    private static String readDefaultPrompt() {
        String filePath = "src/main/resources/prompts/default.txt";
        try {
            return new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            log.error("Error reading file at {}: {}", filePath, e.getMessage(), e);
            return null;
        }
    }
}
