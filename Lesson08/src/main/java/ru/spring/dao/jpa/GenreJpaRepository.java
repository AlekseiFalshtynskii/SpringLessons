package ru.spring.dao.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.spring.dao.GenreDao;
import ru.spring.model.Genre;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@SuppressWarnings("JpaQlInspection")
@Repository
@Transactional
public class GenreJpaRepository implements GenreDao {
    @PersistenceContext
    private EntityManager em;

    @Override
    public Long insert(Genre genre) {
        em.persist(genre);
        return genre.getId();
    }

    @Override
    public Long update(Genre genre) {
        em.merge(genre);
        return genre.getId();
    }

    @Override
    public Genre findById(Long id) {
        return em.find(Genre.class, id);
    }

    @Override
    public List<Genre> findAll() {
        TypedQuery<Genre> query = em.createQuery("select a from Genre a", Genre.class);
        return query.getResultList();
    }

    @Override
    public long count() {
        return findAll().size();
    }

    @Override
    public void deleteById(Long id) {
        Query query = em.createQuery("delete from Genre a where a.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public void deleteAll() {
        Query query = em.createQuery("delete from Genre a");
        query.executeUpdate();
    }
}
