package dao;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import db.DBConnection;
import model.Booking;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.and;

public class BookingDAO {

    private MongoCollection<Document> bookingCollection;
    private MongoCollection<Document> busCollection;

    public BookingDAO() {
        MongoDatabase db = DBConnection.getDatabase();
        bookingCollection = db.getCollection("bookings");
        busCollection = db.getCollection("buses");
    }

    public void saveBooking(Booking booking) {

        // Check duplicate seat
        Document existing = bookingCollection.find(
                and(
                        eq("busNumber", booking.getBusNumber()),
                        eq("seatNumber", booking.getSeatNumber())
                )
        ).first();

        if (existing != null) {
            throw new RuntimeException("Seat already booked!");
        }

        // Insert booking
        Document bookingDoc = new Document("userEmail", booking.getUserEmail())
                .append("busNumber", booking.getBusNumber())
                .append("seatNumber", booking.getSeatNumber())
                .append("passengerName", booking.getPassengerName());

        bookingCollection.insertOne(bookingDoc);

        // Decrease availableSeats
        busCollection.updateOne(
                eq("busNumber", booking.getBusNumber()),
                new Document("$inc", new Document("availableSeats", -1))
        );
    }

    public List<Integer> getBookedSeats(String busNumber) {

        List<Integer> seats = new ArrayList<>();

        FindIterable<Document> docs =
                bookingCollection.find(eq("busNumber", busNumber));

        for (Document doc : docs) {
            seats.add(doc.getInteger("seatNumber"));
        }

        return seats;
    }
}
