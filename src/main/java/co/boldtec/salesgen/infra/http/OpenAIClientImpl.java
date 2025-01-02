package co.boldtec.salesgen.infra.http;

import co.boldtec.salesgen.domain.interfaces.IOpenAIClient;
import co.boldtec.salesgen.domain.requests.OpenAiPromptRequest;
import co.boldtec.salesgen.services.IConfigService;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class OpenAIClientImpl implements IOpenAIClient {
    private final String apiKey;
    private static final String API_URL = "https://api.openai.com/v1/chat/completions";
    private static final String GPT_VERSION = "gpt-4o-mini";
    private static final int MAX_TOKENS = 2000;

    private final OkHttpClient client;

    public OpenAIClientImpl(IConfigService configService) {
        this.apiKey = configService.get("OPENAI_API_KEY");
        this.client = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .build();
    }

    @Override
    public String sendPrompt(List<OpenAiPromptRequest> messageHistory) {
        JSONArray messages = new JSONArray();

        for (OpenAiPromptRequest msg : messageHistory) {
            JSONObject message = new JSONObject();
            message.put("role", msg.getRole());
            message.put("content", msg.getContent());
            messages.put(message);
        }

        JSONObject jsonBody = new JSONObject();
        jsonBody.put("model", GPT_VERSION);
        jsonBody.put("messages", messages);
        jsonBody.put("max_tokens", MAX_TOKENS);

        // Build the request
        RequestBody body = RequestBody.create(jsonBody.toString(), MediaType.get("application/json"));
        Request request = new Request.Builder()
                .url(API_URL)
                .post(body)
                .addHeader("Authorization", "Bearer " + apiKey)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                return "Error: Request failed with status " + response.code();
            }

            String responseBody = response.body().string();
            JSONObject responseJson = new JSONObject(responseBody);

            if (responseJson.has("choices") && !responseJson.getJSONArray("choices").isEmpty()) {
                JSONObject choice = responseJson.getJSONArray("choices").getJSONObject(0);
                return choice.getJSONObject("message").getString("content");
            } else {
                return "Error: No response from model";
            }
        } catch (IOException e) {
            return "Error: " + e.getMessage();
        }
    }
}
