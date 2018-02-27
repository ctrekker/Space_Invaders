import javax.swing.*;
import java.awt.*;

public class GameGUI extends JFrame{
    JPanel panel = new JPanel();
    JLabel label = new JLabel();

    public GameGUI()
    {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800,600);
        this.setResizable(false);
        this.setBackground(Color.WHITE);
        this.setVisible(true);

    }

    public void image()
    {
        panel.add(new JLabel(new ImageIcon("/Users/arjunavinash/IdeaProjects/ArjunJavaSamples/src/StarFighter.png")));
        panel.add(new JLabel(new ImageIcon("/Users/arjunavinash/IdeaProjects/ArjunJavaSamples/src/Alien.jpg")));
        setVisible(true);
        panel.setVisible(true);

    }


}
