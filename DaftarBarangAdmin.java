import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.util.List;

public class DaftarBarangAdmin extends JPanel {
    private JPanel cardPanel;
    private CardLayout cardLayout;
    private JFrame frame;
    private JButton btnKembali, btnTambah, btnEdit, btnHapus;
    private BarangTablePanel barangTablePanel;

    public DaftarBarangAdmin(JPanel cardPanel, CardLayout cardLayout, JFrame frame) {
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
        
        JLabel WelcomeLabel = new JLabel("Daftar Barang");
        WelcomeLabel.setFont(new Font("Arial", Font.BOLD, 18));
        WelcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        containerPanel.add(WelcomeLabel);
        containerPanel.add(Box.createRigidArea(new Dimension(0, 23)));

        List<Barang> barangList = ListBarang.loadBarangFromFile("barang.txt");
        barangTablePanel = new BarangTablePanel(barangList);
        containerPanel.setBorder(new EmptyBorder(10, 10, 50, 10));
        containerPanel.add(barangTablePanel, BorderLayout.CENTER);

        JPanel panelBawah = new JPanel();
        panelBawah.setBackground(Color.WHITE);
        panelBawah.setPreferredSize(new Dimension(200, 100));
        panelBawah.setLayout(new FlowLayout(FlowLayout.CENTER, 1, 5));

        GridBagConstraints gridTombol = new GridBagConstraints();
        gridTombol.fill = GridBagConstraints.HORIZONTAL;
        gridTombol.gridx = 0;
        gridTombol.gridy = 0;
        gridTombol.anchor = GridBagConstraints.WEST;  // Tombol Kembali di kiri
        gridTombol.insets = new Insets(5, 5, 5, 5);

        btnKembali = FormComponents.createInteractButton("Keluar", e -> cardLayout.show(cardPanel, "AdminDashboard"));
        panelBawah.add(btnKembali, gridTombol);

        gridTombol.gridx = 1;  // Tombol Tambah, Edit, dan Hapus ditempatkan di kolom 1
        gridTombol.anchor = GridBagConstraints.CENTER; // Tombol di tengah
        gridTombol.gridwidth = 1;  // Set gridwidth ke 1 untuk setiap tombol
        gridTombol.insets = new Insets(5, 20, 5, 5);  // Menambah jarak antar tombol

        btnTambah = FormComponents.createInteractButton("Tambah", e -> new MenuBarang.MenuTambahBarang(frame, barangTablePanel, barangList).setVisible(true));
        panelBawah.add(btnTambah, gridTombol);
        
        btnEdit = FormComponents.createInteractButton("Edit", null);
        gridTombol.gridx = 2;  // Tombol Edit di sebelah Tombol Tambah
        panelBawah.add(btnEdit, gridTombol);

        btnHapus = FormComponents.createInteractButton("Hapus", null);
        gridTombol.gridx = 3;  // Tombol Hapus di sebelah Tombol Edit
        panelBawah.add(btnHapus, gridTombol);

        gridTombol.gridx = 4;
        gridTombol.anchor = GridBagConstraints.EAST;
        panelBawah.add(btnHapus, gridTombol);


        containerPanel.add(panelBawah, BorderLayout.SOUTH);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        backgroundPanel.add(containerPanel, gbc);
        setLayout(new BorderLayout()); 
        add(backgroundPanel, BorderLayout.CENTER);
    }
}