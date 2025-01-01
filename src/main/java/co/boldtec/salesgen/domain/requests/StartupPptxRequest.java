package co.boldtec.salesgen.domain.requests;

import co.boldtec.salesgen.domain.Feature;
import co.boldtec.salesgen.domain.Graph;
import co.boldtec.salesgen.domain.Problem;
import co.boldtec.salesgen.domain.TeamMember;
import co.boldtec.salesgen.domain.interfaces.IStartupPptxRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StartupPptxRequest implements IStartupPptxRequest {

    private String companyName;
    private String companyEmail;
    private String companyPhone;
    private String companyAddress;

    private String startupName;
    private String startupWebsite;

    private List<Problem> problems;
    private List<Feature> features;
    private List<Graph> graphs;

    private String introduction;
    private List<TeamMember> team;

}
