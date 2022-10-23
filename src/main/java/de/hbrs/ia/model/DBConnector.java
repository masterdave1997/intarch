package de.hbrs.ia.model;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;

import java.util.Scanner;

public class DBConnector {
    private static MongoClient client;
    private static MongoDatabase database;

    public static void connect() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Bitte gebe eine URI ein.");
        String url = sc.next();
        System.out.println("Bitte gebe einen Datenbank-Namen ein.");
        String dbname = sc.next();
        client = new MongoClient(new MongoClientURI(url));
        database = client.getDatabase(dbname);
        sc.close();
    }

    public static void connect(String url, String dbname) {
        client = new MongoClient(new MongoClientURI(url));
        database = client.getDatabase(dbname);
    }

    public static MongoDatabase getDB() {
        return database;
    }

}
