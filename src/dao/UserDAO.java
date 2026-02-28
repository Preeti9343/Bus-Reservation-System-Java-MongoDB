package dao;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import db.DBConnection;
import model.User;
import org.bson.Document;
import org.mindrot.jbcrypt.BCrypt;

import static com.mongodb.client.model.Filters.eq;

public class UserDAO {

    private MongoCollection<Document> collection;

    public UserDAO() {
        MongoDatabase db = DBConnection.getDatabase();
        collection = db.getCollection("users");
    }

    // ✅ Register with Hashing
    public boolean registerUser(User user) {

        // Check if email already exists
        Document existing = collection.find(eq("email", user.getEmail())).first();

        if (existing != null) {
            throw new RuntimeException("Email already registered!");
        }

        // Hash password
        String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());

        Document doc = new Document("name", user.getName())
                .append("email", user.getEmail())
                .append("password", hashedPassword);

        collection.insertOne(doc);

        return true;
    }

    // ✅ Secure Login
    public boolean loginUser(String email, String password) {

        Document found = collection.find(eq("email", email)).first();

        if (found == null) return false;

        String storedHash = found.getString("password");

        return BCrypt.checkpw(password, storedHash);
    }
}