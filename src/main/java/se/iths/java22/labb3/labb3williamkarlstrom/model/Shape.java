package se.iths.java22.labb3.labb3williamkarlstrom.model;

import javafx.beans.Observable;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


public abstract class Shape {

    private final ObjectProperty<Color> color = new SimpleObjectProperty<>();
    private final ObjectProperty<Color> borderColor = new SimpleObjectProperty<>();
    private final DoubleProperty xPosition = new SimpleDoubleProperty();
    private final DoubleProperty yPosition = new SimpleDoubleProperty();
    private final DoubleProperty size = new SimpleDoubleProperty();

    public double getSize() {
        return size.get();
    }

    public DoubleProperty sizeProperty() {
        return size;
    }

    public void setSize(double size) {
        this.size.set(size);
    }

    public Shape(Color color, double xPosition, double yPosition, double size) {
        setColor(color);
        setxPosition(xPosition);
        setyPosition(yPosition);
        setSize(size);
        setBorderColor(Color.TRANSPARENT);
    }

    // public Shape(Shape shape){
//        setColor(Color.TRANSPARENT);
//        setSize(shape.getSize());
//        setxPosition(shape.getxPosition());
//        setyPosition(shape.getyPosition());
    //   }

    public Color getColor() {
        return color.get();
    }

    public ObjectProperty<Color> colorProperty() {
        return color;
    }

    public void setColor(Color color) {
        this.color.set(color);
    }

    public double getxPosition() {
        return xPosition.get();
    }

    public DoubleProperty xPositionProperty() {
        return xPosition;
    }

    public void setxPosition(double xPosition) {
        this.xPosition.set(xPosition);
    }

    public double getyPosition() {
        return yPosition.get();
    }

    public DoubleProperty yPositionProperty() {
        return yPosition;
    }

    public void setyPosition(double yPosition) {
        this.yPosition.set(yPosition);
    }

    public void draw(GraphicsContext context) {
    }

    public Color getBorderColor() {
        return borderColor.get();
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor.set(borderColor);
    }

    public ObjectProperty<Color> borderColorProperty() {
        return borderColor;
    }

//    public void setBorderColorOnSelected(){
//        setBorderColor(Color.BLACK);
//    }
//    public void setBorderColorOnDeselected() {
//        setBorderColor(Color.TRANSPARENT);
//    }

    public abstract boolean checkIfInsideShape(double x, double y);


}