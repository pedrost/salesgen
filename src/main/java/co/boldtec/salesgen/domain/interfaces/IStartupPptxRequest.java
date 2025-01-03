package co.boldtec.salesgen.domain.interfaces;

import co.boldtec.salesgen.domain.*;

import java.util.List;

public interface IStartupPptxRequest {
    String getStartupEmail();
    String getStartupAddress();

    String getStartupName();
    String getCompanyName();
    String getStartupWebsite();

    List<Problem> getProblems();
    List<Feature> getFeatures();
    List<Graph> getGraphs();

    String getIntroduction();
    String getIntroductionDescription1();
    String getIntroductionDescription2();
    List<TeamMember> getTeam();
}