import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class GameGUI extends JFrame {
    public static int canvasWidth;
    public static int canvasHeight;
    private BufferedImage image;
    private ArrayList<ArrayList<Ship>> shipGrid = new ArrayList<ArrayList<Ship>>();


    public GameGUI() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);
        this.setResizable(false);

        add(new GameGraphics());

        this.setVisible(true);
    }
    
    public void addToGrid(int position){
    	shipGrid.get(position).add(new Ship());
    }



    
    class GameGraphics extends Component {
        DynamicBackground background;
        boolean firstTime=true;

        private Player player = new Player("name");
        public GameGraphics() {
            background=new DynamicBackground();


            Timer timer=new Timer("animation");
            timer.scheduleAtFixedRate(new TimerTask() {
                public void run() {
                    repaint();
                }
            }, 10, (int)(1000.0/60));
        }

        public void DrawPanel() {
            URL resource = getClass().getResource("pixil-layer-Background.png");
            try {
                image = ImageIO.read(resource);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void paint(Graphics g) {
            Graphics2D g2=(Graphics2D)g;
            super.paint(g);


            if(firstTime) {
                canvasWidth=getWidth();
                canvasHeight=getHeight();

                for(int i=0; i<getHeight()/2; i++) {
                    background.draw(g2, false);
                }
                this.player.setWidth(600);
                this.player.setHeight(600);
                this.player.setX(getWidth()/2);
                this.player.setY(getHeight()/2);

                firstTime=false;
            }

            background.draw(g2, true);

            player.draw(g2);
        }
    }
}
