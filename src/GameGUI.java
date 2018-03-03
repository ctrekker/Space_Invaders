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
    private Player player = new Player("name");


    public GameGUI() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 700);
        this.setResizable(false);
        this.addKeyListener(this);
        this.add(new GameGraphics());

        this.setVisible(true);
    }

    
    class GameGraphics extends Component {
        public ShipManager shipManager;
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

        public void paint(Graphics g) {
            Graphics2D g2=(Graphics2D)g;
            super.paint(g);


            if(firstTime) {
                canvasWidth=getWidth();
                canvasHeight=getHeight();

                shipManager=new ShipManager();

                for(int i=0; i<getHeight()/2; i++) {
                    background.draw(g2, false);
                }
                player.setWidth(75);
                player.setHeight(75);
                player.setX(getWidth()/2);
                player.setY(getHeight()-player.getWidth());
                player.setSpeed(3);
                player.setDirection(0, 0);

                // 20 variant 1
                // 16 variant 2
                // 4 variant 3

                // 5 waves, 8 each
                // First wave: top-left=1 go right, top-right=2 go left
                // Second wave: bottom-left=2/3 alternate go right
                // Third wave: bottom-right=2 go left
                // Fourth wave: top-center=1 go left
                // Fifth wave: top-center=1 go right

                firstTime=false;
            }

            shipManager.moveShips();

            background.draw(g2, true);

            player.draw(g2);
            shipManager.drawShips(g2);
        }
    }
    public void keyPressed(KeyEvent e) {
        // Left arrow
        if (e.getKeyCode() == 37 || e.getKeyCode() == 65) {
            player.setDeltaX(-Math.abs(player.getSpeed()));
        }
        // Up arrow
        if (e.getKeyCode() == 38 || e.getKeyCode() == 87 ) {
            player.setDeltaY(-Math.abs(player.getSpeed()));
        }
        // Right arrow
        if (e.getKeyCode() == 39 || e.getKeyCode() == 68 ) {
            player.setDeltaX(Math.abs(player.getSpeed()));
        }
        // Down arrow
        if (e.getKeyCode() == 40 || e.getKeyCode() == 83 ) {
            player.setDeltaY(Math.abs(player.getSpeed()));
        }
        // Space bar
        if (e.getKeyCode() == 32 ) {
            player.shootBullet();
        }
    }


    public void keyReleased(KeyEvent e) {
        // Left arrow or right arrow
        if (e.getKeyCode() == 37 || e.getKeyCode() == 39 || e.getKeyCode() == 65 || e.getKeyCode() == 68 ) {
            player.setDeltaX(0);
        }
        // Up arrow or down arrow
        if (e.getKeyCode() == 38 || e.getKeyCode() == 40 || e.getKeyCode() == 87 || e.getKeyCode() == 83 ) {
            player.setDeltaY(0);
        }
    }

    public void keyTyped(KeyEvent e) {

    }
}
