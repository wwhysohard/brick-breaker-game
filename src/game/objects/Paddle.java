package game.objects;

import java.awt.Color;
import java.awt.Rectangle;

public class Paddle extends Rectangle {

    private final Color color = Color.WHITE;

    public Paddle() {
        super(310, 550, 100, 8);
    }

    public Color getColor() {
        return color;
    }

    public void moveLeft() {
        if (x <= 10) {
            x = 10;
        } else {
            x -= 20;
        }
    }

    public void moveRight() {
        if (x >= 600) {
            x = 600;
        } else {
            x += 20;
        }
    }

    public Rectangle leftPart() {
        return new Rectangle((int)x, (int)y, 30, (int)height);
    }

    public Rectangle middlePart() {
        return new Rectangle((int)x + 70, (int)y, 30, (int)height);
    }

    public Rectangle rightPart() {
        return new Rectangle((int)x + 30, (int)y, 40, (int)height);
    }

}
