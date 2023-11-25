package br.com.vitalsupport.repositories;

import br.com.vitalsupport.models.Clinic;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class ClinicRepository {
    private final SessionFactory sessionFactory;

    public ClinicRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void save(Clinic clinic) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(clinic);
            transaction.commit();
        }
    }

    public List<Clinic> findAll(int page, int pageSize) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Clinic> criteriaQuery = criteriaBuilder.createQuery(Clinic.class);

            Root<Clinic> root = criteriaQuery.from(Clinic.class);
            criteriaQuery.select(root);

            int offset = (page - 1) * pageSize;

            return session.createQuery(criteriaQuery)
                    .setFirstResult(offset)
                    .setMaxResults(pageSize)
                    .getResultList();
        }
    }

    public Clinic findOneById(long id) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Clinic.class, id);
        }
    }

    public void update(Clinic clinic) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(clinic);
            transaction.commit();
        }
    }

    public void delete(Clinic clinic) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.remove(clinic);
            transaction.commit();
        }
    }
}
