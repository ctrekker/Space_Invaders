import javax.imageio.ImageIO;
import java.awt.geom.AffineTransform;
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

    private static final int[] rotationOffsets={0, 90, 90, 90};

    public static BufferedImage playerShip = null;
    private static BufferedImage enemyShip1 = null;
    private static BufferedImage enemyShip2 = null;
    private static BufferedImage enemyShip3a = null;
    private static BufferedImage enemyShip3b = null;

	private double x;
	private double y;
	private int width;
	private int height;
	private int variation;
	private int value;
    private int speed=3;
    private double rotation=0;
    private double desiredRotation=0;

    private Vector2D direction;
    public ArrayList<Bullet> bullets=new ArrayList<>();
    private int lastBullet=0;
    private int currentPoint=0;
    private Path path=null;
    private boolean pathfinding=false;

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
            if(enemyShip3a == null)
            {
                enemyShip3a= ImageIO.read( new File("res/img/EnemyShip3a.png" ));
            }
            if(enemyShip3b == null)
            {
                enemyShip3b= ImageIO.read( new File("res/img/EnemyShip3b.png" ));
            }

        }
        catch (IOException e)
        {
            System.out.println("Missing image resource!");
        }
    }
    public void draw(Graphics2D g2) {
        if(path!=null&&pathfinding) {
            pathfinding=!followPath(path);
        }

	    lastBullet++;

//        if(rotation<0&&desiredRotation>0) {
//            rotation+=360;
//        }
//        else if(rotation>0&&desiredRotation<0) {
//            rotation-=360;
//        }
//
//        rotation+=(desiredRotation-rotation)/5;
//        System.out.println(desiredRotation);
	    //else rotation+=-(360-Math.abs(desiredRotation-rotation))/5;
        rotation=desiredRotation;


	    if(((x-width/2>=0||(x-width/2<=0&&getDeltaX()>0))&&(x+width/2<=GameGUI.canvasWidth||(x+width/2>=GameGUI.canvasWidth&&getDeltaX()<0)))||variation!=0) {
	        x+=direction.getDeltaX();
        }
        if(((y-height/2>=0||(y-height/2<=0&&getDeltaY()>0))&&(y+height/2<=GameGUI.canvasHeight||(y+height/2>=GameGUI.canvasHeight&&getDeltaY()<0)))||variation!=0) {
	        y+=direction.getDeltaY();
        }

        for(Bullet bullet : bullets) {
	        bullet.draw(g2);
        }

        AffineTransform preTransform=g2.getTransform();
	    g2.translate(x, y);
	    g2.rotate(Math.toRadians(rotation+rotationOffsets[variation]));

        if(variation==0) {
            g2.drawImage(playerShip , -width/2, -height/2, width, height, null);
        }
        else if(variation==1)
        {
            g2.drawImage(enemyShip1 , -width/2, -height/2, width, height, null);
        }
        else if(variation==2)
        {
            g2.drawImage(enemyShip2 , -width/2, -height/2, width, height, null);
        }
        else if(variation==3)
        {
            g2.drawImage(enemyShip3b , -width/2, -height/2, width, height, null);
        }
        else if(variation==4)
        {
            g2.drawImage(enemyShip3a , -width/2, -height/2, width, height, null);
        }
        else {
	        Color c=Color.GREEN;
	        g2.setColor(c);
            g2.fillRect(-width/2, -height/2, width, height);
        }

        g2.setTransform(preTransform);
    }

    public void shootBullet() {
	    if(lastBullet>15) {
	        bullets.add(new Bullet((int)x, (int)y));
	        lastBullet=0;
        }
    }

    public boolean followPath(Path path) {
        if(currentPoint==0) {
            x=path.getRealPoint(0).x;
            y=path.getRealPoint(0).y;
        }
        if(path==null) return false;

        Point ending=path.getRealPoint(currentPoint);
        double offsetX=ending.getX()-(x);
        double offsetY=ending.getY()-(y);
        int distanceTotal=(int)Math.sqrt(Math.pow(offsetX, 2)+Math.pow(offsetY, 2));

        if(Math.abs(distanceTotal)<speed) {
            Point d=path.getRealPoint(currentPoint);
            x=d.getX();
            y=d.getY();
            currentPoint++;
            if(currentPoint>path.size()-1) {
                currentPoint=0;
                x=ending.getX();
                y=ending.getY();
                setDirection(0, 0);
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

            setDirection(xSpeed, ySpeed);
        }

        int addAngle=0;
        if(x<ending.x) {
            addAngle=180;
        }

        double angle=rotation;
        if(x-ending.x!=0) angle=-Math.toDegrees(Math.atan((ending.y-y)/(x-ending.x)))+addAngle;
        desiredRotation=angle;

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
    public double getRotation() {
        return rotation;
    }
    public void setRotation(double rotation) {
        this.rotation=rotation;
        this.desiredRotation=rotation;
    }

    public int getCurrentPoint() {
        return currentPoint;
    }
    public void setCurrentPoint(int currentPoint) {
        this.currentPoint = currentPoint;
    }

    public void setPath(Path path) {
        pathfinding = path != null;
        this.path = path;
    }
    public void setPath(String strPath) {
        setPath(Path.load(strPath));
    }

    public Path getPath() {
        return path;
    }

    public boolean isPathfinding() {
        return pathfinding;
    }

    public void setPathfinding(boolean pathfinding) {
        this.pathfinding = pathfinding;
    }

    public void calculateDirection(int dX, int dY) {

    }
}
