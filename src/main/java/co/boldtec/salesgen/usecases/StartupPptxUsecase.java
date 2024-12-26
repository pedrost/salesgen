package co.boldtec.salesgen.usecases;

import co.boldtec.salesgen.domain.IStartupPptxRequest;
import co.boldtec.salesgen.domain.TeamMember;
import co.boldtec.salesgen.domain.Problem;
import co.boldtec.salesgen.domain.Feature;
import co.boldtec.salesgen.services.IPowerPointService;

public class StartupPptxUsecase {
    private final IPowerPointService pptxService;

    public StartupPptxUsecase(IPowerPointService pptxService) {
        this.pptxService = pptxService;
    }

    public void createStartupPresentation(IStartupPptxRequest request) {
        pptxService.GetPresentation("src/main/resources/pptx/Style1.pptx")
                .ReplaceText("{{company_name}}", request.getCompanyName())
                .ReplaceText("{{company_email}}", request.getCompanyEmail())
                .ReplaceText("{{company_phone}}", request.getCompanyPhone())
                .ReplaceText("{{company_address}}", request.getCompanyAddress())
                .ReplaceText("{{startup_name}}", request.getStartupName())
                .ReplaceText("{{startup_website}}", request.getStartupWebsite())
                .ReplaceText("{{introduction}}", request.getIntroduction());

        for (int i = 0; i < request.getProblems().size(); i++) {
            Problem problem = request.getProblems().get(i);
            pptxService.ReplaceText("{{problem_" + (i + 1) + "_name}}", problem.getName())
                    .ReplaceText("{{problem_" + (i + 1) + "_description}}", problem.getDescription());
        }

        for (int i = 0; i < request.getFeatures().size(); i++) {
            Feature feature = request.getFeatures().get(i);
            pptxService.ReplaceText("{{feature_" + (i + 1) + "_name}}", feature.getName())
                    .ReplaceText("{{feature_" + (i + 1) + "_description}}", feature.getDescription());
        }

        for (int i = 0; i < request.getTeam().size(); i++) {
            TeamMember member = request.getTeam().get(i);
            pptxService.ReplaceText("{{team_" + (i + 1) + "_name}}", member.getName())
                    .ReplaceText("{{team_" + (i + 1) + "_role}}", member.getRole())
                    .ReplaceText("{{team_" + (i + 1) + "_profile_img_url}}", member.getProfileImgUrl());
        }

    }
}