import java.awt.*;
import java.util.ArrayList;

public class DynamicBackground {
    private double entropy=0.3;
    private int colorMin=100;

    private ArrayList<Star> stars;

    public DynamicBackground() {
        stars=new ArrayList<>();
    }
    public void draw(Graphics2D g2, boolean show) {
        if(Math.random()<entropy) {
            int col=Util.random(colorMin, 255);
            stars.add(new Star(Util.random(0, GameGUI.canvasWidth), 0, Util.random(2, 4), new Color(col, col, col)));
        }

        for(int i=0; i<stars.size(); i++) {
            stars.get(i).setLocation(stars.get(i).x, stars.get(i).y+stars.get(i).getRadius());
            if(stars.get(i).y>GameGUI.canvasHeight+stars.get(i).getRadius()) {
                stars.remove(i);
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
}