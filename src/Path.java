import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class Path extends ArrayList<DoublePoint> {
    private static TreeMap<String, Path> loaded=new TreeMap<>();

    private String name="";

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
    public void join(Path other) {
        DoublePoint connector=null;
        double offsetX=0;
        double offsetY=0;
        for(int i=0; i<other.size(); i++) {
            if(i==0) {
                connector=other.get(i);
                offsetX=get(size()-1).getX()-other.get(i).getX();
                offsetY=get(size()-1).getY()-other.get(i).getY();
            }

            if(connector!=null&&i!=0) {
                add(new DoublePoint(other.get(i).getX()+offsetX, other.get(i).getY()+offsetY));
            }
        }
    }
    public void translate(int baseX, int baseY) {
        translate((double)baseX/GameGUI.canvasWidth, (double)baseY/GameGUI.canvasHeight);
    }
    public void translate(double baseX, double baseY) {
        double offsetX=baseX-get(0).getX();
        double offsetY=baseY-get(0).getY();

        ArrayList<DoublePoint> newList=new ArrayList<DoublePoint>();
        for(DoublePoint point : this) {
            DoublePoint newPoint=new DoublePoint(0, 0);
            newPoint.setX(point.getX()+offsetX);
            newPoint.setY(point.getY()+offsetY);
            newList.add(newPoint);
        }
        clear();
        addAll(newList);
    }
    public Point getRealPoint(int index) {
        return new Point((int)(get(index).getX()*GameGUI.canvasWidth), (int)(get(index).getY()*GameGUI.canvasHeight));
    }


    public static Path load(String name) {
        if(loaded.containsKey(name)) {
            return (Path)(loaded.get(name).clone());
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
        out.setName(name);

        return out;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Path clone() {
        Path out=new Path();
        for(DoublePoint point : this) {
            out.add(new DoublePoint(point.getX(), point.getY()));
        }
        return out;
    }
}
