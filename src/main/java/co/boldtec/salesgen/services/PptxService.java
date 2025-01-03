package co.boldtec.salesgen.services;

import co.boldtec.salesgen.domain.Graph;
import org.apache.poi.sl.usermodel.PaintStyle;
import org.apache.poi.xddf.usermodel.XDDFColor;
import org.apache.poi.xddf.usermodel.XDDFShapeProperties;
import org.apache.poi.xddf.usermodel.XDDFSolidFillProperties;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFShape;
import org.apache.poi.xslf.usermodel.XSLFTextShape;
import org.apache.poi.xslf.usermodel.*;
import java.util.List;
import org.apache.poi.util.Units;
import org.apache.poi.xddf.usermodel.chart.*;

import java.awt.*;
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

    public PptxService AddGraphsToPresentation(List<Graph> graphs) {
        try {
            // Ensure slide exists
            XSLFSlide slide = presentation.getSlides().get(3);

            // Create chart and link it to the slide
            XSLFChart chart = presentation.createChart(slide);

            // Set the chart background color to white
            if (chart.getCTChartSpace().getSpPr() == null) {
                chart.getCTChartSpace().addNewSpPr(); // Create shape properties if absent
            }
            chart.getCTChartSpace().getSpPr().addNewSolidFill().addNewSrgbClr().setVal(new byte[]{(byte) 255, (byte) 255, (byte) 255});

            // Configure axes
            XDDFCategoryAxis bottomAxis = chart.createCategoryAxis(AxisPosition.BOTTOM);
            bottomAxis.setTitle("Year");
            XDDFValueAxis leftAxis = chart.createValueAxis(AxisPosition.LEFT);
            leftAxis.setTitle("Value");
            leftAxis.setCrosses(AxisCrosses.AUTO_ZERO);
            leftAxis.setCrossBetween(AxisCrossBetween.BETWEEN);

            // Prepare data container
            String[] categories = null; // Categories from the first graph
            XDDFChartData data = chart.createData(ChartTypes.LINE, bottomAxis, leftAxis);

            // Add data series for each graph
            for (Graph graph : graphs) {
                if (categories == null) {
                    categories = graph.getData().keySet().stream()
                            .map(String::valueOf)
                            .toArray(String[]::new);
                }

                Double[] values = graph.getData().values().stream()
                        .map(Double::valueOf)
                        .toArray(Double[]::new);

                // Ensure data integrity
                if (categories.length != values.length) {
//                    throw new IllegalArgumentException("Mismatch between categories and values.");
                }

                // Create data sources
                XDDFCategoryDataSource categoryData = XDDFDataSourcesFactory.fromArray(categories);
                XDDFNumericalDataSource<Double> valueData = XDDFDataSourcesFactory.fromArray(values);

                // Add series
                XDDFChartData.Series series = data.addSeries(categoryData, valueData);
                series.setTitle(graph.getTitle(), null);

                // Optional: Set series color
                XDDFSolidFillProperties fill = new XDDFSolidFillProperties(XDDFColor.from(255, 0, 0));
                series.setFillProperties(fill);
            }

            // Plot data
            chart.plot(data);

            // Set chart dimensions and add to slide
            java.awt.geom.Rectangle2D chartDimensions = new java.awt.geom.Rectangle2D.Double(
                    100 * Units.EMU_PER_POINT,
                    100 * Units.EMU_PER_POINT,
                    400 * Units.EMU_PER_POINT,
                    300 * Units.EMU_PER_POINT);

            slide.addChart(chart, chartDimensions);

            // Save presentation
            try (FileOutputStream out = new FileOutputStream("output.pptx")) {
                presentation.write(out);
            }

            System.out.println("Graph added to slide 3 successfully!");

        } catch (Exception e) {
            e.printStackTrace();
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
