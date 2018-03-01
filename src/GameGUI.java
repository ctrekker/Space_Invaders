import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class GameGUI extends JFrame implements KeyListener {
    public static int canvasWidth;
    public static int canvasHeight;
    private BufferedImage image;
    private ArrayList<ArrayList<Ship>> shipGrid = new ArrayList<ArrayList<Ship>>();
    private Player player = new Player("name");


    public GameGUI() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);
        this.setResizable(false);
        this.addKeyListener(this);
        this.add(new GameGraphics());

        this.setVisible(true);
    }
    
    public void addToGrid(int position){
    	shipGrid.get(position).add(new Ship());
    }



    
    class GameGraphics extends Component {
        DynamicBackground background;
        boolean firstTime=true;

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
                player.setWidth(100);
                player.setHeight(100);
                player.setX(getWidth()/2);
                player.setY(getHeight()/2);
                player.setSpeed(5);

                firstTime=false;
            }

            background.draw(g2, true);

            player.draw(g2);
        }
    }
    public void keyPressed(KeyEvent e) {
        System.out.println(e.getKeyCode());
        // Left arrow
        if (e.getKeyCode() == 37 ) {
            player.setDeltaX(-Math.abs(player.getSpeed()));
        }
        // Up arrow
        if (e.getKeyCode() == 38 ) {
            player.setDeltaY(-Math.abs(player.getSpeed()));
        }
        // Right arrow
        if (e.getKeyCode() == 39 ) {
            player.setDeltaX(Math.abs(player.getSpeed()));
        }
        // Down arrow
        if (e.getKeyCode() == 40 ) {
            player.setDeltaY(Math.abs(player.getSpeed()));
        }
    }


    public void keyReleased(KeyEvent e) {
        // Left arrow or right arrow
        if (e.getKeyCode() == 37 || e.getKeyCode() == 39 ) {
            player.setDeltaX(0);
        }
        // Up arrow or down arrow
        if (e.getKeyCode() == 38 || e.getKeyCode() == 40 ) {
            player.setDeltaY(0);
        }
    }

    public void keyTyped(KeyEvent e) {

    }
}
