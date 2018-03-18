import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

// Class which opens a JFrame when the player dies
// Responsible for showing worldwide leaderboard
public class GameOver extends JFrame implements KeyListener {
    private static boolean alreadyOpen=false;
	private String name;
    JPanel panel = new JPanel();
    JLabel label = new JLabel();
    JLabel label2 = new JLabel();
    JLabel label3 = new JLabel();
    JLabel label4 = new JLabel();

    // Construct a new GameOver window
    public GameOver() { //Constructor for the class
        alreadyOpen=true;
    	
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 700);
        setBackground(Color.BLACK);
        setResizable(false);
        this.addKeyListener(this);
        this.setTitle("Game Over");
        label.setText("Game Over");
        label.setBackground(Color.BLACK);
        panel.setBackground(Color.BLACK);
        label.setFont(new Font("Courier New", Font.BOLD, 75));
        label.setForeground(Color.WHITE);
        
        label2.setFont(new Font("Courier New", Font.BOLD, 50));
        label2.setText("Press enter to play again!");
        label2.setForeground(Color.WHITE);
        
        label3.setFont(new Font("Courier New", Font.BOLD, 30));
        label3.setText("       Your score this round was: " + String.valueOf(GameTracker.Score)+"       ");
        label3.setForeground(Color.WHITE);
        
        label4.setFont(new Font("Courier New", Font.BOLD, 15));

        // Open in a new thread to prevent lag spikes
        new Thread(new Runnable() {
            class LeaderboardEntry {
                public int score;
                public String name;
                public LeaderboardEntry(String name, int score) {
                    this.name=name;
                    this.score=score;
                }

                @Override
                public String toString() {
                    return toString(20);
                }
                public String toString(int filler) {
                    StringBuilder out=new StringBuilder(name);
                    String strScore=""+score;

                    int numSpaces=filler-(name.length()+strScore.length());
                    for(int i=0; i<numSpaces; i++) out.append(".");


                    out.append(strScore);
                    return out.toString();
                }
            }

            /*
            This method requests the leaderboard data from Connor's server
            To see the code there, go to /server/highscores.php of the
            source code. Please note that the leaderboard is written in PHP, not Java.
             */
            @Override
            public void run() {
                String resRaw=HTTPUtils.sendGet("http://ctrekker.mjtrekkers.com/school/Galaga/highscores.php?score="+GameTracker.Score+"&name="+Launcher.gui.player.getName());
                JSONObject res=new JSONObject(resRaw);
                ArrayList<LeaderboardEntry> entries=new ArrayList<LeaderboardEntry>();
                for(int i=0; i<res.getJSONArray("highscores").length(); i++) {
                    JSONObject jsonEntry=res.getJSONArray("highscores").getJSONObject(i);
                    entries.add(new LeaderboardEntry(jsonEntry.getString("name"), jsonEntry.getInt("score")));
                }

                // Yay! Sorting algorithm!
                for(int i=0; i<entries.size(); i++) {
                    int k=i;
                    for(int j=i+1; j<entries.size(); j++) {
                        if(entries.get(j).score>entries.get(k).score) {
                            k=j;
                        }
                    }
                    LeaderboardEntry entry=entries.get(k);
                    entries.remove(k);
                    entries.add(i, entry);
                }

                StringBuilder html=new StringBuilder();
                html.append("<html>");
                html.append("<h1>Leaderboard</h1>");
                for(LeaderboardEntry e : entries) {
                    html.append(e.toString());
                    html.append("<br>");
                }
                html.append("</html>");

                label4.setText(html.toString());

                if(res.getBoolean("scoreAdded")) {
                    JOptionPane.showMessageDialog(null, "Congrats! You made the leaderboard with a score of "+GameTracker.Score+"!");
                }
            }
        }).start();
        label4.setText("Loading...");
        label4.setForeground(Color.WHITE);

        panel.add(label);
        panel.add(label2);
        panel.add(label3);
        panel.add(label4);

        add(panel);

        setVisible(true);
    }

    /*
    These next three methods are for
    the key pressed actions so when
    the game over sign comes up, you need to
    press enter to end the game.
     */
    public void keyPressed(KeyEvent e) {
        //Enter
        if(e.getKeyCode() == KeyEvent.VK_ENTER)
        {
            alreadyOpen=false;
            this.dispose();
            GameTracker.reset();
            GameGUI frame = new GameGUI();
            Launcher.gui=frame;
            frame.setVisible(true);
        }

    }

    // Dummy method
    public void keyReleased(KeyEvent e) {

    }
    // Dummy method
    public void keyTyped(KeyEvent e) {

    }
}
