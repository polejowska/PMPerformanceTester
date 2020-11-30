import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class AppStartFrame extends JFrame {

    private final ArrayList<JPanel> panels = new ArrayList<>();
    private AppInformationFrame appInformationFrame;
    private JButton startButton;
    private JTextArea textInfo;
    private JTextField nameText, surnameText;

    public AppStartFrame() {
        super.setTitle("Psychomotor Performance Tester | Start Menu");
        initUI();
    }

    private void initUI() {
        this.getContentPane().setBackground(Color.WHITE);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setSize(800, 700);

        createPanels();
        createButtons();
        createLabelsAndFields();

        focusSetTextFields();
        startButtonClick();
    }

    private void focusSetTextFields() {
        nameText.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (nameText.getText().length() >= 1) {
                    nameText.setForeground(Color.BLACK);
                    nameText.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (nameText.getText().isEmpty()) {
                    nameText.setForeground(Color.GRAY);
                    nameText.setText("Name");
                }
            }
        });

        surnameText.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (surnameText.getText().length() >= 1) {
                    surnameText.setForeground(Color.BLACK);
                    surnameText.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (surnameText.getText().isEmpty()) {
                    surnameText.setForeground(Color.GRAY);
                    surnameText.setText("Surname");
                }
            }
        });


    }

    private void startButtonClick() {
        startButton.addActionListener(e -> {
            if ((nameText.getText().length() <= 2 || nameText.getText().equals("Name"))
                    && (surnameText.getText().length() <= 2 || surnameText.getText().equals("Surname"))) {
                JOptionPane.showMessageDialog(AppStartFrame.this,
                        "Please, enter valid credentials to start the tests. " + nameText.getText()
                                + " " + surnameText.getText() + " is invalid. ");
            } else {
                Main.setName(nameText.getText());
                Main.setSurname(surnameText.getText());
                appInformationFrame = new AppInformationFrame("First");
                appInformationFrame.setVisible(true);
                AppStartFrame.this.dispose();
            }
        });
    }


    private void createLabelsAndFields() {
        JLabel labelLogo = new JLabel("Psychomotor Performance Tester", JLabel.LEFT);
        labelLogo.setFont(new Font("Calibri", Font.BOLD, 30));
        labelLogo.setBorder(new EmptyBorder(40, 0, 0, 0));

        textInfo = new JTextArea(15, 20);
        textInfo.setFont(new Font("Calibri", Font.PLAIN, 20));
        textInfo.setHighlighter(null);
        textInfo.setEditable(false);
        loadInfoData();

        nameText = new JTextField(20);
        surnameText = new JTextField(20);

        nameText.setBounds(100, 20, 200, 40);
        surnameText.setBounds(100, 20, 200, 40);

        nameText.setFont(new Font("Calibri", Font.PLAIN, 30));
        surnameText.setFont(new Font("Calibri", Font.PLAIN, 30));

        nameText.setHorizontalAlignment(JTextField.CENTER);
        surnameText.setHorizontalAlignment(JTextField.CENTER);

        nameText.setForeground(Color.GRAY);
        surnameText.setForeground(Color.GRAY);

        nameText.setText("Name");
        surnameText.setText("Surname");

        Box container = Box.createVerticalBox();

        container.add(Box.createRigidArea(new Dimension(100, 60)));
        container.add(nameText);
        container.add(Box.createRigidArea(new Dimension(100, 20)));
        container.add(surnameText);
        container.add(Box.createRigidArea(new Dimension(100, 65)));
        container.add(textInfo);

        panels.get(1).add(labelLogo);
        panels.get(0).add(container);
    }

    private void loadInfoData() {
        try {
            FileReader fr = new FileReader("src/Resources/Text/start_info.txt");
            BufferedReader reader = new BufferedReader(fr);

            String line;
            while ((line = reader.readLine()) != null) {
                textInfo.append(line + "\n");
            }
        } catch (IOException ioe) {
            System.err.println(ioe);
            System.exit(1);
        }
    }

    private void createButtons() {
        StyledButtonUI.setDesign(0);

        startButton = new JButton("START");
        startButton.setUI(new StyledButtonUI());
        panels.get(2).add(startButton);
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
}
