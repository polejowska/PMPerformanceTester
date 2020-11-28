import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class StartFrame extends JFrame {

    private final ArrayList<JPanel> panels = new ArrayList<>();
    private JButton startButton;
    private JTextField nameText, surnameText;

    public StartFrame() {
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

        startButtonClick();
    }

    private void startButtonClick() {

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if((nameText.getText().length() <= 2 || nameText.getText().equals("Name"))
                        && (surnameText.getText().length() <= 2 || surnameText.getText().equals("Surname"))) {
                    JOptionPane.showMessageDialog(StartFrame.this,
                            "Please, enter valid credentials to start the tests. " + nameText.getText()
                                    + " " + surnameText.getText() + " is invalid. ");
                }
                else {
                    Main.setName(nameText.getText());
                    Main.setSurname(surnameText.getText());

                    Main.setVisibleFirstTest();

                }

            }
        });
    }


    private void createLabelsAndFields() {
        JLabel labelLogo = new JLabel("Psychomotor Performance Tester", JLabel.LEFT);
        labelLogo.setFont(new Font("Verdana", Font.PLAIN, 30));
        labelLogo.setBorder(new EmptyBorder(40,0,0,0));
        panels.get(1).add(labelLogo);

        Box container = Box.createVerticalBox();

        nameText = new JTextField(20);
        surnameText = new JTextField(20);

        nameText.setBounds(100, 20, 200, 40);
        surnameText.setBounds(100, 20, 200, 40);

        nameText.setFont(new Font("Verdana", Font.PLAIN, 25));
        surnameText.setFont(new Font("Verdana", Font.PLAIN, 25));

        nameText.setHorizontalAlignment(JTextField.CENTER);
        surnameText.setHorizontalAlignment(JTextField.CENTER);

        nameText.setForeground(Color.GRAY);
        surnameText.setForeground(Color.GRAY);

        nameText.setText("Name");
        surnameText.setText("Surname");

        container.add(Box.createRigidArea(new Dimension(100, 60)));
        container.add(nameText);
        container.add(Box.createRigidArea(new Dimension(100, 20)));
        container.add(surnameText);

        panels.get(0).add(container);
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

        for (JPanel panel: panels) {
            panel.setBackground(Color.WHITE);
            panel.setLayout(flowLayout);
        }

        this.add(panel1, BorderLayout.CENTER);
        this.add(panel2, BorderLayout.NORTH);
        this.add(panel3, BorderLayout.SOUTH);
    }


}
