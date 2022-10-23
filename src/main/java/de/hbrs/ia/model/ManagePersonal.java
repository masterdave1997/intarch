package de.hbrs.ia.model;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;

import org.bson.Document;

import static com.mongodb.client.model.Filters.*;

import java.util.ArrayList;
import java.util.List;

public class ManagePersonal implements ManagePersonalIF {
    public MongoCollection<Document> personalCollection;

    @Override
    public void createSalesMan(SalesMan record) {
        Document salesman = record.toDocument();
        loadCollection();
        personalCollection.insertOne(salesman);
    }

    @Override
    public void addPerformanceRecord(EvaluationRecord record, int sid) {
        Document performance = record.toDocument();
        loadCollection();
        personalCollection.updateOne(eq("id", sid), new Document("$set", new Document("EvaluationRecord",performance)));
    }

    @Override
    public SalesMan readSalesMan(int sid) {
        loadCollection();
        Document salesman = personalCollection.find(eq("id", sid)).first();
        assert salesman != null;
        return documentToSalesman(salesman);
    }

    @Override
    public List<SalesMan> querySalesMan(String attribute, String key) {
        loadCollection();
        FindIterable<Document> salesman = personalCollection.find(eq(key, attribute));
        List<SalesMan> salesMen = new ArrayList<>();
        for(Document s : salesman) {
            salesMen.add(documentToSalesman(s));
        }
        return salesMen;
    }

    @Override
    public EvaluationRecord readEvaluationRecords(int sid) {
        loadCollection();
        Document salesman = personalCollection.find(eq("id", sid)).first();
        assert salesman != null;
        return new EvaluationRecord(salesman.getInteger("ordersEvaluation"), salesman.getInteger("socialPerformanceEvaluation"));
    }

    public void loadCollection() {
        if(personalCollection == null) {
            DBConnector.connect();
            personalCollection = DBConnector.getDB().getCollection("Personal");
        }
    }

    public SalesMan documentToSalesman(Document salesman) {
        return new SalesMan(salesman.getString("firstname"), salesman.getString("lastname"), salesman.getInteger("id"));
    }

    public void dropDocument(int id) {
        personalCollection.deleteOne(eq("id", id));
    }
}
