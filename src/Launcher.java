import javax.swing.*;

public class Launcher {
    public static final boolean DEBUG_MODE=false;

    private static Launcher instance;
    public static GameGUI gui;
    public static String playerName;
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
        playerName=JOptionPane.showInputDialog(new JFrame(), "Please enter your name for leaderboard entries");
        gui = new GameGUI();
    }
}
