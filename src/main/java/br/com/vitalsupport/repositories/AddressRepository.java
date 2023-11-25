package br.com.vitalsupport.repositories;

import br.com.vitalsupport.models.Address;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class AddressRepository {
    private final SessionFactory sessionFactory;

    public AddressRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void save(Address address) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(address);
            transaction.commit();
        }
    }

    public List<Address> findAll(int page, int pageSize) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Address> criteriaQuery = criteriaBuilder.createQuery(Address.class);

            Root<Address> root = criteriaQuery.from(Address.class);
            criteriaQuery.select(root);

            int offset = (page - 1) * pageSize;

            return session.createQuery(criteriaQuery)
                    .setFirstResult(offset)
                    .setMaxResults(pageSize)
                    .getResultList();
        }
    }

    public Address findOneById(long id) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Address.class, id);
        }
    }

    public void update(Address address) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(address);
            transaction.commit();
        }
    }

    public void delete(Address address) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.remove(address);
            transaction.commit();
        }
    }
}
