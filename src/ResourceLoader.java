import java.io.*;

public class ResourceLoader {
    public static void init() {
        InputStream stream=Launcher.getInstance().getClass().getResourceAsStream("resources.txt");
        BufferedReader in=new BufferedReader(
                new InputStreamReader(stream));
        String line;
        try {
            while((line=in.readLine())!=null) {
                String[] lineSplit=line.split(".");
                String extension=lineSplit[lineSplit.length-1];
                String dirName="misc";
                if(extension.equalsIgnoreCase("png")||extension.equalsIgnoreCase("jpeg")) {
                    dirName="img";
                }
                else if(extension.equalsIgnoreCase("dat")) {
                    dirName="path";
                }
                String relativeRoot="res/"+dirName+"/";
                System.out.println(line);
            }
        }
        catch(IOException e) {
            System.out.println("Unable to read resource configurations!");
        }
    }
}
