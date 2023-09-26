package com.dbd23.demo1.library.repositories;

import com.dbd23.demo1.library.LibraryException;
import com.dbd23.demo1.library.model.Author;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public class AuthorRepositoryImpl implements AuthorRepository{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(Author author) throws LibraryException{
        Session session = null;
        try {
            session = this.sessionFactory.getCurrentSession();
            session.save(author);
        } catch (Exception e ){
            throw new LibraryException(e.getMessage());
        }
    }

    @Override
    public void update(Author author) throws LibraryException {
        Session session = null;
        try {
            session = this.sessionFactory.getCurrentSession();
            session.save(author);
        } catch (Exception e ){
            throw new LibraryException(e.getMessage());
        }
    }

    @Override
    public boolean delete(Author author) throws LibraryException {
        Session session = null;
        try {
            session = this.sessionFactory.getCurrentSession();
            session.delete(author);
            return true;
        } catch (Exception e ){
            throw new LibraryException(e.getMessage());
        }
    }

    @Override
    public Optional<Author> findById(Long id) {
        return this.sessionFactory.getCurrentSession().createQuery(
                "from Author where id = :id"
        ).setParameter("id", id).uniqueResultOptional();
    }

    @Override
    public List<Author> findAll() {
        return this.sessionFactory.getCurrentSession().createQuery(
                "from Author"
        ).list();
    }

    @Override
    public List<Author> findByYoung() {
        LocalDate date = LocalDate.now().minusYears(60);
        return this.sessionFactory.getCurrentSession().createQuery(
                "from Author a where a.dateOfBirth > :date"
        ).setParameter("date", date).list();
    }

    @Override
    public Optional<Author> findWithMoreBooks() {
        return this.sessionFactory.getCurrentSession().createQuery(
                "select a from Author a join a.books b group by a.id order by count(*) desc"
        ).setMaxResults(1).uniqueResultOptional();
    }
}
