import java.awt.*;// A Pacakge that has already been premade and we can just call them
import java.util.ArrayList;//We Are Importing an array list from the package

public class  DynamicBackground {
    private final double slowFactor=2.5;
    private double entropy=0.1;
    private int colorMin=100;

    private ArrayList<Star> stars;

    public DynamicBackground() {
        stars=new ArrayList<>();
    }
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
    public void draw(Graphics2D g2, boolean show) {
        draw(g2, show, true);
    }
}