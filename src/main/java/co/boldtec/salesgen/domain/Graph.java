package co.boldtec.salesgen.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Graph {
    private String title;
    private String description;
    private Map<Integer, Integer> data;

    @JsonCreator
    public Graph(
            @JsonProperty("title") String title,
            @JsonProperty("description") String description,
            @JsonProperty("data") Map<Integer, Integer> data) {
        this.title = title;
        this.description = description;
        this.data = data;
    }
}
