import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.*;// A Pacakge that has already been premade and we can just call them
	
public class Ship {
    public static final int DEFAULT_SPEED=6;
    public static final int DEFAULT_X=0;//Setting the X Value of the ship
    public static final int DEFAULT_Y=0;//Setting the Y Value of the ship
    public static final int DEFAULT_WIDTH =50;//Setting the Width Value of the ship
    public static final int DEFAULT_HEIGHT=50;//Setting the Height Value of the ship
    public static final int DEFAULT_VARIATION=0;
    public static final int DEFAULT_VALUE=1;
    public static final Color DEFAULT_COLOR=Color.WHITE;//Setting the color to white

    private static BufferedImage playerShip = null;
    private static BufferedImage enemyShip1 = null;
    private static BufferedImage enemyShip2 = null;

	private double x;
	private double y;
	private int width;
	private int height;
	private int variation;
	private int value;
    private int speed=4;

    private Vector2D direction;
	private ArrayList<Bullet> bullets=new ArrayList<>();
    private int lastBullet=0;
    private int currentPoint=0;

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
                playerShip = ImageIO.read( new File("res/img/ship_player.png" ));
            }
            if(enemyShip1 == null)
            {
                enemyShip1= ImageIO.read( new File("res/img/EnemyShip1.png" ));
            }
            if(enemyShip2 == null)
            {
                enemyShip2= ImageIO.read( new File("res/img/EnemyShip2.png" ));
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
            g2.drawImage(playerShip , (int)(x-width/2), (int)(y-height/2), width, height, null);

        }
        else if(variation==1)
        {
            g2.drawImage(enemyShip1 , (int)(x-width/2), (int)(y-height/2), width, height, null);
        }
        else if(variation==2)
        {
            g2.drawImage(enemyShip2 , (int)(x-width/2), (int)(y-height/2), width, height, null);
        }
        else {
	        Color c;
	        if(variation==1) c=Color.BLUE;
	        if(variation==2) c=Color.RED;
	        else c=Color.GREEN;
	        g2.setColor(c);
            g2.fillRect((int)(x-width/2), (int)(y-height/2), width, height);
        }
    }

    public void shootBullet() {
	    if(lastBullet>15) {
	        bullets.add(new Bullet((int)x, (int)y));
	        lastBullet=0;
        }
    }

    public boolean followPath(Path path) {
        double offsetX=path.getRealPoint(currentPoint).getX()-(x);
        double offsetY=path.getRealPoint(currentPoint).getY()-(y);
        System.out.println(offsetX+","+offsetY);
        System.out.println(path.getRealPoint(currentPoint));
        int distanceTotal=(int)Math.sqrt(Math.pow(offsetX, 2)+Math.pow(offsetY, 2));
        System.out.println(distanceTotal+"--"+speed+"--"+currentPoint);
        if(Math.abs(distanceTotal)<speed) {
            Point d=path.getRealPoint(currentPoint);
            x=d.getX();
            y=d.getY();
            currentPoint++;
            if(currentPoint>path.size()-1) {
                currentPoint=0;
                return true;
            }
        }
        else {
            double xSpeed;
            if(offsetX!=0) xSpeed = speed*(offsetX/distanceTotal);
            else xSpeed=0;
            double ySpeed;
            if(offsetY!=0) ySpeed = speed*(offsetY/distanceTotal);
            else ySpeed=0;

            System.out.println(xSpeed+","+ySpeed);
//            System.out.println(distanceTotal);
//            System.out.println(offsetX + "," + offsetY);
            //        System.out.println(path.getRealPoint(currentPoint));
            setDirection(xSpeed, ySpeed);
        }
        System.out.println("-----");
        return false;
    }



    public double getX() {
        return x;
    }
    public void setX(double x) {
        this.x = x;
    }
    public double getY() {
        return y;
    }
    public void setY(double y) {
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
    public int getSpeed() {
        return speed;
    }
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getCurrentPoint() {
        return currentPoint;
    }
    public void setCurrentPoint(int currentPoint) {
        this.currentPoint = currentPoint;
    }
}
