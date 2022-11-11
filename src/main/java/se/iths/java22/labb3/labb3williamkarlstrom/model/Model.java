package se.iths.java22.labb3.labb3williamkarlstrom.model;

import javafx.beans.Observable;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import se.iths.java22.labb3.labb3williamkarlstrom.shapes.Circle;
import se.iths.java22.labb3.labb3williamkarlstrom.shapes.Rectangle;
import se.iths.java22.labb3.labb3williamkarlstrom.shapes.Shape;
import se.iths.java22.labb3.labb3williamkarlstrom.shapes.Square;

import java.util.ArrayDeque;
import java.util.Deque;

public class Model {

    private final ObjectProperty<Color> color;
    private final ObjectProperty<Integer> size;
    public ObservableList<Shape> shapes;
    public ObservableList<Shape> selectedShapes;
    public Deque<ObservableList<Shape>> undoShapeDeque;


    public ObservableList<Shape> getShapes() {
        return shapes;
    }

    public void setBorderColorOnSelected(Shape shape) {
        shape.setBorderColor(Color.BLACK);
    }

    public void setBorderColorOnDeselected(Shape shape) {
        shape.setBorderColor(Color.TRANSPARENT);
    }

    public Model() {
        this.shapes = FXCollections.observableArrayList(
                shape -> new Observable[]{
                        shape.xPositionProperty(),
                        shape.yPositionProperty(),
                        shape.colorProperty(),
                        shape.sizeProperty(),
                        shape.borderColorProperty()
                }
        );

        this.color = new SimpleObjectProperty<>(Color.RED);
        this.size = new SimpleObjectProperty<>(50);

        this.undoShapeDeque = new ArrayDeque<>();
        this.selectedShapes = FXCollections.observableArrayList();

    }

    public ObjectProperty<Color> colorProperty() {
        return color;
    }

    public ObjectProperty<Integer> sizeProperty() {
        return size;
    }

    public Color getColor() {
        return color.get();
    }

    public Integer getSize() {
        return size.get();
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public void deleteSelectedShapes() {
        for (var shape : selectedShapes) {
            shapes.remove(shape);
        }

    }

    public void changeColorOnShapes() {
        for (var shape : selectedShapes) {
            shape.setColor(getColor());
        }
    }

    public void changeSizeOnShapes() {
        for (var shape : selectedShapes) {
            shape.setSize(getSize());
        }
    }

    public void addCircleToObservableList(double xPosition, double yPosition) {
        shapes.add(new Circle(getColor(), xPosition, yPosition, getSize()));
    }

    public void addRectangleToObservableList(double xPosition, double yPosition) {
        shapes.add(new Rectangle(getColor(), xPosition, yPosition, getSize()));
    }

    public void addSquareToObservableList(double xPosition, double yPosition) {
        shapes.add(new Square(getColor(), xPosition, yPosition, getSize()));
    }

    public void undo() {
        if (undoShapeDeque.isEmpty())
            return;

        shapes.clear();
        shapes.addAll(undoShapeDeque.removeLast());

    }

    public ObservableList<Shape> getTemporaryList() {
        ObservableList<Shape> temporaryList = FXCollections.observableArrayList();

        for (var shape : shapes) {
            temporaryList.add(shape.copyShape());
        }

        return temporaryList;
    }

    public void addToUndoDeque() {
        ObservableList<Shape> temporaryList = getTemporaryList();
        undoShapeDeque.addLast(temporaryList);
    }

    public void drawShapes(GraphicsContext context) {
        for (var shape : shapes) {
            shape.draw(context);
        }
    }
}