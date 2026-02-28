package dao;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import db.DBConnection;
import model.Bus;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;

public class BusDAO {

    private MongoCollection<Document> collection;

    public BusDAO() {
        MongoDatabase db = DBConnection.getDatabase();
        collection = db.getCollection("buses");
    }

    // Insert Bus
    public void addBus(Bus bus) {

        Document doc = new Document("busNumber", bus.getBusNumber())
                .append("from", bus.getFrom().trim())
                .append("to", bus.getTo().trim())
                .append("totalSeats", bus.getTotalSeats())
                .append("availableSeats", bus.getAvailableSeats());

        collection.insertOne(doc);
    }

    // Search Bus (Fixed + Case Insensitive)
    public List<Bus> searchBus(String from, String to) {

        List<Bus> busList = new ArrayList<>();

        from = from.trim();
        to = to.trim();

        FindIterable<Document> result = collection.find(and(
                eq("from", from),
                eq("to", to)
        )).collation(
                com.mongodb.client.model.Collation.builder()
                        .locale("en")
                        .collationStrength(
                                com.mongodb.client.model.CollationStrength.SECONDARY
                        )
                        .build()
        );

        for (Document doc : result) {

            Bus bus = new Bus(
                    doc.getString("busNumber"),
                    doc.getString("from"),
                    doc.getString("to"),
                    doc.getInteger("totalSeats"),
                    doc.getInteger("availableSeats")
            );

            busList.add(bus);
        }

        return busList;
    }
}