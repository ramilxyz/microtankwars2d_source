package studio.mashkarik.microtankwars2d.actor;

import com.badlogic.gdx.graphics.Color;

public class Vector2Color {
    public Color color;
    int x;
    int y;

    Vector2Color(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
