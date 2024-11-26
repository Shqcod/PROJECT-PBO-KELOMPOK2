import java.io.*;
import javax.swing.*;
import java.awt.*;

public class RegisterPanel extends JPanel {
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField confirmPassField;
    private JComboBox<String> roleComboBox;
    private JButton registerButton;

    public RegisterPanel(CardLayout cardLayout, JPanel mainPanel) {
        this.cardLayout = cardLayout;
        this.mainPanel = mainPanel;
        
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
        
        JLabel titleLabel = new JLabel("Daftar Akun");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        formPanel.add(titleLabel);
        formPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Membuat textField untuk username dan password
        usernameField = FormComponents.createTextField("Masukkan username anda");
        formPanel.add(usernameField);
        formPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        passwordField = FormComponents.createPasswordField("Masukkan password");
        formPanel.add(passwordField);
        formPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        confirmPassField = FormComponents.createPasswordField("Konfirmasi password");
        formPanel.add(confirmPassField);
        formPanel.add(Box.createRigidArea(new Dimension(0, 18)));


        JLabel roleLabel = new JLabel("Daftar sebagai apa?");
        roleLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        roleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        formPanel.add(roleLabel);
        formPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        roleComboBox = new JComboBox<>(new String[]{"Customer", "Admin"});
        roleComboBox.setMaximumSize(new Dimension(200, 30));
        formPanel.add(roleComboBox);
        formPanel.add(Box.createRigidArea(new Dimension(0, 30)));


        registerButton = FormComponents.createButton("Daftar Akun", Color.WHITE, e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            String confirmPass = new String(confirmPassField.getPassword());
            String role = (String) roleComboBox.getSelectedItem().toString();
            String id = generateUniqueId(role);
            // Validasi panjang password
            if (password.length() < 8) {
                JOptionPane.showMessageDialog(this, "Password harus minimal 8 karakter!");
                passwordField.setText("");
                confirmPassField.setText("");
            } else {
                if (!password.equals(confirmPass)){
                    JOptionPane.showMessageDialog(null, "Password dan konfirmasi password tidak cocok");
                    confirmPassField.setText("");
                } else {
                    Akun newAkun = new Akun(id, username, password, role);

                    // Simpan data username dan password sesuai role yang dipilih
                    saveAccountData(newAkun);
                    JOptionPane.showMessageDialog(this, "Akun berhasil dibuat!");
    
                    clearFields();;
                    cardLayout.show(mainPanel, "Login");} 
            
                }
                
        });
        registerButton.setForeground(new Color(65, 195, 100));
        formPanel.add(registerButton);
        formPanel.add(Box.createRigidArea(new Dimension(0, 5)));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        backgroundPanel.add(formPanel, gbc);
        setLayout(new BorderLayout()); 
        add(backgroundPanel, BorderLayout.CENTER);

    }

    private String generateUniqueId(String role){
        String prefix = role.equals("Admin") ? "admin_" : "customer_";
        return prefix = java.util.UUID.randomUUID().toString();
    }

    private void saveAccountData(Akun akun) {
        // Tentukan file berdasarkan role
        String fileName = akun.getRole().equals("Admin") ? "admin.txt" : "customer.txt";
        
        // Membuat file jika belum ada dan disimpan ke folder
        File file = new File(fileName);
        try {
            if (!file.exists()) {
                file.createNewFile();  // Membuat file jika tidak ada
            }

            // Menulis data ke file yang sesuai
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
                writer.write(akun.getUsername() + ":"+ akun.getPassword() + ":" + akun.getRole());
                writer.newLine();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Terjadi kesalahan saat menyimpan data.");
            
        }
    }
    private void clearFields() {
        usernameField.setText("");
        passwordField.setText("");
        roleComboBox.setSelectedIndex(0);  // Menyeting combo box ke pilihan pertama
    }


}