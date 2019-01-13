package ru.spring.dao.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.spring.dao.CommentDao;
import ru.spring.model.Comment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@SuppressWarnings("JpaQlInspection")
@Repository
@Transactional
public class CommentJpaRepository implements CommentDao {
    @PersistenceContext
    private EntityManager em;

    @Override
    public Long insert(Comment comment) {
        em.persist(comment);
        em.flush();
        return comment.getId();
    }

    @Override
    public Long update(Comment comment) {
        em.persist(comment);
        return comment.getId();
    }

    @Override
    public Comment findById(Long id) {
        return em.find(Comment.class, id);
    }

    @Override
    public List<Comment> findAll() {
        TypedQuery<Comment> query = em.createQuery("select a from Comment a", Comment.class);
        return query.getResultList();
    }

    @Override
    public long count() {
        return findAll().size();
    }

    @Override
    public void deleteById(Long id) {
        Query query = em.createQuery("delete from Comment a where a.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public void deleteAll() {
        Query query = em.createQuery("delete from Comment a");
        query.executeUpdate();
    }
}