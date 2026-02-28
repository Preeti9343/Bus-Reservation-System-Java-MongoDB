package ui;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private static final long serialVersionUID = 1L;   // 👈 YAHAN ADD KARNA HAI

    private CardLayout cardLayout;
    private JPanel container;

    // Global State
    private String loggedInEmail;
    private String selectedBus;
    private int selectedSeat;
    private String passengerName;

    private TicketFrame ticketFrame;

    public MainFrame() {

        setTitle("Bus Reservation System");
        setSize(900,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        container = new JPanel(cardLayout);

        container.add(new LoginFrame(this), "login");
        container.add(new RegisterFrame(this), "register");
        container.add(new DashboardFrame(this), "dashboard");
        container.add(new SearchBusFrame(this), "search");
        container.add(new SeatSelectionFrame(this), "seat");
        container.add(new BookingFrame(this), "booking");

        ticketFrame = new TicketFrame(this);
        container.add(ticketFrame, "ticket");

        add(container);
        showScreen("login");

        setVisible(true);
    }

    public void showScreen(String name) {
        cardLayout.show(container, name);
    }

    // getters & setters...
 // =====================
 // GETTERS & SETTERS
 // =====================

 public void setLoggedInEmail(String email) {
     this.loggedInEmail = email;
 }

 public String getLoggedInEmail() {
     return loggedInEmail;
 }

 public void setSelectedBus(String bus) {
     this.selectedBus = bus;
 }

 public String getSelectedBus() {
     return selectedBus;
 }

 public void setSelectedSeat(int seat) {
     this.selectedSeat = seat;
 }

 public int getSelectedSeat() {
     return selectedSeat;
 }

 public void setPassengerName(String name) {
     this.passengerName = name;
 }

 public String getPassengerName() {
     return passengerName;
 }

 public void updateTicket() {
     ticketFrame.setTicketDetails(
         loggedInEmail,
         selectedBus,
         selectedSeat,
         passengerName
     );
 }

}
