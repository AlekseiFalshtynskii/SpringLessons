package ru.spring.dao.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.spring.dao.BookDao;
import ru.spring.model.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@SuppressWarnings("JpaQlInspection")
@Repository
@Transactional
public class BookJpaRepository implements BookDao {
    @PersistenceContext
    private EntityManager em;

    @Override
    public Long insert(Book book) {
        em.persist(book);
        return book.getId();
    }

    @Override
    public Long update(Book book) {
        em.merge(book);
        return book.getId();
    }

    @Override
    public Book findById(Long id) {
        return em.find(Book.class, id);
    }

    @Override
    public List<Book> findAll() {
        TypedQuery<Book> query = em.createQuery("select b from Book b", Book.class);
        return query.getResultList();
    }

    @Override
    public long count() {
        return findAll().size();
    }

    @Override
    public void deleteById(Long id) {
        Query query = em.createQuery("delete from Book b where b.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public void deleteAll() {
        Query query = em.createQuery("delete from Book b");
        query.executeUpdate();
    }
}
