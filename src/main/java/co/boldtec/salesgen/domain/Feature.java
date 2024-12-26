package co.boldtec.salesgen.domain;

public class Feature {
    String name;
    String description;

    // Constructor and getters
    public Feature(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
