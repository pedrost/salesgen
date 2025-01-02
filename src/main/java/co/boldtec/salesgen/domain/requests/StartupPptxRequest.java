package co.boldtec.salesgen.domain.requests;

import co.boldtec.salesgen.domain.Feature;
import co.boldtec.salesgen.domain.Graph;
import co.boldtec.salesgen.domain.Problem;
import co.boldtec.salesgen.domain.TeamMember;
import co.boldtec.salesgen.domain.interfaces.IStartupPptxRequest;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class StartupPptxRequest implements IStartupPptxRequest {

    @JsonProperty("startup_name")
    private String startupName;

    @JsonProperty("company_name")
    private String companyName;

    @JsonProperty("startup_website")
    private String startupWebsite;

    @JsonProperty("startup_email")
    private String companyEmail;

    @JsonProperty("startup_address")
    private String companyAddress;

    @JsonProperty("agenda")
    private String agenda;

    @JsonProperty("n")
    private String n;

    @JsonProperty("introduction_description_1")
    private String introductionDescription1;

    @JsonProperty("introduction_description_2")
    private String introductionDescription2;

    @JsonProperty("problem_1_title")
    private String problem1Title;

    @JsonProperty("problem_1_description")
    private String problem1Description;

    @JsonProperty("problem_2_title")
    private String problem2Title;

    @JsonProperty("problem_2_description")
    private String problem2Description;

    @JsonProperty("problem_3_title")
    private String problem3Title;

    @JsonProperty("problem_3_description")
    private String problem3Description;

    @JsonProperty("additional_info")
    private String additionalInfo;

    private List<Problem> problems;
    private List<Feature> features;
    private List<Graph> graphs;

    private String introduction;

    private List<TeamMember> team;

    public void constructProblems() {
        this.problems = new ArrayList<>();

        if (problem1Title != null && problem1Description != null) {
            problems.add(new Problem(problem1Title, problem1Description));
        }
        if (problem2Title != null && problem2Description != null) {
            problems.add(new Problem(problem2Title, problem2Description));
        }
        if (problem3Title != null && problem3Description != null) {
            problems.add(new Problem(problem3Title, problem3Description));
        }
    }

    public void constructFeatures() {
        this.features = new ArrayList<>();
    }

    public void constructGraphs() {
        this.graphs = new ArrayList<>();
    }

    public void populateLists() {
        constructProblems();
        constructFeatures();
        constructGraphs();
    }
}
