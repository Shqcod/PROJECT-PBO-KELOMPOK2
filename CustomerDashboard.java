import javax.swing.*;
import java.awt.*;

public class CustomerDashboard extends JPanel {
    private JPanel cardPanel;
    private CardLayout cardLayout;
    private JFrame frame;

    public CustomerDashboard(JPanel cardPanel, CardLayout cardLayout, JFrame frame) {
        this.cardPanel = cardPanel;
        this.cardLayout = cardLayout;
        this.frame = frame;

        setLayout(new BorderLayout());

        JLabel label = new JLabel("Harusnya ini berubah", JLabel.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24));

        JButton viewProductsButton = new JButton("View Products");
        JButton manageCartButton = new JButton("Manage Cart");
        JButton logoutButton = new JButton("Logout");

        viewProductsButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "Feature to view products.");
        });

        manageCartButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "Feature to manage cart.");
        });

        logoutButton.addActionListener(e -> cardLayout.show(cardPanel, "Login"));

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(viewProductsButton);
        buttonPanel.add(manageCartButton);
        buttonPanel.add(logoutButton);

        add(label, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
}
