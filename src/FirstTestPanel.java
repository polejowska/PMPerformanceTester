import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class FirstTestPanel extends JPanel implements ActionListener {

    final int PANEL_WIDTH = 800;
    final int PANEL_HEIGHT = 480;

    private String [] imgPaths = {"circle_blue.png", "circle_green.png", "circle_orange.png", "circle_red.png" };
    private BufferedImage circle;

    Timer timer;

    int xSpeed = 1;
    int ySpeed = 1;
    int x = 0;
    int y = 100;

    FirstTestPanel() {
        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        this.setBackground(Color.BLACK);

        try {
            URL url = getClass().getResource(imgPaths[2]);
            File file = new File(url.getPath());
            circle = ImageIO.read(file);
        } catch (IOException ex) {
            System.out.println(ex);
        }

        timer = new Timer(1, this);
        timer.start();

    }

    public void paint(Graphics g) {
        super.paint(g);


        Graphics2D g2D = (Graphics2D) g;
        g2D.drawImage(circle, x, y, null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(x >= PANEL_WIDTH - circle.getWidth(null) || x < 0) {
            xSpeed = xSpeed * -1;
        }

        if(y >= PANEL_HEIGHT - circle.getHeight(null) || y < 0) {
            ySpeed = ySpeed * -1;
        }

        x = x + xSpeed;
        y = y + ySpeed;

        repaint();
    }

}
