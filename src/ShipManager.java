import java.awt.*;
import java.util.ArrayList;

// VERY IMPORTANT CLASS
// Responsible for managing every single enemy ship you EVER see in the game!
public class ShipManager {
    private static final int maxShipSpeed=13;
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
    private final int shipWidth =41;
    private final int shipHeight=41;
    private static int Stage = 0;

    private int deltaMultiplier=1;
    final private int deltaMultiplierEnd=250;
    private boolean deltaOut=true;

    // List of variants which ships should be initialized to when they enter the screen
    private int[] variants={
            1, 2, 1, 2, 1, 2, 1, 2,
            2, 3, 2, 3, 2, 3, 2, 3,
            2, 2, 2, 2, 2, 2, 2, 2,
            1, 1, 1, 1, 1, 1, 1, 1,
            1, 1, 1, 1, 1, 1, 1, 1
    };
    // Dummy constructor
    public ShipManager() {

    }

    // ArrayList encapsulation methods
    public void addShip(Ship s) {
        ships.add(s);
    }
    public void removeShip(int i) {
        ships.remove(i);
    }

    // Getters and setters for anti-encapsulation
    public ArrayList<Ship> getShips() {
        return ships;
    }
    public void setShips(ArrayList<Ship> ships) {
        this.ships = ships;
    }

    // Moves and handles ships per-frame according to the saved game state
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
        else if(initStage==6) {
            for(Ship s : ships) {
                s.setPathfinding(false);
                s.shipChangeCounter=0;
            }
            if(shipSpeed<maxShipSpeed&&GameTracker.stage%3==0) shipSpeed++;
            initStage++;
        }
        else if(initStage==7) {
            int i=0;
            for(Ship s : ships) {
                if(!s.isPathfinding()&&s.getPath()==null) {
                    int focusX=GameGUI.canvasWidth/2;
                    int focusY=50;
                    double offsetX=Path.load("ship_locations").getRealPoint(i).getX()-focusX;
                    double offsetY=Path.load("ship_locations").getRealPoint(i).getY()-focusY;
                    double incX=(offsetX/deltaMultiplierEnd)*(deltaMultiplier/1.4);
                    double incY=(offsetY/deltaMultiplierEnd)*(deltaMultiplier/1.4);

                    s.setX(focusX+offsetX+incX);
                    s.setY(focusY+offsetY+incY);
                }
                i++;
            }
            if(deltaMultiplier<deltaMultiplierEnd&&deltaOut) {
                deltaMultiplier++;
            }
            else if(deltaMultiplier>=deltaMultiplierEnd&&deltaOut) {
                deltaOut=false;
            }
            if(deltaMultiplier>0&&!deltaOut) {
                deltaMultiplier--;
            }
            else if(deltaMultiplier<=0&&!deltaOut) {
                deltaOut=true;
            }
        }


        frame++;
    }
    // Run a ship frame which handles all of the ships on the screen's paths and actions
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
            s1.setWidth(shipWidth);
            s1.setHeight(shipHeight);
            ships.add(s1);
            shipAddCount++;

            if(p2!=null) {
                Ship s2 = new Ship();
                s2.setPath(p2);
                s2.setVariation(2);
                s2.setSpeed(shipSpeed);
                s2.setVariation(variants[ships.size()]);
                s2.setWidth(shipWidth);
                s2.setHeight(shipHeight);
                ships.add(s2);
                shipAddCount++;
            }
        }
        return false;
    }
    // Draws all the ships contained in the class
    // Also does some path handling stuff
    public void drawShips(Graphics2D g2, boolean move) {
        int destroyedCount=0;
        int attackingCount=0;
        for(Ship s : ships) {
            if(s.isDestroyed()) {
                destroyedCount++;
            }
            if(s.getPath()!=null) {
                if(s.getPath().getName().equals("attack-run")) {
                    attackingCount++;
                }
            }
        }
        if(destroyedCount==0) destroyedCount=1;

        int i=0;
        for(Ship s : ships) {
            if(s.getPath()!=null) {
                if(s.getPath().getName().equals("attack-retreat")&&!s.isPathfinding()) {
                    s.setPath((Path)null);
                    s.setRotation(-90);
                    s.setSpeed(s.getSpeed()*2);
                }
                try {
                    if ((s.getPath().getName().equals("attack-run") && !s.isPathfinding()) || s.getPath().getName().equals("attack-retreat")) {
                        if (s.getY() > GameGUI.canvasHeight) {
                            s.setY(-50);
                        }

                        int focusX = GameGUI.canvasWidth / 2;
                        int focusY = 50;
                        double offsetX = Path.load("ship_locations").getRealPoint(i).getX() - focusX;
                        double offsetY = Path.load("ship_locations").getRealPoint(i).getY() - focusY;
                        double incX = (offsetX / deltaMultiplierEnd) * (deltaMultiplier / 2);
                        double incY = (offsetY / deltaMultiplierEnd) * (deltaMultiplier / 2);

                        Path toSpot = new Path();
                        toSpot.add(new DoublePoint(s.getX() / GameGUI.canvasWidth, s.getY() / GameGUI.canvasHeight));
                        toSpot.add(new DoublePoint((focusX + offsetX + incX) / GameGUI.canvasWidth, (focusY + offsetY + incY) / GameGUI.canvasHeight));
                        toSpot.setName("attack-retreat");
                        s.setPath(toSpot);
                    }
                } catch(NullPointerException e) {

                }
            }
            s.draw(g2, move);
            s.setRandomTrigger((0.005/ships.size())*destroyedCount);
            s.randomTick(attackingCount<2);

            i++;
        }
        if(destroyedCount>=ships.size()) {
            GameTracker.showStage=true;
            GameTracker.showStageCounter=0;
            initStage=1;
            frame=0;
            deltaMultiplier=1;
            deltaOut=true;
            ships.clear();
            GameTracker.stage++;
        }
    }
}
