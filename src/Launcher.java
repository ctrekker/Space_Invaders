public class Launcher {
    private static Launcher instance;
    public static void main(String[] args) throws Exception  {
        instance=new Launcher();
        instance.launch();
    }
    public static Launcher getInstance() {
        return instance;
    }

    public Launcher() {

    }
    public void launch() {
        //ResourceLoader.init();
        GameGUI run = new GameGUI();
        //TitleScreen run = new TitleScreen();


        // TEST#1
        // Tests for inclusive range of Util.random method
//        for(int i=0; i<1000; i++) {
//            if(Util.random(200, 255)==255) System.out.println("True-255");
//            if(Util.random(200, 255)==200) System.out.println("True-255");
//        }
    }
}
