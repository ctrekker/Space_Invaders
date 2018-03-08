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
    public ShipManager() {

    }

    public void addShip(Ship s) {
        ships.add(s);
    }
    public void removeShip(int i) {
        ships.remove(i);
    }

    public ArrayList<Ship> getShips() {
        return ships;
    }
    public void setShips(ArrayList<Ship> ships) {
        this.ships = ships;
    }

    public void moveShips() {
        if(initStage==1) {
            int i=0;
            for(Ship s : ships) {
                if(!s.isPathfinding()&&s.getPath()==null) {
                    //Path path=new Path();
                    //path.add(new DoublePoint(s.getX()/GameGUI.canvasWidth, s.getY()/GameGUI.canvasHeight));
                    //path.add(Path.load("ship_locations").get(i));
                    //s.followPath(path);
                }
                i++;
            }
            if(frame%60==0&&shipAddCount<8) {
                Ship s1=new Ship();
                Ship s2=new Ship();
                s1.setPath("1-1a");
                s2.setPath("1-1b");
                s1.setVariation(1);
                s2.setVariation(2);
                ships.add(s1);
                ships.add(s2);

                shipAddCount+=2;
            }
        }
        else if(initStage==2) {

        }

        frame++;
    }
    public void drawShips(Graphics2D g2) {
        for(Ship s : ships) {
            s.draw(g2);
        }
    }
}
