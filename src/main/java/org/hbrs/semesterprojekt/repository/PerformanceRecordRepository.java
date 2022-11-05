package org.hbrs.semesterprojekt.repository;

import org.hbrs.semesterprojekt.entities.PerformanceRecord;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PerformanceRecordRepository extends MongoRepository<PerformanceRecord, String> {
    List<PerformanceRecord> findAllBySid(String sid);
}
