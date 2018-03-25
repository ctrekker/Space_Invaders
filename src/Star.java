//A+ Computer Science  -  www.apluscompsci.com
//Names - Amber, Akash, Arjun, Connor, Nihal, Narendhar, Sonia
//Date - 3/24/18
//Class - AP Comp Sci Period 7
//Lab  - Galaga
import java.awt.*;// A Pacakge that has already been premade and we can just call them

public class Star extends Point { //This class is when we start to make the stars
    private Color color; //Starting to set the color
    private int radius; //Making an int radius
    public Star(int x, int y, int radius, Color color)//Saying the demensions units for the star
     {
        super(x, y); //Call it from another class

        this.radius=radius; //Instantiated the radius
        this.color=color; //Instantiated the color
    }

    // Draws a star based on its instance variables
    public void draw(Graphics2D g2) {
        g2.setColor(color);
        g2.fillArc(x-radius, y-radius, radius*2, radius*2, 0, 360);
    }

    //Getter and Setter methods to set the variables.
    public Color getColor() {
        return color;
    }
    public void setColor(Color color) {
        this.color = color;
    }
    public int getRadius() {
        return radius;
    }
    public void setRadius(int radius) {
        this.radius=radius;
    }
}
