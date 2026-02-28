package ui;

import dao.UserDAO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class LoginFrame extends JPanel {

    private static final long serialVersionUID = 1L;

    private JTextField emailField;
    private JPasswordField passwordField;
    private MainFrame frame;

    public LoginFrame(MainFrame frame) {

        this.frame = frame;

        setLayout(new GridBagLayout());
        setBackground(new Color(18, 18, 30));

        JPanel card = new JPanel();
        card.setPreferredSize(new Dimension(350, 350));
        card.setBackground(Color.WHITE);
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(new EmptyBorder(25, 35, 25, 35));

        JLabel title = new JLabel("LOGIN");
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        emailField = new JTextField();
        passwordField = new JPasswordField();

        styleField(emailField);
        styleField(passwordField);

        JButton loginBtn = new JButton("LOGIN");
        JButton registerBtn = new JButton("CREATE ACCOUNT");

        stylePrimaryButton(loginBtn);
        styleSecondaryButton(registerBtn);

        loginBtn.addActionListener(e -> login());

        registerBtn.addActionListener(e -> {
            frame.showScreen("register");
        });

        card.add(title);
        card.add(Box.createVerticalStrut(25));
        card.add(new JLabel("Email"));
        card.add(emailField);
        card.add(Box.createVerticalStrut(15));
        card.add(new JLabel("Password"));
        card.add(passwordField);
        card.add(Box.createVerticalStrut(25));
        card.add(loginBtn);
        card.add(Box.createVerticalStrut(10));
        card.add(registerBtn);

        add(card);
    }

    private void styleField(JTextField field) {
        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setBorder(BorderFactory.createLineBorder(new Color(200,200,200), 1));
    }

    private void stylePrimaryButton(JButton button) {
        button.setBackground(new Color(0, 120, 215));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);
        button.setContentAreaFilled(true);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
        button.setFont(new Font("Segoe UI", Font.BOLD, 15));
    }

    private void styleSecondaryButton(JButton button) {
        button.setBackground(new Color(230, 230, 230));
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        button.setFont(new Font("Segoe UI", Font.PLAIN, 13));
    }

    private void login() {

        String email = emailField.getText().trim();
        String password = new String(passwordField.getPassword());

        if(email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this,"Enter Email & Password");
            return;
        }

        boolean ok = new UserDAO().loginUser(email, password);

        if (ok) {
            frame.setLoggedInEmail(email);
            frame.showScreen("dashboard");
        } else {
            JOptionPane.showMessageDialog(this, "Invalid Credentials");
        }
    }

}
