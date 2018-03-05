public class Player extends Ship {  //This is the class where we are making the ship
    private Ship ship; //Instantiating The Ship
    private String name;
    private int score;
    private int speed;

    public Player(String name) {
        this.ship=new Ship();
        this.name=name;
        this.score=0;
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
    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }
    public int getSpeed() {
        return speed;
    }
    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
