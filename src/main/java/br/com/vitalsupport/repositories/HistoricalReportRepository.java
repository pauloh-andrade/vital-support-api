package br.com.vitalsupport.repositories;

import br.com.vitalsupport.models.HistoricalReport;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class HistoricalReportRepository {
    private final SessionFactory sessionFactory;

    public HistoricalReportRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void save(HistoricalReport historicalReport)  throws Exception{
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(historicalReport);
            transaction.commit();
        }
    }

    public List<HistoricalReport> findAll(int page, int pageSize) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<HistoricalReport> criteriaQuery = criteriaBuilder.createQuery(HistoricalReport.class);

            Root<HistoricalReport> root = criteriaQuery.from(HistoricalReport.class);
            criteriaQuery.select(root);

            int offset = (page - 1) * pageSize;

            return session.createQuery(criteriaQuery)
                    .setFirstResult(offset)
                    .setMaxResults(pageSize)
                    .getResultList();
        }
    }

    public HistoricalReport findOneById(long id) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            return session.get(HistoricalReport.class, id);
        }
    }

    public void update(HistoricalReport historicalReport) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(historicalReport);
            transaction.commit();
        }
    }

    public void delete(HistoricalReport historicalReport) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.remove(historicalReport);
            transaction.commit();
        }
    }
}
