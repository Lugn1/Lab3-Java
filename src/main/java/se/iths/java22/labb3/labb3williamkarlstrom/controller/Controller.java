package se.iths.java22.labb3.labb3williamkarlstrom.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import se.iths.java22.labb3.labb3williamkarlstrom.model.*;
import se.iths.java22.labb3.labb3williamkarlstrom.shapes.Shape;
import se.iths.java22.labb3.labb3williamkarlstrom.svg.SVGWriter;

import java.util.ArrayList;


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
    public Button changeSizeButton;


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
        model.drawShapes(context);
    }

    public void canvasChanged(){
        //   drawShapes(); TODO onListChanged-observer
    }

    public void canvasClicked(MouseEvent mouseEvent) {

        drawOnClick(mouseEvent);
        renderCanvas();
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
            model.addCircleToObservableList(x, y);
        } else if (squareButton.isFocused()) {
            model.addSquareToObservableList(x, y);
        } else if (rectangleButton.isFocused()) {
            model.addRectangleToObservableList(x, y);
        }
    }

    private void selectShapes(Shape shape) {

        if (model.selectedShapes.contains(shape)) {
            model.setBorderColorOnDeselected(shape);
            model.selectedShapes.remove(shape);
        } else {
            model.setBorderColorOnSelected(shape);
            model.selectedShapes.add(shape);

        }
    }

    public void undoLast() {
        model.undo();
        renderCanvas();
        drawShapes();
    }

    public void changeColorOnSelectedShapes() {
        model.addToUndoDeque();
        model.changeColorOnShapes();
        renderCanvas();
        drawShapes();
    }

    public void changeSizeOnSelectedShapes() {
        model.addToUndoDeque();
        model.changeSizeOnShapes();
        renderCanvas();
        drawShapes();
    }

    public void deleteMarkedShapes() {
        model.addToUndoDeque();
        model.deleteSelectedShapes();
        renderCanvas();
        drawShapes();
    }

    public void saveFile(ActionEvent actionEvent) {
    }

    public void saveToFile(MouseEvent mouseEvent) {
        SVGWriter svgFile = new SVGWriter();
        svgFile.saveToFile(model);


    }
}