package game.objects;

import java.awt.Color;
import java.awt.Rectangle;

public class Ball extends Rectangle {

    private final Color color = Color.WHITE;
    private final int diameter = 20;
    private int dirX;
    private int dirY;
    
    public Ball() {
        super(20, 20);
        init();
    }

    public void init() {
        this.dirX = -1;
        this.dirY = -2;
        x = 120;
        y = 350;
    }

    public int getDiameter() {
        return diameter;
    }

    public int getDirX() {
        return dirX;
    }

    public void setDirX(int xDir) {
        this.dirX = xDir;
    }

    public int getDirY() {
        return dirY;
    }

    public void setDirY(int yDir) {
        this.dirY = yDir;
    }

    public void inverseDirX() {
        dirX = -dirX;
    }
    
    public void inverseDirY() {
        dirY = -dirY;
    }

    public Color getColor() {
        return color;
    }

}
