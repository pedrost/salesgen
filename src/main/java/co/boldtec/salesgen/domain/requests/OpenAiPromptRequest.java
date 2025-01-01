package co.boldtec.salesgen.domain.requests;

import lombok.Data;
import org.json.JSONObject;

@Data
public class OpenAiPromptRequest {
    private String role;
    private String content;

    public OpenAiPromptRequest(String role, String content) {
        this.role = role;
        this.content = content;
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("role", role);
        json.put("content", content);
        return json;
    }
}

