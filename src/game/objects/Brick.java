package game.objects;

import java.awt.Color;
import java.awt.Rectangle;

import game.visualization.Colors;

public class Brick extends Rectangle {

    private Color color;
    private boolean broken;

    public Brick(int height, int width, boolean broken) {
        super(width, height);
        this.broken = broken;
    }
    
    public Color getColor() {
        return color;
    }
    
    public void setColor(Color color) {
        this.color = color;
    }

    public boolean isBroken() {
        return broken;
    }

    public void setBroken(boolean broken) {
        this.broken = broken;
    }

    public void hit() {
        if (color == Colors.FIRST_LINE_COLOR) {
            color = Colors.SECOND_LINE_COLOR;
        } else if (color == Colors.SECOND_LINE_COLOR) {
            color = Colors.THIRD_LINE_COLOR;
        } else if (color == Colors.THIRD_LINE_COLOR) {
            color = Colors.FOURTH_LINE_COLOR;
        } else {
            broken = true;
        }
    }

}
