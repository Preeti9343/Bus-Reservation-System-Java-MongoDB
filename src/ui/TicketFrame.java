package ui;

import javax.swing.*;
import java.awt.*;

public class TicketFrame extends JPanel {

    private static final long serialVersionUID = 1L;

    private MainFrame frame;
    private JTextArea ticketArea;

    public TicketFrame(MainFrame frame) {

        this.frame = frame;

        setLayout(new BorderLayout());
        setBackground(new Color(18,18,30));
        setBorder(BorderFactory.createEmptyBorder(30,30,30,30));

        // ===== TITLE =====
        JLabel title = new JLabel("🎟 Ticket Confirmed", SwingConstants.CENTER);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setBorder(BorderFactory.createEmptyBorder(0,0,30,0));

        // ===== TICKET CARD =====
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(new Color(30,30,45));
        card.setBorder(BorderFactory.createEmptyBorder(20,30,20,30));

        ticketArea = new JTextArea();
        ticketArea.setEditable(false);
        ticketArea.setBackground(new Color(30,30,45));
        ticketArea.setForeground(Color.WHITE);
        ticketArea.setFont(new Font("Consolas", Font.PLAIN, 16));
        ticketArea.setBorder(null);

        card.add(ticketArea, BorderLayout.CENTER);

        // ===== BUTTON PANEL =====
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(18,18,30));

        JButton backBtn = new JButton("Back to Dashboard");
        styleButton(backBtn, new Color(79,70,229));

        backBtn.addActionListener(e -> {
            frame.showScreen("dashboard");
        });

        buttonPanel.add(backBtn);

        add(title, BorderLayout.NORTH);
        add(card, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void styleButton(JButton button, Color bg) {
        button.setBackground(bg);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setPreferredSize(new Dimension(220, 45));
    }

    // 🔥 Dynamic Ticket Update
    public void setTicketDetails(String email, String bus, int seat, String name) {

        ticketArea.setText(
                "====================================\n" +
                "            BUS TICKET              \n" +
                "====================================\n\n" +
                "Passenger Name : " + name + "\n\n" +
                "Email          : " + email + "\n\n" +
                "Bus Number     : " + bus + "\n\n" +
                "Seat Number    : " + seat + "\n\n" +
                "Status         : CONFIRMED ✅\n\n" +
                "====================================\n" +
                "       Thank You For Booking        \n" +
                "===================================="
        );
    }
}
