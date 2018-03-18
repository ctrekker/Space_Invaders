import java.awt.*;

//This is the class where we are making the ship
public class Player extends Ship {
    public static final int DEFAULT_BULLET_WIDTH = 7;
    public static final int DEFAULT_BULLET_HEIGHT = 25;
    private Ship ship; //Instantiating The Ship
    private String name;
    private int latestExtraLifeScore=0;

    public Player(String name) {
        this.ship=new Ship();
        this.name=name;
    }

    @Override
    public void draw(Graphics2D g2, boolean move) {
        super.draw(g2, move);

        if(GameTracker.Score-latestExtraLifeScore>20000) {
            GameTracker.addLife();
            latestExtraLifeScore+=20000;
        }
    }

    public Ship getShip() {
        return ship;
    }
    public void setShip(Ship ship) {
        this.ship = ship;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
