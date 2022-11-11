package se.iths.java22.labb3.labb3williamkarlstrom.model;

import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.iths.java22.labb3.labb3williamkarlstrom.shapes.Circle;
import se.iths.java22.labb3.labb3williamkarlstrom.shapes.Rectangle;


class ModelTest {
    Model model = new Model();

    @Test
    void testCheckIfInsideWhenMousePositionsAreInsideShape(){
        model.shapes.add(new Rectangle(Color.BLUE, 50, 50, 50));
        model.shapes.add(new Circle(Color.BLUE, 50, 50,25));

        double insideXMousePos = 50.0;
        double insideYMousePos = 50.0;

        var insideExpected = true;
        var insideActual = false;

        for (var shape : model.shapes) {
            if (shape.checkIfInsideShape(insideXMousePos, insideYMousePos)) {
                insideActual = true;
            }
        }
        assertEquals(insideExpected, insideActual, "Inside positions: should return true");

}

    @Test
    void testCheckIfInsideWhenMousePositionsAreOutsideShape(){
        model.shapes.add(new Rectangle(Color.BLUE, 50, 50, 50));
        model.shapes.add(new Circle(Color.BLUE, 50, 50,25));

        double outsideXMousePos = 10;
        double outsideYMousePos = 10;

        var outsideExpected = false;
        var outsideActual = false;

        for (var shape : model.shapes) {
            if (shape.checkIfInsideShape(outsideXMousePos, outsideYMousePos)) {
                outsideActual = true;
            }
        }

        assertEquals(outsideExpected, outsideActual, "Outside positions: should return false");
    }
}