package co.boldtec.salesgen.usecases;

import co.boldtec.salesgen.domain.Graph;
import co.boldtec.salesgen.domain.interfaces.IStartupPptxRequest;
import co.boldtec.salesgen.domain.TeamMember;
import co.boldtec.salesgen.domain.Problem;
import co.boldtec.salesgen.domain.Feature;
import co.boldtec.salesgen.domain.requests.StartupPptxRequest;
import co.boldtec.salesgen.services.IPowerPointService;

import java.util.List;

public class StartupPptxUsecase {
    private final IPowerPointService pptxService;

    public StartupPptxUsecase(IPowerPointService pptxService) {
        this.pptxService = pptxService;
    }

    public void createStartupPresentation(IStartupPptxRequest request) {
        List<Graph> graphs = request.getGraphs();
        ((StartupPptxRequest) request).populateLists();

        pptxService.GetPresentation("src/main/resources/pptx/PitchDeckDefaultPPTX.pptx")
                .AddGraphsToPresentation(graphs)
                .ReplaceText("{{startup_email}}", request.getStartupEmail())
                .ReplaceText("{{startup_address}}", request.getStartupAddress())
                .ReplaceText("{{startup_name}}", request.getStartupName())
                .ReplaceText("{{company_name}}", request.getCompanyName())
                .ReplaceText("{{startup_website}}", request.getStartupWebsite())
                .ReplaceText("{{introduction}}", request.getIntroduction())
                .ReplaceText("{{introduction_description_1}}", request.getIntroductionDescription1())
                .ReplaceText("{{introduction_description_2}}", request.getIntroductionDescription2())
                .ReplaceText("{{introduction}}", request.getIntroduction());

        if (request.getProblems() != null && !request.getProblems().isEmpty()) {
            for (int i = 0; i < request.getProblems().size(); i++) {
                Problem problem = request.getProblems().get(i);
                pptxService.ReplaceText("{{problem_" + (i + 1) + "_title}}", problem.getName())
                        .ReplaceText("{{problem_" + (i + 1) + "_description}}", problem.getDescription());
            }
        }

        if (request.getFeatures() != null && !request.getFeatures().isEmpty()) {
            for (int i = 0; i < request.getFeatures().size(); i++) {
                Feature feature = request.getFeatures().get(i);
                pptxService.ReplaceText("{{feature_" + (i + 1) + "_title}}", feature.getName())
                        .ReplaceText("{{feature_" + (i + 1) + "_description}}", feature.getDescription());
            }
        }

        if (request.getTeam() != null && !request.getTeam().isEmpty()) {
            for (int i = 0; i < request.getTeam().size(); i++) {
                TeamMember member = request.getTeam().get(i);
                pptxService.ReplaceText("{{team_" + (i + 1) + "_title}}", member.getName())
                        .ReplaceText("{{team_" + (i + 1) + "_role}}", member.getRole())
                        .ReplaceText("{{team_" + (i + 1) + "_profile_img_url}}", member.getProfileImgUrl());
            }
        }

        pptxService.SaveAs("src/main/resources/pptx/Style2.pptx");
    }
}
