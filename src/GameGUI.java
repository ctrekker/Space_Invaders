import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*; // A Pacakge that has already been premade and we can just call them
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
    public Player player = new Player("name");




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

        Path testPath;
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

                testPath=Path.load("ship_locations");

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


            background.draw(g2, true);

            g2.setColor(Color.WHITE);
            g2.setFont(new Font("Arial", Font.PLAIN, 24));
            g2.drawString(GameTracker.Score+"",25, 25 );

            player.draw(g2);
            shipManager.drawShips(g2);
            shipManager.moveShips();

            player.checkBulletCollisions(shipManager);
            player.checkBulletPlayerCollision(shipManager);

            GameTracker.drawLives(g2);

            if(GameTracker.showStage) {
                if(GameTracker.showStageCounter>180) {
                    GameTracker.showStage=false;
                    GameTracker.showStageCounter=0;
                }
                GameTracker.showStageCounter++;

                g2.drawString("STAGE " + GameTracker.stage, GameGUI.canvasWidth/2-50, GameGUI.canvasHeight/2);
            }
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
        // Space bar
        if (e.getKeyCode() == 32 || e.getKeyCode() == 90) {
            player.shootBullet();
        }
    }

    public void keyTyped(KeyEvent e) {

    }
}
