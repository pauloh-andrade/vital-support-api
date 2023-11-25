package br.com.vitalsupport.repositories;

import br.com.vitalsupport.models.State;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class StateRepository {
    private final SessionFactory sessionFactory;

    public StateRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void save(State state) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(state);
            transaction.commit();
        }
    }

    public List<State> findAll(int page, int pageSize) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<State> criteriaQuery = criteriaBuilder.createQuery(State.class);

            Root<State> root = criteriaQuery.from(State.class);
            criteriaQuery.select(root);

            int offset = (page - 1) * pageSize;

            return session.createQuery(criteriaQuery)
                    .setFirstResult(offset)
                    .setMaxResults(pageSize)
                    .getResultList();
        }
    }

    public State findOneById(int id) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            return session.get(State.class, id);
        }
    }

    public void update(State state) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(state);
            transaction.commit();
        }
    }

    public void delete(State state) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.remove(state);
            transaction.commit();
        }
    }
}
