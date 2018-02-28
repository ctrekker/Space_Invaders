import java.awt.*;

public class Star extends Point {
    private Color color;
    private int radius;
    public Star(int x, int y, int radius, Color color) {
        super(x, y);

        this.radius=radius;
        this.color=color;
    }

    public void draw(Graphics2D g2) {
        g2.setColor(color);
        g2.fillArc(x-radius, y-radius, radius*2, radius*2, 0, 360);
    }

    public Color getColor() {
        return color;
    }
    public void setColor(Color color) {
        this.color = color;
    }
    public int getRadius() {
        return radius;
    }
    public void setRadius(int radius) {
        this.radius=radius;
    }
}
