public class Launcher {
    public static final boolean DEBUG_MODE=false;

    private static Launcher instance;
    public static GameGUI gui;
    public static void main(String[] args) throws Exception  {
        instance=new Launcher();
        instance.launch();
    }
    public static Launcher getInstance() {
        return instance;
    }

    public Launcher() {

    }

    //This method runs the game gui which runs the gui which runs the all of the graphics.
    public void launch() {
        //ResourceLoader.init();
        gui = new GameGUI();
        //TitleScreen run = new TitleScreen();


        // TEST#1
        // Tests for inclusive range of Util.random method
//        for(int i=0; i<1000; i++) {
//            if(Util.random(200, 255)==255) System.out.println("True-255");
//            if(Util.random(200, 255)==200) System.out.println("True-255");
//        }
    }
}
