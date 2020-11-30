import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class AppInformationFrame extends JFrame{

    private final ArrayList<JPanel> panels = new ArrayList<>();
    private AppFirstTestFrame appFirstTestFrame;
    private AppSecondTestFrame appSecondTestFrame;
    private final JTextArea jTextArea = new JTextArea();
    private final String number;

    AppInformationFrame(String number) {
        this.number = number;
        super.setTitle("Psychomotor Performance Tester | Information about " + number + " test");

        initUI();
    }

    private void initUI() {
        this.getContentPane().setBackground(Color.WHITE);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setSize(800, 700);

        createPanels();
        createInformationHeader();
        loadTextInfo();
        addButtons(panels);
    }

    private void createPanels() {
        LayoutManager flowLayout = new FlowLayout();
        Border borderLine = BorderFactory.createLineBorder(Color.lightGray);

        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();

        panel2.setPreferredSize(new Dimension(800, 120));
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
        JLabel headerLabel = new JLabel(this.number + " Test Information", JLabel.LEFT);
        headerLabel.setFont(new Font("Calibri", Font.BOLD, 30));
        headerLabel.setBorder(new EmptyBorder(40,0,0,0));

        panels.get(1).add(headerLabel);
    }

    public void loadTextInfo() {
        jTextArea.setBorder(new EmptyBorder(40,0,0,0));
        jTextArea.setFont(new Font("Calibri", Font.PLAIN, 20));
        jTextArea.setHighlighter(null);
        jTextArea.setEditable(false);
        try {
            FileReader fr = new FileReader("src/Resources/Text/" + this.number.toLowerCase() +"_test_info.txt");
            BufferedReader reader = new BufferedReader(fr);

            String line;
            while ((line = reader.readLine()) != null)
            {
                this.jTextArea.append(line + "\n");
            }
        }
        catch (IOException ioe) {
            System.err.println(ioe);
            System.exit(1);
        }

        panels.get(0).add(jTextArea);
    }

    public void addButtons(ArrayList<JPanel> panels) {
        StyledButtonUI.setDesign(1);
        JButton nextButton = new JButton(" NEXT ");
        JButton returnButton = new JButton("RETURN");
        nextButton.setUI(new StyledButtonUI());
        returnButton.setUI(new StyledButtonUI());

        nextButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(AppInformationFrame.this, "Now, you will start the training phase.");
            if(AppFirstTestFrame.getTrainingPhase()) {
                appFirstTestFrame = new AppFirstTestFrame();
                appFirstTestFrame.setVisible(true);
            } else if (AppSecondTestFrame.getTrainingPhase()) {
                appSecondTestFrame = new AppSecondTestFrame();
                appSecondTestFrame.setVisible(true);
            }
            AppInformationFrame.this.dispose();
        });

        returnButton.addActionListener(e -> {
           AppInformationFrame.this.dispose();
           Main.setVisibileStartFrame();
        });

        panels.get(2).add(returnButton);
        panels.get(2).add(Box.createHorizontalStrut(200));
        panels.get(2).add(nextButton);
    }

}
