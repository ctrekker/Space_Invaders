public class Player {
    private Ship ship;
    private String name;
    private int score;

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
}
