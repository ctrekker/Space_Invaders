import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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
        label.setText("GALACTICA");
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

    public void keyPressed(KeyEvent e) {
//Enter
        if(e.getKeyCode() == KeyEvent.VK_ENTER)
        {
            this.dispose();
            GameGUI frame = new GameGUI();
            frame.setVisible(true);
        }

    }


    public void keyReleased(KeyEvent e) {

    }

    public void keyTyped(KeyEvent e) {

    }
}
