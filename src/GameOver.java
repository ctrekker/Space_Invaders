import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameOver extends JFrame implements KeyListener {

	private String name;
    JPanel panel = new JPanel();
    JLabel label = new JLabel();
    JLabel label2 = new JLabel();
    JLabel label3 = new JLabel();
    JLabel label4 = new JLabel();

    public GameOver() { //Constructor for the class
//    	name = GameTracker.getName();
//    	GameTracker.addToLeaderboard();
//    	GameTracker.sortScore();
    	
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
        label2.setText("Press enter to play again! \n");
        label2.setForeground(Color.WHITE);
        
        label3.setFont(new Font("Courier New", Font.BOLD, 30));
        label3.setText("Your score this round was: " + String.valueOf(GameTracker.Score));
        label3.setForeground(Color.WHITE);
        
        label4.setFont(new Font("Courier New", Font.BOLD, 15));
        //label3.setText(GameTracker.finalString);
        label3.setForeground(Color.WHITE);

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
            this.dispose();
            GameTracker.reset();
            GameGUI frame = new GameGUI();
            Launcher.gui=frame;
            frame.setVisible(true);
        }

    }


    public void keyReleased(KeyEvent e) {

    }

    public void keyTyped(KeyEvent e) {

    }
}
