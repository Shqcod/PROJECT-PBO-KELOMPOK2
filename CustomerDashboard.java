import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public class CustomerDashboard extends JPanel {
    private JPanel cardPanel;
    private CardLayout cardLayout;
    private JFrame frame;
    private JButton btnBelanja, btnTransaksi, btnKeluar;
    private Akun currentUser;

    public CustomerDashboard(JPanel cardPanel, CardLayout cardLayout, JFrame frame, Akun currentUser) {
        this.cardPanel = cardPanel;
        this.cardLayout = cardLayout;
        this.frame = frame;
        this.currentUser = currentUser;

        // Background panel dengan gambar
        BackgroundPanel backgroundPanel = new BackgroundPanel("assets\\background.jpg");
        backgroundPanel.setImageSize(850, 850);
        backgroundPanel.setImagePosition(220, -70);
        backgroundPanel.setLayout(new GridBagLayout());

        // Panel menu dengan tombol
        RoundedPanel menuPanel = new RoundedPanel(30, 30);
        menuPanel.setPreferredSize(new Dimension(270, 250));
        menuPanel.setBackground(Color.WHITE);
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        menuPanel.setBorderColor(Color.LIGHT_GRAY);
        menuPanel.setBorderThickness(1);

        menuPanel.add(Box.createRigidArea(new Dimension(0, 25)));

        JLabel welcomeLabel = new JLabel("Selamat Datang" + currentUser.getUsername());
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        menuPanel.add(welcomeLabel);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 25)));

        // Tombol Belanja
        btnBelanja = FormComponents.createDashboardButton("Belanja", new Color(65, 195, 100), e -> {
            DaftarBarangCustomer daftarBarangCustomer = new DaftarBarangCustomer(cardPanel, cardLayout, frame, currentUser);
            cardPanel.add(daftarBarangCustomer, "DaftarBarangCustomer");
            cardLayout.show(cardPanel, "DaftarBarangCustomer");
        });
        btnTransaksi = FormComponents.createDashboardButton("Riwayat Transaksi", new Color(65, 195, 100), e -> {
            DaftarBarangCustomer daftarBarangCustomer = new DaftarBarangCustomer(cardPanel, cardLayout, frame, currentUser);
            cardPanel.add(daftarBarangCustomer, "DaftarBarangCustomer");
            cardLayout.show(cardPanel, "DaftarBarangCustomer");
        });

        menuPanel.add(btnBelanja);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 25)));

        // Tombol Keluar
        btnKeluar = FormComponents.createDashboardButton("Keluar", new Color(65, 195, 100), e -> {
            cardLayout.show(cardPanel, "Login");
        });

        menuPanel.add(btnKeluar);

        // Menambahkan menu panel ke dalam background panel
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        backgroundPanel.add(menuPanel, gbc);

        // Menambahkan background panel ke CustomerDashboard
        setLayout(new BorderLayout());
        add(backgroundPanel, BorderLayout.CENTER);
    }
}
