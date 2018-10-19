package lt.vu.mif.jate.tasks.task03.jpa;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import lt.vu.mif.jate.tasks.task03.jpa.model.Subject;

public class DbManager implements AutoCloseable {

    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("stud");
    private final EntityManager em;

    public DbManager() {
        this.em = emf.createEntityManager();
    }

    public <T> List<T> getListOf(Class<T> clazz) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(clazz);
        Root<T> root = cq.from(clazz);
        cq.select(root);
        TypedQuery<T> q = em.createQuery(cq);
        return q.getResultList();
    }

    public <T> List<T> getListOf(Class<T> clazz, String string) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(clazz);
        Root<T> root = cq.from(clazz);
        cq.select(root);
        cq.orderBy(cb.asc(root.get(string)));
        TypedQuery<T> q = em.createQuery(cq);
        return q.getResultList();
    }

    Subject getById(Class<Subject> clazz, int id) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Subject> cq = cb.createQuery(clazz);
        Root<Subject> root = cq.from(clazz);
        cq.where(cb.equal(root.get("id"), id));
        TypedQuery<Subject> q = em.createQuery(cq);
        return q.getSingleResult();
    }

    @Override
    public void close() throws Exception {
    }
}
