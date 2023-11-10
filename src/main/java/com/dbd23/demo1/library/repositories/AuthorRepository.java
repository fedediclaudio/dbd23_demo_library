package com.dbd23.demo1.library.repositories;

import com.dbd23.demo1.library.model.Author;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends MongoRepository<Author, ObjectId> {

    List<Author> findByDateOfBirthGreaterThan(LocalDate date);

    @Query("select a from Author a join a.books b group by a.id order by count(*) desc")
    List<Author> findByMoreBooks();

    @Aggregation({
            "{$group: {_id: '$_id', author: {$first: '$$ROOT'}, bookCount: {$sum: {$size:'$$ROOT.books'}}}}",
            "{$sort: {bookCount: -1}}",
            "{$limit: 1}",
            "{$project: { '_id': '$author._id', fullname: '$author.fullname', dateOfBirth: '$author.dateOfBirth', version: '$author.version', books: '$author.books' }}"
    })
    List<Author> findByMoreBooks2();

}
