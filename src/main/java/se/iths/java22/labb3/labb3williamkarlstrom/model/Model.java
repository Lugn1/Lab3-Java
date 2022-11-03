package se.iths.java22.labb3.labb3williamkarlstrom.model;

import javafx.beans.Observable;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.paint.Color;
import se.iths.java22.labb3.labb3williamkarlstrom.controller.Controller;
import se.iths.java22.labb3.labb3williamkarlstrom.model.Shape;
import java.util.ArrayDeque;
import java.util.Deque;

public class Model {


    private final ObjectProperty<Color> color;
    private final ObjectProperty<Color> borderColor;
    private final ObjectProperty<Integer> size;

    public ObservableList<Shape> shapes;
    public ObservableList<Shape> selectedShapes;

    public Deque<Shape> undoShapeDeque;

    public ObservableList<Shape> getSelectedShapes() {
        return selectedShapes;
    }

    public void setSelectedShapes(ObservableList<Shape> selectedShapes) {
        this.selectedShapes = selectedShapes;
    }

    public ObservableList<Shape> getShapes() {
        return shapes;
    }

    public void setColor(Color color) {
        this.color.set(color);
    }

    public Color getBorderColor() {
        return Color.TRANSPARENT;
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor.set(borderColor);
    }

    public void setBorderColorOnSelected(Shape shape){
        shape.setBorderColor(Color.BLACK);
    }
    public void setBorderColorOnDeselected(Shape shape) {
        shape.setBorderColor(Color.WHITE);
    }

    public ObjectProperty<Color> borderColorProperty() {
        return borderColor;
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

        this.borderColor = new SimpleObjectProperty<>();

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

    public void deleteSelectedShapes() {
        for (var shape : selectedShapes){
            shapes.remove(shape);
        }

    }

    @Override
    public String toString() {
        return super.toString();
    }

    public void changeColorOnShapes() {
        for (var shape : selectedShapes){
            shape.setColor(getColor());
        }
    }

    public void changeSizeOnShapes() {
        for (var shape : selectedShapes){
            shape.setSize(getSize());
        }
    }

}