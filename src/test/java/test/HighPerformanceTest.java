package test;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import de.hbrs.ia.model.ManagePersonal;
import de.hbrs.ia.model.ManagePersonalIF;
import de.hbrs.ia.model.SalesMan;
import org.bson.Document;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HighPerformanceTest {

//    private MongoClient client;
//    private MongoDatabase supermongo;
//    private MongoCollection<Document> salesmen;
    private static ManagePersonal mp;

    /**
     * Attention: You might update the version of the Driver
     * for newer version of MongoDB!
     * This tests run with MongoDB 4.2.17 Community
     */
//    @BeforeEach
//    void setUp() {
//        // Setting up the connection to a local MongoDB with standard port 27017
//        // must be started within a terminal with command 'mongod'.
//        client = new MongoClient("localhost", 27017);
//
//        // Get database 'highperformance' (creates one if not available)
//        supermongo = client.getDatabase("highperformance");
//
//        // Get Collection 'salesmen' (creates one if not available)
//        salesmen = supermongo.getCollection("salesmen");
//    }

    @BeforeAll
    static void setUp() {
        mp = new ManagePersonal();
        MongoClient client = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
        MongoDatabase database = client.getDatabase("database");
        mp.personalCollection = database.getCollection("Personal");
    }


    // Wir haben den anderen Test gelöscht, da wir objektorientiert programmiert haben.

    @Test
    void insertSalesMan() {
        // CREATE (Storing) the salesman object
        SalesMan s1 = new SalesMan("Sascha", "Alda", 90133);

        // ... now storing the object
        mp.createSalesMan(s1);

        // READ (Finding) the stored Documnent
        SalesMan s2 = mp.readSalesMan(90133);
        System.out.println("Printing the object (JSON): " + s2.toDocument() );

        // Assertion
        Integer id = s2.getId();
        assertEquals( 90133 , id );

        // Deletion
        mp.dropDocument(90133);
    }
}