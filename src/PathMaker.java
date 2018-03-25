import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

/*
Some of those in-game paths seem pretty complex for hardcoding, huh!
Well, turns out they weren't hardcoded! I decided it would be much more
worthwhile to make an actual path EDITOR so I can draw paths and use them
in-game through code.
 */
public class PathMaker extends JFrame {
    public static void main(String[] args) {
        new PathMaker();
    }

    private PathMakerGraphics graphics;
    private ArrayList<DoublePoint> points;
    private int lastPoint=0;

    // Init some frame stuff... all pretty boring so far...
    public PathMaker() {
        setSize(800, 700);
        setTitle("Path Maker for init game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        graphics=new PathMakerGraphics();
        add(graphics);
        addKeyListener(graphics);
        graphics.addMouseListener(graphics);
        graphics.addMouseMotionListener(graphics);

        setVisible(true);
    }

    // Class designated to graphics stuff
    // That pretty much is all of this...
    private class PathMakerGraphics extends Component implements MouseListener, MouseMotionListener, KeyListener {
        private boolean moveMode=false;
        // Constructor
        public PathMakerGraphics() {
            points=new ArrayList<>();
        }
        // Draw on the graphics object based on the data-layer
        public void paint(Graphics g) {
            Graphics2D g2=(Graphics2D) g;

            g2.setColor(Color.BLACK);
            g2.fillRect(0, 0, getWidth(), getHeight());

            g2.setColor(Color.WHITE);
            DoublePoint previous=null;
            for(DoublePoint point : points) {
                int x=(int)(point.getX()*getWidth());
                int y=(int)(point.getY()*getHeight());

                g2.drawArc(x-15, y-15, 30, 30, 0, 360);
                if(previous!=null) {
                    g2.drawLine((int)(previous.getX()*getWidth()), (int)(previous.getY()*getHeight()), x, y);
                }

                previous=point;
            }
        }


        // Dummy method
        @Override
        public void mouseClicked(MouseEvent e) {

        }
        // Respond to any type of mouse click
        // Left click adds a point
        // Middle click (on scrollbar) clears all the points on the screen
        // Right click saves the current path as a file. This part is my favorite ;)
        @Override
        public void mousePressed(MouseEvent e) {
            moveMode=false;
            if(e.getButton()==1) points.add(new DoublePoint((double)e.getX()/getWidth(), (double)e.getY()/getHeight()));
            if(e.getButton()==2) points.clear();
            if(e.getButton()==3) {
                // Do some cool stuff with dat files here
                // Please note that just because dat files are random characters DOES NOT MEAN THEY ARE ENCRYPTED
                // In fact, dat files are almost NEVER encrypted. The reason random characters appear is because
                // editors don't understand that a dat file doesn't just store string data, so it tries to read
                // double, int, float, long, etc. data as a string, which doesn't work out in binary...
                String fileName=JOptionPane.showInputDialog("Enter file save name");
                File file=new File("res/path/"+fileName+".dat");
                DataOutputStream out=null;
                try {
                    out=new DataOutputStream(
                            new BufferedOutputStream(
                                    new FileOutputStream(file, false)));
                }
                catch(FileNotFoundException err) {
                    err.printStackTrace();
                }
                if(out!=null) {
                    for(DoublePoint p : points) {
                        try {
                            out.writeDouble(p.getX());
                            out.writeDouble(p.getY());
                        }
                        catch(IOException err) {
                            err.printStackTrace();
                        }
                    }
                }
                try {
                    out.flush();
                    out.close();
                }
                catch(IOException err) {
                    err.printStackTrace();
                }
                JOptionPane.showMessageDialog(null, "Saved!");
            }
            repaint();
        }
        // Reset the lastPoint count when the mouse is released
        @Override
        public void mouseReleased(MouseEvent e) {
            lastPoint=10;
        }
        // Dummy method
        @Override
        public void mouseEntered(MouseEvent e) {

        }
        // Dummy method
        @Override
        public void mouseExited(MouseEvent e) {

        }
        // Handle mouse drag, so a line can be "drawn" using a drag
        @Override
        public void mouseDragged(MouseEvent e) {
            if(lastPoint%25==0) points.add(new DoublePoint((double)e.getX()/getWidth(), (double)e.getY()/getHeight()));
            lastPoint++;
            repaint();
        }
        // Handle mouse movement, only in certain conditions though.
        // Can translate in pre-processing here
        @Override
        public void mouseMoved(MouseEvent e) {
            if(moveMode) {
                ArrayList<DoublePoint> newList = new ArrayList<>();
                DoublePoint first = null;
                for (DoublePoint p : points) {
                    if (first != null) {
                        newList.add(new DoublePoint(first.getX()+(first.getX()-p.getX()), first.getY()+(first.getY()-p.getY())));
                    } else {
                        first = new DoublePoint((double)e.getX()/getWidth(), (double)e.getY()/getHeight());
                        newList.add(first);
                    }
                }
                points = newList;
            }
            repaint();
        }

        // Dummy method
        @Override
        public void keyTyped(KeyEvent e) {

        }
        // Handle key "shortcuts"
        // x -> reflect shape over x axis
        // y -> reflect shape over y axis
        // m -> activate whole-shape translation mode
        // l -> manually enter a point's relative coordinates
        // NOTE: pressing ctrl or command is not necessary to perform these keystroke actions
        @Override
        public void keyPressed(KeyEvent e) {
            if(e.getKeyChar()=='x') {
                ArrayList<DoublePoint> newList=new ArrayList<>();
                for(DoublePoint p : points) {
                    newList.add(p.getInvertedX());
                }
                points=newList;
            }
            if(e.getKeyChar()=='y') {
                ArrayList<DoublePoint> newList=new ArrayList<>();
                for(DoublePoint p : points) {
                    newList.add(p.getInvertedY());
                }
                points=newList;
            }
            if(e.getKeyChar()=='m') {
                moveMode=true;
            }
            if(e.getKeyChar()=='l') {
                double x=Double.parseDouble(JOptionPane.showInputDialog("X Position"));
                double y=Double.parseDouble(JOptionPane.showInputDialog("Y Position"));
                points.add(new DoublePoint(x, y));
            }

            repaint();
        }
        // Dummy method
        @Override
        public void keyReleased(KeyEvent e) {

        }
    }

    // Simple class (kind of redundant) for storing points as relative doubles
    private class DoublePoint {
        private double x;
        private double y;

        // Constructor
        public DoublePoint(double x, double y) {
            this.x=x;
            this.y=y;
        }
        // Getters and setters
        public double getX() {
            return x;
        }
        public void setX(double x) {
            this.x = x;
        }
        public double getY() {
            return y;
        }
        public void setY(double y) {
            this.y = y;
        }

        // Methods which calculates inverted (reflected) values across certain axis
        public DoublePoint getInvertedX() {
            return new DoublePoint(1-getX(), getY());
        }
        public DoublePoint getInvertedY() {
            return new DoublePoint(getX(), 1-getY());
        }
    }
}
