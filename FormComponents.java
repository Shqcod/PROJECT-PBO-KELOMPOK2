import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class FormComponents {

    public static JTextField createTextField(String placeholder) {
        JTextField textField = new JTextField(placeholder);
        textField.setPreferredSize(new Dimension(200, 35));
        textField.setMaximumSize(new Dimension(200, 35));
        textField.setForeground(Color.GRAY);
        textField.setFont(new Font("Arial", Font.ITALIC, 12));
        textField.setBorder(new RoundedBorder(10, Color.LIGHT_GRAY));
        textField.setMargin(new Insets(0, 0, 0, 0));


        textField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField.getText().equals(placeholder)) {
                    textField.setText("");
                    textField.setForeground(Color.BLACK);
                    textField.setFont(new Font("Arial", Font.PLAIN, 12));
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setText(placeholder);
                    textField.setForeground(Color.GRAY);
                    textField.setFont(new Font("Arial", Font.ITALIC, 12));
                }
            }
        });
        return textField;
    }

    public static JPasswordField createPasswordField(String placeholder) {
        JPasswordField passwordField = new JPasswordField(placeholder);
        passwordField.setPreferredSize(new Dimension(200, 35));
        passwordField.setMaximumSize(new Dimension(200, 35));
        passwordField.setForeground(Color.GRAY);
        passwordField.setFont(new Font("Arial", Font.ITALIC, 12));
        passwordField.setBorder(new RoundedBorder(10, Color.LIGHT_GRAY));
        passwordField.setOpaque(false);
        passwordField.setEchoChar((char) 0);
        passwordField.setMargin(new Insets(0, 0, 0, 0));

        passwordField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (String.valueOf(passwordField.getPassword()).equals(placeholder)) {
                    passwordField.setText("");
                    passwordField.setForeground(Color.BLACK);
                    passwordField.setFont(new Font("Arial", Font.PLAIN, 12));
                    passwordField.setEchoChar('*');
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (String.valueOf(passwordField.getPassword()).isEmpty()) {
                    passwordField.setForeground(Color.GRAY);
                    passwordField.setFont(new Font("Arial", Font.ITALIC, 12));
                    passwordField.setText(placeholder);
                    passwordField.setEchoChar((char) 0);
                }
            }
        });
        return passwordField;
    }

    public static JButton createButton(String text, Color backgroundColor, ActionListener actionListener) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
                super.paintComponent(g);
            }

            @Override
            protected void paintBorder(Graphics g){
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getForeground());
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 30, 30);
            }
        };

        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setPreferredSize(new Dimension(200, 35));
        button.setMaximumSize(new Dimension(200, 35));
        button.setBackground(backgroundColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setOpaque(false);
        button.setBorder(new RoundedBorder(10, Color.LIGHT_GRAY));
        button.addActionListener(actionListener);
        return button;
    }

    public static JButton createInteractButton(String text, Color backgroundColor, ActionListener actionListener){
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
                super.paintComponent(g);
            }

            @Override
            protected void paintBorder(Graphics g){
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getForeground());
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 30, 30);
            }
        };

        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setPreferredSize(new Dimension(200, 35));
        button.setMaximumSize(new Dimension(200, 35));
        button.setBackground(backgroundColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setOpaque(false);
        button.setBorder(new RoundedBorder(10, Color.LIGHT_GRAY));
        button.addActionListener(actionListener);
        return button;
    }
}
