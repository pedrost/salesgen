package co.boldtec.salesgen.services;

import co.boldtec.salesgen.domain.Graph;

import java.util.List;

public interface IPowerPointService  {
    IPowerPointService GetPresentation(String filePath);
    IPowerPointService ReplaceText(String placeholder, String replacementText);
    IPowerPointService AddGraphsToPresentation(List<Graph> graphs);
    void SaveAs(String outputFilePath);
}
