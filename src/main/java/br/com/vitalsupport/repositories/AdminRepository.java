package br.com.vitalsupport.repositories;

import br.com.vitalsupport.models.Admin;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class AdminRepository {
    private final SessionFactory sessionFactory;

    public AdminRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void save(Admin admin) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(admin);
            transaction.commit();
        }
    }

    public List<Admin> findAll(int page, int pageSize) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Admin> criteriaQuery = criteriaBuilder.createQuery(Admin.class);

            Root<Admin> root = criteriaQuery.from(Admin.class);
            criteriaQuery.select(root);

            int offset = (page - 1) * pageSize;

            return session.createQuery(criteriaQuery)
                    .setFirstResult(offset)
                    .setMaxResults(pageSize)
                    .getResultList();
        }
    }

    public Admin findOneById(long id) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Admin.class, id);
        }
    }

    public void update(Admin admin) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(admin);
            transaction.commit();
        }
    }

    public void delete(Admin admin) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.remove(admin);
            transaction.commit();
        }
    }
}
