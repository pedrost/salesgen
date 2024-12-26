package co.boldtec.salesgen.domain;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class Presentation {

    private Long id;
    private String name;

    public Presentation(String name) {
        if (!StringUtils.isEmpty(name)) {
            this.name = name;
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String value) {
        this.name = value;
    }
}

class Graph {
    String name;
    List<Double> x;
    List<Double> y;

    // Constructor and getters
    public Graph(String name, List<Double> x, List<Double> y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }

    public String getName() {
        return name;
    }

    public List<Double> getX() {
        return x;
    }

    public List<Double> getY() {
        return y;
    }
}

