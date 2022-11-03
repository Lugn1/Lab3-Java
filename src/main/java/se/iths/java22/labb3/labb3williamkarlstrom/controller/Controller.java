package se.iths.java22.labb3.labb3williamkarlstrom.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import se.iths.java22.labb3.labb3williamkarlstrom.model.*;


public class Controller {

    public Button circleButton;
    public Button squareButton;
    public Button rectangleButton;
    public Button deleteButton;
    public Button undoButton;
    public Button changeColorButton;

    public Canvas canvas;

    public CheckBox checkBox;

    public ColorPicker colorPicker;

    public Spinner<Integer> spinner;

    public GraphicsContext context;

    public Model model;

    ObservableList<Shape> shapeObservableList = FXCollections.observableArrayList();

    @FXML
    ListView<Shape> listViewTest = new ListView<>(shapeObservableList);

    public void initialize() {
        model = new Model();

        SpinnerValueFactory<Integer> spinnerValue = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 300);
        spinnerValue.setValue(25);
        spinner.setValueFactory(spinnerValue);

        colorPicker.valueProperty().bindBidirectional(model.colorProperty());
        spinnerValue.valueProperty().bindBidirectional(model.sizeProperty());


        context = canvas.getGraphicsContext2D();
        canvas.setFocusTraversable(true);
        renderCanvas();

        listViewTest.setItems(model.shapes);
    }


    private void drawShapes() {
        for (var shape : model.shapes) {
            shape.draw(context);
        }
    }

    public void canvasClicked(MouseEvent mouseEvent) {
        drawOnClick(mouseEvent);
        listViewTest.getSelectionModel();
        drawShapes();
    }

    private void renderCanvas() {
        context.setFill(Color.WHITE);
        context.fillRect(0, 0, 667, 715);
    }

    public void drawOnClick(MouseEvent mouseEvent) {

        double x = mouseEvent.getX();
        double y = mouseEvent.getY();

        if (checkBox.isSelected()) {
            for (var shape : model.shapes) {
                if (shape.checkIfInsideShape(x, y)) {
                    selectShapes(shape);
                }
            }
        } else AddShapeType(x, y);
    }

    private void AddShapeType(Double x, double y) {

        if (circleButton.isFocused()) {
            addCircleToObservableList(x, y);
        } else if (squareButton.isFocused()) {
            addSquareToObservableList(x, y);
        } else if (rectangleButton.isFocused()) {
            addRectangleToObservableList(x, y);
        }
    }

    private void selectShapes(Shape shape) {

        if (model.selectedShapes.contains(shape)) {
            model.setBorderColorOnDeselected(shape);
            model.selectedShapes.remove(shape);
            // TODO model.unselectShape(shape);
        } else {
            model.setBorderColorOnSelected(shape);
            model.selectedShapes.add(shape);
            // TODO  model.selectShape();
        }
    }

    // TODO Skicka över data metoder till model
    private void addCircleToObservableList(double xPosition, double yPosition) {
        model.shapes.add(new Circle(model.getColor(), xPosition, yPosition, model.getSize()));
        shapeObservableList.add(new Circle(model.getColor(), xPosition, yPosition, model.getSize()));
    }

    private void addRectangleToObservableList(double xPosition, double yPosition) {
        model.shapes.add(new Rectangle(model.getColor(), xPosition, yPosition, model.getSize()));
        shapeObservableList.add(new Rectangle(model.getColor(), xPosition, yPosition, model.getSize()));
    }

    private void addSquareToObservableList(double xPosition, double yPosition) {
        model.shapes.add(new Square(model.getColor(), xPosition, yPosition, model.getSize()));
        shapeObservableList.add(new Square(model.getColor(), xPosition, yPosition, model.getSize()));
    }

    public void deleteMarkedShapes() {
        model.undoShapeDeque.addAll(model.selectedShapes);
        model.deleteSelectedShapes();
        renderCanvas();
        drawShapes();
    }

    public void undoLast() {
        model.shapes.addAll(model.undoShapeDeque.removeLast());
        renderCanvas();
        drawShapes();
    }

    public void changeColorOnSelectedShapes() {
        model.changeColorOnShapes();
        renderCanvas();
        drawShapes();
    }

    public void changeSizeOnSelectedShapes() {
        model.changeSizeOnShapes();
        renderCanvas();
        drawShapes();
    }
}