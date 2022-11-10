package se.iths.java22.labb3.labb3williamkarlstrom.model;

import javafx.collections.ObservableList;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import se.iths.java22.labb3.labb3williamkarlstrom.shapes.Circle;
import se.iths.java22.labb3.labb3williamkarlstrom.shapes.Rectangle;
import se.iths.java22.labb3.labb3williamkarlstrom.shapes.Shape;
import se.iths.java22.labb3.labb3williamkarlstrom.shapes.Square;

class ModelTest {
    Model model = new Model();

    @Test
    void addNewRectangleToShapesObservableList(){

        model.shapes.add(new Rectangle(Color.BLUE, 25, 25, 50));

        var expected = 1;
        var actual = model.shapes.size();

        assertEquals(expected, actual);

    }

    @Test
    void addTheNewShapesFromShapesObservableListToTemporaryListInsideUndoDeque() {

        model.shapes.add(new Rectangle(Color.BLUE, 25, 25, 50));
        model.shapes.add(new Circle(Color.BLUE, 25, 25, 50));
        model.shapes.add(new Square(Color.BLUE, 25, 25, 50));

        var expected = 3;
        var actual = model.getTemporaryList().size();

        assertEquals(expected, actual);

    }

    @Test
    void passShapesFromUndoDequeToShapesObservableList(){

        model.shapes.add(new Rectangle(Color.BLUE, 25, 25, 50));
        model.shapes.add(new Circle(Color.BLUE, 25, 25, 50));
        model.shapes.add(new Square(Color.BLUE, 25, 25, 50));

        ObservableList<Shape> temporaryList = model.getTemporaryList();
        model.undoShapeDeque.addLast(temporaryList);

        model.shapes.clear();
        model.shapes.addAll(temporaryList);

        var expected = 3;
        var actual = model.shapes.size();

        assertEquals(expected, actual);

    }
}