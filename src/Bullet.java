import java.awt.Color;


public class Bullet {
	private int x;
	private int y;
	private int width;
	private int height;
    private Vector2D direction;
	private Color color;
	
	public Bullet(int x, int y) {
		this.x=x;
		this.y=y;
		this.direction=new Vector2D();
	}

    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public Vector2D getDirection() {
		return direction;
	}
	public void setDirection(Vector2D direction) {
		this.direction = direction;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
}
