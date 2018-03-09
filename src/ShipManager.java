import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ShipManager {
    private ArrayList<Ship> ships = new ArrayList<>();
    // Current frame of the movement
    private int frame=0;
    // Stage of init
    private int initStage=1;
    // Current number of ships added during init
    private int shipAddCount=0;
    // The number of ships which have reached their final position
    private int shipsComplete=0;
    private int shipSpeed=9;

    private int[] variants={
            1, 2, 1, 2, 1, 2, 1, 2,
            2, 3, 2, 3, 2, 3, 2, 3,
            2, 2, 2, 2, 2, 2, 2, 2,
            1, 1, 1, 1, 1, 1, 1, 1,
            1, 1, 1, 1, 1, 1, 1, 1
    };
    public ShipManager() {

    }

    public void addShip(Ship s) {
        ships.add(s);
    }
    public void removeShip(int i) {
        ships.remove(i);
    }
    
    public boolean didCollideBullet(){
      boolean collided = false;
      // ArrayList<Bullet> bullets = getBullets();
       for(int i = 0; i < ships.size(); i++){
          Ship s = ships.get(i);
          
          for(int j = 0; j < bullets.size(); j++){
             Bullet b = bullets.get(j);
             
             //on top and to the right
             if(b.getX() + b.getWidth() >= s.getX() && b.getY() >= s.getY() &&
                b.getY() <= s.getY() + s.getHeight() || b.getY() + b.getHeight() >= s.getY() &&
                b.getY() + b.getHeight() <= s.getY() + s.getHeight()){
                  collided = true;
             }
             
             //on bottom and to the left
             else if(b.getX() <= s.getX() + s.getWidth() &&
               (b.getY() >= s.getY() && b.getY()<= s.getY() + s.getHeight() ||
                b.getY() + b.getHeight() >= s.getY() && 
                b.getY() + b.getHeight() <= s.getY() + s.getHeight())){
                  collided = true;       
             }
           }
        }
        //System.out.println(collided);
        return collided;
    }

    public ArrayList<Ship> getShips() {
        return ships;
    }
    public void setShips(ArrayList<Ship> ships) {
        this.ships = ships;
    }

    public void moveShips() {
        if(initStage==1) {
            shipFrame("1-1a", "1-1b");
        }
        else if(initStage==2) {
            shipFrame("1-2", null);
        }
        else if(initStage==3) {
            shipFrame("1-3", null);
        }
        else if(initStage==4) {
            shipFrame("1-4", null);
        }
        else if(initStage==5) {
            shipFrame("1-5", null);
        }

        frame++;
    }
    private boolean shipFrame(String p1, String p2) {
        int i=0;
        for(Ship s : ships) {
            if(!s.isPathfinding()) {
                if(s.getPath()==null||!s.getPath().getName().equals("ship_final_location")) {
                    Path path=new Path();
                    path.add(new DoublePoint(s.getX()/GameGUI.canvasWidth, s.getY()/GameGUI.canvasHeight));
                    path.add(Path.load("ship_locations").get(i));
                    path.setName("ship_final_location");
                    s.setPath(path);
                }


                if(s.getPath().getName().equals("ship_final_location")&&!s.isPathfinding()) {
                    s.setPath((Path)null);
                    s.setPathfinding(true);
                    s.setRotation(-90);
                    s.setDirection(0, 0);
                    shipsComplete++;
                    if(shipsComplete>=8) {
                        initStage++;
                        frame=0;
                        shipAddCount=0;
                        shipsComplete=0;
                        return true;
                    }
                }
            }
            i++;
        }
        if(frame%(100/shipSpeed)==0&&shipAddCount<8) {
            Ship s1 = new Ship();
            s1.setPath(p1);
            s1.setVariation(1);
            s1.setSpeed(shipSpeed);
            s1.setVariation(variants[ships.size()]);
            ships.add(s1);
            shipAddCount++;

            if(p2!=null) {
                Ship s2 = new Ship();
                s2.setPath(p2);
                s2.setVariation(2);
                s2.setSpeed(shipSpeed);
                s2.setVariation(variants[ships.size()]);
                ships.add(s2);
                shipAddCount++;
            }
        }
        return false;
    }
    public void drawShips(Graphics2D g2) {
        for(Ship s : ships) {
            s.draw(g2);
        }
    }
}
