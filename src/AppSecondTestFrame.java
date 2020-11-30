import javax.imageio.ImageIO;
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
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class AppSecondTestFrame extends JFrame {

    private final ArrayList<JPanel> panels = new ArrayList<>();
    private static Boolean trainingPhase = true;

    AppInformationFrame appThirdInformationFrame = new AppInformationFrame("Third");

    private JLabel headerLabel;
    private BufferedImage imageDigit;

    private String [] digits = {"Resources/Images/2.gif", "Resources/Images/5.gif", "Resources/Images/6.gif",  "Resources/Images/7.gif"};
    JTextField textDigits;

    java.util.Timer timer = new Timer();
    private float counter;

    private int x, y;

    public AppSecondTestFrame() {
        super.setTitle("Psychomotor Performance Tester | Second test");
        initUI();
        try {
            URL url = getClass().getResource("Resources/Images/2.gif");
            File file = new File(url.getPath());
            imageDigit = ImageIO.read(file);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void paint(Graphics g) {
        super.paint(g);

        x = (panels.get(0).getWidth() - imageDigit.getWidth(null)) / 2;
        y = ((panels.get(0).getHeight() - imageDigit.getHeight(null)) / 2) + 150;

        Graphics2D g2D = (Graphics2D) g;
        g2D.drawImage(imageDigit, x, y, null);
    }

    private void initUI() {
        this.getContentPane().setBackground(Color.WHITE);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setSize(800, 700);

        createPanels();
        createInformationHeader();
        addButtons(panels);

        textDigits = new JTextField("Enter the digit here");
        textDigits.setBounds(x-20,y+250, 300,50);
        textDigits.setFont(new Font("Calibri", Font.PLAIN, 30));
        textDigits.setHorizontalAlignment(JTextField.CENTER);
        panels.get(0).add(textDigits);

        textDigits.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                    if (textDigits.getText().length() >= 1) {
                        textDigits.setForeground(Color.BLACK);
                        textDigits.setText("");
                    }
                }

                @Override
                public void focusLost(FocusEvent e) {
                    if (textDigits.getText().isEmpty()) {
                        textDigits.setForeground(Color.GRAY);
                        textDigits.setText("Enter the digit here");
                    }
                }
            });
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
                JOptionPane.showMessageDialog(AppSecondTestFrame.this, "Now, you will be examined.");
                trainingPhase = false;
                addClock();
            }
            else {
                appThirdInformationFrame.setVisible(true);
                AppSecondTestFrame.this.dispose();
            }
        });

        okButton.addActionListener(e -> {
            if(("Resources/Images/"+textDigits.getText()+".gif").equals(digits[0])) {
                JOptionPane.showMessageDialog(AppSecondTestFrame.this, "Correct!");
            }
        });

        returnButton.addActionListener(e -> {
            AppInformationFrame appInformationFrame = new AppInformationFrame("First");
            appInformationFrame.setVisible(true);
            AppSecondTestFrame.this.dispose();
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
        AppSecondTestFrame.trainingPhase = trainingPhase;
    }

    public static Boolean getTrainingPhase() {
        return trainingPhase;
    }

}
