package de.hbrs.ia.model;

import org.bson.Document;

public class EvaluationRecord {
    private final int ordersEvaluation;
    private final int socialPerformanceEvaluation;

    public EvaluationRecord(int ordersEvaluation, int socialPerformanceEvaluation) {
        this.ordersEvaluation = ordersEvaluation;
        this.socialPerformanceEvaluation = socialPerformanceEvaluation;
    }

    public Document toDocument() {
        org.bson.Document document = new Document();
        document.append("ordersEvaluation" , this.ordersEvaluation);
        document.append("socialPerformanceEvaluation" , this.socialPerformanceEvaluation);
        return document;
    }
}
