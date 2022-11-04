package org.hbrs.semesterprojekt.entities;

import org.bson.Document;

public record PerformanceRecord(int ordersEvaluation, int socialPerformanceEvaluation) {
    public Document toDocument() {
        Document document = new Document();
        document.append("ordersEvaluation", this.ordersEvaluation);
        document.append("socialPerformanceEvaluation", this.socialPerformanceEvaluation);
        return document;
    }
}
