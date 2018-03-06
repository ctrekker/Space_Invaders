import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.util.ArrayList;

public class PathMaker extends JFrame {
    public static void main(String[] args) {
        new PathMaker();
    }

    private PathMakerGraphics graphics;
    private ArrayList<DoublePoint> points;

    public PathMaker() {
        setSize(800, 700);
        setTitle("Path Maker for init game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        graphics=new PathMakerGraphics();
        add(graphics);
        graphics.addMouseListener(graphics);

        setVisible(true);
    }

    private class PathMakerGraphics extends Component implements MouseListener {
        public PathMakerGraphics() {
            points=new ArrayList<>();
        }
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


        @Override
        public void mouseClicked(MouseEvent e) {

        }
        @Override
        public void mousePressed(MouseEvent e) {
            System.out.println(e);
            if(e.getButton()==1) points.add(new DoublePoint((double)e.getX()/getWidth(), (double)e.getY()/getHeight()));
            if(e.getButton()==3) {
                File file=new File("latest_path.dat");
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
        @Override
        public void mouseReleased(MouseEvent e) {

        }
        @Override
        public void mouseEntered(MouseEvent e) {

        }
        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    private class DoublePoint {
        private double x;
        private double y;

        public DoublePoint(double x, double y) {
            this.x=x;
            this.y=y;
        }
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
    }
}
