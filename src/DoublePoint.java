public class DoublePoint { //Class for the Double Point
    private double x; //Making x a double
    private double y; //Making y a double

    public DoublePoint(double x, double y) {
        this.x=x;
        this.y=y;
    }
    public double getX() { //Get Method for the x
        return x;
    }
    public void setX(double x) { //Set method for the x
        this.x = x;
    }
    public double getY() { //Get method for y
        return y;
    }
    public void setY(double y) {//Set method for y
        this.y = y;
    }

    @Override
    public String toString() {
        return "DoublePoint["+x+","+y+"]";
    }
}