package StudentDatabase;

import javafx.scene.paint.Color;

public enum MyColor {
    HOTPINK(255, 0, 255), SKYBLUE(204, 229, 255), YELLOW(255, 255, 153), BLACK(204, 255, 204), RED(229, 204, 255), WHITE(255, 255, 255);

    private int r;
    private int g;
    private int b;

    private MyColor(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public Color getColor() {
        return new Color(r / 255.0, g / 255.0, b / 255.0, 1.0);
    }


}
