package co.boldtec.salesgen.domain;

import java.util.List;

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