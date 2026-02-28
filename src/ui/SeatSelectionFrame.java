package ui;

import dao.BookingDAO;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SeatSelectionFrame extends JPanel {

    private static final long serialVersionUID = 1L;
    private MainFrame frame;

    public SeatSelectionFrame(MainFrame frame) {
        this.frame = frame;
        setLayout(new BorderLayout());
        setBackground(new Color(18,18,30));
    }

    @Override
    public void addNotify() {
        super.addNotify();
        loadSeats();
    }

    private void loadSeats() {

        removeAll();

        // ===== TITLE =====
        JLabel title = new JLabel("Select Your Seat", SwingConstants.CENTER);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setBorder(BorderFactory.createEmptyBorder(20,0,10,0));

        // ===== LEGEND =====
        JPanel legendPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 5));
        legendPanel.setBackground(new Color(18,18,30));

        legendPanel.add(createLegendBox(new Color(22,163,74), "Available"));
        legendPanel.add(createLegendBox(new Color(200,200,200), "Booked"));

        // ===== SEAT PANEL =====
        JPanel seatPanel = new JPanel(new GridLayout(5,8,15,15));
        seatPanel.setBackground(new Color(18,18,30));
        seatPanel.setBorder(BorderFactory.createEmptyBorder(20,40,40,40));

        List<Integer> bookedSeats =
                new BookingDAO().getBookedSeats(frame.getSelectedBus());

        for(int i = 1; i <= 40; i++) {

            int seatNumber = i;

            JButton seatButton = new JButton();
            seatButton.setLayout(new BorderLayout());
            seatButton.setFocusPainted(false);
            seatButton.setFont(new Font("Segoe UI", Font.BOLD, 14));

            JLabel seatNumberLabel =
                    new JLabel(String.valueOf(i), SwingConstants.CENTER);
            seatNumberLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
            seatNumberLabel.setForeground(Color.WHITE);

            seatButton.add(seatNumberLabel, BorderLayout.CENTER);

            if(bookedSeats.contains(seatNumber)) {

                // 🔴 BOOKED
                seatButton.setBackground(new Color(200,200,200));
                seatButton.setEnabled(false);

                JLabel bookedLabel =
                        new JLabel("Booked", SwingConstants.CENTER);
                bookedLabel.setFont(new Font("Segoe UI", Font.PLAIN, 10));
                bookedLabel.setForeground(Color.RED);

                seatButton.add(bookedLabel, BorderLayout.SOUTH);

            } else {

                // 🟢 AVAILABLE
                seatButton.setBackground(new Color(22,163,74));
                seatButton.setForeground(Color.WHITE);

                seatButton.addActionListener(e -> {
                    frame.setSelectedSeat(seatNumber);
                    frame.showScreen("booking");
                });
            }

            seatPanel.add(seatButton);
        }

        // ===== BACK BUTTON =====
        JButton backBtn = new JButton("← Back");
        backBtn.addActionListener(e -> frame.showScreen("search"));

        add(title, BorderLayout.NORTH);
        add(legendPanel, BorderLayout.CENTER);
        add(seatPanel, BorderLayout.SOUTH);
        add(backBtn, BorderLayout.WEST);

        revalidate();
        repaint();
    }

    private JPanel createLegendBox(Color color, String text) {

        JPanel box = new JPanel(new FlowLayout(FlowLayout.LEFT,5,0));
        box.setBackground(new Color(18,18,30));

        JPanel colorBox = new JPanel();
        colorBox.setPreferredSize(new Dimension(20,20));
        colorBox.setBackground(color);

        JLabel label = new JLabel(text);
        label.setForeground(Color.WHITE);

        box.add(colorBox);
        box.add(label);

        return box;
    }
}