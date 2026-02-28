package model;

public class Booking {

    private String userEmail;
    private String busNumber;
    private int seatNumber;
    private String passengerName;

    public Booking(String userEmail, String busNumber,
                   int seatNumber, String passengerName) {

        this.userEmail = userEmail;
        this.busNumber = busNumber;
        this.seatNumber = seatNumber;
        this.passengerName = passengerName;
    }

    public String getUserEmail() { return userEmail; }
    public String getBusNumber() { return busNumber; }
    public int getSeatNumber() { return seatNumber; }
    public String getPassengerName() { return passengerName; }
}
