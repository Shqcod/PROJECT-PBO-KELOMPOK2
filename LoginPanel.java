import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LoginPanel extends JPanel {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton, registerButton;
    private JComboBox<String> roleComboBox;
    private JPanel cardPanel;
    private CardLayout cardLayout;
    private JFrame frame;
    private Akun currentUser;

    public LoginPanel(JPanel mainPanel, CardLayout cardLayout, JFrame frame) {
        this.frame = frame;
        this.cardPanel = mainPanel;
        this.cardLayout = cardLayout;
        
        BackgroundPanel backgroundPanel = new BackgroundPanel("assets\\background.jpg");
        backgroundPanel.setImageSize(850, 850);
        backgroundPanel.setImagePosition(220, -70);
        backgroundPanel.setLayout(new GridBagLayout());

        RoundedPanel formPanel = new RoundedPanel(30, 30);
        formPanel.setPreferredSize(new Dimension(270, 360));
        formPanel.setBackground(Color.WHITE);
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorderColor(Color.LIGHT_GRAY);
        formPanel.setBorderThickness(1);

        // Komponen Login Form
        formPanel.add(Box.createRigidArea(new Dimension(0, 25)));
        
        // Tambahkan elemen ke formPanel
        JLabel titleLabel = new JLabel("Selamat Datang");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setForeground(new Color(65,195,100));
        formPanel.add(titleLabel);

        formPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        roleComboBox = new JComboBox<>(new String[]{"Admin", "Customer"});
        roleComboBox.setMaximumSize(new Dimension(200, 30));
        formPanel.add(roleComboBox);

        formPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        usernameField = FormComponents.createTextField("Masukkan username");
        formPanel.add(usernameField);
        formPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        passwordField = FormComponents.createPasswordField("Masukkan password");
        formPanel.add(passwordField);
        formPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        loginButton = FormComponents.createButton("Masuk",new Color(65, 195, 100), e -> {
            
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                String role = (String) roleComboBox.getSelectedItem();

                Akun akun = getAccLoginFromFile(username, password, role);

                if (akun != null) {
                    currentUser = akun; // Simpan akun yang sedang login
                    if (role.equals("Admin")) {
                        // Login sebagai Admin
                        AdminDashboard adminDashboard = new AdminDashboard(cardPanel, cardLayout, frame, currentUser);
                        cardPanel.add(adminDashboard, "AdminDashboard");
                        cardLayout.show(cardPanel, "AdminDashboard");
                    } else {
                        // Login sebagai Customer
                        CustomerDashboard customerDashboard = new CustomerDashboard(cardPanel, cardLayout, frame, currentUser);
                        cardPanel.add(customerDashboard, "CustomerDashboard");
                        cardLayout.show(cardPanel, "CustomerDashboard");
                    }
                } else {
                    // Jika login gagal
                    JOptionPane.showMessageDialog(frame, "Akun tidak ditemukan");
                }
            });
            
        formPanel.add(loginButton);

        formPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        JLabel noAccountLabel = new JLabel("Belum punya akun?");
        noAccountLabel.setFont(new Font("Arial", Font.PLAIN, 10));
        noAccountLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        formPanel.add(noAccountLabel);
        
        formPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        registerButton = FormComponents.createButton("Daftar", Color.WHITE, e -> cardLayout.show(mainPanel, "Register"));
        registerButton.setForeground(new Color(65, 195, 100));
        formPanel.add(registerButton);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        backgroundPanel.add(formPanel, gbc);
        setLayout(new BorderLayout()); 
        add(backgroundPanel, BorderLayout.CENTER);
    }

    private Akun getAccLoginFromFile(String username, String password, String role) {
        String fileName = role.equals("Admin") ? "admin.txt" : "customer.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] credentials = line.split(":"); 
                if (credentials[1].equals(username) && credentials[2].equals(password)) {
                    String id = credentials[0];
                    if (role.equals("Admin")) {
                        return new Admin(id, username, password, role);
                    } else {
                        return new Customer(id, username, password, role);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null; // Login gagal
    }
    

    
}
