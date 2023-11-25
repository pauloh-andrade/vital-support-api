package br.com.vitalsupport.repositories;

import br.com.vitalsupport.models.SymptomHistory;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class SymptomHistoryRepository {
    private final SessionFactory sessionFactory;

    public SymptomHistoryRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void save(SymptomHistory symptomHistory) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(symptomHistory);
            transaction.commit();
        }
    }

    public List<SymptomHistory> findAll(int page, int pageSize) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<SymptomHistory> criteriaQuery = criteriaBuilder.createQuery(SymptomHistory.class);

            Root<SymptomHistory> root = criteriaQuery.from(SymptomHistory.class);
            criteriaQuery.select(root);

            int offset = (page - 1) * pageSize;

            return session.createQuery(criteriaQuery)
                    .setFirstResult(offset)
                    .setMaxResults(pageSize)
                    .getResultList();
        }
    }

    public SymptomHistory findOneById(long id) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            return session.get(SymptomHistory.class, id);
        }
    }

    public void update(SymptomHistory symptomHistory) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(symptomHistory);
            transaction.commit();
        }
    }

    public void delete(SymptomHistory symptomHistory) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.remove(symptomHistory);
            transaction.commit();
        }
    }
}
