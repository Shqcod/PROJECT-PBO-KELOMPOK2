import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Set up the main frame
            JFrame frame = new JFrame("TOKO ONLINE");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);  // Maksimalkan jendela
            frame.setLocationRelativeTo(null);  // Posisikan jendela di tengah layar

            frame.setIconImage(new ImageIcon("assets\\mainIcon.png").getImage());

            // Setup CardLayout untuk LoginPanel
            CardLayout cardLayout = new CardLayout();
            JPanel cardPanel = new JPanel(cardLayout);

            // Tambahkan panel login
            LoginPanel loginPanel = new LoginPanel(cardPanel, cardLayout, frame);
            cardPanel.add(loginPanel, "Login");

            // Tambahkan panel pendaftaran akun
            RegisterPanel registerPanel = new RegisterPanel(cardLayout, cardPanel);
            cardPanel.add(registerPanel, "Register");

            // Menambahkan panel ke frame
            frame.add(cardPanel);
            frame.setVisible(true);
        });
    }
}
