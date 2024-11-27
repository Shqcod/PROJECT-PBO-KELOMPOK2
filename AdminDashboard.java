import javax.swing.*;
import java.awt.*;

public class AdminDashboard extends JPanel {
    private JPanel cardPanel;
    private CardLayout cardLayout;
    private JFrame frame;
    private JButton btnLihatBarang, btnLihatTransaksi, btnKeluar;

    public AdminDashboard(JPanel cardPanel, CardLayout cardLayout, JFrame frame) {
        this.cardPanel = cardPanel;
        this.cardLayout = cardLayout;
        this.frame = frame;

        BackgroundPanel backgroundPanel = new BackgroundPanel("assets\\background.jpg");
        backgroundPanel.setImageSize(850, 850);
        backgroundPanel.setImagePosition(220, -70);
        backgroundPanel.setLayout(new GridBagLayout());

        RoundedPanel menuPanel = new RoundedPanel(30, 30);
        menuPanel.setPreferredSize(new Dimension(270, 250));
        menuPanel.setBackground(Color.WHITE);
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        menuPanel.setBorderColor(Color.LIGHT_GRAY);
        menuPanel.setBorderThickness(1);

        menuPanel.add(Box.createRigidArea(new Dimension(0, 25)));
        
        JLabel WelcomeLabel = new JLabel("Admin Dashboard");
        WelcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        WelcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        menuPanel.add(WelcomeLabel);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 25)));

        btnLihatBarang = FormComponents.createDashboardButton("Daftar Barang", new Color(65, 195, 100), e -> {
            DaftarBarangAdmin daftarBarangAdmin = new DaftarBarangAdmin(cardPanel, cardLayout, frame);
            cardPanel.add(daftarBarangAdmin, "DaftarBarangAdmin");
            cardLayout.show(cardPanel, "DaftarBarangAdmin");
        });
       
        menuPanel.add(btnLihatBarang);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 25)));

        btnLihatTransaksi = FormComponents.createDashboardButton("Lihat Transaksi", new Color(65, 195, 100), e -> {
            AdminRiwayatTransaksi lihatTransaksi = new AdminRiwayatTransaksi(cardPanel, cardLayout, frame);
            cardPanel.add(lihatTransaksi, "LihatTransaksi");
            cardLayout.show(cardPanel, "LihatTransaksi");
        });

        menuPanel.add(btnLihatTransaksi);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 25)));

        btnKeluar = FormComponents.createDashboardButton("Keluar", new Color(65, 195, 100), e ->{
            cardLayout.show(cardPanel,"Login");
        });

        menuPanel.add(btnKeluar);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        backgroundPanel.add(menuPanel, gbc);
        setLayout(new BorderLayout()); 
        add(backgroundPanel, BorderLayout.CENTER);
    }
}
