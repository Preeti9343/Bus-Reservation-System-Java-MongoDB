package ui;

import dao.BookingDAO;
import org.bson.Document;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MyBookingsFrame extends JPanel {

    private MainFrame frame;
    private JPanel listPanel;

    public MyBookingsFrame(MainFrame frame) {

        this.frame = frame;

        setLayout(new BorderLayout());
        setBackground(new Color(18,18,30));
        setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        // ===== TITLE =====
        JLabel title = new JLabel("My Bookings", SwingConstants.CENTER);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setBorder(BorderFactory.createEmptyBorder(0,0,20,0));

        // ===== LIST PANEL =====
        listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        listPanel.setBackground(new Color(18,18,30));

        JScrollPane scrollPane = new JScrollPane(listPanel);
        scrollPane.setBorder(null);

        // ===== BACK BUTTON =====
        JButton backBtn = new JButton("← Back");
        backBtn.setBackground(new Color(79,70,229));
        backBtn.setForeground(Color.WHITE);
        backBtn.setFocusPainted(false);
        backBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        backBtn.setPreferredSize(new Dimension(150,40));

        backBtn.addActionListener(e ->
                frame.showScreen("dashboard")
        );

        add(title, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(backBtn, BorderLayout.SOUTH);
    }

    @Override
    public void addNotify() {
        super.addNotify();
        loadBookings();
    }

    private void loadBookings() {

        listPanel.removeAll();

        BookingDAO dao = new BookingDAO();
        List<Document> bookings =
                dao.getUserBookings(frame.getLoggedInEmail());

        if (bookings.isEmpty()) {

            JLabel noData = new JLabel("No bookings found.");
            noData.setForeground(Color.WHITE);
            noData.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            noData.setAlignmentX(Component.CENTER_ALIGNMENT);

            listPanel.add(Box.createVerticalStrut(20));
            listPanel.add(noData);
        }

        for (Document doc : bookings) {

            String bookingId = doc.getString("bookingId");
            String bus = doc.getString("busNumber");
            int seat = doc.getInteger("seatNumber");
            String name = doc.getString("passengerName");
            String time = doc.getString("bookingTime");

            JPanel card = new JPanel();
            card.setBackground(new Color(30,30,45));
            card.setLayout(new BorderLayout());
            card.setMaximumSize(new Dimension(Integer.MAX_VALUE,120));
            card.setBorder(BorderFactory.createEmptyBorder(15,15,15,15));

            JLabel info = new JLabel(
                    "<html>" +
                    "<b>Passenger:</b> " + name + "<br>" +
                    "<b>Bus:</b> " + bus + "<br>" +
                    "<b>Seat:</b> " + seat + "<br>" +
                    "<b>Booked At:</b> " + time +
                    "</html>"
            );

            info.setForeground(Color.WHITE);
            info.setFont(new Font("Segoe UI", Font.PLAIN, 14));

            JButton cancelBtn = new JButton("Cancel Booking");
            cancelBtn.setBackground(new Color(239,68,68));
            cancelBtn.setForeground(Color.WHITE);
            cancelBtn.setFocusPainted(false);

            cancelBtn.addActionListener(e -> {

                int confirm = JOptionPane.showConfirmDialog(
                        this,
                        "Are you sure you want to cancel?",
                        "Confirm",
                        JOptionPane.YES_NO_OPTION
                );

                if (confirm == JOptionPane.YES_OPTION) {

                    dao.cancelBooking(bookingId);

                    JOptionPane.showMessageDialog(this,
                            "Booking Cancelled Successfully");

                    loadBookings();
                }
            });

            card.add(info, BorderLayout.CENTER);
            card.add(cancelBtn, BorderLayout.EAST);

            listPanel.add(card);
            listPanel.add(Box.createVerticalStrut(10));
        }

        revalidate();
        repaint();
    }
}