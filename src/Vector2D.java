public class Vector2D {
    private double deltaX;
    private double deltaY;
    public Vector2D(double xDelta, double yDelta) {
        this.deltaX=xDelta;
        this.deltaY=yDelta;
    }

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
