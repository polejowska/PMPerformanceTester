import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.*;
import java.util.Timer;



public class AppThirdTestFrame extends JFrame {

    private final ArrayList<JPanel> panels = new ArrayList<>();
    private static Boolean trainingPhase = true;

    AppResultsFrame appResultsFrame = new AppResultsFrame();

    private JLabel headerLabel;

    private ArrayList<JButton> volumeButtons = new ArrayList<>();
    private ArrayList<AppThirdTestSound> sounds = new ArrayList<>();

    float soundVolume;
    int soundVolClicked;

    java.util.Timer timer = new Timer();
    int counter;

    public AppThirdTestFrame() {
        super.setTitle("Psychomotor Performance Tester | Third test");

        initUI();
        initSound();
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

    private void initSound() {
        for(int i = 0; i <= 10; i++) {
            soundVolume = ((float)11-i)*-1;
            sounds.add(new AppThirdTestSound(soundVolume));
        }

        sounds.get(0).play();
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
        headerLabel = new JLabel("Third test - training phase", JLabel.LEFT);
        headerLabel.setFont(new Font("Calibri", Font.BOLD, 30));
        headerLabel.setBorder(new EmptyBorder(40,0,0,0));

        panels.get(1).add(headerLabel);
    }

    public void addButtons(ArrayList<JPanel> panels) {

        panels.get(0).add(Box.createVerticalStrut(200));

        for(Integer i = 0; i <= 10; i++) {
            volumeButtons.add(new JButton(i.toString()));
            panels.get(0).add(volumeButtons.get(i));


            Integer finalI1 = i;

            volumeButtons.get(i).addActionListener(e -> {

                soundVolClicked = finalI1;

                sounds.get(finalI1).play();

                for(int k = 0; k <= 10; k++) {
                    if (k != finalI1) {
                        sounds.get(k).stop();
                    }
                }

            });

        }

        StyledButtonUI.setDesign(2);
        JButton nextButton = new JButton(" NEXT ");
        JButton okButton = new JButton(" HEAR ");
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
                appResultsFrame.setVisible(true);
                AppThirdTestFrame.this.dispose();
            }
        });

        okButton.addActionListener(e -> {

            Main.setPoints3(11-soundVolClicked);

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
                headerLabel.setText("Time: " + (int)counter + " s " + " Points: " + Main.getPoints3());
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
