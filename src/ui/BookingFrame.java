package ui;

import dao.BookingDAO;
import model.Booking;

import javax.swing.*;
import java.awt.*;

public class BookingFrame extends JPanel {

    private static final long serialVersionUID = 1L;

    private MainFrame frame;
    private JTextField nameField;

    public BookingFrame(MainFrame frame) {

        this.frame = frame;

        setLayout(new BorderLayout());
        setBackground(new Color(18,18,30));
        setBorder(BorderFactory.createEmptyBorder(30,30,30,30));

        // ===== TITLE =====
        JLabel title = new JLabel("Passenger Details", SwingConstants.CENTER);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setBorder(BorderFactory.createEmptyBorder(0,0,30,0));

        // ===== FORM CARD =====
        JPanel card = new JPanel(new GridLayout(2,1,10,15));
        card.setBackground(new Color(30,30,45));
        card.setBorder(BorderFactory.createEmptyBorder(20,40,20,40));

        JLabel nameLabel = new JLabel("Passenger Name");
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));

        nameField = new JTextField();
        nameField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        nameField.setBorder(BorderFactory.createEmptyBorder(8,10,8,10));

        card.add(nameLabel);
        card.add(nameField);

        // ===== BUTTON PANEL =====
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBackground(new Color(18,18,30));

        JButton backBtn = new JButton("← Back");
        styleButton(backBtn, new Color(107,114,128));

        backBtn.addActionListener(e -> {
            frame.showScreen("seat");
        });

        JButton confirmBtn = new JButton("Confirm Booking");
        styleButton(confirmBtn, new Color(79,70,229));

        confirmBtn.addActionListener(e -> confirmBooking());

        buttonPanel.add(backBtn);
        buttonPanel.add(confirmBtn);

        add(title, BorderLayout.NORTH);
        add(card, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    // 🔥 Button Styling Method
    private void styleButton(JButton button, Color bg) {
        button.setBackground(bg);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setPreferredSize(new Dimension(160, 45));
    }

    private void confirmBooking() {

        System.out.println("Button Clicked");

        if(nameField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this,"Enter Passenger Name");
            return;
        }

        frame.setPassengerName(nameField.getText().trim());

        Booking booking = new Booking(
                frame.getLoggedInEmail(),
                frame.getSelectedBus(),
                frame.getSelectedSeat(),
                frame.getPassengerName()
        );

        try {

            new BookingDAO().saveBooking(booking);

            JOptionPane.showMessageDialog(this,"Booking Successful 🎉");

            frame.updateTicket();
            frame.showScreen("ticket");

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }
}
