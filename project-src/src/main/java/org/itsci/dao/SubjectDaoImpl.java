package org.itsci.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.itsci.exam.model.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class SubjectDaoImpl implements SubjectDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Subject> getSubjects() {
        Class<Subject> clazz = Subject.class;
        Query<Subject> query;
        CriteriaQuery<Subject> criteria;
        Root<Subject> root;
        List<Subject> results;

        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        criteria = builder.createQuery(clazz);
        root = criteria.from(clazz);
        criteria.select(root);

        query = session.createQuery(criteria);
        results = query.getResultList();
        return results;
    }

    @Override
    public void saveSubject(Subject subject) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(subject);
    }

    @Override
    public Subject getSubject(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Subject result = session.get(Subject.class, id);
        return result;
    }

    @Override
    public void deleteSubject(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Subject obj = session.get(Subject.class, id);
        session.delete(obj);
        session.flush() ;
    }
}
