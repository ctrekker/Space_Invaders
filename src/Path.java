import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.TreeMap;

/*
INCREDIBLY POWERFUL CLASS!!
This wonderful class is responsible for every single path the ships take in the game.
If you play a few times, you will see how variant each paths are, and how seemingly random, yet
controlled they are. This presents a logistical issue, which is hardcoding many unique paths that
look good, are functional, and don't take up too much space. See the following methods (specifically
load()) to check out some pure power!

Pretty awesome stuff!
 */
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
    // Joins two paths together based on start and end point
    public Path join(Path other) {
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

        return this;
    }
    // Reverses the order of the points
    // NOTE: This does NOT reverse the direction the path is actually FACING. To change FACING direction,
    //       pre-processing techniques must be used.
    public Path reverse() {
        Path newPath=new Path();
        for(DoublePoint point : this) {
            newPath.add(0, new DoublePoint(point.getX(), point.getY()));
        }
        clear();
        for(DoublePoint p : newPath) {
            add(p);
        }

        return this;
    }
    // Translates a path based on a SCALED coordinate
    public Path translate(int baseX, int baseY) {
        return translate((double)baseX/GameGUI.canvasWidth, (double)baseY/GameGUI.canvasHeight);
    }
    // Translate a path based on a RELATIVE coordinate
    public Path translate(double baseX, double baseY) {
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
        for(DoublePoint p : newList) {
            add(p);
        }

        return this;
    }
    // Scales all path coordinates by sX and sY ratios.
    // NOTE: This is relative to (0, 0). To scale from anywhere else, this method must be used in conjunction with translate
    public Path scale(double sX, double sY) {
        for(int i=0; i<size(); i++) {
            DoublePoint mod=get(i);
            mod.setX(mod.getX()*sX);
            mod.setY(mod.getY()*sY);
            set(i, mod);
        }

        return this;
    }
    // De-resolution - Converts high-res to low-res
    // Essentially just removes certain points so the path is less choppy
    public Path deres(int degree) {
        for(int i=0; i<size(); i++) {
            if(i!=0&&i!=size()-1) {
                if(i%degree==0) {
                    remove(i);
                }
            }
        }

        return this;
    }
    // Pops off the last point ONLY
    public Path pop() {
        return pop(1);
    }
    // Pops off the last n points
    public Path pop(int num) {
        for(int i=0; i<num; i++) {
            remove(size()-1);
        }

        return this;
    }

    // Returns a Point representing their actual screen coordinates, rather than a ratio
    public Point getRealPoint(int index) {
        return new Point((int)(get(index).getX()*GameGUI.canvasWidth), (int)(get(index).getY()*GameGUI.canvasHeight));
    }

    /*
    This class, in essence, moves a stream of binary data, written under v1.0 of this program's standards, into RAM
    in a parsed format, ready to be used.
    Unfortunately, I don't have the time to write standards on this entire thing, but essentially, this is how it works:

    1. Check and make sure the resource being requested (by string id) isn't already cached
    2a. If it isn't, then...
        i.   A specific format of .dat file which stores doubles in pairs of twos is loaded into memory
        ii.  The file is parsed, and loaded into a Path object
        iii. The newly formed Path object is cached in TreeMap, so accessing by association is faster
    2b. If it is, then...
        i.   Request the Path object by reference of String object
    3. Return a CLONE of the Path object. If passed by reference, custom changes would result in corrupted
       memory in the cache.
     */
    public static Path load(String name) {
        if(loaded.containsKey(name)) {
            return loaded.get(name).clone();
        }

        Path out=new Path();

        try {
            DataInputStream in=new DataInputStream(
                    new BufferedInputStream(
                            Launcher.getInstance().getClass().getResourceAsStream("/res/path/"+name+".dat")));

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
        catch(Exception e) {
            System.out.println("Missing resource with name of \""+name+"\"");
        }
        out.setName(name);

        return out.clone();
    }

    // Gets the name saved corresponding to this particular path
    public String getName() {
        return name;
    }
    // Sets the name
    public void setName(String name) {
        this.name = name;
    }

    // Clones the Path into a new place in memory, as well as a new corresponding reference
    @Override
    public Path clone() {
        super.clone();
        Path out=new Path();
        for(DoublePoint point : this) {
            out.add(new DoublePoint(point.getX(), point.getY()));
        }
        return out;
    }
}
