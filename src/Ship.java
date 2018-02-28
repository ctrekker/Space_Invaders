import java.util.ArrayList;
import java.awt.*;
	
public class Ship {
    private static final int DEFAULT_X=0;
    private static final int DEFAULT_Y=0;
    private static final int DEFAULT_WIDTH=10;
    private static final int DEFAULT_HEIGHT=10;
    private static final int DEFAULT_VARIATION=0;
    private static final int DEFAULT_VALUE=1;
    private static final Vector2D DEFAULT_DIRECTION=new Vector2D();
    private static final Color DEFAULT_COLOR=Color.WHITE;

	private int x;
	private int y;
	private int width;
	private int height;
	private int variation;
	private int value;
    private Vector2D direction;
	private ArrayList<Bullet> bullets=new ArrayList<>();

	public Ship() {
	    this(DEFAULT_X, DEFAULT_Y, DEFAULT_WIDTH, DEFAULT_HEIGHT, DEFAULT_VARIATION, DEFAULT_VALUE, DEFAULT_DIRECTION);
    }
    public Ship(int x, int y) {
        this(x, y, DEFAULT_WIDTH, DEFAULT_HEIGHT, DEFAULT_VARIATION, DEFAULT_VALUE, DEFAULT_DIRECTION);
    }
    public Ship(int x, int y, int width, int height) {
        this(x, y, width, height, DEFAULT_VARIATION, DEFAULT_VALUE, DEFAULT_DIRECTION);
    }
    public Ship(int x, int y, int width, int height, int variation) {
        this(x, y, width, height, variation, DEFAULT_VALUE, DEFAULT_DIRECTION);
    }
    public Ship(int x, int y, int width, int height, int variation, int value) {
        this(x, y, width, height, variation, value, DEFAULT_DIRECTION);
    }
    public Ship(int x, int y, int width, int height, int variation, int value, Vector2D direction) {
	    this.x=x;
	    this.y=y;
	    this.width=width;
	    this.height=height;
	    this.variation=variation;
	    this.value=value;
	    this.direction=direction;
    }

    public void draw(Graphics2D g2) {
	    // Insert draw code here
        g2.drawRect(x, y, width, height);
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
    public int getVariation() {
        return variation;
    }
    public void setVariation(int variation) {
        this.variation = variation;
    }
    public int getValue() {
        return value;
    }
    public void setValue(int value) {
        this.value = value;
    }
    public Vector2D getDirection() {
        return direction;
    }
    public void setDirection(Vector2D direction) {
        this.direction = direction;
    }
    public ArrayList<Bullet> getBullets() {
        return bullets;
    }
    public void setBullets(ArrayList<Bullet> bullets) {
        this.bullets = bullets;
    }
}
