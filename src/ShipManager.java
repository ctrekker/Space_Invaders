import java.awt.*;
import java.util.ArrayList;

public class ShipManager {
    private ArrayList<Ship> ships = new ArrayList<>();
    // Current frame of the movement
    private int frame=0;
    // Stage of init
    private int initStage=0;
    // Counter per-frame of the last time a ship was released
    private int lastShipReleaseCounter=0;
    // Current number of ships added during init
    private int shipAddCount=0;
    // The number of ships which have reached their final position
    private int shipsComplete=0;
    public ShipManager() {
        ships.add(new Ship(0, 0, Ship.DEFAULT_WIDTH, Ship.DEFAULT_HEIGHT, 1));
        this.getShips().get(0).shootBullet();
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
        if(initStage==0) {
            doShipFrame("latest_path");
        }
        else if(initStage==1) {

        }
        frame++;
    }
    public boolean doShipFrame(String pathName) {
        Path path=Path.load(pathName);

        for(Ship s : ships) {
            s.followPath(path);
        }

        return false;
    }
    public void drawShips(Graphics2D g2) {
        for(Ship s : ships) {

            s.draw(g2);
        }
    }
}
