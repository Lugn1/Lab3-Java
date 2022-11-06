package se.iths.java22.labb3.labb3williamkarlstrom.shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Rectangle extends Shape {


    public Rectangle(Color color, double xPosition, double yPosition, double size) {
        super(color, xPosition, yPosition, size);
    }

    public Rectangle(Shape shape){
        super(shape);
    }

    public void draw(GraphicsContext context){
        context.setFill(getBorderColor());
        context.fillRect(getxPosition() - getSize()/2,
                getyPosition() - getSize()/2,getSize() * 2, getSize());

        context.setFill(this.getColor());
        context.fillRect(getxPosition() - getSize()/2 + 2,
                getyPosition() - getSize()/2 + 2,getSize() * 2 - 4, getSize() - 4);
    }


    @Override
    public boolean checkIfInsideShape(double xMousePos, double yMousePos) {
        double xPosition = getxPosition() - getSize()/2;
        double yPosition = getyPosition() - getSize()/2;

        return xMousePos >= xPosition && xMousePos <= xPosition + (getSize() * 2) &&
                yMousePos >= yPosition &&
                yMousePos <= yPosition + getSize();
    }

    @Override
    public Shape copyShape(){
        return new Rectangle(this);
    }
}
