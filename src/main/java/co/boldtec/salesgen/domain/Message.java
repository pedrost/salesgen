package co.boldtec.salesgen.domain;
import org.json.JSONObject;
import lombok.Data;

@Data
public class Message {
    private String role;
    private String content;

    public Message(String role, String content) {
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

