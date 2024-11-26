import javax.swing.*;

import java.awt.*;
import java.util.List;

public class AdminLihatTransaksi extends JPanel {
    private JPanel cardPanel;
    private CardLayout cardLayout;
    private JFrame frame;

    public AdminLihatTransaksi (JPanel cardPanel, CardLayout cardLayout, JFrame frame){
        this.cardPanel = cardPanel;
        this.cardLayout = cardLayout;
        this.frame = frame;

        BackgroundPanel backgroundPanel = new BackgroundPanel("assets\\background.jpg");
        backgroundPanel.setImageSize(850, 850);
        backgroundPanel.setImagePosition(220, -70);
        backgroundPanel.setLayout(new GridBagLayout());

        RoundedPanel containerPanel = new RoundedPanel(30, 30);
        containerPanel.setPreferredSize(new Dimension(800, 600));
        containerPanel.setBackground(Color.WHITE);
        containerPanel.setLayout(new BoxLayout(containerPanel, BoxLayout.Y_AXIS));
        containerPanel.setBorderColor(Color.LIGHT_GRAY);
        containerPanel.setBorderThickness(1);

        containerPanel.add(Box.createRigidArea(new Dimension(0, 25)));
        
        JLabel WelcomeLabel = new JLabel("Daftar Transaksi");
        WelcomeLabel.setFont(new Font("Arial", Font.BOLD, 30));
        WelcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        WelcomeLabel.setForeground(new Color(65, 195, 100));
        containerPanel.add(WelcomeLabel);
        containerPanel.add(Box.createRigidArea(new Dimension(0, 23)));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        backgroundPanel.add(containerPanel, gbc);
        setLayout(new BorderLayout()); 
        add(backgroundPanel, BorderLayout.CENTER);
    }
}
