import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class Path extends ArrayList<DoublePoint> {
    private static TreeMap<String, Path> loaded=new TreeMap<>();

    public Path() {
        super();
    }
    // This method is ONLY intended for debug purposes
    public void draw(Graphics2D g2) {
        g2.setColor(Color.WHITE);
        Point previous=null;
        for(int i=0; i<this.size(); i++) {
            Point current=getRealPoint(i);
            g2.drawArc(current.x-5, current.y-5, 10, 10, 0, 360);
            if(previous!=null) {
                g2.drawLine(current.x, current.y, previous.x, previous.y);
            }

            previous=current;
        }
    }
    public Point getRealPoint(int index) {
        return new Point((int)(get(index).getX()*GameGUI.canvasWidth), (int)(get(index).getY()*GameGUI.canvasHeight));
    }


    public static Path load(String name) {
        if(loaded.containsKey(name)) {
            return loaded.get(name);
        }

        Path out=new Path();

        String path="res/path/"+name+".dat";
        File file=new File(path);
        try {
            DataInputStream in=new DataInputStream(
                    new BufferedInputStream(
                            new FileInputStream(file)));

            boolean eof=false;
            while(!eof) {
                try {
                    double x=in.readDouble();
                    double y=in.readDouble();
                    out.add(new DoublePoint(x, y));
                }
                catch(IOException e) {
                    eof=true;
                }
            }

            loaded.put(name, out);
        }
        catch(FileNotFoundException e) {
            System.out.println("Missing resource with name of \""+name+"\"");
        }

        return out;
    }
}
