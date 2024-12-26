package co.boldtec.salesgen.domain;

public class Problem {
    String name;
    String description;

    // Constructor and getters
    public Problem(String name, String description) {
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
