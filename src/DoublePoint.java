//A+ Computer Science  -  www.apluscompsci.com
//Names - Amber, Akash, Arjun, Connor, Nihal, Narendhar, Sonia
//Date - 3/24/18
//Class - AP Comp Sci Period 7
//Lab  - Galaga
public class DoublePoint { //Class for the Double Point
    private double x; //Making x a double
    private double y; //Making y a double

    // Constructor
    public DoublePoint(double x, double y) {
        this.x=x;
        this.y=y;
    }

    // A few getters and setters
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

    // Used for debugging to see what the value stored is by simply printing the object
    @Override
    public String toString() {
        return "DoublePoint["+x+","+y+"]";
    }
}