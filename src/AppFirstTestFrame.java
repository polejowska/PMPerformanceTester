import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class AppFirstTestFrame extends JFrame{

    private final ArrayList<JPanel> panels = new ArrayList<>();

    AppInformationFrame appSecondInformationFrame;

    private JLabel headerLabel;
    private float counter;
    private static Boolean trainingPhase = true;

    private Timer timer = new Timer();

    public AppFirstTestFrame() {
        super.setTitle("Psychomotor Performance Tester | First test");
        initUI();

        trainingPhase = true;
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

        FirstTestPanel panel1 = new FirstTestPanel();
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

        panel1.setBackground(Color.BLACK);

        this.add(panel1, BorderLayout.CENTER);
        this.add(panel2, BorderLayout.NORTH);
        this.add(panel3, BorderLayout.SOUTH);
    }

    public void createInformationHeader() {
        headerLabel = new JLabel("First test - training phase", JLabel.LEFT);
        headerLabel.setFont(new Font("Calibri", Font.BOLD, 30));
        headerLabel.setBorder(new EmptyBorder(40,0,0,0));

        panels.get(1).add(headerLabel);
    }

    public void addButtons(ArrayList<JPanel> panels) {
        StyledButtonUI.setDesign(2);
        JButton nextButton = new JButton(" NEXT ");
        JButton gameButton = new JButton("    ");
        JButton returnButton = new JButton("RETURN");
        nextButton.setUI(new StyledButtonUI());
        gameButton.setUI(new StyledButtonUI());
        returnButton.setUI(new StyledButtonUI());

        nextButton.addActionListener(e -> {
            if(trainingPhase) {
                JOptionPane.showMessageDialog(AppFirstTestFrame.this, "Now, you will be examined.");
                trainingPhase = false;
                addClock();
            }
            else {
                appSecondInformationFrame = new AppInformationFrame("Second");
                appSecondInformationFrame.setVisible(true);
                AppFirstTestFrame.this.dispose();
            }
        });

        gameButton.addActionListener(e -> {

        });

        returnButton.addActionListener(e -> {
            AppInformationFrame appInformationFrame = new AppInformationFrame("First");
            appInformationFrame.setVisible(true);
            AppFirstTestFrame.this.dispose();
        });

        panels.get(2).add(returnButton);
        panels.get(2).add(Box.createHorizontalStrut(200));
        panels.get(2).add(Box.createHorizontalStrut(200));
        panels.get(2).add(nextButton);
    }

    private void addClock() {
        headerLabel.setText("Time: ");
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                counter++;
                if(counter > 60) {
                    appSecondInformationFrame = new AppInformationFrame("Second");
                    appSecondInformationFrame.setVisible(true);
                    AppFirstTestFrame.this.dispose();
                    timer.cancel();
                }
                headerLabel.setText("Time: " + (int)counter + " s " + " Points: " + FirstTestPanel.getPoints());
            }
        }, 1000, 1000);
    }

    public static Boolean getTrainingPhase() {
        return trainingPhase;
    }


}
