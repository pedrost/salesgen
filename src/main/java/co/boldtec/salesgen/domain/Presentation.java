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

public interface IStartupPptxRequest {
    String getCompanyName();
    String getCompanyEmail();
    String getCompanyPhone();
    String getCompanyAddress();

    String getStartupName();
    String getStartupWebsite();

    List<Problem> getProblems();
    List<Feature> getFeatures();
    List<Graph> getGraphs();

    String getIntroduction();
    List<TeamMember> getTeam();
}

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

public class TeamMember {
    String name;
    String profileImgUrl;
    String role;

    // Constructor and getters
    public TeamMember(String name, String profileImgUrl, String role) {
        this.name = name;
        this.profileImgUrl = profileImgUrl;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public String getProfileImgUrl() {
        return profileImgUrl;
    }

    public String getRole() {
        return role;
    }
}
