public class Player extends Ship {
    private Ship ship;
    private String name;
    private int score;
    private int speed;

    public Player(String name) {
        this.ship=new Ship();
        this.name=name;
        this.score=0;
    }

    @Override
    public void setDirection(Vector2D v2) {
        System.out.println(v2);
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
