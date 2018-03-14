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

	private int x;
	private int y;
	private int width;
	private int height;
    private Vector2D direction;
	private Color color;
	private int variant=0;

    private static BufferedImage bullet = null;
	private static BufferedImage lazerBeam = null;
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
				bullet = ImageIO.read(new File("res/img/Bullet.png"));
			}
			if(bulletFlipped == null) {
			    bulletFlipped=ImageIO.read(new File("res/img/BulletFlipped.png"));
            }
			if(lazerBeam == null) {
				lazerBeam=ImageIO.read(new File("res/img/lazerbeam.png"));
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
		else if(variant == 2) g2.drawImage(lazerBeam, x-width/2, y-height/2, width, height, null);
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

    public int getVariant() {
        return variant;
    }

    public void setVariant(int variant) {
        this.variant = variant;
    }
}
