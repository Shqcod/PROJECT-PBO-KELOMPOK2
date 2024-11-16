import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import java.awt.*;

public class LoginInterface extends JFrame {
    public LoginInterface() {
        setTitle("TOKO ONLINE");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(MAXIMIZED_BOTH);
        setLocationRelativeTo(null);

        setIconImage(new ImageIcon("assets\\mainIcon.png").getImage());

        // Menggunakan BackgroundPanel untuk menampilkan gambar
        BackgroundPanel backgroundPanel = new BackgroundPanel("assets\\background.jpg"); // Ganti dengan path gambar Anda
        backgroundPanel.setImageSize(850,850);
        backgroundPanel.setImagePosition(220, -70);
        backgroundPanel.setLayout(new GridBagLayout()); // Gunakan GridBagLayout

        // Membuat panel untuk form login
        JPanel formPanel = new JPanel();
        formPanel.setPreferredSize(new Dimension(270, 360)); // Ukuran panel tetap
        formPanel.setBackground(Color.WHITE);
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        // Menambahkan komponen ke panel form
        JLabel titleLabel = new JLabel("Masuk ke Aplikasi");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        String[] roles = {"User", "Admin"};
        JComboBox<String> roleComboBox = new JComboBox<>(roles);
        roleComboBox.setMaximumSize(new Dimension(200, 30));
        roleComboBox.setAlignmentX(Component.CENTER_ALIGNMENT);
       

        JTextField usernameField = new JTextField("Masukkan username anda");
        usernameField.setPreferredSize(new Dimension(200, 35));
        usernameField.setMaximumSize(new Dimension(200, 35));
        usernameField.setForeground(Color.GRAY);
        usernameField.setFont(new Font("Arial", Font.ITALIC, 12));
        usernameField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2, true),
            new EmptyBorder(5, 10, 5, 10)
        ));

         //MENGHILANGKAN TULISAN MASUKKAN EMAIL SAAT ADA KURSOR
         usernameField.addFocusListener(new FocusAdapter(){
            @Override
            public void focusGained(FocusEvent e){
                if (usernameField.getText().equals("Masukkan email")){
                    usernameField.setText("");
                    usernameField.setForeground(Color.BLACK);
                    usernameField.setFont(new Font("Arial", Font.PLAIN, 12));
                }
            }
        
            @Override
            public void focusLost(FocusEvent e){
                if(usernameField.getText().isEmpty()){
                    usernameField.setForeground(Color.GRAY);
                    usernameField.setFont(new Font("Arial", Font.ITALIC, 12));
                    usernameField.setText("Masukkan email");

                }
            }
        });


        // PEMBUATAN PASSWORD FIELD
        JPasswordField passwordField = new JPasswordField("Masukkan password anda");
        passwordField.setPreferredSize(new Dimension(200, 35));
        passwordField.setMaximumSize(new Dimension(200, 35));
        passwordField.setForeground(Color.GRAY);
        passwordField.setFont(new Font("Arial", Font.ITALIC, 12));
        passwordField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2, true),
            new EmptyBorder(5, 10, 5, 10)
        ));

        passwordField.setEchoChar((char) 0);

        //MENGHILANGKAN TULISAN MASUKKAN PASSWORD SAAT D    
        passwordField.addFocusListener(new FocusAdapter(){
            @Override
            public void focusGained(FocusEvent e){
                if (String.valueOf(passwordField.getPassword()).equals("Masukkan Password")){
                    passwordField.setText("");
                    passwordField.setForeground(Color.BLACK);
                    passwordField.setFont(new Font("Arial", Font.PLAIN, 12));
                    passwordField.setEchoChar('*');
                }
            }

            @Override
            public void focusLost(FocusEvent e){
                if(String.valueOf(passwordField.getPassword()).isEmpty()){
                    passwordField.setForeground(Color.GRAY);
                    passwordField.setFont(new Font("Arial", Font.ITALIC, 12));
                    passwordField.setText("Masukkan Password");
                    passwordField.setEchoChar((char) 0);
                }
            }

        });

        // PEMBUATAN TOMBOL LOGIN
        JButton loginButton = new JButton("Login");
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginButton.setPreferredSize(new Dimension(200, 35));
        loginButton.setMaximumSize(new Dimension(200, 35));

        // PEMBUATAN TULISAN "BELUM PUNYA AKUN"
        JLabel noAccountLabel = new JLabel("Belum punya akun?");
        noAccountLabel.setFont(new Font("Arial", Font.PLAIN, 10));
        noAccountLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        formPanel.add(noAccountLabel);
        formPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // PEMBUATAN TOMBOL DAFTAR
        JButton registerButton = new JButton("Daftar");
        registerButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Menambahkan komponen ke dalam form panel
        formPanel.add(titleLabel);
        formPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        formPanel.add(roleComboBox);
        formPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        formPanel.add(usernameField);
        formPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        formPanel.add(passwordField);
        formPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        formPanel.add(loginButton);
        formPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        formPanel.add(noAccountLabel);
        formPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        formPanel.add(registerButton);

        // Menambahkan panel form ke background panel
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER; // Posisikan di tengah
        backgroundPanel.add(formPanel, gbc);

        // Menambahkan background panel ke frame utama
        add(backgroundPanel);
        setFocusable(true);
        requestFocusInWindow();

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LoginInterface::new);
    }
}
