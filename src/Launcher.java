import javax.swing.*;

// Actual main class used to launch the game itself
public class Launcher {
    // VERY COOL STUFF HERE!
    // This will activate a global "debug mode"
    // which I quite honestly think looks really cool
    public static final boolean DEBUG_MODE=false;

    // Stores an instance of itself, being a singleton by nature
    private static Launcher instance;
    // Stores an instance of the current Game GUI. NOT A SINGLETON
    public static GameGUI gui;
    // The name of the player. Should probably be in the Player instance, but oh well...
    public static String playerName;
    // Main method which launches the game
    public static void main(String[] args) throws Exception  {
        instance=new Launcher();
        instance.launch();
    }
    public static Launcher getInstance() {
        return instance;
    }

    // Constructor which is unused
    public Launcher() {

    }

    //This method runs the game gui which runs the gui which runs the all of the graphics.
    public void launch() {
        //ResourceLoader.init();
        playerName=JOptionPane.showInputDialog(new JFrame(), "Please enter your name for leaderboard entries");
        gui = new GameGUI();
    }
}
