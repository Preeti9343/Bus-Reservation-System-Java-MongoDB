package ui;

import dao.UserDAO;
import model.User;

import javax.swing.*;
import java.awt.*;

public class RegisterFrame extends JPanel {

    private static final long serialVersionUID = 1L;


    private MainFrame frame;

    public RegisterFrame(MainFrame frame) {

        this.frame = frame;

        setLayout(new GridLayout(5,2,10,10));
        setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        JTextField nameField = new JTextField();
        JTextField emailField = new JTextField();
        JPasswordField passField = new JPasswordField();

        JButton registerBtn = new JButton("Register");
        JButton backBtn = new JButton("Back to Login");

        registerBtn.addActionListener(e -> {

            if(nameField.getText().trim().isEmpty() ||
               emailField.getText().trim().isEmpty() ||
               passField.getPassword().length == 0) {

                JOptionPane.showMessageDialog(this,"All fields required!");
                return;
            }

            try {

                User user = new User(
                        nameField.getText().trim(),
                        emailField.getText().trim(),
                        new String(passField.getPassword())
                );

                new UserDAO().registerUser(user);

                JOptionPane.showMessageDialog(this,"Registration Successful!");
                frame.showScreen("login");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        });

        backBtn.addActionListener(e -> {
            frame.showScreen("login");
        });

        add(new JLabel("Name"));
        add(nameField);

        add(new JLabel("Email"));
        add(emailField);

        add(new JLabel("Password"));
        add(passField);

        add(registerBtn);
        add(backBtn);
    }
}
