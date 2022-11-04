package org.hbrs.semesterprojekt.repository;

import org.hbrs.semesterprojekt.entities.Salesman;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesmanRepository extends MongoRepository<Salesman, String> {
    void deleteById(String id);
}
