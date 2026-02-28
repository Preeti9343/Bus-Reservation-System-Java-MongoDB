package model;

public class Bus {

    private String busNumber;
    private String from;
    private String to;
    private int totalSeats;
    private int availableSeats;

    public Bus(String busNumber, String from, String to,
               int totalSeats, int availableSeats) {

        this.busNumber = busNumber;
        this.from = from;
        this.to = to;
        this.totalSeats = totalSeats;
        this.availableSeats = availableSeats;
    }

    public String getBusNumber() { return busNumber; }
    public String getFrom() { return from; }
    public String getTo() { return to; }
    public int getTotalSeats() { return totalSeats; }
    public int getAvailableSeats() { return availableSeats; }
}
