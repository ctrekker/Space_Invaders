import javax.imageio.ImageIO;
import java.awt.*; // A Pacakge that has already been premade and we can just call them
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class Bullet {
    public static final double DEFAULT_SPEED=10;

	private static final int DEFAULT_X=0; //Setting the X Value Of The Bullet
	private static final int DEFAULT_Y=0; //Setting the Y Value Of The Bullet
	private static final int DEFAULT_WIDTH=11; //Setting the Width Value Of The Bullet
	private static final int DEFAULT_HEIGHT=25;//Setting the Height Value Of The Bullet
	private static final Color DEFAULT_COLOR=Color.RED;//Setting The Color To Red
    private static final Vector2D DEFAULT_DIRECTION=new Vector2D(0, -DEFAULT_SPEED);

	private int x; //Instantiating the x
	private int y; //Instantiating the y
	private int width; //Instantaiting the width
	private int height; //Instantiaing the height
    private Vector2D direction;
	private Color color; //Instantiating the color
	private int variant=0;

    private static BufferedImage bullet = null;

    private static BufferedImage bulletFlipped = null;

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

		try {
			if (bullet == null) {
				bullet = ImageIO.read(new File("res/img/lazerbeam.png"));
			}
			if(bulletFlipped == null) {
			    bulletFlipped=ImageIO.read(new File("res/img/BulletFlipped.png"));
            }

		}
		catch (IOException e)
		{
			System.out.println("Missing image resource!");
		}
	}

	public void draw(Graphics2D g2) {
	    x+=direction.getDeltaX();
	    y+=direction.getDeltaY();

		if(variant==0) g2.drawImage(bullet, x-width/2, y-height/2, width, height, null);
		else if(variant==1) g2.drawImage(bulletFlipped, x-width/2, y-height/2, width, height, null);
	}

    public int getX() {//Making a get method for the x
        return x; //Returning the x
    }
    public void setX(int x) { //Making a set method for the x
        this.x = x; //Instantiating  the x
    }
    public int getY() { //Making a get method for y
        return y; //Returning the y
    }
    public void setY(int y) { //Making a set mehtod for y
        this.y = y; //Instantiating the y
    }
	public int getWidth() {//Making the get method for the width
		return width; //Returning the width
	}
	public void setWidth(int width) {//Making a set method for the width
		this.width = width; //Instantaitng the width
	}
	public int getHeight() { //Making a get method for the Height
		return height; //Returning the height 
	}
	public void setHeight(int height) {//Making a set method for the height
		this.height = height;//Returning the height value
	}
	public Vector2D getDirection() {//Making a get Direction methdod
		return direction; //Returning the direction method
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

    public int getVariant() {
        return variant;
    }

    public void setVariant(int variant) {
        this.variant = variant;
    }
}
