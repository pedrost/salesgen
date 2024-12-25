package co.boldtec.salesgen.services;

import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFShape;
import org.apache.poi.xslf.usermodel.XSLFTextShape;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


public class PptxService implements IPowerPointService {

    private XMLSlideShow presentation;

    // Load a presentation file
    public PptxService GetPresentation(String filePath) {
        try (FileInputStream fis = new FileInputStream(filePath)) {
            this.presentation = new XMLSlideShow(fis);
        } catch (IOException e) {
            throw new RuntimeException("Error loading the PowerPoint file", e);
        }
        return this;
    }

    // Replace text in a slide
    public PptxService ReplaceText(String placeholder, String replacementText) {
        for (var slide : presentation.getSlides()) {
            for (XSLFShape shape : slide.getShapes()) {
                if (shape instanceof XSLFTextShape textShape) {
                    String text = textShape.getText();
                    if (text != null && text.contains(placeholder)) {
                        textShape.setText(text.replace(placeholder, replacementText));
                    }
                }
            }
        }
        return this;
    }

    // Save the updated presentation
    public void SaveAs(String outputFilePath) {
        try (FileOutputStream fos = new FileOutputStream(outputFilePath)) {
            presentation.write(fos);
        } catch (IOException e) {
            throw new RuntimeException("Error saving the PowerPoint file", e);
        }
    }


}
