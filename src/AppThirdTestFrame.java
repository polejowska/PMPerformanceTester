import javax.imageio.ImageIO;
import javax.print.attribute.standard.Media;
import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.io.File;
import java.util.Timer;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class AppThirdTestFrame extends JFrame {

    private final ArrayList<JPanel> panels = new ArrayList<>();
    private static Boolean trainingPhase = true;

    AppInformationFrame appThirdInformationFrame = new AppInformationFrame("Third");

    private JLabel headerLabel;

    Clip clip;
    FloatControl gainControl;


    java.util.Timer timer = new Timer();
    int counter;


    public AppThirdTestFrame() {
        playSound();
        super.setTitle("Psychomotor Performance Tester | Third test");
        initUI();

    }

    public void playSound() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(this.getClass().getResource("Resources/Sounds/1_0.wav"));
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);

            gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-10.0f); // Reduce volume by 10 decibels.

            clip.start();
            // If you want the sound to loop infinitely, then put: clip.loop(Clip.LOOP_CONTINUOUSLY);
            // If you want to stop the sound, then use clip.stop();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }



    private void initUI() {
        this.getContentPane().setBackground(Color.WHITE);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setSize(800, 700);

        createPanels();
        createInformationHeader();
        addButtons(panels);


    }


    private void createPanels() {
        LayoutManager flowLayout = new FlowLayout();
        Border borderLine = BorderFactory.createLineBorder(Color.lightGray);

        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();

        panel2.setPreferredSize(new Dimension(800, 100));
        panel3.setPreferredSize(new Dimension(800, 100));

        panel2.setBorder(borderLine);
        panel3.setBorder(borderLine);

        panels.add(panel1);
        panels.add(panel2);
        panels.add(panel3);

        for (JPanel panel : panels) {
            panel.setBackground(Color.WHITE);
            panel.setLayout(flowLayout);
        }

        this.add(panel1, BorderLayout.CENTER);
        this.add(panel2, BorderLayout.NORTH);
        this.add(panel3, BorderLayout.SOUTH);
    }

    public void createInformationHeader() {
        headerLabel = new JLabel("Second test - training phase", JLabel.LEFT);
        headerLabel.setFont(new Font("Calibri", Font.BOLD, 30));
        headerLabel.setBorder(new EmptyBorder(40,0,0,0));

        panels.get(1).add(headerLabel);
    }

    public void addButtons(ArrayList<JPanel> panels) {
        StyledButtonUI.setDesign(2);
        JButton nextButton = new JButton(" NEXT ");
        JButton okButton = new JButton(" OK ");
        JButton returnButton = new JButton("RETURN");
        nextButton.setUI(new StyledButtonUI());
        okButton.setUI(new StyledButtonUI());
        returnButton.setUI(new StyledButtonUI());

        nextButton.addActionListener(e -> {
            if(trainingPhase) {
                JOptionPane.showMessageDialog(AppThirdTestFrame.this, "Now, you will be examined.");
                trainingPhase = false;
                addClock();
                repaint();
            }
            else {
                appThirdInformationFrame.setVisible(true);
                AppThirdTestFrame.this.dispose();
            }
        });

        okButton.addActionListener(e -> {




        });


        returnButton.addActionListener(e -> {
            AppInformationFrame appInformationFrame = new AppInformationFrame("First");
            appInformationFrame.setVisible(true);
            AppThirdTestFrame.this.dispose();
        });

        panels.get(2).add(returnButton);
        panels.get(2).add(Box.createHorizontalStrut(200));
        panels.get(2).add(okButton);
        panels.get(2).add(Box.createHorizontalStrut(200));
        panels.get(2).add(nextButton);
    }

    private void addClock() {
        headerLabel.setText("Time: ");
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                counter++;
                headerLabel.setText("Time: " + (int)counter + " s " + " Points: " + Main.getPoints());
            }
        }, 1000, 1000);
    }

    public void setTrainingPhase(Boolean trainingPhase) {
        AppThirdTestFrame.trainingPhase = trainingPhase;
    }

    public static Boolean getTrainingPhase() {
        return trainingPhase;
    }

}
