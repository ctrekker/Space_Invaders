import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class GameGUI extends JFrame {
    public static int canvasWidth;
    public static int canvasHeight;
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

            if(firstTime) {
                canvasWidth=getWidth();
                canvasHeight=getHeight();

                for(int i=0; i<getHeight()/2; i++) {
                    background.draw(g2, false);
                }

                firstTime=false;
            }

            background.draw(g2, true);
        }
    }
}
