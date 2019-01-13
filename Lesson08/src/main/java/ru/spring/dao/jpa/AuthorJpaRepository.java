package ru.spring.dao.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.spring.dao.AuthorDao;
import ru.spring.model.Author;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@SuppressWarnings("JpaQlInspection")
@Repository
@Transactional
public class AuthorJpaRepository implements AuthorDao {
    @PersistenceContext
    private EntityManager em;

    @Override
    public Long insert(Author author) {
        em.persist(author);
        em.flush();
        return author.getId();
    }

    @Override
    public Long update(Author author) {
        em.persist(author);
        return author.getId();
    }

    @Override
    public Author findById(Long id) {
        return em.find(Author.class, id);
    }

    @Override
    public List<Author> findAll() {
        TypedQuery<Author> query = em.createQuery("select a from Author a", Author.class);
        return query.getResultList();
    }

    @Override
    public long count() {
        return findAll().size();
    }

    @Override
    public void deleteById(Long id) {
        Query query = em.createQuery("delete from Author a where a.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public void deleteAll() {
        Query query = em.createQuery("delete from Author a");
        query.executeUpdate();
    }
}
