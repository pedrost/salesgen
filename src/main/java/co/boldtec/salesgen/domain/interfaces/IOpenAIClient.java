package co.boldtec.salesgen.domain.interfaces;

import co.boldtec.salesgen.domain.requests.OpenAiPromptRequest;

import java.util.List;

public interface IOpenAIClient {
    String sendPrompt(List<OpenAiPromptRequest> messageHistory);
}
