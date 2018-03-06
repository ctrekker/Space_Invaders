import java.awt.*; // A Pacakge that has already been premade and we can just call them


public class Bullet {
    public static final double DEFAULT_SPEED=12;

	private static final int DEFAULT_X=0; //Setting the X Value Of The Bullet
	private static final int DEFAULT_Y=0; //Setting the Y Value Of The Bullet
	private static final int DEFAULT_WIDTH=5;//Setting the Width Value Of The Bullet
	private static final int DEFAULT_HEIGHT=15;//Setting the Height Value Of The Bullet
	private static final Color DEFAULT_COLOR=Color.RED;//Setting The Color To Red
    private static final Vector2D DEFAULT_DIRECTION=new Vector2D(0, -DEFAULT_SPEED);

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

	public void draw(Graphics2D g2) {
	    x+=direction.getDeltaX();
	    y+=direction.getDeltaY();

	    g2.setColor(color);
		g2.fillRect(x-width/2, y-height/2, width, height);
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
