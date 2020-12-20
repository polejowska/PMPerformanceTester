import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.TimerTask;


public class FirstTestPanel extends JPanel implements ActionListener {

    private final int PANEL_WIDTH = 800;
    private final int PANEL_HEIGHT = 480;

    private final String [] imgPaths = {"Resources/Images/circle_blue.png", "Resources/Images/circle_green.png", "Resources/Images/circle.png",
            "Resources/Images/circle_orange.png", "Resources/Images/circle_red.png", "Resources/Images/circle_transparent.png" };
    private String imgPath;
    private BufferedImage circle;

    private Random rand = new Random();

    private Timer timer;
    private String saveTimeState;

    private int xSpeed = 2;
    private int ySpeed = 2;
    private int x = 0;
    private int y = 100;

    FirstTestPanel() {
        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        this.setBackground(Color.BLACK);

        try {
            URL url = getClass().getResource(imgPaths[random()]);
            File file = new File(url.getPath());
            circle = ImageIO.read(file);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        timer = new Timer(100, this);
        timer.start();

        class MyTimerTask extends TimerTask
        {
            public void run()
            {
                try {
                    imgPath = imgPaths[random()];
                    URL url = getClass().getResource(imgPath);
                    File file = new File(url.getPath());
                    circle = ImageIO.read(file);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }

        // Timer for balls color change
        java.util.Timer timer1 = new java.util.Timer();
        TimerTask task = new MyTimerTask();
        timer1.scheduleAtFixedRate(task, 100, 700);

        // Check if clicked correctly
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Point clicked = e.getPoint();
                Rectangle bounds = new Rectangle(x, y, circle.getWidth(), circle.getHeight());
                if (bounds.contains(clicked)) {
                    // If in training phase, show prompts
                     if(AppFirstTestFrame.getTrainingPhase()) {
                         if(imgPath != null && imgPath.equals(imgPaths[4])) {
                            JOptionPane.showMessageDialog(FirstTestPanel.this,"Correct! You have to click the circle" +
                                    " when red circle appears. ");
                        } else {
                             JOptionPane.showMessageDialog(FirstTestPanel.this, "Incorrect! You have to click the circle if and only if" +
                                     " it is red. Try again. ", "Incorrect!", JOptionPane.WARNING_MESSAGE);
                         }
                    } // If not in training phase, update points
                     else {
                         if(imgPath != null && imgPath.equals(imgPaths[4])) {
                             Main.setPoints(10);
                         }
                         else {
                             Main.setPoints(-10);
                         }
                     }
                } // Check if clicked the field outside the ball
                else if (!bounds.contains(clicked)){
                    if(AppFirstTestFrame.getTrainingPhase()) {
                        JOptionPane.showMessageDialog(FirstTestPanel.this, "You have to click on the moving ball!",
                                "Incorrect!", JOptionPane.WARNING_MESSAGE);
                    } else {
                        Main.setPoints(-10);
                    }

                }
            }
        });
    }

    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2D = (Graphics2D) g;
        g2D.drawImage(circle, x, y, null);
    }

    // Change the ball position
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

    public int random() {
        return rand.nextInt(5);
    }

}
