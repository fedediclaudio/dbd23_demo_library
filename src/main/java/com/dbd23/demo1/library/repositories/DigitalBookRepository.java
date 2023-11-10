package com.dbd23.demo1.library.repositories;

import com.dbd23.demo1.library.model.DigitalBook;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DigitalBookRepository extends MongoRepository<DigitalBook, ObjectId> {
}
