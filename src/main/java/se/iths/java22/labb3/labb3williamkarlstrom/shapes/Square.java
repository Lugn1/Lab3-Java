package se.iths.java22.labb3.labb3williamkarlstrom.shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Square extends Shape {

    public Square(Color color, double xPosition, double yPosition, double size) {
        super(color, xPosition, yPosition, size);
    }

    public Square(Shape shape) {
        super(shape);
    }


    public void draw(GraphicsContext context){
        context.setFill(getBorderColor());
        context.fillRect(getXPosition() - getSize()/2,
                getYPosition() - getSize()/2,getSize(),getSize());

        context.setFill(this.getColor());
        context.fillRect(getXPosition() - getSize()/2 + 2,
                getYPosition()- getSize()/2 + 2,getSize() - 4,getSize() - 4);
    }


    @Override
    public boolean checkIfInsideShape(double xMousePos, double yMousePos) {
        double xPosition = getXPosition() - getSize()/2;
        double yPosition = getYPosition() - getSize()/2;

        return xMousePos >= xPosition && xMousePos <= xPosition + (getSize() * 2) &&
                yMousePos >= yPosition &&
                yMousePos <= yPosition + getSize();
    }

    @Override
    public Shape copyShape() {
        return new Circle(this);
    }


    @Override
    public String writeSVG() {
        String svgColorCode = "#" + getColor().toString().substring(2,10);
        return "<rect fill=\"" + svgColorCode + "\"" +
                " width=\"" + getSize() + "\"" +
                " height=\"" + getSize() + "\"" +
                " x=\"" + (getXPosition() - (getSize()/2))  + "\"" +
                " y=\"" + (getYPosition() - (getSize()/2))  + "\" />";

    }
}


