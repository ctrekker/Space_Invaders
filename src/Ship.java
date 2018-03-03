import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.*;
	
public class Ship {
    public static final int DEFAULT_SPEED=5;

    private static final int DEFAULT_X=0;
    private static final int DEFAULT_Y=0;
    private static final int DEFAULT_WIDTH =50;
    private static final int DEFAULT_HEIGHT=50;
    private static final int DEFAULT_VARIATION=0;
    private static final int DEFAULT_VALUE=1;
    private static final Color DEFAULT_COLOR=Color.WHITE;

    private static BufferedImage playerShip = null;

	private int x;
	private int y;
	private int width;
	private int height;
	private int variation;
	private int value;
    private Vector2D direction;
	private ArrayList<Bullet> bullets=new ArrayList<>();
    private int lastBullet=0;
    /*
    State:
    0 -> passive
    1 -> attacking
    2 -> init
     */
    private int state=0;
    private boolean pathMode=false;

    public Ship() {
	    this(DEFAULT_X, DEFAULT_Y, DEFAULT_WIDTH, DEFAULT_HEIGHT, DEFAULT_VARIATION, DEFAULT_VALUE, new Vector2D());
    }
    public Ship(int x, int y) {
        this(x, y, DEFAULT_WIDTH, DEFAULT_HEIGHT, DEFAULT_VARIATION, DEFAULT_VALUE, new Vector2D());
    }
    public Ship(int x, int y, int width, int height) {
        this(x, y, width, height, DEFAULT_VARIATION, DEFAULT_VALUE, new Vector2D());
    }
    public Ship(int x, int y, int width, int height, int variation) {
        this(x, y, width, height, variation, DEFAULT_VALUE, new Vector2D());
    }
    public Ship(int x, int y, int width, int height, int variation, int value) {
        this(x, y, width, height, variation, value, new Vector2D());
    }
    public Ship(int x, int y, int width, int height, int variation, int value, Vector2D direction) {

	    this.x=x;
	    this.y=y;
	    this.width=width;
	    this.height=height;
	    this.variation=variation;
	    this.value=value;
	    this.direction=direction;

        try
        {
            if(playerShip == null)
            {
                playerShip = ImageIO.read( new File("Space_Invader_Pics/ship_player.png" ));
            }

        }
        catch (IOException e)
        {
            System.out.println("Missing image resource!");
        }
    }
    public void draw(Graphics2D g2) {
	    lastBullet++;

	    if(((x-width/2>=0||(x-width/2<=0&&getDeltaX()>0))&&(x+width/2<=GameGUI.canvasWidth||(x+width/2>=GameGUI.canvasWidth&&getDeltaX()<0)))||variation!=0) {
	        x+=direction.getDeltaX();
        }
        if(((y-height/2>=0||(y-height/2<=0&&getDeltaY()>0))&&(y+height/2<=GameGUI.canvasHeight||(y+height/2>=GameGUI.canvasHeight&&getDeltaY()<0)))||variation!=0) {
	        y+=direction.getDeltaY();
        }

        for(Bullet bullet : bullets) {
	        bullet.draw(g2);
        }

        if(variation==0) {
            g2.drawImage(playerShip , x-width/2, y-height/2,width, height, null);
        }
        else {
	        Color c;
	        if(variation==1) c=Color.BLUE;
	        if(variation==2) c=Color.RED;
	        else c=Color.GREEN;
	        g2.setColor(c);
            g2.fillRect(x-width/2, y-height/2, width, height);
        }
    }

    public void shootBullet() {
	    if(lastBullet>15) {
	        bullets.add(new Bullet(x, y));
	        lastBullet=0;
        }
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
    public void setDirection(double dX, double dY) {
	    direction.setDeltaX(dX);
	    direction.setDeltaY(dY);
    }
    public ArrayList<Bullet> getBullets() {
        return bullets;
    }
    public void setBullets(ArrayList<Bullet> bullets) {
        this.bullets = bullets;
    }
    public double getDeltaX() {
        return direction.getDeltaX();
    }
    public double getDeltaY() {
        return direction.getDeltaY();
    }
    public void setDeltaX(double deltaX) {
	    direction.setDeltaX(deltaX);
    }
    public void setDeltaY(double deltaY) {
	    direction.setDeltaY(deltaY);
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
    public void setPathMode(boolean mode) {
        pathMode=mode;
    }
    public boolean isPathMode() {
        return pathMode;
    }
}
