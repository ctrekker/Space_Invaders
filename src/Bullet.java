import java.awt.Color;


public class Bullet {
	private static final int DEFAULT_X=0;
	private static final int DEFAULT_Y=0;
	private static final int DEFAULT_WIDTH=10;
	private static final int DEFAULT_HEIGHT=10;
	private static final Vector2D DEFAULT_DIRECTION=new Vector2D();
	private static final Color DEFAULT_COLOR=Color.WHITE;

	private int x;
	private int y;
	private int width;
	private int height;
    private Vector2D direction;
	private Color color;

	public Bullet() {
		this(DEFAULT_X, DEFAULT_Y, DEFAULT_WIDTH, DEFAULT_HEIGHT, DEFAULT_DIRECTION, DEFAULT_COLOR);
	}
	public Bullet(int x, int y) {
		this(x, y, DEFAULT_WIDTH, DEFAULT_HEIGHT, DEFAULT_DIRECTION, DEFAULT_COLOR);
	}
	public Bullet(int x, int y, int width, int height) {
		this(x, y, width, height, DEFAULT_DIRECTION, DEFAULT_COLOR);
	}
	public Bullet(int x, int y, int width, int height, Vector2D direction) {
		this(x, y, width, height, direction, DEFAULT_COLOR);
	}
	public Bullet(int x, int y, int width, int height, Vector2D direction, Color color) {
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
		this.direction=direction;
		this.color=color;
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
