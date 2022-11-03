package se.iths.java22.labb3.labb3williamkarlstrom.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Circle extends Shape {


    public Circle(Color color, double xPosition, double yPosition, double size) {
        super(color, xPosition, yPosition, size);
    }

//    public Circle(Shape shape) {
//        super(shape);
//    }


    public void draw(GraphicsContext context){
        context.setFill(getBorderColor());
        context.fillOval(getxPosition() - getSize()/2,
                getyPosition() - getSize()/2,getSize(),getSize());

        context.setFill(this.getColor());
        context.fillOval(getxPosition() - getSize()/2 + 2,
                getyPosition() - getSize()/2 + 2,getSize() - 4,getSize() - 4);
    }


    @Override
    public boolean checkIfInsideShape(double xMousePos, double yMousePos) {
        double xPosition = getxPosition() - getSize()/2;
        double yPosition = getyPosition() - getSize()/2;

        return xMousePos >= xPosition && xMousePos <= xPosition + getSize() &&
                yMousePos >= yPosition &&
                yMousePos <= yPosition + getSize();
    }
}
