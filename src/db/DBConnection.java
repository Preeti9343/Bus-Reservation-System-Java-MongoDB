package db;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class DBConnection {

    private static MongoClient client =
            MongoClients.create("mongodb://localhost:27017");

    public static MongoDatabase getDatabase() {
        return client.getDatabase("busDB");
    }
}
