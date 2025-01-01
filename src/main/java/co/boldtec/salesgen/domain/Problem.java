package co.boldtec.salesgen.domain;

import lombok.Data;

@Data
public class Problem {
    String name;
    String description;

    // Constructor and getters
    public Problem(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
