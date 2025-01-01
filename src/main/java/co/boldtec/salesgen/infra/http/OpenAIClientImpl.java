package co.boldtec.salesgen.infra.http;

import co.boldtec.salesgen.domain.Message;
import co.boldtec.salesgen.domain.interfaces.IOpenAIClient;
import co.boldtec.salesgen.domain.requests.OpenAiPromptRequest;
import io.github.lambdua.service.OpenAiService;  // Updated import
import io.github.lambdua.service.completion.chat.ChatMessage;  // Updated import
import io.github.lambdua.service.completion.chat.ChatCompletionRequest;  // Updated import
import io.github.lambdua.service.completion.chat.ChatCompletionResult;  // Updated import

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class OpenAIClientImpl implements IOpenAIClient {
    private final OpenAiService service;

    public OpenAIClientImpl() {
        this.service = new OpenAiService(Duration.ofSeconds(30));  // Adjusted to match new dependency usage
    }

    @Override
    public String sendPrompt(List<OpenAiPromptRequest> messageHistory) {
        List<ChatMessage> messageList = new ArrayList<>();

        // Convert history to ChatMessage format
        messageHistory.forEach(chatMessage -> {
            messageList.add(new ChatMessage(chatMessage.getRole(), chatMessage.getContent()));
        });

        // Create a ChatCompletionRequest
        ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest.builder()
                .model("gpt-4")  // Specify model
                .messages(messageList)
                .maxTokens(100)  // Define max token limit
                .build();

        // Request a completion
        ChatCompletionResult chatCompletionResult = service.createChatCompletion(chatCompletionRequest);

        // Extract and return the response
        if (chatCompletionResult.getChoices() != null && !chatCompletionResult.getChoices().isEmpty()) {
            String responseContent = chatCompletionResult.getChoices().get(0).getMessage().getContent();
            System.out.println(responseContent);
            return responseContent;
        } else {
            return "Error: No response from model";
        }
    }
}
