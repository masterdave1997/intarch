package de.hbrs.ia.model;

import java.util.List;

public interface ManagePersonalIF {

    public void createSalesMan( SalesMan record );

    public void addPerformanceRecord( EvaluationRecord record , int sid );

    public void dropDocument(int id);

    public SalesMan readSalesMan( int sid );

    public List<SalesMan> querySalesMan(String attribute , String key );

    public EvaluationRecord readEvaluationRecords( int sid );
}
