package com.dbd23.demo1.library.repositories;

import com.dbd23.demo1.library.model.DigitalBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DigitalBookRepository extends JpaRepository<DigitalBook, Long> {
}
