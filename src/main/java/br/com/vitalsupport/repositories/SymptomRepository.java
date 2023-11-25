package br.com.vitalsupport.repositories;

import br.com.vitalsupport.models.Symptom;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class SymptomRepository {
    private final SessionFactory sessionFactory;

    public SymptomRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void save(Symptom symptom) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(symptom);
            transaction.commit();
        }
    }

    public List<Symptom> findAll(int page, int pageSize) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Symptom> criteriaQuery = criteriaBuilder.createQuery(Symptom.class);

            Root<Symptom> root = criteriaQuery.from(Symptom.class);
            criteriaQuery.select(root);

            int offset = (page - 1) * pageSize;

            return session.createQuery(criteriaQuery)
                    .setFirstResult(offset)
                    .setMaxResults(pageSize)
                    .getResultList();
        }
    }

    public Symptom findOneById(long id) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Symptom.class, id);
        }
    }

    public void update(Symptom symptom) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(symptom);
            transaction.commit();
        }
    }

    public void delete(Symptom symptom) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.remove(symptom);
            transaction.commit();
        }
    }
}
