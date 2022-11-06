package se.iths.java22.labb3.labb3williamkarlstrom.model;

import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import se.iths.java22.labb3.labb3williamkarlstrom.shapes.Rectangle;

class ModelTest {
    Model model = new Model();

    @Test
    void addNewRectangleToShapesObservableList(){
        model.shapes.add(new Rectangle(Color.BLUE, 25, 25, 50));

        var expected = 1;
        var contains = model.shapes.size();

        assertEquals(expected, contains);

    }

    @Test
    void addShapesFromShapesObservableListToUndoDeque() {

        model.shapes.add(new Rectangle(Color.BLUE, 25, 25, 50));
        model.addToUndoDeque();

        var expected = 1;
        var contains = model.undoShapeDeque.size();

        assertEquals(expected, contains);

    }






}