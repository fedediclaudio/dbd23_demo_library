package com.dbd23.demo1.library.repositories;

import com.dbd23.demo1.library.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    List<Author> findByDateOfBirthGreaterThan(LocalDate date);

    @Query("select a from Author a join a.books b group by a.id order by count(*) desc")
    List<Author> findByMoreBooks();

}
