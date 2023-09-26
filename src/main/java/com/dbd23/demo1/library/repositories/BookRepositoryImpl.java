package com.dbd23.demo1.library.repositories;

import com.dbd23.demo1.library.LibraryException;
import com.dbd23.demo1.library.model.Author;
import com.dbd23.demo1.library.model.Book;
import com.dbd23.demo1.library.model.DigitalBook;
import com.dbd23.demo1.library.model.PhysicalBook;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class BookRepositoryImpl implements BookRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(Book book) throws LibraryException {
        Session session = null;
        try {
            session = this.sessionFactory.getCurrentSession();
            session.save(book);
        } catch (ConstraintViolationException e ) {
            throw new LibraryException("ConstraintViolation");
        }catch (Exception e ){
            throw new LibraryException(e.getMessage());
        }
    }

    @Override
    public List<Book> findAll() {
        return this.sessionFactory.getCurrentSession().createQuery(
                "from Book"
        ).list();
    }

    @Override
    public List<DigitalBook> findAllDigitalBook() {
         return this.sessionFactory.getCurrentSession().createQuery(
                "from DigitalBook"
        ).list();
    }

    @Override
    public Optional<Book> findByIsbn(String isbn) {
        return this.sessionFactory.getCurrentSession().createQuery(
                "from Book where isbn = :isbn"
        ).setParameter("isbn", isbn).uniqueResultOptional();
    }

    @Override
    public List<Book> findByAuthor(Long id) {
        return this.sessionFactory.getCurrentSession().createQuery(
                "select b from Book b join b.author a where a.id = :id"
        ).setParameter("id", id).list();
    }
}
