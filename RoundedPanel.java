import javax.swing.*;
import java.awt.*;

public class RoundedPanel extends JPanel {
    private int arcWidth;
    private int arcHeight;
    private Color borderColor;
    private int borderThickness;

    public RoundedPanel(int arcWidth, int arcHeight) {
        this.arcWidth = arcWidth;
        this.arcHeight = arcHeight;
        this.borderColor = Color.LIGHT_GRAY; 
        this.borderThickness = 2; 
        setOpaque(false); 
    }

    public void setBorderColor(Color color) {
        this.borderColor = color;
    }

    public void setBorderThickness(int thickness) {
        this.borderThickness = thickness;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g.create();

        // Aktifkan anti-aliasing untuk membuat gambar halus
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Gambar latar belakang dengan sudut melengkung
        g2.setColor(getBackground());
        g2.fillRoundRect(
                borderThickness / 2, 
                borderThickness / 2, 
                getWidth() - borderThickness, 
                getHeight() - borderThickness, 
                arcWidth, 
                arcHeight
        );

        // Gambar border dengan sudut melengkung
        g2.setColor(borderColor);
        g2.setStroke(new BasicStroke(borderThickness));
        g2.drawRoundRect(
                borderThickness / 2, 
                borderThickness / 2, 
                getWidth() - borderThickness, 
                getHeight() - borderThickness, 
                arcWidth, 
                arcHeight
        );

        g2.dispose();
    }
}
