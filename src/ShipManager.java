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
        if(initStage==0) {
            if(lastShipReleaseCounter>20&&shipAddCount<8) {
                Ship s1=new Ship(GameGUI.canvasWidth/2-200, -50);
                Ship s2=new Ship(GameGUI.canvasWidth/2+200, -50);
                s1.setVariation(2);
                s2.setVariation(1);
                s1.setDirection(Ship.DEFAULT_SPEED, Ship.DEFAULT_SPEED/1.5);
                s2.setDirection(-Ship.DEFAULT_SPEED, Ship.DEFAULT_SPEED/1.5);
                ships.add(s1);
                ships.add(s2);
                shipAddCount+=2;
                lastShipReleaseCounter=0;
            }
            else if(lastShipReleaseCounter>60&&shipAddCount>=8) {
                //initStage++;
            }

            for(Ship s : ships) {
                if(!s.isPathMode()) {
                    double curveFactor = 0.3;
                    if (s.getX() < 150) {
                        s.setDeltaX(s.getDeltaX() + curveFactor);
                    } else if (s.getX() > GameGUI.canvasWidth - 150) {
                        s.setDeltaX(s.getDeltaX() - curveFactor);
                    }
                    if (s.getY() > GameGUI.canvasHeight / 2) {
                        s.setDeltaY(s.getDeltaY() - curveFactor);
                        if(s.getY()+s.getDeltaY() < GameGUI.canvasHeight/2) {
                            s.setPathMode(true);
                        }
                    }
                }
                else {
                    s.setDirection(0, 0);
                }
            }
            lastShipReleaseCounter++;
        }
        else if(initStage==1) {

        }
        frame++;
    }
    public void drawShips(Graphics2D g2) {
        for(Ship s : ships) {

            s.draw(g2);
        }
    }
}
