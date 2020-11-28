import javax.swing.*;
import java.awt.*;

public class FirstTest extends JFrame {

    public FirstTest() {
        super.setTitle("Psychomotor Performance Tester | First Test");

        initUI();
    }

    private void initUI() {
        this.getContentPane().setBackground(Color.WHITE);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setSize(800, 700);
    }

}
