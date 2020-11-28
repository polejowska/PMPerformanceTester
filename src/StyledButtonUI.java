import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;

class StyledButtonUI extends BasicButtonUI {

    private static int design;

    @Override
    public void installUI (JComponent c) {
        super.installUI(c);
        AbstractButton button = (AbstractButton) c;
        if(design == 0) {
            button.setBorder(new EmptyBorder(25, 120, 25, 120));
        }
        else if (design == 1){
            button.setBorder(new EmptyBorder(15, 25, 15, 25));
        }
        button.setOpaque(false);
        button.setFont(new Font("Calibri", Font.PLAIN, 22));
    }

    @Override
    public void paint (Graphics g, JComponent c) {
        AbstractButton b = (AbstractButton) c;
        paintBackground(g, b, b.getModel().isPressed() ? 2 : 0);
        super.paint(g, c);
    }

    private void paintBackground (Graphics g, JComponent c, int yOffset) {
        Dimension size = c.getSize();
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(c.getBackground().darker());
        g.fillRoundRect(0, yOffset, size.width, size.height - yOffset, 20, 10);
        g.setColor(new Color(242, 242, 242));
        g.fillRoundRect(0, yOffset, size.width, size.height + yOffset - 10, 20, 10);
    }

    public static void setDesign(int design) {
        StyledButtonUI.design = design;
    }

}