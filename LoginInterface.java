import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginInterface extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JComboBox<String> roleComboBox;
    private JButton loginButton;
    private JButton registerButton;
    private JFrame messageLabel;

    public LoginInterface() {
        setTitle("testing");
        setExtendedState(MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10,10,10,10);
        
        JPanel containerPanel = new JPanel();
        containerPanel.setLayout(new BoxLayout(containerPanel, BoxLayout.Y_AXIS));

        JLabel headerLabel = new JLabel("Selamat Datang", JLabel.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        headerLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        containerPanel.add(headerLabel);
        containerPanel.add(Box.createRigidArea(new Dimension(0, 20))); 

        // gbc.gridx = 0;
        // gbc.gridy = 0;
        // gbc.gridwidth = 1;
        // gbc.anchor = GridBagConstraints.NORTH;
        // add(headerLabel, gbc);

        JPanel mainPanel = new JPanel();
        mainPanel.setPreferredSize(new Dimension(300, 400));
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        //Label di dalam panel
        JLabel titleLabel = new JLabel("Masuk ke Tokopaedi");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(titleLabel);

        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        String[] roles = {"User", "Admin"};
        JComboBox<String> roleComboBox = new JComboBox<>(roles);
        roleComboBox.setMaximumSize(new Dimension(200, 30));
        roleComboBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(roleComboBox);

        // INPUT EMAIL
        JTextField emailField = new JTextField("Masukkan email");
        emailField.setPreferredSize(new Dimension(200,35));
        emailField.setMaximumSize(new Dimension(200, 35));
        emailField.setForeground(Color.GRAY);
        emailField.setFont(new Font("Arial", Font.ITALIC, 12));
        emailField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2, true),
            new EmptyBorder(5, 10, 5, 10)
        ));

        emailField.addFocusListener(new FocusAdapter(){
            @Override
            public void focusGained(FocusEvent e){
                if (emailField.getText().equals("Masukkan email")){
                    emailField.setText("");
                    emailField.setForeground(Color.BLACK);
                    emailField.setFont(new Font("Arial", Font.PLAIN, 12));
                }
            }
        
            @Override
            public void focusLost(FocusEvent e){
                if(emailField.getText().isEmpty()){
                    emailField.setForeground(Color.GRAY);
                    emailField.setFont(new Font("Arial", Font.ITALIC, 12));
                    emailField.setText("Masukkan email");

                }
            }
        });

        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // INPUT PASSWORD
        JTextField passwordField = new JTextField("Masukkan Password");
        passwordField.setPreferredSize(new Dimension(200, 35));
        passwordField.setMaximumSize(new Dimension(200, 35));
        passwordField.setForeground(Color.GRAY);
        passwordField.setFont(new Font("Arial", Font.ITALIC, 12));
        passwordField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2, true),
            new EmptyBorder(5, 10, 5, 10)
        ));

        passwordField.addFocusListener(new FocusAdapter(){
            @Override
            public void focusGained(FocusEvent e){
                if (passwordField.getText().equals("Masukkan Password")){
                    passwordField.setText("");
                    passwordField.setForeground(Color.BLACK);
                    passwordField.setFont(new Font("Arial", Font.PLAIN, 12));
                }
            }

            @Override
            public void focusLost(FocusEvent e){
                if(passwordField.getText().isEmpty()){
                    passwordField.setForeground(Color.GRAY);
                    passwordField.setFont(new Font("Arial", Font.ITALIC, 12));
                    passwordField.setText("Masukkan Password");
                }
            }

        });
        

        //emailField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        mainPanel.add(emailField);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(passwordField);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        //Tombol login
        JButton loginButton = new JButton("Login");
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginButton.setPreferredSize(new Dimension(200, 30));
        loginButton.setMaximumSize(new Dimension(200, 30));
        mainPanel.add(loginButton);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        JLabel noAccountLabel = new JLabel("Belum punya akun?");
        noAccountLabel.setFont(new Font("Arial", Font.PLAIN, 10));
        noAccountLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(noAccountLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        //Tombol Register
        JButton registerButton = new JButton("Daftar");
        registerButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(registerButton);
        
        containerPanel.add(mainPanel);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.NORTH;
        add(containerPanel, gbc);

        setFocusable(true);
        requestFocusInWindow();

        setVisible(true);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(LoginInterface::new);
    }
}
