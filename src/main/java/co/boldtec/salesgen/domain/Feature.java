package co.boldtec.salesgen.domain;

import lombok.Data;

@Data
public class Feature {
    String name;
    String description;

    public Feature(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
