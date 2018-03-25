//A+ Computer Science  -  www.apluscompsci.com
//Names - Amber, Akash, Arjun, Connor, Nihal, Narendhar, Sonia
//Date - 3/24/18
//Class - AP Comp Sci Period 7
//Lab  - Galaga
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

// Not used
// Could be used in the future for startup
public class TitleScreen extends JFrame implements KeyListener {


    JPanel panel = new JPanel();
    JLabel label = new JLabel();
    JLabel label2 = new JLabel();

    public TitleScreen() { //Constructor for the class
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 700);
        setBackground(Color.BLACK);
        setResizable(false);
        this.addKeyListener(this);
        label.setText("GALAGA");
        label.setBackground(Color.BLACK);
        panel.setBackground(Color.BLACK);
        label.setFont(new Font("Courier New", Font.BOLD, 75));
        label.setForeground(Color.WHITE);
        label2.setFont(new Font("Courier New", Font.BOLD, 50));
        label2.setText("Press enter to play");
        label2.setForeground(Color.WHITE);
        this.setTitle("SPACE INVADERS");



        panel.add(label);
        panel.add(label2);


        add(panel);

        setVisible(true);

    }

    // Handle when the user presses a key (specifically enter)
    public void keyPressed(KeyEvent e) {
        //Enter
        if(e.getKeyCode() == KeyEvent.VK_ENTER)
        {
            this.dispose();
            GameGUI frame = new GameGUI();
            Launcher.gui=frame;
            frame.setVisible(true);
        }

    }

    // Dummy interface implementation
    public void keyReleased(KeyEvent e) {

    }
    // Dummy interface implementation
    public void keyTyped(KeyEvent e) {

    }
}
