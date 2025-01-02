package co.boldtec.salesgen.usecases;

import co.boldtec.salesgen.domain.interfaces.IOpenAIClient;
import co.boldtec.salesgen.domain.requests.OpenAiPromptRequest;
import co.boldtec.salesgen.domain.requests.StartupPptxRequest;
import co.boldtec.salesgen.domain.interfaces.IStartupPptxRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

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
            System.out.printf("Error parsing JSON response into StartupPptxRequest: %s \n", e.getMessage());
            return null;
        }
    }

    private static String readDefaultPrompt() {
        String filePath = "src/main/resources/prompts/default.txt";
        try {
            return new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            System.out.printf("Error reading file at %s: %s", filePath, e.getMessage());
            return null;
        }
    }
}
