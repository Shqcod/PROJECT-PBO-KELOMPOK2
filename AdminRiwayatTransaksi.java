import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class AdminRiwayatTransaksi extends JPanel {
    private JPanel cardPanel;
    private CardLayout cardLayout;
    private JFrame frame;
    private JButton btnKembali;
    private AdminTabelTransaksi transaksiTable;

    public AdminRiwayatTransaksi(JPanel cardPanel, CardLayout cardLayout, JFrame frame) {
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

        List<Transaksi> listTransaksi = FileTransaksi.bacaTransaksiDariFile("transaksi.txt");
        transaksiTable = new AdminTabelTransaksi(listTransaksi);
        containerPanel.setBorder(new EmptyBorder(10, 10, 50, 10));
        containerPanel.add(transaksiTable, BorderLayout.CENTER);

        JPanel panelTombol = new JPanel();
        panelTombol.setBackground(Color.WHITE);
        panelTombol.setPreferredSize(new Dimension(200, 100));
        panelTombol.setLayout(new FlowLayout(FlowLayout.CENTER, 1, 5));

        GridBagConstraints gridTombol = new GridBagConstraints();
        gridTombol.fill = GridBagConstraints.HORIZONTAL;
        gridTombol.gridx = 0;
        gridTombol.gridy = 0;
        gridTombol.anchor = GridBagConstraints.WEST;  // Tombol Kembali di kiri
        gridTombol.insets = new Insets(5, 5, 5, 5);

        btnKembali = FormComponents.createInteractButton("Keluar", new Color(65, 195, 100), e -> cardLayout.show(cardPanel, "AdminDashboard"));
        panelTombol.add(btnKembali, gridTombol);

        containerPanel.add(panelTombol, BorderLayout.SOUTH);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        backgroundPanel.add(containerPanel, gbc);
        setLayout(new BorderLayout()); 
        add(backgroundPanel, BorderLayout.CENTER);
    }
}
