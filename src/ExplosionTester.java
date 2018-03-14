import javax.swing.*;
import java.awt.*;

public class ExplosionTester extends JFrame{
    JPanel panel = new JPanel();
    JLabel label = new JLabel();

    public ExplosionTester(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 500);
        setBackground(Color.BLACK);
        setResizable(false);
    }
}
