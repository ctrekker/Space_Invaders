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

        }
        else if(initStage==1) {

        }
        frame++;
    }
    public boolean doShipFrame() {
        boolean returnVal=false;

        if(lastShipReleaseCounter>20&&shipAddCount<8) {
            final int shipSpeed=7;
            Ship s1=new Ship(GameGUI.canvasWidth/2-200, -50);
            Ship s2=new Ship(GameGUI.canvasWidth/2+200, -50);
            s1.setVariation(2);
            s2.setVariation(1);
            s1.setDirection(shipSpeed, shipSpeed/1.5);
            s2.setDirection(-shipSpeed, shipSpeed/1.5);
            s1.setDesiredLocation(new Point(GameGUI.canvasWidth/2-100, shipAddCount*40+50));
            s2.setDesiredLocation(new Point(GameGUI.canvasWidth/2+100, shipAddCount*40+50));
            ships.add(s1);
            ships.add(s2);
            shipAddCount+=2;
            lastShipReleaseCounter=0;
        }

        for(Ship s : ships) {
            if(!s.isPathMode()&&!s.isFinished()) {
                double curveFactor = 0.3;
                if (s.getX() < 150) {
                    s.setDeltaX(s.getDeltaX() + curveFactor);
                } else if (s.getX() > GameGUI.canvasWidth - 150) {
                    s.setDeltaX(s.getDeltaX() - curveFactor);
                }
                if (s.getY() > GameGUI.canvasHeight / 2) {
                    s.setDeltaY(s.getDeltaY() - curveFactor/1.75);
                    if(s.getY()+s.getDeltaY() < GameGUI.canvasHeight/2) {
                        s.setPathMode(true);
                    }
                }
            }
            else if(!s.isFinished()) {
                final int speed=45;

                if(!s.hasCalculatedVector()) s.calculateVector(speed);
                else s.setCalculatedVectorCount(s.getCalculatedVectorCount()+1);

                if(s.getCalculatedVectorCount()>=speed) {
                    s.setDirection(0, 0);
                    s.setFinished(true);
                    shipsComplete++;
                    if(shipsComplete%8==0) returnVal=true;
                }
            }
        }
        lastShipReleaseCounter++;
        return returnVal;
    }
    public void drawShips(Graphics2D g2) {
        for(Ship s : ships) {

            s.draw(g2);
        }
    }
}
