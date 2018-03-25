//A+ Computer Science  -  www.apluscompsci.com
//Names - Amber, Akash, Arjun, Connor, Nihal, Narendhar, Sonia
//Date - 3/24/18
//Class - AP Comp Sci Period 7
//Lab  - Galaga
import java.awt.*;// A Pacakge that has already been premade and we can just call them
import java.util.ArrayList;//We Are Importing an array list from the package

/*
This class is responsible for managing the starry background
which moves in a 3d-like manner. Although the effect is done
purely on a linear movement axis, it still looks 3d.
 */
public class  DynamicBackground {
    // Slows down stars by this number
    private final double slowFactor=2.5;
    // Controls how often a new star should be initialized
    private double entropy=0.1;
    // Minimum color within range (colorMin to 255)
    private int colorMin=100;

    // Stores all the stars on the screen
    private ArrayList<Star> stars;

    // Constructor
    public DynamicBackground() {
        stars=new ArrayList<>();
    }
    // Draws the stars on the screen, as well as performs movement calculations
    public void draw(Graphics2D g2, boolean show, boolean move) {
        if(Math.random()<entropy&&move) {
            int col=Util.random(colorMin, 255);
            stars.add(new Star(Util.random(0, GameGUI.canvasWidth), 0, Util.random(2, 4), new Color(col, col, col)));
        }

        if(move) {
            for (int i = 0; i < stars.size(); i++) {
                stars.get(i).setLocation(stars.get(i).x, stars.get(i).y + (stars.get(i).getRadius() / slowFactor));
                if (stars.get(i).y > GameGUI.canvasHeight + stars.get(i).getRadius()) {
                    stars.remove(i);
                }
            }
        }

        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, GameGUI.canvasWidth, GameGUI.canvasHeight);

        for(int i=0; i<stars.size(); i++) {
            stars.get(i).draw(g2);
        }

        if(!show) {
            g2.setColor(Color.BLACK);
            g2.fillRect(0, 0, GameGUI.canvasWidth, GameGUI.canvasHeight);
        }
    }
    // Alternate method for omitting the last boolean
    public void draw(Graphics2D g2, boolean show) {
        draw(g2, show, true);
    }
}