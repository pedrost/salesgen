package co.boldtec.salesgen.services;

public interface IPowerPointService  {
    IPowerPointService GetPresentation(String filePath);
    IPowerPointService ReplaceText(String placeholder, String replacementText);
    void SaveAs(String outputFilePath);
}
