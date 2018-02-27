import java.util.ArrayList;
import java.awt.*;
	
public class Ship {
	private int pointValue;
	private int x;
	private int y;
	private int xSpeed;
	private int ySpeed;
	private int width;
	private int height;
	private Color color;
	
	private ArrayList<Bullet> bullets;

    public Ship(int x, int y) {
        this.x=x;
        this.y=y;
    }

	public int getXSpeed() {
		return xSpeed;
	}

	public void setXSpeed(int xSpeed) {
		this.xSpeed = xSpeed;
	}

	public int getYSpeed() {
		return ySpeed;
	}

	public void setYSpeed(int ySpeed) {
		this.ySpeed = ySpeed;
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

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
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

	public void setPoints(int x) {
		pointValue = x;
	}
	
	public int getPoints() {
		return pointValue;	
	}

}
