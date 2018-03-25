//A+ Computer Science  -  www.apluscompsci.com
//Names - Amber, Akash, Arjun, Connor, Nihal, Narendhar, Sonia
//Date - 3/24/18
//Class - AP Comp Sci Period 7
//Lab  - Galaga

// Stores double values corresponding on a 2D plane
public class Vector2D {
    private double deltaX;
    private double deltaY;
    // Init to default values
    public Vector2D() {
        this.deltaX=0;
        this.deltaY=0;
    }
    // Init to provided values
    public Vector2D(double xDelta, double yDelta) {
        this.deltaX=xDelta;
        this.deltaY=yDelta;
    }

    // Getters and setters
    public double getDeltaX() {
        return deltaX;
    }
    public void setDeltaX(double deltaX) {
        this.deltaX = deltaX;
    }
    public double getDeltaY() {
        return deltaY;
    }
    public void setDeltaY(double deltaY) {
        this.deltaY = deltaY;
    }
}
