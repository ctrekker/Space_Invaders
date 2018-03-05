public class ScoreInvaders extends Ship{
    private Ship lives;

    public ScoreInvaders(String name)
    {
        this.lives = new Ship();
    }

    public Ship getLives(){ return lives;}
    public void setLives(Ship lives){ this.lives = lives;}
}
