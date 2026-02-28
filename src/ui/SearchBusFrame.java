package ui;

import dao.BusDAO;
import model.Bus;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SearchBusFrame extends JPanel {

    private static final long serialVersionUID = 1L;
    private MainFrame frame;

    private JComboBox<String> fromBox;
    private JComboBox<String> toBox;
    private JPanel resultPanel;

    public SearchBusFrame(MainFrame frame) {

        this.frame = frame;

        setLayout(new BorderLayout());
        setBackground(new Color(18,18,30));
        setBorder(BorderFactory.createEmptyBorder(30,30,30,30));

        JLabel title = new JLabel("Search Bus", SwingConstants.CENTER);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));

        fromBox = new JComboBox<>(new String[]{"Bhopal","Indore","Delhi"});
        toBox = new JComboBox<>(new String[]{"Bhopal","Indore","Delhi"});

        JButton searchBtn = new JButton("Search");
        searchBtn.setBackground(new Color(79,70,229));
        searchBtn.setForeground(Color.WHITE);
        searchBtn.setFocusPainted(false);

        searchBtn.addActionListener(e -> searchBus());

        JPanel topPanel = new JPanel(new GridLayout(3,2,10,10));
        topPanel.setBackground(new Color(18,18,30));

        JLabel fromLabel = new JLabel("From");
        fromLabel.setForeground(Color.WHITE);

        JLabel toLabel = new JLabel("To");
        toLabel.setForeground(Color.WHITE);

        topPanel.add(fromLabel);
        topPanel.add(fromBox);
        topPanel.add(toLabel);
        topPanel.add(toBox);
        topPanel.add(new JLabel(""));
        topPanel.add(searchBtn);

        resultPanel = new JPanel();
        resultPanel.setLayout(new BoxLayout(resultPanel, BoxLayout.Y_AXIS));
        resultPanel.setBackground(new Color(18,18,30));

        JScrollPane scrollPane = new JScrollPane(resultPanel);
        scrollPane.setPreferredSize(new Dimension(500,200));

        JButton backBtn = new JButton("← Back");
        backBtn.addActionListener(e -> frame.showScreen("dashboard"));

        add(title, BorderLayout.NORTH);
        add(topPanel, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);
        add(backBtn, BorderLayout.WEST);
    }

    private void searchBus() {

        resultPanel.removeAll();

        String from = (String) fromBox.getSelectedItem();
        String to = (String) toBox.getSelectedItem();

        // Validation
        if(from.equals(to)) {
            JOptionPane.showMessageDialog(this,
                    "From and To cannot be same!");
            return;
        }

        List<Bus> buses = new BusDAO().searchBus(from, to);

        if(buses.isEmpty()) {
            JLabel noBus = new JLabel("No buses available.");
            noBus.setForeground(Color.WHITE);
            resultPanel.add(noBus);
        }

        for(Bus bus : buses) {

            JButton busBtn = new JButton(
                    bus.getBusNumber() +
                            " | Seats: " + bus.getAvailableSeats() +
                            "/" + bus.getTotalSeats()
            );

            busBtn.setBackground(new Color(22,163,74));
            busBtn.setForeground(Color.WHITE);
            busBtn.setFocusPainted(false);

            busBtn.addActionListener(e -> {
                frame.setSelectedBus(bus.getBusNumber());
                frame.showScreen("seat");
            });

            resultPanel.add(busBtn);
            resultPanel.add(Box.createVerticalStrut(10));
        }

        resultPanel.revalidate();
        resultPanel.repaint();
    }
}