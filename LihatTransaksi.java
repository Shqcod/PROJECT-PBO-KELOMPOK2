import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.util.List;

public class LihatTransaksi extends JPanel {
    private JPanel cardPanel;
    private CardLayout cardLayout;
    private JFrame frame;

    public LihatTransaksi(JPanel cardPanel, CardLayout cardLayout, JFrame frame){
        this.cardPanel = cardPanel;
        this.cardLayout = cardLayout;
        this.frame = frame;
    }
}
