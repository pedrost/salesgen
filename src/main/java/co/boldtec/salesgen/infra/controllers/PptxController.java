package co.boldtec.salesgen.infra.controllers;


import co.boldtec.salesgen.services.IPowerPointService;
import co.boldtec.salesgen.services.PptxService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PptxController {
    @PostMapping(value = "/api/v1/presentation")
    public String ping() {
        IPowerPointService service = new PptxService()
                .GetPresentation("src/main/resources/pptx/Style1.pptx")
                .ReplaceText("{{company_name}}", "My Company");

        service.SaveAs("src/main/resources/pptx/Style1_Updated.pptx");

        System.out.println("PowerPoint updated successfully!");
        return "pong";
    }
}
