package co.boldtec.salesgen.domain;

import lombok.Data;
import java.util.List;

@Data
public class Graph {
    String name;
    List<Double> x;
    List<Double> y;

    public Graph(String name, List<Double> x, List<Double> y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }
}
