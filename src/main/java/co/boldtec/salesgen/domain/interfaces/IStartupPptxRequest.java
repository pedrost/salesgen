package co.boldtec.salesgen.domain.interfaces;

import co.boldtec.salesgen.domain.*;

import java.util.List;

public interface IStartupPptxRequest {
    String getCompanyName();
    String getCompanyEmail();
    String getCompanyAddress();

    String getStartupName();
    String getStartupWebsite();

    List<Problem> getProblems();
    List<Feature> getFeatures();
    List<Graph> getGraphs();

    String getIntroduction();
    String getIntroductionDescription1();
    String getIntroductionDescription2();
    String getN();
    String getAgenda();
    List<TeamMember> getTeam();
}