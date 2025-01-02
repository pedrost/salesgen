package co.boldtec.salesgen.infra.controllers;

import co.boldtec.salesgen.config.DotenvConfig;
import co.boldtec.salesgen.domain.interfaces.IOpenAIClient;
import co.boldtec.salesgen.domain.interfaces.IStartupPptxRequest;
import co.boldtec.salesgen.domain.requests.StartupIdeaRequest;
import co.boldtec.salesgen.infra.http.OpenAIClientImpl;
import co.boldtec.salesgen.services.IConfigService;
import co.boldtec.salesgen.services.IPowerPointService;
import co.boldtec.salesgen.services.PptxService;
import co.boldtec.salesgen.usecases.GenerateStartupStructFromTextUseCase;
import co.boldtec.salesgen.usecases.StartupPptxUsecase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.validation.annotation.Validated;

@RestController
public class PptxController {

    /**
     * Endpoint to generate a PowerPoint presentation from a startup idea.
     *
     * @param request The JSON body containing the startup idea.
     * @return A message indicating the success of the PowerPoint creation.
     */
    @PostMapping(value = "/api/v1/presentation")
    @Operation(
            summary = "Generate PowerPoint from startup idea",
            description = "Generates a PowerPoint presentation based on the provided startup idea in JSON format.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "JSON containing the startup idea (required)",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = StartupIdeaRequest.class))
            ),
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "200",
                            description = "PowerPoint created successfully",
                            content = @Content(mediaType = "application/json")
                    )
            }
    )
    public String generatePresentation(
            @Parameter(description = "The JSON body containing the startup idea (required)")
            @Validated @RequestBody StartupIdeaRequest request) {

        String startupIdea = request.getData();

        IConfigService cfgService = new DotenvConfig();
        IOpenAIClient openAIClient = new OpenAIClientImpl(cfgService);

        GenerateStartupStructFromTextUseCase generator = new GenerateStartupStructFromTextUseCase(openAIClient);
        IStartupPptxRequest pptxData = generator.generatePptxDataFromText(startupIdea);

        IPowerPointService serviceIpm = new PptxService();
        StartupPptxUsecase startupPptxUsecase = new StartupPptxUsecase(serviceIpm);
        startupPptxUsecase.createStartupPresentation(pptxData);

        return "PowerPoint created successfully!";
    }
}
