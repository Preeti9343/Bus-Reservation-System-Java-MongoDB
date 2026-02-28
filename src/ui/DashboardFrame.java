package ui;

import javax.swing.*;
import java.awt.*;

public class DashboardFrame extends JPanel {

    private static final long serialVersionUID = 1L;
    private MainFrame frame;

    public DashboardFrame(MainFrame frame) {

        this.frame = frame;

        setLayout(new GridBagLayout());
        setBackground(new Color(18,18,30));

        JPanel card = new JPanel();
        card.setBackground(new Color(30,30,45));
        card.setPreferredSize(new Dimension(400,250));
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("DASHBOARD");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton searchBtn = new JButton("Search Bus");
        styleButton(searchBtn, new Color(79,70,229));
        searchBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        searchBtn.addActionListener(e -> {
            frame.showScreen("search");
        });

        card.add(Box.createVerticalStrut(30));
        card.add(title);
        card.add(Box.createVerticalStrut(30));
        card.add(searchBtn);

        add(card);
    }

    private void styleButton(JButton button, Color bg) {
        button.setBackground(bg);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("Segoe UI", Font.BOLD, 15));
        button.setBorder(BorderFactory.createEmptyBorder(10,25,10,25));
    }
}

