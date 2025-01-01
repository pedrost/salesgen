package co.boldtec.salesgen.services;

import org.apache.poi.sl.usermodel.PaintStyle;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFShape;
import org.apache.poi.xslf.usermodel.XSLFTextShape;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


public class PptxService implements IPowerPointService {

    private XMLSlideShow presentation;

    public PptxService GetPresentation(String filePath) {
        try (FileInputStream fis = new FileInputStream(filePath)) {
            this.presentation = new XMLSlideShow(fis);
        } catch (IOException e) {
            throw new RuntimeException("Error loading the PowerPoint file", e);
        }
        return this;
    }

    public PptxService ReplaceText(String placeholder, String replacementText) {
        for (var slide : presentation.getSlides()) {
            for (XSLFShape shape : slide.getShapes()) {
                if (shape instanceof XSLFTextShape textShape) {
                    String text = textShape.getText();
                    if (text != null && text.contains(placeholder)) {
                        for (var paragraph : textShape.getTextParagraphs()) {
                            for (var run : paragraph.getTextRuns()) {
                                if (run.getRawText().contains(placeholder)) {
                                    String originalFontFamily = run.getFontFamily();
                                    double originalFontSize = run.getFontSize();
                                    boolean isBold = run.isBold();
                                    boolean isItalic = run.isItalic();
                                    PaintStyle originalColor = run.getFontColor();

                                    String newText = run.getRawText().replace(placeholder, replacementText);
                                    run.setText(newText);

                                    run.setFontFamily(originalFontFamily);
                                    run.setFontSize(originalFontSize);
                                    run.setBold(isBold);
                                    run.setItalic(isItalic);
                                    run.setFontColor(originalColor);
                                }
                            }
                        }
                    }
                }
            }
        }
        return this;
    }

    public void SaveAs(String outputFilePath) {
        try (FileOutputStream fos = new FileOutputStream(outputFilePath)) {
            presentation.write(fos);
        } catch (IOException e) {
            throw new RuntimeException("Error saving the PowerPoint file", e);
        }
    }


}
