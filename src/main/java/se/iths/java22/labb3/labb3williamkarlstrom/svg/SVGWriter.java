package se.iths.java22.labb3.labb3williamkarlstrom.svg;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import se.iths.java22.labb3.labb3williamkarlstrom.model.Model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class SVGWriter {

    FileChooser fileChooser = new FileChooser();
    public void saveToFile(Model model){

        newFileChooser();

        Path path = Path.of(fileChooser.showSaveDialog(new Stage()).toURI());
        List<String> svgStrings = new ArrayList<>();

        buildSvgString(model, svgStrings);

        try {
            Files.write(path, svgStrings);
        } catch (IOException e){
            e.printStackTrace();
        }
    }


    private void newFileChooser() {
        fileChooser.setTitle("Save SVG File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("SVG","*.svg"));
    }

    private void buildSvgString(Model model, List<String> svgStrings) {

        svgStrings.add(getHeaderOfSvgString());
        model.getShapes().forEach(shape -> svgStrings.add(shape.writeSVG()));
        svgStrings.add(svgEnding());

    }

    private String getHeaderOfSvgString() {
        return String.join(" ",
                "<svg",
                "xmlns=\"http://www.w3.org/2000/svg\"",
                "version=\"1.1\"",
                "width=\"667.0\"",
                "height=\"715.0\">"
        );
    }

    private String svgEnding() {
        return "</svg>";
    }

}
