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

        JPanel mainPanel = new JPanel();
        mainPanel.setPreferredSize(new Dimension(300, 400));
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        JLabel titleLabel = new JLabel("Masuk ke Tokopaedi");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(titleLabel);

        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // INPUT EMAIL
        JTextField emailField = new JTextField("Masukkan email");
        emailField.setPreferredSize(new Dimension(200,40));
        emailField.setMaximumSize(new Dimension(200, 40));
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
        passwordField.setPreferredSize(new Dimension(200, 40));
        passwordField.setMaximumSize(new Dimension(200, 40));
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

        add(mainPanel);

        setFocusable(true);
        requestFocusInWindow();

        setVisible(true);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(LoginInterface::new);
    }
}
